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
import ecommerce.model.Cart;
import ecommerce.model.DraftOrder;
import ecommerce.model.Item;
import ecommerce.model.Order;

public class CheckoutServiceTest
{
	private CheckoutService checkService;
	
	// Helper
	private Item item(String name, String price)
	{
		return new Item(name, new BigDecimal(price));
	}
	
	@BeforeEach
	void setUp()
	{
		Cart cart = new Cart();
		List<LineItem> cartItems = new ArrayList<LineItem>();
		cart.addItemWithQuantity(item("Item A", "4.99"), 2);
		cart.addItemWithQuantity(item("Item B", "6.99"), 4);
		cart.addItemWithQuantity(item("Item C", "2.99"), 6);
		cart.addItemWithQuantity(item("Item D", "9.99"), 3);
		
		DraftOrder draftOrder = new DraftOrder(cart);
		checkService = new CheckoutService(draftOrder);
	}
	
	@Test
    @DisplayName("AC-CHECKOUT-01: Get Summary of Order")
    void checkout_getOrder() 
	{
		// Get Order with all Items from Cart in checkService
		DraftOrder _draftOrder = checkService.getOrder();
		
		// List all Items and quantities
		LineItem itemA = _draftOrder.getLineItem(item("Item A", "4.99"));
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		
		LineItem itemB = _draftOrder.getLineItem(item("Item B", "6.99"));
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		
		LineItem itemC = _draftOrder.getLineItem(item("Item C", "2.99"));
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		
		LineItem itemD = _draftOrder.getLineItem(item("Item D", "9.99"));
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		
		// Gather totals for each item then amount total for all items
		List<BigDecimal> itemTotals = _draftOrder.getItemTotals();
		assertEquals("9.98", itemTotals.get(0).toPlainString());
		assertEquals("27.96", itemTotals.get(1).toPlainString());
		assertEquals("17.94", itemTotals.get(2).toPlainString());
		assertEquals("29.97", itemTotals.get(3).toPlainString());
		
		assertEquals("85.85", _draftOrder.getTotal().toPlainString());
    }
	
	@Test
    @DisplayName("AC-CHECKOUT-02: Update Order")
    void checkout_updateOrder() 
	{
		// Make an Order with all Items from Cart
		DraftOrder _draftOrder = checkService.getOrder();
		
		// List all Items and quantities
		LineItem itemA = _draftOrder.getLineItem(item("Item A", "4.99"));
		LineItem itemB = _draftOrder.getLineItem(item("Item B", "6.99"));
		LineItem itemC = _draftOrder.getLineItem(item("Item C", "2.99"));
		LineItem itemD = _draftOrder.getLineItem(item("Item D", "9.99"));
		
		assertEquals("Item A", itemA.getItem().getName());
		assertEquals("4.99", itemA.getItem().getPrice().toPlainString());
		assertEquals(2, itemA.getQuantity());
		
		assertEquals("Item B", itemB.getItem().getName());
		assertEquals("6.99", itemB.getItem().getPrice().toPlainString());
		assertEquals(4, itemB.getQuantity());
		
		assertEquals("Item C", itemC.getItem().getName());
		assertEquals("2.99", itemC.getItem().getPrice().toPlainString());
		assertEquals(6, itemC.getQuantity());
		
		assertEquals("Item D", itemD.getItem().getName());
		assertEquals("9.99", itemD.getItem().getPrice().toPlainString());
		assertEquals(3, itemD.getQuantity());
		
		// Update a quantity of an Item
		Item itemToChange = new Item("Item C", new BigDecimal("2.99"));
		_draftOrder.updateQuantity(itemToChange, 1);
		
		LineItem resultItem = _draftOrder.getLineItem(itemC.getItem());
		assertEquals("Item C", resultItem.getItem().getName());
		assertEquals("2.99", resultItem.getItem().getPrice().toPlainString());
		assertEquals(1, resultItem.getQuantity());
		
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
