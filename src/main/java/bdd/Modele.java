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
import servlet.ControleurAction;

/**
 * Modele.java
 *
 * Class de modèle utilisée par les {@link ControleurAction}
 * Seule cette classe et ses enfants peut intéragir avec la base de donnée.
 *
 */
public abstract class Modele {

	/**
	 * Nom de la propriété gérant les login
	 */
	protected static final String PROP_LOGIN_NOM = "login",
			/**
			 * Nom de la propriété gérant les mail
			 */
			PROP_MAIL_NOM = "mail",
			/**
			 * Nom de la propriété gérant les mot de passe
			 */
			PROP_MDP_NOM = "mdp";

	private final BDD DB;

	/**
	 * Créé un accès à la base de donnée
	 */
	public Modele() {
		this.DB = new BDD();
	}

	/**
	 * Lance une transaction côté base de donnée.
	 * Suit la même procédure que {@link BDD#act(bdd.BDDContent)}
	 *
	 * @param <T>  Utilisé afin que la fonction puisse retourner n'importe quel
	 *             type d'objet sans avoir à faire un cast
	 * @param bddC Représente le contenu de la transaction
	 * @return L'objet retourné par {@link BDD#act(bdd.BDDContent)}
	 * @throws SQLException Lancé lors d'erreurs liées au SQL
	 * @throws Exception    Lancé manuellement (ou inattendu)
	 */
	protected <T> T bdd(BDDContent bddC) throws SQLException, Exception {
		return DB.act(bddC);
	}

	/**
	 * Utilisé lors d'actions comme UPDATE ou DELETE.
	 * Lance une exception lorsque le nombre de lignes affectées est anormal (!=
	 * 1).
	 *
	 * @param nbrRows Nombre de lignes affectées
	 * @throws BddException Si nbrRows est != 1
	 */
	protected void checkRows(int nbrRows) throws BddException {
		if (nbrRows != 1) {
			throw new BddException("Le nombre de lignes affectées est anormal: " + nbrRows);
		}
	}

	/**
	 * Depuis une mot de passe non-chiffré et une méthode de chiffrage,
	 * renvoie une {@link Pair} contenant le salt {@code byte[]}
	 * et le mot de passe chiffré {@code String}
	 *
	 * @param mdp   Le mot de passe non chiffré
	 * @param crypt La méthode de cryptage
	 * @return Une {@link Pair} contenant le salt {@code byte[]} et le mot de
	 *         passe chiffré {@code String}
	 * @throws NoSuchAlgorithmException {@link MdpCrypt#getRandomSalt()}
	 */
	protected Pair<byte[], String> getCryptedPassword(String mdp, MdpCrypt crypt) throws NoSuchAlgorithmException {
		byte[] salt = MdpCrypt.getRandomSalt();
		String mdpCrypt = crypt.getCryptedPassword(mdp, salt);

		return new Pair(salt, mdpCrypt);
	}

	/**
	 * Transforme un salt byte[] en String avec des '.' comme séparateur
	 *
	 * @param salt Le salt sous forme de tableau de byte
	 * @return Le String contenant le salt séparé par des '.'
	 */
	protected String saltToString(byte[] salt) {
		String salt_str = salt[0] + "";
		for (int i = 1; i < salt.length; i++) {
			salt_str += "." + salt[i];
		}
		return salt_str;
	}

	/**
	 * Transforme un salt String en salt byte[]
	 *
	 * @param saltStr Le salt sous forme de String séparé par des '.'
	 * @return Le salt sous forme de tableau de byte
	 */
	protected byte[] stringToSalt(String saltStr) {
		String[] split = saltStr.split("\\.");
		byte[] salt = new byte[split.length];
		for (int i = 0; i < salt.length; i++) {
			salt[i] = Byte.parseByte(split[i]);
		}
		return salt;
	}
}
