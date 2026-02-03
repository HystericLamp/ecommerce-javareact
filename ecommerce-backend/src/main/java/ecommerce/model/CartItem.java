package ecommerce.model;

public class CartItem
{
	private Item item;
	private int quantity;
	
	public CartItem(Item item, int quantity)
	{
		this.setItem(item);
		this.setQuantity(quantity);
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
}
