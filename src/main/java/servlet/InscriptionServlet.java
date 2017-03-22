/*
 * 
 * 
 * 
 */
package servlet;

import bdd.Const;
import entity.ContentEntity;
import entity.MainEntity;
import entity.MainEntity.MainEntityError;
import entity.MainEntity.MainEntitySuccess;
import modele.InscriptionModele;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Richard
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

		String pseudo, mail, mdp;

		try {
			pseudo = this.checkParam(Param.LOGIN, request);
			mail = this.checkParam(Param.MAIL, request);
			mdp = this.checkParam(Param.MDP, request);

		} catch (Param.NoCheckException ex) {
			//TODO Erreur
			ex.printStackTrace();
			System.err.println("Inscription impossible. Check des parametres échoué.");
			return new MainEntityError(Const.Code.E_INSCRIPTION_CHECK, new ContentEntity(false, null));
		}

		InscriptionModele modele = new InscriptionModele();

		try {
			if (modele.issetClient(pseudo, mail)) {
				//TODO Erreur
				System.err.println("Inscription impossible. Client déjà existant.");
				return new MainEntityError(Const.Code.E_INSCRIPTION_ISSET, new ContentEntity(false, null));
			}

			modele.inscription(pseudo, mail, mdp);
		} catch (Exception ex) {
			Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new MainEntitySuccess(new ContentEntity(false, null));
	}
}
