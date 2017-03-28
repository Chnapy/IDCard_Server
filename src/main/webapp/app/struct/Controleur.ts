
import {Modele} from 'Modele';
import {Vue} from 'Vue';
import {MainManager} from 'modules/main/MainManager';
import {AjaxEnum} from 'struct/AjaxCallback';

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

	public startAjax(nom: string) {
		this.mainManager.vue.setState({nomAjax: nom, etatAjax: AjaxEnum.Load});
	}

	public endAjax(etat: AjaxEnum) {
		this.mainManager.vue.setState({etatAjax: etat});
	}

	public popConfirm(titre: string, content: string, onConfirm: () => void, element: HTMLElement | EventTarget, fixed?: boolean) {
		this.mainManager.vue.setState({confirmBox: {info: false, srcElement: element, titre: titre, content: content, onConfirm: onConfirm, fixed: fixed}})
	}

	public popInfo(titre: string, content: string, element: HTMLElement | EventTarget, fixed?: boolean) {
		this.mainManager.vue.setState({confirmBox: {info: true, srcElement: element, titre: titre, content: content, onConfirm: () => {}, fixed: fixed}})
	}

	public popNonImplemente(element: HTMLElement | EventTarget, fixed?: boolean) {
		this.popInfo('Fonction non implémentée', 'Patience est mère de vertu', element, fixed);
	}

	public showAlertFromCode(code_num: number, message_debug?: string) {
		this.mainManager.showAlertFromCode(code_num, message_debug);
	}

}