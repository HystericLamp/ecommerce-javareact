package ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DraftOrder
{
	private final Map<Item, LineItem> items = new LinkedHashMap<Item, LineItem>();
	
	public DraftOrder(Cart cart)
	{
		for (LineItem lineItem : cart.getAllItems())
		{
			items.put(lineItem.getItem(), 
					  new LineItem(lineItem.getItem(), lineItem.getQuantity()));
		}
	}
	
	public LineItem getLineItem(Item item)
	{
		return items.get(item);
	}
	
	public final Collection<LineItem> getAllLineItems()
	{
		return this.items.values();
	}
	
	public void updateQuantity(Item item, int quantity) 
	{
        LineItem lineItem = items.get(item);
        
        if (lineItem == null) throw new IllegalArgumentException();
        
        lineItem.setQuantity(quantity);
    }

    public void removeItem(Item item) 
    {
        items.remove(item);
    }

    public Order finalizeOrder() 
    {
        return new Order(items.values());
    }
}
