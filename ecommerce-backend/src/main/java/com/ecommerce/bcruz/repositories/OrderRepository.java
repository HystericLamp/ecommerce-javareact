package com.ecommerce.bcruz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> 
{}
