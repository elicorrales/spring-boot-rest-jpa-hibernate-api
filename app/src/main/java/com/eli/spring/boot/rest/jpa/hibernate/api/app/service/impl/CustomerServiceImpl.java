package com.eli.spring.boot.rest.jpa.hibernate.api.app.service.impl;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.repository.CustomerJpaRepository;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerJpaRepository repository;

    @Override
    public int addCustomer(Customer customer) {
        Customer saved = repository.save(customer);
        return saved.getId();
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Customer> findAll(int size, int page, Direction direction, String sortBy) {
        Sort sort = null;
        switch (direction) {
            case DESC:
                    sort = Sort.by(sortBy).descending();
                    break;
             case ASC:
                    sort = Sort.by(sortBy).ascending();
                    break;
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    @Override
    public List<Customer> findAllMatching() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(int id) {
        return repository.getOne(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        repository.deleteById(id);
    }
}