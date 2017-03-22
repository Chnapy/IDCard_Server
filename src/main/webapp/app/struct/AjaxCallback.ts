
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

export interface Data {
	success: boolean,
	code: number,
	message: string,
	content: {
		user: User
	}
}

export class AjaxCallback {

	private readonly cb: Callbacks;

	public constructor(cb?: Callbacks) {
		if (cb) {
			this.cb = cb;
		}
	}

	public onSuccess(data: Data): void {
		if (data.success) {
			if (this.cb.success) {
				this.cb.success(data);
			}
		} else {
			if (this.cb.error) {
				this.cb.error(data);
			}
		}
	}

	public onFail(): void {
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