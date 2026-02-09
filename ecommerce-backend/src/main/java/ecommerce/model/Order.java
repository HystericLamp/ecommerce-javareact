package ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order
{
	private int orderID;
	private List<LineItem> items;
	
	public Order()
	{
		items = new ArrayList<LineItem>();
	}
	
	public Order(List<LineItem> items)
	{
		this.items = items;
	}
	
	public void setCartItems(List<LineItem> items) { this.items = items; }
	public List<LineItem> getCartItems() { return this.items; }
	
	public BigDecimal getCartSum()
	{
		BigDecimal dollarSum = BigDecimal.ZERO;
		
		for(LineItem item : items)
		{
			// I could use CartItems getItemTotal(), but I don't want to encounter cumulative rounding errors
			// Calculate everything precisely, then round the final total
			BigDecimal itemPrice = item.getItem().getPrice();
			BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
			
			BigDecimal itemTotal = itemPrice.multiply(quantity);
			dollarSum = dollarSum.add(itemTotal);
		}
		
		return dollarSum.setScale(2, RoundingMode.HALF_UP);
	}
}
