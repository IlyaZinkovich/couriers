angular.module('operatorApp')
    .controller('OperatorController', ['$scope', 'OrdersService', function($scope, ordersService) {
        $scope.orders = [];
        $scope.selectedOrder = null;

        ordersService.list().then(function(success) {
            $scope.orders = success.data;
        }, function(error) {
            console.error(error);
        });

        $scope.createOrder = function(orderToCreate) {
            ordersService.create($scope.orderToCreate).then(function(success) {
                var orderId = success.data;
                ordersService.getOrderById(orderId).then(function(success) {
                    var order = success.data;
                    $scope.orders.push(order);
                }, function(error) {
                    console.error(error);
                })
            }, function(error) {
                console.error(error);
            });
        };

        $scope.updateOrder = function(orderToUpdate) {
            ordersService.updateOrder(orderToUpdate.orderId, orderToUpdate);
        };

        $scope.selectOrder = function(orderId) {
            ordersService.getOrderById(orderId).then(function(success) {
                $scope.selectedOrder = success.data;
            }, function(error) {
                console.error(error);
            })
        };

        $scope.deleteOrder = function(orderId) {
            ordersService.deleteOrder(orderId);
        }
}])