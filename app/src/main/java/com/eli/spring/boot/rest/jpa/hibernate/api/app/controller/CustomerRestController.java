package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.utils.ExceptionStackRootCause;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    //@Autowired
    //private OrderService orderService;


    @GetMapping
    public ResponseEntity<?> getCustomers() {
        System.err.println("\n\n\nget customers\n\n\n");
        try {
            List<Customer> customers = customerService.findAll();
            //return new ResponseEntity<>(customers,HttpStatus.OK);
            return ResponseEntity.ok().body(customers);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            //return new ResponseEntity<>(new MessageResponse(t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        System.err.println("\n\n\n get customer id " + id + "\n\n\n");
        try {
            Customer customer = customerService.getCustomer(id);
            //return new ResponseEntity<>(customer,HttpStatus.OK);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            //return new ResponseEntity<>(new MessageResponse(t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        System.err.println("\n\n\n add customer " + customer + "\n\n\n");
        try {
            int newCustomerId = customerService.addCustomer(customer);
            String message = "Added customer " + customer + ", id = " + newCustomerId + "\n";
            return ResponseEntity.ok().body(message);
        } catch (ConstraintViolationException e) {
            e.printStackTrace(System.err);
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            List<String> messages = new ArrayList<>();
            violations.stream().forEach( action -> { messages.add(action.getMessage()); });
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }


    @PostMapping("/{id}/order")
    public ResponseEntity<?> addOrder(@PathVariable int id, @RequestBody Order order) {
        System.err.println("\n\n\n add order " + order + "  to cutomer id " + id + "\n\n\n");
        try {
            Customer customer = customerService.getCustomer(id);
            if (order.getNumber() == null || order.getNumber().isEmpty()) {
                order.setNumber(createOrderNumber(customer,order));
            }
            if (order.getDateCreated() == null || order.getDateCreated().getTime() == 0) {
                order.setDateCreated(new Timestamp(new Date().getTime()));
            }
            customer.addOrder(order);
            Customer updated = customerService.updateCustomer(customer);
            String message = "Updated customer " + customer + ", id = " + updated.getId();
            return ResponseEntity.ok().body(message);
        } catch (ConstraintViolationException e) {
            e.printStackTrace(System.err);
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            List<String> messages = new ArrayList<>();
            violations.stream().forEach( action -> { messages.add(action.getMessage()); });
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }


    @PutMapping("/{id}/order")
    public ResponseEntity<?> editOrder(@PathVariable int id, @RequestBody Order orderUpdate) {
        System.err.println("\n\n\n edit order " + orderUpdate + "  to cutomer id " + id + "\n\n\n");
        try {
            Customer customer = customerService.getCustomer(id);
            if (orderUpdate.getNumber() == null || orderUpdate.getNumber().isEmpty()) {
                throw new IllegalArgumentException("Bad/Missing Order Number");
            }
            customer.setNewOrderInfo(orderUpdate);
            Customer updated = customerService.updateCustomer(customer);
            String message = "Updated order " + orderUpdate.getNumber() + ", id = " + updated.getId() + ", for Customer " + id;
            return ResponseEntity.ok().body(message);
        } catch (ConstraintViolationException e) {
            e.printStackTrace(System.err);
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            List<String> messages = new ArrayList<>();
            violations.stream().forEach( action -> { messages.add(action.getMessage()); });
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }


    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer newStuff) {
        System.err.println("\n\n\n update customer with " + newStuff + "\n\n\n");
        try {
            Customer original = customerService.getCustomer(newStuff.getId());
            original.setNewCustomerInfo(newStuff);
            Customer updated = customerService.updateCustomer(original);
            String message = "Updated customer " + newStuff + ", id = " + updated.getId();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        System.err.println("\n\n\n delete customer " + id + "\n\n\n");
        try {
            customerService.deleteCustomer(id);
            String message = "Deleted customer  id = " + id;
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
    }

    private String createOrderNumber(Customer customer, Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%10d",customer.getId()));
        builder.append("-");
        builder.append(new Date().getTime());
        return builder.toString();
    }
}