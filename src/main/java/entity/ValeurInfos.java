/*
 * 
 * 
 * 
 */
package entity;

import bdd.Modele.TypeValeurProp;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.User_1.USER_1;
import org.jooq.Record;

/**
 * ValeurInfos.java
 *
 */
public class ValeurInfos {

	private final Record record;
	private TypeValeurProp tvp;

	public ValeurInfos(Record record) throws IllegalArgumentException {
		this.record = record;
	}

	public Record getRecord() {
		return record;
	}

	public TypeValeurProp getTvp() {
		return tvp;
	}

	public void setTvp(TypeValeurProp tvp) {
		this.tvp = tvp;
	}

	public long getId_user() {
		return (long) record.get(USER_1.ID_USER.getName());
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
