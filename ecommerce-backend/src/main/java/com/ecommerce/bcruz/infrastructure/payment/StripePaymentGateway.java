package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.infrastructure.enums.PaymentStatus;
import com.ecommerce.bcruz.infrastructure.enums.RefundStatus;
import com.ecommerce.bcruz.infrastructure.models.PaymentIntentResult;
import com.ecommerce.bcruz.infrastructure.models.RefundResult;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;


@Service
@Profile({"dev", "prod"})
public class StripePaymentGateway implements PaymentGateway
{	
	public StripePaymentGateway() 
	{
	    System.out.println("Using StripePaymentGateway");
	}

	@Override
	public PaymentIntentResult createPaymentIntent(long amount, String currency, Map<String, String> metadata)
	{
		PaymentIntentCreateParams params =
	        PaymentIntentCreateParams.builder()
	            .setAmount(amount)
	            .setCurrency(currency)
	            .putAllMetadata(metadata)
	            .build();

        try
        {
            PaymentIntent stripeIntent = PaymentIntent.create(params);

            return toPaymentIntentResult(stripeIntent);
        }
        catch (StripeException e)
        {
            throw new RuntimeException( "Stripe payment intent creation failed", e);
        }
	}

	@Override
	public PaymentIntentResult retrievePaymentIntent(String paymentIntentId)
	{
		try
        {
            PaymentIntent stripeIntent = PaymentIntent.retrieve(paymentIntentId);

            return toPaymentIntentResult(stripeIntent);
        }
        catch (StripeException e)
        {
            throw new RuntimeException("Stripe payment intent retrieval failed", e);
        }
	}

	@Override
	public RefundResult refundPayment(String paymentIntentId, long amount)
	{
		try
        {
            RefundCreateParams params =
	            RefundCreateParams.builder()
	                .setPaymentIntent(paymentIntentId)
	                .setAmount(amount)
	                .build();

            Refund stripeRefund = Refund.create(params);

            return toRefundResult(stripeRefund);
        }
        catch (StripeException e)
        {
            throw new RuntimeException( "Stripe refund failed", e);
        }
	}
	
	/////////////////////////
	// Utility methods
	////////////////////////
	
	private PaymentIntentResult toPaymentIntentResult(PaymentIntent stripeIntent)
    {
        PaymentIntentResult result = new PaymentIntentResult();

        result.setId(stripeIntent.getId());
        result.setClientSecret(stripeIntent.getClientSecret());
        result.setAmount(stripeIntent.getAmount());
        result.setCurrency(stripeIntent.getCurrency());

        result.setMetadata(stripeIntent.getMetadata());

        result.setStatus(mapPaymentStatus(stripeIntent.getStatus()));

        return result;
    }

    private RefundResult toRefundResult(Refund stripeRefund)
    {
        RefundResult result = new RefundResult();

        result.setId(stripeRefund.getId());
        result.setPaymentIntentId(stripeRefund.getPaymentIntent());
        result.setAmount(stripeRefund.getAmount());

        result.setStatus(mapRefundStatus(stripeRefund.getStatus()));

        return result;
    }

    private PaymentStatus mapPaymentStatus(String stripeStatus)
    {
        return switch (stripeStatus)
        {
            case "pending" ->
            	PaymentStatus.PENDING;

            case "succeeded" ->
            	PaymentStatus.SUCCEEDED;

            case "canceled" ->
            	PaymentStatus.CANCELED;
            
            case "refunded" ->
            	PaymentStatus.REFUNDED;

            default ->
            	PaymentStatus.FAILED;
        };
    }

    private RefundStatus mapRefundStatus(String stripeStatus)
    {
        return switch (stripeStatus)
        {
            case "succeeded" ->
            	RefundStatus.SUCCEEDED;
                    
            case "pending" ->
            	RefundStatus.PENDING;

            default ->
            	RefundStatus.FAILED;
        };
    }
}
