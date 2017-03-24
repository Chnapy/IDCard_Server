
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as classNames from 'classnames';
import {Bouton} from 'items/Bouton';

export interface ConfirmBoxProps {
	display: boolean,
	titre: string,
	content: string,
	srcElement: HTMLElement,
	onConfirm: () => void,
	onHide?: () => void,
	fixed?: boolean
}

export interface ConfirmBoxState {
	hide: boolean,
	top: number,
	left: number
}

export class ConfirmBox extends React.Component<ConfirmBoxProps, ConfirmBoxState> {

	public constructor(props?: ConfirmBoxProps, context?: ConfirmBoxState) {
		super(props, context);
		
		let pos = $(this.props.srcElement).offset();
		let eWidth = $(this.props.srcElement).outerWidth();

		this.state = {hide: false, top: pos.top, left: pos.left + eWidth};
		this.hide = this.hide.bind(this, this.state.hide);
	}

	public componentDidMount() {
		document.addEventListener('click', this.confirmClickOutside.bind(this), true);
	}

	public componentWillUnmount() {
		document.removeEventListener('click', this.confirmClickOutside.bind(this), true);
	}

	public confirmClickOutside(e: React.MouseEvent) {
		try {
			const domNode = ReactDOM.findDOMNode(this);
			let n: Node = e.target as Node;
			if ((!domNode || !domNode.contains(n)) && !$(n).hasClass('declic') && !$(n).parents('.declic').length) {
				this.hide();
			}
		} catch (e) {
		}
	}

	private hide(): void {
		this.setState({hide: true, top: this.state.top, left: this.state.left});
		setTimeout(this.props.onHide as () => void, Const.TRANSITION_DURATION);
	}

	public render(): any {
		//		var right = ($(window).width() - ($(this.props.srcElement).offset().left + $(this.props.srcElement).outerWidth()));

		return <div className={classNames('bloc confirm hidable declic', {
			'fixed': this.props.fixed,
			'ishide': this.state.hide
		})} style={{
			top: this.state.top,
			left: this.state.left
		}}>
			<div className='confirm-head'>{this.props.titre}</div>
			<div className='confirm-body'>{this.props.content}</div>
			<div className='confirm-foot'>
				<Bouton value='Annuler' onClick={this.hide} />
				<Bouton ok value='' onClick={e => {this.hide(); this.props.onConfirm()}} primary />
			</div>
		</div>
	}

}