/*
 * 
 * 
 * 
 */
package entity;

import java.util.Collections;
import java.util.List;

/**
 * ContentEntity.java
 *
 */
public class ContentEntity extends Entity {

	private UserEntity user;
	private List<ProprieteEntity> proprietes;

	public ContentEntity() {
		this(new UserEntity(false));
	}

	public ContentEntity(UserEntity user) {
		this(user, Collections.EMPTY_LIST);
	}

	public ContentEntity(UserEntity user, List<ProprieteEntity> proprietes) {
		this.user = user;
		this.proprietes = proprietes;
	}

	public List<ProprieteEntity> getProprietes() {
		return proprietes;
	}

	public void setProprietes(List<ProprieteEntity> proprietes) {
		this.proprietes = proprietes;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
