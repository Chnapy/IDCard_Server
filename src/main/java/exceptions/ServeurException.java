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
public class ServeurException extends ExceptionGeree {

	public ServeurException() {
		super(Code.E_SERVEUR);
	}

}