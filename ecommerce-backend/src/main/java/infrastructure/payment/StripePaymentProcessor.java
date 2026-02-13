package infrastructure.payment;

import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import infrastructure.service.StripeService;

@Service
public class StripePaymentProcessor implements PaymentProcessor
{
	private final StripeService stripeService;
	
	public StripePaymentProcessor(StripeService stripeService)
	{
		this.stripeService = stripeService;
	}
	
	@Override
	public String createPayment(long amount, String currencyType)
	{
		try
		{
			PaymentIntent intent = stripeService.createPaymentIntent(amount, currencyType);
			
			// Returns PaymentIntent ID
			return intent.getId();
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
