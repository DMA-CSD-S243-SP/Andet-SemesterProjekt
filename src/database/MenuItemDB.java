package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AddOnOption;
import model.DipsAndSauces;
import model.Drink;
import model.EnumBarType;
import model.MainCourse;
import model.MenuItem;
import model.MultipleChoiceMenu;
import model.PotatoDish;
import model.SelectionOption;
import model.SelfServiceBar;
import model.SideDish;


/**
 * This class is responsible for accessing and managing MenuItem objects and 
 * it's subclass objects stored in a database.
 * 
 * It implements the MenuItemImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 11/06/2025 - 17:13
 */
public class MenuItemDB implements MenuItemImpl
{
	//MENU ITEM
	// Selects a row from the table MenuItem in the database, based on the given menuItemID
	private static final String FIND_MENUITEM_BY_MENUITEMID_QUERY = "SELECT * FROM MenuItem WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an menuItem based on the menuItemID
	private PreparedStatement statementFindMenuItemById;
	
	
	//SELF SERVE BAR
	// Selects a row from the table SelfServiceBar in the database, based on the given menuItemID
	private static final String FIND_SELFSERVEBAR_BY_MENUITEMID_QUERY = "SELECT * FROM SelfServiceBar WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an SelfServiceBar based on the menuItemID
	private PreparedStatement statementFindSelfServiceBarByMenuId;
	
	
	//DIPs AND SAUCES
	// Selects a row from the table DipAndSauces in the database, based on the given menuItemID
	private static final String FIND_DIPSANDSAUCES_BY_MENUITEMID_QUERY = "SELECT * FROM DipsAndSauces WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an DipAndSauces based on the menuItemID
	private PreparedStatement statementFindDipsAndSaucesByMenuId;
	
	
	//POTATODISH
	// Selects a row from the table PotatoDish in the database, based on the given menuItemID
	private static final String FIND_POTATODISH_BY_MENUITEMID_QUERY = "SELECT * FROM PotatoDish WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an PotatoDish based on the menuItemID
	private PreparedStatement statementFindPotatoDishByMenuId;
	
	
	//SIDEDISH
	// Selects a row from the table SideDish in the database, based on the given menuItemID
	private static final String FIND_SIDEDISH_BY_MENUITEMID_QUERY = "SELECT * FROM SideDish WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an SideDish based on the menuItemID
	private PreparedStatement statementFindSideDishByMenuId;
	
	
	//DRINK
	// Selects a row from the table Drink in the database, based on the given menuItemID
	private static final String FIND_DRINK_BY_MENUITEMID_QUERY = "SELECT * FROM Drink WHERE menuItemID = ?";
	// PreparedStatement for retrieving an Drink based on the menuItemID
	private PreparedStatement statementFindDrinkByMenuId;
	
	
	//MAIN COURSE
	// Selects a row from the table MainCourse in the database, based on the given menuItemID
	private static final String FIND_MAINCOURSE_BY_MENUITEMID_QUERY = "SELECT * FROM MainCourse WHERE menuItemID = ?";
	
	// PreparedStatement for retrieving an MainCourse based on the menuItemID
	private PreparedStatement statementFindMainCourseMenuId;
	
	
	//MULTIPLE CHOICE MENU
	// Selects a row from the table menuItem in the database, based on the given MenuItemID
	private static final String FIND_MULTIPLECHOICEMENUS_BY_MAINCOURSEID_QUERY = "SELECT * FROM MultipleChoiceMenu WHERE mainCourseId = ?";
		
	// PreparedStatement for retrieving an MultipleChoiceMenu based on the menuItemID
	private PreparedStatement statementMultipleChoiceMenuByMainCourseId;
	
	
	//SELECTION OPTION
	// Selects a row from the table SelectionOption in the database, based on the given choiceMenuId
	// which is linked to a MultipleChoiceMenu associated with the given mainCourseId
	private static final String FIND_SELECTIONOPTIONS_BY_CHOICEMENUID_QUERY = "SELECT * FROM SelectionOption WHERE choiceMenuId = ?";
	
	// PreparedStatement for retrieving an SelectionOption based on the choiceMenuId
	private PreparedStatement statementSelectionOptionChoiceMenuId;
	
	
	//ADD ON OPTION
	// Selects a row from the table AddOnOption in the database, based on the given MainCourseId
	private static final String FIND_ADDONOPTIONS_BY_MAINCOURSEID_QUERY = "SELECT * FROM AddOnOption WHERE mainCourseId = ?";
				
	// PreparedStatement for retrieving an AddOnOption based on the MainCourseId
	private PreparedStatement statementAddOnOptionByMainCourseId;

	
	/**
	 * Constructor for MenuItemDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public MenuItemDB() throws SQLException
	{
		// MENU ITEM - Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindMenuItemById = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MENUITEM_BY_MENUITEMID_QUERY);
		
		// SELF SERVE BAR - Prepares the SQL statement for retrieving a SelfServiceBar by its MenuItemId
		statementFindSelfServiceBarByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELFSERVEBAR_BY_MENUITEMID_QUERY);
		
		// DIP AND SAURCES - Prepares the SQL statement for retrieving a DipsAndSauces by its MenuItemId
		statementFindDipsAndSaucesByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_DIPSANDSAUCES_BY_MENUITEMID_QUERY); 
		
		// POTATO DISH - Prepares the SQL statement for retrieving a PotatoDish by its MenuItemId
		statementFindPotatoDishByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_POTATODISH_BY_MENUITEMID_QUERY);
		
		// SIDE DISH - Prepares the SQL statement for retrieving a SideDish by its MenuItemId
		statementFindSideDishByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SIDEDISH_BY_MENUITEMID_QUERY);
		
		// DRINK - Prepares the SQL statement for retrieving a Drink by its MenuItemId
		statementFindDrinkByMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_DRINK_BY_MENUITEMID_QUERY);
		
		// MAIN COURSE - Prepares the SQL statement for retrieving a MainCourse by its MenuItemId
		statementFindMainCourseMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MAINCOURSE_BY_MENUITEMID_QUERY);
		
		// MULTIPLE CHOICE MENU - Prepares the SQL statement for retrieving a MultipleChoiceMenu by its choiceMenuId
		statementMultipleChoiceMenuByMainCourseId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MULTIPLECHOICEMENUS_BY_MAINCOURSEID_QUERY);
		
		// SELECTION OPTION - Prepares the SQL statement for retrieving a SelcetionOption by its choiceMenuId
		statementSelectionOptionChoiceMenuId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELECTIONOPTIONS_BY_CHOICEMENUID_QUERY);
				
		// ADD ON OPTION - Prepares the SQL statement for retrieving a AddOnOption by its menuItemID
		statementAddOnOptionByMainCourseId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_ADDONOPTIONS_BY_MAINCOURSEID_QUERY);
	}

	
	/**
	 * Finds a MenuItem object by searching for a MenuItem with a matching menuItem id.
	 * 
	 * @param menuItemId 			- the id of the menuItem to search for
	 * @return menuItem 			- the corresponding MenuItem object, or null if not found
 	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public MenuItem findMenuItemByMenuItemId(int menuItemId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading MenuItems happens thousands of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
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
		
		finally
		{
			databaseConnection.setAutoCommit(true);
		}
	}	

	
	/**
     * Builds a specific MenuItem object from a database resultSet.
     * 
     * @param resultSet 		- the result set containing MenuItem data
     * @return menuItem 		- a MenuItem object with the extracted data
     * @throws SQLException 	- if accessing the resultSet fails
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
		
		
		// SELF SERVICE BAR
		// Check if the item type is "SelfServiceBar" and build the corresponding object
		if(itemType.equals("SelfServiceBar"))
		{
			// Sets the menu item ID parameter in the prepared statement for the SelfServiceBar query
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
		        menuItem = new SelfServiceBar(barType, lunchPrice, eveningPrice, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		     }
		}
		
		
		// DIP AND SAUCES
		// Check if the item type "DipAndSauces" and build the corresponding object
		else if(itemType.equals("DipsAndSauces"))
		{
			// Sets the menu item ID parameter in the prepared statement for the DipsAndSauces query
			statementFindDipsAndSaucesByMenuId.setInt(1, menuItemId);
		       
		    // Executes the query to retrieve additional DipsAndSauces fields
		    ResultSet resultSetDipAndSauces = statementFindDipsAndSaucesByMenuId.executeQuery();

		    // Checks if PotatoDish-specific data was found
		    if (resultSetDipAndSauces.next()) 
		    { 
		           
		    	// Reads specific attributes for PotatoDish from its result set
		        boolean isSauce = resultSetDipAndSauces.getBoolean("isSauce");
		        double fixedPrice = resultSetDipAndSauces.getDouble("fixedPrice");

		        // Constructs a new DipsAndSauces object with both shared and specific attributes
		        menuItem = new DipsAndSauces(isSauce, fixedPrice, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		     }
		}
		
		
		//POTATO DISH
		// Check if the item type is "PotatoDish" and build the corresponding object
		else if (itemType.equals("PotatoDish")) 
		{
		  	// Sets the menu item ID parameter in the prepared statement for the PotatoDish query
		   	statementFindPotatoDishByMenuId.setInt(1, menuItemId);
		       
		    // Executes the query to retrieve additional PotatoDish fields
		    ResultSet resultSetPotatoDish = statementFindPotatoDishByMenuId.executeQuery();

		    // Checks if PotatoDish-specific data was found
		    if (resultSetPotatoDish.next()) 
		    {  
		    	// Reads specific attributes for PotatoDish from its result set
		        boolean isPremium = resultSetPotatoDish.getBoolean("isPremium");
		        double fixedPrice = resultSetPotatoDish.getDouble("fixedPrice");

		        // Constructs a new PotatoDish object with both shared and specific attributes
		        menuItem = new PotatoDish(isPremium, fixedPrice, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		     }
		}
		
		
		//SIDEDISH
		// Check if the item type is "SideDish" and build the corresponding object
		else if (itemType.equals("SideDish")) 
		{				        
		  	// Sets the menu item ID parameter in the prepared statement for the SideDish query
		   	statementFindSideDishByMenuId.setInt(1, menuItemId);
				       
		    // Executes the query to retrieve additional SideDish fields
		    ResultSet resultSetSideDish = statementFindSideDishByMenuId.executeQuery();

		    // Checks if SideDish data was found
		    if (resultSetSideDish.next()) 
		    {         
		    	// Reads specific attributes for SideDish from its result set
		        int quantityPerServing = resultSetSideDish.getInt("quantityPerServing");
		        double fixedPrice = resultSetSideDish.getDouble("fixedPrice");

		        // Constructs a new SideDish object with both shared and specific attributes
				menuItem = new SideDish(quantityPerServing, fixedPrice, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		     }
		 }
		
		
		//DRINK
		// Check if the item type is "Drink" and build the corresponding object
		else if (itemType.equals("Drink")) 
		{				        
		  	// Sets the menu item ID parameter in the prepared statement for the Drink query
		   	statementFindDrinkByMenuId.setInt(1, menuItemId);
						       
		    // Executes the query to retrieve additional Drink fields
		    ResultSet resultSetDrink = statementFindDrinkByMenuId.executeQuery();

		    // Checks if Drink data was found
		    if (resultSetDrink.next()) 
		    {         
		    	// Reads specific attributes for Drink from its result set
		        boolean isAlcoholic = resultSetDrink.getBoolean("isAlcoholic");
		        boolean isRefill = resultSetDrink.getBoolean("isRefill");
		        double price = resultSetDrink.getDouble("price");

		        // Constructs a new Drink object with both shared and specific attributes
				menuItem = new Drink(isAlcoholic, isRefill, price, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
			 }
		 }
		
		
		//MAINCOURCE
		// Check if the item type is "Drink" and build the corresponding object
		else if (itemType.equals("MainCourse")) 
		{				        
			// Sets the menu item ID parameter in the prepared statement for the Drink query
			statementFindMainCourseMenuId.setInt(1, menuItemId);

			// Executes the query to retrieve additional Drink fields
			ResultSet resultSetMainCourse = statementFindMainCourseMenuId.executeQuery();

			// Checks if Drink data was found
			if (resultSetMainCourse.next()) 
			{         
				// Reads specific attributes for Drink from its result set
				String introductionDescription = resultSetMainCourse.getString("introductionDescription");
				double lunchPrice = resultSetMainCourse.getDouble("lunchPrice");
				double eveningPrice = resultSetMainCourse.getDouble("eveningPrice");
				
				// Constructs a new Drink object with both shared and specific attributes
				MainCourse mainCourse = new MainCourse(introductionDescription, lunchPrice, eveningPrice, menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
				try
				{
					// Finds the AddOnOption objects associated with the specified menuItemId and stores them within the listOfAddOnOptions
					List<AddOnOption> listOfAddOnOptions = findAddOnOptionsByMainCourseId(menuItemId);
					
					// Iterates through all of the AddOnOption objects within the listOfAddOnOptions
					for (AddOnOption addOnOption: listOfAddOnOptions)
					{
						// Adds the AddOnOption object to the listOfAddOnOptions
						mainCourse.addAddOnOption(addOnOption);
					}
					
					// Finds the MultipleChoiceMenu objects associated with the specified menuItemId and stores them within the listOfMultipleChoiceMenus
					List<MultipleChoiceMenu> listOfMultipleChoiceMenus = findMultipleChoiceMenusByMainCourseId(menuItemId);
					
					// Iterates through all of the multipleChoiceMenu objects within the listOfMultipleChoiceMenus
					for (MultipleChoiceMenu multipleChoiceMenu: listOfMultipleChoiceMenus)
					{
						// Adds the MultipleChoiceMenu object to the listOfMultipleChoiceMenus
						mainCourse.addMultipleChoiceMenu(multipleChoiceMenu);
					}
				}
				
				catch (Exception exception)
				{
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
				
				menuItem = mainCourse;
			}
		}
		
		// Returns the fully build MenuItem (or subclass) object
		return menuItem; 
	}
	

	/**
	 * Finds a MultipleChoiceMenu object by searching for a MultipleChoiceMenu with a matching mainCourseId.
	 * 
     * @param multipleChoiceMenu 	- the ID of the multiple choice menu to be retrieved
     * @return multipleChoiceMenu 	- a list of MultipleChoiceMenu object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public List<MultipleChoiceMenu> findMultipleChoiceMenusByMainCourseId(int mainCourseId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		// Creates an empty list to store MultipleChoiceMenu objects within
		List<MultipleChoiceMenu> listOfMultipleChoiceMenus = new ArrayList<MultipleChoiceMenu>();
		
		try
		{	
			// Prepares a SQL statement to find and retrieve an MultipleChoiceMenu with a matching mainCourseId
			statementMultipleChoiceMenuByMainCourseId = databaseConnection.prepareStatement(FIND_MULTIPLECHOICEMENUS_BY_MAINCOURSEID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementMultipleChoiceMenuByMainCourseId.setInt(1, mainCourseId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementMultipleChoiceMenuByMainCourseId.executeQuery();

			// Iterates through the resultSet while there are still more rows in the database's table
			while (resultSet.next())
			{
				// Converts the retrieved database row into an MultipleChoiceMenu object using the buildMultipleChoiceMenuObject method
				listOfMultipleChoiceMenus.add(buildMultipleChoiceMenuObject(resultSet));
			}
			
			// Returns the MultipleChoiceMenu with a matching choiceMenuId or null if no multipleChoiceMenu has the specified choiceMenuId
			return listOfMultipleChoiceMenus;
		}

		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an MultipleChoiceMenu object with an choiceMenuId matching: " + mainCourseId, exception);
		}
	}
	
	
	/**
     * Builds a specific MutipleChoiceMenu object from a database result set.
     * 
     * @param resultSet 			- the result set containing a list of MultipleChoiceMenu data
	 * @return multipleChoiceMenu	- MutipleChoiceMenu object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
     */
	private MultipleChoiceMenu buildMultipleChoiceMenuObject(ResultSet resultSet) throws SQLException
	{
		// Creates a MutipleChoiceMenu object stored within the mutipleChoiceMenu variable based off of the method's provided resultSet
		MultipleChoiceMenu multipleChoiceMenu = new MultipleChoiceMenu(resultSet.getString("selectionDescription"));		
		
		try
		{
			// Retrieves all SelectionOption objects linked to this MultipleChoiceMenu using its choiceMenuId
			List<SelectionOption> listOfSelectionOptions = findSelectionOptionsByChoiceMenuId(resultSet.getInt("choiceMenuId"));
			
			// Uses a for-each loop to iterate through the listOfSelectionOptions
			for (SelectionOption selectionOption: listOfSelectionOptions)
			{
				// Adds each the current SelectionOption object to the MultipleChoiceMenu object
				multipleChoiceMenu.addSelectionOption(selectionOption);
			}
		}
		
		catch (DataAccessException | SQLException exception)
		{
			// Prints any database-related exceptions to help with debugging
			exception.printStackTrace();
		}
		
		// Returns the built MultipleChoiceMenu object
		return multipleChoiceMenu;
	}
	
	
	/**
	 * Finds a SelectionOption object whose choiceMenuId is linked to a
	 * MultipleChoiceMenu entry that belongs to the specified mainCourseId.
	 *
	 * @param choiceMenuId 			- the ID of the SelectionOptions to be retrieved
     * @return selectionOption 		- a list of SelectionOption object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public List<SelectionOption> findSelectionOptionsByChoiceMenuId(int choiceMenuId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		// Creates an empty list to store SelectionOption objects within
		List<SelectionOption> listOfSelectionOptions = new ArrayList<SelectionOption>();
		
		try
		{
			// Prepares a SQL statement to find and retrieve a SelectionOption with a matching mainCourseId
			statementSelectionOptionChoiceMenuId = databaseConnection.prepareStatement(FIND_SELECTIONOPTIONS_BY_CHOICEMENUID_QUERY);

			// Adds the mainCourseId provided in the method's parameter to the int instead of the placeholder
			statementSelectionOptionChoiceMenuId.setInt(1, choiceMenuId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementSelectionOptionChoiceMenuId.executeQuery();

			// Iterates through the resultSet while there are still more rows in the database's table
			while (resultSet.next())
			{
				// Converts the retrieved database row into an SelectionOption object using the buildSelectionOptionObject method
				listOfSelectionOptions.add(buildSelectionOptionObject(resultSet));
			}
			
			// Returns the listOfSelectionOptions with a matching choiceMenuId
			return listOfSelectionOptions;
		}

		catch (SQLException exception)
		{	
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an MultipleChoiceMenu object with an choiceMenuId matching: " + choiceMenuId, exception);
		}	
	}
	
	
	/**
	 * Builds a specific SelectionOption object from a database resultSet.
	 * 
	 * @param resultSet 		- the result set containing SelectionOption data
	 * @return selectionOption 	- a SelectionOption object
	 * @throws SQLException		- if a SQL operation fails
	 */
	private SelectionOption buildSelectionOptionObject(ResultSet resultSet) throws SQLException
	{
		// Creates a AddOnOption object stored within the SelectionOption variable based off of the method's provided resultSet
		SelectionOption selectionOption = new SelectionOption(resultSet.getString("description"), resultSet.getString("kitchenNotes"), resultSet.getDouble("additionalPrice"));

		return selectionOption;	
	}

	
	/**
	 * Finds all AddOnOptions associated with a given mainCourseId.
	 * 
     * @param addOnOption 			- the ID of the menu item to be retrieved
     * @return addOnOption 			- a list of AddOnOption object that matches the provided ID
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public List<AddOnOption> findAddOnOptionsByMainCourseId(int mainCourseId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		// Creates an empty list to store AddOnOption objects within
		List<AddOnOption> listOfAddOnOptions = new ArrayList<>();

		try
		{	
			// Prepares a SQL statement to find and retrieve an AddOnOptions with a matching mainCourseID id
			statementAddOnOptionByMainCourseId = databaseConnection.prepareStatement(FIND_ADDONOPTIONS_BY_MAINCOURSEID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementAddOnOptionByMainCourseId.setInt(1, mainCourseId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementAddOnOptionByMainCourseId.executeQuery();

			// Iterates through the resultSet while there are still more rows in the database's table
			while (resultSet.next())
			{
				// Converts the retrieved database row into an AddOnOption object using the buildAddOnOptionObject method
				listOfAddOnOptions.add(buildAddOnOptionObject(resultSet));
			}

			// Returns the listOfAddOnOptions with a matching choiceMenuId
			return listOfAddOnOptions;
		}

		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an AddOnOption object with an menuItemId matching: " + mainCourseId, exception);
		}
	}
	
	
	/**
     * Builds a specific AddOnOption object from a database result set.
     * 
     * @param resultSet 	- the result set containing AddOnOption data
     * @return addOnOption 	- an AddOnOption object with the extracted data
     * @throws SQLException - if accessing the result set fails
     */
	private AddOnOption buildAddOnOptionObject(ResultSet resultSet) throws SQLException
	{
		// Creates a AddOnOption object stored within the addOnOption variable based off of the method's provided resultSet
		AddOnOption addOnOption = new AddOnOption(resultSet.getString("description"), resultSet.getString("kitchenNotes"), resultSet.getDouble("additionalPrice"));

		return addOnOption;	
	}
}
