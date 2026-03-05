package com.ecommerce.bcruz.service;

import com.ecommerce.bcruz.exceptions.CartItemNotFoundException;
import com.ecommerce.bcruz.exceptions.QuantityZeroOrNegativeException;
import com.ecommerce.bcruz.models.Cart;
import com.ecommerce.bcruz.models.Product;
import com.ecommerce.bcruz.models.LineProduct;

/**
 * <p>
 * <b>CartService</b> class manages a <b>Cart</b> of items
 * Other features WIP
 * </p>
 */
public class CartService
{
	Cart cart;
	
	public CartService()
	{
		cart = new Cart();
	}
	
	/**
	 * <p>
	 * This method adds an <b>Item</b> to the <b>Cart</b> with a default quantity of 1
	 * </p>
	 *  
	 * @param Product item
	 */
	public void addItem(Product item)
	{
		cart.addItem(item);
	}
	
	/**
	 * <p>
	 * This method adds an <b>Item</b> to the <b>Cart</b> with a specified quantity
	 * </p>
	 * 
	 * @param item
	 * @param quantity
	 * @throws Exception
	 */
	public void addItemWithQuantity(Product item, int quantity) throws QuantityZeroOrNegativeException
	{
		// check if quantity is at or below 0
		if (quantity <= 0)
		{
			throw new QuantityZeroOrNegativeException("Tried to add an Item with 0 or negative quantity");
		}
		
		cart.addItemWithQuantity(item, quantity);
	}
	
	/**
	 * <p>
	 * Updates the quantity of an Item in the Cart
	 * </p>
	 * 
	 * @param item
	 * @param quantity
	 * @throws QuantityZeroOrNegativeException
	 * @throws CartItemNotFoundException
	 */
	public void updateQuantity(Product item, int quantity) throws QuantityZeroOrNegativeException, CartItemNotFoundException
	{
		// check if quantity is at or below 0
		if (quantity <= 0)
		{
			throw new QuantityZeroOrNegativeException("Tried to update an Item with 0 or negative quantity");
		}
		
		cart.updateQuantity(item, quantity);
	}
	
	/**
	 * 
	 * @param item
	 */
	public void removeItemFromCart(Product item)
	{
		cart.removeItem(item);
	}
	
	// Utility methods
	/**
	 * 
	 * @param item
	 * @return
	 */
	public LineProduct getCartItem(Product item)
	{
		return cart.getCartItem(item);
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public Product getItem(Product item)
	{
		return cart.getCartItem(item).getProduct();
	}
}
