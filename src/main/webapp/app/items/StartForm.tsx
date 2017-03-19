
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

	private form: HTMLFormElement;

	public constructor(props?: any) {
		super(props);
		this.state = {type: Bouton.Connexion, ip_pseudoOrMail: '', isMail: false};
	}

	private onClick(e: React.FormEvent, but: Bouton) {

		var valid;

		if (but !== this.state.type) {
			this.setState({type: but});
			valid = undefined;
			e.preventDefault();
		} else {
			valid = this.send;
		}
		this.setState({bouton: but}, valid);

//		console.debug(this.state);
	}

	private onSubmit(e: React.FormEvent) {
		e.preventDefault();
	}

	private send() {
//		console.debug(this.form.checkValidity());
		if (this.form.checkValidity()) {
			alert('Type: ' + this.state.type + '\n\
						PseudoOuMail: ' + this.state.ip_pseudoOrMail + '\n\
						Pseudo: ' + this.state.ip_pseudo + '\n\
						MDP: ' + this.state.ip_mdp + '\n\
						Mail: ' + this.state.ip_mail);
		}
	}

	private handleMdp(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.value;

		this.setState({
			ip_mdp: value
		});
	}

	private handlePseudoOrMail(e: React.FormEvent): void {
		const target = e.target as HTMLInputElement;
		const value = target.value;

		this.setState({
			ip_pseudoOrMail: value
		});
		if (this.state.type === Bouton.Connexion) {
			var ismail = this.isMail(value as string);
			var ippseudo = ismail ? '' : value;
			var ipmail = ismail ? value : '';
			this.setState({
				isMail: ismail,
				ip_pseudo: ippseudo,
				ip_mail: ipmail
			});
		} else {
			if (this.state.isMail) {
				this.setState({ip_mail: value})
			} else {
				this.setState({ip_pseudo: value})
			}
		}
	}

	private handlePseudo(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.value;

		this.setState({
			ip_pseudo: value
		});
	}

	private handleMail(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.value;

		this.setState({
			ip_mail: value
		});
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
					onChange={e => this.handlePseudo(e)}
					minLength={Const.LENGTH.PSEUDO.min}
					maxLength={Const.LENGTH.PSEUDO.max}
					required />
			</div>;
		} else {
			ret = <div className="form-group">
				<label htmlFor="ip_insc">Email</label>
				<input type="email" className="field" id="ip_insc" name="ip_insc"
					onChange={e => this.handleMail(e)}
					minLength={Const.LENGTH.MAIL.min}
					maxLength={Const.LENGTH.MAIL.max}
					required />
			</div>;
		}


		return ret;
	}

	public render() {

		let inscription = this.state.type === Bouton.Inscription ? this.renderInscription() : null;

		return <div id="form_conn_insc" className="col-md-6 col-xs-12 bloc">

			<form action="./" method="POST" ref={ref => this.form = ref} onSubmit={e => this.onSubmit(e)}
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
						minLength={this.state.isMail ? Const.LENGTH.MAIL.min : Const.LENGTH.PSEUDO.min}
						maxLength={this.state.isMail ? Const.LENGTH.MAIL.max : Const.LENGTH.PSEUDO.max}
						required />
				</div>
				<div className="form-group">
					<label htmlFor="ip_mdp">Mot de passe</label>
					<input type="password" className="field" id="ip_mdp" name="ip_mdp"
						onChange={e => this.handleMdp(e)}
						minLength={Const.LENGTH.MDP.min}
						maxLength={Const.LENGTH.MDP.max}
						required />
				</div>

				{inscription}

				<div className="form-group">
					<button className="but but-primary d-block" type="submit" onClick={e => this.onClick(e, Bouton.Inscription)}>Inscrivez-vous</button>
				</div>
				<div className="form-group">
					<button className="but d-block" type="submit" onClick={e => this.onClick(e, Bouton.Connexion)}>Connectez-vous</button>
					<div className="text-center"><a href="">Vous avez oublié vos identifiants ?</a></div>
				</div>
			</form>

		</div>
	}

}
