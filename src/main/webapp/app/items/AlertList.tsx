
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as classNames from 'classnames';
import {Alert, AlertLevel} from 'items/Alert';

interface AlertListProps {
	alerts: AlertData[]
}

interface AlertListState {
	open: boolean,
	length?: number
}

export interface AlertData {
	key: number,
	level: AlertLevel,
	title: string,
	content: string,
	code: number,
	time: string,
	hide: boolean
}

export class AlertList extends React.Component<AlertListProps, AlertListState> {

	private alerts: AlertData[];

	public constructor(props?: AlertListProps, context?: AlertListState) {
		super(props, context);
		this.alerts = [];
		this.state = {open: false, length: 0};
		this.onOver = this.onOver.bind(this, this.state);
		this.clean = this.clean.bind(this, this.alerts);
	}

	public componentWillReceiveProps?(nextProps: AlertListProps, nextContext: any): void {
		this.alerts = nextProps.alerts;
		this.check();
	}

	public componentDidMount() {
		document.addEventListener('click', this.handleClickOutside.bind(this), true);
	}

	public componentWillUnmount() {
		document.removeEventListener('click', this.handleClickOutside.bind(this), true);
	}

	public handleClickOutside(e: any) {
		const domNode = ReactDOM.findDOMNode(this);

		if ((!domNode || !domNode.contains(e.target))) {
			this.setState({
				open: false
			});
		}
	}

	private open(length: number): void {
		this.setState({open: length > 0, length: length});
	}

	public check(): void {
		this.alerts = this.alerts.filter(a => !a.hide);
		this.open(this.alerts.length);
		this.forceUpdate();
	}
	
	public clean() {
		this.alerts.forEach(a => a.hide = true);
		this.check();
	}

	public onOver() {
		if (!this.state.open && this.state.length > 0) {
			this.setState({open: true});
		}
	}

	public render(): any {

		let alerts = this.alerts.map(a =>
			<Alert key={a.key} level={a.level} title={a.title} content={a.content} code={a.code} time={a.time} onHide={() => {a.hide = true; this.check()}} />);

		return <div id='alertList' className={classNames('dark',{
			'open': this.state.open,
			'possede': this.state.length > 0
		})} onMouseOver={this.onOver}>
			<span className='tiroir mini-but' onClick={e => this.setState({open: false})}><span className='glyphicon glyphicon-remove'></span></span>
			<span className='cleaner mini-but' onClick={this.clean}><span className='glyphicon glyphicon-erase'></span></span>
			{alerts}
		</div>
	}

}