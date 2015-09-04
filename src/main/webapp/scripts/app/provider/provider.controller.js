'use strict';

angular.module('weatherAggregator')
    .controller('ProviderController', function ($scope, Provider) {
        $scope.providers = [];

        $scope.load = function() {
            Provider.get(function(result) {
                $scope.providers = result;
            });
        };

        $scope.load();
    });