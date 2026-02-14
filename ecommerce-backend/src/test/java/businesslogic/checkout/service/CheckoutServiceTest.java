package businesslogic.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ecommerce.bcruz.infrastructure.payment.PaymentProcessor;
import com.ecommerce.bcruz.infrastructure.payment.StripePaymentProcessor;
import com.ecommerce.bcruz.infrastructure.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import businesslogic.checkout.CheckoutService;
import ecommerce.model.LineItem;
import ecommerce.exceptions.QuantityZeroOrNegativeException;
import ecommerce.model.Cart;
import ecommerce.model.DraftOrder;
import ecommerce.model.Item;
import ecommerce.model.Order;

public class CheckoutServiceTest
{
	private CheckoutService checkService;
	private Cart cart;
	
	// Helper
	private Item item(String name, String price)
	{
		return new Item(name, new BigDecimal(price));
	}
	
	@BeforeEach
	void setUp()
	{
		checkService = new CheckoutService();
		cart = new Cart();
		cart.addItemWithQuantity(item("Item A", "4.99"), 2);
		cart.addItemWithQuantity(item("Item B", "6.99"), 4);
		cart.addItemWithQuantity(item("Item C", "2.99"), 6);
		cart.addItemWithQuantity(item("Item D", "9.99"), 3);
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order")
    void checkout_getOrder() 
	{
		DraftOrder draftOrder = new DraftOrder(cart);
		
		// List all Items and quantities
		LineItem itemA = draftOrder.getLineItem(item("Item A", "4.99"));
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		
		LineItem itemB = draftOrder.getLineItem(item("Item B", "6.99"));
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		
		LineItem itemC = draftOrder.getLineItem(item("Item C", "2.99"));
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		
		LineItem itemD = draftOrder.getLineItem(item("Item D", "9.99"));
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		
		// Gather totals for each item then amount total for all items
		List<BigDecimal> itemTotals = checkService.getItemTotals(draftOrder);
		assertEquals("9.98", itemTotals.get(0).toPlainString());
		assertEquals("27.96", itemTotals.get(1).toPlainString());
		assertEquals("17.94", itemTotals.get(2).toPlainString());
		assertEquals("29.97", itemTotals.get(3).toPlainString());
		
		assertEquals("85.85", checkService.getTotal(draftOrder).toPlainString());
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-02: Update Order")
    void checkout_updateOrder() throws QuantityZeroOrNegativeException 
	{
		// Make an Order with all Items from Cart
		DraftOrder draftOrder = new DraftOrder(cart);
		
		// List all Items and quantities
		LineItem itemA = draftOrder.getLineItem(item("Item A", "4.99"));
		LineItem itemB = draftOrder.getLineItem(item("Item B", "6.99"));
		LineItem itemC = draftOrder.getLineItem(item("Item C", "2.99"));
		LineItem itemD = draftOrder.getLineItem(item("Item D", "9.99"));
		
		assert(itemA != null);
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		assertEquals(2, itemA.getQuantity());
		
		assert(itemB != null);
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		assertEquals(4, itemB.getQuantity());
		
		assert(itemC != null);
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		assertEquals(6, itemC.getQuantity());
		
		assert(itemD != null);
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		assertEquals(3, itemD.getQuantity());
		
		// Update a quantity of an Item
		Item itemToChange = new Item("Item C", new BigDecimal("2.99"));
		draftOrder = checkService.updateOrderItemQuantity(draftOrder, itemToChange, 1);
		
		LineItem resultItem = draftOrder.getLineItem(itemC.getItem());
		assertEquals("Item C", resultItem.getItem().getName());
		assertEquals("2.99", resultItem.getItem().getPrice().toPlainString());
		assertEquals(1, resultItem.getQuantity());
		
		// Remove an Item from Order
		Item itemToRemove = new Item("Item C", new BigDecimal("2.99"));
		draftOrder = checkService.removeItemFromOrder(draftOrder, itemToRemove);
		
		LineItem resultRemovedItem = draftOrder.getLineItem(item("Item C", "2.99"));
		assertEquals(null, resultRemovedItem);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-03: Process Successful Payment")
    void checkout_processSuccessfulPayment() throws StripeException 
	{
		// With an Order make a call to payment provider with total
		DraftOrder draftOrder = new DraftOrder(cart);
		
		// Mocked payment processor
		PaymentProcessor paymentProcessor = mock(PaymentProcessor.class);
	    when(paymentProcessor.createPayment(anyLong(), anyString())).thenReturn("pi_123");
	    when(paymentProcessor.confirmPayment("pi_123")).thenReturn(true);
		
		// Assert a successful payment
		boolean result = checkService.makePayment(draftOrder, "usd", paymentProcessor);
		assert(result);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-04: Failed Payment")
    void checkout_failedPayment() 
	{
		PaymentProcessor paymentProcessor = mock(PaymentProcessor.class);
		when(paymentProcessor.refundPayment(anyString())).thenReturn(true);
		
		
		// Assert a failed payment
		boolean result = checkService.refundPayment("pi_123", paymentProcessor);
        assert(result);
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-05: Retry Payment")
    void checkout_retryPaymentAfterFail() 
	{
		// With an Order make a call to payment provider with total
		DraftOrder draftOrder = new DraftOrder(cart);
		
		PaymentProcessor paymentProcessor = mock(PaymentProcessor.class);
	    when(paymentProcessor.createPayment(anyLong(), anyString())).thenReturn("pi_123");
	    when(paymentProcessor.confirmPayment("pi_123")).thenReturn(false);
		
		// Assert a failed payment
	    boolean result = checkService.makePayment(draftOrder, "usd", paymentProcessor);
		assert(!result);
		
		// Redo payment process
		paymentProcessor = mock(PaymentProcessor.class);
	    when(paymentProcessor.createPayment(anyLong(), anyString())).thenReturn("pi_123");
	    when(paymentProcessor.confirmPayment("pi_123")).thenReturn(true);
		
		// Assert successful payment
	    result = checkService.makePayment(draftOrder, "usd", paymentProcessor);
	    assert(result);
    }
}
