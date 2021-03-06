
class Const {

	public static readonly DEBUG: boolean = true;

	public static readonly TITRE_MAIN: string = 'ID Card';

	public static readonly LENGTH = {
		PSEUDO: {
			min: 3,
			max: 32
		},
		MAIL: {
			min: 6,
			max: 64
		},
		MDP: {
			min: 6,
			max: 32
		}
	};

	public static readonly TRANSITION_DURATION: number = 500;

	public static readonly CONFIRMBOX_WIDTH: number = 300;

	public static readonly CODES: {
		[key: number]: {
			titre: string,
			message: string,
			crit: number
		}
	} = {

		/* crit: 0=info 1=warning 2=error_utilisateur 3=error_serveur 4=error_critique */


		//0xx = messages informatifs
		0: {
			titre: 'OK',
			message: '',
			crit: 0
		},
		1: {
			titre: 'Connexion réussie',
			message: 'Félicitation',
			crit: 0
		},
		2: {
			titre: 'Inscription réussie',
			message: 'Félicitation',
			crit: 0
		},

		//1xx = erreur client
		100: {
			titre: 'Erreur réseau',
			message: 'Le site n\'arrive pas à communiquer avec le serveur. Êtes-vous connecté à internet ?',
			crit: 2
		},
		101: {
			titre: 'Erreur client',
			message: 'Le client envoie de mauvaises informations au serveur. Merci d\'en informer l\'administrateur. \n\
						(ou alors vous bricolez avec de l\'Ajax :o)',
			crit: 2
		},

		//2xx = erreur serveur
		200: {
			titre: 'Erreur serveur',
			message: 'Une erreur serveur est intervenue. Merci d\'en informer l\'administrateur',
			crit: 3
		},

		//3xx = erreur serveur critique
		300: {
			titre: 'Erreur critique',
			message: 'Une erreur critique est intervenue. Les services risques de disfonctionner. Merci d\'en informer l\'administrateur',
			crit: 4
		},

		//4xx = erreur inconnue (code inconnu)
		400: {
			titre: 'Code erreur inconnu',
			message: 'Une requête au serveur a renvoyé un code erreur inconnu. Merci d\'en informer l\'administrateur',
			crit: 3
		},


		//6xx = erreur classiques
		600: {
			titre: 'Erreur entrée formulaire',
			message: 'Vérifiez vos entrées et réessayez',
			crit: 2
		},

		//61x = connexion
		610: {
			titre: 'Échec de la connexion',
			message: 'Vérifiez vos identifiants et réessayez',
			crit: 2
		},

		//62x = inscription
		620: {
			titre: 'Échec de l\'inscription',
			message: 'Vérifiez les informations rentrées et réessayez',
			crit: 2
		},

		621: {
			titre: 'Échec de l\'inscription',
			message: 'Un utilisateur avec le même pseudo ou mail existe déjà',
			crit: 2
		},

		630: {
			titre: 'Échec de la mise à jour du champ',
			message: 'Votre entrée ne correspond pas au format demandé',
			crit: 2
		},

		631: {
			titre: 'Échec de la suppression du site',
			message: '',
			crit: 2
		},

	};

	private constructor() {}

}