/*
 * 
 * 
 * 
 */
package servlet;

import entity.ContentEntity;
import entity.MainEntity;
import entity.ProprieteEntity;
import entity.UserEntity;
import enumerations.Attribut;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.gestionacces.connexion.ProprieteModele;
import enumerations.Page;
import enumerations.Session;

/**
 * Servlet appelé lors du premier accès à la tostring
 *
 * @author Richard
 */
@WebServlet(name = "IndexServlet", urlPatterns = {IndexServlet.URL})
public class IndexServlet extends Controleur {

	public static final String URL = "/index.html";

	private static final String WEB_FILE = "/start.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("INDEX");

		user = (UserEntity) request.getSession().getAttribute(Session.USER.tostring);
		
		boolean isConnected;
		try {
			isConnected = user.isConnected();
		} catch (NullPointerException ex) {
			isConnected = false;
		}

		List<ProprieteEntity> proprietes = null;
		if (isConnected) {
			ProprieteModele modele = new ProprieteModele();
			try {
				proprietes = modele.getAllProprietes(user.getId_user());
			} catch (Exception ex) {
				Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		ContentEntity donnees = new ContentEntity(user, proprietes);

		request.setAttribute(Attribut.DONNEES.tostring, this.entityToJSONString(donnees));
		request.setAttribute(Attribut.PAGE.tostring, (isConnected ? Page.CONFIGURATION : Page.ACCUEIL).tostring);

		getServletContext().getRequestDispatcher(WEB_FILE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
