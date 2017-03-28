/*
 * 
 * 
 * 
 */
package servlet.configuration.removesite;

import entity.ContentEntity;
import entity.VisibiliteInfos;
import servlet.configuration.ConfigurationServlet;
import servlet.ModuleAction;
import enumerations.Param;
import servlet.ControleurAction;

/**
 * RemoveSiteAction.java
 *
 * Action appelée lors de la suppression d'un site d'une valeur (visibilité).
 */
@ModuleAction(servlet = ConfigurationServlet.class, module = "remove_site")
public class RemoveSiteAction extends ControleurAction<ConfigurationServlet> {

	/**
	 * Récupère les paramètres.
	 * Depuis la BDD récupère les informations sur la visibilité.
	 * Check l'id user.
	 * Puis supprime la visibilité de la base de donnée.
	 *
	 * @return null
	 * @throws Exception
	 */
	@Override
	protected ContentEntity doAct() throws Exception {

		long id_val = getParam(Param.ID_VAL);
		long id_site = getParam(Param.ID_SITE);

		RemoveSiteModele modele = new RemoveSiteModele();

		VisibiliteInfos infos = modele.getVisibiliteInfos(id_val, id_site);

		checkIdUser(infos.getId_user());

		modele.removeSite(id_val, id_site);

		return null;

	}
}
