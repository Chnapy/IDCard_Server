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
import servlet.IndexServlet;

/**
 * Filtre limittant l'accès selon si la requête est faites via de l'Ajax ou non
 * Si la requête n'est pas faite en Ajax, on redirige sur l'index
 * {@link IndexServlet}.
 *
 * @author Richard
 */
@WebFilter(filterName = "AjaxFilter")
public class AjaxFilter implements Filter {

	/**
	 *
	 * @param filterConfig
	 * @throws ServletException
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("ajaxFilter");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");

		boolean isAjax = isAjax(req);

		System.out.println("Requete Ajax : " + isAjax);

		if (isAjax) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher(IndexServlet.URL).forward(request, response);
		}
	}

	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
	}

	/**
	 *
	 */
	@Override
	public void destroy() {
	}

}
