
import * as ReactDOM from 'react-dom';
import {Controleur} from 'struct/Controleur';
import {Vue} from 'struct/Vue';
import {MainModele} from 'MainModele';
import {AccueilManager} from 'AccueilManager';
import {ConfigManager} from 'ConfigManager';
import {MainVue} from 'MainVue';
import {AjaxCallback, Data} from 'struct/AjaxCallback';
import {AlertLevel} from 'items/Alert';
import {Pages} from 'pages/Pages';

export class MainManager extends Controleur<MainModele, MainVue> {

	private _accueilM: AccueilManager;
	get accueilM(): AccueilManager {
		return this._accueilM;
	}
	set accueilM(accueilM: AccueilManager) {
		this._accueilM = accueilM;
	}

	private _configM: ConfigManager;
	get configM(): ConfigManager {
		return this._configM;
	}
	set configM(configM: ConfigManager) {
		this._configM = configM;
	}

	public constructor() {
		super(new MainModele(GLOBALS));
		this.mainManager = this;
		this.accueilM = new AccueilManager(this.modele.donnees, this);
		this.configM = new ConfigManager(this.modele.donnees, this);
	}

	public start(): void {
		MainVue.applyVue(this);
	}

	public deconnexion(element: HTMLElement): void {

		function deconnecter(manager: MainManager) {
			manager.modele.deconnexion(new AjaxCallback(manager, 'Déconnexion', {
				success: (data: Data) => {
					manager.modele.donnees = data.content;
					manager.vue.mainSwitchPage(Pages.Accueil);
				}
			}));
		}


		this.askConfirm('Deconnexion', 'Voulez-vous vraiment vous déconnecter ?', () => deconnecter(this), element, true);
		//		this.modele.deconnexion(new AjaxCallback(this, 'Déconnexion', {
		//			success: (data: Data) => {
		//				this.modele.donnees = data.content;
		//				this.vue.mainSwitchPage(Pages.Accueil);
		//			}
		//		}));
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