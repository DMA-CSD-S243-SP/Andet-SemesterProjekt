package database;

//Imports
import java.sql.SQLException;
import java.util.List;

import model.TableOrder;


/**
 * /** An interface that defines a contract for accessing TableOrderDB,
 * specifically for finding a Table Order based on its ID.
 * 
 * This interface is part of the DAO (Data Access Object) design pattern and specifies the methods
 * required to retrieve data from the underlying data source.
 * 
 * The contract ensures consistency across all classes that implements the interface.
 * It also guarantees that certain functionality is available and any class that 
 * implements TableOrderImpl must provide the one method called:
 * findAllTableOrders and findTableOrderById.
 * 
 * 
 * @author Line Bertelsen
 * @version 02/06/2025 - 13:54
 */

public interface TableOrderImpl
{
	/**
	 * Method creates a list containing a shallow clone of every tableOrder in the
	 * database. If fullAssociation is false then associations will only be filled
	 * with information found in the respective row of each tableOrder in the
	 * tableOrder table. Otherwise associations will be filled in with the
	 * information from the row that the TableOrders has a key to.
	 * 
	 * 
	 * @return TableOrderlist 		- A list containing a shallow clone of every TableOrder in the database.
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	List<TableOrder> findAllTableOrders() throws DataAccessException, SQLException;

	/**
	 * Method creates a shallow clone of the tableOrder that has the given unique
	 * ID. If no match is found the method returns null.
	 *
	 * @param tableOrder 			- the ID of the menu item to be retrieved
	 * @return tableOrder 			- the TableOrder object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	TableOrder findTableOrderByTableOrderId(int tableOrderId) throws DataAccessException, SQLException;

	/**
	 * Method updates a given TableOrder in data storage. Doesn't add associations, just updates the specific TableOrderRow
	 * 
	 * @param tableOrder		 	- the TableOrder to be updated.
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	void updateTableOrder(TableOrder tableOrder) throws DataAccessException, SQLException;

	/**
	 * Method gets all the TableOrders that the kitchen should be able to see.
	 * 
	 * @return TableOrder list 		- A List containing every TableOrder where isSentToKitchen is set to
	 *         					      true, and isTableOrderClosed is set to false
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	List<TableOrder> findAllVisibleToKitchenTableOrders() throws DataAccessException, SQLException;
}
