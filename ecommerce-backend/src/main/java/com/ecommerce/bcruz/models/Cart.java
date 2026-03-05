package com.ecommerce.bcruz.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ecommerce.bcruz.exceptions.CartItemNotFoundException;

public class Cart
{
	Map<Product, LineProduct> cartItems;
	
	public Cart()
	{
		cartItems = new LinkedHashMap<Product, LineProduct>();
	}
	
	/**
	 * <p>
	 * Creates a <b>CartItem</b> with <b>Item</b> 
	 * and sets quantity to 1, then adds to a <b>List</b> of <b>CartItems</b>
	 * </p>
	 * 
	 * @param item
	 */
	public void addItem(Product item)
	{
		cartItems.compute(item, (k, v) ->
			v == null ? new LineProduct(item, 1) : new LineProduct(item, v.getQuantity() + 1)
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
	public void addItemWithQuantity(Product item, int quantity)
	{
		cartItems.compute(item, (k, v) ->
			v == null ? new LineProduct(item, quantity) : new LineProduct(item, v.getQuantity() + quantity)
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
	public void updateQuantity(Product item, int quantity) throws CartItemNotFoundException
	{
		LineProduct updated = cartItems.replace(item, new LineProduct(item, quantity));
		
	    if (updated == null) 
	    {
	        throw new CartItemNotFoundException(
	            "Item " + item.getName() + " not found in Cart"
	        );
	    }
	}
	
	public void removeItem(Product item)
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
	public Boolean hasCartItem(Product item)
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
	public LineProduct getCartItem(Product item)
	{
		return cartItems.get(item);
	}
	
	public Collection<LineProduct> getAllItems()
	{
		return cartItems.values();
	}
}
