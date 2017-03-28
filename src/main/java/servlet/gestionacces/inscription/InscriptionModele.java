/*
 * 
 * 
 * 
 */
package servlet.gestionacces.inscription;

import bdd.Modele;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typechiffrage.TYPECHIFFRAGE;
import static bdd.generated.tables.User_1.USER_1;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import javafx.util.Pair;
import bdd.Const;
import org.jooq.Record;
import org.jooq.Result;

/**
 * ConnexionModele.java
 *
 */
public class InscriptionModele extends Modele {

	public boolean issetClient(String pseudo, String mail) throws SQLException, Exception {
		return bdd((create) -> {

			Result<Record> results = create.select()
					.from(USER_1).naturalJoin(PROPRIETE).naturalJoin(VALEUR).naturalJoin(VALEURSTRING)
					.where(PROPRIETE.NOM.eq(PROP_LOGIN_NOM).and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(pseudo.toLowerCase())))
					.or(PROPRIETE.NOM.eq(PROP_MAIL_NOM).and(VALEUR.PRINCIPALE.eq(true)).and(VALEURSTRING.VALEUR.lower().eq(mail.toLowerCase())))
					.fetch();
			return !results.isEmpty();
		});
	}

	public void inscription(String pseudo, String mail, String mdp) throws SQLException, NoSuchAlgorithmException, Exception {

		Pair<byte[], String> mdpCrypt = this.getCryptedPassword(mdp, Const.MDPMAIN_CRYPT);

		bdd((create) -> {

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
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq(PROP_LOGIN_NOM)).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
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
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq(PROP_MAIL_NOM)).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
					)
					.set(VALEUR.PRINCIPALE, true)
					.set(VALEUR.ID_VALEURTYPEE, id_val_typ_mail)
					.execute();

			//MDP
			String salt = this.saltToString(mdpCrypt.getKey());
			String mdpCryptStr = mdpCrypt.getValue();

			long id_val_typ_mdp = create.insertInto(VALEURMDP)
					.set(VALEURMDP.VALEUR, mdpCryptStr)
					.set(VALEURMDP.SALT, salt)
					.set(VALEURMDP.ID_TYPECHIFFRAGE,
							create.select(TYPECHIFFRAGE.ID_TYPECHIFFRAGE)
									.from(TYPECHIFFRAGE)
									.where(TYPECHIFFRAGE.TYPECHIFFRAGE_.eq(Const.MDPMAIN_CRYPT.str))
									.fetchOne().getValue(TYPECHIFFRAGE.ID_TYPECHIFFRAGE)
					)
					.returning(VALEURMDP.ID_VALEURTYPEE)
					.fetchOne()
					.getIdValeurtypee();

			create.insertInto(VALEUR)
					.set(VALEUR.ID_USER, id_user)
					.set(VALEUR.ID_PROPRIETE,
							create.select(PROPRIETE.ID_PROPRIETE).from(PROPRIETE).where(PROPRIETE.NOM.eq(PROP_MDP_NOM)).fetchOne().getValue(PROPRIETE.ID_PROPRIETE)
					)
					.set(VALEUR.PRINCIPALE, true)
					.set(VALEUR.ID_VALEURTYPEE, id_val_typ_mdp)
					.execute();

			return null;
		});
	}

}
