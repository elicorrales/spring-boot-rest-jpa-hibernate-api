package com.eli.spring.boot.rest.jpa.hibernate.api.app.service.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.repository.CustomerJpaRepository;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerJpaRepository repository;

    @Override
    public int addCustomer(Customer customer) {
        //if  ("1".equals("1")) throw new RuntimeException("Fake Error");
        //if  ("1".equals("1")) throw new ConstraintViolationException("Fake Error", null);
        Customer saved = repository.save(customer);
        return saved.getId();
    }

    @Override
    public List<Customer> findAll() {
        //if  ("1".equals("1")) throw new RuntimeException("Fake Error");
        //if  ("1".equals("1")) throw new ConstraintViolationException("Constraint Violation", null);
        //if  ("1".equals("1")) throw new ValidationException("Validation Violation", null);
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        //if  ("1".equals("1")) throw new RuntimeException("Fake Error");
        //if  ("1".equals("1")) throw new ConstraintViolationException("Fake Error", null);
        return repository.getOne(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        //if  ("1".equals("1")) throw new RuntimeException("Fake Error");
        //if  ("1".equals("1")) throw new ConstraintViolationException("Fake Error", null);
        return repository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        //if  ("1".equals("1")) throw new RuntimeException("Fake Error");
        //if  ("1".equals("1")) throw new ConstraintViolationException("Fake Error", null);
        repository.deleteById(id);
    }
}