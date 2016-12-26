'use strict';
var Caller = angular.module('Caller', ['ngRoute','angularUtils.directives.dirPagination']);

Caller.config(function($routeProvider){
  $routeProvider
  .when("/",
		  {
      			templateUrl: "views/Home.html"
		  }
  )
  .when("/user",
		    {
		      	templateUrl: "views/UserManagement.html",
		      	controller: "UserController"
		    }
  )
  .when("/home",
		    {
		      	templateUrl: "views/MainSite.html",		      
		    }
  )
  .otherwise(
		  {
			  	templateUrl : "views/404.html"
		  });
});