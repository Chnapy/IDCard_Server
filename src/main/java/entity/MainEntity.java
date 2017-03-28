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
 */
public class MainEntity extends Entity {

	private boolean success;
	private int code;
	private String debug;
	private ContentEntity content;

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

	public static class MainEntitySuccess extends MainEntity {

		public MainEntitySuccess() {
			this(null);
		}

		public MainEntitySuccess(ContentEntity content) {
			super(true, Code.OK, null, content);
		}

	}

}
