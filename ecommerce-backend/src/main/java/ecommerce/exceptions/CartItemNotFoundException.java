package ecommerce.exceptions;

/**
 * <p>
 * Exception to be used when finding an a <b>CartItem</b> in the <b>Cart</b> was not found
 * </p>
 */
public class CartItemNotFoundException extends Exception
{
	public CartItemNotFoundException(String m)
	{
		super(m);
	}
}
