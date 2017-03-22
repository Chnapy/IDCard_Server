
import * as React from 'react';
import {Page, PageProps} from 'Page';
import {Header} from 'items/Header';
import {StartForm} from 'items/StartForm';

export interface AccueilProps extends PageProps {

}

export class Accueil extends Page<AccueilProps> {

	public static readonly NOM = 'Accueil';

	public constructor(props: AccueilProps) {
		super(props, Accueil.NOM);
	}

	public hasHeader(): boolean {
		return false;
	}

	public renderHeader() {
		return <Header controleur={this.props.controleur} user={this.props.user} page={this.nom} show={this.hasHeader()} onSwitch={this.switchPage} onAlert={this.addAlert} />
	}

	public renderBandeau() {
		return <div className="bandeau dark">
			<div className="container">
				<div className="row" id="main_title">
					<h1>{Const.TITRE_MAIN}</h1>
					<div className="lead">
						Inscrivez-vous une fois, connectez-vous partout.
								</div>
				</div>
			</div>
		</div>;
	}

	public render() {
		return <div className="container page-content">
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
				<StartForm controleur={this.props.controleur} onSwitch={this.props.onSwitch} onAlert={this.props.onAlert} />
			</div>
		</div>;
	}

}