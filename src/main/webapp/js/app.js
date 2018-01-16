'use strict';

var App = angular
.module('MonApp',['toaster','ngAnimate','ui.router'])
.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
	
	$locationProvider.html5Mode(false);
	$locationProvider.hashPrefix('');
	
	$stateProvider
	.state('main', {
	    url: '/',
	    templateUrl: './index.html',
	    controller: 'mainController'
	})
	.state('test', {
	    url: '/test',
	    templateUrl: './views/test.html',
	    controller: 'testController'
	})
});