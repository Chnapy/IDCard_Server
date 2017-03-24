
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {Vue} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';
import {AjaxCallback, Donnees, Data} from 'struct/AjaxCallback';
import {Pages} from 'pages/Pages';
import {AccueilModele} from 'AccueilModele';
import {Accueil} from 'pages/Accueil';

export class AccueilManager extends Controleur<AccueilModele, Accueil> {

	public constructor(donnees: Donnees, mainManager: MainManager) {
		super(new AccueilModele(donnees), mainManager);
	}

	private getConnexionSuccess(): any {
		return (data: Data) => {
			this.modele.donnees = data.content;
			this.showAlertFromCode(1);
			this.mainManager.vue.setState({donnees: this.modele.donnees});
			this.mainManager.vue.mainSwitchPage(Pages.Configuration);
		};
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, vue: Vue<any, any>): void {
		let stopLoad = () => vue.setState({load: false});
		this.modele.connexion(pseudo, mail, mdp, isMail, new AjaxCallback(this, 'Connexion', {
			success: this.getConnexionSuccess(),
			error: stopLoad,
			fail: stopLoad
		}));
	}

	public inscription(pseudo: string, mail: string, mdp: string, vue: Vue<any, any>): void {
		let stopLoad = () => vue.setState({load: false});
		this.modele.inscription(pseudo, mail, mdp, new AjaxCallback(this, 'Inscription', {
			success: (data: Data) => {
				this.showAlertFromCode(2);
				this.getConnexionSuccess()(data);
			},
			error: stopLoad,
			fail: stopLoad
		}));
	}

}