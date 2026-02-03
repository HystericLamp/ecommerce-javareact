package ecommerce.exceptions;

/**
 * <p>
 * Exception to be used when adding an <b>Item</b> to the <b>Cart</> with 0 or negative number
 * </p>
 */
public class QuantityZeroOrNegativeException extends Exception
{
	public QuantityZeroOrNegativeException(String m)
	{
		super(m);
	}
}
