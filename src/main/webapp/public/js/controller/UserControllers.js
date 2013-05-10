function LoginCtrl($scope, $location, UserComm, localStorageService) {
    $scope.login = {"username": "", "password": ""};
    $scope.loginClicked = function () {
        UserComm.login(
            {username: $scope.login.username, password: $scope.login.password},
            function (r) {
                console.log('status:' + r.outcome);
                localStorageService.cookie.add('auth-key', r['auth-key']);
                $scope.$emit('login', [r]);
                $location.url('/');
            },
            function (r) {
                console.error('error', r);
            });
    };
}

function LogoutCtrl($scope, $location) {
    $scope.logoutClicked = function () {
        $scope.$emit('logout', []);
    };
}