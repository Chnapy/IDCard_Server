
import * as React from 'react';
import * as classNames from 'classnames';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';
import {Pages} from 'pages/Pages';
import {AlertLevel} from 'items/Alert';
import {Donnees} from 'struct/AjaxCallback';

export interface HeaderProps {
	mainManager: MainManager,
	donnees: Donnees,
	page: string,
	show: boolean
}

export class Header extends React.Component<HeaderProps, undefined> {

	private renderCompte() {
		return <span className="compte nav-item">
			<span className="nompte-pseudo">{this.props.donnees.user.pseudo}</span>
			<span className="deco mini-but" onClick={e => this.props.mainManager.deconnexion()}>
				<span className='glyphicon glyphicon-off'></span>
			</span>
		</span>;
	}

	private renderNav() {
		return <nav className="header-content container">
			<span className="logo nav-item">{Const.TITRE_MAIN}</span>
			<span className={classNames("nav-item", {
				'active': this.props.page === Pages.Configuration.NOM
			})}>{Pages.Configuration.NOM}</span>
			<span className={classNames("nav-item", {
				'active': this.props.page === 'sessions'
			})}>Sessions</span>
			{this.renderCompte()}
		</nav>;
	}

	public render() {

		console.log('HEADER');

		return <header className="header">
			{!this.props.show ? null : this.renderNav()}
		</header>;
	}

}
