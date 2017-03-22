/* 
 * 
 * 
 * 
 */

import {Modele} from 'struct/Modele';
import {AjaxCallback, User} from 'struct/AjaxCallback';

/**
 * MainModele
 * 
 */
export class MainModele extends Modele {
	
	private _user: User;
	get user(): User {
		return this._user;
	}
	set user(user: User) {
		this._user = user;
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, ajaxc: AjaxCallback): void {
		this.ajaxPost('connexion', {pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail}, ajaxc);
	}

	public deconnexion(ajaxc: AjaxCallback): void {
		this.ajaxPost('deconnexion', {}, ajaxc);
	}

	public inscription(pseudo: string, mail: string, mdp: string, ajaxc: AjaxCallback): void {
		this.ajaxPost('inscription', {pseudo: pseudo, mail: mail, mdp: mdp}, ajaxc);
	}

}
