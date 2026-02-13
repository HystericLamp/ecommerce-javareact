package infrastructure.service;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import config.StripeConfig;

@Service
public class StripeService
{
	private final StripeConfig stripeConfig;
	
	public StripeService(StripeConfig stripeConfig)
	{
		this.stripeConfig = stripeConfig;
		Stripe.apiKey = stripeConfig.getSecretKey();
	}
	
	public PaymentIntent createPaymentIntent(long amount, String currency) throws StripeException
	{
		PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
											.setAmount(amount)
											.setCurrency(currency)
											.setAutomaticPaymentMethods(
													PaymentIntentCreateParams.AutomaticPaymentMethods
														.builder()
														.setEnabled(true)
														.build()
											)
											.build();
		return PaymentIntent.create(params);
	}
	
	public PaymentIntent retrievePaymentIntent(String paymentIntentID) throws StripeException
	{
		return PaymentIntent.retrieve(paymentIntentID);
	}
	
	public void refundPayment(String paymentIntentID) throws StripeException
	{
		com.stripe.param.RefundCreateParams params = 
				com.stripe.param.RefundCreateParams.builder()
					.setPaymentIntent(paymentIntentID)
					.build();
		
		com.stripe.model.Refund.create(params);
	}
}
