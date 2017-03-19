
import * as React from 'react';
import {Vue} from 'struct/Vue';
import {Controleur} from 'struct/Controleur';

export interface InputProps {
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