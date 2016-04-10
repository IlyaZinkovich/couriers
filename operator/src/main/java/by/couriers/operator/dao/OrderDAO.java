package by.couriers.operator.dao;

import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.time.LocalDate;
import java.util.List;


public class OrderDAO extends BasicDAO<Order, ObjectId> {
    public OrderDAO(Class<Order> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public List<Order> getOrdersMatchingCriteria(OrderCriteria criteria) {
        Query<Order> query = this.getDatastore().createQuery(Order.class);
        String address = criteria.getAddress();
        if (address != null) query = query.filter("address", address);
        LocalDate acceptanceDateTime = criteria.getAcceptanceDate();
        if (acceptanceDateTime != null) query = query.filter("acceptanceDate", acceptanceDateTime.toString());
        String contactPhoneNumber = criteria.getContactPhoneNumber();
        if (contactPhoneNumber != null) query = query.filter("contactPhoneNumber", contactPhoneNumber);
        String sort = criteria.getSort();
        if (sort != null) query = query.order(sort);
        Integer skip = criteria.getSkip();
        if (skip != null) query = query.offset(skip);
        Integer limit = criteria.getLimit();
        if (limit != null) query = query.limit(limit);
        return this.find(query).asList();
    }

    public void updateOrder(Order order) {
        Query<Order> query = this.getDatastore().find(Order.class).field("_id").equal(new ObjectId(order.getOrderId()));
        UpdateOperations<Order> updateOperations = this.getDatastore().createUpdateOperations(Order.class);
        updateOperations.set("address", order.getAddress());
        updateOperations.set("acceptanceDate", order.getAcceptanceDate());
        updateOperations.set("contactPhoneNumber", order.getContactPhoneNumber());
        this.update(query, updateOperations);
    }
}
