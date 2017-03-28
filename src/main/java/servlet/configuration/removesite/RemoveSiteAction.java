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
 */
@ModuleAction(servlet = ConfigurationServlet.class, module = "remove_site")
public class RemoveSiteAction extends ControleurAction<ConfigurationServlet> {

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
