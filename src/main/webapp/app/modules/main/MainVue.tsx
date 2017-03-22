
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

export interface MainVueProps extends VueProps<MainManager> {
	user: {
		connected: boolean,
		pseudo: string
	},
	page: string
}

interface MainVueState {
	alertList?: AlertData[],
	page?: any,
	display?: boolean
}

export class MainVue extends Vue<MainVueProps, MainVueState> {

	public static applyVue(controleur: MainManager): void {
		ReactDOM.render(
			<MainVue controleur={controleur} user={GLOBALS.user} page={GLOBALS.page} onSwitch={undefined} onAlert={undefined} />,
			document.getElementById("react_container") as Element
		);
	}

	private alertKey: number;
	private readonly alertList: AlertData[];

	public constructor(props: MainVueProps, context?: MainVueState) {
		super(props, context);
		this.alertList = [];
		this.alertKey = 1;
		
		this.state = {alertList: [], page: (Pages as any)[props.page], display: true};
		this.mainAlert = this.mainAlert.bind(this, this.state.alertList);
		this.mainSwitchPage = this.mainSwitchPage.bind(this, this.state.page);
	}

	public mainSwitchPage(arr: any, page: any) {
		console.log("SWITCH " + page);
		console.log(arguments.length);
		this.setState({display: false});
		setTimeout(() => this.setState({page: page, display: true}), Const.TRANSITION_DURATION);
	}

	public mainAlert(arr: any, level: AlertLevel, title: string, content: string): void {
		//		var alert: any = <Alert key={this.alertKey} level={level} title={title} content={content} />;
		let curDate = new Date();
		var alert: AlertData = {
			key: this.alertKey, level: level, title: title, content: content, time: curDate.toLocaleTimeString(), hide: false
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
			user: this.props.user
		});

		console.log('Page: ' + p.nom);

		return (
			<div id="react_content" className={classNames('main-content', {
				'no-header': !p.hasHeader(),
				'no-display': !this.state.display
			})}>

				<Header controleur={this.props.controleur} user={this.props.user} page={p.nom} show={p.hasHeader()} onSwitch={this.mainSwitchPage} onAlert={this.mainAlert} />

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