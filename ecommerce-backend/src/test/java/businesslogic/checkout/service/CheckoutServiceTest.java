package businesslogic.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import businesslogic.checkout.CheckoutService;

public class CheckoutServiceTest
{
	private CheckoutService checkService;
	
	@BeforeEach
	void setUp()
	{
		checkService = new CheckoutService();
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order")
    void checkout_getOrder() 
	{
		// Make an Order with all Items from Cart
		
		// List all Items and quantities
		
		// Gather totals
		
        assert(false);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-02: Update Order")
    void checkout_updateOrder() 
	{
		// Make an Order with all Items from Cart
		
		// List all Items and quantities
		
		// Update a quantity of an Item
		
		// Remove an Item from Order
		
        assert(false);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-03: Process Successful Payment")
    void checkout_processSuccessfulPayment() 
	{
		// With an Order make a call to payment provider with total
		
		// Assert a successful payment
		
        assert(false);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-04: Failed Payment")
    void checkout_failedPayment() 
	{
		// With an Order make a call to payment provider with total
		
		// Assert a failed payment
		
        assert(false);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-05: Retry Payment")
    void checkout_retryPaymentAfterFail() 
	{
		// With an Order make a call to payment provider with total
		
		// Assert a failed payment
		
		// Redo payment process
		
		// Assert successful payment
		
        assert(false);
    }
}
