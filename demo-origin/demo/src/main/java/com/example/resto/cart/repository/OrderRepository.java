package com.example.resto.cart.repository;

import  com.example.resto.cart.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.name = :name and o.status = 'NEW'")
    Order findByUsername(String name);
}
