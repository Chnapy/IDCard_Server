/* 
 * 
 * 
 * 
 */

import {Modele} from 'struct/Modele';
import {AjaxCallback} from 'struct/AjaxCallback';

/**
 * MainModele
 * 
 */
export class MainModele extends Modele {

	public isConnected(): boolean {
		return GLOBALS.user.connected;
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, ajaxc: AjaxCallback): void {
		$.post('connexion', {pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail}, (data) => ajaxc.onSuccess(data))
			.fail(() => ajaxc.onFail())
			.done((data) => ajaxc.onDone(data))
			.always((data) => ajaxc.onAlways(data));
	}

	public deconnexion(ajaxc: AjaxCallback): void {
		$.post('deconnexion', {}, (data) => ajaxc.onSuccess(data))
			.fail(() => ajaxc.onFail())
			.done((data) => ajaxc.onDone(data))
			.always((data) => ajaxc.onAlways(data));
	}

	public inscription(pseudo: string, mail: string, mdp: string, ajaxc: AjaxCallback): void {
		$.post('inscription', {pseudo: pseudo, mail: mail, mdp: mdp}, (data) => ajaxc.onSuccess(data))
			.fail(() => ajaxc.onFail())
			.done((data) => ajaxc.onDone(data))
			.always((data) => ajaxc.onAlways(data));
	}

}
