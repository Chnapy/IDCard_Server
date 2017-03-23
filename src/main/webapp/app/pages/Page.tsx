
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';
import {Donnees} from 'struct/AjaxCallback';

export interface PageProps extends VueProps<MainManager> {
	donnees: Donnees
}

export abstract class Page<P extends PageProps> extends Vue<P, undefined> {

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