'use strict';

angular.module('weatherAggregator')
    .controller('NavbarController', function ($scope, $location, $state, Provider) {
        $scope.$state = $state;
        $scope.providers = {};

        $scope.load = function() {
            Provider.get(function(result) {
                $scope.providers = result;
            });
        }



    });
