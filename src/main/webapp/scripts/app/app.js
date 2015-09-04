'use strict';

angular.module('weatherAggregator', [
        'LocalStorageModule',
        'ngResource',
        'ui.router',
        'ngCookies',
        'ngCacheBuster',
        'infinite-scroll',
        'ui.bootstrap',
        'ui.grid',
        'ui.grid.autoResize'
        ])


        .run(function ($rootScope, $location, $window, $http, $state) {
            $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
                $rootScope.toState = toState;
                $rootScope.toStateParams = toStateParams;

            });

            $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
                var titleKey = 'global.title' ;

                $rootScope.previousStateName = fromState.name;
                $rootScope.previousStateParams = fromParams;


            });

        })

        .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, httpRequestInterceptorCacheBusterProvider) {

            //Cache everything except rest api requests
            httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

            $urlRouterProvider.otherwise('/');
            $stateProvider.state('site', {
                'abstract': true,
                views: {
                    'navbar@': {
                        templateUrl: 'scripts/components/navbar/navbar.html',
                        controller: 'ProviderController'
                    }
                }
            });

        });