package database;

// Imports
import java.sql.SQLException;

import model.Table;


/**
 * An interface that defines a contract for accessing TableDB,
 * specifically for finding a restaurant by its code.
 *
 * This interface is part of the DAO (Data Access Object) design pattern and specifies the methods
 * required to retrieve data from the underlying data source.
 * 
 * The contract ensures consistency across all classes that implements the interface.
 * It also guarantees that certain functionality is available and any class that 
 * implements TableImpl must provide the one method called:
 * findTableByCode.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 07/06/2025 - 16:48
 */
public interface TableImpl
{
	/**
	 * This method creates a clone of the table that has a given tableCode. if no matching table
	 * is found the method returns null. 
	 * 
	 * @param tableCode 	- the code used in the search
	 * @return chosenTable 	- a clone of a table that matches the search parameters
	 * @throws SQLException	- if a SQL operation fails
     */
	Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException;
}
