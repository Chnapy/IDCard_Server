/*
 * 
 * 
 * 
 */
package servlet.gestionacces.connexion;

import servlet.gestionacces.ConnexionServlet;
import entity.ContentEntity;
import entity.ProprieteEntity;
import entity.UserEntity;
import enumerations.Attribut;
import enumerations.Param;
import enumerations.Session;
import java.util.List;
import servlet.ControleurAction;
import servlet.ModuleAction;

/**
 * ConnexionAction.java
 *
 */
@ModuleAction(servlet = ConnexionServlet.class)
public class ConnexionAction extends ControleurAction<ConnexionServlet> {

	@Override
	protected ContentEntity doAct() throws Exception {
		
		ConnexionModele con_modele = new ConnexionModele();

		boolean isMail = (boolean) (getRequest().getAttribute(Attribut.IS_MAIL.tostring) != null
				? getRequest().getAttribute(Attribut.IS_MAIL.tostring)
				: getParam(Param.IS_MAIL));
		String mdp = getParam(Param.MDP);

		UserEntity user;
		if (isMail) {
			String mail = getParam(Param.MAIL);
			user = con_modele.connexionParMail(mail, mdp);
		} else {
			String pseudo = getParam(Param.LOGIN);
			user = con_modele.connexionParPseudo(pseudo, mdp);
		}

		ProprieteModele prop_modele = new ProprieteModele();

		List<ProprieteEntity> proprietes = prop_modele.getAllProprietes(con_modele.getId_user());

		getRequest().getSession().setAttribute(Session.USER.tostring, user);

		return new ContentEntity(user, proprietes);
	}

}
