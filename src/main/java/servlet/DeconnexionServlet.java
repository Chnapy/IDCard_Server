/*
 * 
 * 
 * 
 */
package servlet;

import bdd.Const;
import entity.ContentEntity;
import entity.MainEntity;
import entity.UserEntity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DeconnexionModele;

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

		return new MainEntity(true, Const.Code.OK, new ContentEntity(new UserEntity(false), null));

	}

	private void deconnexion(HttpServletRequest request) {
		request.getSession().invalidate();
	}

}
