//Packages
package gui;

//Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.table.AbstractTableModel;

import model.EnumStatusType;
import model.PersonalOrder;
import model.PersonalOrderLine;
import model.TableOrder;

//import model.SimpleProduct;


/**
* TODO: Write a relatively detailed description of what this
* class represents, and add a version number containing both date 
* and time, matching the other classes' java documentation.
*
* @author Line Bertelsen & Christoffer Søndergaard & Lumière Schack
* @version 02/06/2025 - 15:30
*/
public class ViewStaffTableOrderOverviewTableModel extends AbstractTableModel
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// Array of column names for the table.
	private static final String[] COLUMN_NAMES = {"Bestillingsnummer", "Oprettelsestidspunkt", "Personens Navn", "Antal", "Rettens Navn", "Noter" };
	
	private List<String[]> tableContent;
	
	

	
	
/**
 * Initializes the table model with empty lists
 */
	public ViewStaffTableOrderOverviewTableModel()
	{
		tableContent = new ArrayList<>();
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
		return tableContent.size();
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
		Object value = "";
		// Checks if given value is within bounds
		if ( 0 <= rowIndex && rowIndex < tableContent.size() && columnIndex < COLUMN_NAMES.length && 0 <= columnIndex)
		{
			String[] row = tableContent.get(rowIndex);
			value = row[columnIndex];
		}
		return value;
	}
	
	public void setData(List<TableOrder> data)
	{
		tableContent = new ArrayList<>();
		for (TableOrder order: data)
		{
			// Adds header for the TableOrder
			tableContent.add(new String[]{""+order.getTableOrderId(), ""+order.getTimeOfArrival(), "", "", "", "",});
			
			List<PersonalOrder> listPersonOrders = order.getPersonalOrders();
			for (PersonalOrder personOrder: listPersonOrders) 
			{
				tableContent.add(new String[]{"", "", ""+personOrder.getCustomerName(), "", "", "",});
				
				List<PersonalOrderLine> plines = personOrder.getPersonalOrderLines();
				for (PersonalOrderLine personLine: plines)
				{
					tableContent.add(new String[]{"", "", "", ""+1, ""+personLine.getMenuItem().getName(), ""+personLine.getNotes(),});
				}
			}
		}
		
		updateTableModel();
	}

	
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
