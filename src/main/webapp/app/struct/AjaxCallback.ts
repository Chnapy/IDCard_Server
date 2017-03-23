
import {MainManager} from 'modules/main/MainManager';
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
	proprietes: BlocProprieteProps[]
}

export interface Data {
	success: boolean,
	code: number,
	message: string,
	content: Donnees
}

export class AjaxCallback {

	private readonly manager: MainManager;
	private readonly cb: Callbacks;

	public constructor(manager: MainManager, cb: Callbacks) {
		this.manager = manager;
		this.cb = cb;
	}

	public onSuccess(data: Data): void {
		if (data.success) {
			if (this.cb.success) {
				this.cb.success(data);
			}
		} else {
			this.manager.showAlertFromCode(data.code)
			if (this.cb.error) {
				this.cb.error(data);
			}
		}
	}

	public onFail(): void {
		this.manager.showAlertFromCode(100)
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