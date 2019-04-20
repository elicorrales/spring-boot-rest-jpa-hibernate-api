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




    
}