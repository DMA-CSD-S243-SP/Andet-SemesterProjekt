package database;

import java.sql.SQLException;
import java.util.List;

import model.PersonalOrder;

/**
 * An interface that defines a contract for accessing PersonalOrderImpl,
 * specifically for finding a PersonalOrder based on its ID.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It guarantees that certain functionality is available 
 * and any class that implements MenuItemImpl must provide 
 * the methods called findPersonalOrderById .
 * 
 * @author Line Bertelsen
 * @version 02/06/2025 - 15:08
 */
public interface PersonalOrderImpl
{
	/**
     * Method creates a shallow clone of the personalOrder that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param personalOrderId  		- the code of the personalOrder to be retrieved
     * @return personalOrder 		- the PersonalOrder object that matches the provided ID
     * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
     * @throws SQLException			- if a SQL operation fails
     */
	PersonalOrder findPersonalOrderById(int personalOrderId) throws DataAccessException, SQLException;


	/**
	 * The method is used in ViewGuesTableOrderConfirmation in gui layer.
	 * and the method FinishPersonalOrder in PersonalOrderController.
	 * Inserts a new PersonalOrder into the PersonalOrder table in the Database. 
     *
	 * @param personalOrder			- the PersonalOrder object containing customer.
	 * @param tableOrderId 			- the Id that belongs to this personal order
	 * @return personalOrder		- that PersonalOrder object, now considered persisted in the database
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 */
	PersonalOrder insertPersonalOrder(PersonalOrder personalOrder, int tableOrderId) throws DataAccessException;
	
	/**
	 * The method is use in ViewGuesTableOrder in gui layer
	 * to find all personalOrders that is listed in the currentTableOrder
	 * 
	 * @param tableOrderId 			- the Id to find matching personalOrders
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	List<PersonalOrder> findPersonalOrdersBytableOrderId (int TableOrderId) throws SQLException, DataAccessException;
}
