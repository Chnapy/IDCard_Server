
import {AjaxCallback, Donnees} from 'struct/AjaxCallback';

export abstract class Modele {

	private static _donnees: Donnees;
	get donnees(): Donnees {
		return Modele._donnees;
	}
	set donnees(donnees: Donnees) {
		Modele._donnees = donnees;
	}

	public constructor(donnees: Donnees) {
		this.donnees = donnees;
	}

	public ajaxPost(url: string, donnees: {}, ajaxc: AjaxCallback): void {
		$.post(url, donnees, (data) => ajaxc.onSuccess(data), 'json')
			.fail(() => ajaxc.onFail())
			.done((data) => ajaxc.onDone(data))
			.always((data) => ajaxc.onAlways(data));
	}

}