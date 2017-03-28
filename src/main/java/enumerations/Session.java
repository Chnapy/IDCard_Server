/*
 * 
 * 
 * 
 */
package enumerations;

/**
 * Session.java
 *
 */
public enum Session {

	USER("user");

	public final String tostring;

	private Session(String tostring) {
		this.tostring = tostring;
	}
}
