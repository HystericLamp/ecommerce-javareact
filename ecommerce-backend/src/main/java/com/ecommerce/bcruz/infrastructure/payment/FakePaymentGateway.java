package com.ecommerce.bcruz.infrastructure.payment;

import java.util.Map;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

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
@Primary
@Profile({"test", "dev"})
public class FakePaymentGateway implements PaymentGateway
{
	private final Map<String, PaymentIntent> paymentStore = new ConcurrentHashMap<>();
    private final Map<String, Refund> refundStore = new ConcurrentHashMap<>();
    
    /**
     * Creates a fake payment intent instead of calling Stripe.
     */
    @Override
    public PaymentIntent createPaymentIntent(long amount, String currency, Map<String, String> metadata) 
    {
        PaymentIntent intent = new PaymentIntent();

        intent.setId("pi_fake_" + UUID.randomUUID());
        intent.setAmount(amount);
        intent.setCurrency(currency);
        intent.setStatus("requires_payment_method");
        intent.setMetadata(metadata);

        paymentStore.put(intent.getId(), intent);

        return intent;
    }
    
    /**
     * Retrieves a fake payment intent from memory.
     */
    @Override
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) 
    {
        PaymentIntent intent = paymentStore.get(paymentIntentId);

        if (intent == null) {
            throw new RuntimeException("Fake PaymentIntent not found: " + paymentIntentId);
        }

        return intent;
    }
    
    /**
     * Creates a fake refund object.
     */
    @Override
    public Refund refundPayment(String paymentIntentId, long amount) 
    {
        PaymentIntent intent = paymentStore.get(paymentIntentId);

        if (intent == null) {
            throw new RuntimeException("Cannot refund non-existent PaymentIntent: " + paymentIntentId);
        }

        Refund refund = new Refund();

        refund.setId("re_fake_" + UUID.randomUUID());
        refund.setAmount(amount);
        refund.setStatus("succeeded");
        refund.setPaymentIntent(paymentIntentId);

        refundStore.put(refund.getId(), refund);

        // optionally update payment status
        intent.setStatus("refunded");

        return refund;
    }
}
