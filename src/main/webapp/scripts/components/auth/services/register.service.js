'use strict';

angular.module('danceHoursApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


