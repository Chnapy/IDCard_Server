/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * ServeurException.java
 *
 * Erreur serveur imprévue. Demande une intervention.
 *
 */
public class ServeurException extends ExceptionGeree {

	/**
	 *
	 */
	public ServeurException() {
		super(Code.E_SERVEUR);
	}

}
