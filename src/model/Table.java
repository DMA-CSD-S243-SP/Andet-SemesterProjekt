package model;

/*
 * The table class represents a physical table in
 * @Author Anders Trankjær
 * @Version 2025/08/05/10:00
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
		this.tableCode = tableNumber + restaurantCode;
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
