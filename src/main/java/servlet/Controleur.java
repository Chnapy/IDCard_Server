/*
 * 
 * 
 * 
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bdd.Const;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Entity;
import entity.MainEntity;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import servlet.Controleur.Param.NoCheckException;

/**
 * Controleur.java
 *
 */
public abstract class Controleur extends HttpServlet {

	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	protected abstract MainEntity onPost(HttpServletRequest request, HttpServletResponse response);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainEntity me = this.onPost(request, response);
		this.sendReturn(me, response);
	}

	protected void sendReturn(MainEntity e, HttpServletResponse response) {
		try (PrintWriter out = response.getWriter()) {
			out.print(this.entityToJSONString(e));
		} catch (IOException ex) {
			Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected String entityToJSONString(Entity e) throws JsonProcessingException {
		return JSON_MAPPER.writeValueAsString(e);
	}

	public <T> T checkParam(Param param, ServletRequest req) throws NoCheckException {
		String value = req.getParameter(param.param);
		if (!Param.checkParam(param, value)) {
			throw new NoCheckException(param, value);
		}
		return param.getCastValue(value);
	}

	public static enum Page {

		ACCUEIL("Accueil"), CONFIGURATION("Configuration");

		public final String page;

		private Page(String page) {
			this.page = page;
		}
	}

	public static enum Sess {

		USER("user");

		public final String sess;

		private Sess(String sess) {
			this.sess = sess;
		}
	}

	public static enum Attr {

		IS_MAIL("isMail");

		public final String attr;

		private Attr(String attr) {
			this.attr = attr;
		}
	}

	public static enum Param {

		LOGIN("pseudo", Const.CD_PSEUDO),
		MAIL("mail", Const.CD_MAIL),
		MDP("mdp", Const.CD_MDP),
		IS_MAIL("isMail", Const.CD_BOOLEAN);

		public final String param;
		private final Const.CheckData cd;

		private Param(String param, Const.CheckData cd) {
			this.param = param;
			this.cd = cd;
		}

		public <T> T getCastValue(String value) {
			if (cd.classe.equals(String.class)) {
				return (T) value;
			}
			if (cd.classe.equals(Boolean.class)) {
				return (T) (Boolean) Boolean.parseBoolean(value);
			}
			if (cd.classe.equals(Integer.class)) {
				return (T) (Integer) Integer.parseInt(value);
			}
			throw new Error("Classe non gérée: " + cd.classe);
		}

		//Recois la valeur du parametre lorsqu'il est encore en String
		public boolean check(String txt) {
			if (cd.classe.equals(String.class)) {
				return txt.length() >= cd.minLength && txt.length() <= cd.maxLength
						&& (cd.patternRequired == null || txt.matches(cd.patternRequired))
						&& (cd.patternNotAllowed == null || !txt.matches(cd.patternNotAllowed));
			}
			if (cd.classe.equals(Boolean.class)) {
				return "true".equals(txt.toLowerCase()) || "false".equals(txt.toLowerCase());
			}
			if (cd.classe.equals(Integer.class)) {
				try {
					Integer.parseInt(txt);
					return true;
				} catch (NumberFormatException ex) {
					return false;
				}
			}
			throw new Error("Classe non gérée: " + cd.classe);
		}

		public static boolean checkParam(Param param, String value) {
			for (Param p : Param.values()) {
				if (p.equals(param)) {
					return p.check(value);
				}
			}
			throw new IllegalArgumentException("Param non présent: " + param + " [" + value + "]");
		}

		public static class NoCheckException extends Exception {

			public NoCheckException(Param param, String value) {
				super("Check échoué: " + param.param + " [" + value + "]");
			}
		}
	}

}
