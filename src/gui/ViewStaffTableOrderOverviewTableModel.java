//Packages
package gui;

//Imports
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

//import model.Product;
//import model.SimpleProduct;


/**
* TODO: Write a relatively detailed description of what this
* class represents, and add a version number containing both date 
* and time, matching the other classes' java documentation.
*
* @author Line Bertelsen & Christoffer Søndergaard
* @version 01/06/2025 - 22:24
*/
public class ViewStaffTableOrderOverviewTableModel extends AbstractTableModel
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// Array of column names for the table.
	private static final String[] COLUMN_NAMES = { "Bordnummer", "Bestillingsnummer", "Oprettelsestidspunkt", "Personens Navn", "Antal", "Rettens Navn", "Noter" };
	
	// TODO
	// A list to hold the collection of Product objects displayed in the table.
//	private List<SimpleProduct> productCollection;

	
	
/**
 * Initializes the table model with empty lists
 */
	public ViewStaffTableOrderOverviewTableModel()
	{
		// Creates an empty list to store all products
//TODO		this.productCollection = new ArrayList<>();
	}
	
	
	/**
 * Returns the name of a given column, based on its index.
 * 
 * @param columnIndex - The column index (starting at 0).
 * @return The name of the column as a String.
 */
	@Override
	public String getColumnName(int columnIndex)
	{
		// Retrieves the column name from the predefined array using the provided index
		return COLUMN_NAMES[columnIndex];
	}

/**
 * Returns the number of columns in the table.
 *
 * @return the column count
 */
	@Override
	public int getColumnCount()
	{
		// Returns the total number of columns based on the column names array length
		return COLUMN_NAMES.length;
	}
	
	
/**
 * Returns the number of rows currently displayed in the table.
 *
 * @return the row count
 */
	@Override
	public int getRowCount()
	{
		// Returns the size of the filtered list, which reptableCellValueents the number of rows
		return 0;
	}


	/**
	 * Gets the value in any given column, at any given row. Each row corresponds to
	 * one Product. rowIndex matches the list index at which the corresponding Product
	 * is stored. Each column contains a different type of data, thus each column
	 * has a unique implementation.
	 * 
	 * @param rowIndex    - The index of the row.
	 * @param columnIndex - The index of the column.
	 * @return Product - An object that corresponds to the property indicated by the
	 *         row, of the Order that corresponds to the row.
	 */
//TODO	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{

		// Old example code from a previous project to show how
		// it was done using a container
		// TODO
		
		// Retrieves the SimpleProduct object corresponding to the row index
//		SimpleProduct currentProduct = (SimpleProduct) getDataAt(rowIndex);
		
		// Initializes an empty string to store the cell value
		String tableCellValue = "";
		
		// Determines which field to display based on the column index
		switch (columnIndex)
		{
/*	        case 0:
	        	// Retrieves the ProductID and converts it to a string
	            tableCellValue = "" + currentProduct.getProductID();
	            break;
	            
	        case 1:
	        	// Retrieves the product name as a string
	            tableCellValue = "" + currentProduct.getProductName();
	            break;
	            
	        case 2:
	        	// Retrieves the product model name as a string
	            tableCellValue = "" + currentProduct.getProductModelName();
	            break;
	            
	        case 3:
	        	// Retrieves the product description as a string
	            tableCellValue = "" + currentProduct.getProductDescription();
	            break;
	            
	        case 4:
	        	// Retrieves the manufacturer name as a string
	            tableCellValue = "" + currentProduct.getManufacturerName();
	            break;
	            
	        case 5:
	        	// Retrieves the manufacturer ID and converts it to a string
	            tableCellValue = "" + currentProduct.getManufacturerID();
	            break;
	            
	        case 6:
	        	// Retrieves the MSRP (Manufacturer's Suggested Retail Price) as a string
	            tableCellValue = "" + currentProduct.getMsrp();
	            break;
	            
	        case 7:
	        	// Retrieves the cost price as a string
	            tableCellValue = "" + currentProduct.getCost();
	            break;
	            
	        case 8:
	        	// Retrieves the selling price as a string
	            tableCellValue = "" + currentProduct.getPrice();
	            break;
	            
	        case 9:
	        	// Retrieves the shelf location as a string
	            tableCellValue = "" + currentProduct.getShelfLocation();
	            break;
	            
	        case 10:
	        	// Retrieves the number of units on shelves and converts to a string
	            tableCellValue = "" + currentProduct.getUnitsOnShelves();
	            break;
	            
	        case 11:
	        	// Retrieves the storage location as a string
	            tableCellValue = "" + currentProduct.getStorageLocation();
	            break;
	            
	        case 12:
	        	// Retrieves the number of units in storage and converts to a string
	            tableCellValue = "" + currentProduct.getUnitsInStorage();
	            break;
	            
	        default:
	        	// Returns an unknown column index message
	        	tableCellValue = "<unknown " + columnIndex + ">";
	        	break;
	        	*/
		}
		
		// Returns the determined value for the table cell
		return tableCellValue;
	}

	
/**
 * Retrieves the product object for the given row index, handling potential errors.
 *
 * @param rowIndex the index of the row
 * @return the corresponding product object or null if out of bounds
 */
/*	public Product getDataAt(int rowIndex)
	{
    try
    {
        // Attempts to retrieve the product object from the filtered list
        return searchedList.get(rowIndex);
    }
    
    catch (IndexOutOfBoundsException exception)
    {
        // Returns null if the requested index is out of bounds
        return null;
    }
	}
*/
	
	/**
 * Updates the data in the table with a new collection of products.
 * 
 * @param list - The new list of products.
 */
/*	public void setData(List<SimpleProduct> list)
	{
		// Update the product list
//		this.productCollection = list;
		
    // Updates the displayed table data based on the new products
    updateTableModel();
	}
	*/
	
	
/**
 * Updates the table by notifying all listener that the content
 * in a cell might have changed
 */
	private void updateTableModel() 
	{
		// Notifies the table that the data has been modified
		fireTableDataChanged(); 
	}
}
