package database;

import java.sql.SQLException;
import java.util.List;

import model.AvailabilityTracker;
import model.MenuCard;

/**
 * An interface that defines a contract for accessing MenuCardImpl,
 * specifically for finding a list of menuCards and availabilityTacker based on its Code/ID.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It guarantees that certain functionality is available 
 * and any class that implements MenuItemImpl must provide a two methods called 
 * findMenuCardsByRestaurantCode and findAvailabilityTrackerById.
 * 
 * @author Line Bertelsen
 * @version 02/06/2025 - 16.02
 */
public interface MenuCardImpl
{
	/**
	 * Method creates a list containing a shallow clone of every MenuCard in the
	 * database with a matching restaurantCode in the MenuCard table. 
	 * 
	 * @param restaurantCode  		- whether the MenuCard is associated with 
	 * 		  						  the provide restaurantCode in the database.
	 * @return availableMenuCards 	- A list containing a shallow clone of every MenuCard in the database
	 * 		   						  with a matching restaurantCode.
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	List<MenuCard> findMenuCardsByRestaurantCode(String restaurantCode) throws DataAccessException, SQLException;
	
	/**
     * Method creates a list of shallow clone of the AvailabilityTrackers that has the given unique id. 
     * If no match is found the method returns null. 
     *
     * @param menuCardId	 		- the id of the menuItem to search for
     * @return availabilityTrackers - the AvailabilityTrackers object that matches the provided id
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
     */
	List<AvailabilityTracker> findAvailabilityTrackersByMenuCardId(int menuCardId) throws DataAccessException;
}
