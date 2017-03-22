/*
 * 
 * 
 * 
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})
public class ConnexionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo"),
				mail = request.getParameter("mail"),
				mdp = request.getParameter("mdp");
		boolean isMail = Boolean.parseBoolean(request.getParameter("isMail"));
		
		ConnexionModele modele = new ConnexionModele();
		
		if(isMail) {
			modele.connexionParMail(mail, mdp);
		} else {
			modele.connexionParPseudo(pseudo, mdp);
		}
	}

}
