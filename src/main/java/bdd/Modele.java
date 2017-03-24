/*
 * 
 * 
 * 
 */
package bdd;

import static bdd.generated.tables.Typechiffrage.TYPECHIFFRAGE;
import static bdd.generated.tables.Valeurbigint.VALEURBIGINT;
import static bdd.generated.tables.Valeurboolean.VALEURBOOLEAN;
import static bdd.generated.tables.Valeurdate.VALEURDATE;
import static bdd.generated.tables.Valeurdouble.VALEURDOUBLE;
import static bdd.generated.tables.Valeurinteger.VALEURINTEGER;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import javafx.util.Pair;
import modele.ProprieteModele;
import org.jooq.Field;
import org.jooq.Table;

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

	protected TypeValeurProp parseType(long id_typeprop) {

		Field valeur_val;
		Field valeur_idtype;
		Table val_;
		String type;

		switch ((int) id_typeprop) {
			case 1:
				type = "text";
				valeur_val = VALEURSTRING.VALEUR;
				valeur_idtype = VALEURSTRING.ID_VALEURTYPEE;
				val_ = VALEURSTRING;
				break;
			case 6:
				type = "email";
				valeur_val = VALEURSTRING.VALEUR;
				valeur_idtype = VALEURSTRING.ID_VALEURTYPEE;
				val_ = VALEURSTRING;
				break;
			case 2:
				type = "number";
				valeur_val = VALEURINTEGER.VALEUR;
				valeur_idtype = VALEURINTEGER.ID_VALEURTYPEE;
				val_ = VALEURINTEGER;
				break;
			case 3:
				type = "checkbox";
				valeur_val = VALEURBOOLEAN.VALEUR;
				valeur_idtype = VALEURBOOLEAN.ID_VALEURTYPEE;
				val_ = VALEURBOOLEAN;
				break;
			case 4:
				type = "date";
				valeur_val = VALEURDATE.VALEUR;
				valeur_idtype = VALEURDATE.ID_VALEURTYPEE;
				val_ = VALEURDATE;
				break;
			case 5:
				type = "mdp";
				valeur_val = TYPECHIFFRAGE.TYPECHIFFRAGE_;
				valeur_idtype = VALEURMDP.ID_VALEURTYPEE;
				val_ = VALEURMDP;
				break;
			case 7:
				type = "number";
				valeur_val = VALEURBIGINT.VALEUR;
				valeur_idtype = VALEURBIGINT.ID_VALEURTYPEE;
				val_ = VALEURBIGINT;
				break;
			case 8:
				type = "double";
				valeur_val = VALEURDOUBLE.VALEUR;
				valeur_idtype = VALEURDOUBLE.ID_VALEURTYPEE;
				val_ = VALEURDOUBLE;
				break;
			default:
				throw new ProprieteModele.TypePropNonGereError();
		}
		return new TypeValeurProp(valeur_val, valeur_idtype, val_, type);
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

	public static class TypeValeurProp {

		private final Field valeur_val;
		private final Field valeur_idtype;
		private final Table val_;
		private final String type;

		public TypeValeurProp(Field valeur_val, Field valeur_idtype, Table val_, String type) {
			this.valeur_val = valeur_val;
			this.valeur_idtype = valeur_idtype;
			this.val_ = val_;
			this.type = type;
		}

		public Field getValeur_val() {
			return valeur_val;
		}

		public Field getValeur_idtype() {
			return valeur_idtype;
		}

		public Table getVal_() {
			return val_;
		}

		public String getType() {
			return type;
		}
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
