
import * as React from 'react';
import * as ReactDOM from 'react-dom';
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
	onHide: any
}

export interface AlertState {
//	display: boolean
}

export class Alert extends React.Component<AlertProps, AlertState> {

	public constructor(props?: AlertProps, context?: AlertState) {
		super(props, context);
		this.hide = this.hide.bind(this, this.props.onHide);
	}
	
	public hide(): void {
		this.props.onHide();
	}

	public render(): any {

		return <div className={classNames('myalert', {
			'primary': this.props.level === AlertLevel.Primary,
			'error': this.props.level === AlertLevel.Error
		})}>
			<span className='myalert-close' onClick={this.hide}></span>
			<div className='myalert-title'>{this.props.title}</div>
			<div className='myalert-content'>{this.props.content}</div>
		</div>
	}

}