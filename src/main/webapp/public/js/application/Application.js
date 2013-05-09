angular.module('punters-den', ['userService', 'ngCookies', 'ngResource', 'LocalStorageModule']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/user/login', {templateUrl: '/public/html/user/login.html', controller: LoginCtrl}).
            otherwise({redirectTo: '/user/login'});
    }]);