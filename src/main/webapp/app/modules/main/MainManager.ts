
import {Controleur} from 'struct/Controleur';
import {MainModele} from 'MainModele';
import {MainVue} from 'MainVue';

export class MainManager extends Controleur {
	
	public constructor() {
		super(new MainModele(), new MainVue());
	}
	
	public applyVue(): void {
		this.vue.applyVue();
		
	}
	
}