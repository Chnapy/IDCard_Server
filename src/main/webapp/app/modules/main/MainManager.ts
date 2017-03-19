
import * as ReactDOM from 'react-dom';
import {Vue} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainModele} from 'MainModele';
import {MainVue} from 'MainVue';

export class MainManager extends Controleur<MainModele, MainVue> {
	
	public constructor() {
		super(new MainModele(), new MainVue());
	}
	
	public start(): void {
		if (this.modele.isConnected()) {
			
		} else {
			
		}
		
		this.vue.applyVue(this);
	}
	
}