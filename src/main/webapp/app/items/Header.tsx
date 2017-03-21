
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';

export interface HeaderProps extends VueProps<MainManager> {
	user: any,
	page: string
}

export class Header extends Vue<HeaderProps, undefined> {

	public render() {
		
		var nav = !this.props.user.connected ? null :
			<nav className="header-content container">
				<span className="logo nav-item">{Const.TITRE_MAIN}</span>
				<span className={"nav-item" + this.props.page === 'configuration' ? 'active' : ''}>Configuration</span>
				<span className={"nav-item" + this.props.page === 'sessions' ? 'active' : ''}>Sessions</span>
			</nav>;

		return <header className="header">
			{nav}
		</header>;
	}

}
