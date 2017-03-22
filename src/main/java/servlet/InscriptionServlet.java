/*
 * 
 * 
 * 
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.Const;
import main.Const.CheckData;

/**
 *
 * @author Richard
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends HttpServlet {

	private static final Checker CH_PSEUDO = new Checker(Const.CD_PSEUDO),
			CH_MAIL = new Checker(Const.CD_MAIL),
			CH_MDP = new Checker(Const.CD_MDP);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo"),
				mail = request.getParameter("mail"),
				mdp = request.getParameter("mdp");

		try (PrintWriter out = response.getWriter()) {

			if (!this.checkAll(pseudo, mail, mdp)) {
				//TODO Erreur
				out.println("Inscription impossible. Check des parametres échoué.");
				System.err.println("Inscription impossible. Check des parametres échoué.");
				return;
			}

			InscriptionModele modele = new InscriptionModele();

			try {
				if (modele.issetClient(pseudo, mail)) {
					//TODO Erreur
					out.println("Inscription impossible. Client déjà existant.");
					System.err.println("Inscription impossible. Client déjà existant.");
					return;
				}

				modele.inscription(pseudo, mail, mdp);
			} catch (SQLException | NoSuchAlgorithmException ex) {
				Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
			out.println("FIN !");
		}
	}

	private boolean checkAll(String pseudo, String mail, String mdp) {
		return CH_PSEUDO.check(pseudo) && CH_MAIL.check(mail) && CH_MDP.check(mdp);
	}

	public static class Checker {

		private final CheckData cd;

		public Checker(CheckData cd) {
			this.cd = cd;
		}

		public boolean check(String txt) {
			return txt.length() >= cd.minLength && txt.length() <= cd.maxLength
					&& (cd.patternRequired == null || txt.matches(cd.patternRequired))
					&& (cd.patternNotAllowed == null || !txt.matches(cd.patternNotAllowed));
		}

	}

}
