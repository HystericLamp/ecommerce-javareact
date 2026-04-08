package com.ecommerce.bcruz.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bcruz.dto.CheckoutRequest;
import com.ecommerce.bcruz.dto.PaymentResult;
import com.ecommerce.bcruz.models.DraftOrder;
import com.ecommerce.bcruz.service.CheckoutService;

@RestController
@RequestMapping("/api/shop")
public class CheckoutController
{
	private final CheckoutService checkoutService;
	
	public CheckoutController(CheckoutService checkoutService)
	{
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/draft")
    public ResponseEntity<?> createDraftOrder(@RequestBody CheckoutRequest request) 
	{

        Long userId = 1L; // Replace later

        DraftOrder draftOrder = checkoutService.createDraftOrder(request, userId);

        return ResponseEntity.ok(Map.of(
                "draftOrderId", draftOrder.getId(),
                "totalAmount", draftOrder.getTotalAmountInCents(),
                "currency", draftOrder.getCurrency()
        ));
    }
	
	@PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) 
	{
		Long userId = 1L; // Replace later

	    PaymentResult result = checkoutService.createDraftOrderAndPayment(request, userId);

	    return ResponseEntity.ok(Map.of(
	            "draftOrderId", result.getDraftOrderId(),
	            "clientSecret", result.getClientSecret()
	    ));
    }
}
