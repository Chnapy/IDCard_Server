/*
 * 
 * 
 * 
 */
package servlet;

import entity.ContentEntity;
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
import servlet.gestionacces.connexion.ProprieteModele;
import enumerations.Page;
import enumerations.Session;

/**
 * Servlet appelé lors du premier accès au site (pas en Ajax donc !).
 *
 * @author Richard
 */
@WebServlet(name = "IndexServlet", urlPatterns = {IndexServlet.URL})
public class IndexServlet extends Controleur {

	/**
	 * Url pattern du servlet.
	 * Par une url vide (/) renvoie vers /index.html.
	 * Il s'agit donc ici que l'utilisateur ait accès au site sans entrer d'url
	 * pattern.
	 *
	 */
	public static final String URL = "/index.html";

	/**
	 * Fichier JSP affiché.
	 */
	private static final String WEB_FILE = "/start.jsp";

	/**
	 * Le premier accès au site se fait en GET.
	 *
	 * On récupère ses données utilisateur depuis la session.
	 * S'il est connecté, on récupère les propriétés.
	 * On transmet toutes ces données au JSP, ainsi que le nom de la page qui
	 * doit être affichée.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 * L'utilisateur n'est pas censé accéder au servlet par POST, on le redirige
	 * donc comme s'il s'agissait d'un GET
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
