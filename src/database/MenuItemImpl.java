package database;

// Imports
import java.sql.SQLException;
import java.util.List;

import model.AddOnOption;
import model.MenuItem;
import model.MultipleChoiceMenu;
import model.SelectionOption;


/**
 * An interface that defines a contract for accessing MenuItemDB,
 * specifically for finding a menu item, multiple choice menu, selection option and add on option based on its ID.
 *
 * This interface is part of the DAO (Data Access Object) design pattern and specifies the methods
 * required to retrieve data from the underlying data source.
 * 
 * The contract ensures consistency across all classes that implements the interface.
 * It also guarantees that certain functionality is available and any class that 
 * implements MenuItemImpl must provide the four methods called:
 * findMenuItemByMenuItemId, findMultipleChoiceMenuByChoiceMenuId, findSelectionOptionByChoiceMenuId and findAddOnOptionByMenuItemId.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 07/06/2025 - 15:46
 */
public interface MenuItemImpl
{
	/**
     * Method creates a shallow clone of the menuItem that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param menuItemId 			- the ID of the menu item to be retrieved
     * @return menuItem 			- the MenuItem object that matches the provided ID
 	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	MenuItem findMenuItemByMenuItemId(int menuItemId) throws DataAccessException, SQLException;
	
	
	/**
     * Method creates a list of shallow clones of the multipleChoiceMenu that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param multipleChoiceMenu 	- the ID of the multiple choice menu to be retrieved
     * @return multipleChoiceMenu 	- a list of MultipleChoiceMenu object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	List<MultipleChoiceMenu> findMultipleChoiceMenusByMainCourseId(int mainCourseId) throws DataAccessException, SQLException;
	
	
	/**
     * Method creates a list of shallow clones of the selectionOption that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param selectionOption 		- the ID of the menu item to be retrieved
     * @return selectionOption 		- a list of SelectionOption object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
     */
	List<SelectionOption> findSelectionOptionsByChoiceMenuId(int mainCourseId) throws DataAccessException, SQLException;
	
	
	/**
     * Method creates a list of shallow clones of the addOnOption that has the given unique ID. 
     * If no match is found the method returns null. 
     *
     * @param addOnOption the ID of the menu item to be retrieved
     * @return addOnOption 			- a list of AddOnOption object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
     */
	List<AddOnOption> findAddOnOptionsByMainCourseId(int mainCourseId) throws DataAccessException, SQLException;
}
