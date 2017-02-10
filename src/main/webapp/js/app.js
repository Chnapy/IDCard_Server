var Const = (function () {
    function Const() {
    }
    return Const;
}());
Const.DEBUG = true;
var Main = (function () {
    function Main() {
    }
    Main.main = function () {
        console.log("Main go !");
    };
    return Main;
}());
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
            Main.main();
        });
    };
    return RequireAll;
}());
RequireAll.LOADED = false;
window.onload = function () {
    RequireAll.loadAll();
};
