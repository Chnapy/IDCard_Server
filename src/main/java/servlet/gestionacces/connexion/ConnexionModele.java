/*
 * 
 * 
 * 
 */
package servlet.gestionacces.connexion;

import bdd.Modele;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typechiffrage.TYPECHIFFRAGE;
import static bdd.generated.tables.User_1.USER_1;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import entity.UserEntity;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import org.jooq.DSLContext;
import org.jooq.Record;
import enumerations.MdpCrypt;
import exceptions.BadLogException;

/**
 * ConnexionModele.java
 *
 */
public class ConnexionModele extends Modele {

	private long id_user;

	/**
	 * Gère la connexion par pseudo.
	 *
	 * @param form_pseudo
	 * @param form_mdp
	 * @return Données utilisateur récupérées depuis la BDD.
	 * @throws SQLException
	 * @throws BadLogException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public UserEntity connexionParPseudo(String form_pseudo, String form_mdp) throws SQLException, BadLogException, NoSuchAlgorithmException, Exception {

		return bdd((create) -> {
			Optional<? extends Record> result_pseudo = create.select(USER_1.ID_USER, USER_1.DATEINSCRIPTION, USER_1.DATEDERNIERECONNEXION, USER_1.NBRCONNEXION,
					VALEURSTRING.VALEUR)
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(PROPRIETE.NOM.eq(PROP_LOGIN_NOM).and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(form_pseudo.toLowerCase())))
					.fetchOptional();

			Record record_pseudo = result_pseudo.orElseThrow(BadLogException::new);

			id_user = record_pseudo.get(USER_1.ID_USER);
			String pseudo = record_pseudo.get(VALEURSTRING.VALEUR);
			Date dateinscription = record_pseudo.get(USER_1.DATEINSCRIPTION);
			Date datederniereconnexion = record_pseudo.get(USER_1.DATEDERNIERECONNEXION);
			long nbrconnexion = record_pseudo.get(USER_1.NBRCONNEXION);

			Optional<? extends Record> result_mail = create.select(VALEURSTRING.VALEUR)
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(USER_1.ID_USER.eq(id_user).and(PROPRIETE.NOM.eq(PROP_MAIL_NOM)).and(VALEUR.PRINCIPALE.eq(true)))
					.fetchOptional();

			Record record_mail = result_mail.orElseThrow(BadLogException::new);

			String mail = record_mail.get(VALEURSTRING.VALEUR);

			return this.generalConnexion(create, id_user, pseudo, mail, form_mdp, dateinscription, datederniereconnexion, nbrconnexion);
		});

	}

	/**
	 * Gère la connexion par mail.
	 *
	 * @param form_mail
	 * @param form_mdp
	 * @return Données utilisateur récupérées depuis la BDD.
	 * @throws SQLException
	 * @throws BadLogException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public UserEntity connexionParMail(String form_mail, String form_mdp) throws SQLException, BadLogException, NoSuchAlgorithmException, Exception {

		return bdd((create) -> {
			Optional<? extends Record> result_mail = create.select(USER_1.ID_USER, USER_1.DATEINSCRIPTION, USER_1.DATEDERNIERECONNEXION, USER_1.NBRCONNEXION,
					VALEURSTRING.VALEUR)
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(PROPRIETE.NOM.eq(PROP_MAIL_NOM).and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(form_mail.toLowerCase())))
					.fetchOptional();

			Record record_mail = result_mail.orElseThrow(BadLogException::new);

			id_user = record_mail.get(USER_1.ID_USER);
			String mail = record_mail.get(VALEURSTRING.VALEUR);
			Date dateinscription = record_mail.get(USER_1.DATEINSCRIPTION);
			Date datederniereconnexion = record_mail.get(USER_1.DATEDERNIERECONNEXION);
			long nbrconnexion = record_mail.get(USER_1.NBRCONNEXION);

			Optional<? extends Record> result_pseudo = create.select(VALEURSTRING.VALEUR)
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(USER_1.ID_USER.eq(id_user).and(PROPRIETE.NOM.eq(PROP_LOGIN_NOM)).and(VALEUR.PRINCIPALE.eq(true)))
					.fetchOptional();

			Record record_pseudo = result_pseudo.orElseThrow(BadLogException::new);

			String pseudo = record_pseudo.get(VALEURSTRING.VALEUR);

			return this.generalConnexion(create, id_user, pseudo, mail, form_mdp, dateinscription, datederniereconnexion, nbrconnexion);
		});
	}

	/**
	 * Procède à la vérification du mot de passe.
	 *
	 * @param create
	 * @param id_user
	 * @param pseudo
	 * @param mail
	 * @param form_mdp
	 * @param dateinscription
	 * @param datederniereconnexion
	 * @param nbrconnexion
	 * @return Données utilisateur récupérées depuis la BDD.
	 * @throws BadLogException
	 * @throws NoSuchAlgorithmException
	 */
	private UserEntity generalConnexion(DSLContext create, long id_user, String pseudo, String mail, String form_mdp, Date dateinscription, Date datederniereconnexion, long nbrconnexion) throws BadLogException, NoSuchAlgorithmException {

		Optional<? extends Record> result_mdp = create.select(VALEURMDP.VALEUR, VALEURMDP.SALT, TYPECHIFFRAGE.TYPECHIFFRAGE_)
				.from(VALEUR).naturalJoin(PROPRIETE).naturalJoin(VALEURMDP).naturalJoin(TYPECHIFFRAGE)
				.where(PROPRIETE.NOM.eq(PROP_MDP_NOM).and(VALEUR.PRINCIPALE.eq(true)).and(VALEUR.ID_USER.eq(id_user)))
				.fetchOptional();

		Record record_mdp = result_mdp.orElseThrow(BadLogException::new);

		String mdpCrypt = record_mdp.get(VALEURMDP.VALEUR);
		String saltStr = record_mdp.get(VALEURMDP.SALT);
		String typeChiffrage = record_mdp.get(TYPECHIFFRAGE.TYPECHIFFRAGE_);

		byte[] salt = this.stringToSalt(saltStr);
		MdpCrypt crypt = MdpCrypt.getCryptFromString(typeChiffrage);

		String mdpFormCrypt = crypt.getCryptedPassword(form_mdp, salt);

		boolean bonMdp = mdpCrypt.equals(mdpFormCrypt);
		if (!bonMdp) {
			throw new BadLogException();
		}

		nbrconnexion++;
		datederniereconnexion = new Date(System.currentTimeMillis());
		this.newConnexion(create, id_user, datederniereconnexion, nbrconnexion);

		return new UserEntity(id_user, pseudo, mail, dateinscription, datederniereconnexion, nbrconnexion);
	}

	/**
	 * Notifie la base de donnée de la nouvelle connexion.
	 *
	 * @param create
	 * @param id_user
	 * @param derniereco
	 * @param nbrco
	 */
	private void newConnexion(DSLContext create, long id_user, Date derniereco, long nbrco) {
		create.update(USER_1)
				.set(USER_1.DATEDERNIERECONNEXION, derniereco)
				.set(USER_1.NBRCONNEXION, nbrco)
				.where(USER_1.ID_USER.eq(id_user))
				.execute();
	}

	/**
	 *
	 * @return ID user de la base de donnée.
	 */
	public long getId_user() {
		return id_user;
	}

}
