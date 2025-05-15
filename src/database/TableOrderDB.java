package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AddOnOption;
import model.TableOrder;

/**
 * This class is responsible for accessing and managing 
 * TabelOrder objects stored in a database.
 * 
 * It implements the TableOrderImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen
 * @version 15.05.2025 - 10:08
 */

public class TableOrderDB implements TableOrderImpl
{

	// Selects all the data within tableOrder table in the database
	private static final String FIND_AllTABLEORDERS_QUERY = "SELECT tableOrderId FROM TableOrder";
	
	// PreparedStatement for retrieving all tableOrder from the database
	private PreparedStatement statementFindAllTableOrders; 
	
	
	// Selects a row from the table menuItem in the database, based on the given tableOrderId
	private static final String FIND_TABLEORDER_BY_TABLEORDERID_QUERY = "SELECT * FROM TableOrder WHERE TableOrderId = ?";
	
	// PreparedStatement for retrieving an TableOrder based on the tableOrderId
	private PreparedStatement statementFindTableOrderByTableOrderId;
	
	
	public TableOrderDB() throws SQLException
	{
		//Prepares the SQL statement for retrieving a MenuItem by its MenuItemId
		statementFindAllTableOrders = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_AllTABLEORDERS_QUERY);

		//Prepares the SQL statement for retrieving a SelfServeBar by its MenuItemId
		statementFindTableOrderByTableOrderId = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_TABLEORDER_BY_TABLEORDERID_QUERY);
				
	}
	
	
	/**
     * Retrieves all tableOrder from the database.
     * 
     * @return a list of all tableOrders
     * @throws DataAccessException if retrieval fails
	 * @throws SQLException 
     */
	@Override
	public List<TableOrder> findAllTableOrders() throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading tableOrders happens many times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			// Prepare a SQL statement to retrieve all tableOrders
			statementFindAllTableOrders = databaseConnection.prepareStatement(FIND_AllTABLEORDERS_QUERY);
			
			// Executes the prepared statement and stores the result set
			ResultSet resultSetTableOrder = statementFindAllTableOrders.executeQuery();

			// Converts the result set into a list of TableOrder objects
			List<TableOrder> resultListOfTableOrder = buildTableOrderObjects(resultSetTableOrder);

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the list of TableOrder objects
			return resultListOfTableOrder;
		}

		catch (SQLException exception1)
		{
			
			//Try catch for rollback
			try
			{
				databaseConnection.rollback();
			} 
			
			catch (Exception exception2)
			{
				throw new DataAccessException("", exception2);
			}
			
			
			//Try catch for setAutoCommit
			try
			{
				databaseConnection.setAutoCommit(true);
			} 
			
			catch (Exception exception3)
			{
				throw new DataAccessException("", exception3);
			}
			
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find TableOrder objects in the database", exception1);
		}
	}
	
	
	/**
     * Converts a result set into a list of TableOrder objects.
     * 
     * @param resultSet the result set containing multiple TableOrder records
     * @return a list of TableOrder objects
     * @throws SQLException if accessing the result set fails
     */
	private List<TableOrder> buildTableOrderObjects(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store Employee objects within
		List<TableOrder> tableOrder = new ArrayList<>();

		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a Employee object and add it to the list
			tableOrder.add(buildTableOrderObject(resultSet));
		}

		// Returns the populated list of Employee objects
		return tableOrder;
		
	}

	
	/**
     * Builds a specific TableOrder object from a database resultSet.
     * 
     * @param resultSet the result set containing TableOrder data
     * @return an TableOrder object with the extracted data
     * @throws SQLException if accessing the resultSet fails
     */
	@Override
	public TableOrder findTableOrderByTableOrderId(int tableOrderId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
		
			// Reading tabelOrder happens many of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
						
			// Prepares a SQL statement to find and retrieve an MultipleChoiceMenu with a matching employee id
			statementFindTableOrderByTableOrderId = databaseConnection.prepareStatement(FIND_TABLEORDER_BY_TABLEORDERID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementFindTableOrderByTableOrderId.setInt(1, tableOrderId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindTableOrderByTableOrderId.executeQuery();

			// Creates and initializes an MultipleChoiceMenu object as null, which will later be populated with Employee specific data
			TableOrder tableOrder = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an MultipleChoiceMenu object using the buildMultipleChoiceMenuObject method
				tableOrder = buildTableOrderObject(resultSet);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns the MultipleChoiceMenu with a matching choiceMenuId or null if no multipleChoiceMenu has the specified choiceMenuId
			return tableOrder;
		}

		catch (SQLException exception1)
		{
			
			//Try catch for rollback
			try
			{
				databaseConnection.rollback();
			} 
			
			catch (Exception exception2)
			{
				throw new DataAccessException("", exception2);
			}
			
			
			//Try catch for setAutoCommit
			try
			{
				databaseConnection.setAutoCommit(true);
			} 
			
			catch (Exception exception3)
			{
				throw new DataAccessException("", exception3);
			}
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an TableOrder object with an tableOrderId matching: " + tableOrderId, exception1);
		}
	}
	
	
	/**
     * Builds a specific TableOrder object from a database result set.
     * 
     * @param resultSet the result set containing TableOrder data
     * @return an TableOrder object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private TableOrder buildTableOrderObject(ResultSet resultSet) throws SQLException
	{
		// Creates a AddOnOption object stored within the addOnOption variable based off of the method's provided resultSet
		TableOrder tableOrder = new TableOrder(
				resultSet.getInt("tableOrderId")
				);		

		return tableOrder;	
	}
}
