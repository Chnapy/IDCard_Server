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
 */
@ModuleAction(servlet = DeconnexionServlet.class)
public class DeconnexionAction extends ControleurAction<DeconnexionServlet> {

	@Override
	protected ContentEntity doAct() throws Exception {

		getRequest().getSession().invalidate();
		
		return new ContentEntity(new UserEntity(false), null);
	}

}
