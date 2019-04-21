package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.OrderService;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.utils.ExceptionStackRootCause;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping({ "/", "" })
    public ResponseEntity<?> getOrders() {
        System.err.println("\n\n\nget orders\n\n\n");
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        System.err.println("\n\n\n get order id " + id + "\n\n\n");
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return new ResponseEntity<>(new MessageResponse(t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}