package by.couriers.operator.service.impl;

import by.couriers.operator.dao.OrderDAO;
import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import by.couriers.operator.service.OrderService;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public String createOrder(Order order) {
        Key<Order> key = orderDAO.save(order);
        return (String) key.getId();
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDAO.deleteById(new ObjectId(orderId));
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderDAO.get(new ObjectId(orderId));
    }

    @Override
    public List<Order> getOrdersMatchingCriteria(OrderCriteria criteria) {
        return orderDAO.getOrdersMatchingCriteria(criteria);
    }
}
