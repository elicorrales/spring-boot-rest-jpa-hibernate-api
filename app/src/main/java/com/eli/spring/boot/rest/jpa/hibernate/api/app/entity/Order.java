package com.eli.spring.boot.rest.jpa.hibernate.api.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="number")
    private String number;
    @Column(name="description")
    private String description;
    @Column(name="date_ordered")
    private String dateOrdered;
    @Column(name="status")
    private String status;
    @Column(name="customer_id")
    private int customerId;

    public Order() {}

    public Order(String number,String description, String dateOrdered,String status) {
        this.number = number;
        this.description = description;
        this.dateOrdered = dateOrdered;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDateOrdered() { return dateOrdered; }
    public void setDateOrdered(String dateOrdered) { this.dateOrdered = dateOrdered; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    @Override
    public String toString() {
        return "Order [customerId=" + customerId + ", dateOrdered=" + dateOrdered + ", description=" + description
                + ", id=" + id + ", number=" + number + ", status=" + status + "]";
    }

}