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
import javax.servlet.http.HttpSession;
import org.jooq.tools.json.JSONObject;

/**
 * Servlet appelé lors du premier accès à la page
 *
 * @author Richard
 */
@WebServlet(name = "IndexServlet", urlPatterns = {IndexServlet.URL})
public class IndexServlet extends HttpServlet {

	public static final String URL = "/index.html";

	private static final String WEB_FILE = "/start.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("Acces index : ");
		System.out.println(request.getRequestURI());

		HttpSession session = request.getSession();
		boolean isConnected;
		try {
			isConnected = (boolean) session.getAttribute("isConnected");
		} catch (NullPointerException ex) {
			isConnected = false;
		}

		JSONObject user = new JSONObject();
		user.put("connected", isConnected);
		if (isConnected) {
			user.put("pseudo", session.getAttribute("pseudo"));
		}

		request.setAttribute("user", user);
		request.setAttribute("page", isConnected ? "Configuration" : "Accueil");

		getServletContext().getRequestDispatcher(WEB_FILE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
