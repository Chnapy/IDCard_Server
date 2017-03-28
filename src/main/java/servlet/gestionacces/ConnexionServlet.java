/*
 * 
 * 
 * 
 */
package servlet.gestionacces;

import javax.servlet.annotation.WebServlet;
import servlet.Controleur;

/**
 * Servlet appelé lors de la connexion.
 *
 * @author Richard
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/connexion"})
public class ConnexionServlet extends Controleur {

}
