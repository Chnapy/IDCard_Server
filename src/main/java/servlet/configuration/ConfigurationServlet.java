/*
 * 
 * 
 * 
 */
package servlet.configuration;

import bdd.CheckData;
import entity.ValeurInfos;
import exceptions.ClientException;
import exceptions.NoCheckException;
import javax.servlet.annotation.WebServlet;
import servlet.Controleur;

/**
 * Servlet appelé lors des actions de configuration.
 *
 * @author Richard
 */
@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends Controleur {

	/**
	 * Vérifie le format d'une valeur d'après son type et les données de sa
	 * propriété. Renvoie la valeur castée.
	 *
	 * @param form_val
	 * @param infos
	 * @return La valeur castée dans le type du CheckData
	 * @throws ClientException
	 * @throws NoCheckException
	 */
	public Object checkVal(String form_val, ValeurInfos infos) throws ClientException, NoCheckException {

		if (!infos.isProprieteModifiable()) {
			throw new ClientException("Propriété non modifiable");
		}

		CheckData cd = this.getCheckDataFromTypeProp(infos.getId_typeprop(), infos.getProprieteNom());

		return this.check(form_val, cd, infos.getProprieteTaillevalmin(), infos.getProprieteTaillevalmax());
	}

}
