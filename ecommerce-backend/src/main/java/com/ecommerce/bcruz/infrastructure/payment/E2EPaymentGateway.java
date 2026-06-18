package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ecommerce.bcruz.infrastructure.enums.PaymentStatus;
import com.ecommerce.bcruz.infrastructure.enums.RefundStatus;
import com.ecommerce.bcruz.infrastructure.models.PaymentIntentResult;
import com.ecommerce.bcruz.infrastructure.models.RefundResult;

/**
 * Implementation of the PaymentGateway interface used for E2E testing.
 */

@Service
@Profile("e2e")
public class E2EPaymentGateway implements PaymentGateway
{
	private final Map<String, PaymentIntentResult> payments = new ConcurrentHashMap<>();

	public E2EPaymentGateway() 
	{
	    System.out.println("Using E2EPaymentGateway");
	}
	
	@Override
	public PaymentIntentResult createPaymentIntent(long amount, String currency, Map<String, String> metadata)
	{
		PaymentIntentResult payment = new PaymentIntentResult();

        payment.setId("pi_e2e_" + UUID.randomUUID());
        payment.setClientSecret("e2e_secret");
        payment.setAmount(amount);
        payment.setCurrency(currency);
        payment.setStatus(PaymentStatus.SUCCEEDED);
        payment.setMetadata(metadata);

        payments.put(payment.getId(), payment);

        return payment;

	}

	@Override
	public PaymentIntentResult retrievePaymentIntent(String paymentIntentId)
	{
		PaymentIntentResult payment = payments.get(paymentIntentId);

        if (payment == null)
        {
            throw new RuntimeException("Payment not found: " + paymentIntentId);
        }

        return payment;
	}

	@Override
	public RefundResult refundPayment(String paymentIntentId, long amount)
	{
		PaymentIntentResult payment = payments.get(paymentIntentId);

        if (payment == null)
        {
            throw new RuntimeException(
                "Payment not found: " + paymentIntentId);
        }

        RefundResult refund = new RefundResult();

        refund.setId("re_e2e_" + UUID.randomUUID());
        refund.setPaymentIntentId(paymentIntentId);
        refund.setAmount(amount);
        refund.setStatus(RefundStatus.SUCCEEDED);
        
        return refund;
	}
	
}
