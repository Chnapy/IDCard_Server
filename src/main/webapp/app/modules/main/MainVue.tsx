
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {Modele} from 'struct/Modele';
import {MainManager} from 'MainManager';
import {Header} from 'items/Header';
import {Page, PageProps} from 'pages/Page';
import {Pages} from 'pages/Pages';
import {AlertLevel} from 'items/Alert';
import {ConfirmBox, ConfirmBoxProps} from 'items/ConfirmBox';
import {AlertList, AlertData} from 'items/AlertList';
import {Donnees} from 'struct/AjaxCallback';
import {AjaxEnum} from 'struct/AjaxCallback';

export interface MainVueProps extends VueProps<MainManager> {
	//	mainManager: MainManager,
	donnees: Donnees,
	page: string
}

interface MainVueState {
	controleur?: Controleur<Modele, Page<PageProps<any>>>,
	alertList?: AlertData[],
	page?: any,
	display?: boolean,
	donnees?: Donnees,
	etatAjax?: AjaxEnum,
	nomAjax?: string,
	confirmBox?: ConfirmBoxProps
}

export class MainVue extends Vue<MainVueProps, MainVueState> {

	public static applyVue(controleur: MainManager): void {
		ReactDOM.render(
			<MainVue controleur={controleur} donnees={GLOBALS.donnees} page={GLOBALS.page} />,
			document.getElementById("react_container") as Element
		);
	}

	private alertKey: number;
	private readonly alertList: AlertData[];

	public constructor(props: MainVueProps, context?: MainVueState) {
		super(props, context);
		this.alertList = [];
		this.alertKey = 1;

		let page = (Pages as any)[props.page];
		let controleur = this.getControleurFromPage(page);

		this.state = {controleur: controleur, alertList: [], page: page, display: true, donnees: props.donnees, etatAjax: AjaxEnum.None};
	}

	private getControleurFromPage(page: any): Controleur<Modele, Page<PageProps<any>>> {
		if (page === Pages.Accueil) {
			return this.props.controleur.accueilM;
		}
		if (page === Pages.Configuration) {
			return this.props.controleur.configM;
		}
		throw new Error('Page non gérée: ' + page);
	}

	public mainSwitchPage(page: any) {
		console.log("SWITCH " + page);
		console.log(arguments.length);
		this.setState({display: false});

		let controleur = this.getControleurFromPage(page)
		setTimeout(() => {
			this.setState({controleur: controleur, page: page, display: true});
			window.scrollTo(0, 0);
		}, Const.TRANSITION_DURATION);
	}

	public mainAlert(level: AlertLevel, title: string, content: string, code: number, message_debug?: string) {
		let curDate = new Date();
		if (!code) {
			code = 0;
		}
		var alert: AlertData = {
			key: this.alertKey, level: level, title: title, content: content, code: code, 
			time: curDate.toLocaleTimeString(), hide: false, debug: message_debug
		};
		this.alertList.push(alert);
		this.alertKey++;

		this.setState({
			alertList: this.alertList
		});
	}

	public render() {
		this.props.controleur.vue = this;

		let p: Page<PageProps<any>> = new this.state.page({
			controleur: this.state.controleur,
			onSwitch: this.mainSwitchPage,
			onAlert: this.mainAlert,
			donnees: this.state.donnees
		});

		console.log('Page: ' + p.nom);

		return (
			<div id="react_content" className={classNames('main-content', {
				'no-header': !p.hasHeader(),
				'no-display': !this.state.display
			})}>

				<Header mainManager={this.props.controleur} donnees={this.state.donnees as Donnees}
					page={p.nom} show={p.hasHeader()}
					nomAjax={this.state.nomAjax as string} etatAjax={this.state.etatAjax as AjaxEnum}
				/>

				<div id="content" className="body-content">

					{p.renderBandeau()}

					{p.render()}

				</div>

				<AlertList alerts={this.state.alertList as AlertData[]} />

				<footer className="footer">

				</footer>

				{this.state.confirmBox ? <ConfirmBox {...this.state.confirmBox} onHide={() => this.setState({confirmBox: undefined})} /> : ''}

			</div>
		);
	}

}