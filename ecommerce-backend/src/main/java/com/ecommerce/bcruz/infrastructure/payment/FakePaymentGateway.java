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
 * Fake implementation of the PaymentGateway interface used for testing.
 * 
 * StripePaymentGateway talks directly to Stripe's servers. 
 * FakePaymentGateway solves this problem by acting like a payment provider
 * completely in memory.
 * 
 * ---------------------------------------------------------------------------
 * 
 * Instead of calling Stripe:
 *
 * - createPaymentIntent() creates a fake PaymentIntent object
 * - retrievePaymentIntent() retrieves it from memory
 * - refundPayment() creates a fake Refund object
 *
 * Everything happens locally inside the application.
 * 
 * ---------------------------------------------------------------------------
 * 
 * Use FakePaymentGateway:
 *
 * - unit tests
 * - integration tests
 * - local development
 * - CI/CD pipelines
 *
 * Use StripePaymentGateway:
 *
 * - production
 * - real payment testing
 * - staging environments
 * 
 */

@Service
@Profile("test")
public class FakePaymentGateway implements PaymentGateway
{
	private final Map<String, PaymentIntentResult> paymentStorage = new ConcurrentHashMap<>();
    private final Map<String, RefundResult> refundStorage = new ConcurrentHashMap<>();
    
    public FakePaymentGateway() 
    {
        System.out.println("Using FakePaymentGateway");
    }
    
    /**
     * Creates a fake payment intent instead of calling Stripe.
     */
    @Override
    public PaymentIntentResult createPaymentIntent(long amount, String currency, Map<String, String> metadata) 
    {
        PaymentIntentResult intent = new PaymentIntentResult();
        
        String id = "pi_fake_" + UUID.randomUUID();

        intent.setId(id);
        intent.setClientSecret(
            id + "_secret_" + UUID.randomUUID()
        );
        
        intent.setAmount(amount);
        intent.setCurrency(currency);
        intent.setStatus(PaymentStatus.SUCCEEDED);
        intent.setMetadata(metadata);

        paymentStorage.put(intent.getId(), intent);

        return intent;
    }
    
    /**
     * Retrieves a fake payment intent from memory.
     */
    @Override
    public PaymentIntentResult retrievePaymentIntent(String paymentIntentId) 
    {
    	PaymentIntentResult intent = paymentStorage.get(paymentIntentId);

        if (intent == null) {
            throw new RuntimeException("Fake PaymentIntent not found: " + paymentIntentId);
        }

        return intent;
    }
    
    /**
     * Creates a fake refund object.
     */
    @Override
    public RefundResult refundPayment(String paymentIntentId, long amount) 
    {
        PaymentIntentResult intent = paymentStorage.get(paymentIntentId);

        if (intent == null) {
            throw new RuntimeException("Cannot refund non-existent PaymentIntent: " + paymentIntentId);
        }

        RefundResult refund = new RefundResult();

        refund.setId("re_fake_" + UUID.randomUUID());
        refund.setAmount(amount);
        refund.setStatus(RefundStatus.SUCCEEDED);
        refund.setPaymentIntentId(paymentIntentId);

        refundStorage.put(refund.getId(), refund);

        // optionally update payment status
        intent.setStatus(PaymentStatus.REFUNDED);

        return refund;
    }
}
