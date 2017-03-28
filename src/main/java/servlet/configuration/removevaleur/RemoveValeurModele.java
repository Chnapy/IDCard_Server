/*
 * 
 * 
 * 
 */
package servlet.configuration.removevaleur;

import bdd.Modele;
import static bdd.generated.tables.Valeur.VALEUR;
import entity.ValeurInfos;
import org.jooq.Field;
import org.jooq.Table;

/**
 * RemoveValeurModele.java
 *
 */
class RemoveValeurModele extends Modele {

	public ValeurInfos removeValeur(long id_val, long id_val_typee, Table table_valeurtypee, Field field_valeurtypee_id) throws Exception {

		return bdd((create) -> {

			int nbrRows;

			nbrRows = create.delete(VALEUR)
					.where(VALEUR.ID_VALEUR.eq(id_val))
					.execute();

			this.checkRows(nbrRows);

			nbrRows = create.delete(table_valeurtypee)
					.where(field_valeurtypee_id.eq(id_val_typee))
					.execute();

			this.checkRows(nbrRows);

			return null;
		});

	}
}
