package com.eli.spring.boot.rest.jpa.hibernate.api.app.service;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;

public interface OrderService {

    List<Order> findAll();
    Order getOrder(int id);
}