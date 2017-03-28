/*
 * 
 * 
 * 
 */
package enumerations;

/**
 * Attribut.java
 * 
 */
public enum Attribut {

		IS_MAIL("isMail"),
		DONNEES("donnees"),
		PAGE("page");

		public final String tostring;

		private Attribut(String tostring) {
			this.tostring = tostring;
		}
	}
