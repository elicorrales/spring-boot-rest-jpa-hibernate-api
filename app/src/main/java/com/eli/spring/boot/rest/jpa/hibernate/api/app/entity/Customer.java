package com.eli.spring.boot.rest.jpa.hibernate.api.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="fname")
    @NotEmpty(message="First Name May Not Be Empty")
    private String fname;
    @Column(name="lname")
    @NotEmpty(message="Last Name May Not Be Empty")
    private String lname;
    @Column(name="email")
    @NotEmpty(message="Email May Not Be Empty")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Order> orders;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        order.setCustomerId(this.id);
        orders.add(order);
    }
    @Override
    public String toString() {
        return "Customer [email=" + email + ", fname=" + fname + ", id=" + id + ", lname=" + lname + "]";
    }





}