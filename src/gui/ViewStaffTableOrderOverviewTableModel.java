//Packages
package gui;

//Imports
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.PersonalOrder;
import model.PersonalOrderLine;
import model.TableOrder;


/**
 * The ViewStaffTableOrderOverviewTableModel class serves as a custom table model 
 * in order to render the staff-facing overviews of all active table orders in the restaurant.
 * 
 * Each row in the table represents either a table order, a personal order within that table,
 * or an individual dish that needs preparation by kitchen staff. 
 * 
 * To give the kitchen staff a better overview the table model 
 * is structured in the following order:
 * - Order Number
 * - - Customer's Name
 * - - - Quantity
 * - - - Name of Dish
 * - - - Notes to the dish
 * 
 * The class extends AbstractTableModel and overrides the standard table model methods 
 * for dynamic rendering of data.
 * 
 * 
 * @author: Line Bertelsen & Christoffer Søndergaard & Lumière Schack
 * @version: 08/06/2025 - 14:03
 */
public class ViewStaffTableOrderOverviewTableModel extends AbstractTableModel
{
	// Added in order to suppress the warning that appears in serializable classes
	// where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;

	// Array of column names for the table.
	private static final String[] COLUMN_NAMES = { "Bestillingsnummer", "Oprettelsestidspunkt", "Personens Navn", "Antal", "Rettens Navn", "Noter" };

	private List<String[]> tableModelContent;

	
	/**
	 * Constructs a new ViewStaffTableOrderOverviewTableModel
	 * and initializes its internal table data structure to an empty list.
	 */
	public ViewStaffTableOrderOverviewTableModel()
	{
		tableModelContent = new ArrayList<>();
	}

	
	/**
	 * Returns the name of a given column at the specified index.
	 *
	 * This is used by JTable to label each column header.
	 *
	 * @param columnIndex the zero-based index of the column
	 * @return the column name as a string
	 */
	@Override
	public String getColumnName(int columnIndex)
	{
		// Retrieves the column name from the predefined array using the provided index
		return COLUMN_NAMES[columnIndex];
	}

	
	/**
	 * Returns the total number of columns in the table model.
	 *
	 * @return the number of defined column headers
	 */
	@Override
	public int getColumnCount()
	{
		// Returns the total number of columns based on the column names array length
		return COLUMN_NAMES.length;
	}

	
	/**
	 * Returns the number of rows currently in the table model.
	 *
	 * This depends on how many TableOrders, PersonalOrders, and 
	 * kitchen-relevant PersonalOrderLines are currently stored.
	 *
	 * @return the number of visible rows in the table
	 */
	@Override
	public int getRowCount()
	{
		return tableModelContent.size();
	}

	
	/**
	 * Retrieves the value that is stored within the specific cell in the table
	 * which is identified by the row and column index.
	 *
	 * This method provides the visual content for the table display.
	 *
	 * @param rowIndex    the zero-based index of the row
	 * @param columnIndex the zero-based index of the column
	 * @return the value stored at the specified cell, or an empty string if the value is out of bounds
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		// Initialize a default value as an empty string
		Object cellValue = "";
		
		// Checks if given value is within bounds
		
		// If both the rowIndex and columnIndex are within valid bounds then execute this section
		if (0 <= rowIndex && rowIndex < tableModelContent.size() && columnIndex < COLUMN_NAMES.length && 0 <= columnIndex)
		{
			// Retrieves the array of data at the specified row
			String[] row = tableModelContent.get(rowIndex);
			
			// Extracts the value of the cell from the specified column in that row
			cellValue = row[columnIndex];
		}
		
		// Returns the found cell's value or an empty string if it is out of bounds
		return cellValue;
	}

	
	/**
	 * Populates the table model with data in the form of a list of TableOrders.
	 *
	 * Each TableOrder is divided in to multiple rows:
	 * - One header row for the TableOrder ID and timestamp
	 * - One row per PersonalOrder showing the customer's name
	 * - One row per PersonalOrderLine that needs kitchen preparation
	 * - Two empty rows used for visual spacing
	 *
	 * This method also clears any previously added data to the table model.
	 *
	 * @param data a list of TableOrder objects to be displayed within the table
	 */
	public void setData(List<TableOrder> listOfData)
	{
		// Reset the internal list that stores the table's row data
		tableModelContent = new ArrayList<>();
		
		// Uses a for-each loop to iterate through each TableOrder provided in the supplied listOfData variable
		for (TableOrder tableOrder : listOfData)
		{
			// Adds a heading row representing the table order's ID and the first guest's time of arrival
			tableModelContent.add(new String[] 
			{
					"" + tableOrder.getTableOrderId(), "" + tableOrder.getTimeOfArrival(), "", "", "", "", 
			});
			
			// Retrieves the list of PersonalOrders objects associated with this table order and stores it within the listOfPersonalOrders variable
			List<PersonalOrder> listOfPersonalOrders = tableOrder.getPersonalOrders();
			
			// Uses a for-each loop to iterate through each PersonalOrder object within the listOfPersonalOrders
			for (PersonalOrder personalOrder : listOfPersonalOrders)
			{
				// Adds a row with the customer's name within the table order to distinguish the buyer
				tableModelContent.add(new String[]
				{
						"", "", "" + personalOrder.getCustomerName(), "", "", "",
				});

				// Retrieves all individual PersonalOrderLine objects associated with this PersonalOrder
				List<PersonalOrderLine> listOfPersonalOrderLines = personalOrder.getPersonalOrderLines();
				
				// Uses a for-each loop to iterate through each PersonalOrderLine object within the listOfPersonalOrderLines
				for (PersonalOrderLine personalOrderLine : listOfPersonalOrderLines)
				{
					// If the PersonalOrderLine item is required to be made by kitchen staff then execute this section
					if (personalOrderLine.getMenuItem().isMadeByKitchenStaff())
					{
						// Adds a row with the quantity, the menu item's name and any possible additional notes
						tableModelContent.add(new String[]
						{
								"", "", "", "" + 1, "" + personalOrderLine.getMenuItem().getName(), "" + personalOrderLine.getNotes(),
						});

					}
				}
			}
			
			// Adds two rows that are empty to create visual space in the table to make it 
			// easier for the kitchen personel to distinguish the table orders from each other
			tableModelContent.add(new String[] { "", "", "", "", "", "" });
			tableModelContent.add(new String[] { "", "", "", "", "", "" });
		}

		// Notify all listeners that the table data has changed so the UI can refresh
		updateTableModel();
	}

	
	/**
	 * Triggers a refresh of the table data.
	 *
	 * This actually uses an observer pattern, and therefore triggers all observers like
	 * the JTable component, informing it that the table model's data has changed and makes it
	 * request the GUi to re-render the table model.
	 */
	private void updateTableModel()
	{
		// Notifies the table that the data has been modified
		fireTableDataChanged();
	}
}
