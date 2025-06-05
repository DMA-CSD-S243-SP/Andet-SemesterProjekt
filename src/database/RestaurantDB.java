package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Restaurant;

/**
 * This class is responsible for accessing and managing restaurant objects stored
 * in a database.
 * 
 * It implements the restaurantImpl interface. 
 * 
 * @author Anders Trankjær & Line Bertelsen
 * @version 02/06/2025 - 14:19
 */
public class RestaurantDB implements RestaurantImpl
{
	// selects a specific row from restaurant table in the database
	private static final String FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY = "SELECT * FROM Restaurant WHERE restaurantCode = ?";
	
	// PreparedStatement for retrieving a restaurant based on the restaurantCode
	private PreparedStatement statementFindByRestaurantCode; 
	
	
	public RestaurantDB() throws SQLException
	{
		statementFindByRestaurantCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY);
	}

	/**
	 * This method will search through the database using the search criteria from the parameterlist
	 * make a clone of using the data found and return that. 
	 * 
	 * @param restaurantCode 		- the Restaurant object containing the restaurant code
	 * @return restaurant 			- that match the restaurantCode 
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public Restaurant findRestaurantByCode(String restaurantCode) throws DataAccessException, SQLException 
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
			databaseConnection.setAutoCommit(false);
			
			// Reading restaurantscodes happens many times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			// Prepares a SQL statement to find and retrieve a restaurant with a matching tableCode
			statementFindByRestaurantCode = databaseConnection.prepareStatement(FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY);
			
			// Adds the tableCode from the methods parameterlist to the String instead of the placeholder
			statementFindByRestaurantCode.setString(1, restaurantCode);
			
			// Executes the query, and stores the retrieved data as a ResultSet
			ResultSet resultSet = statementFindByRestaurantCode.executeQuery();
			
			// Creates and initializes an restaurant object as null
			Restaurant restaurant = null;
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into a restaurant object using the buildrestaurantObject method
				restaurant = buildRestaurantObject(resultSet);
			}
			
			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// Returns an object with with a restaurantCode matching the parameterlist after it was built
			return restaurant;
		}
		
		catch (SQLException exception)
		{
			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs while trying to find restaurant, an exception is thrown with the specified details
			throw new DataAccessException("Unable to find an resetaurant object with a restaurantCode matching: " + restaurantCode, exception);
		}
	}


	/**
     * Builds a specific Restaurant object from a database resultSet.
     * 
     * @param resultSet 			- the result set containing Restaurant data
     * @return restaurant 			- an Restaurant object with the extracted data
	 * @throws SQLException			- if a SQL operation fails
     */
	private Restaurant buildRestaurantObject(ResultSet resultSet) throws SQLException 
	{
		// creates a restaurant object using the resultSets data
		Restaurant restaurant = new Restaurant(
				resultSet.getString("name"),
				resultSet.getString("city"),
				resultSet.getString("streetName"));
		
		return restaurant;
	}
}
