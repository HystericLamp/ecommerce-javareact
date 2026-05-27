package com.ecommerce.bcruz.infrastructure.payment.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ecommerce.bcruz.BcruzApplication;
import com.ecommerce.bcruz.infrastructure.payment.PaymentGateway;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

@SpringBootTest(classes=BcruzApplication.class)
@ActiveProfiles("test")
public class PaymentGatewayIntegrationTest
{
	@Autowired
    private PaymentGateway paymentGateway;
	
	@Test
	@DisplayName("AC-PAYMENT-INTEGRATION-01: Process Successful Payment")
	void Integration_CreateAndRetrievePaymentIntent() throws Exception
	{
		long amount = 1500L;
        String currency = "usd";

        Map<String, String> metadata = new HashMap<>();
        metadata.put("orderId", "123");
        
        PaymentIntent created =
            paymentGateway.createPaymentIntent(
				amount,
				currency,
				metadata
            );
        
        assertNotNull(created);
        assertNotNull(created.getId());

        assertEquals(amount, created.getAmount());
        assertEquals(currency, created.getCurrency());
        
        PaymentIntent retrieved = paymentGateway.retrievePaymentIntent(created.getId());
        
        assertNotNull(retrieved);

        assertEquals(created.getId(), retrieved.getId());
        assertEquals(amount, retrieved.getAmount());
        assertEquals(currency, retrieved.getCurrency());

        assertEquals("123", retrieved.getMetadata().get("orderId"));
	}
	
	@Test
	@DisplayName("AC-PAYMENT-INTEGRATION-02: Refund Payment")
	void Integration_RefundPayment() throws Exception
	{
        PaymentIntent paymentIntent =
            paymentGateway.createPaymentIntent(
                    1000L,
                    "usd",
                    null
            );

        Refund refund =
            paymentGateway.refundPayment(
                    paymentIntent.getId(),
                    1000L
            );

        assertNotNull(refund);
        assertNotNull(refund.getId());
        assertEquals(1000L, refund.getAmount());

        assertEquals(
            paymentIntent.getId(),
            refund.getPaymentIntent()
        );
	}
}
