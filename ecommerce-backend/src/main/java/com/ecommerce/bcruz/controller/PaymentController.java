package com.ecommerce.bcruz.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;

@RestController
@RequestMapping("/api/payments")
public class PaymentController
{
	private final PaymentProcessor paymentProcessor;
	
	public PaymentController(PaymentProcessor paymentProcessor)
	{
		this.paymentProcessor = paymentProcessor;
	}
	
	@PostMapping("/create/{orderId}")
	public ResponseEntity<Map<String, String>> createPayment(
			@PathVariable Long orderId) throws Exception
	{
		// Temporary until DB is made
		Long amountInCents = 5000L;
		String currency = "usd";
		
		String clientSecret = paymentProcessor.createPayment(amountInCents, currency);
		
		return ResponseEntity.ok(Map.of("clientSecret", clientSecret));
	}
}
