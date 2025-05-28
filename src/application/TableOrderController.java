package application;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.TableOrderDB;
import database.TableOrderImpl;
import model.TableOrder;

/**
 * a controller class that bridges the logic from the model layer and the user interface from the gui layer
 * 
 * @author Anders Trankjær
 * @version 2025/19/05/12:40
 */
public class TableOrderController
{

	public TableOrderController()
	{
		
	}
	
	
	/**
	 * sets the given tableOrder objects isSentToKitchen variable to true so its ready to be sent to the kitchen
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
		TableOrderImpl dao = new TableOrderDB();
		dao.updateTableOrder(tableOrder);
	}
	
	
	/**
	 * returns a list of all tableOrders that have the isSentToKitchen variable to true 
	 * 
	 * @return - a list of all tableOrders that are "sent to kitchen"
	 * @throws SQLException
	 * @throws DataAccessException 
	 */
	public List<TableOrder> findAllVisibleToKitchenTableOrders() throws SQLException, DataAccessException
	{
		TableOrderImpl dao = new TableOrderDB();
		return dao.findAllVisibleToKitchenTableOrders();
	}
	
}
