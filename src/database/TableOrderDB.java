package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.PersonalOrder;
import model.TableOrder;

/**
 * This class is responsible for accessing and managing 
 * TabelOrder objects stored in a database.
 * 
 * It implements the TableOrderImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen
 * @version 02/06/2025 - 13:54
 */
public class TableOrderDB implements TableOrderImpl
{
	// Selects all the data within tableOrder table in the database
	private static final String FIND_AllTABLEORDERS_QUERY = "SELECT tableOrderId FROM TableOrder";
	
	// PreparedStatement for retrieving all tableOrder from the database
	private PreparedStatement statementFindAllTableOrders; 
	
	
	// Selects a row from the table menuItem in the database, based on the given tableOrderId
	private static final String FIND_TABLEORDER_BY_TABLEORDERID_QUERY = "SELECT * FROM TableOrder WHERE tableOrderId = ?";
	
	// PreparedStatement for retrieving a TableOrder based on the tableOrderId
	private PreparedStatement statementFindTableOrderByTableOrderId;
	
	
	// Selects a row from the table menuItem in the database, based on the given tableOrderId
	private static final String UPDATE_TABLEORDER_QUERY = "UPDATE TableOrder SET timeOfArrival = ?, isTableOrderClosed = ?, paymentType = ?, totalTableOrderPrice = ?, totalAmountPaid = ?, isSentToKitchen = ?, isRequestingService = ?, orderPreparationTime = ? WHERE tableOrderId = ?";
	
	// PreparedStatement for update a TableOrder
	private PreparedStatement statementUpdateTableOrder;
	
	
	// Selects every row from the TableOrder where isSentToKitchen = true and isTableOrderClsoed = false, in the database
	private static final String FIND_VISIBLE_TO_KITCHEN_TABLE_ORDERS_QUERY =  "SELECT * FROM TableOrder WHERE isSentToKitchen = 1 AND isTableOrderClosed = 0";
			
	// PreparedStatement for retrieving a TableOrder based on the tableOrderId
	private PreparedStatement statementFindVisibleToKitchenTableOrders;
	
	
	public TableOrderDB() throws SQLException
	{
		
	}
	
	
	/**
     * Retrieves all tableOrder from the database.
     * 
     * @return resultListOfTableOrdera 	- list of all tableOrders
	 * @throws DataAccessException 		- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException				- if a SQL operation fails
     */
	@Override
	public List<TableOrder> findAllTableOrders() throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
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

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// Returns the list of TableOrder objects
			return resultListOfTableOrder;
		}

		catch (SQLException exception)
		{
			
			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs an exception is thrown with the specified details
			throw new DataAccessException("Unable to find TableOrder objects in the database", exception);
		}
	}
	
	
	/**
     * Converts a result set into a list of TableOrder objects.
     * 
     * @param resultSet 			- the result set containing multiple TableOrder records
     * @return tableOrders  		- a list of TableOrder objects
	 * @throws SQLException			- if a SQL operation fails
     */
	private List<TableOrder> buildTableOrderObjects(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store TableOrder objects within
		List<TableOrder> tableOrder = new ArrayList<>();

		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a TableOrder object and add it to the list
			tableOrder.add(buildTableOrderObject(resultSet));
		}

		// Returns the populated list of TableOrder objects
		return tableOrder;
		
	}

	
	/**
     * Builds a specific TableOrder object from a database resultSet.
     * 
     * @param resultSet 			- the result set containing TableOrder data
     * @return tableOrder 			- an TableOrder object with the extracted data
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
     */
	@Override
	public TableOrder findTableOrderByTableOrderId(int tableOrderId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
		
			// Reading tabelOrder happens many of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
						
			// Prepares a SQL statement to find and retrieve an MultipleChoiceMenu with a matching TableOrder id
			statementFindTableOrderByTableOrderId = databaseConnection.prepareStatement(FIND_TABLEORDER_BY_TABLEORDERID_QUERY);

			// Adds the choiceMenuId provided in the method's parameter to the String instead of the placeholder
			statementFindTableOrderByTableOrderId.setInt(1, tableOrderId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindTableOrderByTableOrderId.executeQuery();

			// Creates and initializes a MultipleChoiceMenu object as null, which will later be populated with TableOrderspecific data
			TableOrder tableOrder = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an MultipleChoiceMenu object using the buildMultipleChoiceMenuObject method
				tableOrder = buildTableOrderObject(resultSet);
			}

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// Returns the MultipleChoiceMenu with a matching choiceMenuId or null if no multipleChoiceMenu has the specified choiceMenuId
			return tableOrder;
		}

		catch (SQLException exception)
		{
			
			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs while trying to find tableOrder, an exception is thrown with the specified details
			throw new DataAccessException("Unable to find an TableOrder object with an tableOrderId matching: " + tableOrderId, exception);
		}
	}
	
	
	/**
     * Builds a specific TableOrder object from a database result set.
     * 
     * @param resultSet  			- the result set containing TableOrder data
     * @return tableOrder 			- an TableOrder object with the extracted data
	 * @throws SQLException			- if a SQL operation fails
     */
	private TableOrder buildTableOrderObject(ResultSet resultSet) throws SQLException
	{
		// Retrieves the timeOfArrival from the current row in the result set 
		// and combines it into Timestamp object
		Timestamp timeOfArrivalTimeStamp = resultSet.getTimestamp("timeOfArrival");

		// Creates an instance of LocalDateTime and sets it to null, this will later hold a converted date-time value
		LocalDateTime timeOfArrivalLocalDate = null;

		// If the timestamp that is retrieved from the database is not null then execute this section
		if (timeOfArrivalTimeStamp != null)
		{
		    // Converts the SQL Timestamp to a LocalDateTime and stores it within the timeOfArrivalLocalDate variable
		    timeOfArrivalLocalDate = timeOfArrivalTimeStamp.toLocalDateTime();
		}
		
		// Creates a TableOrder object with the data that was retrieved from the database
		TableOrder tableOrder = new TableOrder(resultSet.getInt("tableOrderId"), timeOfArrivalLocalDate, resultSet.getBoolean("isTableOrderClosed"),
				resultSet.getString("paymentType"), resultSet.getDouble("totalTableOrderPrice"), resultSet.getDouble("totalAmountPaid"),
				resultSet.getBoolean("isSentToKitchen"), resultSet.getBoolean("isRequestingService"), resultSet.getInt("orderPreparationTime"));
		
		return tableOrder;
	}
	

	/**
	 * Method updates a given TableOrder in data storage. Doesn't add associations, just updates the specific TableOrderRow.
	 * Rolls back the transaction and restores auto-commit if an error occurs.
	 * 
	 * @param tableOrder			- the TableOrder object containing the updated data to persist. 
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 */
	@Override
	public void updateTableOrder(TableOrder tableOrder) throws DataAccessException 
	{
	    
	    // Gets a connection to the database
	    Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

	    try 
	    {
	    	// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
	    	// When turned off multiple SQL statements is grouped into one transaction.
	    	databaseConnection.setAutoCommit(false);

	        // Set transaction isolation level
	        databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

	        //Prepares the SQL statement for updating TableOrder for the matching tableOrderId
	        statementUpdateTableOrder = databaseConnection.prepareStatement(UPDATE_TABLEORDER_QUERY);

	        // Set values in the prepared statement
	        statementUpdateTableOrder.setTimestamp(1, java.sql.Timestamp.valueOf(tableOrder.getTimeOfArrival()));
	        statementUpdateTableOrder.setBoolean(2, tableOrder.isTableOrderClosed());
	        statementUpdateTableOrder.setString(3, tableOrder.getPaymentType());
	        statementUpdateTableOrder.setDouble(4, tableOrder.calculateTotalTableOrderPrice());
	        statementUpdateTableOrder.setDouble(5, tableOrder.getTotalAmountPaid());
	        statementUpdateTableOrder.setBoolean(6, tableOrder.isSentToKitchen());
	        statementUpdateTableOrder.setBoolean(7, tableOrder.isRequestingService());
	        statementUpdateTableOrder.setInt(8, tableOrder.getOrderPreparationTime());
	        statementUpdateTableOrder.setInt(9, tableOrder.getTableOrderId()); 

	        // Execute update
	        statementUpdateTableOrder.executeUpdate();

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);

	    } 
	    catch (SQLException exception) 
	    {
	        try 
	        {
				//Undo all changes made so far in the transaction
				databaseConnection.rollback();
				
				//Restores the default behavior and turns on auto-commit
				databaseConnection.setAutoCommit(true);
	        } 
	        
	        catch (SQLException rollbackException) 
	        {
	        	// If rollback fails, throw a custom exception with details
	        	throw new DataAccessException("Rollback failed after updateTableOrder error", rollbackException);
	        }

	        // If an SQL error occurs while updating the tableOrder an exception is thrown with the specified details
	        throw new DataAccessException("Failed to update TableOrder in database", exception);
	    }
	}


	/**
	 * Method gets all the TableOrders that the kitchen should be able to see.
	 * 
	 * @return tableOrder 			- A List containing every TableOrder where isSentToKitchen is set to
	 *         				  		  true, and isTableOrderClosed is set to false
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public List<TableOrder> findAllVisibleToKitchenTableOrders() throws DataAccessException, SQLException
	{   
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

	    try 
	    {
	    	// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
	    	// When turned off multiple SQL statements is grouped into one transaction.
	    	databaseConnection.setAutoCommit(false);
	        
	    	// Prevent data from changing between reads, to ensure stable views of the data in multi-user environments
	    	// Once a row has been read, it can be changed during the transaction
	        databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			// Prepares a SQL statement to find all tableOrder instances that are visible to the kitchen staff
	        statementFindVisibleToKitchenTableOrders = databaseConnection.prepareStatement(FIND_VISIBLE_TO_KITCHEN_TABLE_ORDERS_QUERY);

	        // Run query
	        ResultSet resultSet = statementFindVisibleToKitchenTableOrders.executeQuery();

	        // Convert resultSet to a list
	        List<TableOrder> tableOrders = buildTableOrderObjectsAssociated(resultSet);

	        //All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);

	        return tableOrders;
	    } 
	    
	    catch (SQLException exception)
		{
			
	    	//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs while finding all visible tableOrders, an exception is thrown with the specified details
			throw new DataAccessException("Failed to update TableOrder in database", exception);
		}
	}
	
	
	private List<TableOrder> buildTableOrderObjectsAssociated(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store TableOrder objects within
		List<TableOrder> tableOrder = new ArrayList<>();

		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a TableOrder object and add it to the list
			tableOrder.add(buildTableOrderObjectAssociated(resultSet));
		}

		// Returns the populated list of TableOrder objects
		return tableOrder;
		
	}
	
	
	private TableOrder buildTableOrderObjectAssociated(ResultSet resultSet) throws SQLException
	{
		// Retrieves the timeOfArrival from the current row in the result set 
		// and combines it into Timestamp object
		Timestamp timeOfArrivalTimeStamp = resultSet.getTimestamp("timeOfArrival");

		// Creates an instance of LocalDateTime and sets it to null, this will later hold a converted date-time value
		LocalDateTime timeOfArrivalLocalDate = null;

		// If the timestamp that is retrieved from the database is not null then execute this section
		if (timeOfArrivalTimeStamp != null)
		{
		    // Converts the SQL Timestamp to a LocalDateTime and stores it within the timeOfArrivalLocalDate variable
		    timeOfArrivalLocalDate = timeOfArrivalTimeStamp.toLocalDateTime();
		}
		
		// Creates a TableOrder object with the data that was retrieved from the database
		TableOrder tableOrder = new TableOrder(resultSet.getInt("tableOrderId"), timeOfArrivalLocalDate, resultSet.getBoolean("isTableOrderClosed"),
				resultSet.getString("paymentType"), resultSet.getDouble("totalTableOrderPrice"), resultSet.getDouble("totalAmountPaid"),
				resultSet.getBoolean("isSentToKitchen"), resultSet.getBoolean("isRequestingService"), resultSet.getInt("orderPreparationTime"));
		
		try
		{
			List<PersonalOrder> personalOrders = new PersonalOrderDB().findPersonalOrdersBytableOrderId(tableOrder.getTableOrderId());
			for (PersonalOrder order: personalOrders)
			{
				tableOrder.addPersonalOrder(order);
			}
		}
		
		catch (DataAccessException exception)
		{
			throw new SQLException("Failed to retrieve Personal Orders.", exception);
		}
		
		return tableOrder;
	}
}
