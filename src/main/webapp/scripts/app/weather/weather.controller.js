'use strict';

angular.module('weatherAggregator')
    .controller('WeatherController', function ($scope, $stateParams, Cities, WeatherByCity, uiGridConstants) {
        $scope.provider = $stateParams.provider;
        $scope.city;
//        $scope.cities = [];
        $scope.weatherByCity;
//        $scope.keys = [];
        $scope.gridOptions;




        $scope.load = function (provider) {
            Cities.get(function(result) {
              $scope.cities = result;
            });
//            $scope.provider = provider;

            WeatherByCity.get({provider: provider}, function(result) {
                $scope.weatherByCity = result;
                // удаляем служебные ключи
                delete $scope.weatherByCity['$promise'];
                delete $scope.weatherByCity['$resolved'];

                $scope.gridOptions.data = $scope.weatherByCity[$scope.city.name+' ('+$scope.city.country+')'];



            });


            };



        $scope.gridOptions = {
                    enableSorting: false,
                    enableFiltering: false,
                    showGridFooter: true,
                    enableGridMenu: true,
                    columnDefs: [
                          {
                            field: 'dateTime',
                            displayName: 'Дата Время',
                            type: 'date',
                            enableSorting: true,
                            enableFiltering: true,
                            cellFilter: 'date:\'dd.MM.yyyy HH:mm\'',
                            width: 140
                          },
                         {
                           field: 'temperature',
                           displayName: 'Температура (С)',
                           type: 'number',
                           width: 150
                         },
                         {
                           field: 'humidity',
                           displayName: 'Влажность (%)',
                           width: 140,
                           type: 'number'
                         },
                         {
                           field: 'pressure',
                           displayName: 'Давление (мм рт. ст.)',
                           width: 160,
                           type: 'number'
                         },
                         {
                           field: 'visibility',
                           displayName: 'Видимость (км)',
                           width: 140,
                           type: 'number'
                         },
                         {
                           field: 'windDirection',
                           displayName: 'Направление ветра',
                           width: 170,
                           type: 'string'
                         },
                         {
                           field: 'windSpeed',
                           displayName: 'Скорость ветра (м/с)',
                           width: 160,
                           type: 'number'
                         },
                          {
                            field: 'description',
                            displayName: 'Описание',
//                            width: 120,
                            type: 'string'
                          }
                        ]
                  };


        $scope.load($stateParams.provider);

        $scope.selectTab = function(activeTab) {
                    $scope.city=activeTab;
                    if (typeof $scope.weatherByCity != 'undefined') { // костыль
                        $scope.gridOptions.data = $scope.weatherByCity[activeTab.name+' ('+activeTab.country+')'];
                    }
        };





    });

