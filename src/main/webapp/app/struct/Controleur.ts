
import {Modele} from 'Modele';
import {Vue} from 'Vue';

export abstract class Controleur {

	private _modele: Modele;
	get modele(): Modele {
		return this._modele;
	}
	set modele(modele: Modele) {
		this._modele = modele;
	}

	private _vue: Vue;
	get vue(): Vue {
		return this._vue;
	}
	set vue(vue: Vue) {
		this._vue = vue;
	}

	public constructor(modele: Modele, vue: Vue) {
		this.modele = modele;
		this.vue = vue;
	}

}