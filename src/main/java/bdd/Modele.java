/*
 * 
 * 
 * 
 */
package bdd;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.util.Pair;
import enumerations.MdpCrypt;
import exceptions.BddException;

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

	protected void checkRows(int nbrRows) throws BddException {
		if (nbrRows != 1) {
			throw new BddException("Le nombre de lignes affectées est anormal: " + nbrRows);
		}
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
}
