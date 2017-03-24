
import {Modele} from 'Modele';
import {Vue} from 'Vue';
import {MainManager} from 'modules/main/MainManager';
import {Page} from 'pages/Page';

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

	private _mainManager: MainManager;
	get mainManager(): MainManager {
		return this._mainManager;
	}
	set mainManager(mainManager: MainManager) {
		this._mainManager = mainManager;
	}

	public constructor(modele: M, mainManager?: MainManager) {
		this.modele = modele;
		this.mainManager = mainManager as MainManager;
	}

	public showAlertFromCode(code_num: number) {
		this.mainManager.showAlertFromCode(code_num);
	}

}