package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

public interface PaymentGateway
{
	PaymentIntent createPaymentIntent(long amount, String currency, Map<String, String> metadata);
	
	PaymentIntent retrievePaymentIntent(String paymentIntentId);
	
	Refund refundPayment(String paymentIntentId, long amount);
}
