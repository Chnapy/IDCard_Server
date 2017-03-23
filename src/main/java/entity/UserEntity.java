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
 */
public class UserEntity extends Entity {

	private boolean connected;
	private long id_user;
	private String pseudo;
	private String mail;
	private Date dateinscription;
	private Date datederniereconnexion;
	private long nbrconnexion;

	public UserEntity(boolean connected) {
		this.connected = connected;
	}

	public UserEntity(long id_user, String pseudo, String mail, Date dateinscription, Date datederniereconnexion, long nbrconnexion) {
		this(true);
		this.id_user = id_user;
		this.pseudo = pseudo;
		this.mail = mail;
		this.dateinscription = dateinscription;
		this.datederniereconnexion = datederniereconnexion;
		this.nbrconnexion = nbrconnexion;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setDateinscription(Date dateinscription) {
		this.dateinscription = dateinscription;
	}

	public void setDatederniereconnexion(Date datederniereconnexion) {
		this.datederniereconnexion = datederniereconnexion;
	}

	public void setNbrconnexion(long nbrconnexion) {
		this.nbrconnexion = nbrconnexion;
	}

	public long getId_user() {
		return id_user;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getMail() {
		return mail;
	}

	public Date getDateinscription() {
		return dateinscription;
	}

	public Date getDatederniereconnexion() {
		return datederniereconnexion;
	}

	public long getNbrconnexion() {
		return nbrconnexion;
	}

}
