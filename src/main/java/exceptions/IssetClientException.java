/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * IssetClientException.java
 *
 * Tentative d'accès à un user non existant (Bdd)
 *
 */
public class IssetClientException extends ExceptionGeree {

	/**
	 *
	 */
	public IssetClientException() {
		super(Code.E_INSCRIPTION_ISSET);
	}

}
