package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AvailabilityTracker;
import model.MenuCard;
import model.MenuItem;

/**
 * This class is responsible for accessing and managing MenuCards and 
 * AvailabilityTracker objects stored in a database.
 * 
 * It implements the MenuCardImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen & Lumière Schack
 * @version 02/06/2025 - 16.02
 */

public class MenuCardDB implements MenuCardImpl
{
	// Selects a row from the table MenuCard in the database, based on the given restaurantCode
	private static final String FIND_MENUCARDS_BY_RESTAURANTCODE_QUERY = "SELECT * FROM MenuCard WHERE restaurantCode = ?";
	
	// PreparedStatement for retrieving MenuCards based on the restaurantCode
	private PreparedStatement statementFindByRestaurantCode;
	
	
	// Selects a row from the table AvailabilityTracker in the database, based on the given MenuCardId
	private static final String FIND_AVAILABILITYTRACKERS_BY_MENUCARDID_QUERY = "SELECT * FROM AvailabilityTracker WHERE menuCardId = ?";

	// PreparedStatement for retrieving an menuItem based on the menuItemID
	private PreparedStatement statementFindTrackerByMenuCardId;
	
	
	//Constructor
	public MenuCardDB() throws SQLException
	{
		// MENU ITEM - Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindByRestaurantCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_MENUCARDS_BY_RESTAURANTCODE_QUERY);

		// SELF SERVE BAR - Prepares the SQL statement for retrieving a SelfServeBar by its MenuItemId
		statementFindTrackerByMenuCardId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_AVAILABILITYTRACKERS_BY_MENUCARDID_QUERY);	
	}
	
	
	/**
	 * Its being used by UtilityGuestInformation.
	 * After you have entered your discount a list of menuCard is returned 
	 * 
	 * Retrieves all MenuCards from the database.
	 * 
	 * @param restaurantCode 			- the code of the MenuCard to search for
	 * @return availableMenuCards 		- the corresponding MenuCard object, or null if not found
	 * @throws DataAccessException 		- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException				- if a SQL operation fails
	 */
	@Override
	public List<MenuCard> findMenuCardsByRestaurantCode(String restaurantCode) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
			databaseConnection.setAutoCommit(false);
			
			// Reading MenuCards happens thousands of times per day. However it occurs almost exclusively during
			// business hours, and updating happens rarely, and can usually be scheduled, which is why we use read uncommitted.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			// Prepare a SQL statement to retrieve all menucards
			statementFindByRestaurantCode = databaseConnection.prepareStatement(FIND_MENUCARDS_BY_RESTAURANTCODE_QUERY);
			statementFindByRestaurantCode.setString(1, restaurantCode);

			// Executes the prepared statement and stores the result set
			ResultSet resultSet = statementFindByRestaurantCode.executeQuery();

			// Converts the result set into a list of MenuCard objects
			List<MenuCard> availableMenuCards = buildMenuCardObjects(resultSet);

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// Returns the list of MenuCard objects
			return availableMenuCards;
		}
		
		catch (SQLException exception)
		{
			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs an exception is thrown with the specified details
			throw new DataAccessException("Unable to find MenuCards objects in the database with a matching restaurant code" + restaurantCode, exception);
		}
	}

	
	/**
     * Builds a specific MenuCard object from a database result set.
     * 
     * @param resultSet 		- the result set containing MenuCard data
     * @return menuCard 		- an MenuCard object with the extracted data
	 * @throws SQLException		- if a SQL operation fails
     */
	private MenuCard buildMenuCardObject(ResultSet resultSet) throws SQLException
	{
		// Creates a new MenuCard object using the 'name' column from the result set
		MenuCard menuCard = new MenuCard(resultSet.getString("name"));
		
		// Extracts the menuCardId from the result set to be used in a lookup for availability trackers
		int menuCardId = resultSet.getInt("menuCardId");
		
		try
		{
			// Retrieves all AvailabilityTracker objects linked to the MenuCard by its ID
			List<AvailabilityTracker> availabilityTrackers = findAvailabilityTrackersByMenuCardId(menuCardId);
			
			// Adds each retrieved AvailabilityTracker to the MenuCard
			for (AvailabilityTracker availabilityTracker: availabilityTrackers)
			{
				menuCard.addAvailabilityTracker(availabilityTracker);
			}
		}
		
		catch (DataAccessException exception)
		{
			// If an SQL error occurs an exception is thrown with the specified details
			throw new SQLException("Unable to find trackers with given menuCardId", exception);
		}
		
		// Returns the complete MenuCard object including its availability data
		return menuCard;
	}
	
	
	/**
     * Converts a result set into a list of MenuCard objects.
     * 
     * @param resultSet 		- the result set containing multiple MenuCards records
     * @return menuCards 		- a list of MenuCards objects
     * @throws SQLException 	- if accessing the result set fails
     */
	private List<MenuCard> buildMenuCardObjects(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store MenuCard objects within
		List<MenuCard> menuCards = new ArrayList<>();
		
		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a MenuCard object and add it to the list of MenuCard objects
			// by using the buildMenuCardObject method
			menuCards.add(buildMenuCardObject(resultSet));
		}
		
		// Returns the populated list of MenuCard objects
		return menuCards;
	}
		
	
	/**
	 * Finds a AvailabilityTracker object by searching for a AvailabilityTracker with a matching menuCardId id.
	 * 
	 * @param menuCardId 				- the id of the menuItem to search for
	 * @return availabilityTrackers		- the AvailabilityTrackers object that matches the provided id
	 * @throws DataAccessException 		- if an error occurs during data access, such as rollback or connection issues
	 */
	@Override
	public List<AvailabilityTracker> findAvailabilityTrackersByMenuCardId(int menuCardId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			// Prepares a SQL statement to find and retrieve an AvailabilityTracker with a matching menuCardId
			statementFindTrackerByMenuCardId = databaseConnection.prepareStatement(FIND_AVAILABILITYTRACKERS_BY_MENUCARDID_QUERY);

			// Adds the menuCardId provided in the method's parameter to the String instead of the placeholder
			statementFindTrackerByMenuCardId.setInt(1, menuCardId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindTrackerByMenuCardId.executeQuery();

			// Creates and initializes an AvailabilityTracker object as null, which will later be populated with AvailabilityTracker specific data
			List<AvailabilityTracker> availabilityTrackers = new ArrayList<>();

			// Iterates through the resultSet while there are still more rows in the database's table
			while (resultSet.next())
			{
				// Converts the retrieved database row into an AvailabilityTracker object using the buildAvailabilityTrackerObject method
				availabilityTrackers.add(buildAvailabilityTrackerObject(resultSet));
			}

			// Returns the availabilityTracker with a matching menuCardId or null if no AvailabilityTracker has the specified menuCardId
			return availabilityTrackers;
		}

		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an AvailabilityTracker object with an choiceMenuId matching Id: " + menuCardId, exception);
		}	
	}
	
	/**
     * Builds a specific AvailabilityTracker object from a database resultSet.
     * 
     * @param resultSet 			- the result set containing AvailabilityTracker data
     * @return availabilityTracker 	- a AvailabilityTracker object with the extracted data
	 * @throws SQLException			- if a SQL operation fails
      */
	private AvailabilityTracker buildAvailabilityTrackerObject(ResultSet resultSet) throws SQLException
	{
		// Creates a new AvailabilityTracker object using the 'isAvailable' boolean from the result set
		AvailabilityTracker availabilityTracker = new AvailabilityTracker(resultSet.getBoolean("isAvailable"));
		
		// Placeholder for the associated MenuItem object
		MenuItem menuItem = null;
		
		// Retrieves the menuItemId from the result set, which is used to look up the full MenuItem object
		int menuItemId = resultSet.getInt("menuItemId");
		
		try
		{
			// Uses MenuItemDB to retrieve the full MenuItem object by its ID
			menuItem = new MenuItemDB().findMenuItemByMenuItemId(menuItemId);
		}
		
		catch (DataAccessException exception)
		{
			// If a SQL operation fails, print the stack trace
			exception.printStackTrace();
		}
		
		// Links the found MenuItem to the AvailabilityTracker
		availabilityTracker.setMenuItem(menuItem);
		
		// Returns the fully constructed AvailabilityTracker object
		return availabilityTracker;
	}
}
