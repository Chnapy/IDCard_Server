
import {AjaxCallback} from 'struct/AjaxCallback';

export abstract class Modele {

	public ajaxPost(url: string, donnees: {}, ajaxc: AjaxCallback): void {
		$.post(url, donnees, (data) => ajaxc.onSuccess(data), 'json')
			.fail(() => ajaxc.onFail())
			.done((data) => ajaxc.onDone(data))
			.always((data) => ajaxc.onAlways(data));
	}

}