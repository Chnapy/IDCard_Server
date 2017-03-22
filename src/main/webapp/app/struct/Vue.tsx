
import * as React from 'react';
import {Controleur} from 'Controleur';
import {AlertLevel} from 'items/Alert';

export interface VueProps<C extends Controleur<any, any>> {
	controleur: C,
	onSwitch: any,
	onAlert: any
}

export abstract class Vue<A extends VueProps<any>, B> extends React.Component<A, B> {

	public constructor(props?: A, context?: B) {
		super(props, context);
	}
	
	public switchPage(page: any) {
		if (this.props.onSwitch) {
			this.props.onSwitch(page);
		}
	}

	public addAlert(level: AlertLevel, title: string, content: string, code?: number): void {
		if (this.props.onAlert) {
			this.props.onAlert(level, title, content, code);
		}
	}

}