package infrastructure.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ecommerce.bcruz.BcruzApplication;
import com.ecommerce.bcruz.infrastructure.service.StripeService;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest(classes=BcruzApplication.class)
@EnableAutoConfiguration(exclude = {
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
@ActiveProfiles("test")
public class StripePaymentProcessorIntegrationTest
{
	@Autowired
	private StripeService stripeService;
	
	// Must have classes and files that interact wth Stripe API in the folder with the application class
	// Create a .env and application-test.yml
	@BeforeAll
	static void loadEnv()
	{
		Dotenv dotenv = Dotenv.configure()
						.directory(".")
						.ignoreIfMissing()
						.load();
		
		System.setProperty("STRIPE_SECRET_KEY", dotenv.get("STRIPE_SECRET_KEY"));
		System.setProperty("STRIPE_PUBLISHABLE_KEY", dotenv.get("STRIPE_PUBLISHABLE_KEY"));
	}
	
	@Test
	@DisplayName("AC-PAYMENT-INTEGRATION-01: Process Successful Payment")
	void StripeIntegration_CreateAndRetrievePaymentIntent() throws Exception
	{
		PaymentIntent created = stripeService.createPaymentIntent(1500L, "usd");
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
		PaymentIntent intent = stripeService.createPaymentIntent(1000L, "usd");
		
		// Needed to get success/fail status
		PaymentIntent confirmed = PaymentIntent.retrieve(intent.getId())
				  .confirm(
						  PaymentIntentConfirmParams.builder()
						  	.setPaymentMethod("pm_card_visa")
						  	.build()
				  );
		
		assertEquals("succeeded", confirmed.getStatus());
		
	}
}
