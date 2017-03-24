
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';
import {Donnees} from 'struct/AjaxCallback';

export interface PageProps<C extends Controleur<any, any>> extends VueProps<C> {
	donnees: Donnees
}

export abstract class Page<P extends PageProps<any>> extends Vue<P, undefined> {

	private readonly _nom: string;
	get nom(): string {
		return this._nom;
	}

	public constructor(props: P, nom: string) {
		super(props);
		this._nom = nom;
	}

	public abstract hasHeader(): boolean;

	public abstract renderBandeau(): any;

	public abstract render(): any;

}