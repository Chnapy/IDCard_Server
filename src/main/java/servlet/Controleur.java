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
import entity.UserEntity;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import modele.ProprieteModele;
import servlet.Controleur.Param.NoCheckException;

/**
 * Controleur.java
 *
 */
public abstract class Controleur extends HttpServlet {

	protected static final String PARAM_MODULE = "m";
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	protected UserEntity user;

	protected abstract MainEntity onPost(HttpServletRequest request, HttpServletResponse response);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = (UserEntity) request.getSession().getAttribute(Controleur.Sess.USER.sess);
		MainEntity me = this.onPost(request, response);
		this.sendReturn(me, response);
	}

	protected void sendReturn(MainEntity e, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {

			out.print(this.entityToJSONString(e));
		} catch (IOException ex) {
			Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	protected void checkIdUser(long id) throws Param.NoCheckException {
		if (this.user.getId_user() != id) {
			throw new Param.NoCheckException("Id_user différents: " + this.user.getId_user() + " != " + id);
		}
	}

	protected String entityToJSONString(Entity e) throws JsonProcessingException {
		return JSON_MAPPER.writeValueAsString(e);
	}

	protected Const.CheckData getCheckDataFromTypeProp(long id_typeprop, String nomProp) {

		Const.CheckData cd;

		switch (nomProp) {
			case "login":
				cd = Const.CD_PSEUDO;
				break;
			case "mdp":
				cd = Const.CD_MDP;
				break;
			default:
				switch ((int) id_typeprop) {
					case 1:
						cd = Const.CD_VALSTRING;
						break;
					case 2:
						cd = Const.CD_INTEGER;
						break;
					case 3:
						cd = Const.CD_BOOLEAN;
						break;
					case 4:
						cd = Const.CD_DATE;
						break;
					case 5:
						cd = Const.CD_VALSTRING;
						break;
					case 6:
						cd = Const.CD_MAIL;
						break;
					case 7:
						cd = Const.CD_LONG;
						break;
					case 8:
						cd = Const.CD_DOUBLE;
						break;
					default:
						throw new ProprieteModele.TypePropNonGereError();
				}
		}

		return cd;
	}

	public <T> T checkParam(Param param, ServletRequest req) throws NoCheckException {
		String value = req.getParameter(param.param);
		if (!Param.checkParam(param, value)) {
			throw new NoCheckException(param, value);
		}
		return param.getCastValue(value);
	}

	public <T> T check(String value, Const.CheckData cd, int tailleMin, int tailleMax) throws NoCheckException {
		if (!Param.check(cd, value)) {
			throw new NoCheckException(cd, value);
		}
		T val_final = Param.getCastValue(cd, value);
		
		if (val_final instanceof String) {
			String s = (String) val_final;
			if (s.length() >= tailleMin && s.length() <= tailleMax) {
				return val_final;
			}
		}
		if (val_final instanceof Boolean) {
			return val_final;
		}
		if (val_final instanceof Number) {
			Number n = (Number) val_final;
			if ((val_final instanceof Integer || val_final instanceof Long)
					&& (n.longValue() >= tailleMin && n.longValue() <= tailleMax)) {
				return val_final;
			}
			if (val_final instanceof Double
					&& (n.doubleValue() >= tailleMin && n.doubleValue() <= tailleMax)) {
				return val_final;
			}
		}
		if (val_final instanceof Date) {
			Date d = (Date) val_final;
			if (d.getTime() >= tailleMin && d.getTime() <= tailleMax) {
				return val_final;
			}
		}
		
		throw new Param.NoCheckException(cd, value);
	}

	public static enum Page {

		ACCUEIL("Accueil"), CONFIGURATION("Configuration");

		public final String page;

		private Page(String page) {
			this.page = page;
		}
	}

	//Variable de session
	public static enum Sess {

		USER("user");

		public final String sess;

		private Sess(String sess) {
			this.sess = sess;
		}
	}

	//Attributs de request
	public static enum Attr {

		IS_MAIL("isMail");

		public final String attr;

		private Attr(String attr) {
			this.attr = attr;
		}
	}

	//Parametres de request
	public static enum Param {

		LOGIN("pseudo", Const.CD_PSEUDO),
		MAIL("mail", Const.CD_MAIL),
		MDP("mdp", Const.CD_MDP),
		IS_MAIL("isMail", Const.CD_BOOLEAN),
		ID_VAL("id_val", Const.CD_LONG),
		ID_PROP("id_prop", Const.CD_LONG),
		ID_SITE("id_site", Const.CD_LONG),
		VAL("val", Const.CD_VALSTRING);

		public final String param;
		private final Const.CheckData cd;

		private Param(String param, Const.CheckData cd) {
			this.param = param;
			this.cd = cd;
		}

		public <T> T getCastValue(String value) {
			return getCastValue(cd, value);
		}

		//Recois la valeur du parametre lorsqu'il est encore en String
		public boolean check(String txt) {
			return check(cd, txt);
		}

		public static <T> T getCastValue(Const.CheckData cd, String value) {
			if (cd.classe.equals(String.class)) {
				return (T) value;
			}
			if (cd.classe.equals(Boolean.class)) {
				return (T) (Boolean) Boolean.parseBoolean(value);
			}
			if (cd.classe.equals(Integer.class)) {
				return (T) (Integer) Integer.parseInt(value);
			}
			if (cd.classe.equals(Long.class)) {
				return (T) (Long) Long.parseLong(value);
			}
			if (cd.classe.equals(Double.class)) {
				return (T) (Double) Double.parseDouble(value);
			}
			if (cd.classe.equals(Date.class)) {
				try {
					return (T) (Date) DateFormat.getDateInstance().parse(value);
				} catch (ParseException ex) {
					throw new Error("Cast date impossible: " + value);
				}
			}
			throw new Error("Classe non gérée: " + cd.classe);
		}

		public static boolean check(Const.CheckData cd, String txt) {
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
			if (cd.classe.equals(Long.class)) {
				try {
					Long.parseLong(txt);
					return true;
				} catch (NumberFormatException ex) {
					return false;
				}
			}
			if (cd.classe.equals(Double.class)) {
				try {
					Double.parseDouble(txt);
					return true;
				} catch (NumberFormatException ex) {
					return false;
				}
			}
			if (cd.classe.equals(Date.class)) {
				try {
					DateFormat.getDateInstance().parse(txt);
					return true;
				} catch (ParseException ex) {
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

			public NoCheckException(String value) {
				super("Check échoué: [" + value + "]");
			}

			public NoCheckException(Const.CheckData cd, String value) {
				super("Check échoué: " + cd + " [" + value + "]");
			}

			public NoCheckException(Param param, String value) {
				super("Check échoué: " + param.param + " [" + value + "]");
			}
		}
	}

}
