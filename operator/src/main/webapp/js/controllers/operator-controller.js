angular.module('operatorApp')
    .controller('OperatorController', ['$scope', '$filter', 'OrdersService', function($scope, $filter, ordersService) {
        $scope.orders = [];
        $scope.selectedOrder = null;

        ordersService.list().then(function(success) {
            $scope.orders = success.data;
        }, function(error) {
            console.error(error);
        });

        $scope.createOrder = function(orderToCreate) {
            var objectToCreate = angular.copy($scope.orderToCreate);
            objectToCreate.acceptanceDate = $filter('date')($scope.orderToCreate.acceptanceDate, 'dd-MM-yyyy');
            ordersService.create(objectToCreate).then(function(success) {
                $scope.orders.push(success.data);
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