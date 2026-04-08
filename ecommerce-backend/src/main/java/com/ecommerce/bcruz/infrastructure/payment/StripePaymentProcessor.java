package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.infrastructure.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class StripePaymentProcessor implements PaymentProcessor
{
	private final StripeService stripeService;
	
	public StripePaymentProcessor(StripeService stripeService)
	{
		this.stripeService = stripeService;
	}
	
	@Override
	public PaymentIntent createPayment(long amount, String currencyType, Map<String, String> metadata)
	{
		try
		{
			return stripeService.createPaymentIntent(amount, currencyType, metadata);
		} 
		catch (StripeException e)
		{
			throw new RuntimeException("Failed to create Stripe payment", e);
		}
	}
	
	@Override
	public boolean confirmPayment(String paymentID)
	{
		try
		{
			PaymentIntent intent = stripeService.retrievePaymentIntent(paymentID);
			
			return "succeeded".equals(intent.getStatus());
		} 
		catch (StripeException e)
		{
			return false;
		}
	}

	@Override
	public boolean refundPayment(String paymentID)
	{
		try
		{
			stripeService.refundPayment(paymentID);
			return true;
		} 
		catch (StripeException e)
		{
			return false;
		}
	}
}
