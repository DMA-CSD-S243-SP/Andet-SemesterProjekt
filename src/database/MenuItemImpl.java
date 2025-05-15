package database;

import java.sql.SQLException;

import model.AddOnOption;
import model.MenuItem;
import model.MultipleChoiceMenu;
import model.SelectionOption;

/**
 * An interface that defines a contract for accessing MenuItemImpl,
 * specifically for finding a menu item, multiple choice menu, selection option and add on option based on its ID.
 * 
 * The contract ensures consistency across all classes that implement the interface. 
 * It guarantees that certain functionality is available 
 * and any class that implements MenuItemImpl must provide a four methods called 
 * findMenuItemByMenuItemId, findMultipleChoiceMenuByChoiceMenuId, findSelectionOptionByChoiceMenuId and findAddOnOptionByMenuItemId.
 * 
 * @author Line Bertelsen
 * @version 12.05.25 - 16.00
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
	 * @throws SQLException 
     */
	MenuItem findMenuItemByMenuItemId(int menuItemId) throws DataAccessException, SQLException;
	
	/**
     * Method creates a shallow clone of the multipleChoiceMenu that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param multipleChoiceMenu the ID of the multiple choice menu to be retrieved
     * @return the MultipleChoiceMenu object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
	 * @throws SQLException 
     */
	MultipleChoiceMenu findMultipleChoiceMenuByMainCourseId(int mainCourseId) throws DataAccessException, SQLException;
	
	/**
     * Method creates a shallow clone of the selectionOption that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param selectionOption the ID of the menu item to be retrieved
     * @return the SelectionOption object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
	 * @throws SQLException 
     */
	SelectionOption findSelectionOptionByMainCourseId(int mainCourseId) throws DataAccessException, SQLException;
	
	/**
     * Method creates a shallow clone of the addOnOption that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param addOnOption the ID of the menu item to be retrieved
     * @return the AddOnOption object that matches the provided ID
     * @throws DataAccessException if there is an issue accessing the data
	 * @throws SQLException 
     */
	AddOnOption findAddOnOptionByMainCourseId(int mainCourseId) throws DataAccessException, SQLException;
}
