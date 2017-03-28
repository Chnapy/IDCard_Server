
import * as React from 'react';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {Input} from 'items/inputs/Input';
import {BoutonAdd, BoutonDelete, BoutonAddProps} from 'items/Bouton';
import {Tag} from 'items/Tag';
import {ConfigManager} from 'modules/main/ConfigManager';
import {BlocPropriete, SiteProp} from 'BlocPropriete';

interface LigneValeurProps extends VueProps<ConfigManager> {
	key?: number,
	id?: number,
	id_prop: number,
	valeur?: string,
	type: string,
	principal?: boolean,
	publique?: boolean,
	prive?: boolean,
	sites?: SiteProp[],
	modifiable?: boolean,
	supprimable?: boolean,
	taillemin: number,
	taillemax: number,
	autofocus?: boolean,
	onenter?: (e: React.SyntheticEvent, input: Input) => void,
	blocProp: BlocPropriete
}

interface LigneValeurState {
	sites: SiteProp[]
}

export class LigneValeur extends Vue<LigneValeurProps, LigneValeurState> {

	public constructor(props: LigneValeurProps) {
		super(props);
		this.state = {sites: props.sites ? props.sites : []};
		this.onEnter = this.onEnter.bind(this);
		this.onRemoveSite = this.onRemoveSite.bind(this);
	}

	private onEnter(e: React.KeyboardEvent, input: Input) {
		const n: HTMLElement = $(e.target).find('input')[0];
		var f = () => {n.blur()};

		this.props.controleur.updateValeur(this.props.id as number, input.state.value, f);
	}

	private onRemoveSite(e: React.MouseEvent, tag: Tag) {
		this.props.controleur.removeSite(this.props.id_prop, this.props.id as number, tag.props.id as number, this, e.target);

	}

	public render() {
		return <div className={classNames("box-line", "row", {
			"principal": this.props.principal,
			"modifiable": this.props.modifiable,
			"supprimable": this.props.supprimable,
			"public": this.props.publique,
			"prive": this.props.prive
		})}>
			<div className='box-line-ip col-xs-6' onSubmit={e => e.preventDefault()}>
				<Input type={this.props.type}
					value={this.props.valeur} readonly={!this.props.modifiable}
					onenter={this.props.onenter ? this.props.onenter : this.onEnter}
					minlength={this.props.taillemin} maxlength={this.props.taillemax} required
					checkvalidation autofocus={this.props.autofocus} />
			</div>
			<div className="box-line-visib col-xs-6">
				{this.state.sites.map(s =>
					<Tag key={s.key} id={s.key} value={s.site} title={s.site} reduce
						tooltip tooltip_placement='top'
						icon={<img className='icon'
							src={'http://www.google.com/s2/favicons?domain_url=' + s.site} />}
						deletable onhover ondelete={this.onRemoveSite} />
				)}
			</div>
			{this.props.supprimable ? <BoutonDelete className="but-fh" onClick={(e) =>
				this.props.controleur.removeValeur(this.props.id_prop, this.props.id as number, this.props.blocProp, e.target)
			} /> : ''}
		</div>;

	}

}

export class LigneAdd extends React.Component<BoutonAddProps, any> {

	public render() {
		return <div className="box-line row">
			<BoutonAdd {...this.props} />
		</div>
	}
}

export class NewLigneValeur extends Vue<LigneValeurProps, undefined> {

	public constructor(props: LigneValeurProps) {
		super(props);
		this.onEnter = this.onEnter.bind(this);
	}

	private onEnter(e: React.SyntheticEvent, input: Input) {
		this.props.controleur.addValeur(this.props.id_prop, input.state.value, this.props.blocProp);
	}

	public render() {
		return <LigneValeur modifiable autofocus onenter={this.onEnter} {...this.props} />
	}

}