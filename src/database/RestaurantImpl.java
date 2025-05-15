package database;

import java.sql.SQLException;

import model.Restaurant;

/**
 * 
 * @author Anders Trankjær
 * @Version 2025/12/05/12:05 
 */
public interface RestaurantImpl
{
	/**
	 * this method will search through the database using the search criteria from the parameterlist
	 * make a clone of using the data found and return that. 
	 * 
	 * @param restaurantCode
	 * @return
	 * @throws DataAccessException 
	 * @throws SQLException 
	 */
	Restaurant findRestaurantByCode(String restaurantCode) throws DataAccessException, SQLException;
}
