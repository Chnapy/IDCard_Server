/*
 * 
 * 
 * 
 */
package servlet.enumerations;

/**
 * Code.java
 *
 */
public enum Code {

	//0xx = messages informatifs
	OK(000),
	//1xx = erreur réseau client
	//--

	//2xx = erreur serveur
	E_SERVEUR(200),
	//3xx = erreur serveur critique
	E_CRITIQUE(300),
	//4xx = erreur inconnue (code inconnu)
	//--

	//6xx = erreur classiques

	//61x = connexion
	E_CONNEXION_CHECK(610),
	//62x = inscription
	E_INSCRIPTION_CHECK(620), E_INSCRIPTION_ISSET(621),
	//63x = configuration
	E_CONFIGURATION_UPDATE(630), E_CONFIGURATION_REMOVESITE(631);

	public final int code;

	private Code(int code) {
		this.code = code;
	}
}
