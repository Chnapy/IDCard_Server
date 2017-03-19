
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {Vue} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'MainManager';
import {Header} from 'items/Header';
import {StartForm} from 'items/StartForm';

export interface MainVueProps {
	controleur: MainManager,
	user: any,
	page: string
}

export class MainVue extends Vue<MainVueProps, undefined> {

	private affichage: Vue<any, any>;

	public applyVue(controleur: MainManager): void {
		ReactDOM.render(
			<MainVue controleur={controleur} user={GLOBALS.user} page={GLOBALS.page} />,
			document.getElementById("react_container") as Element
		);
	}

	public render() {

		let connected: boolean = this.props.user.connected;

		return (
			<div id="react_content" className={connected ? '' : 'no-header'}>
				
				<Header user={this.props.user} page={this.props.page} />

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
							<StartForm />
						</div>
					</div>
				</div>

				<footer className="footer">

				</footer>
			</div>
		);
	}

}