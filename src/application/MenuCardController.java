package application;

// Imports
import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.MenuCardDB;
import database.MenuCardImpl;
import model.MenuCard;


/**
 * The MenuCardController acts as a bridge between the GUI layer and the data access layer (DAO).
 * The controller coordinates the requests and responses related to MenuCard entities, by delegating
 * the database operations to the DAO implementation MenuCardDB.
 *
 * This separation helps to ensure low coupling between GUI and data persistence, and thereby improve
 * the system's maintainability and possibility to scale.
 *
 *
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 07/06/2025 - 17:16
 */
public class MenuCardController
{
    /**
     * Retrieves a list of MenuCard objects from a restaurant with a given restaurantCode
     *
     * The controller delegates the data retrieval to the DAO layer, hiding database logic from the GUI.
     *
     * @param restaurantCode the unique code used to identify a specific restaurant
     * @return a list of MenuCard instances associated with the given restaurant
     * @throws DataAccessException if a database access issue occurs somewhere at the DAO level
     * @throws SQLException if an SQL query execution fails
     */
	public List<MenuCard> findMenuCardsByRestaurantCode(String restaurantCode) throws DataAccessException, SQLException
	{
		// Creates an instance of the DAO interface using the concrete MenuCardDB class implementation
		MenuCardImpl dataAccessObject = new MenuCardDB();
	
		// Calls upon the DAO method to retrieve a list of menucard objects from the Restaurant with the specified code
		return dataAccessObject.findMenuCardsByRestaurantCode(restaurantCode);
	}
}
