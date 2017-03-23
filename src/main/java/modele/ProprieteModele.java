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
import static bdd.generated.tables.Valeurbigint.VALEURBIGINT;
import static bdd.generated.tables.Valeurboolean.VALEURBOOLEAN;
import static bdd.generated.tables.Valeurdate.VALEURDATE;
import static bdd.generated.tables.Valeurdouble.VALEURDOUBLE;
import static bdd.generated.tables.Valeurinteger.VALEURINTEGER;
import static bdd.generated.tables.Valeurmdp.VALEURMDP;
import static bdd.generated.tables.Valeurstring.VALEURSTRING;
import static bdd.generated.tables.Visibilite.VISIBILITE;
import entity.ProprieteEntity;
import entity.ProprieteEntity.ValeurEntity;
import java.util.List;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;

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

				TableField valeur_val;
				TableImpl val_;

				switch (r.get(TYPEPROP.ID_TYPEPROP)) {
					case 1:
					case 6:
						valeur_val = VALEURSTRING.VALEUR;
						val_ = VALEURSTRING;
						break;
					case 2:
						valeur_val = VALEURINTEGER.VALEUR;
						val_ = VALEURINTEGER;
						break;
					case 3:
						valeur_val = VALEURBOOLEAN.VALEUR;
						val_ = VALEURBOOLEAN;
						break;
					case 4:
						valeur_val = VALEURDATE.VALEUR;
						val_ = VALEURDATE;
						break;
					case 5:
						valeur_val = TYPECHIFFRAGE.TYPECHIFFRAGE_;
						val_ = VALEURMDP;
						break;
					case 7:
						valeur_val = VALEURBIGINT.VALEUR;
						val_ = VALEURBIGINT;
						break;
					case 8:
						valeur_val = VALEURDOUBLE.VALEUR;
						val_ = VALEURDOUBLE;
						break;
					default:
						throw new TypePropNonGereError();
				}

				Result<? extends Record> res_val;

				if (r.get(TYPEPROP.ID_TYPEPROP) == 5) {
					//MDP
					res_val = create.select(valeur_val, VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(val_).naturalJoin(TYPECHIFFRAGE)
							.where(VALEUR.ID_USER.eq(id_user).and(VALEUR.ID_PROPRIETE.eq(r.get(PROPRIETE.ID_PROPRIETE))))
							.fetch();
				} else {
					res_val = create.select(valeur_val, VALEUR.ID_VALEUR, VALEUR.PRINCIPALE)
							.from(VALEUR).naturalJoin(val_)
							.where(VALEUR.ID_USER.eq(id_user).and(VALEUR.ID_PROPRIETE.eq(r.get(PROPRIETE.ID_PROPRIETE))))
							.fetch();
				}

				List<ValeurEntity> valeurs = res_val.map((v) -> {

					Result<? extends Record> res_sites = create.select(DOMAINE.IP)
							.from(VISIBILITE).naturalJoin(DOMAINE)
							.where(VISIBILITE.ID_VALEUR.eq(v.get(VALEUR.ID_VALEUR)))
							.fetch();

					List<String> sites = res_sites.map(s -> s.get(DOMAINE.IP));

					boolean prive = sites.isEmpty();
					boolean publique = !prive && sites.contains("*");

					return new ValeurEntity(v.get(VALEUR.ID_VALEUR), v.get(valeur_val), v.get(VALEUR.PRINCIPALE), publique, prive, sites);
				});

				ProprieteEntity pe = new ProprieteEntity(r.get(PROPRIETE.ID_PROPRIETE), r.get(PROPRIETE.NOM), r.get(TYPEPROP.TYPEPROP_), r.get(TYPEPROP.TYPEPROP_), r.get(PROPRIETE.MODIFIABLE), r.get(PROPRIETE.SUPPRIMABLE), r.get(PROPRIETE.TAILLEVALMIN), r.get(PROPRIETE.TAILLEVALMAX), r.get(PROPRIETE.NBRVALMIN), r.get(PROPRIETE.NBRVALMAX), valeurs);

				return pe;
			});

			return proprietes;
		});

	}

	public static class TypePropNonGereError extends Error {

	}

}
