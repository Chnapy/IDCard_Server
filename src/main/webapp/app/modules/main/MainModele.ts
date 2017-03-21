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
		$.post('connexion', {pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail}, (data: any) => ajaxc.onSuccess(data))
			.fail(() => ajaxc.onFail)
			.done((data: any) => ajaxc.onDone(data))
			.always((data: any) => ajaxc.onAlways(data));
	}

	public inscription(pseudo: string, mail: string, mdp: string, ajaxc: AjaxCallback): void {
		$.post('inscription', {pseudo: pseudo, mail: mail, mdp: mdp}, (data: any) => ajaxc.onSuccess(data))
			.fail(() => ajaxc.onFail)
			.done((data: any) => ajaxc.onDone(data))
			.always((data: any) => ajaxc.onAlways(data));
	}

}
