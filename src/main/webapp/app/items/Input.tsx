
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';
import {MainManager} from 'modules/main/MainManager';

export interface InputProps extends VueProps<MainManager> {
	type: string,
//	name: string,
//	value: string,
//	readonly: boolean,
	required: boolean,
//	disabled: boolean,
//	minlength: number,
//	maxlength: number,
//	placeholder: string
}

export class Input extends Vue<InputProps, undefined> {

	public render() {
		return <input type={this.props.type} required={this.props.required} />
	}

}