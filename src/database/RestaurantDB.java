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
 * @author Anders Trankjær
 * @version 2025/12/05/12:50
 */
public class RestaurantDB implements RestaurantImpl
{
//  selects a specific row from table_object
	private static final String FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY = "SELECT * FROM Restaurant WHERE restaurantCode = ?";
		
	private PreparedStatement statementFindByTableCode; 
	
	public RestaurantDB() throws SQLException
	{
		statementFindByTableCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY);
	}

	@Override
	public Restaurant findRestaurantByCode(String restaurantCode) throws DataAccessException {
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			// Prepares a SQL statement to find and retrieve a restaurant with a matching tableCode
			statementFindByTableCode = databaseConnection.prepareStatement(FIND_RESTAURANT_BY_RESTAURANTCODE_QUERY);
			
			// Adds the tableCode from the methods parameterlist to the String instead of the placeholder
			statementFindByTableCode.setString(1, restaurantCode);
			
			// Executes the query, and stores the retrieved data as a ResultSet
			ResultSet resultSet = statementFindByTableCode.executeQuery();
			
			// Creates and initializes an restaurant object as null
			Restaurant restaurant = null;
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into a restaurant object using the buildrestaurantObject method
				restaurant = buildRestaurantObject(resultSet);
			}
			
			// Returns an object with with a restaurantCode matching the parameterlist after it was built
			return restaurant;
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an resetaurant object with a restaurantCode matching: " + restaurantCode, exception);
		}
	}

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
