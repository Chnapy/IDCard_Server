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
 * Exception gérée, et donc lançée manuellement et prévue.
 * Possède un code d'erreur utilisé pour notifier le client du type d'erreur.
 *
 */
public class ExceptionGeree extends Exception {

	private final Code codeErreur;

	/**
	 *
	 * @param codeErreur
	 * @param cause
	 */
	public ExceptionGeree(Code codeErreur, Throwable cause) {
		super(cause);
		this.codeErreur = codeErreur;
	}

	/**
	 *
	 * @param codeErreur
	 * @param txt
	 */
	public ExceptionGeree(Code codeErreur, String txt) {
		super(txt);
		this.codeErreur = codeErreur;
	}

	/**
	 *
	 * @param codeErreur
	 */
	public ExceptionGeree(Code codeErreur) {
		this.codeErreur = codeErreur;
	}

	/**
	 *
	 * @return
	 */
	public Code getCodeErreur() {
		return codeErreur;
	}

}
