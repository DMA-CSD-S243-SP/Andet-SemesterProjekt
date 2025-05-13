package application;

import java.sql.SQLException;

import database.DataAccessException;
import database.RestaurantDB;
import database.RestaurantImpl;
import model.Restaurant;

/**
 * a controller class that bridges the logic from the model layer and the user interface from the gui layer
 * 
 * @author Anders Trankjær
 * @version 2025/13/05/12:45
 */
public class RestaurantController
{
	
	/**
	 * this method retrieves the restaurantCode from a given restaurant object
	 * 
	 * @param restaurant
	 * @return
	 */
	public String getRestaurantCode(Restaurant restaurant)
	{
		return restaurant.getRestaurantCode();
	}
	
	/**
	 * this method asks the Database to retrieve a restaurant object with a given restaurantCode
	 * 
	 * @param restaurantCode - the searchcriteria for finding the restaurant object
	 * @return a restaurant object with the given restaurantCode or if nnothing was found it will return null
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public Restaurant findRestaurantByCode(String restaurantCode) throws SQLException, DataAccessException
	{
		RestaurantImpl dao = new RestaurantDB();
		return dao.findRestaurantByCode(restaurantCode);
	}
}
