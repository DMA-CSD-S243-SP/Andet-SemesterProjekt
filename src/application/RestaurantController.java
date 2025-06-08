package application;

// Imports
import java.sql.SQLException;

import database.DataAccessException;
import database.RestaurantDB;
import database.RestaurantImpl;
import model.Restaurant;


/**
 * The RestaurantController acts as a bridge between the GUI layer and the data access layer (DAO).
 * The controller coordinates the requests and responses related to Restaurant entities, by delegating
 * the database operations to the DAO implementation RestaurantDB.
 *
 * This separation helps to ensure low coupling between GUI and data persistence, and thereby improve
 * the system's maintainability and possibility to scale.
 *
 *
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 07/06/2025 - 17:51
 */
public class RestaurantController
{
    /**
     * Retrieves the unique restaurantCode from the given Restaurant object.
     *
     * This method delegates to the model layer to extract the Restaurant's 
     * unique three digit restaurantCode, from the specified Restaurant instance.
     *
     * @param restaurant the Restaurant object to retrieve the restaurantCode from
     * @return the restaurant's unique three digit identification code
     */
	public String getRestaurantCode(Restaurant restaurant)
	{
		// Returns the restaurant code from the Restaurant class in the model layer
		return restaurant.getRestaurantCode();
	}
	
	
    /**
     * Finds and returns a Restaurant object based on its unique code.
     *
     * This method delegates the database query to the DAO layer and returns the matching Restaurant.
     * It encapsulates all data retrieval logic related to restaurant identification.
     *
     * @param restaurantCode the unique three digit code that is used to identify the restaurant
     * @return the Restaurant object associated with the given restaurantCode or null if nothing was found
     * @throws DataAccessException if a database access issue occurs somewhere at the DAO level
     * @throws SQLException if an SQL query execution fails
     */
	public Restaurant findRestaurantByCode(String restaurantCode) throws SQLException, DataAccessException
	{
		// Creates an instance of the DAO interface using the concrete RestaurantDB class implementation
        RestaurantImpl dataAccessObject = new RestaurantDB();
        
        // Calls upon the DAO method to find the Restaurant object with a matching restaurantCode
        return dataAccessObject.findRestaurantByCode(restaurantCode);
	}
}
