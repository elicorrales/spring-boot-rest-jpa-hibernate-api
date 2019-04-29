package com.eli.spring.boot.rest.jpa.hibernate.api.app.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.dto.CustomerDTO;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;
import com.eli.spring.boot.rest.jpa.hibernate.api.app.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        System.err.println("\n\n\nget customers....");
        // try {
        // List<Customer> customers = customerService.findAll(); //this causes eager
        // loading (or exception if open-in-view is false)
        List<CustomerDTO> customers = customerService.findAll();
        System.err.println("got customers\n\n\n");
        return ResponseEntity.ok().body(customers);
        /*
         * } catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @GetMapping(params = { "page", "size", "dir", "sort" })
    public ResponseEntity<?> getPageOfCustomers(@RequestParam("page") int page, @RequestParam("size") int size,
            @RequestParam("dir") String direction, @RequestParam("sort") String sortBy) {
        System.err.println("\n\n\nget customers (page)\n\n\n");
        Page<Customer> customers = customerService.findAllPaged(page, size, Direction.valueOf(direction), sortBy);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        System.err.println("\n\n\n get customer id " + id + "\n\n\n");
        // try {
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.ok().body(new CustomerDTO(customer));
        /*
         * } catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        System.err.println("\n\n\n add customer " + customer + "\n\n\n");
        // try {
        int newCustomerId = customerService.addCustomer(customer);
        // String message = "Added customer " + customer + ", id = " + newCustomerId +
        // "\n";
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomerId)
                .toUri();
        System.err.println(location);
        return ResponseEntity.created(location).build();
        // return ResponseEntity.created(location).body(message);
        /*
         * } catch (ConstraintViolationException e) { e.printStackTrace(System.err);
         * Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
         * List<String> messages = new ArrayList<>(); violations.stream().forEach(
         * action -> { messages.add(action.getMessage()); }); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages); }
         * catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<?> addOrder(@PathVariable int id, @RequestBody Order order) {
        System.err.println("\n\n\n add order " + order + "  to cutomer id " + id);
        // try {
        System.err.println("\tfirst, get customer for id...");
        Customer customer = customerService.getCustomer(id);
        System.err.println("\tsecond, fix any issues with incoming new order...");
        if (order.getNumber() == null || order.getNumber().isEmpty()) {
            order.setNumber(createOrderNumber(customer, order));
        }
        if (order.getDateCreated() == null || order.getDateCreated().getTime() == 0) {
            order.setDateCreated(new Timestamp(new Date().getTime()));
        }
        System.err.println("\tthird, add new order to customer object...");
        customer.addOrder(order);
        System.err.println("\tfinally, update customer object (hibernate)...");
        Customer updated = customerService.updateCustomer(customer);
        String message = "Updated customer " + customer + ", id = " + updated.getId();
        return ResponseEntity.ok().body(message);
        /*
         * } catch (ConstraintViolationException e) { e.printStackTrace(System.err);
         * Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
         * List<String> messages = new ArrayList<>(); violations.stream().forEach(
         * action -> { messages.add(action.getMessage()); }); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages); }
         * catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @PutMapping("/{id}/order")
    public ResponseEntity<?> editOrder(@PathVariable int id, @RequestBody Order orderUpdate) {
        System.err.println("\n\n\n edit order " + orderUpdate + "  to cutomer id " + id + "\n\n\n");
        // try {
        Customer customer = customerService.getCustomer(id);
        if (orderUpdate.getNumber() == null || orderUpdate.getNumber().isEmpty()) {
            throw new IllegalArgumentException("Bad/Missing Order Number");
        }
        customer.setNewOrderInfo(orderUpdate);
        Customer updated = customerService.updateCustomer(customer);
        String message = "Updated order " + orderUpdate.getNumber() + ", id = " + updated.getId() + ", for Customer "
                + id;
        return ResponseEntity.ok().body(message);
        /*
         * } catch (ConstraintViolationException e) { e.printStackTrace(System.err);
         * Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
         * List<String> messages = new ArrayList<>(); violations.stream().forEach(
         * action -> { messages.add(action.getMessage()); }); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messages); }
         * catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer newStuff) {
        System.err.println("\n\n\n update customer with " + newStuff + "\n\n\n");
        // try {
        Customer original = customerService.getCustomer(newStuff.getId());
        original.setNewCustomerInfo(newStuff);
        Customer updated = customerService.updateCustomer(original);
        //String message = "Updated customer " + newStuff + ", id = " + updated.getId();
        return ResponseEntity.ok().body(new CustomerDTO(updated));
        /*
         * } catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        System.err.println("\n\n\n delete customer " + id + "\n\n\n");
        // try {
        customerService.deleteCustomer(id);
        //String message = "Deleted customer  id = " + id;
        return ResponseEntity.ok().build();
        /*
         * } catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllCustomers() {
        System.err.println("\n\n\n delete all customers\n\n\n");
        // try {
        customerService.deleteAllCustomers();
        // String message = "Deleted All Customers";
        // return ResponseEntity.ok().body(message);
        return ResponseEntity.ok().build();
        /*
         * } catch (Exception e) { e.printStackTrace(System.err); Throwable t =
         * ExceptionStackRootCause.getRootCause(e); return
         * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
         * MessageResponse(t.getMessage())); }
         */
    }

    @GetMapping(path="/count")
    public ResponseEntity<?> getNumberOfCustomers() {
        System.err.println("\n\n\n get number of customers\n\n\n");
        // try {
        long number = customerService.getNumberOfCustomers();
        return ResponseEntity.ok().body("{\"count\":\""+number+"\"}");
        /*
        } catch (Exception e) {
            e.printStackTrace(System.err);
            Throwable t = ExceptionStackRootCause.getRootCause(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(t.getMessage()));
        }
        */
    }


    private String createOrderNumber(Customer customer, Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%10d",customer.getId()));
        builder.append("-");
        builder.append(new Date().getTime());
        return builder.toString();
    }
}