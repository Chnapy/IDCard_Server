/*
 * 
 * 
 * 
 */
package servlet.gestionacces.deconnexion;

import servlet.gestionacces.DeconnexionServlet;
import entity.ContentEntity;
import entity.UserEntity;
import servlet.ControleurAction;
import servlet.ModuleAction;

/**
 * ConnexionAction.java
 *
 * Action appelée lors de la déconnexion.
 */
@ModuleAction(servlet = DeconnexionServlet.class)
public class DeconnexionAction extends ControleurAction<DeconnexionServlet> {

	/**
	 * Invalide la session et retourne des données vide (avec utilisateur non
	 * connecté).
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	protected ContentEntity doAct() throws Exception {

		getRequest().getSession().invalidate();

		return new ContentEntity(new UserEntity(false), null);
	}

}
