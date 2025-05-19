package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Table;
import model.TableOrder;

/**
 * This class is responsible for accessing and managing table objects stored
 * in a database.
 * 
 * It implements the tableImpl interface. 
 * 
 * @author Anders Trankjær, Line Bertelsen & Christoffer Søndergaard
 * @version 18/05/2025 - 16:46
 */
public class TableDB implements TableImpl
{
	//  selects a specific row from table_object
	private static final String FIND_TABLE_BY_TABLECODE_QUERY = "SELECT * FROM Object_table WHERE tableNumber = ? AND restaurantCode = ?";
		
	private PreparedStatement statementFindByTableCode; 
	
	public TableDB() throws SQLException
	{
		statementFindByTableCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_TABLE_BY_TABLECODE_QUERY);
	}
	
	
	@Override
	public Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException 
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			databaseConnection.setAutoCommit(false);
			
			// Reading Tables happens many of times per day. However it occurs almost exclusively during business
			// hours, and updating happens rarely, and can usually be scheduled.
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			// Prepares a SQL statement to find and retrieve a table with a matching tableCode
			statementFindByTableCode = databaseConnection.prepareStatement(FIND_TABLE_BY_TABLECODE_QUERY);

			// Adds the tableCode from the methods parameterlist to the String instead of the placeholder
			statementFindByTableCode.setString(1, tableNumber);
			statementFindByTableCode.setString(2, restaurantCode);

			// Executes the query, and stores the retrieved data as a ResultSet
			ResultSet resultSet = statementFindByTableCode.executeQuery();

			// Creates and initializes an table object as null, which will later have table specific data
			Table table = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into a table object using the buildTableObject method
				table = buildTableObject(resultSet);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			// Returns a object with with a tableCode matching the parameterlist
			return table;
		}

		catch (SQLException exception1)
		{
			//Try catch for rollback
			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
			
			
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an table object with a tableCode matching: " + tableNumber + restaurantCode, exception1);
		}
	}
	
	/**
	 * this method builds a table object with the data provided from the database. 
	 * 
	 * @param resultSet - the data about the object from the database
	 * @return an instance of the table class with the specific data from the database
	 * @throws SQLException
	 * @throws DataAccessException 
	 */
	private Table buildTableObject(ResultSet resultSet) throws SQLException, DataAccessException
	{	
		String tableCode = resultSet.getString("restaurantCode") + resultSet.getString("tableNumber");
		
		Table table = new Table(tableCode);
		TableOrder tableOrder = new TableOrderDB().findTableOrderByTableOrderId(resultSet.getInt("tableOrderId"));
		table.setCurrentTableOrder(tableOrder);
		return table;
	}	
}
