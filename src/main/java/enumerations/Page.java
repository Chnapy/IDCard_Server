/*
 * 
 * 
 * 
 */
package enumerations;

/**
 * Page.java
 *
 * Page pouvant être affichées par le client.
 * Utilisé par le serveur lorsque qu'il faut notifier au client quel page
 * afficher lors du premier accès au site.
 *
 */
public enum Page {

	/**
	 *
	 */
	ACCUEIL("Accueil"),
	/**
	 *
	 */
	CONFIGURATION("Configuration");

	/**
	 *
	 */
	public final String tostring;

	private Page(String tostring) {
		this.tostring = tostring;
	}
}
