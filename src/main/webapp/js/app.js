var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var __assign = (this && this.__assign) || Object.assign || function(t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
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
Const.TRANSITION_DURATION = 500;
Const.CONFIRMBOX_WIDTH = 300;
Const.CODES = {
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
    200: {
        titre: 'Erreur serveur',
        message: 'Une erreur serveur est intervenue. Merci d\'en informer l\'administrateur',
        crit: 3
    },
    300: {
        titre: 'Erreur critique',
        message: 'Une erreur critique est intervenue. Les services risques de disfonctionner. Merci d\'en informer l\'administrateur',
        crit: 4
    },
    400: {
        titre: 'Code erreur inconnu',
        message: 'Une requête au serveur a renvoyé un code erreur inconnu. Merci d\'en informer l\'administrateur',
        crit: 3
    },
    600: {
        titre: 'Erreur entrée formulaire',
        message: 'Vérifiez vos entrées et réessayez',
        crit: 2
    },
    610: {
        titre: 'Échec de la connexion',
        message: 'Vérifiez vos identifiants et réessayez',
        crit: 2
    },
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
define("struct/Modele", ["require", "exports"], function (require, exports) {
    "use strict";
    var Modele = (function () {
        function Modele(donnees) {
            this.donnees = donnees;
        }
        Object.defineProperty(Modele.prototype, "donnees", {
            get: function () {
                return Modele._donnees;
            },
            set: function (donnees) {
                Modele._donnees = donnees;
            },
            enumerable: true,
            configurable: true
        });
        Modele.prototype.ajaxPost = function (url, donnees, ajaxc) {
            ajaxc.onStart();
            $.post(url, donnees, function (data) { return ajaxc.onSuccess(data); }, 'json')
                .fail(function () { return ajaxc.onFail(); })
                .done(function (data) { return ajaxc.onDone(data); })
                .always(function (data) { return ajaxc.onAlways(data); });
        };
        return Modele;
    }());
    exports.Modele = Modele;
});
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
define("modules/main/MainModele", ["require", "exports", "struct/Modele"], function (require, exports, Modele_1) {
    "use strict";
    var MainModele = (function (_super) {
        __extends(MainModele, _super);
        function MainModele(GLOBALS) {
            return _super.call(this, GLOBALS.donnees) || this;
        }
        MainModele.prototype.deconnexion = function (ajaxc) {
            this.ajaxPost('deconnexion', {}, ajaxc);
        };
        return MainModele;
    }(Modele_1.Modele));
    exports.MainModele = MainModele;
});
define("pages/Page", ["require", "exports", "struct/Vue"], function (require, exports, Vue_1) {
    "use strict";
    var Page = (function (_super) {
        __extends(Page, _super);
        function Page(props, nom) {
            var _this = _super.call(this, props) || this;
            _this._nom = nom;
            return _this;
        }
        Object.defineProperty(Page.prototype, "nom", {
            get: function () {
                return this._nom;
            },
            enumerable: true,
            configurable: true
        });
        Page.prototype.componentDidMount = function () {
        };
        return Page;
    }(Vue_1.Vue));
    exports.Page = Page;
});
define("items/Bouton", ["require", "exports", "react", "classnames"], function (require, exports, React, classNames) {
    "use strict";
    var Bouton = (function (_super) {
        __extends(Bouton, _super);
        function Bouton(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.state = { disabled: _this.props.disabled, load: _this.props.load };
            return _this;
        }
        Bouton.prototype.render = function () {
            var _this = this;
            return React.createElement("button", { className: classNames('but', {
                    'primary': this.props.primary,
                    'add': this.props.add,
                    'delete error': this.props.delete,
                    'load': this.props.load,
                    'disabled': this.state.disabled || this.props.disabled
                }, this.props.className), type: this.props.submit ? 'submit' : 'button', onClick: function (e) {
                    return _this.props.onClick(e, _this);
                }, disabled: this.state.disabled || this.props.disabled },
                this.props.value,
                this.props.ok ? React.createElement("span", { className: 'glyphicon glyphicon-ok' }) : '');
        };
        return Bouton;
    }(React.Component));
    exports.Bouton = Bouton;
    var BoutonAdd = (function (_super) {
        __extends(BoutonAdd, _super);
        function BoutonAdd() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        BoutonAdd.prototype.render = function () {
            return React.createElement(Bouton, __assign({ value: '', add: true }, this.props));
        };
        return BoutonAdd;
    }(React.Component));
    exports.BoutonAdd = BoutonAdd;
    var BoutonDelete = (function (_super) {
        __extends(BoutonDelete, _super);
        function BoutonDelete() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        BoutonDelete.prototype.render = function () {
            return React.createElement(Bouton, __assign({ value: '', delete: true }, this.props));
        };
        return BoutonDelete;
    }(React.Component));
    exports.BoutonDelete = BoutonDelete;
});
define("items/inputs/Input", ["require", "exports", "react", "classnames"], function (require, exports, React, classNames) {
    "use strict";
    var Input = (function (_super) {
        __extends(Input, _super);
        function Input(props) {
            var _this = _super.call(this, props) || this;
            _this.state = { value: props.value };
            return _this;
        }
        Input.prototype.render = function () {
            var classes = classNames(this.props.class, 'field');
            return this.props.checkvalidation
                ? this.renderForm(classes, this.props.onenter)
                : this.renderDiv(classes, this.props.onenter);
        };
        Input.prototype.renderDiv = function (classes, onenter) {
            return React.createElement("div", { className: classes },
                this.getInput(onenter),
                this.getFeedback());
        };
        Input.prototype.renderForm = function (classes, onenter) {
            var _this = this;
            return React.createElement("form", { onSubmit: function (e) {
                    console.debug("SUBMIT");
                    e.preventDefault();
                    if (onenter) {
                        console.debug("ONENTER");
                        onenter(e, _this);
                    }
                }, className: classes },
                this.getInput(),
                this.getFeedback());
        };
        Input.prototype.getFeedback = function () {
            return React.createElement("span", { className: 'field-feedback form-control-feedback' },
                React.createElement("span", { className: 'glyphicon' }));
        };
        Input.prototype.getInput = function (onenter) {
            var _this = this;
            return React.createElement("input", { type: this.props.type, id: this.props.id, name: this.props.name, value: this.state.value, onChange: function (e) {
                    _this.setState({ value: e.target.value });
                    if (_this.props.onchange) {
                        _this.props.onchange(e);
                    }
                }, onKeyUp: function (e) {
                    if (e.key === 'Enter' && onenter) {
                        onenter(e, _this);
                    }
                }, readOnly: this.props.readonly, minLength: this.props.minlength, maxLength: this.props.maxlength, required: this.props.required, placeholder: this.props.placeholder, disabled: this.props.disabled, autoFocus: this.props.autofocus });
        };
        return Input;
    }(React.Component));
    exports.Input = Input;
});
define("items/StartForm", ["require", "exports", "react", "struct/Vue", "items/Bouton", "items/inputs/Input"], function (require, exports, React, Vue_2, Bouton_1, Input_1) {
    "use strict";
    var BoutonType;
    (function (BoutonType) {
        BoutonType[BoutonType["Inscription"] = 0] = "Inscription";
        BoutonType[BoutonType["Connexion"] = 1] = "Connexion";
    })(BoutonType || (BoutonType = {}));
    var StartForm = (function (_super) {
        __extends(StartForm, _super);
        function StartForm(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.state = { type: BoutonType.Connexion, ip_pseudoOrMail: '', ip_pseudo: '', ip_mail: '', ip_mdp: '', isMail: false, load: false };
            return _this;
        }
        StartForm.prototype.onClick = function (e, bouton, but) {
            var valid;
            if (but !== this.state.type) {
                this.setState({ type: but });
                valid = undefined;
                e.preventDefault();
            }
            else {
                var t_1 = this;
                valid = function () { t_1.send(bouton); };
            }
            this.setState({ bouton: but }, valid);
        };
        StartForm.prototype.onSubmit = function (e) {
            e.preventDefault();
        };
        StartForm.prototype.send = function (bouton) {
            if (!this.form.checkValidity()) {
                bouton.setState({ disabled: false, load: false });
                return;
            }
            this.setState({ load: true });
            if (this.state.type === BoutonType.Connexion) {
                this.props.controleur.connexion(this.state.ip_pseudo, this.state.ip_mail, this.state.ip_mdp, this.state.isMail, this);
            }
            else {
                this.props.controleur.inscription(this.state.ip_pseudo, this.state.ip_mail, this.state.ip_mdp, this);
            }
        };
        StartForm.prototype.handleMdp = function (e) {
            var target = e.target;
            var value = target.value.replace(/\s/g, '');
            this.setState({
                ip_mdp: value
            });
        };
        StartForm.prototype.handlePseudoOrMail = function (e) {
            var target = e.target;
            var value = target.value.replace(/\s/g, '');
            this.setState({
                ip_pseudoOrMail: value
            });
            if (this.state.type === BoutonType.Connexion) {
                var ismail = this.isMail(value);
                var ippseudo = ismail ? '' : value;
                var ipmail = ismail ? value : '';
                this.setState({
                    isMail: ismail,
                    ip_pseudo: ippseudo,
                    ip_mail: ipmail
                });
            }
            else {
                if (this.state.isMail) {
                    this.setState({ ip_mail: value });
                }
                else {
                    this.setState({ ip_pseudo: value });
                }
            }
        };
        StartForm.prototype.handlePseudo = function (e) {
            var target = e.target;
            var value = target.value.replace(/\s/g, '');
            this.setState({
                ip_pseudo: value
            });
        };
        StartForm.prototype.handleMail = function (e) {
            var target = e.target;
            var value = target.value.replace(/\s/g, '').toLowerCase();
            this.setState({
                ip_mail: value
            });
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
                    React.createElement(Input_1.Input, { type: "text", id: "ip_insc", name: "ip_insc", value: this.state.ip_pseudo, onchange: function (e) { return _this.handlePseudo(e); }, minlength: Const.LENGTH.PSEUDO.min, maxlength: Const.LENGTH.PSEUDO.max, required: true, readonly: this.state.load }));
            }
            else {
                ret = React.createElement("div", { className: "form-group" },
                    React.createElement("label", { htmlFor: "ip_insc" }, "Email"),
                    React.createElement(Input_1.Input, { type: "email", id: "ip_insc", name: "ip_insc", value: this.state.ip_mail, onchange: function (e) { return _this.handleMail(e); }, minlength: Const.LENGTH.MAIL.min, maxlength: Const.LENGTH.MAIL.max, required: true, readonly: this.state.load }));
            }
            return ret;
        };
        StartForm.prototype.render = function () {
            var _this = this;
            var inscription = this.state.type === BoutonType.Inscription ? this.renderInscription() : null;
            return React.createElement("div", { id: "form_conn_insc", className: "col-md-6 col-xs-12 bloc" },
                React.createElement("form", { action: "./", method: "POST", ref: function (ref) { return _this.form = ref; }, onSubmit: function (e) { return _this.onSubmit(e); }, className: this.state.type === BoutonType.Connexion ? 'formConnexion' : 'formInscription' },
                    React.createElement("div", { className: "form-group " +
                            (this.state.ip_pseudoOrMail === '' ? '' :
                                (this.state.isMail ? 'ipMail' : 'ipPseudo')) },
                        React.createElement("label", { htmlFor: "ip_pseudo" },
                            React.createElement("span", { className: 'lbPseudo' }, "Pseudonyme"),
                            React.createElement("span", null, " ou "),
                            React.createElement("span", { className: 'lbMail' }, "email")),
                        React.createElement(Input_1.Input, { type: this.state.isMail ? 'email' : 'text', id: "ip_pseudo", name: "ip_pseudo", value: this.state.ip_pseudoOrMail, onchange: function (e) { return _this.handlePseudoOrMail(e); }, minlength: this.state.isMail ? Const.LENGTH.MAIL.min : Const.LENGTH.PSEUDO.min, maxlength: this.state.isMail ? Const.LENGTH.MAIL.max : Const.LENGTH.PSEUDO.max, required: true, autofocus: !this.state.load, readonly: this.state.load })),
                    React.createElement("div", { className: "form-group" },
                        React.createElement("label", { htmlFor: "ip_mdp" }, "Mot de passe"),
                        React.createElement(Input_1.Input, { type: "password", id: "ip_mdp", name: "ip_mdp", value: this.state.ip_mdp, onchange: function (e) { return _this.handleMdp(e); }, minlength: Const.LENGTH.MDP.min, maxlength: Const.LENGTH.MDP.max, required: true, readonly: this.state.load })),
                    inscription,
                    React.createElement("div", { className: "form-group" },
                        React.createElement(Bouton_1.Bouton, { value: "Inscrivez-vous", className: "d-block", primary: this.state.type === BoutonType.Inscription, submit: this.state.type === BoutonType.Inscription, onClick: function (e, bouton) { return _this.onClick(e, bouton, BoutonType.Inscription); }, disabled: this.state.load, load: this.state.load && this.state.type === BoutonType.Inscription })),
                    React.createElement("div", { className: "form-group" },
                        React.createElement(Bouton_1.Bouton, { value: "Connectez-vous", className: "d-block", primary: this.state.type === BoutonType.Connexion, submit: this.state.type === BoutonType.Connexion, onClick: function (e, bouton) { return _this.onClick(e, bouton, BoutonType.Connexion); }, disabled: this.state.load, load: this.state.load && this.state.type === BoutonType.Connexion }),
                        React.createElement("div", { className: "text-center" },
                            React.createElement("a", { href: "#", onClick: function (e) {
                                    e.preventDefault();
                                    _this.props.controleur.popNonImplemente(e.target);
                                } }, "Vous avez oubli\u00E9 vos identifiants ?")))));
        };
        return StartForm;
    }(Vue_2.Vue));
    StartForm.MAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    exports.StartForm = StartForm;
});
define("pages/Accueil", ["require", "exports", "react", "pages/Page", "items/StartForm"], function (require, exports, React, Page_1, StartForm_1) {
    "use strict";
    var Accueil = (function (_super) {
        __extends(Accueil, _super);
        function Accueil(props) {
            return _super.call(this, props, Accueil.NOM) || this;
        }
        Accueil.prototype.hasHeader = function () {
            return false;
        };
        Accueil.prototype.renderBandeau = function () {
            return React.createElement("div", { className: "bandeau dark" },
                React.createElement("div", { className: "container" },
                    React.createElement("div", { className: "row", id: "main_title" },
                        React.createElement("h1", null, Const.TITRE_MAIN),
                        React.createElement("div", { className: "lead" }, "Inscrivez-vous une fois, connectez-vous partout."))));
        };
        Accueil.prototype.render = function () {
            return React.createElement("div", { className: "container page-content" },
                React.createElement("div", { className: "row" },
                    React.createElement("div", { id: "description", className: "col-md-6 col-xs-12" },
                        React.createElement("div", { className: "lead" },
                            "Inscrivez-vous une fois,",
                            React.createElement("br", null),
                            "Connectez-vous partout."),
                        React.createElement("div", null, "Description du service, blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla" + " " + "blablabla blablabla blablabla blablabla blablabla blablabla blablabla blablabla")),
                    React.createElement(StartForm_1.StartForm, { controleur: this.props.controleur })));
        };
        return Accueil;
    }(Page_1.Page));
    Accueil.NOM = 'Accueil';
    exports.Accueil = Accueil;
});
define("items/Tag", ["require", "exports", "react", "react-dom", "classnames"], function (require, exports, React, ReactDOM, classNames) {
    "use strict";
    var Tag = (function (_super) {
        __extends(Tag, _super);
        function Tag(props) {
            return _super.call(this, props) || this;
        }
        Tag.prototype.componentDidMount = function () {
            var e = ReactDOM.findDOMNode(this);
            $(e).tooltip();
        };
        Tag.prototype.render = function () {
            var _this = this;
            return React.createElement("span", { className: classNames('tag', this.props.class, {
                    'clickable': this.props.clickable,
                    'deletable': this.props.deletable,
                    'reduce': this.props.reduce,
                    'onhover': this.props.onhover,
                    'extendhover': this.props.extendhover
                }), title: this.props.title, onClick: function (e) {
                    if (_this.props.onclick) {
                        _this.props.onclick(e, _this);
                    }
                }, "data-toggle": this.props.tooltip ? 'tooltip' : '', "data-placement": this.props.tooltip_placement },
                this.props.icon,
                React.createElement("span", { className: 'tag-text' }, this.props.value),
                React.createElement("span", { className: 'delete mini-but glyphicon glyphicon-remove', onClick: function (e) {
                        if (_this.props.ondelete) {
                            _this.props.ondelete(e, _this);
                        }
                    } }));
        };
        return Tag;
    }(React.Component));
    exports.Tag = Tag;
});
define("modules/main/ConfigModele", ["require", "exports", "struct/Modele"], function (require, exports, Modele_2) {
    "use strict";
    var ConfigModele = (function (_super) {
        __extends(ConfigModele, _super);
        function ConfigModele(donnees) {
            return _super.call(this, donnees) || this;
        }
        ConfigModele.prototype.updateValeur = function (key_val, val, ajaxc) {
            this.ajaxPost('configuration', { m: 'update_val', id_val: key_val, val: val }, ajaxc);
        };
        ConfigModele.prototype.addValeur = function (key_prop, val, ajaxc) {
            this.ajaxPost('configuration', { m: 'add_val', id_prop: key_prop, val: val }, ajaxc);
        };
        ConfigModele.prototype.removeValeur = function (key_val, ajaxc) {
            this.ajaxPost('configuration', { m: 'remove_val', id_val: key_val }, ajaxc);
        };
        ConfigModele.prototype.removeSite = function (key_val, key_site, ajaxc) {
            this.ajaxPost('configuration', { m: 'remove_site', id_val: key_val, id_site: key_site }, ajaxc);
        };
        return ConfigModele;
    }(Modele_2.Modele));
    exports.ConfigModele = ConfigModele;
});
define("modules/main/ConfigManager", ["require", "exports", "struct/Controleur", "modules/main/ConfigModele", "struct/AjaxCallback"], function (require, exports, Controleur_1, ConfigModele_1, AjaxCallback_1) {
    "use strict";
    var ConfigManager = (function (_super) {
        __extends(ConfigManager, _super);
        function ConfigManager(donnees, mainManager) {
            return _super.call(this, new ConfigModele_1.ConfigModele(donnees), mainManager) || this;
        }
        ConfigManager.prototype.updateValeur = function (key_val, val, onsuccess) {
            this.modele.updateValeur(key_val, val, new AjaxCallback_1.AjaxCallback(this, 'Mise à jour de valeur', {
                success: function (data) { return onsuccess(); }
            }));
        };
        ConfigManager.prototype.addValeur = function (key_prop, val, blocProp) {
            var _this = this;
            this.modele.addValeur(key_prop, val, new AjaxCallback_1.AjaxCallback(this, 'Ajout de valeur', {
                success: function (data) {
                    var vp = {
                        key: data.content.id_val, valeur: val, sites: [], principal: false, prive: false, publique: false
                    };
                    _this.modele.donnees.proprietes.filter(function (p) { return p.key == key_prop; })[0].valeurs.push(vp);
                    blocProp.setState({ newval: false });
                }
            }));
        };
        ConfigManager.prototype.removeValeur = function (key_prop, key_val, blocProp, element) {
            var _this = this;
            function remover(manager) {
                manager.modele.removeValeur(key_val, new AjaxCallback_1.AjaxCallback(manager, 'Suppression de valeur', {
                    success: function (data) {
                        var valeurs_filtered = manager.modele.donnees.proprietes.filter(function (p) { return p.key == key_prop; })[0].valeurs.filter(function (v) { return v.key !== key_val; });
                        manager.modele.donnees.proprietes.filter(function (p) { return p.key == key_prop; })[0].valeurs = valeurs_filtered;
                        blocProp.setState({ valeurs: valeurs_filtered });
                    }
                }));
            }
            this.popConfirm('Suppression d\'une valeur', 'Voulez-vous vraiment supprimer cette valeur ?', function () { return remover(_this); }, element);
        };
        ConfigManager.prototype.removeSite = function (key_prop, key_val, key_site, ligne, element) {
            var _this = this;
            function remover(manager) {
                manager.modele.removeSite(key_val, key_site, new AjaxCallback_1.AjaxCallback(manager, 'Suppression de site', {
                    success: function (data) {
                        var sites = manager.modele.donnees
                            .proprietes.filter(function (p) { return p.key === key_prop; })[0]
                            .valeurs.filter(function (v) { return v.key === key_val; })[0]
                            .sites;
                        var sites_filtered = sites.filter(function (s) { return s.key !== key_site; });
                        manager.modele.donnees
                            .proprietes.filter(function (p) { return p.key === key_prop; })[0]
                            .valeurs.filter(function (v) { return v.key === key_val; })[0]
                            .sites = sites_filtered;
                        ligne.setState({ sites: sites_filtered });
                    }
                }));
            }
            this.popConfirm('Suppression d\'un site', 'Voulez-vous vraiment supprimer ce site ?', function () { return remover(_this); }, element);
        };
        return ConfigManager;
    }(Controleur_1.Controleur));
    exports.ConfigManager = ConfigManager;
});
define("items/LigneValeur", ["require", "exports", "react", "classnames", "struct/Vue", "items/inputs/Input", "items/Bouton", "items/Tag"], function (require, exports, React, classNames, Vue_3, Input_2, Bouton_2, Tag_1) {
    "use strict";
    var LigneValeur = (function (_super) {
        __extends(LigneValeur, _super);
        function LigneValeur(props) {
            var _this = _super.call(this, props) || this;
            _this.state = { sites: props.sites ? props.sites : [] };
            _this.onEnter = _this.onEnter.bind(_this);
            _this.onRemoveSite = _this.onRemoveSite.bind(_this);
            return _this;
        }
        LigneValeur.prototype.onEnter = function (e, input) {
            var n = $(e.target).find('input')[0];
            var f = function () { n.blur(); };
            this.props.controleur.updateValeur(this.props.id, input.state.value, f);
        };
        LigneValeur.prototype.onRemoveSite = function (e, tag) {
            this.props.controleur.removeSite(this.props.id_prop, this.props.id, tag.props.id, this, e.target);
        };
        LigneValeur.prototype.render = function () {
            var _this = this;
            return React.createElement("div", { className: classNames("box-line", "row", {
                    "principal": this.props.principal,
                    "modifiable": this.props.modifiable,
                    "supprimable": this.props.supprimable,
                    "public": this.props.publique,
                    "prive": this.props.prive
                }) },
                React.createElement("div", { className: 'box-line-ip col-xs-6', onSubmit: function (e) { return e.preventDefault(); } },
                    React.createElement(Input_2.Input, { type: this.props.type, value: this.props.valeur, readonly: !this.props.modifiable, onenter: this.props.onenter ? this.props.onenter : this.onEnter, minlength: this.props.taillemin, maxlength: this.props.taillemax, required: true, checkvalidation: true, autofocus: this.props.autofocus })),
                React.createElement("div", { className: "box-line-visib col-xs-6" }, this.state.sites.map(function (s) {
                    return React.createElement(Tag_1.Tag, { key: s.key, id: s.key, value: s.site, title: s.site, reduce: true, tooltip: true, tooltip_placement: 'top', icon: React.createElement("img", { className: 'icon', src: 'http://www.google.com/s2/favicons?domain_url=' + s.site }), deletable: true, onhover: true, ondelete: _this.onRemoveSite });
                })),
                this.props.supprimable ? React.createElement(Bouton_2.BoutonDelete, { className: "but-fh", onClick: function (e) {
                        return _this.props.controleur.removeValeur(_this.props.id_prop, _this.props.id, _this.props.blocProp, e.target);
                    } }) : '');
        };
        return LigneValeur;
    }(Vue_3.Vue));
    exports.LigneValeur = LigneValeur;
    var LigneAdd = (function (_super) {
        __extends(LigneAdd, _super);
        function LigneAdd() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        LigneAdd.prototype.render = function () {
            return React.createElement("div", { className: "box-line row" },
                React.createElement(Bouton_2.BoutonAdd, __assign({}, this.props)));
        };
        return LigneAdd;
    }(React.Component));
    exports.LigneAdd = LigneAdd;
    var NewLigneValeur = (function (_super) {
        __extends(NewLigneValeur, _super);
        function NewLigneValeur(props) {
            var _this = _super.call(this, props) || this;
            _this.onEnter = _this.onEnter.bind(_this);
            return _this;
        }
        NewLigneValeur.prototype.onEnter = function (e, input) {
            this.props.controleur.addValeur(this.props.id_prop, input.state.value, this.props.blocProp);
        };
        NewLigneValeur.prototype.render = function () {
            return React.createElement(LigneValeur, __assign({ modifiable: true, autofocus: true, onenter: this.onEnter }, this.props));
        };
        return NewLigneValeur;
    }(Vue_3.Vue));
    exports.NewLigneValeur = NewLigneValeur;
});
define("items/BlocPropriete", ["require", "exports", "react", "struct/Vue", "items/LigneValeur"], function (require, exports, React, Vue_4, LigneValeur_1) {
    "use strict";
    var BlocPropriete = (function (_super) {
        __extends(BlocPropriete, _super);
        function BlocPropriete(props) {
            var _this = _super.call(this, props) || this;
            _this.state = { valeurs: props.valeurs };
            return _this;
        }
        BlocPropriete.prototype.render = function () {
            var _this = this;
            var valeurs = this.state.valeurs;
            return React.createElement("div", { className: "box col-md-6" },
                React.createElement("div", { className: "box-content bloc col-md-12" },
                    React.createElement("div", { className: "container-fluid" },
                        React.createElement("div", { className: "box-head row" },
                            React.createElement("span", { className: "box-title" }, this.props.nom),
                            React.createElement("div", { className: "box-head-right" },
                                React.createElement("span", { className: "tag typeStr" }, this.props.typeStr))),
                        React.createElement("div", { className: "box-body row" },
                            React.createElement("div", { className: "container-fluid" },
                                valeurs.map(function (v) {
                                    return React.createElement(LigneValeur_1.LigneValeur, { key: v.key, id: v.key, id_prop: _this.props.id, controleur: _this.props.controleur, valeur: v.valeur, type: _this.props.type, principal: v.principal, publique: v.publique, prive: v.prive, sites: v.sites, modifiable: _this.props.modifiable, supprimable: _this.props.supprimable && !v.principal && valeurs.length > _this.props.nbrmin, taillemin: _this.props.taillemin, taillemax: _this.props.taillemax, blocProp: _this });
                                }),
                                this.renderFoot(valeurs))))));
        };
        BlocPropriete.prototype.renderFoot = function (valeurs) {
            var _this = this;
            if (valeurs.length >= this.props.nbrmax) {
                return undefined;
            }
            return this.state.newval
                ? React.createElement(LigneValeur_1.NewLigneValeur, { controleur: this.props.controleur, id_prop: this.props.id, type: this.props.type, taillemin: this.props.taillemin, taillemax: this.props.taillemax, blocProp: this })
                : React.createElement(LigneValeur_1.LigneAdd, { className: "but-fh", onClick: function () { return _this.setState({ newval: true }); } });
        };
        return BlocPropriete;
    }(Vue_4.Vue));
    exports.BlocPropriete = BlocPropriete;
});
define("pages/Configuration", ["require", "exports", "react", "pages/Page", "items/BlocPropriete"], function (require, exports, React, Page_2, BlocPropriete_1) {
    "use strict";
    var Configuration = (function (_super) {
        __extends(Configuration, _super);
        function Configuration(props) {
            return _super.call(this, props, Configuration.NOM) || this;
        }
        Configuration.prototype.hasHeader = function () {
            return true;
        };
        Configuration.prototype.renderBandeau = function () {
            return React.createElement("div", { className: "bandeau dark" },
                React.createElement("div", { className: "container" },
                    React.createElement("div", { className: "row" },
                        React.createElement("h1", null, this.nom),
                        React.createElement("div", { className: "lead" }, "Configurer vos propri\u00E9t\u00E9s, leurs valeurs et visibilit\u00E9s."))));
        };
        Configuration.prototype.render = function () {
            var _this = this;
            return React.createElement("div", { id: "list-box", className: "page-content container" },
                React.createElement("div", { className: "row" }, this.props.donnees.proprietes.map(function (p) {
                    return React.createElement(BlocPropriete_1.BlocPropriete, __assign({ id: p.key, controleur: _this.props.controleur }, p));
                })));
        };
        return Configuration;
    }(Page_2.Page));
    Configuration.NOM = 'Configuration';
    exports.Configuration = Configuration;
});
define("pages/Pages", ["require", "exports", "pages/Accueil", "pages/Configuration"], function (require, exports, A, C) {
    "use strict";
    var Pages;
    (function (Pages) {
        Pages.Accueil = A.Accueil;
        Pages.Configuration = C.Configuration;
    })(Pages = exports.Pages || (exports.Pages = {}));
});
define("modules/main/AccueilModele", ["require", "exports", "struct/Modele"], function (require, exports, Modele_3) {
    "use strict";
    var AccueilModele = (function (_super) {
        __extends(AccueilModele, _super);
        function AccueilModele(donnees) {
            return _super.call(this, donnees) || this;
        }
        AccueilModele.prototype.connexion = function (pseudo, mail, mdp, isMail, ajaxc) {
            this.ajaxPost('connexion', { pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail }, ajaxc);
        };
        AccueilModele.prototype.inscription = function (pseudo, mail, mdp, ajaxc) {
            this.ajaxPost('inscription', { pseudo: pseudo, mail: mail, mdp: mdp }, ajaxc);
        };
        return AccueilModele;
    }(Modele_3.Modele));
    exports.AccueilModele = AccueilModele;
});
define("modules/main/AccueilManager", ["require", "exports", "struct/Controleur", "struct/AjaxCallback", "pages/Pages", "modules/main/AccueilModele"], function (require, exports, Controleur_2, AjaxCallback_2, Pages_1, AccueilModele_1) {
    "use strict";
    var AccueilManager = (function (_super) {
        __extends(AccueilManager, _super);
        function AccueilManager(donnees, mainManager) {
            return _super.call(this, new AccueilModele_1.AccueilModele(donnees), mainManager) || this;
        }
        AccueilManager.prototype.getConnexionSuccess = function () {
            var _this = this;
            return function (data) {
                _this.modele.donnees = data.content;
                _this.showAlertFromCode(1);
                _this.mainManager.vue.setState({ donnees: _this.modele.donnees });
                _this.mainManager.vue.mainSwitchPage(Pages_1.Pages.Configuration);
            };
        };
        AccueilManager.prototype.connexion = function (pseudo, mail, mdp, isMail, vue) {
            var stopLoad = function () { return vue.setState({ load: false }); };
            this.modele.connexion(pseudo, mail, mdp, isMail, new AjaxCallback_2.AjaxCallback(this, 'Connexion', {
                success: this.getConnexionSuccess(),
                error: stopLoad,
                fail: stopLoad
            }));
        };
        AccueilManager.prototype.inscription = function (pseudo, mail, mdp, vue) {
            var _this = this;
            var stopLoad = function () { return vue.setState({ load: false }); };
            this.modele.inscription(pseudo, mail, mdp, new AjaxCallback_2.AjaxCallback(this, 'Inscription', {
                success: function (data) {
                    _this.showAlertFromCode(2);
                    _this.getConnexionSuccess()(data);
                },
                error: stopLoad,
                fail: stopLoad
            }));
        };
        return AccueilManager;
    }(Controleur_2.Controleur));
    exports.AccueilManager = AccueilManager;
});
define("items/Header", ["require", "exports", "react", "classnames", "pages/Pages", "items/AjaxNotif"], function (require, exports, React, classNames, Pages_2, AjaxNotif_1) {
    "use strict";
    var Header = (function (_super) {
        __extends(Header, _super);
        function Header() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        Header.prototype.renderRight = function () {
            var _this = this;
            return React.createElement("span", { className: "nav-right" },
                React.createElement("span", { className: "nav-ajax nav-item" },
                    React.createElement(AjaxNotif_1.AjaxNotif, { value: this.props.nomAjax, etat: this.props.etatAjax })),
                React.createElement("span", { className: "compte nav-item" },
                    React.createElement("span", { className: "compte-pseudo", onClick: function (e) { return _this.props.mainManager.popNonImplemente(e.target, true); } }, this.props.donnees.user.pseudo),
                    React.createElement("span", { className: "deco mini-but", onClick: function (e) { return _this.props.mainManager.deconnexion(e.target); } },
                        React.createElement("span", { className: 'glyphicon glyphicon-off' }))));
        };
        Header.prototype.renderNav = function () {
            var _this = this;
            return React.createElement("nav", { className: "header-content container" },
                React.createElement("span", { className: "logo nav-item" }, Const.TITRE_MAIN),
                React.createElement("span", { className: classNames("nav-item", {
                        'active': this.props.page === Pages_2.Pages.Configuration.NOM
                    }) }, Pages_2.Pages.Configuration.NOM),
                React.createElement("span", { className: classNames("nav-item", {
                        'active': this.props.page === 'sessions'
                    }), onClick: function (e) { return _this.props.mainManager.popNonImplemente(e.target, true); } }, "Sessions"),
                this.renderRight());
        };
        Header.prototype.render = function () {
            return React.createElement("header", { className: "header" }, !this.props.show ? null : this.renderNav());
        };
        return Header;
    }(React.Component));
    exports.Header = Header;
});
define("items/Alert", ["require", "exports", "react", "classnames"], function (require, exports, React, classNames) {
    "use strict";
    var AlertLevel;
    (function (AlertLevel) {
        AlertLevel[AlertLevel["Primary"] = 1] = "Primary";
        AlertLevel[AlertLevel["Normal"] = 2] = "Normal";
        AlertLevel[AlertLevel["Error"] = 3] = "Error";
    })(AlertLevel = exports.AlertLevel || (exports.AlertLevel = {}));
    var Alert = (function (_super) {
        __extends(Alert, _super);
        function Alert(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.state = { hide: props.hide };
            _this.hide = _this.hide.bind(_this, _this.props.onHide);
            return _this;
        }
        Alert.prototype.componentDidMount = function () {
            if (this.props.hide || this.state.hide) {
                this.hide();
            }
        };
        Alert.prototype.hide = function () {
            this.setState({ hide: true });
            setTimeout(this.props.onHide, Const.TRANSITION_DURATION);
        };
        Alert.prototype.render = function () {
            return React.createElement("div", { className: classNames('myalert hidable', {
                    'primary': this.props.level === AlertLevel.Primary,
                    'error': this.props.level === AlertLevel.Error,
                    'ishide': this.state.hide || this.props.hide
                }), "data-time": this.props.time, "data-code": this.props.code },
                React.createElement("span", { className: 'myalert-close', onClick: this.hide }),
                React.createElement("div", { className: 'myalert-title' }, this.props.title),
                React.createElement("div", { className: 'myalert-content' }, this.props.content),
                this.props.debug ? React.createElement("div", { className: 'myalert-debug' }, this.props.debug) : '');
        };
        return Alert;
    }(React.Component));
    exports.Alert = Alert;
});
define("items/ConfirmBox", ["require", "exports", "react", "react-dom", "classnames", "items/Bouton"], function (require, exports, React, ReactDOM, classNames, Bouton_3) {
    "use strict";
    var ConfirmBox = (function (_super) {
        __extends(ConfirmBox, _super);
        function ConfirmBox(props, context) {
            var _this = _super.call(this, props, context) || this;
            var pos = $(_this.props.srcElement).offset();
            var eWidth = $(_this.props.srcElement).outerWidth();
            var top = pos.top - (_this.props.fixed ? $(window).scrollTop() : 0);
            var left = pos.left + eWidth;
            var right = 'auto';
            if (left + Const.CONFIRMBOX_WIDTH > window.innerWidth) {
                left = 'auto';
                right = 0;
            }
            _this.state = { hide: false, top: top, left: left, right: right };
            _this.hide = _this.hide.bind(_this, _this.state.hide);
            return _this;
        }
        ConfirmBox.prototype.componentDidMount = function () {
            document.addEventListener('click', this.confirmClickOutside.bind(this), true);
        };
        ConfirmBox.prototype.componentWillUnmount = function () {
            document.removeEventListener('click', this.confirmClickOutside.bind(this), true);
        };
        ConfirmBox.prototype.confirmClickOutside = function (e) {
            try {
                var domNode = ReactDOM.findDOMNode(this);
                var n = e.target;
                if ((!domNode || !domNode.contains(n)) && !$(n).hasClass('declic') && !$(n).parents('.declic').length) {
                    this.hide();
                }
            }
            catch (e) {
            }
        };
        ConfirmBox.prototype.hide = function () {
            this.setState({ hide: true, top: this.state.top, left: this.state.left, right: this.state.right });
            setTimeout(this.props.onHide, Const.TRANSITION_DURATION);
        };
        ConfirmBox.prototype.render = function () {
            var _this = this;
            return React.createElement("div", { className: classNames('bloc confirm hidable declic', {
                    'fixed': this.props.fixed,
                    'ishide': this.state.hide,
                    'info': this.props.info
                }), style: {
                    top: this.state.top,
                    left: this.state.left,
                    right: this.state.right
                } },
                React.createElement("div", { className: 'confirm-head' }, this.props.titre),
                React.createElement("div", { className: 'confirm-body' }, this.props.content),
                this.props.info ? '' :
                    React.createElement("div", { className: 'confirm-foot' },
                        React.createElement(Bouton_3.Bouton, { value: 'Annuler', onClick: this.hide }),
                        React.createElement(Bouton_3.Bouton, { ok: true, value: '', onClick: function (e) { _this.hide(); _this.props.onConfirm(); }, primary: true })));
        };
        return ConfirmBox;
    }(React.Component));
    exports.ConfirmBox = ConfirmBox;
    var InfoBox = (function (_super) {
        __extends(InfoBox, _super);
        function InfoBox() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        InfoBox.prototype.render = function () {
            return React.createElement(ConfirmBox, __assign({ info: true, onConfirm: function () { } }, this.props));
        };
        return InfoBox;
    }(React.Component));
    exports.InfoBox = InfoBox;
});
define("items/AlertList", ["require", "exports", "react", "react-dom", "classnames", "items/Alert"], function (require, exports, React, ReactDOM, classNames, Alert_1) {
    "use strict";
    var AlertList = (function (_super) {
        __extends(AlertList, _super);
        function AlertList(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.alerts = [];
            _this.state = { open: false, length: 0 };
            _this.onOver = _this.onOver.bind(_this, _this.state);
            _this.clean = _this.clean.bind(_this, _this.alerts);
            _this.check = _this.check.bind(_this, _this.alerts);
            return _this;
        }
        AlertList.prototype.componentWillReceiveProps = function (nextProps, nextContext) {
            if (this.alerts.length !== nextProps.alerts.length) {
                this.alerts = nextProps.alerts;
                this.check();
            }
        };
        AlertList.prototype.componentDidMount = function () {
            document.addEventListener('click', this.alertClickOutside.bind(this), true);
        };
        AlertList.prototype.componentWillUnmount = function () {
            document.removeEventListener('click', this.alertClickOutside.bind(this), true);
        };
        AlertList.prototype.alertClickOutside = function (e) {
            var domNode = ReactDOM.findDOMNode(this);
            var n = e.target;
            if ((!domNode || !domNode.contains(n)) && !$(n).hasClass('declic') && !$(n).parents('.declic').length) {
                this.setState({
                    open: false
                });
            }
        };
        AlertList.prototype.open = function (length) {
            this.setState({ open: length > 0, length: length });
        };
        AlertList.prototype.check = function () {
            this.alerts = this.alerts.filter(function (a) { return !a.hide; });
            this.open(this.alerts.length);
        };
        AlertList.prototype.clean = function () {
            this.alerts.forEach(function (a) { return a.hide = true; });
            this.forceUpdate();
            setTimeout(this.check, Const.TRANSITION_DURATION);
        };
        AlertList.prototype.onOver = function () {
            if (!this.state.open && this.state.length > 0) {
                this.setState({ open: true });
            }
        };
        AlertList.prototype.render = function () {
            var _this = this;
            var alerts = this.alerts.map(function (a) {
                return React.createElement(Alert_1.Alert, { key: a.key, hide: a.hide, level: a.level, title: a.title, content: a.content, code: a.code, time: a.time, onHide: function () { a.hide = true; _this.check(); }, debug: a.debug });
            });
            return React.createElement("div", { id: 'alertList', className: classNames('dark declic', {
                    'open': this.state.open,
                    'possede': this.state.length > 0
                }), onMouseOver: this.onOver },
                React.createElement("span", { className: 'tiroir mini-but', onClick: function (e) { return _this.setState({ open: false }); } },
                    React.createElement("span", { className: 'glyphicon glyphicon-remove' })),
                React.createElement("span", { className: 'cleaner mini-but', onClick: this.clean },
                    React.createElement("span", { className: 'glyphicon glyphicon-erase' })),
                alerts);
        };
        return AlertList;
    }(React.Component));
    exports.AlertList = AlertList;
});
define("modules/main/MainVue", ["require", "exports", "react", "react-dom", "classnames", "struct/Vue", "items/Header", "pages/Pages", "items/ConfirmBox", "items/AlertList", "struct/AjaxCallback"], function (require, exports, React, ReactDOM, classNames, Vue_5, Header_1, Pages_3, ConfirmBox_1, AlertList_1, AjaxCallback_3) {
    "use strict";
    var MainVue = (function (_super) {
        __extends(MainVue, _super);
        function MainVue(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.alertList = [];
            _this.alertKey = 1;
            var page = Pages_3.Pages[props.page];
            var controleur = _this.getControleurFromPage(page);
            _this.state = { controleur: controleur, alertList: [], page: page, display: true, donnees: props.donnees, etatAjax: AjaxCallback_3.AjaxEnum.None };
            return _this;
        }
        MainVue.applyVue = function (controleur) {
            ReactDOM.render(React.createElement(MainVue, { controleur: controleur, donnees: GLOBALS.donnees, page: GLOBALS.page }), document.getElementById("react_container"));
        };
        MainVue.prototype.getControleurFromPage = function (page) {
            if (page === Pages_3.Pages.Accueil) {
                return this.props.controleur.accueilM;
            }
            if (page === Pages_3.Pages.Configuration) {
                return this.props.controleur.configM;
            }
            throw new Error('Page non gérée: ' + page);
        };
        MainVue.prototype.mainSwitchPage = function (page) {
            var _this = this;
            console.log("SWITCH " + page);
            console.log(arguments.length);
            this.setState({ display: false });
            var controleur = this.getControleurFromPage(page);
            setTimeout(function () {
                _this.setState({ controleur: controleur, page: page, display: true });
                window.scrollTo(0, 0);
            }, Const.TRANSITION_DURATION);
        };
        MainVue.prototype.mainAlert = function (level, title, content, code, message_debug) {
            var curDate = new Date();
            if (!code) {
                code = 0;
            }
            var alert = {
                key: this.alertKey, level: level, title: title, content: content, code: code,
                time: curDate.toLocaleTimeString(), hide: false, debug: message_debug
            };
            this.alertList.push(alert);
            this.alertKey++;
            this.setState({
                alertList: this.alertList
            });
        };
        MainVue.prototype.render = function () {
            var _this = this;
            this.props.controleur.vue = this;
            var p = new this.state.page({
                controleur: this.state.controleur,
                onSwitch: this.mainSwitchPage,
                onAlert: this.mainAlert,
                donnees: this.state.donnees
            });
            console.log('Page: ' + p.nom);
            return (React.createElement("div", { id: "react_content", className: classNames('main-content', {
                    'no-header': !p.hasHeader(),
                    'no-display': !this.state.display
                }) },
                React.createElement(Header_1.Header, { mainManager: this.props.controleur, donnees: this.state.donnees, page: p.nom, show: p.hasHeader(), nomAjax: this.state.nomAjax, etatAjax: this.state.etatAjax }),
                React.createElement("div", { id: "content", className: "body-content" },
                    p.renderBandeau(),
                    p.render()),
                React.createElement(AlertList_1.AlertList, { alerts: this.state.alertList }),
                React.createElement("footer", { className: "footer" }),
                this.state.confirmBox ? React.createElement(ConfirmBox_1.ConfirmBox, __assign({}, this.state.confirmBox, { onHide: function () { return _this.setState({ confirmBox: undefined }); } })) : ''));
        };
        return MainVue;
    }(Vue_5.Vue));
    exports.MainVue = MainVue;
});
define("modules/main/MainManager", ["require", "exports", "struct/Controleur", "modules/main/MainModele", "modules/main/AccueilManager", "modules/main/ConfigManager", "modules/main/MainVue", "struct/AjaxCallback", "items/Alert", "pages/Pages"], function (require, exports, Controleur_3, MainModele_1, AccueilManager_1, ConfigManager_1, MainVue_1, AjaxCallback_4, Alert_2, Pages_4) {
    "use strict";
    var MainManager = (function (_super) {
        __extends(MainManager, _super);
        function MainManager() {
            var _this = _super.call(this, new MainModele_1.MainModele(GLOBALS)) || this;
            _this.mainManager = _this;
            _this.accueilM = new AccueilManager_1.AccueilManager(_this.modele.donnees, _this);
            _this.configM = new ConfigManager_1.ConfigManager(_this.modele.donnees, _this);
            return _this;
        }
        Object.defineProperty(MainManager.prototype, "accueilM", {
            get: function () {
                return this._accueilM;
            },
            set: function (accueilM) {
                this._accueilM = accueilM;
            },
            enumerable: true,
            configurable: true
        });
        Object.defineProperty(MainManager.prototype, "configM", {
            get: function () {
                return this._configM;
            },
            set: function (configM) {
                this._configM = configM;
            },
            enumerable: true,
            configurable: true
        });
        MainManager.prototype.start = function () {
            MainVue_1.MainVue.applyVue(this);
        };
        MainManager.prototype.deconnexion = function (element) {
            var _this = this;
            function deconnecter(manager) {
                manager.modele.deconnexion(new AjaxCallback_4.AjaxCallback(manager, 'Déconnexion', {
                    success: function (data) {
                        manager.modele.donnees = data.content;
                        manager.vue.mainSwitchPage(Pages_4.Pages.Accueil);
                    }
                }));
            }
            this.popConfirm('Deconnexion', 'Voulez-vous vraiment vous déconnecter ?', function () { return deconnecter(_this); }, element, true);
        };
        MainManager.prototype.showAlertFromCode = function (code_num, message_debug) {
            var code = Const.CODES[code_num];
            if (!code) {
                code = Const.CODES[400];
            }
            var level;
            switch (code.crit) {
                case 0:
                    level = Alert_2.AlertLevel.Normal;
                    break;
                default:
                    level = Alert_2.AlertLevel.Error;
                    break;
            }
            this.vue.mainAlert(level, code.titre, code.message, code_num, message_debug);
        };
        return MainManager;
    }(Controleur_3.Controleur));
    exports.MainManager = MainManager;
});
define("struct/Controleur", ["require", "exports", "struct/AjaxCallback"], function (require, exports, AjaxCallback_5) {
    "use strict";
    var Controleur = (function () {
        function Controleur(modele, mainManager) {
            this.modele = modele;
            this.mainManager = mainManager;
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
        Object.defineProperty(Controleur.prototype, "mainManager", {
            get: function () {
                return this._mainManager;
            },
            set: function (mainManager) {
                this._mainManager = mainManager;
            },
            enumerable: true,
            configurable: true
        });
        Controleur.prototype.startAjax = function (nom) {
            this.mainManager.vue.setState({ nomAjax: nom, etatAjax: AjaxCallback_5.AjaxEnum.Load });
        };
        Controleur.prototype.endAjax = function (etat) {
            this.mainManager.vue.setState({ etatAjax: etat });
        };
        Controleur.prototype.popConfirm = function (titre, content, onConfirm, element, fixed) {
            this.mainManager.vue.setState({ confirmBox: { info: false, srcElement: element, titre: titre, content: content, onConfirm: onConfirm, fixed: fixed } });
        };
        Controleur.prototype.popInfo = function (titre, content, element, fixed) {
            this.mainManager.vue.setState({ confirmBox: { info: true, srcElement: element, titre: titre, content: content, onConfirm: function () { }, fixed: fixed } });
        };
        Controleur.prototype.popNonImplemente = function (element, fixed) {
            this.popInfo('Fonction non implémentée', 'Patience est mère de vertu', element, fixed);
        };
        Controleur.prototype.showAlertFromCode = function (code_num, message_debug) {
            this.mainManager.showAlertFromCode(code_num, message_debug);
        };
        return Controleur;
    }());
    exports.Controleur = Controleur;
});
define("struct/AjaxCallback", ["require", "exports"], function (require, exports) {
    "use strict";
    var AjaxEnum;
    (function (AjaxEnum) {
        AjaxEnum[AjaxEnum["None"] = 0] = "None";
        AjaxEnum[AjaxEnum["Load"] = 1] = "Load";
        AjaxEnum[AjaxEnum["Success"] = 2] = "Success";
        AjaxEnum[AjaxEnum["Error"] = 3] = "Error";
    })(AjaxEnum = exports.AjaxEnum || (exports.AjaxEnum = {}));
    var AjaxCallback = (function () {
        function AjaxCallback(manager, label, cb) {
            this.manager = manager;
            this.label = label;
            this.cb = cb;
        }
        AjaxCallback.prototype.onStart = function () {
            this.manager.startAjax(this.label);
        };
        AjaxCallback.prototype.onSuccess = function (data) {
            if (data.success) {
                this.manager.endAjax(AjaxEnum.Success);
                if (this.cb.success) {
                    this.cb.success(data);
                }
            }
            else {
                this.manager.endAjax(AjaxEnum.Error);
                this.manager.showAlertFromCode(data.code, data.debug);
                if (this.cb.error) {
                    this.cb.error(data);
                }
            }
        };
        AjaxCallback.prototype.onFail = function () {
            this.manager.endAjax(AjaxEnum.Error);
            this.manager.showAlertFromCode(100);
            if (this.cb.fail) {
                this.cb.fail();
            }
        };
        AjaxCallback.prototype.onDone = function (data) {
            if (this.cb.done) {
                this.cb.done(data);
            }
        };
        AjaxCallback.prototype.onAlways = function (data) {
            if (this.cb.always) {
                this.cb.always(data);
            }
        };
        return AjaxCallback;
    }());
    exports.AjaxCallback = AjaxCallback;
});
define("items/AjaxNotif", ["require", "exports", "react", "classnames", "struct/AjaxCallback"], function (require, exports, React, classNames, AjaxCallback_6) {
    "use strict";
    var AjaxNotif = (function (_super) {
        __extends(AjaxNotif, _super);
        function AjaxNotif(props) {
            return _super.call(this, props) || this;
        }
        AjaxNotif.prototype.render = function () {
            return React.createElement("span", { className: classNames('ajax-bloc', {
                    'hide': this.props.etat == AjaxCallback_6.AjaxEnum.None,
                    'load': this.props.etat == AjaxCallback_6.AjaxEnum.Load,
                    'success': this.props.etat == AjaxCallback_6.AjaxEnum.Success,
                    'error': this.props.etat == AjaxCallback_6.AjaxEnum.Error,
                }) },
                this.props.value,
                React.createElement("span", { className: 'spinner-box' },
                    React.createElement("span", { className: classNames('icon', 'l-white', {
                            'spinner visible': this.props.etat == AjaxCallback_6.AjaxEnum.Load,
                            'glyphicon glyphicon-ok': this.props.etat == AjaxCallback_6.AjaxEnum.Success,
                            'glyphicon glyphicon-remove': this.props.etat == AjaxCallback_6.AjaxEnum.Error,
                        }) })));
        };
        return AjaxNotif;
    }(React.Component));
    exports.AjaxNotif = AjaxNotif;
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
RequireAll.loadAll();
