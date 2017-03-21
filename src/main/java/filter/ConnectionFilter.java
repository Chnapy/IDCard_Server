/*
 * 
 * 
 * 
 */
package filter;

import java.io.IOException;
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

/**
 * Filtre limittant l'acces selon si l'user est connecté ou non
 *
 * @author Richard
 */
@WebFilter(filterName = "ConnectionFilter")
public class ConnectionFilter implements Filter {

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
		
		System.out.println("User connecté : " + isConnected);

//		if (isConnected) {
			chain.doFilter(request, response);
//		} else {
//			request.getRequestDispatcher(IndexServlet.URL).forward(request, response);
//		}
	}
	
	private boolean isConnected(HttpSession session) {
		try {
			return (boolean) session.getAttribute("isConnected");
		} catch (NullPointerException ex) {
			return false;
		}
	}

	@Override
	public void destroy() {
	}

}
