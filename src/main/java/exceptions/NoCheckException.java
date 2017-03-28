/*
 * 
 * 
 * 
 */
package exceptions;

import bdd.CheckData;
import enumerations.Code;
import enumerations.Param;

/**
 * NewClass.java
 *
 * Donnée envoyée ne respectant pas le format demandé.
 * Habituellement la valeur d'un input
 *
 */
public class NoCheckException extends ExceptionGeree {

	/**
	 *
	 * @param value
	 */
	public NoCheckException(String value) {
		super(Code.E_CHECK, "Check échoué: [" + value + "]");
	}

	/**
	 *
	 * @param cd
	 * @param value
	 */
	public NoCheckException(CheckData cd, String value) {
		super(Code.E_CHECK, "Check échoué: " + cd + " [" + value + "]");
	}

	/**
	 *
	 * @param param
	 * @param value
	 */
	public NoCheckException(Param param, String value) {
		super(Code.E_CHECK, "Check échoué: " + param.tostring + " [" + value + "]");
	}
}
