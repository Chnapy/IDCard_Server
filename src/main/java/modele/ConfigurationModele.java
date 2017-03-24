/*
 * 
 * 
 * 
 */
package modele;

import bdd.Modele;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.Valeur.VALEUR;
import entity.ValeurInfos;
import java.sql.SQLException;
import java.util.Optional;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

/**
 * ConfigurationModele.java
 *
 */
public class ConfigurationModele extends Modele {

	/**
	 * Récupérer les infos de la valeur:
	 * -Valeur[id_user]
	 * -Propriete[modifiable, taillevalmin, taillevalmax]
	 * -TypeProp[id_typeprop]
	 * -ValeurTypee[valeur]
	 *
	 * @param id_val
	 * @return
	 * @throws modele.ConfigurationModele.NoIssetValeurException
	 * @throws java.lang.Exception
	 */
	public ValeurInfos getValeurInfos(final long id_val) throws NoIssetValeurException, Exception {

		return (ValeurInfos) bdd((create) -> {

			Optional<? extends Record> result_val_infos = create.select(VALEUR.ID_USER,
					PROPRIETE.NOM, PROPRIETE.MODIFIABLE, PROPRIETE.TAILLEVALMIN, PROPRIETE.TAILLEVALMAX,
					TYPEPROP.ID_TYPEPROP)
					.from(VALEUR).naturalJoin(PROPRIETE).naturalJoin(TYPEPROP)
					.where(VALEUR.ID_VALEUR.eq(id_val))
					.fetchOptional();

			Record record_val_infos = result_val_infos.orElseThrow(NoIssetValeurException::new);

			ValeurInfos val_infos = new ValeurInfos(record_val_infos);

			TypeValeurProp tvp = this.parseType(val_infos.getId_typeprop());

			val_infos.setTvp(tvp);

			Optional<? extends Record> result_val = create.select(tvp.getValeur_val())
					.from(VALEUR).naturalJoin(tvp.getVal_())
					.where(VALEUR.ID_VALEUR.eq(id_val))
					.fetchOptional();

			Record record_val = result_val.orElseThrow(NoIssetValeurException::new);

			val_infos.setValeur(record_val.get(tvp.getValeur_val()));

			return val_infos;
		});

	}

	public void updateValeur(long id_val, Table valeur_table, Field valeur_attr, Field valeur_idtype, Object val_final) throws Exception {

		bdd((create) -> {

			int nbrRows = create.update(valeur_table)
					.set(valeur_attr, val_final)
					.from(VALEUR)
					.where(VALEUR.ID_VALEUR.eq(id_val).and(VALEUR.ID_VALEURTYPEE.eq(valeur_idtype)))
					.execute();
			
			if(nbrRows != 1) {
				
				throw new SQLException("Le nombre de lignes affectées est anormal: " + nbrRows);
			}
			
			return null;
		});
	}

	public static class NoIssetValeurException extends Exception {

	}

}
