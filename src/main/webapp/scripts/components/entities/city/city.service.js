'use strict';

angular.module('weatherAggregator')
    .factory('Cities', function ($resource) {
        return $resource('rest/city', [], {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                isArray: true,
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    })
    .factory('CityById', function ($resource) {
            return $resource('rest/city/:id', {}, {
                'query': { method: 'GET', isArray: false},
                'get': {
                    method: 'GET',
                    isArray: true,
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                }
            });
        })

    ;
