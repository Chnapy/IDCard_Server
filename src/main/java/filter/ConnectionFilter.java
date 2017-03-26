/*
 * 
 * 
 * 
 */
package filter;

import entity.UserEntity;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.IndexServlet;
import servlet.enumerations.Session;

/**
 * Filtre limittant l'acces selon si l'user est connecté ou non
 *
 * @author Richard
 */
@WebFilter(filterName = "ConnectionFilter")
public class ConnectionFilter implements Filter {

	private static final List<String> URL_NOCONNECT = Arrays.asList(
		"/index.html",
		"/connexion",
		"/inscription"
	);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("connectionFilter");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		boolean isConnected = this.isConnected(session);

		String url = req.getServletPath();
		
		System.out.println("SERVLET: " + url);
		
		boolean isUrlNoconnect = URL_NOCONNECT.contains(url);

		System.err.println("User connecté : " + isConnected);

		if (isConnected) {
			//Si l'url est une no-connect, rediriger sur start[/configuration]
			//Sinon doFilter
			if (isUrlNoconnect) {
				//rediriger sur start[/configuration]
			} else {
				chain.doFilter(request, response);
			}
		} else {

			//Si l'url n'est pas une no-connect, rediriger sur start[/]
			//Sinon doFilter
			if (!isUrlNoconnect) {
				//rediriger sur start
				request.getRequestDispatcher(IndexServlet.URL).forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	private boolean isConnected(HttpSession session) {
		try {
			return ((UserEntity) session.getAttribute(Session.USER.tostring)).isConnected();
		} catch (NullPointerException ex) {
			return false;
		}
	}

	@Override
	public void destroy() {
	}

}
