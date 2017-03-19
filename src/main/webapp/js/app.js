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
Const.TITRE_MAIN = 'ID Card';
Const.LENGTH = {
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
define("struct/Vue", ["require", "exports", "react"], function (require, exports, React) {
    "use strict";
    var Vue = (function (_super) {
        __extends(Vue, _super);
        function Vue(props, context) {
            return _super.call(this, props, context) || this;
        }
        return Vue;
    }(React.Component));
    exports.Vue = Vue;
});
define("struct/Modele", ["require", "exports"], function (require, exports) {
    "use strict";
    var Modele = (function () {
        function Modele() {
        }
        return Modele;
    }());
    exports.Modele = Modele;
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
define("items/Header", ["require", "exports", "react", "struct/Vue"], function (require, exports, React, Vue_1) {
    "use strict";
    var Header = (function (_super) {
        __extends(Header, _super);
        function Header() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        Header.prototype.render = function () {
            var nav = !this.props.user.connected ? null :
                React.createElement("nav", { className: "header-content container" },
                    React.createElement("span", { className: "logo nav-item" }, Const.TITRE_MAIN),
                    React.createElement("span", { className: "nav-item" + this.props.page === 'configuration' ? 'active' : '' }, "Configuration"),
                    React.createElement("span", { className: "nav-item" + this.props.page === 'sessions' ? 'active' : '' }, "Sessions"));
            return React.createElement("header", { className: "header" }, nav);
        };
        return Header;
    }(Vue_1.Vue));
    exports.Header = Header;
});
define("items/Input", ["require", "exports", "react", "struct/Vue"], function (require, exports, React, Vue_2) {
    "use strict";
    var Input = (function (_super) {
        __extends(Input, _super);
        function Input() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        Input.prototype.render = function () {
            return React.createElement("input", { type: this.props.type, required: this.props.required });
        };
        return Input;
    }(Vue_2.Vue));
    exports.Input = Input;
});
define("items/StartForm", ["require", "exports", "react", "struct/Vue"], function (require, exports, React, Vue_3) {
    "use strict";
    var Bouton;
    (function (Bouton) {
        Bouton[Bouton["Inscription"] = 0] = "Inscription";
        Bouton[Bouton["Connexion"] = 1] = "Connexion";
    })(Bouton || (Bouton = {}));
    var StartForm = (function (_super) {
        __extends(StartForm, _super);
        function StartForm(props) {
            var _this = _super.call(this, props) || this;
            _this.state = { type: Bouton.Connexion, ip_pseudoOrMail: '', isMail: false };
            _this.handleInputChange = _this.handleInputChange.bind(_this);
            return _this;
        }
        StartForm.prototype.onClick = function (but) {
            this.setState({ bouton: but });
        };
        StartForm.prototype.onSubmit = function (e) {
            e.preventDefault();
            if (this.state.bouton !== this.state.type) {
                this.setState({ type: this.state.bouton });
            }
        };
        StartForm.prototype.handleInputChange = function (e) {
            var target = e.target;
            var value = target.type === 'checkbox' ? target.checked : target.value;
            var name = target.name;
            this.setState((_a = {},
                _a[name] = value,
                _a));
            var _a;
        };
        StartForm.prototype.handlePseudoOrMail = function (e) {
            var target = e.target;
            var value = target.value;
            this.setState({
                ip_pseudoOrMail: value
            });
            if (this.state.type === Bouton.Connexion) {
                this.setState({
                    isMail: this.isMail(this.state.ip_pseudoOrMail)
                });
            }
            else {
                if (this.state.isMail) {
                    this.setState({ ip_mail: this.state.ip_pseudoOrMail });
                }
                else {
                    this.setState({ ip_pseudo: this.state.ip_pseudoOrMail });
                }
            }
        };
        StartForm.prototype.isMail = function (text) {
            return StartForm.MAIL_REGEX.test(text);
        };
        StartForm.prototype.renderInscription = function () {
            var _this = this;
            var ret;
            if (this.state.isMail) {
                ret = React.createElement("div", { className: "form-group" },
                    React.createElement("label", { htmlFor: "ip_insc" }, "Pseudonyme"),
                    React.createElement("input", { type: "text", className: "field", id: "ip_insc", name: "ip_insc", onChange: function (e) { return _this.handleInputChange(e); }, minLength: Const.LENGTH.PSEUDO.min, maxLength: Const.LENGTH.PSEUDO.max, required: true }));
            }
            return ret;
        };
        StartForm.prototype.render = function () {
            var _this = this;
            var inscription = this.state.type === Bouton.Inscription ? this.renderInscription() : null;
            return React.createElement("div", { id: "form_conn_insc", className: "col-md-6 col-xs-12 bloc" },
                React.createElement("form", { action: "./", method: "POST", onSubmit: function (e) { return _this.onSubmit(e); }, className: this.state.type === Bouton.Connexion ? 'formConnexion' : 'formInscription' },
                    React.createElement("div", { className: "form-group " +
                            (this.state.ip_pseudoOrMail === '' ? '' :
                                (this.state.isMail ? 'ipMail' : 'ipPseudo')) },
                        React.createElement("label", { htmlFor: "ip_pseudo" },
                            React.createElement("span", { className: 'lbPseudo' }, "Pseudonyme"),
                            React.createElement("span", null, " ou "),
                            React.createElement("span", { className: 'lbMail' }, "email")),
                        React.createElement("input", { type: this.state.isMail ? 'email' : 'text', className: "field", id: "ip_pseudo", name: "ip_pseudo", onChange: function (e) { return _this.handlePseudoOrMail(e); }, minLength: Const.LENGTH.PSEUDO.min, maxLength: Const.LENGTH.PSEUDO.max, required: true })),
                    React.createElement("div", { className: "form-group" },
                        React.createElement("label", { htmlFor: "ip_mdp" }, "Mot de passe"),
                        React.createElement("input", { type: "password", className: "field", id: "ip_mdp", name: "ip_mdp", onChange: function (e) { return _this.handleInputChange(e); }, minLength: Const.LENGTH.MDP.min, maxLength: Const.LENGTH.MDP.max, required: true })),
                    inscription,
                    React.createElement("div", { className: "form-group" },
                        React.createElement("button", { className: "but but-primary d-block", type: "submit", onClick: function (e) { return _this.onClick(Bouton.Inscription); } }, "Inscrivez-vous")),
                    React.createElement("div", { className: "form-group" },
                        React.createElement("button", { className: "but d-block", type: "submit", onClick: function (e) { return _this.onClick(Bouton.Connexion); } }, "Connectez-vous"))));
        };
        return StartForm;
    }(Vue_3.Vue));
    StartForm.MAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    exports.StartForm = StartForm;
});
define("modules/main/MainModele", ["require", "exports", "struct/Modele"], function (require, exports, Modele_1) {
    "use strict";
    var MainModele = (function (_super) {
        __extends(MainModele, _super);
        function MainModele() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        MainModele.prototype.isConnected = function () {
            return GLOBALS.user.connected;
        };
        return MainModele;
    }(Modele_1.Modele));
    exports.MainModele = MainModele;
});
define("modules/main/MainVue", ["require", "exports", "react", "react-dom", "struct/Vue", "items/Header", "items/StartForm"], function (require, exports, React, ReactDOM, Vue_4, Header_1, StartForm_1) {
    "use strict";
    var MainVue = (function (_super) {
        __extends(MainVue, _super);
        function MainVue() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        MainVue.prototype.applyVue = function (controleur) {
            ReactDOM.render(React.createElement(MainVue, { controleur: controleur, user: GLOBALS.user, page: GLOBALS.page }), document.getElementById("react_container"));
        };
        MainVue.prototype.render = function () {
            var connected = this.props.user.connected;
            return (React.createElement("div", { id: "react_content", className: connected ? '' : 'no-header' },
                React.createElement(Header_1.Header, { user: this.props.user, page: this.props.page }),
                React.createElement("div", { id: "content", className: "body-content" },
                    React.createElement("div", { className: "bandeau dark" },
                        React.createElement("div", { className: "container" },
                            React.createElement("div", { className: "row", id: "main_title" },
                                React.createElement("h1", null, Const.TITRE_MAIN),
                                React.createElement("div", { className: "lead" }, "Inscrivez-vous une fois, connectez-vous partout.")))),
                    React.createElement("div", { className: "container" },
                        React.createElement("div", { className: "row" },
                            React.createElement("div", { id: "description", className: "col-md-6 col-xs-12" },
                                React.createElement("div", { className: "lead" },
                                    "Inscrivez-vous une fois,",
                                    React.createElement("br", null),
                                    "Connectez-vous partout."),
                                React.createElement("div", null, "Description du service, blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla")),
                            React.createElement(StartForm_1.StartForm, null)))),
                React.createElement("footer", { className: "footer" })));
        };
        return MainVue;
    }(Vue_4.Vue));
    exports.MainVue = MainVue;
});
define("modules/main/MainManager", ["require", "exports", "struct/Controleur", "modules/main/MainModele", "modules/main/MainVue"], function (require, exports, Controleur_1, MainModele_1, MainVue_1) {
    "use strict";
    var MainManager = (function (_super) {
        __extends(MainManager, _super);
        function MainManager() {
            return _super.call(this, new MainModele_1.MainModele(), new MainVue_1.MainVue()) || this;
        }
        MainManager.prototype.start = function () {
            if (this.modele.isConnected()) {
            }
            else {
            }
            this.vue.applyVue(this);
        };
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
            manager.start();
        };
        return Main;
    }());
    exports.Main = Main;
});
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
            require(['Main'], function (mod) {
                RequireAll.LOADED = true;
                console.log('Chargement des fichiers terminé');
                mod.Main.main();
            });
        });
    };
    return RequireAll;
}());
RequireAll.LOADED = false;
window.onload = function () {
    RequireAll.loadAll();
};
