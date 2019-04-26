package com.eli.spring.boot.rest.jpa.hibernate.api.app.service;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

public interface CustomerService {

    int addCustomer(Customer customer);
    List<Customer> findAll();
    public Page<Customer> findAll(int size, int page, Direction direction, String properties); 
    List<Customer> findAllMatching();
    Customer getCustomer(int id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int id);
}