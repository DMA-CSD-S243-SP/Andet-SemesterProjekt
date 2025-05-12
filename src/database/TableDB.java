package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Table;

/**
 * This class is responsible for accessing and managing table objects stored
 * in a database.
 * 
 * It implements the tableImpl interface. 
 * 
 * @author Anders Trankjær
 * @version 2025/12/05/11:20
 */
public class TableDB implements TableImpl
{
	//  selects a specific row from table_object
	private static final String FIND_TABLE_BY_TABLECODE_QUERY = "SELECT * FROM Table_object WHERE tableCode = ?";
		
	private PreparedStatement statementFindByTableCode; 
	
	public TableDB() throws SQLException
	{
		statementFindByTableCode = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_TABLE_BY_TABLECODE_QUERY);
	}
	
	
	@Override
	public Table findTableByCode(String tableCode) throws DataAccessException 
	{
		// Gets a connection to the database
				Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
				
				try
				{
					// Prepares a SQL statement to find and retrieve a table with a matching tableCode
					statementFindByTableCode = databaseConnection.prepareStatement(FIND_TABLE_BY_TABLECODE_QUERY);
					
					// Adds the tableCode from the methods parameterlist to the String instead of the placeholder
					statementFindByTableCode.setString(1, tableCode);
					
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
					
					// Returns a object with with a tableCode matching the parameterlist
					return table;
				}
				
				catch (SQLException exception)
				{
					// If an SQL error occurs a custom exception is thrown with the specified details
					throw new DataAccessException("Unable to find an table object with a tableCode matching: " + tableCode, exception);
				}
	}
	
	/**
	 * this method builds a table object with the data provided from the database. 
	 * 
	 * @param resultSet - the data about the object from the database
	 * @return an instance of the table class with the specific data from the database
	 * @throws SQLException
	 */
	private Table buildTableObject(ResultSet resultSet) throws SQLException
	{
		Table table = new Table(
				resultSet.getInt("tableNumber"), 
				resultSet.getString("tableCode"));
		return table;
	}	
}
