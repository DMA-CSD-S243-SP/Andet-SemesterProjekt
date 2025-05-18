package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Restaurant;
import model.Table;


/**
 * This class tests the functionality of tableCode across the Restaurant and Table classes
 * 
 * @author Anders Have & Christoffer Søndergaard
 * @version 18/05/2025 - 15:23
 */
public class TestTableCode 
{
	@Test
	public void testTableCode()
	{
		// Arrange
		Table table1 = new Table(1, null);
		Table table2 = new Table(2, null);
		
		Restaurant restaurant1 = new Restaurant("Bones", "Aalborg", "epicStreet");
		
		
		// Act
		restaurant1.setRestaurantCode("001");
			
		table1.setTableCode(table1.getTableNumber(), restaurant1.getRestaurantCode());
		table2.setTableCode(table2.getTableNumber(), restaurant1.getRestaurantCode());
	
	
		// Assert
		// tableCode is a unique code made up of tableNumber and the restaurantCode of the restaurant the table is within.
		assertEquals("0001001", table1.getTableCode());
		assertEquals("0002001", table2.getTableCode());
	}
}
