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
 * Entité possédée par {@link MainEntity} et donc envoyée au client
 * Possède plusieurs données, non-nulle uniquement lorsque nécessaires
 */
public class ContentEntity extends Entity {

	/**
	 * Données utilisateur
	 */
	private UserEntity user;

	/**
	 * Liste des propriétés possédées par l'utilisateur
	 */
	private List<ProprieteEntity> proprietes;

	/**
	 * ID valeur, utilisé dans certains cas (ajout d'une valeur
	 * {@link servlet.configuration.addvaleur.AddValeurAction})
	 */
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
