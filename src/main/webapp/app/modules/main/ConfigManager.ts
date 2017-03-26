
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';
import {ConfigModele} from 'ConfigModele';
import {AjaxCallback, Data, Donnees} from 'struct/AjaxCallback';
import {Configuration} from 'pages/Configuration';
import {LigneValeur} from 'items/LigneValeur';

export class ConfigManager extends Controleur<ConfigModele, Configuration> {

	public constructor(donnees: Donnees, mainManager: MainManager) {
		super(new ConfigModele(donnees), mainManager);
	}

	public updateValeur(key: number, val: string, onsuccess: () => void) {
		this.modele.updateValeur(key, val, new AjaxCallback(this, 'Mise à jour de valeur', {
			success: (data: Data) => onsuccess()
		}));
	}

	public addValeur(keyProp: number, val: string, onsuccess: () => void) {
		this.modele.addValeur(keyProp, val, new AjaxCallback(this, 'Ajout de valeur', {
			success: (data: Data) => onsuccess()
		}));
		console.log(keyProp + ' ' + val);
	}

	public removeSite(key_prop: number, key_val: number, key_site: number, ligne: LigneValeur, element: HTMLElement) {

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

		this.askConfirm('Suppression d\'un site', 'Voulez-vous vraiment supprimer ce site ?', () => remover(this), element);
	}

}