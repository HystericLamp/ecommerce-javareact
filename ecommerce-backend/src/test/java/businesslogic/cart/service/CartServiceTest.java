package businesslogic.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import businesslogic.cart.CartService;
import ecommerce.exceptions.CartItemNotFoundException;
import ecommerce.exceptions.QuantityZeroOrNegativeException;
import ecommerce.model.LineItem;
import ecommerce.model.Item;

class CartServiceTest 
{
	private CartService cartService;
	
	@BeforeEach
	void setUp()
	{
		cartService = new CartService();
	}
	
	@Test
    @DisplayName("AC-CART-01: Add item to cart sets default quantity to 1")
    void addItem_setsDefaultQuantityToOne() 
	{
        Item item = new Item("Espresso", new BigDecimal("4.99"));
        
        cartService.addItem(item);
        
        boolean hasItem = cartService.getItem(item) != null;
        assert(hasItem);
        
        Item resultItem = cartService.getItem(item);
        assertEquals(resultItem.getName(), "Espresso");
        assertEquals(resultItem.getPrice().doubleValue(), 4.99);
        
        LineItem resultCartItem = cartService.getCartItem(item);
        assertEquals(resultCartItem.getQuantity(), 1);
    }
	
	@Test
    @DisplayName("AC-CART-02: Add item to cart with quantity")
    void addItem_addToCartWithQuantity() throws Exception 
    {
		Item item1 = new Item("Espresso", new BigDecimal("4.99"));
		Item item2 = new Item("Machiato", new BigDecimal("3.99"));
		
		cartService.addItemWithQuantity(item1, 2);
		cartService.addItemWithQuantity(item2, 100);
        
		// Item 1
        Item resultItem1 = cartService.getItem(item1);
        assertEquals(resultItem1.getName(), "Espresso");
        assertEquals(resultItem1.getPrice().doubleValue(), 4.99);
        
        LineItem resultCartItem1 = cartService.getCartItem(item1);
        assertEquals(resultCartItem1.getQuantity(), 2);
        
        // Item 2
        Item resultItem2 = cartService.getItem(item2);
        assertEquals(resultItem2.getName(), "Machiato");
        assertEquals(resultItem2.getPrice().doubleValue(), 3.99);
        
        LineItem resultCartItem2 = cartService.getCartItem(item2);
        assertEquals(resultCartItem2.getQuantity(), 100); 
    }
	
	@Test
    @DisplayName("AC-CART-03: Do not add an item with 0 or negative quantity")
    void addItem_doNotAddItemWithZeroOrNegativeQuantity() 
    {
		Item item = new Item("Espresso", new BigDecimal("4.99"));
        
        assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.addItemWithQuantity(item, 0);
        });
        
        assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.addItemWithQuantity(item, -1);
        });
        
        assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.addItemWithQuantity(item, -100);
        });
    }
	
	@Test
    @DisplayName("AC-CART-04: Adding existing Item into the Cart should increment quantity")
    void addItem_incrementItemQuantityIfExists() throws Exception 
    {
		Item item1 = new Item("Espresso", new BigDecimal("4.99"));
		Item item2 = new Item("Machiato", new BigDecimal("3.99"));
		Item item3 = new Item("Cappuccino", new BigDecimal("6.99"));
		
		cartService.addItemWithQuantity(item1, 2);
		cartService.addItemWithQuantity(item2, 1);
		cartService.addItemWithQuantity(item3, 3);
		
		// Check if current quantity is 1 for Item2
        LineItem resultCartItem2 = cartService.getCartItem(item2);
        assertEquals(resultCartItem2.getQuantity(), 1, "Expected 1, but was " + resultCartItem2.getQuantity());
        
        // Re-add the same item "Machiato" and check if it incremented quality
        // Item4 should have the same values as Item2 aside from quantity
        Item item4 = new Item("Machiato", new BigDecimal("3.99"));
        cartService.addItemWithQuantity(item4, 3);
        
        LineItem resultCartItem4 = cartService.getCartItem(item4);
        assertEquals(resultCartItem4.getQuantity(), 4, "Expected 4, but was " + resultCartItem4.getQuantity());
    }
	
	@Test
    @DisplayName("AC-CART-05: Update quantity of an Item in Cart")
    void updateQuantity_updatesCartTotal() throws Exception
    {
		Item item1 = new Item("Espresso", new BigDecimal("4.99"));
		cartService.addItemWithQuantity(item1, 2);
		
		// assert initial value
		LineItem cartitem1 = cartService.getCartItem(item1);
		assertEquals(cartitem1.getItem().getName(), "Espresso");
        assertEquals(cartitem1.getItem().getPrice().doubleValue(), 4.99);
        assertEquals(cartitem1.getQuantity(), 2);
        
        // Update then assert
        cartService.updateQuantity(item1, 5);
        cartitem1 = cartService.getCartItem(item1);
        assertEquals(cartitem1.getItem().getName(), "Espresso");
        assertEquals(cartitem1.getItem().getPrice().doubleValue(), 4.99);
        assertEquals(cartitem1.getQuantity(), 5);
    }
	
	@Test
    @DisplayName("AC-CART-06: Do not update an item quantity with a negative quantity or non-existent item")
    void updateQuantity_edgeCases() throws Exception
    {
		Item item1 = new Item("Espresso", new BigDecimal("4.99"));
		cartService.addItemWithQuantity(item1, 2);
		
		// Assert 0 or negatives
		assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.updateQuantity(item1, 0);
        });
		
		assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.updateQuantity(item1, -1);
        });
        
        assertThrows(QuantityZeroOrNegativeException.class, () -> {
        	cartService.updateQuantity(item1, -100);
        });
        
        // Assert non-existent item
        assertThrows(CartItemNotFoundException.class, () -> {
        	cartService.updateQuantity(new Item("Machiato", new BigDecimal("4.99")), 3);
        });
    }

    @Test
    @DisplayName("AC-CART-07: Remove item from cart")
    void removeItem_removesItemFromCart() throws Exception 
    {
    	Item item1 = new Item("Espresso", new BigDecimal("4.99"));
		Item item2 = new Item("Machiato", new BigDecimal("3.99"));
		Item item3 = new Item("Cappuccino", new BigDecimal("6.99"));
		
		cartService.addItemWithQuantity(item1, 2);
		cartService.addItemWithQuantity(item2, 1);
		cartService.addItemWithQuantity(item3, 3);
		
		// Assert Item exists in Cart
		LineItem cartItem2 = cartService.getCartItem(item2);
		assert(cartItem2 != null);
		
		// Assert removal
		cartService.removeItemFromCart(item2);
		cartItem2 = cartService.getCartItem(item2);
		assert(cartItem2 == null);
    }
}
