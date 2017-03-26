/*
 * 
 * 
 * 
 */
package servlet;

import bdd.CheckData;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import modele.ProprieteModele;
import servlet.enumerations.Param;
import servlet.enumerations.Session;

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
		this.user = (UserEntity) request.getSession().getAttribute(Session.USER.tostring);
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
	
	protected void checkIdUser(long id) throws NoCheckException {
		if (this.user.getId_user() != id) {
			throw new NoCheckException("Id_user différents: " + this.user.getId_user() + " != " + id);
		}
	}

	protected String entityToJSONString(Entity e) throws JsonProcessingException {
		return JSON_MAPPER.writeValueAsString(e);
	}

	protected CheckData getCheckDataFromTypeProp(long id_typeprop, String nomProp) {

		CheckData cd;

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
		String value = req.getParameter(param.tostring);
		if (!Param.checkParam(param, value)) {
			throw new NoCheckException(param, value);
		}
		return param.getCastValue(value);
	}

	public <T> T check(String value, CheckData cd, int tailleMin, int tailleMax) throws NoCheckException {
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
		
		throw new NoCheckException(cd, value);
	}

	public static class NoCheckException extends Exception {

		public NoCheckException(String value) {
			super("Check échoué: [" + value + "]");
		}

		public NoCheckException(CheckData cd, String value) {
			super("Check échoué: " + cd + " [" + value + "]");
		}

		public NoCheckException(Param param, String value) {
			super("Check échoué: " + param.tostring + " [" + value + "]");
		}
	}

}
