
import * as React from 'react';
import {Controleur} from 'Controleur';
import {AlertLevel} from 'items/Alert';

export interface VueProps<C extends Controleur<any, any>> {
	controleur: C,
	onAlert: any
}

export abstract class Vue<A extends VueProps<any>, B> extends React.Component<A, B> {

	public constructor(props?: A, context?: B) {
		super(props, context);
	}

	public addAlert(level: AlertLevel, title: string, content: string): void {
		console.log(level);
		if (this.props.onAlert) {
			this.props.onAlert(level, title, content);
		}
	}


}