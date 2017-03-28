
import {Controleur} from 'struct/Controleur';
import {BlocProprieteProps} from 'items/BlocPropriete';

interface Callbacks {
	success?: any,
	error?: any,
	fail?: any,
	done?: any,
	always?: any
}

export interface User {

	connected: boolean,
	id_user: number;
	pseudo: string;
	mail: string;
	dateinscription: Date;
	datederniereconnexion: Date;
	nbrconnexion: number;

}

export interface Donnees {
	user: User,
	proprietes: BlocProprieteProps[],
	id_val?: number
}

export interface Data {
	success: boolean,
	code: number,
	debug: string,
	content: Donnees
}

export enum AjaxEnum {
	None, Load, Success, Error
}


export class AjaxCallback {

	private readonly manager: Controleur<any, any>;
	private readonly label: string;
	private readonly cb: Callbacks;

	public constructor(manager: Controleur<any, any>, label: string, cb: Callbacks) {
		this.manager = manager;
		this.label = label;
		this.cb = cb;
	}

	public onStart(): void {
		this.manager.startAjax(this.label);
	}

	public onSuccess(data: Data): void {
		if (data.success) {
			this.manager.endAjax(AjaxEnum.Success);
			if (this.cb.success) {
				this.cb.success(data);
			}
		} else {
			this.manager.endAjax(AjaxEnum.Error);
			this.manager.showAlertFromCode(data.code, data.debug);
			if (this.cb.error) {
				this.cb.error(data);
			}
		}
	}

	public onFail(): void {
		this.manager.endAjax(AjaxEnum.Error);
		this.manager.showAlertFromCode(100);
		if (this.cb.fail) {
			this.cb.fail();
		}
	}

	public onDone(data: Data): void {
		if (this.cb.done) {
			this.cb.done(data);
		}
	}

	public onAlways(data: Data): void {
		if (this.cb.always) {
			this.cb.always(data);
		}
	}

}