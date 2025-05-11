package database;

import model.MenuItem;

/**
 * An interface that defines a contract for accessing table orders,
 * specifically for finding a menu item based on its ID.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It guarantees that certain functionality is available 
 * and any class that implements TableOrderImpl must provide a method called findMenuItemById.
 * That method must take an int and return a MenuItem, and it must be able to throw DataAccessException.
 * 
 * @author Line Bertelsen
 * @version 11.05.25 - 20:06
 */

public interface MenuItemImpl
{
	/**
     * Method creates a shallow clone of the menuItem that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param menuItemId the ID of the menu item to be retrieved
     * @return the MenuItem object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
     */
	MenuItem findMenuItemById(int menuItemId) throws DataAccessException;
}
