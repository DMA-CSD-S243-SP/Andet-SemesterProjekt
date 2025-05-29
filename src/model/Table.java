// Packages
package model;


/**
 * Represents a physical table in one of Bone's restaurants.
 * 
 * Each physical table will have a unique identifiable code that consists
 * of a combination of the restaurant's unique ID and the table's ID.
 * By combining the two a uniquely identifiable string for each table
 * will exist.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 29/05/2025 - 11:37
 */
public class Table 
{
	// Instance variables
	private String tableNumber;
	private String restaurantCode;
	private TableOrder currentTableOrder;

	
	/**
	 * Constructs a new Table instance with the specified tableCode
	 * in the parameter list.
	 *
	 * @param tableCode the unique identifier for this particular table
	 */
	public Table(String tableCode) 
	{
		// Retrieves the 3 first digits from the supplied tableCode and stores it within the restaurantCode instance variable
		this.restaurantCode = tableCode.substring(0, 3);
		
		// Retrieves the 4 last digits from the supplied tableCode and stores it within the tableNumber instance variable
		this.tableNumber = tableCode.substring(3, 7);
	}
	
	
	/**
	 * Retrieves the Table object's assigned tableNumber.
	 * 
	 * @return the table instance's tableNumber
	 */
	public String getTableNumber() 
	{
		return tableNumber;
	}

	
	/**
	 * Sets the Table object's assigned tableNumber.
	 * 
	 * @param tableNumber the new tableNumber to set
	 */
	public void setTableNumber(String tableNumber) 
	{
		this.tableNumber = tableNumber;
	}

	
	/**
	 * Performs string concatenation on the restaurantCode instance variabble
	 * with the tableNumber instance variable, to create a uniquely identifiable
	 * tableCode and retrieves this combined string.
	 * 
	 * @return the uniquely identifiable tableCode of this table.
	 */
	public String getTableCode() 
	{
		return this.restaurantCode + this.tableNumber;
	}

	
	/**
	 * Changes the values of the tableNumber and restaurantCode instance variables to
	 * the values supplied in the parameter list, which are the two values that 
	 * make up a table object's uniquely identifiable tableCode.
	 * 
	 * @param tableNumber the table's 4-digit number used to identify the table in a given restaurant
	 * @param restaurantCode the unique 3-digit number used to identify the specific restaurant
	 */
	public void setTableCode(String tableNumber, String restaurantCode) 
	{
		this.tableNumber = tableNumber;
		this.restaurantCode = restaurantCode;
	}

	
	/**
	 * Returns the TableOrder instance that is associated with this particular
	 * Table instance.
	 * 
	 * @return the currentTableOrder that is associated with this table instance
	 */
	public TableOrder getCurrentTableOrder() 
	{
		return currentTableOrder;
	}

	
	/**
	 * Sets the TableOrder object instance that is associated with this particular
	 * Table instance.
	 * 
	 * @param Specifies the TableOrder object to associate this table with
	 */
	public void setCurrentTableOrder(TableOrder tableOrder) 
	{
		this.currentTableOrder = tableOrder;
	}
}
