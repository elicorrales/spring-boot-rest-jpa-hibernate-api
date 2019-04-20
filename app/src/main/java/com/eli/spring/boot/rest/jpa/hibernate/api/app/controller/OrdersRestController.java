package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    @GetMapping({ "/", "" })
    public ResponseEntity<?> getOrders() {
        System.err.println("\n\n\nget orders\n\n\n");
        return new ResponseEntity<>("get orders",HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        System.err.println("\n\n\n get order id " + id + "\n\n\n");
        return new ResponseEntity<>("get order " + id + "\n",HttpStatus.OK);
    }

}