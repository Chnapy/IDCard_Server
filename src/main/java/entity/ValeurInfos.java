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
 */
public class ValeurInfos {

	private final Record record;
	private TypeProp typeProp;

	public ValeurInfos(Record record) throws IllegalArgumentException {
		this.record = record;
	}

	public Record getRecord() {
		return record;
	}

	public TypeProp getTypeProp() {
		return typeProp;
	}

	public void setTypeProp(TypeProp typeProp) {
		this.typeProp = typeProp;
	}

	public long getId_user() {
		return (long) record.get(USER_1.ID_USER.getName());
	}
	
	public long getId_valeurTypee() {
		return (long) record.get(VALEUR.ID_VALEURTYPEE.getName());
	}

	public long getId_propriete() {
		return (long) record.get(PROPRIETE.ID_PROPRIETE.getName());
	}

	public int getId_typeprop() {
		return (int) record.get(TYPEPROP.ID_TYPEPROP.getName());
	}

	public String getProprieteNom() {
		return record.get(PROPRIETE.NOM);
	}

	public boolean isProprieteModifiable() {
		return record.get(PROPRIETE.MODIFIABLE);
	}

	public int getProprieteTaillevalmin() {
		return record.get(PROPRIETE.TAILLEVALMIN);
	}

	public int getProprieteTaillevalmax() {
		return record.get(PROPRIETE.TAILLEVALMAX);
	}

}
