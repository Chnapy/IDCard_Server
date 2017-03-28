/*
 * 
 * 
 * 
 */
package enumerations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * NewClass.java
 *
 */
public enum MdpCrypt {

	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");

	public final String str;

	private MdpCrypt(String str) {
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

	public static MdpCrypt getCryptFromString(String str) {
		for (MdpCrypt crypt : MdpCrypt.values()) {
			if (crypt.str.equals(str)) {
				return crypt;
			}
		}
		throw new IllegalArgumentException("MdpCrypt non présent: " + str);
	}

}
