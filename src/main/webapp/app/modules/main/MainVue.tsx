
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'MainManager';
import {Header} from 'items/Header';
import {Page, PageProps} from 'pages/Page';
import {Pages} from 'pages/Pages';
import {AlertLevel} from 'items/Alert';
import {AlertList, AlertData} from 'items/AlertList';
import {Donnees} from 'struct/AjaxCallback';

export interface MainVueProps extends VueProps<MainManager> {
	donnees: Donnees,
	page: string
}

interface MainVueState {
	alertList?: AlertData[],
	page?: any,
	display?: boolean,
	donnees?: Donnees
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

		this.state = {alertList: [], page: (Pages as any)[props.page], display: true, donnees: props.donnees};
	}

	public mainSwitchPage(page: any) {
		console.log("SWITCH " + page);
		console.log(arguments.length);
		this.setState({display: false});
		setTimeout(() => this.setState({page: page, display: true}), Const.TRANSITION_DURATION);
	}

	public mainAlert(level: AlertLevel, title: string, content: string, code: number) {
		let curDate = new Date();
		if (!code) {
			code = 0;
		}
		var alert: AlertData = {
			key: this.alertKey, level: level, title: title, content: content, code: code, time: curDate.toLocaleTimeString(), hide: false
		};
		this.alertList.push(alert);
		this.alertKey++;

		this.setState({
			alertList: this.alertList
		});
	}

	public render() {
		this.props.controleur.vue = this;

		let p: Page<PageProps> = new this.state.page({
			controleur: this.props.controleur,
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

				<Header controleur={this.props.controleur} donnees={this.state.donnees as Donnees} page={p.nom} show={p.hasHeader()} />

				<div id="content" className="body-content">

					{p.renderBandeau()}

					{p.render()}

				</div>

				<AlertList alerts={this.state.alertList as AlertData[]} />

				<footer className="footer">

				</footer>
			</div>
		);
	}

}