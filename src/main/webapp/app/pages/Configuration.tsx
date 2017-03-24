
import * as React from 'react';
import {Page, PageProps} from 'Page';
import {BlocPropriete} from 'items/BlocPropriete';
import {ConfigManager} from 'modules/main/ConfigManager';

export interface ConfigurationProps extends PageProps<ConfigManager> {

}

export class Configuration extends Page<ConfigurationProps> {

	public static readonly NOM = 'Configuration';

	public constructor(props: ConfigurationProps) {
		super(props, Configuration.NOM);
	}

	public hasHeader(): boolean {
		return true;
	}

	public renderBandeau() {
		return <div className="bandeau dark">
			<div className="container">
				<div className="row">
					<h1>{this.nom}</h1>
					<div className="lead">
						Configurer vos propriétés, leurs valeurs et visibilités.
								</div>
				</div>
			</div>
		</div>;
	}

	public render() {
		return <div id="list-box" className="page-content container">
			<div className="row">

				{this.props.donnees.proprietes.map(p => {
					return <BlocPropriete
						key={p.key} id={p.key} controleur={this.props.controleur} nom={p.nom} typeStr={p.typeStr}
						type={p.type} modifiable={p.modifiable} supprimable={p.supprimable}
						nbrmin={p.nbrmin} nbrmax={p.nbrmax} taillemin={p.taillemin} taillemax={p.taillemax}
						valeurs={p.valeurs}
					/>;
				})}

			</div>
		</div>;
	}

}