
import * as React from 'react';
import * as classNames from 'classnames';
import {AjaxEnum} from 'struct/AjaxCallback';

export interface AjaxNotifProps {
	value: string,
	etat: AjaxEnum
}

export class AjaxNotif extends React.Component<AjaxNotifProps, undefined> {
	
	public constructor(props: AjaxNotifProps) {
		super(props);
	}

	public render() {

		return <span className={classNames('ajax-bloc', {
			'hide': this.props.etat == AjaxEnum.None,
			'load': this.props.etat == AjaxEnum.Load,
			'success': this.props.etat == AjaxEnum.Success,
			'error': this.props.etat == AjaxEnum.Error,
		})}>
			{this.props.value}
			<span className='spinner-box'>
				<span className={classNames('icon', 'l-white', {
					'spinner visible': this.props.etat == AjaxEnum.Load,
					'glyphicon glyphicon-ok': this.props.etat == AjaxEnum.Success,
					'glyphicon glyphicon-remove': this.props.etat == AjaxEnum.Error,
				})}></span>
			</span>
		</span>;
	}

}