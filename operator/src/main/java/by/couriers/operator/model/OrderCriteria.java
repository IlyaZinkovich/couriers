package by.couriers.operator.model;

import java.time.LocalDateTime;

public class OrderCriteria {

    private String contactPhoneNumber;
    private String address;
    private LocalDateTime acceptanceDateTime;
    private String sort;
    private Integer skip;
    private Integer limit;

    public OrderCriteria() {

    }

    private OrderCriteria(String contactPhoneNumber, String address, LocalDateTime acceptanceDateTime, String sort, Integer skip, Integer limit) {
        this.contactPhoneNumber = contactPhoneNumber;
        this.address = address;
        this.acceptanceDateTime = acceptanceDateTime;
        this.sort = sort;
        this.skip = skip;
        this.limit = limit;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAcceptanceDateTime(LocalDateTime acceptanceDateTime) {
        this.acceptanceDateTime = acceptanceDateTime;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public Integer getSkip() {
        return skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getAcceptanceDateTime() {
        return acceptanceDateTime;
    }

    public class OrderCriteriaBuilder {

        private String contactPhoneNumber;
        private String address;
        private LocalDateTime acceptanceDateTime;
        private String sort;
        private Integer skip;
        private Integer limit;

        public OrderCriteriaBuilder() {
        }

        public OrderCriteriaBuilder contactPhoneNumber(String contactPhoneNumber) {
            this.contactPhoneNumber = contactPhoneNumber;
            return this;
        }

        public OrderCriteriaBuilder address(String address) {
            this.address = address;
            return this;
        }

        public OrderCriteriaBuilder acceptanceDateTime(LocalDateTime acceptanceDateTime) {
            this.acceptanceDateTime = acceptanceDateTime;
            return this;
        }

        public OrderCriteriaBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public OrderCriteriaBuilder skip(Integer skip) {
            this.skip = skip;
            return this;
        }

        public OrderCriteriaBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public OrderCriteria build() {
            return new OrderCriteria(contactPhoneNumber, address, acceptanceDateTime, sort, skip, limit);
        }
    }
}
