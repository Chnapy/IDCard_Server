/*
 * 
 * 
 * 
 */
package servlet.gestionacces;

import javax.servlet.annotation.WebServlet;
import servlet.Controleur;

/**
 * Servlet appelé lors de la déconnexion.
 *
 * @author Richard
 */
@WebServlet(name = "DeconnexionServlet", urlPatterns = {"/deconnexion"})
public class DeconnexionServlet extends Controleur {

}
