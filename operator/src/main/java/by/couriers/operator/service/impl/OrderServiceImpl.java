package by.couriers.operator.service.impl;

import by.couriers.operator.dao.OrderRepository;
import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import by.couriers.operator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public String createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteOrder(orderId);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public List<Order> getOrdersMatchingCriteria(OrderCriteria criteria) {
        return orderRepository.getOrdersMatchingCriteria(criteria);
    }
}
