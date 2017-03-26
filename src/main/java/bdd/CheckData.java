/*
 * 
 * 
 * 
 */
package bdd;

/**
 * CheckData.java
 * 
 */
public class CheckData {

		public final Class<?> classe;
		public final int minLength;
		public final int maxLength;
		public final String patternRequired;
		public final String patternNotAllowed;

		CheckData(Class<?> classe, int minLength, int maxLength, String patternRequired, String patternNotAllowed) {
			this.classe = classe;
			this.minLength = minLength;
			this.maxLength = maxLength;
			this.patternRequired = patternRequired;
			this.patternNotAllowed = patternNotAllowed;
		}
}
