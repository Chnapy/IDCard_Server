/*
 * 
 * 
 * 
 */
package exceptions;

import enumerations.Code;

/**
 * IssetClientException.java
 * 
 */
public class IssetClientException extends ExceptionGeree {

	public IssetClientException() {
		super(Code.E_INSCRIPTION_ISSET);
	}

}
