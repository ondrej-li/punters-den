function MainCtrl($scope, $location, UserComm, localStorageService) {
    $scope.logoutClicked = function () {
        UserComm.logout(
            {username: $scope.login.username},
            function (r) {
                console.log('status:' + r.outcome);
                localStorageService.cookie.remove('auth-key');
                $location.url("/");
            },
            function (r) {
                console.error('error', r);
            });
    };
    $scope.$on('logout', $scope.logoutClicked);
}