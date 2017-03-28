/*
 * 
 * 
 * 
 */
package servlet.gestionacces;

import entity.MainEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.Controleur;
import enumerations.Attribut;

/**
 * Servlet appelé lors de l'inscription.
 *
 * @author Richard
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends Controleur {

	/**
	 * On surcharge la fonction pour que, lorsque l'inscription réussi, on
	 * redirige directement l'utilisateur vers le servlet de connexion.
	 * Ainsi il est immédiatement connecté.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainEntity me = this.onPost(request, response);
		if (!me.isSuccess()) {
			this.sendReturn(me, response);
		} else {
			request.setAttribute(Attribut.IS_MAIL.tostring, false);
			request.getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		}
	}
}
