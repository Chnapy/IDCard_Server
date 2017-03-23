
import * as React from 'react';
import * as classNames from 'classnames';

export enum AlertLevel {
	Primary = 1,
	Normal = 2,
	Error = 3
}

interface AlertProps {
	level: AlertLevel,
	title: string,
	content: string,
	code: number,
	time: string,
	onHide: any
}

export interface AlertState {
	display: boolean
}

export class Alert extends React.Component<AlertProps, AlertState> {

	public constructor(props?: AlertProps, context?: AlertState) {
		super(props, context);
		this.state = {display: true};
		this.hide = this.hide.bind(this, this.props.onHide);
	}

	public hide(): void {
		this.setState({display: false});
		setTimeout(() => this.props.onHide(), Const.TRANSITION_DURATION);
	}

	public render(): any {

		return <div className={classNames('myalert', {
			'primary': this.props.level === AlertLevel.Primary,
			'error': this.props.level === AlertLevel.Error,
			'no-display': !this.state.display
		})} data-time={this.props.time} data-code={this.props.code}>
			<span className='myalert-close' onClick={this.hide}></span>
			<div className='myalert-title'>{this.props.title}</div>
			<div className='myalert-content'>{this.props.content}</div>
		</div>
	}

}