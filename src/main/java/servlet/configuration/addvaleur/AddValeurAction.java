/*
 * 
 * 
 * 
 */
package servlet.configuration.addvaleur;

import entity.ContentEntity;
import entity.ValeurInfos;
import servlet.configuration.ConfigurationServlet;
import servlet.ModuleAction;
import enumerations.Param;
import enumerations.TypeProp;
import servlet.ControleurAction;

/**
 * AddValeurAction.java
 *
 */
@ModuleAction(servlet = ConfigurationServlet.class, module = "add_val")
public class AddValeurAction extends ControleurAction<ConfigurationServlet> {

	@Override
	protected ContentEntity doAct() throws Exception {

		long id_prop = getParam(Param.ID_PROP);
		String form_val = getParam(Param.VAL);

		AddValeurModele modele = new AddValeurModele();

		ValeurInfos infos = modele.getProprieteRecord(id_prop);

		TypeProp tp = infos.getTypeProp();

		Object val_final = getControleur().checkVal(form_val, infos);

		long id_val = modele.addValeur(id_prop, getUser().getId_user(), val_final, tp);
		
		ContentEntity retour = new ContentEntity(null, null);
		retour.setId_val(id_val);
		
		return retour;
	}
}
