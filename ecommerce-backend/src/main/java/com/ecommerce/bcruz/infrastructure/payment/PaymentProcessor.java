package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import com.stripe.model.PaymentIntent;

public interface PaymentProcessor
{
	PaymentIntent createPayment(long amount, String currencyType, Map<String, String> metadata);
	boolean confirmPayment(String paymentID);
	boolean refundPayment(String paymentID);
}
