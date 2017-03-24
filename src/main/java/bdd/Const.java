/*
 * 
 * 
 * 
 */
package bdd;

import bdd.Modele.MdpCrypt;
import java.sql.Date;

/**
 * Classe regroupant les constantes globales
 *
 */
public class Const {

	public static final CheckData CD_PSEUDO = new CheckData(String.class, 3, 32, null, "/\\s/g"),
			CD_MAIL = new CheckData(String.class, 6, 64, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", null),
			CD_MDP = new CheckData(String.class, 6, 32, null, "/\\s/g"),
			CD_BOOLEAN = new CheckData(Boolean.class, -1, -1, null, null),
			CD_LONG = new CheckData(Long.class, -1, -1, null, null),
			CD_DOUBLE = new CheckData(Double.class, -1, -1, null, null),
			CD_VALSTRING = new CheckData(String.class, 1, 128, null, null),
			CD_INTEGER = new CheckData(Integer.class, -1, -1, null, null),
			CD_DATE = new CheckData(Date.class, -1, -1, null, null);

	public static final int PROPRIETE_VALMAX = 64;

	public static final MdpCrypt MDPMAIN_CRYPT = MdpCrypt.SHA256;

	public static enum Code {

		//0xx = messages informatifs
		OK(000),
		//1xx = erreur réseau client
		//--

		//2xx = erreur serveur
		E_SERVEUR(200),
		//3xx = erreur serveur critique
		E_CRITIQUE(300),
		//4xx = erreur inconnue (code inconnu)
		//--

		//6xx = erreur classiques

		//61x = connexion
		E_CONNEXION_CHECK(610),
		//62x = inscription
		E_INSCRIPTION_CHECK(620), E_INSCRIPTION_ISSET(621),
		//63x = configuration
		E_CONFIGURATION_UPDATE(630),
		;

		public final int code;

		private Code(int code) {
			this.code = code;
		}
	}

	public static class CheckData {

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

}
