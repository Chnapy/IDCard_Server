/*
 * 
 * 
 * 
 */
package entity;

import java.util.List;

/**
 * ContentEntity.java
 *
 */
public class ContentEntity extends Entity {

	private UserEntity user;
	private List<ProprieteEntity> proprietes;
	private long id_val;

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

	public long getId_val() {
		return id_val;
	}

	public void setId_val(long id_val) {
		this.id_val = id_val;
	}

}
