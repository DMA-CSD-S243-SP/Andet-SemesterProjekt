package model;

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
	
	public int getTableNumber() 
	{
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) 
	{
		this.tableNumber = tableNumber;
	}

	public String getTableCode() 
	{
		return tableCode;
	}

	public void setTableCode(int tableNumber, String restaurantCode) 
	{
		this.tableCode = tableNumber + restaurantCode;
	}

	public TableOrder getCurrentTableOrder() 
	{
		return currentTableOrder;
	}

	public void setCurrentTableOrder(TableOrder tableOrder) 
	{
		this.currentTableOrder = tableOrder;
	}
}
