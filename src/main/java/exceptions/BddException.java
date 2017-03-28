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
 * Exception liée à la base de donnée.
 * Structure ou données incorrect, nécessite une intervention.
 *
 */
public class BddException extends ExceptionGeree {

	/**
	 *
	 */
	public BddException() {
		super(Code.E_SERVEUR);
	}

	/**
	 *
	 * @param txt
	 */
	public BddException(String txt) {
		super(Code.E_SERVEUR, txt);
	}

}
