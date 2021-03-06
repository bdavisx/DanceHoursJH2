'use strict';

angular.module('danceHoursApp')
    .controller('RegisterController', function($scope, $timeout, Auth) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.errorUserExists = null;
        $scope.registerAccount = {};
        $timeout(function() {
            angular.element('[ng-model="registerAccount.login"]').focus();
        });

        function invalidateForm() {
            angular.forEach($scope.registerAccount.$error.required, function(field) {
                field.$setDirty();
            });
        }

        $scope.copyLoginToEmail = function() {
            $scope.registerAccount.login = $scope.registerAccount.email;
            invalidateForm();
        }

        $scope.register = function() {
            if($scope.registerAccount.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.registerAccount.langKey = 'en';
                $scope.doNotMatch = null;
                $scope.error = null;
                $scope.errorUserExists = null;
                $scope.errorEmailExists = null;

                Auth.createAccount($scope.registerAccount).then(function() {
                    $scope.success = 'OK';
                }).catch(function(response) {
                    $scope.success = null;
                    if(response.status === 400) {
                        if(reponse.data.contains('login already in use')) {$scope.errorUserExists = 'ERROR';}
                        if(reponse.data.contains('e-mail address already in use')) {$scope.errorEmailExists = 'ERROR';}
                    } else {
                        $scope.error = 'ERROR';
                    }
                });
            }
        };
    });
