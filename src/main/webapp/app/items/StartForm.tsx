
import * as React from 'react';
import {Vue} from 'struct/Vue';

enum Bouton {
	Inscription,
	Connexion
}

export interface StartFormState {
	bouton?: Bouton,
	ip_pseudoOrMail?: string,
	ip_pseudo?: string,
	ip_mail?: string,
	ip_mdp?: string,
	type?: Bouton,
	isMail?: boolean
}

export class StartForm extends Vue<any, StartFormState> {

	private static readonly MAIL_REGEX: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	public constructor(props?: any) {
		super(props);
		this.state = {type: Bouton.Connexion, ip_pseudoOrMail: '', isMail: false};
		this.handleInputChange = this.handleInputChange.bind(this);
	}

	private onClick(but: Bouton) {
		this.setState({bouton: but});
	}

	private onSubmit(e: React.FormEvent) {
		e.preventDefault();
		if (this.state.bouton !== this.state.type) {
			this.setState({type: this.state.bouton});
		}
	}

	private handleInputChange(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.type === 'checkbox' ? target.checked : target.value;
		const name = target.name;

		this.setState({
			[name]: value
		});
	}

	private handlePseudoOrMail(e: React.FormEvent): void {
		const target = e.target as HTMLInputElement;
		const value = target.value;

		this.setState({
			ip_pseudoOrMail: value
		});
		if (this.state.type === Bouton.Connexion) {
			this.setState({
				isMail: this.isMail(this.state.ip_pseudoOrMail as string)
			});
		} else {
			if (this.state.isMail) {
				this.setState({ip_mail: this.state.ip_pseudoOrMail})
			} else {
				this.setState({ip_pseudo: this.state.ip_pseudoOrMail})
			}
		}
	}

	private isMail(text: string): boolean {
		return StartForm.MAIL_REGEX.test(text);
	}

	private renderInscription() {
		let ret;
		
		if (this.state.isMail) {
			ret = <div className="form-group">
					<label htmlFor="ip_insc">Pseudonyme</label>
					<input type="text" className="field" id="ip_insc" name="ip_insc"
						onChange={e => this.handleInputChange(e)}
						minLength={Const.LENGTH.PSEUDO.min}
						maxLength={Const.LENGTH.PSEUDO.max}
						required />
				</div>;
		}
		
		
		return ret;
	}

	public render() {

		let inscription = this.state.type === Bouton.Inscription ? this.renderInscription() : null;

		return <div id="form_conn_insc" className="col-md-6 col-xs-12 bloc">

			<form action="./" method="POST" onSubmit={e => this.onSubmit(e)}
				className={this.state.type === Bouton.Connexion ? 'formConnexion' : 'formInscription'}>

				<div className={"form-group " +
					(this.state.ip_pseudoOrMail === '' ? '' :
						(this.state.isMail ? 'ipMail' : 'ipPseudo')
					)
				}>

					<label htmlFor="ip_pseudo">
						<span className='lbPseudo'>Pseudonyme</span><span> ou </span><span className='lbMail'>email</span>
					</label>
					<input type={this.state.isMail ? 'email' : 'text'} className="field" id="ip_pseudo" name="ip_pseudo"
						onChange={e => this.handlePseudoOrMail(e)}
						minLength={Const.LENGTH.PSEUDO.min}
						maxLength={Const.LENGTH.PSEUDO.max}
						required />
				</div>
				<div className="form-group">
					<label htmlFor="ip_mdp">Mot de passe</label>
					<input type="password" className="field" id="ip_mdp" name="ip_mdp"
						onChange={e => this.handleInputChange(e)}
						minLength={Const.LENGTH.MDP.min}
						maxLength={Const.LENGTH.MDP.max}
						required />
				</div>

				{inscription}

				<div className="form-group">
					<button className="but but-primary d-block" type="submit" onClick={e => this.onClick(Bouton.Inscription)}>Inscrivez-vous</button>
				</div>
				<div className="form-group">
					<button className="but d-block" type="submit" onClick={e => this.onClick(Bouton.Connexion)}>Connectez-vous</button>
				</div>
			</form>

		</div>
	}

}
