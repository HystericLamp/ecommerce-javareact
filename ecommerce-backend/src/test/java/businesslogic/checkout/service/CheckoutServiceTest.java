package businesslogic.checkout.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import businesslogic.checkout.CheckoutService;
import ecommerce.model.LineItem;
import ecommerce.model.Item;
import ecommerce.model.Order;

public class CheckoutServiceTest
{
	private CheckoutService checkService;
	
	// Helper
	private LineItem cartItem(String name, String price, int quantity)
	{
		return new LineItem(
			new Item(name, new BigDecimal(price)),
			quantity
		);
	}
	
	@BeforeEach
	void setUp()
	{
		List<LineItem> cartItems = new ArrayList<LineItem>();
		cartItems.add(cartItem("Item A", "4.99", 2));
		cartItems.add(cartItem("Item B", "6.99", 4));
		cartItems.add(cartItem("Item C", "2.99", 6));
		cartItems.add(cartItem("Item D", "9.99", 3));
		
		Order order = new Order(cartItems);
		checkService = new CheckoutService(order);
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order")
    void checkout_getOrder() 
	{
		// Get Order with all Items from Cart in checkService
		Order anOrder = checkService.getOrder();
		List<LineItem> orderItems = anOrder.getCartItems();
		LineItem itemA = orderItems.get(0);
		LineItem itemB = orderItems.get(1);
		LineItem itemC = orderItems.get(2);
		LineItem itemD = orderItems.get(3);
		
		// List all Items and quantities
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		
		// Gather totals for each item then amount total for all items
		List<BigDecimal> itemTotals = checkService.getItemTotals();
		assertEquals("9.98", itemTotals.get(0).toPlainString());
		assertEquals("27.96", itemTotals.get(1).toPlainString());
		assertEquals("17.94", itemTotals.get(2).toPlainString());
		assertEquals("29.97", itemTotals.get(3).toPlainString());
		
		assertEquals("85.85", checkService.getOrderTotal().toPlainString());
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-02: Update Order")
    void checkout_updateOrder() 
	{
		// Make an Order with all Items from Cart
		Order anOrder = checkService.getOrder();
		
		// List all Items and quantities
		List<LineItem> orderItems = anOrder.getCartItems();
		LineItem itemA = orderItems.get(0);
		LineItem itemB = orderItems.get(1);
		LineItem itemC = orderItems.get(2);
		LineItem itemD = orderItems.get(3);
		
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		
		// Update a quantity of an Item
		
		// Remove an Item from Order
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
