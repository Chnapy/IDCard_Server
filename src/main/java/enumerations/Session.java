/*
 * 
 * 
 * 
 */
package enumerations;

import javax.servlet.http.HttpSession;

/**
 * Session.java
 *
 * Enumeration pour les variables de session utilisées par
 * {@link HttpSession#getAttribute(java.lang.String)}
 * {@link HttpSession#setAttribute(java.lang.String, java.lang.Object)}
 *
 */
public enum Session {

	/**
	 *
	 */
	USER("user");

	/**
	 *
	 */
	public final String tostring;

	private Session(String tostring) {
		this.tostring = tostring;
	}
}
