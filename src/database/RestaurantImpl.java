package database;

import java.util.List;

import model.Restaurant;

/**
 * 
 * @author Anders Trankjær
 * @Version 2025/12/05/12:05 
 */
public interface RestaurantImpl
{
	/**
	 * TODO
	 * 
	 * @return - a list of all restaurants in the database
	 */
	List<Restaurant> findAllRestaurants();
	
	/**
	 * this method will search through the database using the search criteria from the parameterlist
	 * make a clone of using the data found and return that. 
	 * 
	 * @param restaurantCode
	 * @return
	 */
	Restaurant findRestaurantByCode(String restaurantCode);
}
