package com.eli.spring.boot.rest.jpa.hibernate.api.app.repository;

import java.util.List;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order,Integer> {

    List<Order> findByCustomerId(int id);
}