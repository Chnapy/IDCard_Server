var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Const = (function () {
    function Const() {
    }
    return Const;
}());
Const.DEBUG = true;
define("struct/Modele", ["require", "exports"], function (require, exports) {
    "use strict";
    var Modele = (function () {
        function Modele() {
        }
        return Modele;
    }());
    exports.Modele = Modele;
});
define("struct/Vue", ["require", "exports"], function (require, exports) {
    "use strict";
    var Vue = (function () {
        function Vue() {
        }
        return Vue;
    }());
    exports.Vue = Vue;
});
define("struct/Controleur", ["require", "exports"], function (require, exports) {
    "use strict";
    var Controleur = (function () {
        function Controleur(modele, vue) {
            this.modele = modele;
            this.vue = vue;
        }
        Object.defineProperty(Controleur.prototype, "modele", {
            get: function () {
                return this._modele;
            },
            set: function (modele) {
                this._modele = modele;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(Controleur.prototype, "vue", {
            get: function () {
                return this._vue;
            },
            set: function (vue) {
                this._vue = vue;
            },
            enumerable: true,
            configurable: true
        });
        return Controleur;
    }());
    exports.Controleur = Controleur;
});
define("modules/main/MainModele", ["require", "exports", "struct/Modele"], function (require, exports, Modele_1) {
    "use strict";
    var MainModele = (function (_super) {
        __extends(MainModele, _super);
        function MainModele() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        return MainModele;
    }(Modele_1.Modele));
    exports.MainModele = MainModele;
});
define("modules/main/MainVue", ["require", "exports", "struct/Vue"], function (require, exports, Vue_1) {
    "use strict";
    var MainVue = (function (_super) {
        __extends(MainVue, _super);
        function MainVue() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        return MainVue;
    }(Vue_1.Vue));
    exports.MainVue = MainVue;
});
define("modules/main/MainManager", ["require", "exports", "struct/Controleur", "modules/main/MainModele", "modules/main/MainVue"], function (require, exports, Controleur_1, MainModele_1, MainVue_1) {
    "use strict";
    var MainManager = (function (_super) {
        __extends(MainManager, _super);
        function MainManager() {
            return _super.call(this, new MainModele_1.MainModele(), new MainVue_1.MainVue()) || this;
        }
        return MainManager;
    }(Controleur_1.Controleur));
    exports.MainManager = MainManager;
});
define("Main", ["require", "exports", "modules/main/MainManager"], function (require, exports, MainManager_1) {
    "use strict";
    var Main = (function () {
        function Main() {
        }
        Main.main = function () {
            console.debug("Main go !");
            var manager = new MainManager_1.MainManager();
        };
        return Main;
    }());
    exports.Main = Main;
});
define("RequireAll", ["require", "exports", "Main"], function (require, exports, Main_1) {
    "use strict";
    var RequireAll = (function () {
        function RequireAll() {
        }
        RequireAll.loadAll = function () {
            if (RequireAll.LOADED) {
                throw new Error();
            }
            requirejs.config({
                baseUrl: 'libs',
                paths: {
                    jquery: 'jquerylib/jquery-3.1.1.min',
                    bootstrap: 'bootstraplib/js/bootstrap.min',
                    react: 'react/' + (Const.DEBUG ? 'react' : 'react.min'),
                    react_dom: 'react/' + (Const.DEBUG ? 'react-dom' : 'react-dom.min'),
                    classnames: 'react/classnames'
                },
                shim: {
                    bootstrap: {
                        deps: ['jquery']
                    }
                }
            });
            requirejs(['jquery', 'bootstrap', 'react', 'react_dom', 'classnames'], function () {
                RequireAll.LOADED = true;
                console.log('Chargement des fichiers terminé');
                Main_1.Main.main();
            });
        };
        return RequireAll;
    }());
    RequireAll.LOADED = false;
    window.onload = function () {
        RequireAll.loadAll();
    };
});
