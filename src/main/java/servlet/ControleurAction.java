/*
 * 
 * 
 * 
 */
package servlet;

import entity.ContentEntity;
import entity.UserEntity;
import exceptions.NoCheckException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import enumerations.Param;

/**
 * ControleurAction.java
 *
 * Un ControleurAction est appelé par un {@link Controleur} via la généricité
 * ainsi que l'annotation qui est requise:
 *
 * @ModuleAction(servlet = ClassDuControleur.class, module = "nom_du_module")
 *
 * @param <C> {@link Controleur} (servlet) lié à l'action
 */
public abstract class ControleurAction<C extends Controleur> {

	private C controleur;
	private UserEntity user;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * Est appelé par le {@link Controleur}.
	 *
	 * @param controleur Le {@link Controleur} (servlet) lié à l'action
	 * @param user       Données user contenues dans la session
	 * @param request
	 * @param response
	 * @return Les données envoyées au client
	 * @throws Exception
	 */
	public ContentEntity act(C controleur, UserEntity user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.controleur = controleur;
		this.user = user;
		this.request = request;
		this.response = response;

		return this.doAct();
	}

	/**
	 * Contenu de l'action.
	 *
	 * @return Les données envoyées au client
	 * @throws Exception
	 */
	protected abstract ContentEntity doAct() throws Exception;

	/**
	 * {@link Controleur#checkParam(enumerations.Param, javax.servlet.ServletRequest)}
	 *
	 * @param <T>
	 * @param param
	 * @return
	 * @throws NoCheckException
	 */
	protected <T> T getParam(Param param) throws NoCheckException {
		return controleur.checkParam(param, request);
	}

	/**
	 * {@link Controleur#checkIdUser(long)}
	 *
	 * @param id_user
	 * @throws NoCheckException
	 */
	protected void checkIdUser(long id_user) throws NoCheckException {
		controleur.checkIdUser(id_user);
	}

	/**
	 *
	 * @return
	 */
	protected C getControleur() {
		return controleur;
	}

	/**
	 *
	 * @return
	 */
	protected UserEntity getUser() {
		return user;
	}

	/**
	 *
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}

	/**
	 *
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return response;
	}

}
