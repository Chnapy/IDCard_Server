/*
 * 
 * 
 * 
 */
package enumerations;

import bdd.CheckData;
import bdd.Const;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

/**
 * Param.java
 *
 * Enumeration pour les paramètres utilisés par
 * {@link HttpServletRequest#getParameter(java.lang.String)}
 * Lie également les {@link CheckData} correspondant
 */
public enum Param {

	/**
	 *
	 */
	LOGIN("pseudo", Const.CD_PSEUDO),
	/**
	 *
	 */
	MAIL("mail", Const.CD_MAIL),
	/**
	 *
	 */
	MDP("mdp", Const.CD_MDP),
	/**
	 *
	 */
	IS_MAIL("isMail", Const.CD_BOOLEAN),
	/**
	 *
	 */
	ID_VAL("id_val", Const.CD_LONG),
	/**
	 *
	 */
	ID_PROP("id_prop", Const.CD_LONG),
	/**
	 *
	 */
	ID_SITE("id_site", Const.CD_LONG),
	/**
	 *
	 */
	VAL("val", Const.CD_VALSTRING);

	/**
	 *
	 */
	public final String tostring;
	private final CheckData cd;

	private Param(String tostring, CheckData cd) {
		this.tostring = tostring;
		this.cd = cd;
	}

	/**
	 * Retourne la valeur castée depuis la classe du CheckData
	 * {@link Param#getCastValue(bdd.CheckData, java.lang.String)}
	 *
	 * @param <T>
	 * @param value
	 * @return
	 */
	public <T> T getCastValue(String value) {
		return getCastValue(cd, value);
	}

	//Recois la valeur du parametre lorsqu'il est encore en String
	/**
	 * Check la valeur txt depuis le CheckData du Param
	 * {@link Param#check(bdd.CheckData, java.lang.String)}
	 *
	 * @param txt
	 * @return
	 */
	public boolean check(String txt) {
		return check(cd, txt);
	}

	/**
	 * Retourne la valeur castée de value.
	 * Le type de retour dépend de {@link CheckData#classe}
	 *
	 * @param <T>
	 * @param cd
	 * @param value
	 * @return
	 */
	public static <T> T getCastValue(CheckData cd, String value) {
		if (cd.classe.equals(String.class)) {
			return (T) value;
		}
		if (cd.classe.equals(Boolean.class)) {
			return (T) (Boolean) Boolean.parseBoolean(value);
		}
		if (cd.classe.equals(Integer.class)) {
			return (T) (Integer) Integer.parseInt(value);
		}
		if (cd.classe.equals(Long.class)) {
			return (T) (Long) Long.parseLong(value);
		}
		if (cd.classe.equals(Double.class)) {
			return (T) (Double) Double.parseDouble(value);
		}
		if (cd.classe.equals(Date.class)) {
			try {
				return (T) (Date) DateFormat.getDateInstance().parse(value);
			} catch (ParseException ex) {
				throw new Error("Cast date impossible: " + value);
			}
		}
		throw new Error("Classe non gérée: " + cd.classe);
	}

	/**
	 * Check la valeur txt depuis le CheckData cd
	 *
	 * @param cd
	 * @param txt
	 * @return
	 */
	public static boolean check(CheckData cd, String txt) {
		if (cd.classe.equals(String.class)) {
			return txt.length() >= cd.minLength && txt.length() <= cd.maxLength
					&& (cd.patternRequired == null || txt.matches(cd.patternRequired))
					&& (cd.patternNotAllowed == null || !txt.matches(cd.patternNotAllowed));
		}
		if (cd.classe.equals(Boolean.class)) {
			return "true".equals(txt.toLowerCase()) || "false".equals(txt.toLowerCase());
		}
		if (cd.classe.equals(Integer.class)) {
			try {
				Integer.parseInt(txt);
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		if (cd.classe.equals(Long.class)) {
			try {
				Long.parseLong(txt);
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		if (cd.classe.equals(Double.class)) {
			try {
				Double.parseDouble(txt);
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		if (cd.classe.equals(Date.class)) {
			try {
				DateFormat.getDateInstance().parse(txt);
				return true;
			} catch (ParseException ex) {
				return false;
			}
		}
		throw new Error("Classe non gérée: " + cd.classe);
	}

	/**
	 * Vérifie si la valeur correspond et check le Param
	 *
	 * @param param
	 * @param value
	 * @return True si checké, False sinon
	 */
	public static boolean checkParam(Param param, String value) {
		for (Param p : Param.values()) {
			if (p.equals(param)) {
				return p.check(value);
			}
		}
		throw new IllegalArgumentException("Param non présent: " + param + " [" + value + "]");
	}
}
