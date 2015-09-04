'use strict';

angular.module('weatherAggregator')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'ProviderController'
                    }
                }
            });
    });