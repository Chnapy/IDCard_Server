
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';
import {ConfigModele} from 'ConfigModele';
import {AjaxCallback, Data, Donnees} from 'struct/AjaxCallback';
import {Configuration} from 'pages/Configuration';

export class ConfigManager extends Controleur<ConfigModele, Configuration> {

	public constructor(donnees: Donnees, mainManager: MainManager) {
		super(new ConfigModele(donnees), mainManager);
	}
	
	public updateValeur(key: number, val: string) {
		this.modele.updateValeur(key, val, new AjaxCallback(this, {
			success: (data: Data) => {
//				console.debug(data);
			}
		}));
	}

}