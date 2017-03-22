/*
 * 
 * 
 * 
 */
package entity;

/**
 * ContentEntity.java
 * 
 */
public class ContentEntity extends Entity {
	
	private final boolean connected;
	private final UserEntity user;

	public ContentEntity(boolean connected, UserEntity user) {
		this.connected = connected;
		this.user = user;
	}

	public boolean isConnected() {
		return connected;
	}

	public UserEntity getUser() {
		return user;
	}

}
