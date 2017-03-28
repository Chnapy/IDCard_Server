/*
 * 
 * 
 * 
 */
package servlet.configuration.removevaleur;

import bdd.generated.tables.records.ProprieteRecord;
import bdd.generated.tables.records.ValeurRecord;
import servlet.configuration.updatevaleur.*;
import entity.ContentEntity;
import entity.ValeurInfos;
import servlet.configuration.ConfigurationServlet;
import enumerations.Param;
import enumerations.TypeProp;
import exceptions.ClientException;
import servlet.ModuleAction;
import servlet.ControleurAction;
import servlet.gestionacces.connexion.ProprieteModele;

/**
 *
 * Action appelée lors de la suppression d'une valeur.
 */
@ModuleAction(servlet = ConfigurationServlet.class, module = "remove_val")
public class RemoveValeurAction extends ControleurAction<ConfigurationServlet> {

	/**
	 * Récupère l'id de la valeur.
	 * Puis les infos liées à cette valeur.
	 * On check l'id user.
	 * On récupère les données de la propriété liée à la valeur. Puis les
	 * données de la valeur en question. Puis le nombre de valeurs possédées par
	 * la propriété.
	 * D'après ces données, on vérifie si la suppression est possible.
	 * Enfin, on supprime la valeur.
	 *
	 * @return null
	 * @throws Exception
	 */
	@Override
	protected ContentEntity doAct() throws Exception {

		long id_val = getParam(Param.ID_VAL);

		RemoveValeurModele remo_modele = new RemoveValeurModele();
		UpdateValeurModele upda_modele = new UpdateValeurModele();
		ProprieteModele prop_modele = new ProprieteModele();

		ValeurInfos infos = upda_modele.getValeurInfos(id_val);

		TypeProp tp = infos.getTypeProp();

		checkIdUser(infos.getId_user());

		ProprieteRecord pr = prop_modele.getPropriete(infos.getId_propriete());
		ValeurRecord vr = prop_modele.getValeur(id_val);
		int nbrVals = prop_modele.getNbrValFromProp(infos.getId_propriete());

		System.out.println("p:" + vr.getPrincipale() + " s:" + pr.getSupprimable() + " m:" + pr.getNbrvalmin() + " n:" + nbrVals);

		if (vr.getPrincipale()
				|| !pr.getSupprimable()
				|| pr.getNbrvalmin() >= nbrVals) {
			throw new ClientException();
		}

		remo_modele.removeValeur(id_val, infos.getId_valeurTypee(), tp.getTable_ValeurTypee(), tp.getField_ValeurTypee_id());

		return null;

	}

}
