package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * This class is responsible for accessing and managing employee objects stored
 * in a database.
 * 
 * It implements the employeeDaoImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen
 * @version 15.05.2025 - 10:24
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
	private static final String FIND_MULTIPLECHOICEMENU_BY_MAINCOURSEID_QUERY = "SELECT * FROM MultipleChoiceMenu WHERE mainCourseId = ?";
		
	// PreparedStatement for retrieving an MultipleChoiceMenu based on the menuItemID
	private PreparedStatement statementMultipleChoiceMenuByMainCourseId;
	
	
	//SELECTION OPTION
	// Selects a row from the table SelectionOption in the database, based on the given choiceMenuId
	// which is linked to a MultipleChoiceMenu associated with the given mainCourseId
	private static final String FIND_SELECTIONOPTION_BY_MAINCOURSEID_QUERY = "SELECT * FROM SelectionOption WHERE choiceMenuId IN (SELECT choiceMenuId FROM MultipleChoiceMenu WHERE mainCourseId = ?)";
	
	// PreparedStatement for retrieving an SelectionOption based on the choiceMenuId
	private PreparedStatement statementSelectionOptionByMainCourseId;
	
	
	//ADD ON OPTION
	// Selects a row from the table AddOnOption in the database, based on the given MainCourseId
	private static final String FIND_ADDONOPTION_BY_MAINCOURSEID_QUERY = "SELECT * FROM AddOnOption WHERE mainCourseId = ?";
				
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
		statementMultipleChoiceMenuByMainCourseId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MULTIPLECHOICEMENU_BY_MAINCOURSEID_QUERY);
		
		// SELECTION OPTION - Prepares the SQL statement for retrieving a SelcetionOption by its choiceMenuId
		statementSelectionOptionByMainCourseId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_SELECTIONOPTION_BY_MAINCOURSEID_QUERY);
				
		// ADD ON OPTION - Prepares the SQL statement for retrieving a AddOnOption by its menuItemID
		statementAddOnOptionByMainCourseId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_ADDONOPTION_BY_MAINCOURSEID_QUERY);
	}

	
	/**
	 * Finds a MenuItem object by searching for a MenuItem with a matching menuItem id.
	 * 
	 * @param menuItemId the id of the menuItem to search for
	 * @return the corresponding MenuItem object, or null if not found
	 * @throws DataAccessException if retrieval fails
	 * @throws SQLException 
	 */
	@Override
	public MenuItem findMenuItemByMenuItemId(int menuItemId) throws DataAccessException, SQLException
	{
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
			
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the menuItem with a matching menuItem id or null if no menuItem has the specified menuItem id
			return menuItem;
		} 
		
		catch (SQLException exception)
		{
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
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
		        menuItem = new SelfServiceBar(barType, lunchPrice, eveningPrice);
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
		        menuItem = new DipsAndSauces(isSauce, fixedPrice);
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
		        menuItem = new PotatoDish(isPremium, fixedPrice);
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
				menuItem = new SideDish(quantityPerServing, fixedPrice);
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
				menuItem = new Drink(isAlcoholic, isRefill, price);
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
				MainCourse mainCourse = new MainCourse(introductionDescription, lunchPrice, eveningPrice);
				try
				{
					mainCourse.addAddOnOption(findAddOnOptionByMainCourseId(menuItemId));
					mainCourse.addMultipleChoiceMenu(findMultipleChoiceMenuByMainCourseId(menuItemId));
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				menuItem = mainCourse;
			}
		}
		if (menuItem != null)
		{
			menuItem.setDescription(description);
			menuItem.setMadeByKitchenStaff(isMadeByKitchenStaff);
			menuItem.setMenuItemId(menuItemId);
			menuItem.setName(name);
			menuItem.setPreparationTime(preparationTime);
		}
		// Returns the fully build MenuItem (or subclass) object
		return menuItem; 
		}
	
	

	/**
	 * Finds a MultipleChoiceMenu object by searching for a MultipleChoiceMenu with a matching mainCourseId.
	 * 
	 * @param mainCourseId the id of the MultipleChoiceMenu to search for
	 * @return the corresponding MultipleChoiceMenu object, or null if not found
	 * @throws DataAccessException if retrieval fails
	 * @throws SQLException 
	 */
	@Override
	public MultipleChoiceMenu findMultipleChoiceMenuByMainCourseId(int mainCourseId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading MultipleChoiceMenu happens thousands of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			// Prepares a SQL statement to find and retrieve an MultipleChoiceMenu with a matching employee id
			statementMultipleChoiceMenuByMainCourseId = databaseConnection.prepareStatement(FIND_MULTIPLECHOICEMENU_BY_MAINCOURSEID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementMultipleChoiceMenuByMainCourseId.setInt(1, mainCourseId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementMultipleChoiceMenuByMainCourseId.executeQuery();

			// Creates and initializes an MultipleChoiceMenu object as null, which will later be populated with Employee specific data
			MultipleChoiceMenu multipleChoiceMenu = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an MultipleChoiceMenu object using the buildMultipleChoiceMenuObject method
				multipleChoiceMenu = buildMultipleChoiceMenuObject(resultSet);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the MultipleChoiceMenu with a matching choiceMenuId or null if no multipleChoiceMenu has the specified choiceMenuId
			return multipleChoiceMenu;
		}

		catch (SQLException exception)
		{
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an MultipleChoiceMenu object with an choiceMenuId matching: " + mainCourseId, exception);
		}

	}
	
	
	/**
     * Builds a specific MutipleChoiceMenu object from a database result set.
     * 
     * @param resultSet the result set containing MutipleChoiceMenu data
     * @return an MutipleChoiceMenu object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private MultipleChoiceMenu buildMultipleChoiceMenuObject(ResultSet resultSet) throws SQLException
	{
		// Creates a MutipleChoiceMenu object stored within the mutipleChoiceMenu variable based off of the method's provided resultSet
		MultipleChoiceMenu multipleChoiceMenu = new MultipleChoiceMenu(
			resultSet.getString("selectionDescription")			
			);		
		try
		{
			multipleChoiceMenu.addSelectionOption(findSelectionOptionByMainCourseId(1));
		} catch (DataAccessException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return multipleChoiceMenu;

	}
	
	/**
	 * Finds a SelectionOption object whose choiceMenuId is linked to a
	 * MultipleChoiceMenu entry that belongs to the specified mainCourseId.
	 *
	 * @param mainCourseId the ID of the main course to find related selection options for
	 * @return the corresponding SelectionOption object, or null if not found
	 * @throws DataAccessException if a database access error occurs
	 * @throws SQLException 

	 */
	@Override
	public SelectionOption findSelectionOptionByMainCourseId(int mainCourseId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading selectionsOptions happens thousands of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			// Prepares a SQL statement to find and retrieve an SelectionOption with a matching mainCourseId
			statementSelectionOptionByMainCourseId = databaseConnection.prepareStatement(FIND_SELECTIONOPTION_BY_MAINCOURSEID_QUERY);

			// Adds the mainCourseId provided in the method's parameter to the String instead of the placeholder
			statementSelectionOptionByMainCourseId.setInt(1, mainCourseId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementSelectionOptionByMainCourseId.executeQuery();

			// Creates and initializes an SelectionOption object as null, which will later be populated with SelectionOption specific data
			SelectionOption selectionOption = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an SelectionOption object using the buildSelectionOptionObject method
				selectionOption = buildSelectionOptionObject(resultSet);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the SelectionOption with a matching mainCourseId or null if no SelectionOption has the specified mainCourseId
			return selectionOption;
		}

		catch (SQLException exception)
		{
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an MultipleChoiceMenu object with an choiceMenuId matching: " + mainCourseId, exception);
		}	
	}
	 
	
	private SelectionOption buildSelectionOptionObject(ResultSet resultSet) throws SQLException
	{
		// Creates a AddOnOption object stored within the SelectionOption variable based off of the method's provided resultSet
		SelectionOption selectionOption = new SelectionOption(
				resultSet.getString("description"),
				resultSet.getString("kitchenNotes"),
				resultSet.getDouble("additionalPrice")
				);		

		return selectionOption;	
	}

	
	/**
     * Builds a specific AddOnOption object from a database resultSet.
     * 
     * @param resultSet the result set containing AddOnOption data
     * @return an AddOnOption object with the extracted data
     * @throws SQLException if accessing the resultSet fails
     */
	@Override
	public AddOnOption findAddOnOptionByMainCourseId(int mainCourseId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading addOnOptions happens thousands of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			// Prepares a SQL statement to find and retrieve an MultipleChoiceMenu with a matching employee id
			statementAddOnOptionByMainCourseId = databaseConnection.prepareStatement(FIND_ADDONOPTION_BY_MAINCOURSEID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementAddOnOptionByMainCourseId.setInt(1, mainCourseId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementAddOnOptionByMainCourseId.executeQuery();

			// Creates and initializes an MultipleChoiceMenu object as null, which will later be populated with Employee specific data
			AddOnOption addOnOption = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an MultipleChoiceMenu object using the buildMultipleChoiceMenuObject method
				addOnOption = buildAddOnOptionObject(resultSet);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the MultipleChoiceMenu with a matching choiceMenuId or null if no multipleChoiceMenu has the specified choiceMenuId
			return addOnOption;
		}

		catch (SQLException exception)
		{
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an AddOnOption object with an menuItemId matching: " + mainCourseId, exception);
		}
	}
	 
	/**
     * Builds a specific AddOnOption object from a database result set.
     * 
     * @param resultSet the result set containing AddOnOption data
     * @return an AddOnOption object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private AddOnOption buildAddOnOptionObject(ResultSet resultSet) throws SQLException
	{
		// Creates a AddOnOption object stored within the addOnOption variable based off of the method's provided resultSet
		AddOnOption addOnOption = new AddOnOption(
				resultSet.getString("description"),
				resultSet.getString("kitchenNotes"),
				resultSet.getDouble("additionalPrice")
				);		

		return addOnOption;	
	}
}
