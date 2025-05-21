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
 * @version 13.05.25 - 12.25
 */
public interface PersonalOrderImpl
{
	/**
     * Method creates a shallow clone of the personalOrder that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param personalOrderId the code of the personalOrder to be retrieved
     * @return the PersonalOrder object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
     */
	PersonalOrder findPersonalOrderById(int personalOrderId) throws DataAccessException;


	/**
	 * Inserts a new PersonalOrder into the PersonalOrder table in the Database. 
     *
	 * @param personalOrder
	 * @return
	 * @throws DataAccessException
	 */
	PersonalOrder insertPersonalOrder(PersonalOrder personalOrder, int tableOrderId) throws DataAccessException;
	
	List<PersonalOrder> findPersonalOrderBytableOrderId(int TableOrderId) throws SQLException, DataAccessException;
}
