package database;

// Imports
import java.sql.SQLException;
import java.util.List;

import model.PersonalOrder;


/**
 * An interface that defines a contract for accessing PersonalOrderDB,
 * specifically for finding a PersonalOrder based on its ID.
 * 
 * This interface is part of the DAO (Data Access Object) design pattern and specifies the methods
 * required to retrieve data from the underlying data source.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It also guarantees that certain functionality is available and any class that 
 * implements PersonalOrderImpl must provide the methods called:
 * findPersonalOrderById, insertPersonalOrder, and findPersonalOrdersBytableOrderId.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 11/06/2025 - 16:19
 */
public interface PersonalOrderImpl
{
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
	List<PersonalOrder> findPersonalOrdersBytableOrderId(int TableOrderId) throws SQLException, DataAccessException;
	
	
	/**
	 * NOTE: This method is related to a future use case: Read PersonalOrder
	 * and is currently not being used anywhere in the system.
	 * 
     * Method creates a shallow clone of the personalOrder that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param personalOrderId  		- the code of the personalOrder to be retrieved
     * @return personalOrder 		- the PersonalOrder object that matches the provided ID
     * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
     * @throws SQLException			- if a SQL operation fails
     */
	PersonalOrder findPersonalOrderById(int personalOrderId) throws DataAccessException, SQLException;
}
