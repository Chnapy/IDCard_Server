
import * as React from 'react';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';

interface LigneValeurProps extends VueProps<MainManager> {
	key: number,
	valeur: string,
	type: string,
	principal: boolean,
	publique: boolean,
	prive: boolean,
	sites: string[],
	modifiable: boolean,
	supprimable: boolean,
	taillemin: number,
	taillemax: number
}

interface LigneValeurState {
	valeur: string
}

export class LigneValeur extends Vue<LigneValeurProps, LigneValeurState> {

	public constructor(props: LigneValeurProps) {
		super(props);
		this.state = {valeur: props.valeur};
	}

	public render() {

		return <div className={classNames("box-line", "row", {
			"main": this.props.principal,
			"modifiable": this.props.modifiable,
			"supprimable": this.props.supprimable,
			"public": this.props.publique,
			"prive": this.props.prive
		})}>
			<input type={this.props.type} className={classNames("box-line-ip", "field", "col-xs-6")}
				value={this.state.valeur} onChange={e => this.setState({valeur: (e.target as HTMLInputElement).value})} readOnly={!this.props.modifiable} minLength={this.props.taillemin} maxLength={this.props.taillemax} />
			<div className="box-line-visib col-xs-6">{this.props.sites.join(', ')}</div>
			{this.props.supprimable ? <button className="but but-delete but-error but-fh"></button> : ''}
		</div>;

	}

}