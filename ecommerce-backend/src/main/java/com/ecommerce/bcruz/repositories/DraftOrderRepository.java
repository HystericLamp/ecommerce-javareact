package com.ecommerce.bcruz.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bcruz.models.DraftOrder;

public interface DraftOrderRepository extends JpaRepository<DraftOrder, Long> 
{
	Optional<DraftOrder> findByPaymentIntentId(String paymentIntentId);
}
