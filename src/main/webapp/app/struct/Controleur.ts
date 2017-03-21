
import {Modele} from 'Modele';
import {Vue} from 'Vue';

export abstract class Controleur<M extends Modele, V extends Vue<any, any>> {

	private _modele: M;
	get modele(): M {
		return this._modele;
	}
	set modele(modele: M) {
		this._modele = modele;
	}

	private _vue: V;
	get vue(): V {
		return this._vue;
	}
	set vue(vue: V) {
		this._vue = vue;
	}

	public constructor(modele: M, vue?: V) {
		this.modele = modele;
		if (vue) {
			this.vue = vue;
		}
	}

}