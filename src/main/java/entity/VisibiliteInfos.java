/*
 * 
 * 
 * 
 */
package entity;

import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Visibilite.VISIBILITE;
import org.jooq.Record;

/**
 * VisibiliteInfos.java
 * 
 */
public class VisibiliteInfos {
	
	private final long id_user;
	private final long id_valeur;
	private final long id_domaine;

	public VisibiliteInfos(Record record_visi_infos) throws IllegalArgumentException {
		this.id_user = record_visi_infos.get(VALEUR.ID_USER);
		this.id_valeur = record_visi_infos.get(VALEUR.ID_VALEUR);
		this.id_domaine = record_visi_infos.get(VISIBILITE.ID_DOMAINE);
	}

	public long getId_user() {
		return id_user;
	}

	public long getId_valeur() {
		return id_valeur;
	}

	public long getId_domaine() {
		return id_domaine;
	}
	

}
