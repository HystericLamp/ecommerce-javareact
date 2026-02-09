package ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Order
{
	private int orderID;
	private final List<LineItem> items;
	
	public Order(Collection<LineItem> cartItems)
	{
		this.items = cartItems.stream()
					 .map(li -> new LineItem(li.getItem(), li.getQuantity()))
					 .toList();
	}
	
	public List<LineItem> getCartItems() { return this.items; }
	
	public BigDecimal getItemTotal(Item item)
	{
		for (LineItem lineItem : items)
		{
			if (lineItem.getItem().equals(item))
			{
				return lineItem.getItem().getPrice().multiply(BigDecimal.valueOf(lineItem.getQuantity()));
			}
		}
		
		return null;
	}
	
	public BigDecimal getCartSum()
	{
		return items.stream()
	            .map(LineItem::getItemTotal)
	            .reduce(BigDecimal.ZERO, BigDecimal::add)
	            .setScale(2, RoundingMode.HALF_UP);
	}
}
