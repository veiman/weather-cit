'use strict';

angular.module('weatherAggregator')
    .factory('Provider', function ($resource) {
        return $resource('rest/providers', [], {
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
    });
