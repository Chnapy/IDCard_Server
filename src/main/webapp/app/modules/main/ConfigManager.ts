
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';
import {ConfigModele} from 'ConfigModele';
import {AjaxCallback, Data, Donnees} from 'struct/AjaxCallback';
import {Configuration} from 'pages/Configuration';
import {LigneValeur} from 'items/LigneValeur';
import {BlocPropriete, ValeurProp} from 'items/BlocPropriete';

export class ConfigManager extends Controleur<ConfigModele, Configuration> {

	public constructor(donnees: Donnees, mainManager: MainManager) {
		super(new ConfigModele(donnees), mainManager);
	}

	public updateValeur(key_val: number, val: string, onsuccess: () => void) {
		this.modele.updateValeur(key_val, val, new AjaxCallback(this, 'Mise à jour de valeur', {
			success: (data: Data) => onsuccess()
		}));
	}

	public addValeur(key_prop: number, val: string, blocProp: BlocPropriete) {
		this.modele.addValeur(key_prop, val, new AjaxCallback(this, 'Ajout de valeur', {
			success: (data: Data) => {
				let vp: ValeurProp = {
					key: data.content.id_val as number, valeur: val, sites: [], principal: false, prive: false, publique: false
				};
				this.modele.donnees.proprietes.filter(p => p.key == key_prop)[0].valeurs.push(vp);
				blocProp.setState({newval: false});
			}
		}));
	}

	public removeValeur(key_prop: number, key_val: number, blocProp: BlocPropriete, element: EventTarget) {

		function remover(manager: ConfigManager) {
			manager.modele.removeValeur(key_val, new AjaxCallback(manager, 'Suppression de valeur', {
				success: (data: Data) => {
					let valeurs_filtered = manager.modele.donnees.proprietes.filter(p => p.key == key_prop)[0].valeurs.filter(v => v.key !== key_val);
					manager.modele.donnees.proprietes.filter(p => p.key == key_prop)[0].valeurs = valeurs_filtered;
					blocProp.setState({valeurs: valeurs_filtered});
				}
			}));
		}

		this.popConfirm('Suppression d\'une valeur', 'Voulez-vous vraiment supprimer cette valeur ?', () => remover(this), element);
	}

	public removeSite(key_prop: number, key_val: number, key_site: number, ligne: LigneValeur, element: EventTarget) {

		function remover(manager: ConfigManager) {
			manager.modele.removeSite(key_val, key_site, new AjaxCallback(manager, 'Suppression de site', {
				success: (data: Data) => {
					let sites = manager.modele.donnees
						.proprietes.filter(p => p.key === key_prop)[0]
						.valeurs.filter(v => v.key === key_val)[0]
						.sites;
					let sites_filtered = sites.filter(s => s.key !== key_site);
					manager.modele.donnees
						.proprietes.filter(p => p.key === key_prop)[0]
						.valeurs.filter(v => v.key === key_val)[0]
						.sites = sites_filtered;
					ligne.setState({sites: sites_filtered})
				}
			}));
		}

		this.popConfirm('Suppression d\'un site', 'Voulez-vous vraiment supprimer ce site ?', () => remover(this), element);
	}

}