package database;

// Imports
import java.sql.SQLException;

import model.Restaurant;


/**
 * An interface that defines a contract for accessing RestaurantDB,
 * specifically for finding a restaurant by its code.
 *
 * This interface is part of the DAO (Data Access Object) design pattern and specifies the methods
 * required to retrieve data from the underlying data source.
 * 
 * The contract ensures consistency across all classes that implements the interface.
 * It also guarantees that certain functionality is available and any class that 
 * implements RestaurantImpl must provide the one method called:
 * findRestaurantByCode.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 07/06/2025 - 16:38
 */
public interface RestaurantImpl
{
	/**
	 * This method will search through the database using the search criteria from the parameterlist
	 * make a clone of using the data found and return that. 
	 * 
	 * @param restaurantCode		- the Restaurant object containing the restaurant code
	 * @return restaurant 			- that match the restaurantCode 
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	Restaurant findRestaurantByCode(String restaurantCode) throws DataAccessException, SQLException;
}
