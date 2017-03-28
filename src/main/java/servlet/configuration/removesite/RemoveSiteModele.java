/*
 * 
 * 
 * 
 */
package servlet.configuration.removesite;

import bdd.Modele;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Visibilite.VISIBILITE;
import entity.VisibiliteInfos;
import exceptions.BddException;
import exceptions.ClientException;
import java.util.Optional;
import org.jooq.Record;

/**
 * ConfigurationModele.java
 *
 */
public class RemoveSiteModele extends Modele {

	public VisibiliteInfos getVisibiliteInfos(long id_val, long id_site) throws ClientException, Exception {

		return (VisibiliteInfos) bdd((create) -> {

			Optional<? extends Record> result_visi_infos = create.select(VALEUR.ID_USER,
					VALEUR.ID_VALEUR, VISIBILITE.ID_DOMAINE)
					.from(VALEUR).naturalJoin(VISIBILITE)
					.where(VISIBILITE.ID_VALEUR.eq(id_val).and(VISIBILITE.ID_DOMAINE.eq(id_site)))
					.fetchOptional();

			Record record_visi_infos = result_visi_infos.orElseThrow(ClientException::new);

			VisibiliteInfos visi_infos = new VisibiliteInfos(record_visi_infos);

			return visi_infos;
		});

	}

	public void removeSite(long id_val, long id_site) throws BddException, Exception {

		bdd((create) -> {

			int nbrRows = create.delete(VISIBILITE)
					.where(VISIBILITE.ID_VALEUR.eq(id_val).and(VISIBILITE.ID_DOMAINE.eq(id_site)))
					.execute();

			this.checkRows(nbrRows);

			return null;
		});
	}

}
