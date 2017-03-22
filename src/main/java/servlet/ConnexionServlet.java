/*
 * 
 * 
 * 
 */
package servlet;

import bdd.Const;
import bdd.Const.Code;
import entity.ContentEntity;
import entity.MainEntity;
import entity.MainEntity.MainEntityError;
import entity.MainEntity.MainEntitySuccess;
import entity.UserEntity;
import modele.ConnexionModele;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.ConnexionModele.MauvaisMdpException;
import modele.ConnexionModele.NoIssetPseudoException;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})
public class ConnexionServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

		ConnexionModele modele = new ConnexionModele();

		boolean success;
		Code code;
		UserEntity user = null;

		try {

			boolean isMail = this.checkParam(Param.IS_MAIL, request);
			String mdp = this.checkParam(Param.MDP, request);
			if (isMail) {
				String mail = this.checkParam(Param.MAIL, request);
				user = modele.connexionParMail(mail, mdp);
			} else {
				String pseudo = this.checkParam(Param.LOGIN, request);
				user = modele.connexionParPseudo(pseudo, mdp);
			}

			success = true;
			code = Code.OK;

		} catch (Param.NoCheckException | NoIssetPseudoException | MauvaisMdpException ex) {
			code = Code.E_CONNEXION_CHECK;
			success = false;
		} catch (Exception ex) {
			code = Code.E_SERVEUR;
			success = false;
		}

		return new MainEntity(success, code, new ContentEntity(success, user));

	}

}
