angular.module('operatorApp')
    .factory('OrdersService', ['$http', function($http) {
        var service = {};
        service.list = function() {
            return $http.get('http://localhost:8090/orders');
        };
        service.create = function(order) {
            return $http.post('http://localhost:8090/orders', order);
        };
        service.getOrderById = function(orderId) {
            return $http.get('http://localhost:8090/orders/' + orderId);
        };
        service.updateOrder = function(orderId, order) {
            return $http.put('http://localhost:8090/orders/' + orderId, order);
        };
        service.deleteOrder = function(orderId) {
            return $http.delete('http://localhost:8090/orders/' + orderId);
        };
        return service;
}])