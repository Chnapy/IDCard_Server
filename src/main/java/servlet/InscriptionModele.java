/*
 * 
 * 
 * 
 */
package servlet;

import bdd.BDD;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.User_1.USER_1;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import javafx.util.Pair;
import main.Const;
import main.Const.MdpCrypt;
import org.jooq.Record;
import org.jooq.Result;

/**
 * ConnexionModele.java
 *
 */
public class InscriptionModele {

	public boolean issetClient(String pseudo, String mail) throws SQLException {
		return BDD.act((create) -> {

			Result<Record> results = create.select()
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(PROPRIETE.NOM.eq("login").and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(pseudo.toLowerCase())))
					.or(PROPRIETE.NOM.eq("mail").and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(mail.toLowerCase())))
					.fetch();
			return !results.isEmpty();
		});
	}

	public void inscription(String pseudo, String mail, String mdp) throws SQLException, NoSuchAlgorithmException {

		Pair<byte[], String> mdpCrypt = this.getCryptedPassword(mdp, Const.MDPMAIN_CRYPT);

		BDD.act((create) -> {

			//USER
			long id_user = create.insertInto(USER_1, USER_1.DATEINSCRIPTION)
					.values(new Date(System.currentTimeMillis()))
					.returning(USER_1.ID_USER)
					.fetchOne()
					.getIdUser();

			//LOGIN
			long id_val_typ_login = create.insertInto(VALEURSTRING)
					.set(VALEURSTRING.VALEUR, pseudo)
					.returning(VALEURSTRING.ID_VALEURTYPEE)
					.fetchOne()
					.getIdValeurtypee();

			create.insertInto(VALEUR)
					.set(VALEUR.ID_USER, id_user)
					.set(VALEUR.ID_PROPRIETE,
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq("login")).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
					)
					.set(VALEUR.PRINCIPALE, true)
					.set(VALEUR.ID_VALEURTYPEE, id_val_typ_login)
					.execute();

			//MAIL
			long id_val_typ_mail = create.insertInto(VALEURSTRING)
					.set(VALEURSTRING.VALEUR, mail)
					.returning(VALEURSTRING.ID_VALEURTYPEE)
					.fetchOne()
					.getIdValeurtypee();

			create.insertInto(VALEUR)
					.set(VALEUR.ID_USER, id_user)
					.set(VALEUR.ID_PROPRIETE,
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq("mail")).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
					)
					.set(VALEUR.PRINCIPALE, true)
					.set(VALEUR.ID_VALEURTYPEE, id_val_typ_mail)
					.execute();

			//MDP
			
			String salt = mdpCrypt.getKey()[0] + "";
			for (int i = 1; i < mdpCrypt.getKey().length; i++) {
				salt += "." + mdpCrypt.getKey()[i];
			}

			long id_val_typ_mdp = create.insertInto(VALEURMDP)
					.set(VALEURMDP.VALEUR, mdpCrypt.getValue())
					.set(VALEURMDP.SALT, salt)
					.returning(VALEURMDP.ID_VALEURTYPEE)
					.fetchOne()
					.getIdValeurtypee();

			create.insertInto(VALEUR)
					.set(VALEUR.ID_USER, id_user)
					.set(VALEUR.ID_PROPRIETE,
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq("mdp")).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
					)
					.set(VALEUR.PRINCIPALE, true)
					.set(VALEUR.ID_VALEURTYPEE, id_val_typ_mdp)
					.execute();

			return null;
		});
	}

	private Pair<byte[], String> getCryptedPassword(String mdp, MdpCrypt crypt) throws NoSuchAlgorithmException {

		byte[] salt = MdpCrypt.getRandomSalt();
		String mdpCrypt = crypt.getCryptedPassword(mdp, salt);

		return new Pair(salt, mdpCrypt);
	}

}
