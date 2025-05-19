package model;


/*
 * The table class represents a physical table in
 * @Author Anders Trankjær & Christoffer Søndergaard
 * @Version 18/05/2025 - 15:59
 */
public class Table 
{
	private String tableNumber;
	private String restaurantCode;
	private TableOrder currentTableOrder;

	public Table(String tableCode) 
	{
		this.tableNumber = tableCode.substring(3, 7);
		this.restaurantCode = tableCode.substring(0, 3);
	}
	
	/**
	 * @return the tableNumber
	 */
	public String getTableNumber() 
	{
		return tableNumber;
	}

	/**
	 * @param tableNumber the new tableNumber to set
	 */
	public void setTableNumber(String tableNumber) 
	{
		this.tableNumber = tableNumber;
	}

	/**
	 * @return the tableCode
	 */
	public String getTableCode() 
	{
		return restaurantCode+tableNumber;
	}

	/**
	 * the tableCode variable is made of two other variables tableNumber and restaurantCode
	 * 
	 * @param tableNumber a unique four-digit number in the form of a string for each table in a restaurant
	 * @param restaurantCode a unique number that identifies a restaurant
	 */
	public void setTableCode(String tableNumber, String restaurantCode) 
	{
/* TODO - Clean this up later
		// Retrieves the inputted tableNumber's length and store it within the tableNumberLength variable
		int tableNumberLength = String.valueOf(tableNumber).length();
		
		// Creates a String object with the name formattedTableNumberString 
		String formattedTableNumberString = null;
		
		// If the length of the supplied tableNumber is only 1 digit long then execute this section
		if(tableNumberLength == 1)
		{
			formattedTableNumberString = "000";
		}
		
		// If the length of the supplied tableNumber is only 2 digit long then execute this section
		else if(tableNumberLength == 2)
		{
			formattedTableNumberString = "00";
		}
		
		// If the length of the supplied tableNumber is only 3 digit long then execute this section
		else if(tableNumberLength == 3)
		{
			formattedTableNumberString = "0";
		}
		
		// Combines the tableNumber with the formattedTableNumberString variable to form a four digit string
		// E.g. supplying 1 will make this string be equivilant to "0001"
		formattedTableNumberString = formattedTableNumberString + tableNumber;
*/
		
		this.tableNumber = tableNumber;
		this.restaurantCode = restaurantCode;
	}

	
	/**
	 * @return the currentTableOrder associated with the table
	 */
	public TableOrder getCurrentTableOrder() 
	{
		return currentTableOrder;
	}

	
	/**
	 * @param tableOrder the new tableOrder to set
	 */
	public void setCurrentTableOrder(TableOrder tableOrder) 
	{
		this.currentTableOrder = tableOrder;
	}
}
