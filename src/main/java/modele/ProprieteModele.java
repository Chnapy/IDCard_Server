/*
 * 
 * 
 * 
 */
package modele;

import bdd.Modele;
import static bdd.generated.tables.Domaine.DOMAINE;
import static bdd.generated.tables.Propriete.PROPRIETE;
import static bdd.generated.tables.Typechiffrage.TYPECHIFFRAGE;
import static bdd.generated.tables.Typeprop.TYPEPROP;
import static bdd.generated.tables.Valeur.VALEUR;
import static bdd.generated.tables.Visibilite.VISIBILITE;
import entity.ProprieteEntity;
import entity.ProprieteEntity.ValeurEntity;
import entity.ProprieteEntity.ValeurEntity.SiteEntity;
import java.util.List;
import org.jooq.Record;
import org.jooq.Result;

/**
 * ProprieteModele.java
 *
 */
public class ProprieteModele extends Modele {

	public List<ProprieteEntity> getAllProprietes(long id_user) throws Exception {

		return bdd((create) -> {

			Result<? extends Record> result = create.select(PROPRIETE.ID_PROPRIETE, PROPRIETE.NOM, TYPEPROP.ID_TYPEPROP, TYPEPROP.TYPEPROP_, PROPRIETE.MODIFIABLE, PROPRIETE.SUPPRIMABLE, PROPRIETE.TAILLEVALMIN, PROPRIETE.TAILLEVALMAX, PROPRIETE.NBRVALMIN, PROPRIETE.NBRVALMAX)
					.from(VALEUR).naturalJoin(PROPRIETE).naturalJoin(TYPEPROP)
					.where(VALEUR.ID_USER.eq(id_user))
					.fetch();

			List<ProprieteEntity> proprietes = result.map((r) -> {

				TypeValeurProp tvp = this.parseType(r.get(TYPEPROP.ID_TYPEPROP));

				Result<? extends Record> res_val;

				if (r.get(TYPEPROP.ID_TYPEPROP) == 5) {
					//MDP
					res_val = create.select(tvp.getValeur_val(), VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(tvp.getVal_()).naturalJoin(TYPECHIFFRAGE)
							.where(VALEUR.ID_USER.eq(id_user).and(VALEUR.ID_PROPRIETE.eq(r.get(PROPRIETE.ID_PROPRIETE))))
							.fetch();
				} else {
					res_val = create.select(tvp.getValeur_val(), VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(tvp.getVal_())
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

					return new ValeurEntity(v.get(VALEUR.ID_VALEUR), v.get(tvp.getValeur_val()), v.get(VALEUR.PRINCIPALE), publique, prive, sites);
				});

				ProprieteEntity pe = new ProprieteEntity(r.get(PROPRIETE.ID_PROPRIETE), r.get(PROPRIETE.NOM), r.get(TYPEPROP.TYPEPROP_), tvp.getType(), r.get(PROPRIETE.MODIFIABLE), r.get(PROPRIETE.SUPPRIMABLE), r.get(PROPRIETE.TAILLEVALMIN), r.get(PROPRIETE.TAILLEVALMAX), r.get(PROPRIETE.NBRVALMIN), r.get(PROPRIETE.NBRVALMAX), valeurs);

				return pe;
			});

			return proprietes;
		});

	}

	public static class TypePropNonGereError extends Error {

	}

}
