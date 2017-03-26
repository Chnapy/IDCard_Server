
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {LigneValeur, LigneAdd, NewLigneValeur} from 'LigneValeur';
import {BoutonAdd} from 'items/Bouton';
import {ConfigManager} from 'modules/main/ConfigManager';

export interface SiteProp {
	key: number,
	site: string
}

export interface ValeurProp {
	key: number,
	valeur: string,
	principal: boolean,
	publique: boolean,
	prive: boolean,
	sites: SiteProp[]
}

export interface BlocProprieteProps extends VueProps<ConfigManager> {
	key: number,
	id: number,
	nom: string,
	valeurs: ValeurProp[]
	typeStr: string,
	type: string,
	modifiable: boolean,
	supprimable: boolean,
	taillemin: number,
	taillemax: number,
	nbrmin: number,
	nbrmax: number
}

interface BlocProprieteState {
	valeurs?: ValeurProp[],
	newval?: boolean
}

export class BlocPropriete extends Vue<BlocProprieteProps, BlocProprieteState> {

	public constructor(props: BlocProprieteProps) {
		super(props);
		this.state = {valeurs: props.valeurs};
	}

	public render() {

		let valeurs = this.state.valeurs as ValeurProp[];

		return <div className="box col-md-6">
			<div className="box-content bloc col-md-12">
				<div className="container-fluid">
					<div className="box-head row">
						<span className="box-title">{this.props.nom}</span>
						<div className="box-head-right">
							<span className="tag typeStr">{this.props.typeStr}</span>
						</div>
					</div>
					<div className="box-body row">
						<div className="container-fluid">
							{valeurs.map((v) => {
								return <LigneValeur key={v.key} id={v.key} id_prop={this.props.id} controleur={this.props.controleur} valeur={v.valeur}
									type={this.props.type} principal={v.principal} publique={v.publique} prive={v.prive}
									sites={v.sites} modifiable={this.props.modifiable}
									supprimable={this.props.supprimable && valeurs.length > this.props.nbrmin}
									taillemin={this.props.taillemin} taillemax={this.props.taillemax} />
							})}
							{this.renderFoot(valeurs)}
						</div>
					</div>
				</div>
			</div>
		</div>;

	}

	private renderFoot(valeurs: ValeurProp[]) {
		if (valeurs.length >= this.props.nbrmax) {
			return undefined;
		}

		return this.state.newval
			? <NewLigneValeur controleur={this.props.controleur}
				id_prop={this.props.id} type={this.props.type}
				taillemin={this.props.taillemin}
				taillemax={this.props.taillemax} />
			: <LigneAdd className="but-fh" onClick={(e: any, b: any) => this.setState({newval: true})}
			 />

	}

}