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
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	public BigDecimal getItemTotal() 
	{
		BigDecimal itemPrice = item.getPrice();
		BigDecimal itemQuantity = BigDecimal.valueOf(this.quantity);
		
		BigDecimal itemTotal = itemPrice.multiply(itemQuantity);
		
		return itemTotal.setScale(2, RoundingMode.HALF_UP);
	}
}
