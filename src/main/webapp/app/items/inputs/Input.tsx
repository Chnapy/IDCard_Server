
import * as React from 'react';
import * as classNames from 'classnames';

export interface InputProps {
	type: string,
	id?: string,
	class?: string,
	name?: string,
	value?: string,
	readonly?: boolean,
	required?: boolean,
	disabled?: boolean,
	minlength?: number,
	maxlength?: number,
	placeholder?: string,
	autofocus?: boolean,
	onchange?: React.EventHandler<any>,
	onenter?: (e: React.SyntheticEvent, input: Input) => void
	checkvalidation?: boolean
}

export interface InputState {
	value: string
}

export class Input extends React.Component<InputProps, InputState> {

	public constructor(props: InputProps) {
		super(props);
		this.state = {value: props.value as string};
	}

	public render() {

		let classes = classNames(this.props.class, 'field');

		return this.props.checkvalidation 
		? this.renderForm(classes, this.props.onenter) 
		: this.renderDiv(classes, this.props.onenter);
	}
	
	private renderDiv(classes: string, onenter?: (e: React.SyntheticEvent, input: Input) => void) {
		return <div className={classes}>
			{this.getInput(onenter)}
			{this.getFeedback()}
		</div>;
	}

	private renderForm(classes: string, onenter?: (e: React.SyntheticEvent, input: Input) => void) {
		return <form onSubmit={e => {
			console.debug("SUBMIT");
			e.preventDefault();
			if (onenter) {
			console.debug("ONENTER");
				onenter(e, this);
			}
		}} className={classes}>
			{this.getInput()}
			{this.getFeedback()}
		</form>;
	}
	
	private getFeedback() {
		return <span className='field-feedback form-control-feedback'><span className='glyphicon'></span></span>;
	}

	private getInput(onenter?: (e: React.SyntheticEvent, input: Input) => void) {
		return <input type={this.props.type} id={this.props.id} name={this.props.name}
			value={this.state.value} onChange={e => {
				this.setState({value: (e.target as HTMLInputElement).value});
				if (this.props.onchange) {
					this.props.onchange(e);
				}
			}} onKeyUp={e => {
				if (e.key === 'Enter' && onenter) {
					onenter(e, this);
				}
			}}
			readOnly={this.props.readonly} minLength={this.props.minlength} maxLength={this.props.maxlength}
			required={this.props.required} placeholder={this.props.placeholder} disabled={this.props.disabled}
			autoFocus={this.props.autofocus} />
	}

}