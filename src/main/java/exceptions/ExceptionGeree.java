/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * ExceptionGeree.java
 * 
 */
public class ExceptionGeree extends Exception {
	
	private final Code codeErreur;
	
	public ExceptionGeree(Code codeErreur, Throwable cause) {
		super(cause);
		this.codeErreur = codeErreur;
	}
	
	public ExceptionGeree(Code codeErreur, String txt) {
		super(txt);
		this.codeErreur = codeErreur;
	}
	
	public ExceptionGeree(Code codeErreur) {
		this.codeErreur = codeErreur;
	}

	public Code getCodeErreur() {
		return codeErreur;
	}

}
