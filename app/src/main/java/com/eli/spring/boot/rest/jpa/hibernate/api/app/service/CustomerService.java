package com.eli.spring.boot.rest.jpa.hibernate.api.app.service;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;

public interface CustomerService {

    int addCustomer(Customer customer);
}