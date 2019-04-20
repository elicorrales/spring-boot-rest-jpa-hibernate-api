package com.eli.spring.boot.rest.jpa.hibernate.api.app.repository;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer,Integer> {

}