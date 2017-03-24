
import * as React from 'react';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {Input} from 'items/inputs/Input';
import {Tag} from 'items/Tag';
import {ConfigManager} from 'modules/main/ConfigManager';
import {SiteProp} from 'BlocPropriete';

interface LigneValeurProps extends VueProps<ConfigManager> {
	key: number,
	id: number,
	id_prop: number,
	valeur: string,
	type: string,
	principal: boolean,
	publique: boolean,
	prive: boolean,
	sites: SiteProp[],
	modifiable: boolean,
	supprimable: boolean,
	taillemin: number,
	taillemax: number
}

interface LigneValeurState {
	sites: SiteProp[]
}

export class LigneValeur extends Vue<LigneValeurProps, LigneValeurState> {

	private input: Input;

	public constructor(props: LigneValeurProps) {
		super(props);
		this.state = {sites: props.sites};
		this.onEnter = this.onEnter.bind(this);
		this.onRemoveSite = this.onRemoveSite.bind(this);
	}

	private onEnter(e: React.KeyboardEvent, input: Input) {
		const n: HTMLElement = $(e.target).find('input')[0];
		var f = () => {n.blur()};
		
		this.props.controleur.updateValeur(this.props.id, input.state.value, f);
	}

	private onRemoveSite(e: React.MouseEvent, tag: Tag) {
		this.props.controleur.removeSite(this.props.id_prop, this.props.id, tag.props.id as number, this, e.target as HTMLElement);

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
				<Input ref={i => this.input = i} type={this.props.type}
					value={this.props.valeur} readonly={!this.props.modifiable} onenter={this.onEnter}
					minlength={this.props.taillemin} maxlength={this.props.taillemax} required={true}
					checkvalidation={true} />
			</div>
			<div className="box-line-visib col-xs-6">
				{this.state.sites.map(s => <Tag key={s.key} id={s.key} value={s.site} deletable={true} onhover={true}
					ondelete={this.onRemoveSite} />)}
			</div>
			{this.props.supprimable ? <button className="but but-delete but-error but-fh"></button> : ''}
		</div>;

	}

}