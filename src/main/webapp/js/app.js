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
            _this.hide = _this.hide.bind(_this, _this.props.onHide);
            return _this;
        }
        Alert.prototype.hide = function () {
            this.props.onHide();
        };
        Alert.prototype.render = function () {
            return React.createElement("div", { className: classNames('myalert', {
                    'primary': this.props.level === AlertLevel.Primary,
                    'error': this.props.level === AlertLevel.Error
                }) },
                React.createElement("span", { className: 'myalert-close', onClick: this.hide }),
                React.createElement("div", { className: 'myalert-title' }, this.props.title),
                React.createElement("div", { className: 'myalert-content' }, this.props.content));
        };
        return Alert;
    }(React.Component));
    exports.Alert = Alert;
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
            return _this;
        }
        AlertList.prototype.componentWillReceiveProps = function (nextProps, nextContext) {
            this.alerts = nextProps.alerts;
            this.check();
        };
        AlertList.prototype.componentDidMount = function () {
            document.addEventListener('click', this.handleClickOutside.bind(this), true);
        };
        AlertList.prototype.componentWillUnmount = function () {
            document.removeEventListener('click', this.handleClickOutside.bind(this), true);
        };
        AlertList.prototype.handleClickOutside = function (e) {
            var domNode = ReactDOM.findDOMNode(this);
            if ((!domNode || !domNode.contains(e.target))) {
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
            this.forceUpdate();
        };
        AlertList.prototype.onOver = function () {
            if (!this.state.open && this.state.length > 0) {
                this.setState({ open: true });
            }
        };
        AlertList.prototype.render = function () {
            var _this = this;
            var alerts = this.alerts.map(function (a) {
                return React.createElement(Alert_1.Alert, { key: a.key, level: a.level, title: a.title, content: a.content, onHide: function () { a.hide = true; _this.check(); } });
            });
            return React.createElement("div", { id: 'alertList', className: classNames({
                    'open': this.state.open,
                    'possede': this.state.length > 0
                }), onMouseOver: this.onOver },
                React.createElement("span", { className: 'tiroir', onClick: function (e) { return _this.setState({ open: false }); } }),
                alerts);
        };
        return AlertList;
    }(React.Component));
    exports.AlertList = AlertList;
});
define("items/Bouton", ["require", "exports", "react", "classnames"], function (require, exports, React, classNames) {
    "use strict";
    var Bouton = (function (_super) {
        __extends(Bouton, _super);
        function Bouton(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.state = { disabled: _this.props.disabled, load: false };
            return _this;
        }
        Bouton.prototype.render = function () {
            var _this = this;
            return React.createElement("button", { className: classNames({
                    'but': true,
                    'but-primary': this.props.primary,
                    'load': this.state.load,
                    'disabled': this.state.disabled || this.props.disabled
                }, this.props.className), type: this.props.submit ? 'submit' : 'button', onClick: function (e) {
                    _this.props.onClick(e, _this);
                    if (_this.props.onClickDisable) {
                        _this.setState({ disabled: true });
                    }
                    if (_this.props.onClickLoad) {
                        _this.setState({ load: true });
                    }
                }, disabled: this.state.disabled || this.props.disabled }, this.props.value);
        };
        return Bouton;
    }(React.Component));
    exports.Bouton = Bouton;
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
            if (vue) {
                this.vue = vue;
            }
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
define("struct/Vue", ["require", "exports", "react"], function (require, exports, React) {
    "use strict";
    var Vue = (function (_super) {
        __extends(Vue, _super);
        function Vue(props, context) {
            return _super.call(this, props, context) || this;
        }
        Vue.prototype.addAlert = function (level, title, content) {
            console.log(level);
            if (this.props.onAlert) {
                this.props.onAlert(level, title, content);
            }
        };
        return Vue;
    }(React.Component));
    exports.Vue = Vue;
});
define("struct/AjaxCallback", ["require", "exports"], function (require, exports) {
    "use strict";
    var AjaxCallback = (function () {
        function AjaxCallback(array) {
            if (array) {
                this.success = array.success;
                this.fail = array.fail;
                this.done = array.done;
                this.always = array.always;
            }
        }
        AjaxCallback.prototype.onSuccess = function (data) {
            if (this.success) {
                this.success(data);
            }
        };
        AjaxCallback.prototype.onFail = function () {
            if (this.fail) {
                this.fail();
            }
        };
        AjaxCallback.prototype.onDone = function (data) {
            if (this.done) {
                this.done(data);
            }
        };
        AjaxCallback.prototype.onAlways = function (data) {
            if (this.always) {
                this.always(data);
            }
        };
        return AjaxCallback;
    }());
    exports.AjaxCallback = AjaxCallback;
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
        MainModele.prototype.connexion = function (pseudo, mail, mdp, isMail, ajaxc) {
            $.post('connexion', { pseudo: pseudo, mail: mail, mdp: mdp, isMail: isMail }, function (data) { return ajaxc.onSuccess(data); })
                .fail(function () { return ajaxc.onFail(); })
                .done(function (data) { return ajaxc.onDone(data); })
                .always(function (data) { return ajaxc.onAlways(data); });
        };
        MainModele.prototype.inscription = function (pseudo, mail, mdp, ajaxc) {
            $.post('inscription', { pseudo: pseudo, mail: mail, mdp: mdp }, function (data) { return ajaxc.onSuccess(data); })
                .fail(function () { return ajaxc.onFail(); })
                .done(function (data) { return ajaxc.onDone(data); })
                .always(function (data) { return ajaxc.onAlways(data); });
        };
        return MainModele;
    }(Modele_1.Modele));
    exports.MainModele = MainModele;
});
define("items/StartForm", ["require", "exports", "react", "struct/Vue", "items/Bouton", "items/Alert"], function (require, exports, React, Vue_1, Bouton_1, Alert_2) {
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
            var _this = this;
            if (!this.form.checkValidity()) {
                bouton.setState({ disabled: false, load: false });
                return;
            }
            this.setState({ load: true });
            if (this.state.type === BoutonType.Connexion) {
                this.props.controleur.connexion(this.state.ip_pseudo, this.state.ip_mail, this.state.ip_mdp, this.state.isMail, {
                    fail: function () { return _this.addAlert(Alert_2.AlertLevel.Error, "Connexion impossible", "Serveur inaccessible"); },
                    always: function () { return _this.setState({ load: false }); }
                });
            }
            else {
                this.props.controleur.inscription(this.state.ip_pseudo, this.state.ip_mail, this.state.ip_mdp, {
                    fail: function () { return _this.addAlert(Alert_2.AlertLevel.Error, "Inscription impossible", "Serveur inaccessible"); },
                    always: function () { return _this.setState({ load: false }); }
                });
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
                    React.createElement("input", { type: "text", className: "field", id: "ip_insc", name: "ip_insc", value: this.state.ip_pseudo, onChange: function (e) { return _this.handlePseudo(e); }, minLength: Const.LENGTH.PSEUDO.min, maxLength: Const.LENGTH.PSEUDO.max, required: true }));
            }
            else {
                ret = React.createElement("div", { className: "form-group" },
                    React.createElement("label", { htmlFor: "ip_insc" }, "Email"),
                    React.createElement("input", { type: "email", className: "field", id: "ip_insc", name: "ip_insc", value: this.state.ip_mail, onChange: function (e) { return _this.handleMail(e); }, minLength: Const.LENGTH.MAIL.min, maxLength: Const.LENGTH.MAIL.max, required: true }));
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
                        React.createElement("input", { type: this.state.isMail ? 'email' : 'text', className: "field", id: "ip_pseudo", name: "ip_pseudo", value: this.state.ip_pseudoOrMail, onChange: function (e) { return _this.handlePseudoOrMail(e); }, minLength: this.state.isMail ? Const.LENGTH.MAIL.min : Const.LENGTH.PSEUDO.min, maxLength: this.state.isMail ? Const.LENGTH.MAIL.max : Const.LENGTH.PSEUDO.max, required: true })),
                    React.createElement("div", { className: "form-group" },
                        React.createElement("label", { htmlFor: "ip_mdp" }, "Mot de passe"),
                        React.createElement("input", { type: "password", className: "field", id: "ip_mdp", name: "ip_mdp", value: this.state.ip_mdp, onChange: function (e) { return _this.handleMdp(e); }, minLength: Const.LENGTH.MDP.min, maxLength: Const.LENGTH.MDP.max, required: true })),
                    inscription,
                    React.createElement("div", { className: "form-group" },
                        React.createElement(Bouton_1.Bouton, { value: "Inscrivez-vous", className: "d-block", primary: this.state.type === BoutonType.Inscription, submit: this.state.type === BoutonType.Inscription, onClick: function (e, bouton) { return _this.onClick(e, bouton, BoutonType.Inscription); }, disabled: this.state.load, onClickLoad: this.state.type === BoutonType.Inscription })),
                    React.createElement("div", { className: "form-group" },
                        React.createElement(Bouton_1.Bouton, { value: "Connectez-vous", className: "d-block", primary: this.state.type === BoutonType.Connexion, submit: this.state.type === BoutonType.Connexion, onClick: function (e, bouton) { return _this.onClick(e, bouton, BoutonType.Connexion); }, disabled: this.state.load, onClickLoad: this.state.load && (this.state.type === BoutonType.Connexion) }),
                        React.createElement("div", { className: "text-center" },
                            React.createElement("a", { href: "" }, "Vous avez oubli\u00E9 vos identifiants ?")))));
        };
        return StartForm;
    }(Vue_1.Vue));
    StartForm.MAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    exports.StartForm = StartForm;
});
define("modules/main/MainVue", ["require", "exports", "react", "react-dom", "struct/Vue", "items/Header", "items/StartForm", "items/AlertList"], function (require, exports, React, ReactDOM, Vue_2, Header_1, StartForm_1, AlertList_1) {
    "use strict";
    var MainVue = (function (_super) {
        __extends(MainVue, _super);
        function MainVue(props, context) {
            var _this = _super.call(this, props, context) || this;
            _this.alertList = [];
            _this.alertKey = 1;
            _this.state = { alertList: [] };
            _this.mainAlert = _this.mainAlert.bind(_this, _this.state.alertList);
            return _this;
        }
        MainVue.applyVue = function (controleur) {
            ReactDOM.render(React.createElement(MainVue, { controleur: controleur, user: GLOBALS.user, page: GLOBALS.page, onAlert: undefined }), document.getElementById("react_container"));
        };
        MainVue.prototype.mainAlert = function (arr, level, title, content) {
            var alert = {
                key: this.alertKey, level: level, title: title, content: content, hide: false
            };
            this.alertList.push(alert);
            this.alertKey++;
            this.setState({
                alertList: this.alertList
            });
        };
        MainVue.prototype.render = function () {
            this.props.controleur.vue = this;
            var connected = this.props.user.connected;
            return (React.createElement("div", { id: "react_content", className: connected ? '' : 'no-header' },
                React.createElement(Header_1.Header, { controleur: this.props.controleur, user: this.props.user, page: this.props.page, onAlert: this.mainAlert }),
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
                            React.createElement(StartForm_1.StartForm, { controleur: this.props.controleur, onAlert: this.mainAlert })))),
                React.createElement(AlertList_1.AlertList, { alerts: this.state.alertList }),
                React.createElement("footer", { className: "footer" })));
        };
        return MainVue;
    }(Vue_2.Vue));
    exports.MainVue = MainVue;
});
define("modules/main/MainManager", ["require", "exports", "struct/Controleur", "modules/main/MainModele", "modules/main/MainVue", "struct/AjaxCallback"], function (require, exports, Controleur_1, MainModele_1, MainVue_1, AjaxCallback_1) {
    "use strict";
    var MainManager = (function (_super) {
        __extends(MainManager, _super);
        function MainManager() {
            return _super.call(this, new MainModele_1.MainModele(), new MainVue_1.MainVue()) || this;
        }
        MainManager.prototype.start = function () {
            MainVue_1.MainVue.applyVue(this);
        };
        MainManager.prototype.connexion = function (pseudo, mail, mdp, isMail, callbacks) {
            this.modele.connexion(pseudo, mail, mdp, isMail, new AjaxCallback_1.AjaxCallback(callbacks));
        };
        MainManager.prototype.inscription = function (pseudo, mail, mdp, callbacks) {
            this.modele.inscription(pseudo, mail, mdp, new AjaxCallback_1.AjaxCallback(callbacks));
        };
        return MainManager;
    }(Controleur_1.Controleur));
    exports.MainManager = MainManager;
});
define("items/Header", ["require", "exports", "react", "struct/Vue"], function (require, exports, React, Vue_3) {
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
    }(Vue_3.Vue));
    exports.Header = Header;
});
define("items/Input", ["require", "exports", "react", "struct/Vue"], function (require, exports, React, Vue_4) {
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
    }(Vue_4.Vue));
    exports.Input = Input;
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
