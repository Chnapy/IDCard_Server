/*
 * 
 * 
 * 
 */
package servlet;

import entity.ContentEntity;
import entity.MainEntity;
import entity.UserEntity;
import exceptions.NoCheckException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.configuration.ConfigurationServlet;
import servlet.Controleur;
import enumerations.Code;
import enumerations.Param;

/**
 * ControleurAction.java
 *
 * @param <C>
 */
public abstract class ControleurAction<C extends Controleur> {

	private C controleur;
	private UserEntity user;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ContentEntity act(C controleur, UserEntity user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.controleur = controleur;
		this.user = user;
		this.request = request;
		this.response = response;

//		boolean success = false;
//		Code code;
//		ContentEntity content = null;
//		try {
//			content = this.doAct();
//			code = Code.OK;
//			success = true;
//		} catch (NoCheckException ex) {
//			code = Code.E_CONFIGURATION_UPDATE;
//		} catch (Exception ex) {
//			Logger.getLogger(ConfigurationServlet.class.getName()).log(Level.SEVERE, null, ex);
//			code = Code.E_SERVEUR;
//		}
		return this.doAct();
	}

	protected abstract ContentEntity doAct() throws Exception;

	protected <T> T getParam(Param param) throws NoCheckException {
		return controleur.checkParam(param, request);
	}

	protected void checkIdUser(long id_user) throws NoCheckException {
		controleur.checkIdUser(id_user);
	}

	protected C getControleur() {
		return controleur;
	}

	protected UserEntity getUser() {
		return user;
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

}
