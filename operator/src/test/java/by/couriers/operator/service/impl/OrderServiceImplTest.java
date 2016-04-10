package by.couriers.operator.service.impl;

import by.couriers.operator.TestConfig;
import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import by.couriers.operator.model.Product;
import by.couriers.operator.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void simpleTest() throws Exception {
        Order order = new Order();
        order.setAddress("Boston");
        order.setContactPhoneNumber("+222");
        order.setAcceptanceDateTime(LocalDateTime.now());
        Product product = new Product();
        product.setName("t-shirt");
        product.setPrice(40);
        product.setQuantity(2);
        order.getProductList().add(product);
        String orderId = orderService.createOrder(order);
        assertNotNull(orderId);
        order.setOrderId(orderId);
        Order persistedOrder = orderService.getOrderById(orderId);
        assertEquals(order, persistedOrder);
        order.setContactPhoneNumber("+333");
        orderService.updateOrder(order);
        persistedOrder = orderService.getOrderById(orderId);
        assertEquals(order, persistedOrder);
        OrderCriteria criteria = new OrderCriteria.OrderCriteriaBuilder().contactPhoneNumber("+333").build();
        List<Order> ordersMatchingCriteria = orderService.getOrdersMatchingCriteria(criteria);
        assertNotNull(ordersMatchingCriteria);
        assertEquals(1, ordersMatchingCriteria.size());
        assertEquals(persistedOrder, ordersMatchingCriteria.get(0));
        OrderCriteria deleteAllCriteria = new OrderCriteria();
        List<Order> deleteOrders = orderService.getOrdersMatchingCriteria(deleteAllCriteria);
        deleteOrders.forEach(o -> orderService.deleteOrder(o.getOrderId()));
        List<Order> ordersAfterDelete = orderService.getOrdersMatchingCriteria(deleteAllCriteria);
        assertTrue(ordersAfterDelete.isEmpty());
    }

}