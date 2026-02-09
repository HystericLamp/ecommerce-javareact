package businesslogic.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ecommerce.model.LineItem;
import ecommerce.model.Order;

public class CheckoutService
{
	private Order order;
	
	public CheckoutService(Order order) 
	{
		this.order = order;
	}
	
	public Order getOrder()
	{
		return this.order;
	}
	
	public void updateOrderItemQuantity(String itemName, int quantity)
	{
		
	}
	
	public List<BigDecimal> getItemTotals()
	{
		List<BigDecimal> itemTotals = new ArrayList<BigDecimal>();
		List<LineItem> cartItems = order.getCartItems();
		
		for (LineItem item : cartItems)
		{
			itemTotals.add(item.getItemTotal());
		}
		
		return itemTotals;
	}
	
	public BigDecimal getOrderTotal()
	{
		return order.getCartSum();
	}
}
