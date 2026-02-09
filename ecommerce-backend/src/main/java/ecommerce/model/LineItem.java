package ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LineItem
{
	private Item item;
	private int quantity;
	
	public LineItem(Item item, int quantity)
	{
		this.setItem(item);
		this.setQuantity(quantity);
	}
	
	// Setters & Getters
	public Item getItem() { return item; }
	public void setItem(Item item) { this.item = item; }
	public int getQuantity() { return quantity; }
	
	public void setQuantity(int quantity) 
	{
		if (quantity <= 0) 
		{
			throw new IllegalArgumentException("Cannot set quantity of " + item.getName() + " to or below 0");
		}
		
		this.quantity = quantity; 
	}
	
	/**
	 * <p>
	 * Calculates the total cost of the <b>Item</b>'s price multiplied by its quantity
	 * </p>
	 * 
	 * @return
	 */
	public BigDecimal getItemTotal() 
	{
		return item.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
}
