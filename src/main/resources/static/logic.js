var app = angular.module('appShop', ['ngRoute']);
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

app.controller('productCtrl', function($scope, $http) {
    fillTable = function() {
        $http.get(contextPath + "" + '/api/v1/products')
            .then(function(response) {
                $scope.Products = response.data;
                console.log($scope.Products);

            });
    };
    fillTable();
});

app.controller('addController', function($scope, $http) {
    $scope.submitProduct = function() {
        $http.post(contextPath + '/api/v1/products', $scope.product)
            .then(function(response) {
                console.log(response);
                // $scope.Demos.push(response.data);
            });
    };
});

app.controller('productEditCtrl', function($scope, $http, $routeParams) {
    const advertsPath = contextPath + '/api/v1/products';

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