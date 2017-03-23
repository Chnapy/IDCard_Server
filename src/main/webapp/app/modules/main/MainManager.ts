
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {Vue} from 'struct/Vue';
import {MainModele} from 'MainModele';
import {MainVue} from 'MainVue';
import {AjaxCallback, Data} from 'struct/AjaxCallback';
import {AlertLevel} from 'items/Alert';
import {Pages} from 'pages/Pages';

export class MainManager extends Controleur<MainModele, MainVue> {

	public constructor() {
		super(new MainModele(GLOBALS));
	}

	public start(): void {
		MainVue.applyVue(this);
	}

	private getConnexionSuccess(): any {
		return (data: Data) => {
			this.modele.donnees = data.content;
			this.showAlertFromCode(1);
			this.vue.setState({donnees: this.modele.donnees});
			this.vue.mainSwitchPage(Pages.Configuration);
		};
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, vue: Vue<any, any>): void {
		let stopLoad = () => vue.setState({load: false});
		this.modele.connexion(pseudo, mail, mdp, isMail, new AjaxCallback(this, {
			success: this.getConnexionSuccess(),
			error: stopLoad,
			fail: stopLoad
		}));
	}

	public deconnexion(): void {
		this.modele.deconnexion(new AjaxCallback(this, {
			success: (data: Data) => {
				this.modele.donnees = data.content;
				this.vue.mainSwitchPage(Pages.Accueil);
			}
		}));
	}

	public inscription(pseudo: string, mail: string, mdp: string, vue: Vue<any, any>): void {
		let stopLoad = () => vue.setState({load: false});
		this.modele.inscription(pseudo, mail, mdp, new AjaxCallback(this, {
			success: (data: Data) => {
				this.showAlertFromCode(2);
				this.getConnexionSuccess()(data);
			},
			error: stopLoad,
			fail: stopLoad
		}));
	}

	public showAlertFromCode(code_num: number) {
		var code = Const.CODES[code_num];
		if (!code) {
			code = Const.CODES[400];
		}

		var level: AlertLevel;
		switch (code.crit) {
			case 0: level = AlertLevel.Normal; break;
			default: level = AlertLevel.Error; break;
		}
		this.vue.mainAlert(level, code.titre, code.message, code_num);
	}

}