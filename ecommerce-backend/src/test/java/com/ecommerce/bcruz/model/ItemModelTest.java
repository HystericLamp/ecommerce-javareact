package com.ecommerce.bcruz.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ecommerce.bcruz.models.Item;

/**
 * Just making this to test my development environment
 */
public class ItemModelTest
{
	@Test
	void instantaiteItem()
	{
		Item item = new Item("Espresso", new BigDecimal("4.99"));
		
		assertEquals("Espresso", item.getName());
		assertEquals(4.99, item.getPrice());
	}
}
