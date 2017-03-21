
import * as ReactDOM from 'react-dom';
import {Vue} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainModele} from 'MainModele';
import {MainVue} from 'MainVue';
import {AjaxCallback} from 'struct/AjaxCallback';
import {AlertLevel} from 'items/Alert';

export class MainManager extends Controleur<MainModele, MainVue> {

	public constructor() {
		super(new MainModele(), new MainVue());
	}

	public start(): void {
		MainVue.applyVue(this);
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, callbacks: any): void {
		this.modele.connexion(pseudo, mail, mdp, isMail, new ConnexionAjax(callbacks));
	}

	public inscription(pseudo: string, mail: string, mdp: string): void {
		this.modele.inscription(pseudo, mail, mdp, new InscriptionAjax());
	}

}

class ConnexionAjax extends AjaxCallback {

	public onSuccess(data: any): void {
		super.onSuccess(data);
		console.debug(data);
	}

	public onFail(): void {
		super.onFail();
		console.debug("connexion fail");
	}
}

class InscriptionAjax extends AjaxCallback {

	public onSuccess(data: any): void {
		super.onSuccess(data);
		console.debug(data);
	}

	public onFail(): void {
		super.onFail();
		console.debug("inscription fail");
	}
}