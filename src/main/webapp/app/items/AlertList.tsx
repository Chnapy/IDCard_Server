
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
	hide: boolean,
	debug?: string
}

export class AlertList extends React.Component<AlertListProps, AlertListState> {

	private alerts: AlertData[];

	public constructor(props?: AlertListProps, context?: AlertListState) {
		super(props, context);
		this.alerts = [];
		this.state = {open: false, length: 0};
		this.onOver = this.onOver.bind(this, this.state);
		this.clean = this.clean.bind(this, this.alerts);
		this.check = this.check.bind(this, this.alerts);
	}

	public componentWillReceiveProps?(nextProps: AlertListProps, nextContext: any): void {
		if (this.alerts.length !== nextProps.alerts.length) {
			this.alerts = nextProps.alerts;
			this.check();
		}
	}

	public componentDidMount() {
		document.addEventListener('click', this.alertClickOutside.bind(this), true);
	}

	public componentWillUnmount() {
		document.removeEventListener('click', this.alertClickOutside.bind(this), true);
	}

	public alertClickOutside(e: React.MouseEvent) {
		const domNode = ReactDOM.findDOMNode(this);
		let n: Node = e.target as Node;
		if ((!domNode || !domNode.contains(n)) && !$(n).hasClass('declic') && !$(n).parents('.declic').length) {
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
	}

	public clean() {
		this.alerts.forEach(a => a.hide = true);
		this.forceUpdate();
		setTimeout(this.check, Const.TRANSITION_DURATION);
	}

	public onOver() {
		if (!this.state.open && this.state.length > 0) {
			this.setState({open: true});
		}
	}

	public render(): any {

		let alerts = this.alerts.map(a =>
			<Alert key={a.key} hide={a.hide} level={a.level} title={a.title} content={a.content} code={a.code} time={a.time} onHide={() => {a.hide = true; this.check()}} debug={a.debug} />);

		return <div id='alertList' className={classNames('dark declic', {
			'open': this.state.open,
			'possede': this.state.length > 0
		})} onMouseOver={this.onOver}>
			<span className='tiroir mini-but' onClick={e => this.setState({open: false})}><span className='glyphicon glyphicon-remove'></span></span>
			<span className='cleaner mini-but' onClick={this.clean}><span className='glyphicon glyphicon-erase'></span></span>
			{alerts}
		</div>
	}

}