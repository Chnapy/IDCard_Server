/*
 * 
 * 
 * 
 */
package servlet.configuration.updatevaleur;

import bdd.Modele;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.Valeur.VALEUR;
import entity.ValeurInfos;
import enumerations.TypeProp;
import exceptions.BddException;
import exceptions.ClientException;
import java.util.Optional;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

/**
 * UpdateValeurModele.java
 *
 */
public class UpdateValeurModele extends Modele {

	/**
	 * Récupérer les infos de la valeur:
	 * -Valeur[id_user]
	 * -Propriete[modifiable, taillevalmin, taillevalmax]
	 * -TypeProp[id_typeprop]
	 * -ValeurTypee[valeur]
	 *
	 * @param id_val
	 * @return Les infos sur la valeur, propriété, et type de propriété
	 * @throws exceptions.ClientException
	 * @throws exceptions.BddException
	 * @throws java.lang.Exception
	 */
	public ValeurInfos getValeurInfos(final long id_val) throws ClientException, BddException, Exception {

		return bdd((create) -> {

			Optional<? extends Record> result_val_infos = create.select(VALEUR.ID_USER, VALEUR.ID_VALEURTYPEE,
					PROPRIETE.ID_PROPRIETE, PROPRIETE.NOM, PROPRIETE.MODIFIABLE, PROPRIETE.TAILLEVALMIN, PROPRIETE.TAILLEVALMAX,
					TYPEPROP.ID_TYPEPROP)
					.from(VALEUR).naturalJoin(PROPRIETE).naturalJoin(TYPEPROP)
					.where(VALEUR.ID_VALEUR.eq(id_val))
					.fetchOptional();

			Record record_val_infos = result_val_infos.orElseThrow(ClientException::new);

			ValeurInfos val_infos = new ValeurInfos(record_val_infos);

			TypeProp tp = TypeProp.getTypeProp(val_infos.getId_typeprop());
			val_infos.setTypeProp(tp);

			Optional<? extends Record> result_val = create.select(tp.getField_ValeurTypee_valeur())
					.from(VALEUR).naturalJoin(tp.getTable_ValeurTypee())
					.where(VALEUR.ID_VALEUR.eq(id_val))
					.fetchOptional();

			Record record_val = result_val.orElseThrow(BddException::new);

			return val_infos;
		});

	}

	/**
	 * Procède à l'update de la valeur dans les tables correspondantes.
	 *
	 * @param id_val
	 * @param valeur_table
	 * @param valeur_field
	 * @param valeur_idtype
	 * @param val_final
	 * @throws BddException
	 * @throws Exception
	 */
	public void updateValeur(long id_val, Table valeur_table, Field valeur_field, Field valeur_idtype, Object val_final) throws BddException, Exception {

		bdd((create) -> {

			int nbrRows = create.update(valeur_table)
					.set(valeur_field, val_final)
					.from(VALEUR)
					.where(VALEUR.ID_VALEUR.eq(id_val).and(VALEUR.ID_VALEURTYPEE.eq(valeur_idtype)))
					.execute();

			this.checkRows(nbrRows);

			return null;
		});
	}

}
