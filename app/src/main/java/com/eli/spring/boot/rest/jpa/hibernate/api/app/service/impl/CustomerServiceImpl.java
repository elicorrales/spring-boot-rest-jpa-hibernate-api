package com.eli.spring.boot.rest.jpa.hibernate.api.app.service.impl;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.repository.CustomerJpaRepository;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerJpaRepository repository;

    public int addCustomer(Customer customer) {
        Customer saved = repository.save(customer);
        return saved.getId();
    }
}