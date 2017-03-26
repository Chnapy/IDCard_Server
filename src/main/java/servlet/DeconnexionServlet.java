/*
 * 
 * 
 * 
 */
package servlet;

import entity.ContentEntity;
import entity.MainEntity;
import entity.UserEntity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.enumerations.Code;

/**
 *
 * @author Richard
 */
@WebServlet(name = "DeconnexionServlet", urlPatterns = {"/deconnexion"})
public class DeconnexionServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {

//		DeconnexionModele modele = new DeconnexionModele();

		this.deconnexion(request);

		return new MainEntity(true, Code.OK, new ContentEntity(new UserEntity(false), null));

	}

	private void deconnexion(HttpServletRequest request) {
		request.getSession().invalidate();
	}

}
