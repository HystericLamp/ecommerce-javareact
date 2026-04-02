package com.ecommerce.bcruz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.DraftOrder;

public interface DraftOrderRepository extends JpaRepository<DraftOrder, Long> 
{}
