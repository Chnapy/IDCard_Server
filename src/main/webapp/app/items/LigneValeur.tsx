
import * as React from 'react';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {Input} from 'items/inputs/Input';
import {Tag} from 'items/Tag';
import {ConfigManager} from 'modules/main/ConfigManager';

interface LigneValeurProps extends VueProps<ConfigManager> {
	key: number,
	id: number,
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
}

export class LigneValeur extends Vue<LigneValeurProps, LigneValeurState> {

	public constructor(props: LigneValeurProps) {
		super(props);
		this.onEnter = this.onEnter.bind(this);
	}

	private onEnter(e: React.KeyboardEvent, input: Input) {
		this.props.controleur.updateValeur(this.props.id, input.state.value);
	}

	public render() {
		return <div className={classNames("box-line", "row", {
			"main": this.props.principal,
			"modifiable": this.props.modifiable,
			"supprimable": this.props.supprimable,
			"public": this.props.publique,
			"prive": this.props.prive
		})}>
			<div className='box-line-ip col-xs-6' onSubmit={e => e.preventDefault()}>
				<Input type={this.props.type}
					value={this.props.valeur} readonly={!this.props.modifiable} onenter={this.onEnter}
					minlength={this.props.taillemin} maxlength={this.props.taillemax} required={true}
					checkvalidation={true} />
			</div>
			<div className="box-line-visib col-xs-6">
				{this.props.sites.map((s, i) => <Tag key={i} value={s} />)}
			</div>
			{this.props.supprimable ? <button className="but but-delete but-error but-fh"></button> : ''}
		</div>;

	}

}