
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';
import {LigneValeur} from 'LigneValeur';

export interface ValeurProp {
	key: number,
	valeur: string,
	principal: boolean,
	publique: boolean,
	prive: boolean,
	sites: string[]
}

export interface BlocProprieteProps extends VueProps<MainManager> {
	key: number,
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
	valeurs: ValeurProp[]
}

export class BlocPropriete extends Vue<BlocProprieteProps, BlocProprieteState> {

	private static keys: number = 0;

	public constructor(props: BlocProprieteProps) {
		super(props);
		this.state = {valeurs: props.valeurs};
	}

	public render() {

		return <div className="box col-md-6">
			<div className="box-content bloc col-md-12">
				<div className="container-fluid">
					<div className="box-head row">
						<span className="box-title">{this.props.nom}</span>
						<div className="box-head-right">
							<span className="field typeStr">{this.props.typeStr}</span>
						</div>
					</div>
					<div className="box-body row">
						<div className="container-fluid">
							{this.state.valeurs.map((v) => {
								BlocPropriete.keys++;
								return <LigneValeur key={BlocPropriete.keys} controleur={this.props.controleur} valeur={v.valeur}
									type={this.props.type} principal={v.principal} publique={v.publique} prive={v.prive}
									sites={v.sites} modifiable={this.props.modifiable}
									supprimable={this.props.supprimable && this.state.valeurs.length > this.props.nbrmin}
									taillemin={this.props.taillemin} taillemax={this.props.taillemax} />
							})}
							{this.state.valeurs.length < this.props.nbrmax ? <div className="box-line row">
								<button className="but but-add but-fh"></button>
							</div> : ''}
						</div>
					</div>
				</div>
			</div>
		</div>;

	}

}