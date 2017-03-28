/*
 * 
 * 
 * 
 */
package servlet;

import bdd.CheckData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bdd.Const;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ContentEntity;
import entity.Entity;
import entity.MainEntity;
import entity.MainEntity.MainEntitySuccess;
import entity.UserEntity;
import enumerations.Code;
import exceptions.NoCheckException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import enumerations.Param;
import enumerations.Session;
import enumerations.TypeProp;
import exceptions.ClientException;
import exceptions.ExceptionGeree;
import java.io.StringWriter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;

/**
 * Controleur.java
 *
 * Un Controleur est une Servlet.
 *
 * Dans la majorité des cas un Controleur n'agit pas lui-même.
 * Il appele le {@link ControleurAction} correspondant au module demandé,
 * qui va alors retourner les données que le controleur va envoyer au client.
 * L'appel des {@link ControleurAction} se fait via des annotations:
 * {@link ModuleAction}.
 *
 * Note: il n'est pas rare de trouver des Controleur au contenu vide. Cela
 * signifie qu'ils se contentent de faire la redirection vers les
 * {@link ControleurAction} correspondants.
 *
 */
public abstract class Controleur extends HttpServlet {

	/**
	 * Package source utilisé par
	 * ${@link Reflections#Reflections(java.lang.Object...)}
	 */
	private static final String REFLECTION_PACKAGE = "servlet";

	/**
	 * Nom du paramètre module utilisé par le client
	 */
	protected static final String PARAM_MODULE = "m";

	/**
	 * Utilisé pour générer le JSON
	 */
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	/**
	 * Données utilisateur
	 */
	protected UserEntity user;

	/**
	 * Lors d'un Post client (uniquement en Ajax!).
	 * On récupère ses données utilisateur depuis la session.
	 * Puis les données que l'on va renvoyer au client {@link MainEntity}.
	 * Puis le controleur l'envoie.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = (UserEntity) request.getSession().getAttribute(Session.USER.tostring);

		MainEntity me = this.onPost(request, response);

		this.sendReturn(me, response);
	}

	/**
	 * Récupère le module demandé par la requête client.
	 *
	 * Via introspection, récupère le {@link ControleurAction} correspondant.
	 * Lancement de l'action récupérée.
	 * On récupère le {@link ContentEntity} renvoyé qu'on ajoute au
	 * {@link MainEntity} que l'on va envoyer au client.
	 * En cas d'exception on défini le {@link MainEntity} d'après l'exception.
	 *
	 * Renvoie le {@link MainEntity} demandé par
	 * {@link Controleur#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

		//Récupération du module s'il existe, sinon sa valeur par défaut ("")
		String module = Optional.ofNullable(request.getParameter(PARAM_MODULE)).orElse("");
		System.out.println("Module: '" + module + "'");

		try {
			//Par introspection on récupère les ControleurAction avec annotation
			Reflections reflections = new Reflections(REFLECTION_PACKAGE);
			Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ModuleAction.class);

			Class<ControleurAction> ca;
			try {
				ca = annotated.stream().filter(c -> {
					ModuleAction ma = c.getAnnotation(ModuleAction.class);

					return this.getClass().equals(ma.servlet()) && module.equals(ma.module());
				}).map(c -> (Class<ControleurAction>) c).collect(Collectors.toList()).get(0);
			} catch (IndexOutOfBoundsException e) {
				throw new ClientException("Tentative d'accès à un module inexistant: " + module);
			}

			//Nouvelle instance de l'action
			ControleurAction action = ca.newInstance();

			//On retourne un succes avec le comme données le retour de l'action
			return new MainEntitySuccess(action.act(this, user, request, response));
		} catch (Exception e) {
			e.printStackTrace(System.out);

			//Définition du code d'erreur
			Code code;
			if (e instanceof ExceptionGeree) {
				code = ((ExceptionGeree) e).getCodeErreur();
			} else {
				code = Code.E_SERVEUR;
			}

			//Définition du message de debug
			String message_debug = null;
			try (StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw, true)) {
				e.printStackTrace(pw);
				message_debug = sw.getBuffer().toString();
			} catch (IOException ex) {
				Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
			}

			//On retourne un echec
			return new MainEntity(false, code, message_debug);
		}
	}

	/**
	 * Envoi au client des données en JSON
	 *
	 * @param e
	 * @param response
	 */
	protected void sendReturn(MainEntity e, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {

			out.print(this.entityToJSONString(e));
		} catch (IOException ex) {
			Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Vérifie l'id envoyé avec l'id user possédé.
	 *
	 * @param id
	 * @throws NoCheckException Si l'id ne correspond pas
	 */
	public void checkIdUser(long id) throws NoCheckException {
		if (this.user.getId_user() != id) {
			throw new NoCheckException("Id_user différents: " + this.user.getId_user() + " != " + id);
		}
	}

	/**
	 * Transform une Entity en String (format JSON)
	 *
	 * @param e
	 * @return Un String (format JSON)
	 * @throws JsonProcessingException
	 */
	protected String entityToJSONString(Entity e) throws JsonProcessingException {
		return JSON_MAPPER.writeValueAsString(e);
	}

	/**
	 * Retourne le CheckData correspondant à l'id et au nom de propriété envoyé.
	 *
	 * @param id_typeprop id du type de propriete (id BDD)
	 * @param nomProp     nom de la propriété (nom BDD)
	 * @return Le CheckData correspondant
	 */
	protected CheckData getCheckDataFromTypeProp(int id_typeprop, String nomProp) {

		switch (nomProp) {
			case "login":
				return Const.CD_PSEUDO;
			case "mdp":
				return Const.CD_MDP;
			default:
				return TypeProp.getTypeProp(id_typeprop).getCd();
		}
	}

	/**
	 * Vérifie la présence et le format d'un paramètre d'après le Param fourni.
	 *
	 * @param <T>   Moyen d'obtenir le type voulu sans cast
	 * @param param
	 * @param req
	 * @return Le paramètre souhaité casté dans le type voulu par le Param
	 * @throws NoCheckException Si le paramètre est absent, ou dans un mauvais
	 *                          format
	 */
	public <T> T checkParam(Param param, ServletRequest req) throws NoCheckException {
		String value = req.getParameter(param.tostring);
		if (!Param.checkParam(param, value)) {
			throw new NoCheckException(param, value);
		}
		return param.getCastValue(value);
	}

	/**
	 * Vérifie le format d'une valeur d'après un CheckData et une taille voulu.
	 *
	 * @param <T>
	 * @param value
	 * @param cd
	 * @param tailleMin
	 * @param tailleMax
	 * @return
	 * @throws NoCheckException Si la valeur ne respecte pas le format
	 */
	public <T> T check(String value, CheckData cd, int tailleMin, int tailleMax) throws NoCheckException {
		if (!Param.check(cd, value)) {
			throw new NoCheckException(cd, value);
		}
		T val_final = Param.getCastValue(cd, value);

		if (val_final instanceof String) {
			String s = (String) val_final;
			if (s.length() >= tailleMin && s.length() <= tailleMax) {
				return val_final;
			}
		}
		if (val_final instanceof Boolean) {
			return val_final;
		}
		if (val_final instanceof Number) {
			Number n = (Number) val_final;
			if ((val_final instanceof Integer || val_final instanceof Long)
					&& (n.longValue() >= tailleMin && n.longValue() <= tailleMax)) {
				return val_final;
			}
			if (val_final instanceof Double
					&& (n.doubleValue() >= tailleMin && n.doubleValue() <= tailleMax)) {
				return val_final;
			}
		}
		if (val_final instanceof Date) {
			Date d = (Date) val_final;
			if (d.getTime() >= tailleMin && d.getTime() <= tailleMax) {
				return val_final;
			}
		}

		throw new NoCheckException(cd, value);
	}

}
