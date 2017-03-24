

import {Modele} from 'struct/Modele';
import {AjaxCallback, Donnees} from 'struct/AjaxCallback';


export class ConfigModele extends Modele {

	public constructor(donnees: Donnees) {
		super(donnees);
	}

	public updateValeur(key: number, val: string, ajaxc: AjaxCallback): void {
		this.ajaxPost('configuration', {m: 'update_val', id_val: key, val: val}, ajaxc);
	}

}