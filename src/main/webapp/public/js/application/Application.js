angular.module('punters-den', ['userCommService', 'ngCookies', 'ngResource', 'LocalStorageModule']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/', {templateUrl: '/public/html/home.html', controller: HomeCtrl}).
            when('/user/login', {templateUrl: '/public/html/user/login.html', controller: LoginCtrl}).
            when('/user/logout', {templateUrl: '/public/html/user/logout.html', controller: LogoutCtrl}).
            otherwise({redirectTo: '/'});
    }]);