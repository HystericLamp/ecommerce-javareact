package businesslogic.cart;

import java.util.HashMap;
import java.util.Map;

import ecommerce.exceptions.CartItemNotFoundException;
import ecommerce.model.LineItem;
import ecommerce.model.Item;

public class Cart
{
	Map<Item, LineItem> cartItems;
	
	public Cart()
	{
		cartItems = new HashMap<Item, LineItem>();
	}
	
	/**
	 * <p>
	 * Creates a <b>CartItem</b> with <b>Item</b> 
	 * and sets quantity to 1, then adds to a <b>List</b> of <b>CartItems</b>
	 * </p>
	 * 
	 * @param item
	 */
	public void addItem(Item item)
	{
		cartItems.compute(item, (k, v) ->
			v == null ? new LineItem(item, 1) : new LineItem(item, v.getQuantity() + 1)
		);
	}
	
	/**
	 * <p>
	 * Creates a <b>CartItem</b> with <b>Item</b> and specified quantity,
	 * then adds to a <b>List</b> of <b>CartItems</b>
	 * </p>
	 * 
	 * @param item
	 * @param quantity
	 */
	public void addItemWithQuantity(Item item, int quantity)
	{
		cartItems.compute(item, (k, v) ->
			v == null ? new LineItem(item, quantity) : new LineItem(item, v.getQuantity() + quantity)
		);
	}
	
	/**
	 * <p>
	 * Updates quantity of an <b>Item</b> in the <b>Cart</b>.
	 * Throws an exception if specified <b>Item</b> is not found in the <b>Cart</b>
	 * </p>
	 * 
	 * @param item
	 * @param quantity
	 * @throws CartItemNotFoundException
	 */
	public void updateQuantity(Item item, int quantity) throws CartItemNotFoundException
	{
		LineItem updated = cartItems.replace(item, new LineItem(item, quantity));
		
	    if (updated == null) 
	    {
	        throw new CartItemNotFoundException(
	            "Item " + item.getName() + " not found in Cart"
	        );
	    }
	}
	
	public void removeItem(Item item)
	{
		cartItems.remove(item);
	}
	
	// Utility Methods
	/**
	 * <p>
	 * Iterates through the <b>Cart</b> to find if specified <b>Item</b> exists
	 * </p>
	 * @param item
	 * @return true if item exists in cart, false otherwise
	 */
	public Boolean hasCartItem(Item item)
	{
		return cartItems.containsKey(item);
	}
	
	/**
	 * <p>
	 * Finds <b>Item</b> in the <b>Cart</b> if it exists,
	 * then returns the <b>CartItem</b> else returns null
	 * </p>
	 * @param item
	 * @return <b>CartItem</b> with <b>Item</b> and quantity
	 */
	public LineItem getCartItem(Item item)
	{
		return cartItems.get(item);
	}
}
