package by.couriers.operator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = -1783170765132153711L;

    @Id
    private String orderId;
    private String contactPhoneNumber;
    private String address;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate acceptanceDate;
    @Embedded
    private List<Product> productList;

    public Order() {
        this.productList = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDate acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (getContactPhoneNumber() != null ? !getContactPhoneNumber().equals(order.getContactPhoneNumber()) : order.getContactPhoneNumber() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(order.getAddress()) : order.getAddress() != null) return false;
        if (getAcceptanceDate() != null ? !getAcceptanceDate().equals(order.getAcceptanceDate()) : order.getAcceptanceDate() != null)
            return false;
        return !(getProductList() != null ? !getProductList().equals(order.getProductList()) : order.getProductList() != null);

    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getContactPhoneNumber() != null ? getContactPhoneNumber().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getAcceptanceDate() != null ? getAcceptanceDate().hashCode() : 0);
        result = 31 * result + (getProductList() != null ? getProductList().hashCode() : 0);
        return result;
    }
}
