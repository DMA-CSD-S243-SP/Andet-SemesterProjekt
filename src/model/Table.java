package model;


/*
 * The table class represents a physical table in
 * @Author Anders Trankjær & Christoffer Søndergaard
 * @Version 18/05/2025 - 15:23
 */
public class Table 
{
	private int tableNumber;
	private String tableCode;
	private TableOrder currentTableOrder;

	public Table(int tableNumber, String tableCode) 
	{
		this.tableNumber = tableNumber;
		this.tableCode = tableCode;
	}
	
	/**
	 * @return the tableNumber
	 */
	public int getTableNumber() 
	{
		return tableNumber;
	}

	/**
	 * @param tableNumber the new tableNumber to set
	 */
	public void setTableNumber(int tableNumber) 
	{
		this.tableNumber = tableNumber;
	}

	/**
	 * @return the tableCode
	 */
	public String getTableCode() 
	{
		return tableCode;
	}

	/**
	 * the tableCode variable is made of two other variables tableNumber and restaurantCode
	 * 
	 * @param tableNumber a unique number for each table in a restaurant
	 * @param restaurantCode a unique number that identifies a restaurant
	 */
	public void setTableCode(int tableNumber, String restaurantCode) 
	{
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

		this.tableCode = formattedTableNumberString + restaurantCode;
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
