/*
 * 
 * 
 * 
 */
package servlet;

import bdd.Const.Code;
import entity.ContentEntity;
import entity.MainEntity;
import entity.ProprieteEntity;
import entity.UserEntity;
import java.util.List;
import modele.ConnexionModele;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.ConnexionModele.MauvaisMdpException;
import modele.ConnexionModele.NoIssetMailException;
import modele.ConnexionModele.NoIssetPseudoException;
import modele.ProprieteModele;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})
public class ConnexionServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {
		
		ConnexionModele con_modele = new ConnexionModele();

		boolean success;
		Code code;
		UserEntity user = null;
		List<ProprieteEntity> proprietes = null;

		try {
			boolean isMail = (boolean) (request.getAttribute(Attr.IS_MAIL.attr) != null
					? request.getAttribute(Attr.IS_MAIL.attr)
					: this.checkParam(Param.IS_MAIL, request));
			String mdp = this.checkParam(Param.MDP, request);
			if (isMail) {
				String mail = this.checkParam(Param.MAIL, request);
				user = con_modele.connexionParMail(mail, mdp);
			} else {
				String pseudo = this.checkParam(Param.LOGIN, request);
				user = con_modele.connexionParPseudo(pseudo, mdp);
			}
			
			ProprieteModele prop_modele = new ProprieteModele();
			
			proprietes = prop_modele.getAllProprietes(con_modele.getId_user());

			success = true;
			code = Code.OK;
			
			request.getSession().setAttribute(Sess.USER.sess, user);

		} catch (Param.NoCheckException | NullPointerException | NoIssetPseudoException | NoIssetMailException | MauvaisMdpException ex) {
			ex.printStackTrace();
			code = Code.E_CONNEXION_CHECK;
			success = false;
		} catch (Exception ex) {
			ex.printStackTrace();
			code = Code.E_SERVEUR;
			success = false;
		}

		return new MainEntity(success, code, new ContentEntity(user, proprietes));

	}

}
