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
 */
public abstract class Controleur extends HttpServlet {

	private static final String REFLECTION_PACKAGE = "servlet";
	protected static final String PARAM_MODULE = "m";
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	protected UserEntity user;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = (UserEntity) request.getSession().getAttribute(Session.USER.tostring);

		MainEntity me = this.onPost(request, response);

		this.sendReturn(me, response);
	}

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

			ControleurAction action = ca.newInstance();

			return new MainEntitySuccess(action.act(this, user, request, response));
		} catch (Exception e) {
			e.printStackTrace(System.out);
			Code code;
			if (e instanceof ExceptionGeree) {
				code = ((ExceptionGeree) e).getCodeErreur();
			} else {
				code = Code.E_SERVEUR;
			}
			String message_debug = null;
			try (StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw, true)) {
				e.printStackTrace(pw);
				message_debug = sw.getBuffer().toString();
			} catch (IOException ex) {
				Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
			}
			return new MainEntity(false, code, message_debug);
		}
	}

	protected void sendReturn(MainEntity e, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {

			out.print(this.entityToJSONString(e));
		} catch (IOException ex) {
			Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void checkIdUser(long id) throws NoCheckException {
		if (this.user.getId_user() != id) {
			throw new NoCheckException("Id_user différents: " + this.user.getId_user() + " != " + id);
		}
	}

	protected String entityToJSONString(Entity e) throws JsonProcessingException {
		return JSON_MAPPER.writeValueAsString(e);
	}

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

	public <T> T checkParam(Param param, ServletRequest req) throws NoCheckException {
		String value = req.getParameter(param.tostring);
		if (!Param.checkParam(param, value)) {
			throw new NoCheckException(param, value);
		}
		return param.getCastValue(value);
	}

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
