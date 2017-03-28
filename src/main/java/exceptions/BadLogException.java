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
 */
public class BadLogException extends ExceptionGeree {

	public BadLogException() {
		super(Code.E_CONNEXION_CHECK);
	}

}
