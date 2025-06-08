// Packages
package application;

// Imports
import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.TableOrderDB;
import database.TableOrderImpl;
import model.TableOrder;


/**
 * The TableOrderController acts as a bridge between the GUI layer and the data access layer (DAO).
 * The controller coordinates the requests and responses related to TableOrder entities, by delegating
 * the database operations to the DAO implementation TableOrderDB.
 * 
 * This separation helps to ensure low coupling between GUI and data persistence, and thereby improve
 * the system's maintainability and possibility to scale.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 08/06/2025 - 15:28
 */
public class TableOrderController
{
    /**
     * Constructs a new TableOrderController instance with no initialization needed.
     */
	public TableOrderController()
	{
		
	}
	
	
	/**
	 * sets the given tableOrder objects isSentToKitchen instance variable to true 
	 * so its ready to be sent to the kitchen
	 * 
	 * @param tableOrder -  the given tabelOrder
	 */
	public void sendToKitchen(TableOrder tableOrder)
	{
		tableOrder.setSentToKitchen(true);
	}
	
	
	/**
	 * This method is used in ViewGuesTableOrderConfirmation
	 * it update a given tableOrder object into the database
	 * when the customer press bthnConfirm "Ja, send til køkken".
	 * 
	 * @param tableOrder - the tableorder that is to be updated
	 * @throws SQLException - is catch/caught in ViewGuesTableOrderConfirmation
	 * @throws DataAccessException - is catch/caught in ViewGuesTableOrderConfirmation
	 */
	public void updateTableOrder(TableOrder tableOrder) throws SQLException, DataAccessException
	{
		// Creates an instance of the DAO interface using the concrete TableOrderDB class implementation
		TableOrderImpl dataAccessObject = new TableOrderDB();
		
		// Calls upon the DAO method to update the given TableOrder object 
		dataAccessObject.updateTableOrder(tableOrder);
	}
	
	
	/**
	 * returns a list of all tableOrders that have the isSentToKitchen instance variable
	 * set to true and the isTableOrderClosed set to false
	 * 
	 * @return - a list of all tableOrders that are "sent to kitchen"
	 * @throws SQLException
	 * @throws DataAccessException 
	 */
	public List<TableOrder> findAllVisibleToKitchenTableOrders() throws SQLException, DataAccessException
	{
		// Creates an instance of the DAO interface using the concrete TableOrderDB class implementation
		TableOrderImpl dataAccessObject = new TableOrderDB();
		
		// Returns a list of TableOrder objects where the isSentToKitchen attribute
		// has been set to true and the isTableOrderClosed attribute is still false
		return dataAccessObject.findAllVisibleToKitchenTableOrders();
	}
}
