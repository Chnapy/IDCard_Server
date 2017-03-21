
import * as React from 'react';
import {Vue, VueProps} from 'struct/Vue';
import {MainManager} from 'modules/main/MainManager';
import {Bouton} from 'items/Bouton';
import {AlertLevel} from 'items/Alert';

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

export class StartForm extends Vue<VueProps<MainManager>, StartFormState> {

	private static readonly MAIL_REGEX: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	private form: HTMLFormElement;

	public constructor(props?: VueProps<MainManager>, context?: StartFormState) {
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

		//		console.debug(this.state);
//		this.addAlert(AlertLevel.Error, "TESTTITLEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "TESTCONTENTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		this.addAlert(AlertLevel.Primary, "TESTTITLEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "TESTCONTENTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		this.addAlert(AlertLevel.Normal, "TESTTITLEaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "TESTCONTENTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
			this.props.controleur.connexion(this.state.ip_pseudo as string, this.state.ip_mail as string, this.state.ip_mdp as string, this.state.isMail as boolean,
				{success: () => this.setState({load: false})});
		} else {
			this.props.controleur.inscription(this.state.ip_pseudo as string, this.state.ip_mail as string, this.state.ip_mdp as string);
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
				<input type="text" className="field" id="ip_insc" name="ip_insc"
					value={this.state.ip_pseudo}
					onChange={e => this.handlePseudo(e)}
					minLength={Const.LENGTH.PSEUDO.min}
					maxLength={Const.LENGTH.PSEUDO.max}
					required />
			</div>;
		} else {
			ret = <div className="form-group">
				<label htmlFor="ip_insc">Email</label>
				<input type="email" className="field" id="ip_insc" name="ip_insc"
					value={this.state.ip_mail}
					onChange={e => this.handleMail(e)}
					minLength={Const.LENGTH.MAIL.min}
					maxLength={Const.LENGTH.MAIL.max}
					required />
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
					<input type={this.state.isMail ? 'email' : 'text'} className="field" id="ip_pseudo" name="ip_pseudo"
						value={this.state.ip_pseudoOrMail}
						onChange={e => this.handlePseudoOrMail(e)}
						minLength={this.state.isMail ? Const.LENGTH.MAIL.min : Const.LENGTH.PSEUDO.min}
						maxLength={this.state.isMail ? Const.LENGTH.MAIL.max : Const.LENGTH.PSEUDO.max}
						required />
				</div>
				<div className="form-group">
					<label htmlFor="ip_mdp">Mot de passe</label>
					<input type="password" className="field" id="ip_mdp" name="ip_mdp"
						value={this.state.ip_mdp}
						onChange={e => this.handleMdp(e)}
						minLength={Const.LENGTH.MDP.min}
						maxLength={Const.LENGTH.MDP.max}
						required />
				</div>

				{inscription}

				<div className="form-group">
					<Bouton value="Inscrivez-vous" className={"d-block"} primary={this.state.type === BoutonType.Inscription}
						submit={this.state.type === BoutonType.Inscription} onClick={(e: any, bouton: Bouton) => this.onClick(e, bouton, BoutonType.Inscription)}
						disabled={this.state.load}
						onClickLoad={this.state.type === BoutonType.Inscription} />
				</div>
				<div className="form-group">
					<Bouton value="Connectez-vous" className={"d-block"} primary={this.state.type === BoutonType.Connexion}
						submit={this.state.type === BoutonType.Connexion} onClick={(e: any, bouton: Bouton) => this.onClick(e, bouton, BoutonType.Connexion)}
						disabled={this.state.load}
						onClickLoad={this.state.load && (this.state.type === BoutonType.Connexion)} />
					<div className="text-center"><a href="">Vous avez oublié vos identifiants ?</a></div>
				</div>
			</form>

		</div>
	}

}
