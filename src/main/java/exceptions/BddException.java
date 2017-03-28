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
 */
public class BddException extends ExceptionGeree {

	public BddException() {
		super(Code.E_SERVEUR);
	}

	public BddException(String txt) {
		super(Code.E_SERVEUR, txt);
	}

}