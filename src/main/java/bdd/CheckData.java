/*
 * 
 * 
 * 
 */
package bdd;

/**
 * CheckData.java
 *
 * Données permettant de "checker" (vérifier) si un objet correspond au format
 * demandé.
 * En cas de pattern null, ils sont simplement ignorés.
 *
 */
public class CheckData {

	/**
	 * Classe requise.
	 */
	public final Class<?> classe;

	/**
	 * Longueur minimum requise.
	 */
	public final int minLength;

	/**
	 * Longueur maximum requise.
	 */
	public final int maxLength;

	/**
	 * Pattern regex requis.
	 */
	public final String patternRequired;

	/**
	 * Pattern regex refusé.
	 */
	public final String patternNotAllowed;

	CheckData(Class<?> classe, int minLength, int maxLength, String patternRequired, String patternNotAllowed) {
		this.classe = classe;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.patternRequired = patternRequired;
		this.patternNotAllowed = patternNotAllowed;
	}
}
