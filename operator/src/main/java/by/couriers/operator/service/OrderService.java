package by.couriers.operator.service;

import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;

import java.util.List;

public interface OrderService {

    String createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(String orderId);
    Order getOrderById(String orderId);
    List<Order> getOrdersMatchingCriteria(OrderCriteria criteria);

}
