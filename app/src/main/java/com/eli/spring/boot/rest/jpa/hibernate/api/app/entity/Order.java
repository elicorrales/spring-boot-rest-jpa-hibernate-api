package com.eli.spring.boot.rest.jpa.hibernate.api.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="number")
    private String orderNumber;
    @Column(name="description")
    private String description;
    @Column(name="order_date")
    private String orderDate;
    @Column(name="status")
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order [description=" + description + ", id=" + id + ", orderDate=" + orderDate + ", orderNumber="
                + orderNumber + ", status=" + status + "]";
    }

    
}