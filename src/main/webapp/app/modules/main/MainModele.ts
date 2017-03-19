/* 
 * 
 * 
 * 
 */
 
 import {Modele} from 'struct/Modele';

/**
 * MainModele
 * 
 */
export class MainModele extends Modele {
	
	public isConnected(): boolean {
		return GLOBALS.user.connected;
	}

}
