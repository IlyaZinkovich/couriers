package by.couriers.operator.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private static final long serialVersionUID = -1783170765132153711L;

    private String orderId;
    private String contactPhoneNumber;
    private String address;
    private LocalDateTime acceptanceDateTime;

    public Order() {
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

    public LocalDateTime getAcceptanceDateTime() {
        return acceptanceDateTime;
    }

    public void setAcceptanceDateTime(LocalDateTime acceptanceDateTime) {
        this.acceptanceDateTime = acceptanceDateTime;
    }
}
