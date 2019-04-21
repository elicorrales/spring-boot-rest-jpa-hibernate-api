package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.model.MessageResponse;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.utils.ExceptionStackRootCause;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;


    @GetMapping({ "/", "" })
    public ResponseEntity<?> getCustomers() {
        System.err.println("\n\n\nget customers\n\n\n");
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable String id) {
        System.err.println("\n\n\n get customer id " + id + "\n\n\n");
        return new ResponseEntity<>("get customer " + id + "\n",HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        System.err.println("\n\n\n add customer " + customer + "\n\n\n");
        try {
            int newCustomerId = customerService.addCustomer(customer);
            return new ResponseEntity<>("Added customer " + customer + ", id = " + newCustomerId + "\n",HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new MessageResponse("Already Exists"),HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/customer/{id}/order")
    public ResponseEntity<?> addCustomer(@PathVariable int id, @RequestBody Order order) {
        System.err.println("\n\n\n add order " + order + "  to cutomer id " + id + "\n\n\n");
        try {
            Customer customer = customerService.getCustomer(id);
            customer.addOrder(order);
            Customer updated = customerService.updateCustomer(customer);
            return new ResponseEntity<>(new MessageResponse("Updated customer " + customer + ", id = " + updated.getId()),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return new ResponseEntity<>(new MessageResponse(t.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        System.err.println("\n\n\n update customer " + customer + "\n\n\n");
        return new ResponseEntity<>("update customer " + customer + "\n",HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        System.err.println("\n\n\n delete customer " + id + "\n\n\n");
        return new ResponseEntity<>("delete customer " + id + "\n",HttpStatus.OK);
    }

}