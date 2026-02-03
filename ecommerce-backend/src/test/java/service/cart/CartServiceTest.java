package service.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cart.CartService;
import ecommerce.exceptions.QuantityZeroOrNegativeException;
import ecommerce.model.CartItem;
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
        Item item = new Item("Espresso", 4.99);
        
        cartService.addItem(item);
        
        boolean hasItem = cartService.getItem(item) != null;
        assert(hasItem);
        
        Item resultItem = cartService.getItem(item);
        assertEquals(resultItem.getName(), "Espresso");
        assertEquals(resultItem.getPrice(), 4.99);
        
        CartItem resultCartItem = cartService.getCartItem(item);
        assertEquals(resultCartItem.getQuantity(), 1);
    }
	
	@Test
    @DisplayName("AC-CART-02: Add item to cart with quantity")
    void addItem_addToCartWithQuantity() throws Exception 
    {
		Item item1 = new Item("Espresso", 4.99);
		Item item2 = new Item("Machiato", 3.99);
		
		cartService.addItemWithQuantity(item1, 2);
		cartService.addItemWithQuantity(item2, 100);
        
		// Item 1
        Item resultItem1 = cartService.getItem(item1);
        assertEquals(resultItem1.getName(), "Espresso");
        assertEquals(resultItem1.getPrice(), 4.99);
        
        CartItem resultCartItem1 = cartService.getCartItem(item1);
        assertEquals(resultCartItem1.getQuantity(), 2);
        
        // Item 2
        Item resultItem2 = cartService.getItem(item2);
        assertEquals(resultItem2.getName(), "Machiato");
        assertEquals(resultItem2.getPrice(), 3.99);
        
        CartItem resultCartItem2 = cartService.getCartItem(item2);
        assertEquals(resultCartItem2.getQuantity(), 100); 
    }
	
	@Test
    @DisplayName("AC-CART-03: Do not add an item with 0 or negative quantity")
    void addItem_doNotAddItemWithZeroOrNegativeQuantity() 
    {
		Item item = new Item("Espresso", 4.99);
        
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
    @DisplayName("AC-CART-04: Update quantity changes cart total")
    void updateQuantity_updatesCartTotal() 
    {
		assert(false);
    }

    @Test
    @DisplayName("AC-CART-05: Remove item removes it from cart")
    void removeItem_removesItemFromCart() 
    {
    	assert(false);
    }
}
