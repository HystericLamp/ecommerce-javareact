package com.ecommerce.bcruz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;
import com.ecommerce.bcruz.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController
{
	private final CheckoutService checkoutService;
	private final PaymentProcessor paymentProcessor;
	
	public CheckoutController(CheckoutService checkoutService, PaymentProcessor paymentProcessor)
	{
		this.checkoutService = checkoutService;
		this.paymentProcessor = paymentProcessor;
	}
	
	@PostMapping
	public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request)
	{
		
		
		return null;
	}
}
