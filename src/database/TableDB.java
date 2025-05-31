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
		
	// PreparedStatement for retrieving a table from the database
	private PreparedStatement statementFindByTableCode; 
	
	public TableDB() throws SQLException
	{
		//Prepares the SQL statement for retrieving a table
		//statementFindByTableCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_TABLE_BY_TABLECODE_QUERY);
	}
	
	/**
	 * Retrieve table by table code
	 * tableCode is made up by tableNumber and restaurantCode
	 */
	@Override
	public Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException 
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();

		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
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

			// Creates and initializes an Table object as null, which may later have table specific data
			// If no matching table is found it will return null
			Table chosenTable = null;
			
			// Moves the cursor to the next row in the result set returned by the database.
			// Returns true if a row exists, returns false if there are no more rows to read.
			if (resultSet.next())
			{
				// Converts the retrieved database row into a table object using the buildTableObject method
				chosenTable = buildTableObject(resultSet);
			}

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
			
			// Returns a object with with a tableCode matching the parameterlist
			return chosenTable;
		}

		catch (SQLException exception1)
		{
			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
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
		// Retrieves the restaurantCode and tableNumber from the current row in the result set
		// and combines them into a single tableCode string
		String tableCode = resultSet.getString("restaurantCode") + resultSet.getString("tableNumber");
		
		// Creates a new Table object with the generated tableCode
		Table table = new Table(tableCode);
		
		// Retrieves the tableOrderId from the result set
		// and uses TableOrderDB to fetch the corresponding TableOrder object
		TableOrder tableOrder = new TableOrderDB().findTableOrderByTableOrderId(resultSet.getInt("tableOrderId"));
		
		// Assigns the TableOrder object to the Table
		table.setCurrentTableOrder(tableOrder);
		
		return table;
	}	
}
