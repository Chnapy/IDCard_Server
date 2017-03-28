/*
 * 
 * 
 * 
 */
package entity;

import bdd.Const;
import enumerations.Code;

/**
 * MainEntity.java
 *
 * Entity principale, envoyée au client à chacune de ses requêtes Ajax.
 *
 */
public class MainEntity extends Entity {

	/**
	 * Défini si la requête est un succes (s'il n'y a pas eu d'Exception)
	 */
	private boolean success;

	/**
	 * Code l'Exception, ou 0 en cas de succes
	 */
	private int code;

	/**
	 * Message de debug {@link Exception#printStackTrace()}, null si succes ou
	 * si !{@link Const#DEBUG}
	 */
	private String debug;

	/**
	 * Données envoyées, peuvent être nulles si non nécessaires
	 */
	private ContentEntity content;

	/**
	 * {@link #MainEntity(boolean, enumerations.Code, java.lang.String, null) }
	 */
	public MainEntity(boolean success, Code code, String debug) {
		this(success, code, debug, null);
	}

	public MainEntity(boolean success, Code code, String debug, ContentEntity content) {
		this.success = success;
		this.code = code.code;
		this.debug = Const.DEBUG ? debug : null;
		this.content = content;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public ContentEntity getContent() {
		return content;
	}

	public void setContent(ContentEntity content) {
		this.content = content;
	}

	/**
	 * {@link MainEntity} en cas de succès
	 */
	public static class MainEntitySuccess extends MainEntity {

		/**
		 * {@link MainEntitySuccess#MainEntitySuccess(null) }
		 */
		public MainEntitySuccess() {
			this(null);
		}

		/**
		 * {@link MainEntity#MainEntity(true, enumerations.Code.OK, null, entity.ContentEntity)
		 * }
		 */
		public MainEntitySuccess(ContentEntity content) {
			super(true, Code.OK, null, content);
		}

	}

}
