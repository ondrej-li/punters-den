function MainCtrl($scope, $location, UserComm, localStorageService) {
    $scope.user = null;
    $scope.logoutClicked = function () {
        UserComm.logout(
            {username: $scope.user.username},
            function (r) {
                console.log('status:' + r.status);
                $scope.user = null;
                localStorageService.cookie.remove('auth-key');
                $location.url("/");
            },
            function (r) {
                console.error('error', r);
            });
    };
    $scope.$on('logout', $scope.logoutClicked);
    $scope.$on('login', function (u, data) {
        $scope.user = data[0].user;
    });
}