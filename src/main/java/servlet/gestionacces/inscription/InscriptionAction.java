/*
 * 
 * 
 * 
 */
package servlet.gestionacces.inscription;

import servlet.gestionacces.InscriptionServlet;
import exceptions.IssetClientException;
import entity.ContentEntity;
import enumerations.Param;
import servlet.ControleurAction;
import servlet.ModuleAction;

/**
 * ConnexionAction.java
 *
 */
@ModuleAction(servlet = InscriptionServlet.class)
public class InscriptionAction extends ControleurAction<InscriptionServlet> {

	@Override
	protected ContentEntity doAct() throws Exception {

		String pseudo = getParam(Param.LOGIN);
		String mail = getParam(Param.MAIL);
		String mdp = getParam(Param.MDP);

		InscriptionModele modele = new InscriptionModele();

		if (modele.issetClient(pseudo, mail)) {
			throw new IssetClientException();
		}

		modele.inscription(pseudo, mail, mdp);

		return null;
	}

}
