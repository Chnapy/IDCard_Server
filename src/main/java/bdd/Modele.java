/*
 * 
 * 
 * 
 */
package bdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import javafx.util.Pair;

/**
 * Modele.java
 *
 */
public abstract class Modele {

	protected static final String PROP_LOGIN_NOM = "login",
			PROP_MAIL_NOM = "mail",
			PROP_MDP_NOM = "mdp";

	private final BDD DB;

	public Modele() {
		this.DB = new BDD();
	}

	protected <T> T bdd(BDDContent bddC) throws SQLException, Exception {
		return DB.act(bddC);
	}

	protected Pair<byte[], String> getCryptedPassword(String mdp, MdpCrypt crypt) throws NoSuchAlgorithmException {

		byte[] salt = MdpCrypt.getRandomSalt();
		String mdpCrypt = crypt.getCryptedPassword(mdp, salt);

		return new Pair(salt, mdpCrypt);
	}

	protected String saltToString(byte[] salt) {
		String salt_str = salt[0] + "";
		for (int i = 1; i < salt.length; i++) {
			salt_str += "." + salt[i];
		}
		return salt_str;
	}

	protected byte[] stringToSalt(String saltStr) {
		String[] split = saltStr.split("\\.");
		byte[] salt = new byte[split.length];
		for (int i = 0; i < salt.length; i++) {
			salt[i] = Byte.parseByte(split[i]);
		}
		return salt;
	}

	public static enum MdpCrypt {

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

}
