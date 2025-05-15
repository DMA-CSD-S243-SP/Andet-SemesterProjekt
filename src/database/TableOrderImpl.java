package database;

import java.sql.SQLException;
import java.util.List;

import model.TableOrder;

/**
 * /**
 * An interface that defines a contract for accessing TableOrderImpl,
 * specifically for finding a Table Order based on its ID.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It guarantees that certain functionality is available  
 * and any class that implements TableOrderImpl must provide the methods findAllTableOrders and findTableOrderById
 * 
 * @author Line Bertelsen
 * @version 12.05.25 - 17.30
 */

public interface TableOrderImpl
{
	/**
	 * Method creates a list containing a shallow clone of every tableOrder in the
	 * database. If fullAssociation is false then associations will only be filled
	 * with information found in the respective row of each tableOrder in the tableOrder table. 
	 * Otherwise associations will be filled in with the information from the
	 * row that the TableOrders has a key to.
	 * 
	 * 
	 * @return A list containing a shallow clone of every TableOrder in the database.
	 * @throws DataAccessException if there is an issue accessing the data
	 * @throws SQLException 
	 */
	List <TableOrder> findAllTableOrders() throws DataAccessException, SQLException;

	/**
     * Method creates a shallow clone of the tableOrder that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param tableOrder the ID of the menu item to be retrieved
     * @return the TableOrder object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
	 * @throws SQLException 
     */
	TableOrder findTableOrderByTableOrderId(int tableOrderId) throws DataAccessException, SQLException;
}
