package application;

// Imports
import java.sql.SQLException;

import database.DataAccessException;
import database.TableDB;
import database.TableImpl;
import model.Table;
import model.TableOrder;


/**
 * The TableController acts as a bridge between the GUI layer and the data access layer (DAO).
 * The controller coordinates the requests and responses related to Table entities, by delegating
 * the database operations to the DAO implementation TableDB.
 *
 * This separation helps to ensure low coupling between GUI and data persistence, and thereby improve
 * the system's maintainability and possibility to scale.
 *
 *
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 07/06/2025 - 17:28
 */
public class TableController
{
    /**
     * Finds a specific table in a given restaurant, based on the unique combination of the
     * table's number and the supplied restaurant code.
     *
     * The controller delegates the data retrieval to the DAO layer, hiding database logic from the GUI.
     *
     * @param tableNumber the four digit number used to identify a table
     * @param restaurantCode the unique three digit number used to identify a specific restaurant
     * @return the table with the given tableCode or null if non was found
     * @throws DataAccessException if a database access issue occurs somewhere at the DAO level
     * @throws SQLException if an SQL query execution fails
     */
	public Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException 
	{
		// Creates an instance of the DAO interface using the concrete TableDB class implementation
		TableImpl dataAccessObject = new TableDB();

		// Calls upon the DAO method to find the Table object with a matching 
		// concatenated tableNumber and restaurantCode
		return dataAccessObject.findTableByCode(tableNumber, restaurantCode);
	}
	
	
    /**
     * Retrieves the TableOrder object that is currently associated with the specified Table instance.
     *
     * This method delegates to the model layer to extract the current active order from the Table instance.
     * Useful for viewing or modifying the ongoing order of a particular table.
     *
     * @param table the table we want to find the tableOrder of
     * @return the active TableOrder associated with the supplied Table object
     */
	public TableOrder getCurrentTableOrder(Table table)
	{
		// Returns the TableOrder instance this Table object is associated with from the Table class in the model layer
		return table.getCurrentTableOrder();
	}
}
