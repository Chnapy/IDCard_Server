
import * as React from 'react';
import * as classNames from 'classnames';

export interface TagProps {
	id?: number,
	class?: string,
	value?: string,
	onhover?: boolean,
	clickable?: boolean,
	deletable?: boolean,
	onclick?: (e: React.MouseEvent, tag: Tag) => void,
	ondelete?: (e: React.MouseEvent, tag: Tag) => void
}

export interface TagState {
}

export class Tag extends React.Component<TagProps, TagState> {

	public constructor(props: TagProps) {
		super(props);
	}

	public render() {
		return <span className={classNames('tag', this.props.class, {
			'clickable': this.props.clickable,
			'deletable': this.props.deletable,
			'onhover': this.props.onhover
		})} onClick={e => {
			if (this.props.onclick) {
				this.props.onclick(e, this);
			}
		}} title={this.props.value}>
			{this.props.value}
			<span className='delete mini-but glyphicon glyphicon-remove' onClick={e => {
				if (this.props.ondelete) {
					this.props.ondelete(e, this);
				}
			}}></span>
		</span>;
	}

}