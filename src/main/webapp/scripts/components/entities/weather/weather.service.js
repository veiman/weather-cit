'use strict';

angular.module('weatherAggregator')
    .factory('WeatherByCity', function ($resource) {
        return $resource('rest/:provider/weatherByCity', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                isArray: false,
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
