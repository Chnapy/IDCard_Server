
import * as React from 'react';
import * as classNames from 'classnames';

export interface TagProps {
	id?: string,
	class?: string,
	value?: string,
	clickable?: boolean,
	deletable?: boolean,
	onclick?: React.EventHandler<any>,
	ondelete?: React.EventHandler<any>
}

export interface TagState {
}

export class Tag extends React.Component<TagProps, TagState> {

	public constructor(props: TagProps) {
		super(props);
	}

	public render() {
		return <span id={this.props.id} className={classNames('tag', this.props.class, {
			'clickable': this.props.clickable,
			'deletable': this.props.deletable
		})} onClick={e => {
			if (this.props.onclick) {
				this.props.onclick(e);
			}
		}} title={this.props.value}>
			{this.props.value}
			<span className='delete mini-but glyphicon glyphicon-remove' onClick={e => {
				if (this.props.ondelete) {
					this.props.ondelete(e);
				}
			}}></span>
		</span>;
	}

}