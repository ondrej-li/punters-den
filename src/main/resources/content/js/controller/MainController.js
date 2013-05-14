function MainCtrl($scope, $location, UserComm, localStorageService) {
    $scope.user = null;
    $scope.logoutClicked = function () {
        UserComm.logout(
            {username: $scope.user.username},
            function (r) {
                console.log('status:' + r.outcome);
                $scope.user = null;
                localStorageService.cookie.remove('auth-key');
                $location.url("/");
            },
            function (r) {
                console.error('error', r);
            });
    };
    $scope.$on('logout', $scope.logoutClicked);
    $scope.$on('login', function (u) {
        $scope.user = u;
    });
}