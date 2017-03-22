
class RequireAll {

	private static LOADED: boolean = false;

	public static loadAll() {
		if (RequireAll.LOADED) {
			throw new Error();
		}

		requirejs.config({
			baseUrl: 'libs',
			paths: {
				jquery: 'jquerylib/jquery-3.1.1.min',
				bootstrap: 'bootstraplib/js/bootstrap.min',
				react: 'react/' + (Const.DEBUG ? 'react' : 'react.min'),
				"react-dom": 'react/' + (Const.DEBUG ? 'react-dom' : 'react-dom.min'),
				classnames: 'react/classnames'
			},
			shim: {
				bootstrap: {
					deps: ['jquery']
				},
				"react-dom": {
					deps: ['react']
				},
				classnames: {
					deps: ['react', 'react-dom']
				}
			}
		});

		requirejs(['jquery', 'bootstrap', 'react', 'react-dom', 'classnames'], function () {

			require(['Main'], function (mod: any) {
				RequireAll.LOADED = true;
				console.log('Chargement des fichiers terminé');
				mod.Main.main();
			});

		});
	}

	private constructor() {}

}

declare var GLOBALS: {titre_main: string, user: any, page: string};

window.onload = function () {
	RequireAll.loadAll();
};