/*
 * 
 * 
 * 
 */
package servlet.gestionacces.connexion;

import bdd.Modele;
import static bdd.generated.tables.Domaine.DOMAINE;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typechiffrage.TYPECHIFFRAGE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Visibilite.VISIBILITE;
import bdd.generated.tables.records.ProprieteRecord;
import bdd.generated.tables.records.ValeurRecord;
import entity.ProprieteEntity;
import entity.ProprieteEntity.ValeurEntity;
import entity.ProprieteEntity.ValeurEntity.SiteEntity;
import enumerations.TypeProp;
import java.util.List;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;

/**
 * ProprieteModele.java
 *
 */
public class ProprieteModele extends Modele {

	/**
	 * Retourne le {@link ProprieteRecord} (contenu) de la propriété recherchée.
	 *
	 * @param id_propriete ID de la propriété recherchée
	 * @return Contenu de la propriété recherchée.
	 * @throws Exception
	 */
	public ProprieteRecord getPropriete(long id_propriete) throws Exception {

		return bdd((create) -> {

			return create.selectFrom(PROPRIETE)
					.where(PROPRIETE.ID_PROPRIETE.eq(id_propriete))
					.fetchAny();
		});
	}

	/**
	 * Retourne le {@link ValeurRecord} (contenu) de la valeur recherchée.
	 *
	 * @param id_valeur
	 * @return Contenu de la valeur recherchée.
	 * @throws Exception
	 */
	public ValeurRecord getValeur(long id_valeur) throws Exception {

		return bdd((create) -> {

			return create.selectFrom(VALEUR)
					.where(VALEUR.ID_VALEUR.eq(id_valeur))
					.fetchAny();
		});
	}

	/**
	 * Retourne le nombre de valeurs possédées par la propriété spécifiée
	 *
	 * @param id_propriete ID de la propriété concernée
	 * @return Nombre de valeurs
	 * @throws Exception
	 */
	public int getNbrValFromProp(long id_propriete) throws Exception {

		return bdd((create) -> {

			return create.selectCount()
					.from(VALEUR)
					.where(VALEUR.ID_PROPRIETE.eq(id_propriete))
					.fetchAny().value1();
		});
	}

	/**
	 * Retourne toutes les propriétés dont l'utilisateur spécifié possède au
	 * moins une valeur. Ainsi que les valeurs liées, et leurs domaines.
	 *
	 * @param id_user ID utilisateur concerné
	 * @return List des {@link ProprieteEntity} contenant les données des
	 *         propriétés, ainsi que leurs valeurs
	 * @throws Exception
	 */
	public List<ProprieteEntity> getAllProprietes(long id_user) throws Exception {

		return bdd((create) -> {

			Result<? extends Record> result = create.selectDistinct(PROPRIETE.ID_PROPRIETE, PROPRIETE.NOM,
					TYPEPROP.ID_TYPEPROP, TYPEPROP.TYPEPROP_,
					PROPRIETE.PRINCIPAL, PROPRIETE.MODIFIABLE, PROPRIETE.SUPPRIMABLE,
					PROPRIETE.TAILLEVALMIN, PROPRIETE.TAILLEVALMAX,
					PROPRIETE.NBRVALMIN, PROPRIETE.NBRVALMAX)
					.from(VALEUR).naturalJoin(PROPRIETE).naturalJoin(TYPEPROP)
					.where(VALEUR.ID_USER.eq(id_user))
					.fetch();

			List<ProprieteEntity> proprietes = result.map((r) -> {

				TypeProp tp = TypeProp.getTypeProp(r.get(TYPEPROP.ID_TYPEPROP));

				Field valeur;

				Result<? extends Record> res_val;

				if (r.get(TYPEPROP.ID_TYPEPROP) == 5) {
					//MDP
					valeur = TYPECHIFFRAGE.TYPECHIFFRAGE_;
					res_val = create.select(valeur, VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(tp.getTable_ValeurTypee()).naturalJoin(TYPECHIFFRAGE)
							.where(VALEUR.ID_USER.eq(id_user).and(VALEUR.ID_PROPRIETE.eq(r.get(PROPRIETE.ID_PROPRIETE))))
							.fetch();
				} else {
					valeur = tp.getField_ValeurTypee_valeur();
					res_val = create.select(valeur, VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(tp.getTable_ValeurTypee())
							.where(VALEUR.ID_USER.eq(id_user).and(VALEUR.ID_PROPRIETE.eq(r.get(PROPRIETE.ID_PROPRIETE))))
							.fetch();
				}

				List<ValeurEntity> valeurs = res_val.map((v) -> {

					Result<? extends Record> res_sites = create.select(DOMAINE.ID_DOMAINE, DOMAINE.IP)
							.from(VISIBILITE).naturalJoin(DOMAINE)
							.where(VISIBILITE.ID_VALEUR.eq(v.get(VALEUR.ID_VALEUR)))
							.fetch();

					List<SiteEntity> sites = res_sites.map(s -> new SiteEntity(s.get(DOMAINE.ID_DOMAINE), s.get(DOMAINE.IP)));

					boolean prive = sites.isEmpty();
					boolean publique = !sites.isEmpty() && "*".equals(sites.get(0).getSite());

					return new ValeurEntity(v.get(VALEUR.ID_VALEUR), v.get(valeur), v.get(VALEUR.PRINCIPALE), publique, prive, sites);
				});

				ProprieteEntity pe = new ProprieteEntity(
						r.get(PROPRIETE.ID_PROPRIETE),
						r.get(PROPRIETE.NOM),
						r.get(TYPEPROP.TYPEPROP_),
						tp.getInputType(),
						r.get(PROPRIETE.PRINCIPAL),
						r.get(PROPRIETE.MODIFIABLE),
						r.get(PROPRIETE.SUPPRIMABLE),
						r.get(PROPRIETE.TAILLEVALMIN),
						r.get(PROPRIETE.TAILLEVALMAX),
						r.get(PROPRIETE.NBRVALMIN),
						r.get(PROPRIETE.NBRVALMAX),
						valeurs);

				return pe;
			});

			return proprietes;
		});

	}

}
