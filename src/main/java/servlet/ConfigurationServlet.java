/*
 * 
 * 
 * 
 */
package servlet;

import bdd.CheckData;
import bdd.Modele.TypeValeurProp;
import entity.MainEntity;
import entity.MainEntity.MainEntityError;
import entity.ValeurInfos;
import entity.VisibiliteInfos;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.ConfigurationModele;
import servlet.enumerations.Code;
import servlet.enumerations.Param;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

		String module = Optional.ofNullable(request.getParameter(PARAM_MODULE)).orElse("");

		switch (module) {
			case "add_val":
				return addVal(request, response);
			case "update_val":
				return updateVal(request, response);
			case "remove_site":
				return removeSite(request, response);
			default:
				return new MainEntityError(Code.E_SERVEUR);
		}
	}

	private MainEntity addVal(HttpServletRequest request, HttpServletResponse response) {
		boolean success = false;
		Code code;
		try {
			long id_prop = this.checkParam(Param.ID_PROP, request);
			String form_val = this.checkParam(Param.VAL, request);

			ConfigurationModele modele = new ConfigurationModele();

			ValeurInfos infos = modele.getProprieteRecord(id_prop);

			TypeValeurProp tvp = infos.getTvp();

			Object val_final = this.checkVal(form_val, infos);

			modele.addValeur(id_prop, user.getId_user(), val_final, tvp.getVal_(), tvp.getValeur_val());

			code = Code.OK;
			success = true;
		} catch (NoCheckException ex) {
			code = Code.E_CONFIGURATION_UPDATE;
		} catch (Exception ex) {
			Logger.getLogger(ConfigurationServlet.class.getName()).log(Level.SEVERE, null, ex);
			code = Code.E_SERVEUR;
		}

		return new MainEntity(success, code);
	}

	/**
	 * Récupérer et checker les paramètres:
	 * -id_val
	 * -val
	 *
	 * Récupérer les infos de la valeur:
	 * -Valeur[id_user]
	 * -Propriete[modifiable, taillevalmin, taillevalmax]
	 * -TypeProp[id_typeprop]
	 * -ValeurTypee[valeur]
	 *
	 * Vérifier la possibilité de update, check de la nouvelle valeur
	 *
	 * Update de la valeur
	 *
	 */
	private MainEntity updateVal(HttpServletRequest request, HttpServletResponse response) {
		boolean success = false;
		Code code;
		try {
			long id_val = this.checkParam(Param.ID_VAL, request);
			String form_val = this.checkParam(Param.VAL, request);

			ConfigurationModele modele = new ConfigurationModele();

			ValeurInfos infos = modele.getValeurInfos(id_val);

			TypeValeurProp tvp = infos.getTvp();

			this.checkIdUser(infos.getId_user());

			Object val_final = this.checkVal(form_val, infos);

			modele.updateValeur(id_val, tvp.getVal_(), tvp.getValeur_val(), tvp.getValeur_idtype(), val_final);

			code = Code.OK;
			success = true;
		} catch (NoCheckException ex) {
			code = Code.E_CONFIGURATION_UPDATE;
		} catch (Exception ex) {
			Logger.getLogger(ConfigurationServlet.class.getName()).log(Level.SEVERE, null, ex);
			code = Code.E_SERVEUR;
		}

		return new MainEntity(success, code);
	}

	private Object checkVal(String form_val, ValeurInfos infos) throws NoCheckException {

		if (!infos.isProprieteModifiable()) {
			throw new NoCheckException("Propriété non modifiable");
		}

		CheckData cd = this.getCheckDataFromTypeProp(infos.getId_typeprop(), infos.getProprieteNom());

		return this.check(form_val, cd, infos.getProprieteTaillevalmin(), infos.getProprieteTaillevalmax());
	}

	private MainEntity removeSite(HttpServletRequest request, HttpServletResponse response) {
		boolean success = false;
		Code code;
		try {
			long id_val = this.checkParam(Param.ID_VAL, request);
			long id_site = this.checkParam(Param.ID_SITE, request);

			ConfigurationModele modele = new ConfigurationModele();

			VisibiliteInfos infos = modele.getVisibiliteInfos(id_val, id_site);

			this.checkIdUser(infos.getId_user());

			modele.removeSite(id_val, id_site);

			code = Code.OK;
			success = true;
		} catch (NoCheckException ex) {
			code = Code.E_CONFIGURATION_REMOVESITE;
		} catch (Exception ex) {
			Logger.getLogger(ConfigurationServlet.class.getName()).log(Level.SEVERE, null, ex);
			code = Code.E_SERVEUR;
		}

		return new MainEntity(success, code);
	}

}
