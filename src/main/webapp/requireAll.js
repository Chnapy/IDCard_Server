
/* global requirejs, Main */

'use strict';

var DEBUG = true;

var React;
var ReactDOM;
var classNames;

requirejs.config({
	baseUrl: 'libs',
	paths: {
		jquery: 'jquerylib/jquery-3.1.1.min',
		react: 'react/' + (DEBUG ? 'react' : 'react.min'),
		react_dom: 'react/' + (DEBUG ? 'react-dom' : 'react-dom.min'),
		classnames: 'react/classnames'
	}
});

requirejs(['jquery', 'react', 'react_dom', 'classnames'], function () {
	React = require('react');
	ReactDOM = require('react_dom');
	classNames = require('classnames');

	requirejs.config({
		baseUrl: 'js',
		paths: {
			app: 'app'
		}
	});

	requirejs(['app'], function () {
		console.log('Chargement des fichiers terminé');
		
		main();
	});
});
