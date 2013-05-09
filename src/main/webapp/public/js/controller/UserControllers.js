function LoginCtrl($scope, $location, User, localStorageService) {
    $scope.login = {"username": "", "password": ""};
    $scope.loginClicked = function () {
        User.login(
            {username: $scope.login.username, password: $scope.login.password},
            function (r) {
                console.log('status:' + r.outcome);
                localStorageService.cookie.add('auth-key', r['auth-key']);
                $location.url('/');
            },
            function (r) {
                console.error('error', r);
            });
    };
}