

import {Modele} from 'struct/Modele';
import {AjaxCallback, Donnees} from 'struct/AjaxCallback';


export class AccueilModele extends Modele {

	public constructor(donnees: Donnees) {
		super(donnees);
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, ajaxc: AjaxCallback): void {
		this.ajaxPost('connexion', {pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail}, ajaxc);
	}

	public inscription(pseudo: string, mail: string, mdp: string, ajaxc: AjaxCallback): void {
		this.ajaxPost('inscription', {pseudo: pseudo, mail: mail, mdp: mdp}, ajaxc);
	}

}