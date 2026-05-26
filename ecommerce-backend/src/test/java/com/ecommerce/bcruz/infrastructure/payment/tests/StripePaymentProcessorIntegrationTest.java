package com.ecommerce.bcruz.infrastructure.payment.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ecommerce.bcruz.BcruzApplication;
import com.ecommerce.bcruz.infrastructure.service.StripeService;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.RefundCreateParams;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest(classes=BcruzApplication.class)
@ActiveProfiles("test")
public class StripePaymentProcessorIntegrationTest
{
	@Autowired
	private StripeService stripeService;
	
	@Value("${stripe.secret-key}")
	private String stripeSecretKey;
	
	@Test
	@DisplayName("AC-PAYMENT-INTEGRATION-01: Process Successful Payment")
	void StripeIntegration_CreateAndRetrievePaymentIntent() throws Exception
	{
		PaymentIntent created = stripeService.createPaymentIntent(1500L, "usd", null);
		assertNotNull(created.getId());
		assertEquals(1500L, created.getAmount());
		assertEquals("usd", created.getCurrency());
		
		// Needed to get success/fail status
		PaymentIntent confirmed = PaymentIntent.retrieve(created.getId())
								  .confirm(
										  PaymentIntentConfirmParams.builder()
										  	.setPaymentMethod("pm_card_visa")
										  	.build()
								  );
		
		PaymentIntent retrieved = stripeService.retrievePaymentIntent(confirmed.getId());
		assertEquals("succeeded", retrieved.getStatus());
		assertEquals(confirmed.getId(), retrieved.getId());
		assertEquals(1500L, retrieved.getAmount());
		assertEquals("usd", retrieved.getCurrency());
	}
	
	@Test
	@DisplayName("AC-PAYMENT-INTEGRATION-02: Refund Payment")
	void StripeIntegration_RefundPayment() throws Exception
	{
		PaymentIntent intent = stripeService.createPaymentIntent(1000L, "usd", null);
		
		// Needed to get success/fail status
		PaymentIntent confirmed = PaymentIntent.retrieve(intent.getId())
				  .confirm(
						  PaymentIntentConfirmParams.builder()
						  	.setPaymentMethod("pm_card_visa")
						  	.build()
				  );
		
		assertEquals("succeeded", confirmed.getStatus());
		
		
		// Create refund and assert
		String chargeId = confirmed.getLatestCharge();
	    assertNotNull(chargeId);

	    RefundCreateParams refundParams = RefundCreateParams.builder()
	            .setCharge(chargeId)
	            .build();

	    Refund refund = Refund.create(refundParams);

	    assertNotNull(refund);
	    assertEquals("succeeded", refund.getStatus());
	    assertEquals(1000L, refund.getAmount());

	    // Optional additional validations
	    assertEquals(chargeId, refund.getCharge());
	    assertFalse(refund.getFailureReason() != null);
	}
}
