/*
 * 
 * 
 * 
 */
package entity;

import bdd.Modele;
import bdd.Modele.TypeValeurProp;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.Valeur.VALEUR;
import org.jooq.Record;

/**
 * ValeurInfos.java
 *
 */
public class ValeurInfos {

	private final long id_user;
	private final String propriete_nom;
	private final boolean modifiable;
	private final int taillevalmin;
	private final int taillevalmax;
	private final long id_typeprop;
	private Modele.TypeValeurProp tvp;
	private Object valeur;

	public ValeurInfos(Record record_val_infos) throws IllegalArgumentException {
		this.id_user = record_val_infos.get(VALEUR.ID_USER);
		this.propriete_nom = record_val_infos.get(PROPRIETE.NOM);
		this.modifiable = record_val_infos.get(PROPRIETE.MODIFIABLE);
		this.taillevalmin = record_val_infos.get(PROPRIETE.TAILLEVALMIN);
		this.taillevalmax = record_val_infos.get(PROPRIETE.TAILLEVALMAX);
		this.id_typeprop = record_val_infos.get(TYPEPROP.ID_TYPEPROP);
	}

	public long getId_user() {
		return id_user;
	}

	public String getPropriete_nom() {
		return propriete_nom;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public int getTaillevalmin() {
		return taillevalmin;
	}

	public int getTaillevalmax() {
		return taillevalmax;
	}

	public long getId_typeprop() {
		return id_typeprop;
	}

	public TypeValeurProp getTvp() {
		return tvp;
	}

	public void setTvp(TypeValeurProp tvp) {
		this.tvp = tvp;
	}

	public Object getValeur() {
		return valeur;
	}

	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}
}
