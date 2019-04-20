package com.eli.spring.boot.rest.jpa.hibernate.api.app.service.impl;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.repository.OrderJpaRepository;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderJpaRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}