/*
 * 
 * 
 * 
 */
package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Classe regroupant les constantes globales
 *
 */
public class Const {

	public static final CheckData CD_PSEUDO = new CheckData(3, 32, null, "/\\s/g"),
			CD_MAIL = new CheckData(6, 64, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", null),
			CD_MDP = new CheckData(6, 32, null, "/\\s/g");

	public static final int PROPRIETE_VALMAX = 64;

	public static final MdpCrypt MDPMAIN_CRYPT = MdpCrypt.SHA256;

	public static enum MdpCrypt {

		SHA1("SHA-1"),
		SHA256("SHA-256"),
		SHA384("SHA-384"),
		SHA512("SHA-512");

		public final String str;

		MdpCrypt(String str) {
			this.str = str;
		}

		public String getCryptedPassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {

			MessageDigest md = MessageDigest.getInstance(this.str);
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
		}

		public static byte[] getRandomSalt() throws NoSuchAlgorithmException {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt;
		}

	}

	public static class CheckData {

		public final int minLength;
		public final int maxLength;
		public final String patternRequired;
		public final String patternNotAllowed;

		CheckData(int minLength, int maxLength, String patternRequired, String patternNotAllowed) {
			this.minLength = minLength;
			this.maxLength = maxLength;
			this.patternRequired = patternRequired;
			this.patternNotAllowed = patternNotAllowed;
		}
	}

}
