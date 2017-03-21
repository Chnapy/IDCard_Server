
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Vue, VueProps} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'MainManager';
import {Header} from 'items/Header';
import {StartForm} from 'items/StartForm';
import {Alert, AlertLevel} from 'items/Alert';
import {AlertList, AlertData} from 'items/AlertList';

export interface MainVueProps extends VueProps<MainManager> {
	user: any,
	page: string
}

interface MainVueState {
	alertList: Array<AlertData>
}

export class MainVue extends Vue<MainVueProps, MainVueState> {

	public static applyVue(controleur: MainManager): void {
		ReactDOM.render(
			<MainVue controleur={controleur} user={GLOBALS.user} page={GLOBALS.page} onAlert={undefined} />,
			document.getElementById("react_container") as Element
		);
	}

	private alertKey: number;
	private readonly alertList: Array<AlertData>;

	public constructor(props?: MainVueProps, context?: MainVueState) {
		super(props, context);
		this.alertList = [];
		this.alertKey = 1;
		this.state = {alertList: []};
		this.mainAlert = this.mainAlert.bind(this, this.state.alertList);
	}

	public mainAlert(arr: any, level: AlertLevel, title: string, content: string): void {
//		var alert: any = <Alert key={this.alertKey} level={level} title={title} content={content} />;
		var alert: AlertData = {
			key: this.alertKey, level: level, title: title, content: content, hide: false
		};
		this.alertList.push(alert);
		this.alertKey++;

		this.setState({
			alertList: this.alertList
		});
	}

	public render() {
		this.props.controleur.vue = this;

		let connected: boolean = this.props.user.connected;

		return (
			<div id="react_content" className={connected ? '' : 'no-header'}>

				<Header controleur={this.props.controleur} user={this.props.user} page={this.props.page} onAlert={this.mainAlert} />

				<div id="content" className="body-content">
					<div className="bandeau dark">
						<div className="container">
							<div className="row" id="main_title">
								<h1>{Const.TITRE_MAIN}</h1>
								<div className="lead">
									Inscrivez-vous une fois, connectez-vous partout.
								</div>
							</div>
						</div>
					</div>

					<div className="container">
						<div className="row">
							<div id="description" className="col-md-6 col-xs-12">
								<div className="lead">
									Inscrivez-vous une fois,<br />
									Connectez-vous partout.
						</div>
								<div>
									Description du service, blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
							blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla
						</div>
							</div>
							<StartForm controleur={this.props.controleur} onAlert={this.mainAlert} />
						</div>
					</div>
				</div>

				<AlertList alerts={this.state.alertList} />

				<footer className="footer">

				</footer>
			</div>
		);
	}

}