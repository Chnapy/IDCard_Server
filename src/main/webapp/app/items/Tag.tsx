
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as classNames from 'classnames';

export interface TagProps {
	id?: number,
	class?: string,
	value?: string,
	title?: string,
	icon?: JSX.Element,
	onhover?: boolean,
	extendhover?: boolean,
	clickable?: boolean,
	deletable?: boolean,
	reduce?: boolean,
	tooltip?: boolean,
	tooltip_placement?: string,
	onclick?: (e: React.MouseEvent, tag: Tag) => void,
	ondelete?: (e: React.MouseEvent, tag: Tag) => void
}

export interface TagState {
}

export class Tag extends React.Component<TagProps, TagState> {

	public constructor(props: TagProps) {
		super(props);
	}
	
	public componentDidMount() {
		let e:HTMLElement = ReactDOM.findDOMNode(this) as HTMLElement;
		$(e).tooltip();
	}

	public render() {
		return <span className={classNames('tag', this.props.class, {
			'clickable': this.props.clickable,
			'deletable': this.props.deletable,
			'reduce': this.props.reduce,
			'onhover': this.props.onhover,
			'extendhover': this.props.extendhover
		})} title={this.props.title} onClick={e => {
			if (this.props.onclick) {
				this.props.onclick(e, this);
			}
		}} data-toggle={this.props.tooltip ? 'tooltip' : ''} data-placement={this.props.tooltip_placement}>
			{this.props.icon}
			<span className='tag-text'>{this.props.value}</span>
			<span className='delete mini-but glyphicon glyphicon-remove' onClick={e => {
				if (this.props.ondelete) {
					this.props.ondelete(e, this);
				}
			}}></span>
		</span>;
	}

}