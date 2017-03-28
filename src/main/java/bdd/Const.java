/*
 * 
 * 
 * 
 */
package bdd;

import java.sql.Date;
import enumerations.MdpCrypt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe regroupant les constantes globales
 *
 */
public class Const {

	static {
		//Pour éviter certains messages console informatifs
		Logger.getLogger("org.jooq.Constants").setLevel(Level.WARNING);
	}

	/**
	 * Défini le mode debug
	 */
	public static final boolean DEBUG = false;

	/**
	 * CheckData pour les pseudo utilisateur
	 */
	public static final CheckData CD_PSEUDO = new CheckData(String.class, 3, 32, null, "/\\s/g"),
			/**
			 * CheckData pour les mail
			 */
			CD_MAIL = new CheckData(String.class, 6, 64, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", null),
			/**
			 * CheckData pour les mot de passe
			 */
			CD_MDP = new CheckData(String.class, 6, 32, null, "/\\s/g"),
			/**
			 * CheckData pour les booléen
			 */
			CD_BOOLEAN = new CheckData(Boolean.class, -1, -1, null, null),
			/**
			 * CheckData pour les long
			 */
			CD_LONG = new CheckData(Long.class, -1, -1, null, null),
			/**
			 * CheckData pour les double
			 */
			CD_DOUBLE = new CheckData(Double.class, -1, -1, null, null),
			/**
			 * CheckData pour les string normaux
			 */
			CD_VALSTRING = new CheckData(String.class, 1, 128, null, null),
			/**
			 * ChackData pour les entiers
			 */
			CD_INTEGER = new CheckData(Integer.class, -1, -1, null, null),
			/**
			 * CheckData pour les dates
			 */
			CD_DATE = new CheckData(Date.class, -1, -1, null, null);

	/**
	 * Défini le nombre maximum de valeurs que peut posséder une propriété
	 */
	public static final int PROPRIETE_VALMAX = 64;

	/**
	 * Cryptage par défaut des mot de passe
	 */
	public static final MdpCrypt MDPMAIN_CRYPT = MdpCrypt.SHA256;

}
