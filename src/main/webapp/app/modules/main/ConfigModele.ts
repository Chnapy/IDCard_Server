

import {Modele} from 'struct/Modele';
import {AjaxCallback, Donnees} from 'struct/AjaxCallback';


export class ConfigModele extends Modele {

	public constructor(donnees: Donnees) {
		super(donnees);
	}

	public updateValeur(key: number, val: string, ajaxc: AjaxCallback): void {
		this.ajaxPost('configuration', {m: 'update_val', id_val: key, val: val}, ajaxc);
	}

	public addValeur(keyProp: number, val: string, ajaxc: AjaxCallback): void {
		this.ajaxPost('configuration', {m: 'add_val', id_prop: keyProp, val: val}, ajaxc);
	}

	public removeSite(key_val: number, key_site: number, ajaxc: AjaxCallback): void {
		this.ajaxPost('configuration', {m: 'remove_site', id_val: key_val, id_site: key_site}, ajaxc);
	}

}