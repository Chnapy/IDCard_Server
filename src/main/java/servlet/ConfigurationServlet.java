/*
 * 
 * 
 * 
 */
package servlet;

import bdd.Const;
import bdd.Const.Code;
import entity.MainEntity;
import entity.MainEntity.MainEntityError;
import entity.ValeurInfos;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.ConfigurationModele;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

		String module = request.getParameter("m");
		if (module == null) {
			module = "";
		}

		switch (module) {
			case "update_val":
				return updateVal(request, response);
			default:
				return new MainEntityError(Const.Code.E_SERVEUR);
		}

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

			Object val_final = this.checkVal(form_val, infos);

			modele.updateValeur(id_val, infos.getTvp().getVal_(), infos.getTvp().getValeur_val(), infos.getTvp().getValeur_idtype(), val_final);

			code = Code.OK;
			success = true;
		} catch (Param.NoCheckException ex) {
			code = Code.E_CONFIGURATION_UPDATE;
		} catch (Exception ex) {
			Logger.getLogger(ConfigurationServlet.class.getName()).log(Level.SEVERE, null, ex);
			code = Code.E_SERVEUR;
		}

		return new MainEntity(success, code);
	}

	private Object checkVal(String form_val, ValeurInfos infos) throws Param.NoCheckException {
		
		if(!infos.isModifiable()) {
			throw new Param.NoCheckException("Propriété non modifiable");
		}

		Const.CheckData cd = this.getCheckDataFromTypeProp(infos.getId_typeprop(), infos.getPropriete_nom());

		Object val_final = this.check(form_val, cd);

		if (val_final instanceof String) {
			String s = (String) val_final;
			if (s.length() >= infos.getTaillevalmin() && s.length() <= infos.getTaillevalmax()) {
				return val_final;
			}
		}
		if (val_final instanceof Boolean) {
			return val_final;
		}
		if (val_final instanceof Number) {
			Number n = (Number) val_final;
			if ((val_final instanceof Integer || val_final instanceof Long)
					&& (n.longValue() >= infos.getTaillevalmin() && n.longValue() <= infos.getTaillevalmax())) {
				return val_final;
			}
			if (val_final instanceof Double
					&& (n.doubleValue() >= infos.getTaillevalmin() && n.doubleValue() <= infos.getTaillevalmax())) {
				return val_final;
			}
		}
		if (val_final instanceof Date) {
			Date d = (Date) val_final;
			if (d.getTime() >= infos.getTaillevalmin() && d.getTime() <= infos.getTaillevalmax()) {
				return val_final;
			}
		}

		throw new Param.NoCheckException(cd, form_val);
	}

}
