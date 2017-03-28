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
 * Contient les infos de la base de donnée au sujet de la visibilité d'un
 * valeur,
 * ainsi que l'id user qui possède le tout
 *
 */
public class VisibiliteInfos {

	private final long id_user;
	private final long id_valeur;
	private final long id_domaine;

	/**
	 *
	 * @param record
	 * @throws IllegalArgumentException
	 */
	public VisibiliteInfos(Record record) throws IllegalArgumentException {
		this.id_user = record.get(VALEUR.ID_USER);
		this.id_valeur = record.get(VALEUR.ID_VALEUR);
		this.id_domaine = record.get(VISIBILITE.ID_DOMAINE);
	}

	/**
	 *
	 * @return
	 */
	public long getId_user() {
		return id_user;
	}

	/**
	 *
	 * @return
	 */
	public long getId_valeur() {
		return id_valeur;
	}

	/**
	 *
	 * @return
	 */
	public long getId_domaine() {
		return id_domaine;
	}

}
