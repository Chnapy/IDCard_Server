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

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	public boolean isSupprimable() {
		return supprimable;
	}

	public void setSupprimable(boolean supprimable) {
		this.supprimable = supprimable;
	}

	public int getTaillemin() {
		return taillemin;
	}

	public void setTaillemin(int taillemin) {
		this.taillemin = taillemin;
	}

	public int getTaillemax() {
		return taillemax;
	}

	public void setTaillemax(int taillemax) {
		this.taillemax = taillemax;
	}

	public int getNbrmin() {
		return nbrmin;
	}

	public void setNbrmin(int nbrmin) {
		this.nbrmin = nbrmin;
	}

	public int getNbrmax() {
		return nbrmax;
	}

	public void setNbrmax(int nbrmax) {
		this.nbrmax = nbrmax;
	}

	public List<ValeurEntity> getValeurs() {
		return valeurs;
	}

	public void setValeurs(List<ValeurEntity> valeurs) {
		this.valeurs = valeurs;
	}

	public static class ValeurEntity<T> extends Entity {

		private long key;
		private T valeur;
		private boolean principal;
		private boolean publique;
		private boolean prive;
		private List<SiteEntity> sites;

		public ValeurEntity(long key, T valeur, boolean principal, boolean publique, boolean prive, List<SiteEntity> sites) {
			this.key = key;
			this.valeur = valeur;
			this.principal = principal;
			this.publique = publique;
			this.prive = prive;
			this.sites = sites;
		}

		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		public T getValeur() {
			return valeur;
		}

		public void setValeur(T valeur) {
			this.valeur = valeur;
		}

		public boolean isPrincipal() {
			return principal;
		}

		public void setPrincipal(boolean principal) {
			this.principal = principal;
		}

		public boolean isPublique() {
			return publique;
		}

		public void setPublique(boolean publique) {
			this.publique = publique;
		}

		public boolean isPrive() {
			return prive;
		}

		public void setPrive(boolean prive) {
			this.prive = prive;
		}

		public List<SiteEntity> getSites() {
			return sites;
		}

		public void setSites(List<SiteEntity> sites) {
			this.sites = sites;
		}

		public static class SiteEntity extends Entity {

			private long key;
			private String site;

			public SiteEntity(long key, String site) {
				this.key = key;
				this.site = site;
			}

			public long getKey() {
				return key;
			}

			public void setKey(long key) {
				this.key = key;
			}

			public String getSite() {
				return site;
			}

			public void setSite(String site) {
				this.site = site;
			}
		}
	}
}
