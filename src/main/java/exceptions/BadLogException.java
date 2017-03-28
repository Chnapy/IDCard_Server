/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * NoIssetPseudoException.java
 *
 * Exception lors de l'echec d'authentification dû à de mauvais identifiants.
 *
 */
public class BadLogException extends ExceptionGeree {

	/**
	 *
	 */
	public BadLogException() {
		super(Code.E_CONNEXION_CHECK);
	}

}
