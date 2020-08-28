var app = angular.module('appShop', ['ngRoute', 'ngStorage']);
var contextPath = 'http://localhost:8189/market'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'homepage.html'
        })
        .when('/product', {
            templateUrl: 'product.html',
            controller: 'productCtrl'
        })
        .when('/add', {
            templateUrl: 'add.html',
            controller: 'addController'
        })
        .when('/edit', {
            templateUrl: 'edit.html',
            controller: 'productEditCtrl'
        })
        .when('/new_user',{
            templateUrl: 'new_user.html',
            controller: 'userNewCtrl'
        })
});

app.controller('mainController', function ($scope, $http, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = { username: $scope.user.username, token: response.data.token };
                }
            });
    };

    $scope.tryToLogout = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };
});

app.controller('productCtrl', function($scope, $http, $localStorage) {

    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    fillTable = function() {
        $http.get(contextPath + "" + '/api/v1/products')
            .then(function(response) {
                $scope.Products = response.data;
                console.log($scope.Products);

            });
    };
    fillTable();
});

app.controller('addController', function($scope, $http, $localStorage) {
    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    $scope.submitProduct = function() {
        $http.post(contextPath + '/api/v1/products', $scope.product)
            .then(function(response) {
                console.log(response);
                // $scope.Demos.push(response.data);
            });
    };
});

app.controller('productEditCtrl', function($scope, $http, $routeParams, $localStorage) {
    const advertsPath = contextPath + '/api/v1/products';

    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    fillForm = function() {
        $http.get(advertsPath + '/' + $routeParams.id)
            .then(function(response) {
                $scope.product = response.data;
                console.log($scope.product);
            });
    };

    fillForm();

    $scope.submitEditProduct = function() {
        $http.put('http://localhost:8189/market/api/v1/products ', $scope.product)
            .then(function(response) {
                console.log(response);
                // $scope.Demos.push(response.data);
            });
    };
});

app.controller('userNewCtrl', function($scope, $http) {
    $scope.submitNewUser = function() {
        $http.post('http://localhost:8189/market/api/v1/users/new_user', $scope.systemUser)
            .then(function(response) {

                $scope.usersRespons = response.data;
                console.log($scope.registrationError);
                // $scope.Demos.push(response.data);
            });
    };


});