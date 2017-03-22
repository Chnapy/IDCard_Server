
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
		super(new MainModele());
	}

	public start(): void {
		MainVue.applyVue(this);
	}

	public connexion(pseudo: string, mail: string, mdp: string, isMail: boolean, vue: Vue<any, any>): void {
		this.modele.connexion(pseudo, mail, mdp, isMail, new AjaxCallback({
			success: (data: Data) => {
				vue.addAlert(AlertLevel.Primary, "Connexion réussie", "Félicitations");
				this.modele.user = data.content.user;
				this.vue.setState({user: this.modele.user});
			},
			error: (data: Data) => this.showAlertFromCode(data.code, vue),
			fail: () => this.showAlertFromCode(100, vue),
			always: () => {
				vue.setState({load: false});
				vue.switchPage(Pages.Configuration);
			}
		}));
	}

	public deconnexion(callbacks: any): void {
		this.modele.deconnexion(new AjaxCallback(callbacks));
	}

	public inscription(pseudo: string, mail: string, mdp: string, vue: Vue<any, any>): void {
		this.modele.inscription(pseudo, mail, mdp, new AjaxCallback({
			success: (data: Data) => vue.addAlert(AlertLevel.Primary, "Inscription réussie", "Félicitations"),
			error: (data: Data) => this.showAlertFromCode(data.code, vue),
			fail: () => this.showAlertFromCode(100, vue),
			always: () => vue.setState({load: false})
		}));
	}

	private showAlertFromCode(code_num: number, vue: Vue<any, any>) {
		let code = Const.CODES[code_num];
		//		console.debug(data);
		if (!code) {
			code = Const.CODES[400];
		}

		let level: AlertLevel;
		switch (code.crit) {
			case 0: level = AlertLevel.Normal; break;
			default: level = AlertLevel.Error; break;
		}
		vue.addAlert(level, code.titre, code.message, code_num);
	}

}