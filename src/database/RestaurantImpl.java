package database;

import java.sql.SQLException;

import model.Restaurant;

/**
 * 
 * @author Anders Trankjær
* @version 02/06/2025 - 14:19 
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
