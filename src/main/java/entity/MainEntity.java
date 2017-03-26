/*
 * 
 * 
 * 
 */
package entity;

import servlet.enumerations.Code;

/**
 * MainEntity.java
 *
 */
public class MainEntity extends Entity {

	private boolean success;
	private int code;
	private ContentEntity content;

	public MainEntity(boolean success, Code code) {
		this(success, code, null);
	}

	public MainEntity(boolean success, Code code, ContentEntity content) {
		this.success = success;
		this.code = code.code;
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
			super(true, Code.OK, content);
		}

	}

	public static class MainEntityError extends MainEntity {

		public MainEntityError(Code code) {
			this(code, null);
		}

		public MainEntityError(Code code, ContentEntity content) {
			super(false, code, content);
		}

	}

}
