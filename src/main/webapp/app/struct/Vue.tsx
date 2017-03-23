
import * as React from 'react';
import {Controleur} from 'Controleur';

export interface VueProps<C extends Controleur<any, any>> {
	controleur: C
}

export abstract class Vue<A extends VueProps<any>, B> extends React.Component<A, B> {

	public constructor(props?: A, context?: B) {
		super(props, context);
	}

}