/*
 * 
 * 
 * 
 */
package servlet;

import entity.MainEntity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Richard
 */
@WebServlet(name = "ConfigurationServlet", urlPatterns = {"/configuration"})
public class ConfigurationServlet extends Controleur {

	@Override
	protected MainEntity onPost(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
