package service.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cart.CartService;
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
    @DisplayName("AC-CART-02: Update quantity changes cart total")
    void updateQuantity_updatesCartTotal() 
    {
		assert(false);
    }

    @Test
    @DisplayName("AC-CART-03: Remove item removes it from cart")
    void removeItem_removesItemFromCart() 
    {
    	assert(false);
    }
}
