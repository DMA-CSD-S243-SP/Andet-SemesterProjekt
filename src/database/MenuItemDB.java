package database;

import java.lang.classfile.AnnotationValue.OfAnnotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EnumBarType;
import model.MenuItem;
import model.PotatoDish;
import model.SelfServiceBar;

/**
 * This class is responsible for accessing and managing employee objects stored
 * in a database.
 * 
 * It implements the employeeDaoImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen
 * @version 11.05.2025 - 21:10
 */

public class MenuItemDB implements MenuItemImpl
{
	// Selects a row from the table menuItem in the database, based on the given menuItemID
	private static final String FIND_MENUITEM_BY_MENUITEMID_QUERY = "SELECT * FROM MenuItem WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an menuItem based on the menuItemID
	private PreparedStatement statementFindMenuItemById;
	
	private static final String FIND_SELFSERVEBAR_BY_MENUITEMID_QUERY = "SELECT * FROM SelfServiceBar WHERE menuItemID = ?";
	private PreparedStatement statementFindSelfServiceBarByMenuId;
	
	private static final String FIND_DIPANDSAUCES_BY_MENUITEMID_QUERY = "SELECT * FROM DipAndSauces WHERE menuItemID = ?";
	private PreparedStatement statementFindDipsAndSaucesByMenuId;
	
	private static final String FIND_POTATODISH_BY_MENUITEMID_QUERY = "SELECT * FROM PotatoDish WHERE menuItemID = ?";
	private PreparedStatement statementFindPotatoDishByMenuId;
	
	private static final String FIND_SIDEDISH_BY_MENUITEMID_QUERY = "SELECT * FROM SideDish WHERE menuItemID = ?";
	private PreparedStatement statementFindSideDishByMenuId;
	
	private static final String FIND_DRINK_BY_MENUITEMID_QUERY = "SELECT * FROM Drink WHERE menuItemID = ?";
	private PreparedStatement statementFindDrinkByMenuId;
	
	
	// Selects a row from the table menuItem in the database, based on the given MenuItemID
	private static final String FIND_MULTIPLECHOICEMENU_BY_CHOICEMENUID_QUERY = "SELECT * FROM MultipleChoiceMenu WHERE ChoiceMenuId = ?";
		
	// PreparedStatement for retrieving an MultipleChoiceMenu based on the menuItemID
	private PreparedStatement statementMultipleChoiceMenuByChoiceMenuId;
	
	
	// Selects a row from the table menuItem in the database, based on the given choiceMenuId
	private static final String FIND_SELECTIONOPTION_BY_CHOICEMENUID_QUERY = "SELECT * FROM Option WHERE ChoiceMenuId = ?";
			
	// PreparedStatement for retrieving an SelectionOption based on the choiceMenuId
	private PreparedStatement statementSelectionOptionByChoiceMenuId;
	
	
	// Selects a row from the table menuItem in the database, based on the given menuItemId
	private static final String FIND_ADDONOPTION_BY_MENUITEMID_QUERY = "SELECT * FROM AddOnOption WHERE menuItemId = ?";
				
	// PreparedStatement for retrieving an SelectionOption based on the menuItemId
	private PreparedStatement statementAddOnOptionByMenuItemId;

	
	/**
	 * Constructor for MenuItemDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public MenuItemDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindMenuItemById = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MENUITEM_BY_MENUITEMID_QUERY);
		
		// Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindSelfServiceBarByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELFSERVEBAR_BY_MENUITEMID_QUERY);
		
		// Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindPotatoDishByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_POTATODISH_BY_MENUITEMID_QUERY);
		
		// Prepares the SQL statement for retrieving a MultipleChoiceMenu by its menuItemId
		statementMultipleChoiceMenuByChoiceMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MULTIPLECHOICEMENU_BY_CHOICEMENUID_QUERY);
		
		// Prepares the SQL statement for retrieving a SelcetionOption by its choiceMenuId
		statementSelectionOptionByChoiceMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELECTIONOPTION_BY_CHOICEMENUID_QUERY);
				
		// Prepares the SQL statement for retrieving a AddOnOption by its menuItemID
		statementAddOnOptionByMenuItemId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_ADDONOPTION_BY_MENUITEMID_QUERY);
	}

	
	/**
	 * Finds a MenuItem object by searching for a MenuItem with a matching menuItem id.
	 * 
	 * @param menuItemId the id of the menuItem to search for
	 * @return the corresponding MenuItem object, or null if not found
	 * @throws DataAccessException if retrieval fails
	 */
	@Override
	public MenuItem findMenuItemByMenuItemId(int menuItemId) throws DataAccessException
	{
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			//Prepare a SQL statement to find and retrieve MenuItem with a matching menuItemId
			statementFindMenuItemById = databaseConnection.prepareStatement(FIND_MENUITEM_BY_MENUITEMID_QUERY);
			
			//Add the menuItem id provided in the method's parameter to the String instead of the placeholder
			statementFindMenuItemById.setInt(1, menuItemId);
			
			//Execute the query, and store the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindMenuItemById.executeQuery();
			
			//Create and initializes an MenuItem object as null, which will later be populated with MenuItem specific data
			MenuItem menuItem = null;
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if(resultSet.next())
			{
				//Converts the retrieved database row into an MenuItem object using the buildMenuItemObject method
				menuItem = buildMenuItemObject(resultSet);
			}
			
			// Returns the menuItem with a matching menuItem id or null if no menuItem has the specified menuItem id
			return menuItem;
		} 
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an MenuItem object with an menuItem id matching: " + menuItemId, exception);
		}
	}	

	/**
     * Builds a specific MenuItem object from a database resultSet.
     * 
     * @param resultSet the result set containing MenuItem data
     * @return a MenuItem object with the extracted data
     * @throws SQLException if accessing the resultSet fails
     */
	private MenuItem buildMenuItemObject(ResultSet resultSet) throws SQLException
	{		
		// Extracts common fields from the MenuItem table
		int menuItemId = resultSet.getInt("menuItemId");
	    String name = resultSet.getString("name");
	    String description = resultSet.getString("description");
	    int preparationTime = resultSet.getInt("preparationTime");
		    
		// Gets the item type (e.g. "PotatoDish")
		String itemType = resultSet.getString("itemType");             
		boolean isMadeByKitchenStaff = resultSet.getBoolean("isMadeByKitchenStaff");
			
		// Placeholder to store the fully constructed item
		MenuItem menuItem = null; 
		
		
		//SELF SERVICE BAR
		// Check if the item type is "SelfServiceBar" and build the corresponding object
		if(itemType.equals("SelfServiceBar"))
		{
			// Sets the menu item ID parameter in the prepared statement for the SelfServiceBar-specific query
			statementFindSelfServiceBarByMenuId.setInt(1, menuItemId);
		       
		    // Executes the query to retrieve additional SelfServiceBar-specific fields
		    ResultSet resultSetSelfServiceBar = statementFindSelfServiceBarByMenuId.executeQuery();

		    // Checks if SelfServiceBar-specific data was found
		    if (resultSetSelfServiceBar.next()) 
		    { 
		           
		    	// Retrieve the enum as an int
		    	int barTypeInt = resultSetSelfServiceBar.getInt("BarType");	
		    	
		    	// Convert the int to an enum constant 
		    	EnumBarType barType = EnumBarType.values()[barTypeInt];
		    	
		    	// Retrieve the price as an double
		        double lunchPrice = resultSetSelfServiceBar.getDouble("lunchPrice");
		        
		        // Retrieve the price as an double
		        double eveningPrice = resultSetSelfServiceBar.getDouble("eveningPrice");

		        // Constructs a new SelfServiceBar object with both shared and specific attributes
		        menuItem = new SelfServiceBar(barType, lunchPrice, eveningPrice);
		     }
		}
		
		//POTATO DISH
		// Check if the item type is "PotatoDish" and build the corresponding object
		else if (itemType.equals("PotatoDish")) 
		{
		        
		  	// Sets the menu item ID parameter in the prepared statement for the PotatoDish-specific query
		   	statementFindPotatoDishByMenuId.setInt(1, menuItemId);
		       
		    // Executes the query to retrieve additional PotatoDish-specific fields
		    ResultSet resultSetPotatoDish = statementFindPotatoDishByMenuId.executeQuery();

		    // Checks if PotatoDish-specific data was found
		    if (resultSetPotatoDish.next()) 
		    { 
		           
		    	// Reads specific attributes for PotatoDish from its result set
		        boolean isPremium = resultSetPotatoDish.getBoolean("isPremium");
		        double fixedPrice = resultSetPotatoDish.getDouble("fixedPrice");

		        // Constructs a new PotatoDish object with both shared and specific attributes
		        menuItem = new PotatoDish(isPremium, fixedPrice);
		     }

		}
		
		    // Returns the fully built MenuItem (or subclass) object
		    return menuItem;
		    
		}
	
	@Override
	public MenuItem findMultipleChoiceMenuByChoiceMenuId(int choiceMenuId) throws DataAccessException
	{
		//TODO:
		return null;	
	}
	 
	private MenuItem buildMultipleChoiceMenuObject(ResultSet resultSet) throws SQLException
	{
		return null;
		//TODO:
	}
	
	@Override
	public MenuItem findSelectionOptionByChoiceMenuId(int choiceMenuId) throws DataAccessException
	{
		//TODO:
		return null;	
	}
	 
	
	private MenuItem buildSelectionOptionObject(ResultSet resultSet) throws SQLException
	{
		return null;
		//TODO:
	}

	@Override
	public MenuItem findAddOnOptionByMenuItemId(int menuItemId) throws DataAccessException
	{
		//TODO:
		return null;	
	}
	 
	private MenuItem buildAddOnOptionObject(ResultSet resultSet) throws SQLException
	{
		return null;
		//TODO:
	}


}
