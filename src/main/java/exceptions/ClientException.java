/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * ClientException.java
 * Erreur dû aux actions client.
 * Client mal conçu, ou alors requêtes Ajax mal intentionnées.
 *
 */
public class ClientException extends ExceptionGeree {

	/**
	 *
	 */
	public ClientException() {
		super(Code.E_CLIENT);
	}

	/**
	 *
	 * @param txt
	 */
	public ClientException(String txt) {
		super(Code.E_CLIENT, txt);
	}

}
