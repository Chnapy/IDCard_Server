/*
 * 
 * 
 * 
 */
package entity;

import java.util.List;

/**
 * ProprieteEntity.java
 *
 * Entity possédant une propriété de la table PROPRIETE ainsi que ses valeurs
 *
 */
public class ProprieteEntity extends Entity {

	private long key;
	private String nom;
	private String typeStr;
	private String type;
	private boolean principal;
	private boolean modifiable;
	private boolean supprimable;
	private int taillemin;
	private int taillemax;
	private int nbrmin;
	private int nbrmax;
	private List<ValeurEntity> valeurs;

	/**
	 *
	 * @param key
	 * @param nom
	 * @param typeStr
	 * @param type
	 * @param principal
	 * @param modifiable
	 * @param supprimable
	 * @param taillemin
	 * @param taillemax
	 * @param nbrmin
	 * @param nbrmax
	 * @param valeurs
	 */
	public ProprieteEntity(long key, String nom, String typeStr, String type, boolean principal, boolean modifiable, boolean supprimable, int taillemin, int taillemax, int nbrmin, int nbrmax, List<ValeurEntity> valeurs) {
		this.key = key;
		this.nom = nom;
		this.typeStr = typeStr;
		this.type = type;
		this.principal = principal;
		this.modifiable = modifiable;
		this.supprimable = supprimable;
		this.taillemin = taillemin;
		this.taillemax = taillemax;
		this.nbrmin = nbrmin;
		this.nbrmax = nbrmax;
		this.valeurs = valeurs;
	}

	/**
	 *
	 * @return
	 */
	public long getKey() {
		return key;
	}

	/**
	 *
	 * @param key
	 */
	public void setKey(long key) {
		this.key = key;
	}

	/**
	 *
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 *
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 *
	 * @return
	 */
	public String getTypeStr() {
		return typeStr;
	}

	/**
	 *
	 * @param typeStr
	 */
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	/**
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 * @return
	 */
	public boolean isPrincipal() {
		return principal;
	}

	/**
	 *
	 * @param principal
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	/**
	 *
	 * @return
	 */
	public boolean isModifiable() {
		return modifiable;
	}

	/**
	 *
	 * @param modifiable
	 */
	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 *
	 * @return
	 */
	public boolean isSupprimable() {
		return supprimable;
	}

	/**
	 *
	 * @param supprimable
	 */
	public void setSupprimable(boolean supprimable) {
		this.supprimable = supprimable;
	}

	/**
	 *
	 * @return
	 */
	public int getTaillemin() {
		return taillemin;
	}

	/**
	 *
	 * @param taillemin
	 */
	public void setTaillemin(int taillemin) {
		this.taillemin = taillemin;
	}

	/**
	 *
	 * @return
	 */
	public int getTaillemax() {
		return taillemax;
	}

	/**
	 *
	 * @param taillemax
	 */
	public void setTaillemax(int taillemax) {
		this.taillemax = taillemax;
	}

	/**
	 *
	 * @return
	 */
	public int getNbrmin() {
		return nbrmin;
	}

	/**
	 *
	 * @param nbrmin
	 */
	public void setNbrmin(int nbrmin) {
		this.nbrmin = nbrmin;
	}

	/**
	 *
	 * @return
	 */
	public int getNbrmax() {
		return nbrmax;
	}

	/**
	 *
	 * @param nbrmax
	 */
	public void setNbrmax(int nbrmax) {
		this.nbrmax = nbrmax;
	}

	/**
	 *
	 * @return
	 */
	public List<ValeurEntity> getValeurs() {
		return valeurs;
	}

	/**
	 *
	 * @param valeurs
	 */
	public void setValeurs(List<ValeurEntity> valeurs) {
		this.valeurs = valeurs;
	}

	/**
	 * Entity possédant les données d'un valeur de la table VALEUR
	 *
	 * @param <T>
	 */
	public static class ValeurEntity<T> extends Entity {

		private long key;
		private T valeur;
		private boolean principal;
		private boolean publique;
		private boolean prive;
		private List<SiteEntity> sites;

		/**
		 *
		 * @param key
		 * @param valeur
		 * @param principal
		 * @param publique
		 * @param prive
		 * @param sites
		 */
		public ValeurEntity(long key, T valeur, boolean principal, boolean publique, boolean prive, List<SiteEntity> sites) {
			this.key = key;
			this.valeur = valeur;
			this.principal = principal;
			this.publique = publique;
			this.prive = prive;
			this.sites = sites;
		}

		/**
		 *
		 * @return
		 */
		public long getKey() {
			return key;
		}

		/**
		 *
		 * @param key
		 */
		public void setKey(long key) {
			this.key = key;
		}

		/**
		 *
		 * @return
		 */
		public T getValeur() {
			return valeur;
		}

		/**
		 *
		 * @param valeur
		 */
		public void setValeur(T valeur) {
			this.valeur = valeur;
		}

		/**
		 *
		 * @return
		 */
		public boolean isPrincipal() {
			return principal;
		}

		/**
		 *
		 * @param principal
		 */
		public void setPrincipal(boolean principal) {
			this.principal = principal;
		}

		/**
		 *
		 * @return
		 */
		public boolean isPublique() {
			return publique;
		}

		/**
		 *
		 * @param publique
		 */
		public void setPublique(boolean publique) {
			this.publique = publique;
		}

		/**
		 *
		 * @return
		 */
		public boolean isPrive() {
			return prive;
		}

		/**
		 *
		 * @param prive
		 */
		public void setPrive(boolean prive) {
			this.prive = prive;
		}

		/**
		 *
		 * @return
		 */
		public List<SiteEntity> getSites() {
			return sites;
		}

		/**
		 *
		 * @param sites
		 */
		public void setSites(List<SiteEntity> sites) {
			this.sites = sites;
		}

		/**
		 * Entity possédant les données d'un site de la table DOMAINE
		 *
		 */
		public static class SiteEntity extends Entity {

			private long key;
			private String site;

			/**
			 *
			 * @param key
			 * @param site
			 */
			public SiteEntity(long key, String site) {
				this.key = key;
				this.site = site;
			}

			/**
			 *
			 * @return
			 */
			public long getKey() {
				return key;
			}

			/**
			 *
			 * @param key
			 */
			public void setKey(long key) {
				this.key = key;
			}

			/**
			 *
			 * @return
			 */
			public String getSite() {
				return site;
			}

			/**
			 *
			 * @param site
			 */
			public void setSite(String site) {
				this.site = site;
			}
		}
	}
}
