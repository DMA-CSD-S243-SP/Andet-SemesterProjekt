package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is responsible for accessing and managing employee objects stored
 * in a database.
 * 
 * It implements the employeeDaoImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen
 * @version 11.05.2025 - 20:18
 */

public class MenuItemDB implements MenuCardImpl
{

	// Selects a row from the table menuItem in the database, based on the given MenuItemID
	private static final String FIND_MENUITEM_BY_MENUITEMID_QUERY = "SELECT * FROM MenuItem WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an menuItem based on the MenuItemID
	private PreparedStatement statementFindMenuItemById;
	
	
	// Selects a row from the table menuItem in the database, based on the given MenuItemID
	private static final String FIND_MULTIPLECHOICEMENU_BY_MENUITEMID_QUERY = "SELECT * FROM MultipleChoiceMenu WHERE menuItemID = ?";
		
	// PreparedStatement for retrieving an MultipleChoiceMenu based on the MenuItemID
	private PreparedStatement statementMultipleChoiceMenuByMenuItemId;
	
	
	// Selects a row from the table menuItem in the database, based on the given ChoiceMenuId
	private static final String FIND_SELECTIONOPTION_BY_CHOICEMENUID_QUERY = "SELECT * FROM SelectionOption WHERE ChoiceMenuItemID = ?";
			
	// PreparedStatement for retrieving an SelectionOption based on the ChoiceMenuId
	private PreparedStatement statementSelectionOptionByChoiceMenuId;
	
	
	// Selects a row from the table menuItem in the database, based on the given MenuItemId
	private static final String FIND_ADDONOPTION_BY_MENUITEMID_QUERY = "SELECT * FROM AddOnOption WHERE MenuItemId = ?";
				
	// PreparedStatement for retrieving an SelectionOption based on the MenuItemId
	private PreparedStatement statementAddOnOptionByMenuItemId;

	
	/**
	 * Constructor for MenuItemDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public MenuItemDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving all employees
		statementFindMenuItemById = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MENUITEM_BY_MENUITEMID_QUERY);
		
		// Prepares the SQL statement for retrieving an Employee by their employee id
		statementMultipleChoiceMenuByMenuItemId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MULTIPLECHOICEMENU_BY_MENUITEMID_QUERY);
		
		// Prepares the SQL statement for retrieving all employees
		statementSelectionOptionByChoiceMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELECTIONOPTION_BY_CHOICEMENUID_QUERY);
				
		// Prepares the SQL statement for retrieving an Employee by their employee id
		statementAddOnOptionByMenuItemId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_ADDONOPTION_BY_MENUITEMID_QUERY);
	}

	 
}
