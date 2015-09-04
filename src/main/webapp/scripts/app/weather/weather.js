'use strict';

angular.module('weatherAggregator')
    .config(function ($stateProvider) {
        $stateProvider
            .state('weather', {
                parent: 'entity',
                url: '/:provider',
                data: {
                    pageTitle: 'Weather'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/weather/weather.html',
                        controller: 'WeatherController'
                    }
                },
                resolve: {
                    provider: function($stateParams) {
                        return $stateParams.provider;
                    }
                }
            })
            ;
    });