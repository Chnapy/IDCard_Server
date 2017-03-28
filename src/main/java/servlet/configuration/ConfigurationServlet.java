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
 *
 * @author Richard
 */
@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends Controleur {

	public Object checkVal(String form_val, ValeurInfos infos) throws ClientException, NoCheckException {

		if (!infos.isProprieteModifiable()) {
			throw new ClientException("Propriété non modifiable");
		}

		CheckData cd = this.getCheckDataFromTypeProp(infos.getId_typeprop(), infos.getProprieteNom());

		return this.check(form_val, cd, infos.getProprieteTaillevalmin(), infos.getProprieteTaillevalmax());
	}

}
