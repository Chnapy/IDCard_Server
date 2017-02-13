/*
 * 
 * 
 * 
 */
package filter;

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

/**
 * Filtre permettant aux ressources (css, js, etc) de passer les autres filtres.
 *
 * @author Richard
 */
@WebFilter(filterName = "RessourceFilter")
public class RessourceFilter implements Filter {

	private static final List<String> RESSOURCE_DIR = Arrays.asList(
			"css",
			"js",
			"libs",
			"img"
	);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("ressourceFilter");

		HttpServletRequest req = (HttpServletRequest) request;

		System.out.println("Requete URI : " + req.getRequestURI());

		if (isRessource(req)) {
			request.getRequestDispatcher(req.getServletPath()).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isRessource(HttpServletRequest req) {
		try {
			String dir = req.getRequestURI().split("/")[2];
			return RESSOURCE_DIR.contains(dir);
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}

	@Override
	public void destroy() {
	}

}
