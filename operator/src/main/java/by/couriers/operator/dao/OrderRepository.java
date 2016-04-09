package by.couriers.operator.dao;

import by.couriers.operator.model.Order;
import by.couriers.operator.model.OrderCriteria;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderRepository {

    @Autowired
    private MongoCollection<Document> ordersCollection;

    public String createOrder(Order order) {
        Document orderDocument = buildDocumentFromOrder(order);
        ordersCollection.insertOne(orderDocument);
        ObjectId id = (ObjectId) orderDocument.get("_id");
        return id.toHexString();
    }

    public void updateOrder(Order order) {
        Document orderDocument = buildDocumentFromOrder(order);
        ordersCollection.updateOne(new Document().append("_id", new ObjectId(order.getOrderId())), orderDocument);
    }

    public void deleteOrder(String orderId) {
        ordersCollection.deleteOne(new Document().append("_id", new ObjectId(orderId)));
    }

    public Order getOrderById(String orderId) {
        ArrayList<Document> foundDocument = ordersCollection.find(new Document()
                .append("_id", new ObjectId(orderId))).limit(1).into(new ArrayList<>());
        if (foundDocument.isEmpty()) return null;
        return buildOrderFromDocument(foundDocument.get(0));
    }

    public List<Order> getOrdersMatchingCriteria(OrderCriteria criteria) {
        Document filter = new Document();
        String address = criteria.getAddress();
        if (address != null) filter.append("address", address);
        LocalDateTime acceptanceDateTime = criteria.getAcceptanceDateTime();
        if (acceptanceDateTime != null) filter.append("acceptanceDateTime", acceptanceDateTime.toString());
        String contactPhoneNumber = criteria.getContactPhoneNumber();
        if (contactPhoneNumber != null) filter.append("contactPhoneNumber", contactPhoneNumber);
        FindIterable<Document> ordersIterable = ordersCollection.find(filter);
        String sort = criteria.getSort();
        if (sort != null) ordersIterable = ordersIterable.sort(new Document("sort", sort));
        Integer skip = criteria.getSkip();
        if (skip != null) ordersIterable = ordersIterable.skip(skip);
        Integer limit = criteria.getLimit();
        if (limit != null) ordersIterable = ordersIterable.limit(limit);
        ArrayList<Document> ordersDocuments = ordersIterable.into(new ArrayList<>());
        return ordersDocuments.stream().map(this::buildOrderFromDocument).collect(Collectors.toList());
    }

    private Document buildDocumentFromOrder(Order order) {
        Document orderDocument = new Document();
        orderDocument.append("_id", new ObjectId(order.getOrderId()));
        orderDocument.append("contactPhoneNumber", order.getContactPhoneNumber());
        orderDocument.append("address", order.getAddress());
        orderDocument.append("acceptanceDateTime", order.getAcceptanceDateTime());
        return orderDocument;
    }

    private Order buildOrderFromDocument(Document document) {
        Order order = new Order();
        order.setOrderId(document.getObjectId("_id").toHexString());
        order.setContactPhoneNumber(document.getString("contactPhoneNumber"));
        order.setAddress(document.getString("address"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        order.setAcceptanceDateTime(LocalDateTime.parse(document.getString("acceptanceDateTime"), formatter));
        return order;
    }

}
