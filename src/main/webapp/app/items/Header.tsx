
import * as React from 'react';
import * as classNames from 'classnames';
import {MainManager} from 'modules/main/MainManager';
import {Pages} from 'pages/Pages';
import {Donnees} from 'struct/AjaxCallback';
import {AjaxEnum} from 'struct/AjaxCallback';
import {AjaxNotif} from 'items/AjaxNotif';

export interface HeaderProps {
	mainManager: MainManager,
	donnees: Donnees,
	page: string,
	show: boolean,
	nomAjax: string,
	etatAjax: AjaxEnum
}

export class Header extends React.Component<HeaderProps, undefined> {

	private renderRight() {
		return <span className="nav-right">
			<span className="nav-ajax nav-item">
				<AjaxNotif value={this.props.nomAjax} etat={this.props.etatAjax} />
			</span>
			<span className="compte nav-item">
				<span className="compte-pseudo" onClick={e => this.props.mainManager.popNonImplemente(e.target, true)}>{this.props.donnees.user.pseudo}</span>
				<span className="deco mini-but" onClick={e => this.props.mainManager.deconnexion(e.target)}>
					<span className='glyphicon glyphicon-off'></span>
				</span>
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
			})} onClick={e => this.props.mainManager.popNonImplemente(e.target, true)}>Sessions</span>
			
			{this.renderRight()}
		</nav>;
	}

	public render() {

		return <header className="header">
			{!this.props.show ? null : this.renderNav()}
		</header>;
	}

}
