package ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
	List<CartItem> items;
	
	public Cart()
	{
		items = new ArrayList<CartItem>();
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
		items.add(new CartItem(item, 1));
	}
	
	/**
	 * <p>
	 * Creates a <b>CartItem</b> with <b>Item</b> and specified quantity,
	 * then adds to a <b>List</b> of <b>CartItems</b>
	 * </p>
	 * @param item
	 * @param quantity
	 */
	public void addItemWithQuantity(Item item, int quantity)
	{
		items.add(new CartItem(item, quantity));
	}
	
	/**
	 * <p>
	 * Iterates through the <b>Cart</b> to find if specified <b>Item</b> exists
	 * </p>
	 * @param item
	 * @return true if item exists in cart, false otherwise
	 */
	public Boolean hasCartItem(Item item)
	{
		for(CartItem ci : items)
		{
			if (ci.getItem().equals(item))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * <p>
	 * Iterates through <b>Cart</b> to find specified <b>Item</b>,
	 * then returns the <b>CartItem</b>
	 * </p>
	 * @param item
	 * @return <b>CartItem</b> with <b>Item</b> and quantity
	 */
	public CartItem getCartItem(Item item)
	{
		for(CartItem ci : items)
		{
			if (ci.getItem().equals(item))
			{
				return ci;
			}
		}
		
		return null;
	}
}
