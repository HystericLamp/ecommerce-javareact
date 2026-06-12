package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import com.ecommerce.bcruz.infrastructure.models.PaymentIntentResult;
import com.ecommerce.bcruz.infrastructure.models.RefundResult;

public interface PaymentGateway
{
	PaymentIntentResult createPaymentIntent(long amount, String currency, Map<String, String> metadata);
	PaymentIntentResult retrievePaymentIntent(String paymentIntentId);
	RefundResult refundPayment(String paymentIntentId, long amount);
}
