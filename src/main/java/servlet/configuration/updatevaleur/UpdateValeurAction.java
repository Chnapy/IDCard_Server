/*
 * 
 * 
 * 
 */
package servlet.configuration.updatevaleur;

import entity.ContentEntity;
import entity.ValeurInfos;
import servlet.configuration.ConfigurationServlet;
import enumerations.Param;
import enumerations.TypeProp;
import servlet.ModuleAction;
import servlet.ControleurAction;

/**
 * UpdateValeurAction.java
 *
 * Action appelée lors de la mise à jour d'une valeur.
 */
@ModuleAction(servlet = ConfigurationServlet.class, module = "update_val")
public class UpdateValeurAction extends ControleurAction<ConfigurationServlet> {

	/**
	 *
	 * Récupère et check les paramètres:
	 * -id_val;
	 * -form_val;
	 *
	 * Récupère les infos BDD de la valeur:
	 * -Valeur[id_user];
	 * -Propriete[modifiable, taillevalmin, taillevalmax];
	 * -TypeProp[id_typeprop];
	 * -ValeurTypee[valeur];
	 *
	 * Check de l'id user.
	 *
	 * Check de la valeur.
	 *
	 * Update de la valeur.
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	protected ContentEntity doAct() throws Exception {

		long id_val = getParam(Param.ID_VAL);
		String form_val = getParam(Param.VAL);

		UpdateValeurModele modele = new UpdateValeurModele();

		ValeurInfos infos = modele.getValeurInfos(id_val);

		TypeProp tp = infos.getTypeProp();

		checkIdUser(infos.getId_user());

		Object val_final = getControleur().checkVal(form_val, infos);

		modele.updateValeur(id_val, tp.getTable_ValeurTypee(), tp.getField_ValeurTypee_valeur(), tp.getField_ValeurTypee_id(), val_final);

		return null;

	}

}
