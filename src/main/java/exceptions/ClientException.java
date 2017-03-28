/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * ClientException.java
 * 
 */
public class ClientException extends ExceptionGeree {

	public ClientException() {
		super(Code.E_CLIENT);
	}

	public ClientException(String txt) {
		super(Code.E_CLIENT, txt);
	}

}