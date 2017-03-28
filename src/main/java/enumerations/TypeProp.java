/*
 * 
 * 
 * 
 */
package enumerations;

import bdd.CheckData;
import bdd.Const;
import static bdd.generated.tables.Valeurbigint.VALEURBIGINT;
import static bdd.generated.tables.Valeurboolean.VALEURBOOLEAN;
import static bdd.generated.tables.Valeurdate.VALEURDATE;
import static bdd.generated.tables.Valeurdouble.VALEURDOUBLE;
import static bdd.generated.tables.Valeurinteger.VALEURINTEGER;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.impl.TableImpl;

/**
 * TypeProp.java
 *
 */
public enum TypeProp {

	STRING(1, "text", Const.CD_VALSTRING, VALEURSTRING),
	INTEGER(2, "number", Const.CD_INTEGER, VALEURINTEGER),
	BOOLEAN(3, "checkbox", Const.CD_BOOLEAN, VALEURBOOLEAN),
	DATE(4, "date", Const.CD_DATE, VALEURDATE),
	CHIFFRE(5, "mdp", Const.CD_VALSTRING, VALEURMDP),
	EMAIL(6, "email", Const.CD_MAIL, VALEURSTRING),
	BIGINT(7, "number", Const.CD_LONG, VALEURBIGINT),
	DOUBLE(8, "double", Const.CD_DOUBLE, VALEURDOUBLE);

	public final int id;
	private final String inputType;
	private final CheckData cd;
	private final TableImpl table_valeurTypee;

	private TypeProp(int id, String inputType, CheckData cd, TableImpl table_valeurTypee) {
		this.id = id;
		this.inputType = inputType;
		this.cd = cd;
		this.table_valeurTypee = table_valeurTypee;
	}

	public String getInputType() {
		return inputType;
	}

	public CheckData getCd() {
		return cd;
	}

	public Table getTable_ValeurTypee() {
		return table_valeurTypee;
	}

	public Field getField_ValeurTypee_id() {
		return table_valeurTypee.field("id_valeurtypee");
	}

	public Field getField_ValeurTypee_valeur() {
		return table_valeurTypee.field("valeur");
	}

	public static TypeProp getTypeProp(int id) {
		for (TypeProp tp : TypeProp.values()) {
			if (tp.id == id) {
				return tp;
			}
		}
		throw new IllegalArgumentException("TypeProp non présent: " + id);
	}
}
