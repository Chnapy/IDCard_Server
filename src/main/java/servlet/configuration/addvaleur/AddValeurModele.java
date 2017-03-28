/*
 * 
 * 
 * 
 */
package servlet.configuration.addvaleur;

import bdd.Modele;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Valeur.VALEUR;
import bdd.generated.tables.records.ProprieteRecord;
import entity.ValeurInfos;
import enumerations.TypeProp;
import exceptions.ClientException;
import java.util.Optional;

/**
 * ConfigurationModele.java
 *
 */
public class AddValeurModele extends Modele {

	/**
	 * Retourne les infos sur la propriété et sur le type de propriété dans un
	 * {@link ValeurInfos}.
	 *
	 * @param id_prop
	 * @return
	 * @throws ClientException
	 * @throws Exception
	 */
	public ValeurInfos getProprieteInfos(long id_prop) throws ClientException, Exception {
		return bdd((create) -> {

			Optional<ProprieteRecord> pr = create.selectFrom(PROPRIETE)
					.where(PROPRIETE.ID_PROPRIETE.eq(id_prop))
					.fetchOptional();

			ValeurInfos infos = new ValeurInfos(pr.orElseThrow(ClientException::new));

			TypeProp tp = TypeProp.getTypeProp(infos.getId_typeprop());

			infos.setTypeProp(tp);

			return infos;
		});
	}

	/**
	 * Ajoute la valeur à la base de donnée.
	 *
	 * @param id_prop
	 * @param id_user
	 * @param valeur
	 * @param tp
	 * @return
	 * @throws Exception
	 */
	public long addValeur(long id_prop, long id_user, Object valeur, TypeProp tp) throws Exception {

		return bdd((create) -> {

			long id_valeurtypee = (long) create.insertInto(tp.getTable_ValeurTypee())
					.set(tp.getField_ValeurTypee_valeur(), valeur)
					.returning(tp.getField_ValeurTypee_id())
					.fetchOne()
					.get(tp.getField_ValeurTypee_id());

			return create.insertInto(VALEUR)
					.set(VALEUR.ID_PROPRIETE, id_prop)
					.set(VALEUR.ID_USER, id_user)
					.set(VALEUR.PRINCIPALE, false)
					.set(VALEUR.ID_VALEURTYPEE, id_valeurtypee)
					.returning(VALEUR.ID_VALEUR)
					.fetchOne()
					.get(VALEUR.ID_VALEUR);

		});

	}

}
