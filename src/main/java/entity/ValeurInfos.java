/*
 * 
 * 
 * 
 */
package entity;

import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.User_1.USER_1;
import static bdd.generated.tables.Valeur.VALEUR;
import enumerations.TypeProp;
import org.jooq.Record;

/**
 * ValeurInfos.java
 *
 * Permet de récupérer des données VALEUR, PROPRIETE, ou encore TYPEPROP
 * depuis un objet {@link Record}
 *
 */
public class ValeurInfos {

	/**
	 * Objet JOOQ contenant le résultat d'une requête (SELECT) à la base de
	 * données
	 */
	private final Record record;

	private TypeProp typeProp;

	/**
	 *
	 * @param record
	 * @throws IllegalArgumentException
	 */
	public ValeurInfos(Record record) throws IllegalArgumentException {
		this.record = record;
	}

	/**
	 *
	 * @return
	 */
	public Record getRecord() {
		return record;
	}

	/**
	 *
	 * @return
	 */
	public TypeProp getTypeProp() {
		return typeProp;
	}

	/**
	 *
	 * @param typeProp
	 */
	public void setTypeProp(TypeProp typeProp) {
		this.typeProp = typeProp;
	}

	/**
	 *
	 * @return
	 */
	public long getId_user() {
		return (long) record.get(USER_1.ID_USER.getName());
	}

	/**
	 *
	 * @return
	 */
	public long getId_valeurTypee() {
		return (long) record.get(VALEUR.ID_VALEURTYPEE.getName());
	}

	/**
	 *
	 * @return
	 */
	public long getId_propriete() {
		return (long) record.get(PROPRIETE.ID_PROPRIETE.getName());
	}

	/**
	 *
	 * @return
	 */
	public int getId_typeprop() {
		return (int) record.get(TYPEPROP.ID_TYPEPROP.getName());
	}

	/**
	 *
	 * @return
	 */
	public String getProprieteNom() {
		return record.get(PROPRIETE.NOM);
	}

	/**
	 *
	 * @return
	 */
	public boolean isProprieteModifiable() {
		return record.get(PROPRIETE.MODIFIABLE);
	}

	/**
	 *
	 * @return
	 */
	public int getProprieteTaillevalmin() {
		return record.get(PROPRIETE.TAILLEVALMIN);
	}

	/**
	 *
	 * @return
	 */
	public int getProprieteTaillevalmax() {
		return record.get(PROPRIETE.TAILLEVALMAX);
	}

}
