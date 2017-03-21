

interface Callbacks {
	success?: any,
	fail?: any,
	done?: any,
	always?: any
}

export class AjaxCallback {

	private readonly success: any;
	private readonly fail: any;
	private readonly done: any;
	private readonly always: any;

	public constructor(array?: Callbacks) {
		if (array) {
			this.success = array.success;
			this.fail = array.fail;
			this.done = array.done;
			this.always = array.always;
		}
	}

	public onSuccess(data: any): void {
		if (this.success) {
			this.success(data);
		}
	}

	public onFail(): void {
		if (this.fail) {
			this.fail();
		}
	}

	public onDone(data: any): void {
		if (this.done) {
			this.done(data);
		}
	}

	public onAlways(data: any): void {
		if (this.always) {
			this.always(data);
		}
	}

}