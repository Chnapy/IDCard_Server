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
 * MdpCrypt.java
 *
 * Methodes de cryptage.
 *
 */
public enum MdpCrypt {

	/**
	 *
	 */
	SHA1("SHA-1"),
	/**
	 *
	 */
	SHA256("SHA-256"),
	/**
	 *
	 */
	SHA384("SHA-384"),
	/**
	 *
	 */
	SHA512("SHA-512");

	/**
	 *
	 */
	public final String tostring;

	private MdpCrypt(String tostring) {
		this.tostring = tostring;
	}

	/**
	 * Depuis un mot de passe non-chiffré et un salt,
	 * retourne un mot de passe chiffré
	 *
	 * @param passwordToHash Mot de passe non-chiffré
	 * @param salt           Tableau représentant la clef de cryptage du mot de
	 *                       passe
	 * @return Le mot de passe chiffré via le salt fourni
	 * @throws
	 * sageDigest#getInstance(java.lang.String)}
	 */
	public String getCryptedPassword(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(this.tostring);
		md.update(salt);
		byte[] bytes = md.digest(passwordToHash.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/**
	 * Génere un salt aléatoire.
	 *
	 * @return Un salt aléatoire de taille 16
	 * @throws
	 * ureRandom#getInstance(java.lang.String)}
	 */
	public static byte[] getRandomSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	/**
	 * Renvoi le MdpCrypt correspondant depuis son string
	 *
	 * @param str
	 * @return
	 */
	public static MdpCrypt getCryptFromString(String str) {
		for (MdpCrypt crypt : MdpCrypt.values()) {
			if (crypt.tostring.equals(str)) {
				return crypt;
			}
		}
		throw new IllegalArgumentException("MdpCrypt non présent: " + str);
	}

}
