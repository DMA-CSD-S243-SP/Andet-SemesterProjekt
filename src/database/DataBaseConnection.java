// Packages
package database;

// Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class manages the connection between the program and the database.
 * It follows a singleton pattern to ensure only one connection instance is used.
 * 
 * @author Anders Have & Christoffer Søndergaard
 * @version 08/05/2025 - 12:20
 */
public class DataBaseConnection
{
	// The database connection instance is initialized as null
	private Connection connection = null;
	
	// The singleton instance of dataBaseConnection is declared
	private static DataBaseConnection dataBaseConnection;

	// Database driver class
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	// Name of the database
	private static final String dataBaseName = "DMA-CSD-S243_10644781";

	// Address of the server
	private static final String serverAddress = "hildur.ucn.dk";

	// Server port number
	private static final int serverPort = 1433;
	
	// Database username
	private static final String userName = "DMA-CSD-S243_10644781";
	
	// Database password
	private static final String password = "Password1!";

	
    /**
     * Private constructor that initializes the database connection.
     */
	private DataBaseConnection()
	{
		 // Constructs the full database connection string
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false", serverAddress, serverPort, dataBaseName, userName, password);
		
		try
		{
			// Load the database driver class
			Class.forName(driverClass);
			
			// Establishes connection to the database
			connection = DriverManager.getConnection(connectionString);
		}
		
		catch (ClassNotFoundException exception)
		{
			// Error handling for missing JDBC driver
			System.err.println("Unable to load the JDBC driver");
			
			exception.printStackTrace();
		}
		
		catch (SQLException exception)
		{
			// Error handling for connection failure
			System.err.println("Could not connect to database " + dataBaseName + "@" + serverAddress + ":" + serverPort + " as user " + userName + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - password.length()) + "....");
			
			exception.printStackTrace();
		}
	}

	
    /**
     * Retrieves the singleton instance of DataBaseConnection.
     * 
     * @return the singleton instance of DataBaseConnection
     */
	public static DataBaseConnection getInstance()
	{
	    // Checks if an instance of DataBaseConnection already exists
	    if (dataBaseConnection == null) 
	    {
	        // If no instance exists, create a new DataBaseConnection object
	        dataBaseConnection = new DataBaseConnection();
	    }
	    
	    // Returns the existing or newly created instance of DataBaseConnection
		return dataBaseConnection;
	}
	

    /**
     * Retrieves the active database connection.
     * 
     * @return the active Connection object
     */
	public Connection getConnection()
	{
		return connection;
	}

	
	 /**
     * Closes the current database connection.
     */
	public void disconnect()
	{
		try
		{
			// Closes the connection
			connection.close();
		}
		
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}
	
	
    /**
     * Starts a database transaction by disabling auto-commit mode.
     * 
     * @throws SQLException if an error occurs while setting auto-commit
     */
	public void startTransaction() throws SQLException
	{
		// Disables auto-commit mode to start a transaction
		connection.setAutoCommit(false);
	}

	
    /**
     * Rolls back the current transaction and re-enables auto-commit mode.
     * 
     * @throws SQLException if an error occurs while rolling back
     */
	public void rollbackTransaction() throws SQLException
	{
		// Rolls back the transaction to revert any changes made during the transaction
		connection.rollback();
		
		// Re-enables auto-commit mode so future statements are committed automatically
		connection.setAutoCommit(true);
	}

	
    /**
     * Executes an INSERT statement and retrieves the generated identity key.
     * 
     * @param preparedStatement the prepared SQL statement
     * @return the generated identity key, or -1 if no key was generated
     * @throws SQLException if an error occurs during execution
     */
	public int executeInsertWithIdentity(PreparedStatement preparedStatement) throws SQLException
	{
		// Creates an integer variable named generatedKey and sets its initial value to -1
		int generatedKey = -1;
		
		try
		{
			// Executes the insert statement and returns the number of affected rows
			generatedKey = preparedStatement.executeUpdate();
			
			// Checks if the insertion was successful
			if (generatedKey > 0)
			{
				// Retrieves the generated keys from the executed statement
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				
				// Moves the cursor to the first row of the result set
				resultSet.next();
				
				// Extracts the generated key from the first column, commonly this is the auto-incremented ID
				generatedKey = resultSet.getInt(1);
			}
		}
		
		catch (SQLException exception)
		{
			// Prints the stack trace if an SQL exception occurs
			exception.printStackTrace();
			
			// Throws the exception to be handled by the calling method
			throw exception;
		}
		
		// Returns the generated key (or -1 if the insertion failed)
		return generatedKey;
	}
	

    /**
     * Executes an INSERT statement using a raw SQL string and retrieves the generated identity key.
     * 
     * @param sql the SQL INSERT statement
     * @return the generated identity key, or -1 if no key was generated
     * @throws SQLException if an error occurs during execution
     */
	public int executeInsertWithIdentity(String sql) throws SQLException
	{
		// Prints the SQL query that is being being executed to the console
		System.out.println("DataBaseConnection, Inserting: " + sql);
		
		// Creates an integer variable named generatedKey and sets its initial value to -1
		int generatedKey = -1;
		
		// Tries to execute the SQL query using a Statement
		try (Statement statement = connection.createStatement())
		{
			// Executes the insert statement and requests the generated keys
			generatedKey = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			// Checks if the insert operation was successful
			if (generatedKey > 0)
			{
				// Retrieves the generated keys from the executed statement
				ResultSet resultSet = statement.getGeneratedKeys();
				
				// Moves the cursor to the first row of the result set
				resultSet.next();
	            
				// Extracts the generated key (usually the auto-incremented ID) from the first column
				generatedKey = resultSet.getInt(1);
			}

		}
		
		catch (SQLException exception)
		{
			// Prints the stack trace if an SQL exception occurs
			exception.printStackTrace();
			
			// Throws the exception to be handled by the calling method
			throw exception;
		}
		
		// Returns the generated key (or -1 if the insertion failed)
		return generatedKey;
	}
}
