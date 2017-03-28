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
 */
public class NoCheckException extends ExceptionGeree {

	public NoCheckException(String value) {
		super(Code.E_CHECK, "Check échoué: [" + value + "]");
	}

	public NoCheckException(CheckData cd, String value) {
		super(Code.E_CHECK, "Check échoué: " + cd + " [" + value + "]");
	}

	public NoCheckException(Param param, String value) {
		super(Code.E_CHECK, "Check échoué: " + param.tostring + " [" + value + "]");
	}
}
