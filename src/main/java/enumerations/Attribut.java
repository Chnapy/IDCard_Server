/*
 * 
 * 
 * 
 */
package enumerations;

import javax.servlet.http.HttpServletRequest;

/**
 * Attribut.java
 *
 * Enumeration pour les attributs utilisés par {@link HttpServletRequest#getAttribute(java.lang.String)}
 *  {@link HttpServletRequest#setAttribute(java.lang.String, java.lang.Object)}
 *
 */
public enum Attribut {

	/**
	 *
	 */
	IS_MAIL("isMail"),
	/**
	 *
	 */
	DONNEES("donnees"),
	/**
	 *
	 */
	PAGE("page");

	/**
	 *
	 */
	public final String tostring;

	private Attribut(String tostring) {
		this.tostring = tostring;
	}
}
