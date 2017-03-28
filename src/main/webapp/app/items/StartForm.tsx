
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {AccueilManager} from 'modules/main/AccueilManager';
import {Bouton} from 'items/Bouton';
import {Input} from 'items/inputs/Input';

enum BoutonType {
	Inscription,
	Connexion
}

interface StartFormState {
	bouton?: BoutonType,
	ip_pseudoOrMail?: string,
	ip_pseudo?: string,
	ip_mail?: string,
	ip_mdp?: string,
	type?: BoutonType,
	isMail?: boolean,
	load?: boolean
}

export class StartForm extends Vue<VueProps<AccueilManager>, StartFormState> {

	private static readonly MAIL_REGEX: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	private form: HTMLFormElement;

	public constructor(props?: VueProps<AccueilManager>, context?: StartFormState) {
		super(props, context);
		this.state = {type: BoutonType.Connexion, ip_pseudoOrMail: '', ip_pseudo: '', ip_mail: '', ip_mdp: '', isMail: false, load: false};
	}

	private onClick(e: React.FormEvent, bouton: Bouton, but: BoutonType) {
		var valid;

		if (but !== this.state.type) {
			this.setState({type: but});
			valid = undefined;
			e.preventDefault();
		} else {
			let t = this;
			valid = function () {t.send(bouton)};
		}
		this.setState({bouton: but}, valid);
	}

	private onSubmit(e: React.FormEvent) {
		e.preventDefault();
	}

	private send(bouton: Bouton) {
		if (!this.form.checkValidity()) {
			bouton.setState({disabled: false, load: false});
			return;
		}

		this.setState({load: true});
		if (this.state.type === BoutonType.Connexion) {
			this.props.controleur.connexion(this.state.ip_pseudo as string, this.state.ip_mail as string, this.state.ip_mdp as string, this.state.isMail as boolean, this);
		} else {
			this.props.controleur.inscription(this.state.ip_pseudo as string, this.state.ip_mail as string, this.state.ip_mdp as string, this);
		}
	}

	private handleMdp(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.value.replace(/\s/g, '');

		this.setState({
			ip_mdp: value
		});
	}

	private handlePseudoOrMail(e: React.FormEvent): void {
		const target = e.target as HTMLInputElement;
		const value = target.value.replace(/\s/g, '');

		this.setState({
			ip_pseudoOrMail: value
		});
		if (this.state.type === BoutonType.Connexion) {
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
		const value = target.value.replace(/\s/g, '');

		this.setState({
			ip_pseudo: value
		});
	}

	private handleMail(e: React.FormEvent) {
		const target = e.target as HTMLInputElement;
		const value = target.value.replace(/\s/g, '').toLowerCase();

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
				<Input type="text" id="ip_insc" name="ip_insc"
					value={this.state.ip_pseudo}
					onchange={e => this.handlePseudo(e)}
					minlength={Const.LENGTH.PSEUDO.min}
					maxlength={Const.LENGTH.PSEUDO.max}
					required readonly={this.state.load} />
			</div>;
		} else {
			ret = <div className="form-group">
				<label htmlFor="ip_insc">Email</label>
				<Input type="email" id="ip_insc" name="ip_insc"
					value={this.state.ip_mail}
					onchange={e => this.handleMail(e)}
					minlength={Const.LENGTH.MAIL.min}
					maxlength={Const.LENGTH.MAIL.max}
					required readonly={this.state.load} />
			</div>;
		}


		return ret;
	}

	public render() {

		let inscription = this.state.type === BoutonType.Inscription ? this.renderInscription() : null;

		return <div id="form_conn_insc" className="col-md-6 col-xs-12 bloc">

			<form action="./" method="POST" ref={ref => this.form = ref} onSubmit={e => this.onSubmit(e)}
				className={this.state.type === BoutonType.Connexion ? 'formConnexion' : 'formInscription'}>

				<div className={"form-group " +
					(this.state.ip_pseudoOrMail === '' ? '' :
						(this.state.isMail ? 'ipMail' : 'ipPseudo')
					)
				}>

					<label htmlFor="ip_pseudo">
						<span className='lbPseudo'>Pseudonyme</span><span> ou </span><span className='lbMail'>email</span>
					</label>
					<Input type={this.state.isMail ? 'email' : 'text'} id="ip_pseudo"
						name="ip_pseudo" value={this.state.ip_pseudoOrMail} onchange={e => this.handlePseudoOrMail(e)}
						minlength={this.state.isMail ? Const.LENGTH.MAIL.min : Const.LENGTH.PSEUDO.min}
						maxlength={this.state.isMail ? Const.LENGTH.MAIL.max : Const.LENGTH.PSEUDO.max}
						required autofocus={!this.state.load} readonly={this.state.load} />
				</div>
				<div className="form-group">
					<label htmlFor="ip_mdp">Mot de passe</label>
					<Input type="password" id="ip_mdp" name="ip_mdp"
						value={this.state.ip_mdp}
						onchange={e => this.handleMdp(e)}
						minlength={Const.LENGTH.MDP.min}
						maxlength={Const.LENGTH.MDP.max}
						required readonly={this.state.load} />
				</div>

				{inscription}

				<div className="form-group">
					<Bouton value="Inscrivez-vous" className={"d-block"} primary={this.state.type === BoutonType.Inscription}
						submit={this.state.type === BoutonType.Inscription} onClick={(e: any, bouton: Bouton) => this.onClick(e, bouton, BoutonType.Inscription)}
						disabled={this.state.load} load={this.state.load && this.state.type === BoutonType.Inscription}
					/>
				</div>
				<div className="form-group">
					<Bouton value="Connectez-vous" className={"d-block"} primary={this.state.type === BoutonType.Connexion}
						submit={this.state.type === BoutonType.Connexion} onClick={(e: any, bouton: Bouton) => this.onClick(e, bouton, BoutonType.Connexion)}
						disabled={this.state.load} load={this.state.load && this.state.type === BoutonType.Connexion} />
					<div className="text-center"><a href="#" onClick={e => {
						e.preventDefault();
						this.props.controleur.popNonImplemente(e.target);
					}}>Vous avez oublié vos identifiants ?</a></div>
				</div>
			</form>

		</div>
	}

}
