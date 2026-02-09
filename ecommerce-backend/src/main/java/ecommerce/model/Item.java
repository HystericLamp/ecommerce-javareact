package ecommerce.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item
{
	private String name;
	private BigDecimal price;
	
	public Item(String name, BigDecimal price)
	{
		this.name = name;
		this.price = price;
	}
	
	// Getters & Setters
	public String getName(){ return name; }
	public void setName(String name){ this.name = name; }
	public BigDecimal getPrice(){ return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
	
	@Override
	public String toString() 
	{
	    return "Item{name='" + name + "', price=" + price + "}";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Item)) return false;
		
		Item item =(Item) o;
		
		return price.compareTo(item.price) == 0 &&
			   name.equalsIgnoreCase(item.name);
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(name.toLowerCase(), price);
	}
}
