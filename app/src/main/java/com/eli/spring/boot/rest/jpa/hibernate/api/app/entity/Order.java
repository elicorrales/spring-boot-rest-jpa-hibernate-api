package com.eli.spring.boot.rest.jpa.hibernate.api.app.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="number")
    @NotEmpty(message="Order Number Required")
    private String number;
    @Column(name="description")
    @NotEmpty(message="Description Required")
    private String description;
    @Column(name="date_created")
    private Timestamp dateCreated;
    @Column(name="status")
    @NotEmpty(message="Status Required")
    private String status;
    @Column(name="customer_id")
    private int customerId;

    public Order() {}

    public Order(String number,String description, Timestamp dateCreated, String status) {
        this.number = number;
        this.description = description;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getDateCreated() { return dateCreated; }
    public void setDateCreated(Timestamp dateCreated) { this.dateCreated = dateCreated; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public void setAll(Order other) {
        this.number = other.number;
        this.description = other.description;
        this.status = other.status;
    }
    @Override
    public String toString() {
        return "Order [customerId=" + customerId + ", dateCreated=" + dateCreated + ", description=" + description
                + ", id=" + id + ", number=" + number + ", status=" + status + "]";
    }

}