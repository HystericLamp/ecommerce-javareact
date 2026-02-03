package cart;

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
	
	public CartItem getCartItem(Item item)
	{
		return cart.getCartItem(item);
	}
	
	public Item getItem(Item item)
	{
		return cart.getCartItem(item).getItem();
	}
}
