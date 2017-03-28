/*
 * 
 * 
 * 
 */
package enumerations;

/**
 * Page.java
 *
 */
public enum Page {

	ACCUEIL("Accueil"), CONFIGURATION("Configuration");

	public final String tostring;

	private Page(String tostring) {
		this.tostring = tostring;
	}
}
