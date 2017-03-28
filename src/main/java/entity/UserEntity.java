/*
 * 
 * 
 * 
 */
package entity;

import java.sql.Date;

/**
 * UserEntity.java
 *
 * Données utilisateur majoritairement récupérées depuis la table USER_1
 *
 */
public class UserEntity extends Entity {

	/**
	 * Défini si l'utilisateur est connecté ou non
	 */
	private boolean connected;

	private long id_user;
	private String pseudo;
	private String mail;
	private Date dateinscription;
	private Date datederniereconnexion;
	private long nbrconnexion;

	/**
	 *
	 * @param connected
	 */
	public UserEntity(boolean connected) {
		this.connected = connected;
	}

	/**
	 *
	 * @param id_user
	 * @param pseudo
	 * @param mail
	 * @param dateinscription
	 * @param datederniereconnexion
	 * @param nbrconnexion
	 */
	public UserEntity(long id_user, String pseudo, String mail, Date dateinscription, Date datederniereconnexion, long nbrconnexion) {
		this(true);
		this.id_user = id_user;
		this.pseudo = pseudo;
		this.mail = mail;
		this.dateinscription = dateinscription;
		this.datederniereconnexion = datederniereconnexion;
		this.nbrconnexion = nbrconnexion;
	}

	/**
	 *
	 * @return
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 *
	 * @param connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 *
	 * @param id_user
	 */
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	/**
	 *
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 *
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 *
	 * @param dateinscription
	 */
	public void setDateinscription(Date dateinscription) {
		this.dateinscription = dateinscription;
	}

	/**
	 *
	 * @param datederniereconnexion
	 */
	public void setDatederniereconnexion(Date datederniereconnexion) {
		this.datederniereconnexion = datederniereconnexion;
	}

	/**
	 *
	 * @param nbrconnexion
	 */
	public void setNbrconnexion(long nbrconnexion) {
		this.nbrconnexion = nbrconnexion;
	}

	/**
	 *
	 * @return
	 */
	public long getId_user() {
		return id_user;
	}

	/**
	 *
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 *
	 * @return
	 */
	public String getMail() {
		return mail;
	}

	/**
	 *
	 * @return
	 */
	public Date getDateinscription() {
		return dateinscription;
	}

	/**
	 *
	 * @return
	 */
	public Date getDatederniereconnexion() {
		return datederniereconnexion;
	}

	/**
	 *
	 * @return
	 */
	public long getNbrconnexion() {
		return nbrconnexion;
	}

}
