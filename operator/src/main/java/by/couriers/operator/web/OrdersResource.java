package by.couriers.operator.web;

import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import by.couriers.operator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrdersResource {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public String createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.PUT)
    public void updateOrder(@PathVariable("orderId") String orderId, Order order) {
        order.setOrderId(orderId);
        orderService.updateOrder(order);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getOrdersMatchingCriteria(@RequestParam(required = false) OrderCriteria orderCriteria) {
        if (orderCriteria == null) orderCriteria = new OrderCriteria();
        return orderService.getOrdersMatchingCriteria(orderCriteria);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("orderId") String orderId) {
        orderService.deleteOrder(orderId);
    }

}
