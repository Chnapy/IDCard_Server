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
import java.io.IOException;
import modele.InscriptionModele;
import javax.servlet.ServletException;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainEntity me = this.onPost(request, response);
		if (!me.isSuccess()) {
			this.sendReturn(me, response);
		} else {
			request.setAttribute(Attr.IS_MAIL.attr, false);
			request.getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		}
	}

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
			return new MainEntityError(Const.Code.E_INSCRIPTION_CHECK);
		}

		InscriptionModele modele = new InscriptionModele();

		try {
			if (modele.issetClient(pseudo, mail)) {
				return new MainEntityError(Const.Code.E_INSCRIPTION_ISSET);
			}

			modele.inscription(pseudo, mail, mdp);
		} catch (Exception ex) {
			return new MainEntityError(Const.Code.E_SERVEUR);
		}
		return new MainEntitySuccess();
	}
}
