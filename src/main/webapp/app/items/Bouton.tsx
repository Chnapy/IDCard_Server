
import * as React from 'react';
import * as classNames from 'classnames';

interface BoutonProps {
	value: string,
	add?: boolean,
	delete?: boolean,
	primary?: boolean,
	className?: string,
	submit?: boolean,
	disabled?: boolean,
	load?: boolean,
	onClick: any
}

interface BoutonState {
	disabled?: boolean,
	load?: boolean
}

export class Bouton extends React.Component<BoutonProps, BoutonState> {

	public constructor(props: BoutonProps, context?: BoutonState) {
		super(props, context);
		this.state = {disabled: this.props.disabled, load: this.props.load};
	}

	public render() {

		return <button className={classNames({
			'but': true,
			'primary': this.props.primary,
			'add': this.props.add,
			'delete': this.props.delete,
			'load': this.props.load,
			'disabled': this.state.disabled || this.props.disabled
		}, this.props.className)
		} type={this.props.submit ? 'submit' : 'button'} onClick={e =>
			this.props.onClick(e, this)
		} disabled={this.state.disabled || this.props.disabled}>{this.props.value}</button>
	}

}

interface BoutonAddProps {
	primary?: boolean;
	className?: string;
	submit?: boolean;
	disabled?: boolean;
	load?: boolean;
	onClick: any;
}

export class BoutonAdd extends React.Component<BoutonAddProps, BoutonState> {

	public constructor(props?: BoutonAddProps, context?: BoutonState) {
		super(props, context);
	}
	
	public render() {
		return <Bouton value='' add={true} delete={false} className={'but-add'.concat(this.props.className as string)} {...this.props} />
	}

}