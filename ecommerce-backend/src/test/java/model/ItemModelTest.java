package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecommerce.model.Item;

/**
 * Just making this to test my development environment
 */
public class ItemModelTest
{
	@Test
	void instantaiteItem()
	{
		Item item = new Item("Espresso", 4.99);
		
		assertEquals("Espresso", item.getName());
		assertEquals(4.99, item.getPrice());
	}
}
