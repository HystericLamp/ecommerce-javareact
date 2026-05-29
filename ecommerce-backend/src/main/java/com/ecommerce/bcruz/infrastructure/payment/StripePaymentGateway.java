package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;


@Service
@Profile("prod")
public class StripePaymentGateway implements PaymentGateway
{

	@Override
	public PaymentIntent createPaymentIntent(long amount, String currency, Map<String, String> metadata)
	{
		PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency(currency)
                        .putAllMetadata(metadata)
                        .build();
		
		try
		{
			return PaymentIntent.create(params);
		} 
		catch (StripeException e)
		{
			throw new RuntimeException("Stripe payment intent creation failed", e);
		}
	}

	@Override
	public PaymentIntent retrievePaymentIntent(String paymentIntentId)
	{
		try
		{
			return PaymentIntent.retrieve(paymentIntentId);
		} 
		catch (StripeException e)
		{
			throw new RuntimeException("Stripe payment intent retrieval failed", e);
		}
	}

	@Override
	public Refund refundPayment(String paymentIntentId, long amount)
	{
		try
		{
			RefundCreateParams params =
	                RefundCreateParams.builder()
	                        .setPaymentIntent(paymentIntentId)
	                        .setAmount(amount)
	                        .build();

        
			return Refund.create(params);
		} 
		catch (StripeException e)
		{
			throw new RuntimeException("Stripe refund failed", e);
		}
	}
}
