
import {MainManager} from 'modules/main/MainManager';

export class Main {

	public static main(): void {
		console.debug("Main go !");
		
		var manager: MainManager = new MainManager();
	}

	private constructor() {}

}