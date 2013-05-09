function MainCtrl($scope, User, localStorageService) {
    $scope.logoutClicked = function () {
        User.logout(
            {username: $scope.login.username},
            function (r) {
                console.log('status:' + r.outcome);
                localStorageService.cookie.remove('auth-key');
            },
            function (r) {
                console.error('error', r);
            });
    };
}