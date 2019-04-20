package com.eli.spring.boot.rest.jpa.hibernate.api.app.repository;

import com.eli.spring.boot.rest.jpa.hibernate.api.app.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order,Integer> {

}