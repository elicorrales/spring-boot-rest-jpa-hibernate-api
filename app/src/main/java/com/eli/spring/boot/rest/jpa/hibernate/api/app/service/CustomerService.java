package com.eli.spring.boot.rest.jpa.hibernate.api.app.service;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;

public interface CustomerService {

    int addCustomer(Customer customer);
    List<Customer> findAll();
    Customer getCustomer(int id);
    Customer updateCustomer(Customer customer);
}