
import * as React from 'react';
import * as classNames from 'classnames';

interface BoutonProps {
	value: string,
	primary?: boolean,
	className?: string,
	submit?: boolean,
	disabled?: boolean,
	load?: boolean,
	onClick: any,
	onClickDisable?: boolean,
	onClickLoad?: boolean
}

interface BoutonState {
	disabled?: boolean,
	load?: boolean
}

export class Bouton extends React.Component<BoutonProps, BoutonState> {

	public constructor(props?: BoutonProps, context?: BoutonState) {
		super(props, context);
		this.state = {disabled: this.props.disabled, load: false};
	}

	public render() {

		return <button className={classNames({
			'but': true,
			'but-primary': this.props.primary,
			'load': this.props.load,
			'disabled': this.state.disabled || this.props.disabled
		}, this.props.className)
		} type={this.props.submit ? 'submit' : 'button'} onClick={e => {
			this.props.onClick(e, this);
			if (this.props.onClickDisable) {
				this.setState({disabled: true});
			}
			if (this.props.onClickLoad) {
				this.setState({load: true});
			}
		}} disabled={this.state.disabled || this.props.disabled}>{this.props.value}</button>
	}

}