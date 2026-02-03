package cart;

import ecommerce.exceptions.QuantityZeroOrNegativeException;
import ecommerce.model.Cart;
import ecommerce.model.CartItem;
import ecommerce.model.Item;

public class CartService
{
	Cart cart;
	
	public CartService()
	{
		cart = new Cart();
	}
	
	/**
	 * <p>
	 * This method adds an item to the cart with a default quantity of 1
	 * </p>
	 *  
	 * @param Item item
	 */
	public void addItem(Item item)
	{
		cart.addItem(item);
	}
	
	public void addItemWithQuantity(Item item, int quantity) throws Exception
	{
		// check if quantity is at or below 0
		if (quantity <= 0)
		{
			throw new QuantityZeroOrNegativeException("Tried to add an Item with 0 or negative quantity");
		}
		
		cart.addItemWithQuantity(item, quantity);
	}
	
	public CartItem getCartItem(Item item)
	{
		return cart.getCartItem(item);
	}
	
	public Item getItem(Item item)
	{
		return cart.getCartItem(item).getItem();
	}
}
