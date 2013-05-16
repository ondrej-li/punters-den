angular.module('punters-den', ['userCommService', 'ngCookies', 'ngResource', 'LocalStorageModule']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/', {templateUrl: '/html/home.html', controller: HomeCtrl}).
            when('/user/login', {templateUrl: '/html/user/login.html', controller: LoginCtrl}).
            when('/user/logout', {templateUrl: '/html/user/logout.html', controller: LogoutCtrl}).
            otherwise({redirectTo: '/'});
    }]);