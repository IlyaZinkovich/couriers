package by.couriers.operator;


import by.couriers.operator.dao.OrderDAO;
import by.couriers.operator.model.LocalDateTimeConverter;
import by.couriers.operator.model.Order;
import by.couriers.operator.model.Product;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.couriers.operator")
public class AppConfig {

    private static final String MONGO_URI = "mongodb://localhost";
    private static final String DB_NAME = "couriers";
    private static final String COLLECTION_NAME = "orders";

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new MongoClientURI(MONGO_URI));
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(DB_NAME);
    }

    @Bean
    public MongoCollection ordersMongoCollection() {
        return mongoDatabase().getCollection(COLLECTION_NAME);
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAO(Order.class, datastore());
    }

    @Bean
    public Morphia morphia() {
        Morphia morphia = new Morphia();
        morphia.getMapper().getConverters().addConverter(new LocalDateTimeConverter());
        morphia.map(Order.class, Product.class);
        return morphia;
    }

    @Bean
    public Datastore datastore() {
        return morphia().createDatastore(mongoClient(), DB_NAME);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AppConfig.class, args);
    }

}