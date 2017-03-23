/* 
 * 
 * 
 * 
 */

import {Modele} from 'struct/Modele';
import {AjaxCallback, Donnees} from 'struct/AjaxCallback';

/**
 * MainModele
 * 
 */
export class MainModele extends Modele {

	private _donnees: Donnees;
	get donnees(): Donnees {
		return this._donnees;
	}
	set donnees(donnees: Donnees) {
		this._donnees = donnees;
	}

	public constructor(GLOBALS: {titre_main: string, donnees: Donnees, page: string}) {
		super();
		this.donnees = GLOBALS.donnees;
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
