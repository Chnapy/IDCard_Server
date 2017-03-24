
import * as React from 'react';
import * as classNames from 'classnames';

export enum AlertLevel {
	Primary = 1,
	Normal = 2,
	Error = 3
}

interface AlertProps {
	level: AlertLevel,
	hide: boolean,
	title: string,
	content: string,
	code: number,
	time: string,
	onHide: any
}

export interface AlertState {
	hide: boolean
}

export class Alert extends React.Component<AlertProps, AlertState> {

	public constructor(props: AlertProps, context?: AlertState) {
		super(props, context);
		this.state = {hide: props.hide};
		this.hide = this.hide.bind(this, this.props.onHide);
	}

	public componentDidMount() {
		if(this.props.hide || this.state.hide) {
			this.hide();
		}
	}

	private hide(): void {
		this.setState({hide: true});
		setTimeout(this.props.onHide as () => void, Const.TRANSITION_DURATION);
	}

	public render(): any {

		return <div className={classNames('myalert hidable', {
			'primary': this.props.level === AlertLevel.Primary,
			'error': this.props.level === AlertLevel.Error,
			'ishide': this.state.hide || this.props.hide
		})} data-time={this.props.time} data-code={this.props.code}>
			<span className='myalert-close' onClick={this.hide}></span>
			<div className='myalert-title'>{this.props.title}</div>
			<div className='myalert-content'>{this.props.content}</div>
		</div>
	}

}