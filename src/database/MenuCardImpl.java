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
 * @version 13.05.25 - 08.51
 */
public interface MenuCardImpl
{
	/**
	 * Method creates a list containing a shallow clone of every MenuCard in the
	 * database with a matching restaurantCode in the MenuCard table. 
	 * 
	 * 
	 * @param restaurantCode, whether the MenuCard is associated with 
	 * 		  the provide restaurantCode in the database.
	 * 
	 * @return A list containing a shallow clone of every MenuCard in the database
	 * 		   with a matching restaurantCode.
	 * 
	 * @throws DataAccessException if there is an issue accessing the data 
	 */
	List<MenuCard> findMenuCardsByRestaurantCode(String restaurantCode) throws DataAccessException, SQLException;
	
	/**
     * Method creates a shallow clone of the menuCard that has the given unique code. 
     * If no match is found the method returns null. 
     *
     * @param restaurantCode the code of the restaurant to be retrieved
     * @return the Restaurant object that matches the provided code
     * @throws DataAccessException if there is an issue accessing the data
     */
	List<AvailabilityTracker> findAvailabilityTrackersByMenuCardId(int menuCardId) throws DataAccessException,SQLException;
}
