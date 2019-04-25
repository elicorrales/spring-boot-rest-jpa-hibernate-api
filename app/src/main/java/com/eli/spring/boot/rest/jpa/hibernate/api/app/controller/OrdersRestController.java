package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.OrderService;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.utils.ExceptionStackRootCause;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrdersRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders() {
        System.err.println("\n\n\nget orders\n\n\n");
        try {
            List<Order> orders = orderService.findAll();
            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getOrdersForCustomer(@PathVariable int id) {
        System.err.println("\n\n\nget customer " + id + " orders\n\n\n");
        try {
            List<Order> orders = orderService.findAllOrdersForCustomer(id);
            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        System.err.println("\n\n\n delete order id " + id + "\n\n\n");
        try {
            orderService.deleteOrder(id);
            String message = "Delete Order id : " + id;
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        System.err.println("\n\n\n get order id " + id + "\n\n\n");
        try {
            Order order = orderService.getOrder(id);
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }
}