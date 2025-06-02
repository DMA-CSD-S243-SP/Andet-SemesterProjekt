package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EnumStatusType;
import model.MenuItem;
import model.PersonalOrder;
import model.PersonalOrderLine;

/**
 * This class is responsible for accessing and managing PersonalOrders objects
 * stored in a database.
 * 
 * Retrieves the PersonalOrder row, builds an object from that row.
 * It retrieves related PersonalOrderLine rows, where each of those is built.
 * 
 * It implements the PersonalOrderImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen, Anders Trankjær & Lumière Schack
 * @version 02/06/2025 - 15:08
 */
public class PersonalOrderDB implements PersonalOrderImpl
{
	// Selects a row from the table PersonalOrder in the database, based on the given personalOrderId
	private static final String FIND_PERSONALORDER_BY_PERSONALORDERID_QUERY = "SELECT * FROM PersonalOrder WHERE personalOrderId = ?";

	private static final String FIND_PERSONALORDERLINES_BY_PERSONALORDERID_QUERY = "SELECT * FROM PersonalOrderLine WHERE personalOrderId = ?";

	private static final String INSERT_PERSONALORDER = "INSERT INTO PersonalOrder (customerAge, customerName, tableOrderId) VALUES (?, ?, ?)";

	private static final String INSERT_PERSONALORDERLINE = "INSERT INTO PersonalOrderLine (additionalPrice, notes, status, personalOrderId, menuItemId) VALUES (?, ?, ?, ?, ?);";
	
	private static final String FIND_PERSONALORDERS_BY_TABLEORDERID_QUERY = "SELECT * FROM PersonalOrder WHERE tableOrderId = ?";

	// PreparedStatement for retrieving PersonalOrder based on the personalOrderId
	private PreparedStatement statementFindByPersonalOrderId;

	private PreparedStatement statementFindLinesByPersonalOrderId;

	private PreparedStatement statementInsertPersonalOrder;

	private PreparedStatement statementInsertPersonalOrderLine;
	
	private PreparedStatement statementFindByTableOrderId;

	// Constructor
	public PersonalOrderDB()
	{
		//When a PersonalOrderDB object is created, nothing happens
	}

	/**
	 * Retrieves a PersonalOrder from the database using the given ID.
	 * 
	 * Steps:
	 * - Establish DB connection.
	 * - Prepare and execute SQL query using the given ID.
	 * - If a matching result exists, build a PersonalOrder object from the result.
	 * - Return the fully constructed PersonalOrder.
	 * 
	 * @param personalOrderId 		- the id of the personalOrderId to search for
	 * @return personalOrder 		- the corresponding PersonaOrder object, or null if not found
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public PersonalOrder findPersonalOrderById(int personalOrderId) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
			databaseConnection.setAutoCommit(false);
			
			// Prevent data from changing between reads, to ensure stable views of the data in multi-user environments
			// Once a row had been read, it can be changed during the transaction
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			
			// Prepares a SQL statement to find and retrieve an PersonalOrder with a matching personalOrderId
			statementFindByPersonalOrderId = databaseConnection.prepareStatement(FIND_PERSONALORDER_BY_PERSONALORDERID_QUERY);

			// Adds the personalOrderId provided in the method's parameter to the String instead of the placeholder
			statementFindByPersonalOrderId.setInt(1, personalOrderId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindByPersonalOrderId.executeQuery();

			// Creates and initializes an PersonalOrder object as null, which will later be populated with PersonalOrder specific data
			PersonalOrder personalOrder = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an PersonalOrder object using the buildPersonalOrderObject method
				personalOrder = buildPersonalOrderObject(resultSet);
			}

			// Returns the personalOrder with a matching personalOrderId or null if no
			// PersonalOrder has the specified personalOrderId
			databaseConnection.setAutoCommit(true);
			
			return personalOrder;
		}

		catch (SQLException exception)
		{	
			// Reset auto-commit
			databaseConnection.setAutoCommit(true);
			
			// If an SQL error occurs while trying to find personalOrder, an exception is thrown with the specified details
			throw new DataAccessException("Unable to find an AvailabilityTracker object with an choiceMenuId matching Id: " + personalOrderId,
						exception);
		}	
	}

	
	/**
	 * Builds a specific PersonalOrder object from a database resultSet.
	 * 
	 * Steps:
	 * - Read fields from the current ResultSet row.
	 * - Create a new PersonalOrder and set its basic info.
	 * - Find associated PersonalOrderLines by order ID.
	 * - Add those lines to the order.
	 * 
	 * @param resultSet 			- the result set containing PersonalOrder data
	 * @return PersonalOrder 		- a PersonalOrder object with the extracted data
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	private PersonalOrder buildPersonalOrderObject(ResultSet resultSet) throws SQLException, DataAccessException
	{
		//TableOrder tableOrder = new TableOrder(resultSet.getInt("tableOrderId"));

		// Create PersonalOrder with null TableOrder, to be set later
		PersonalOrder personalOrder = new PersonalOrder(null);

		//Set the name and age, it gets from the database through a ResultSet, into a PersonalOrder object
		personalOrder.setCustomerAge(resultSet.getInt("customerAge"));
		personalOrder.setCustomerName(resultSet.getString("customerName"));
		
		// Get all associated lines using the findPersonalOrderLinesByPersonalOrderLineId method
		List<PersonalOrderLine> listOfLines = findPersonalOrderLinesByPersonalOrderLineId(resultSet.getInt("personalOrderId"));
		
		// Add them to the personalOrder
		for (PersonalOrderLine personalOrderLine : listOfLines)
		{
			personalOrder.addPersonalOrderLine(personalOrderLine);
		}
		// TODO: Add Discounts

		return personalOrder;
	}

	
	/**
	 * The method is being used by buildPersonalOrderObject
	 * Retrieves all PersonalOrderLine rows tied to a given PersonalOrder ID
	 * 
	 * @param personalOrderLineId 		- ID of the PersonalOrder
	 * @return personalOrderLineList 	- List of PersonalOrderLine objects
	 * @throws DataAccessException 		- if an error occurs during data access, such as rollback or connection issues
	 */
	private List<PersonalOrderLine> findPersonalOrderLinesByPersonalOrderLineId(int personalOrderLineId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		// Creates an empty list of PersonalOrderLine names personalOrderLineList
		List<PersonalOrderLine> personalOrderLineList = new ArrayList<>();
		
		try
		{
			// Prepares a SQL statement to find and retrieve an PersonalOrderLines with a matching personalOrderId
			statementFindLinesByPersonalOrderId = databaseConnection.prepareStatement(FIND_PERSONALORDERLINES_BY_PERSONALORDERID_QUERY);
			
			// Adds the personalOrderId provided in the method's parameter to the String instead of the placeholder
			statementFindLinesByPersonalOrderId.setInt(1, personalOrderLineId);
			
			// Executes the query, and stores the retrieved data in the variable named lineResultSet, which is a ResultSet object
			ResultSet lineResultSet = statementFindLinesByPersonalOrderId.executeQuery();
			
			// Convert each line into a PersonalOrderLine using the buildPersonalOrderLineObject method
			while (lineResultSet.next())
			{
				personalOrderLineList.add(buildPersonalOrderLineObject(lineResultSet));
			}
		} 
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find PersonalOrder objects in the database", exception);
		}
		return personalOrderLineList;
	}

	
	/**
	 * Builds a specific PersonalOrder object from a database resultSet.
	 * 
	 * @param resultSet 			- the result set containing PersonalOrder data
	 * @return personalOrder 		- a PersonalOrder object with the extracted data
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	private PersonalOrderLine buildPersonalOrderLineObject(ResultSet resultSet) throws SQLException, DataAccessException
	{
		// Retrieves the SQL MenuItem object from the "menuItemId" column in the supplied result set
		MenuItem menuItem = new MenuItemDB().findMenuItemByMenuItemId(resultSet.getInt("menuItemId"));
		
		// Creates a PersonalOrderLine object with the menuItem data that was retrieved from the database
		PersonalOrderLine personalOrderLine = new PersonalOrderLine(menuItem);
		
		//Set the notes, additionalPrice and status, it gets from the database through a ResultSet, into a PersonalOrderLine object
		personalOrderLine.setNotes(resultSet.getString("notes"));
		personalOrderLine.setAdditionalPrice(resultSet.getDouble("additionalPrice"));
		personalOrderLine.setStatus(EnumStatusType.values()[resultSet.getInt("status")]);

		return personalOrderLine;
	}

	
	/**
	 * The method is used in ViewGuesTableOrderConfirmation in gui layer.
	 * and the method FinishPersonalOrder in PersonalOrderController.
	 * Inserts a new PersonalOrder into the PersonalOrder table in the Database.
	 * 
	 * @param personalOrder			- the PersonalOrder object containing customer.
	 * @param tableOrderId 			- the Id that belongs to this personal order
	 * @return personalOrder		- that PersonalOrder object, now considered persisted in the database
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 */
	@Override
	public PersonalOrder insertPersonalOrder(PersonalOrder personalOrder, int tableOrderId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		try
		{
			// Turns off the auto-commit in the database, so it doesn't automatically save changes after each SQL statement.
			// When turned off multiple SQL statements is grouped into one transaction.
			databaseConnection.setAutoCommit(false);

			// Prevent data from changing between reads, to ensure stable views of the data in multi-user environments
			// Once a row had been read, it can be changed during the transaction
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			// Prepares a SQL statement to insert PersonalOrder and return a generated key
			statementInsertPersonalOrder = databaseConnection.prepareStatement(INSERT_PERSONALORDER,
					Statement.RETURN_GENERATED_KEYS);
			
			// Fill customer information
			statementInsertPersonalOrder.setInt(1, personalOrder.getCustomerAge());
			statementInsertPersonalOrder.setString(2, personalOrder.getCustomerName());
			statementInsertPersonalOrder.setInt(3, tableOrderId);

			statementInsertPersonalOrder.executeUpdate();

			// The database generates a key and retrieve the new personalOrderId 
			// and the data is stored in the ResultSet object called generatedKey
			ResultSet generatedKey = statementInsertPersonalOrder.getGeneratedKeys();
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if (generatedKey.next())
			{
				// Give the value of the first(1) column in the current row of the result set
				// Store it in the personalOrderId variable
				int personalOrderId = generatedKey.getInt(1);
				
				// The new generated key is stored in the PersonalOrderLine table in the database 
				insertPersonalOrderLines(personalOrder.getPersonalOrderLines(), personalOrderId);
			}
			
			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);
		} 
		
		//If an error happens 
		catch (SQLException exception)
		{
			exception.printStackTrace();
			
			try
			{
				//Undo all changes made so far in the transaction
				databaseConnection.rollback();
				
				//Restores the default behavior and turns on auto-commit
				databaseConnection.setAutoCommit(true);
			} 
			
			//If a rollback error happens it throws an exception
			catch (SQLException rollbackException)
			{
				// If rollback fails, throw a custom exception with details
				throw new DataAccessException("Rollback failed after updateTableOrder error", rollbackException);
			}
			
			// If an SQL error occurs while updating the tableOrder an exception is thrown with the specified details
			throw new DataAccessException("Failed to insert PersonalOrder", exception);
		}

		return personalOrder;
	}

	
	/**
	 * This method inserts a list of PersonalOrderLine objects into the database, each linked to a specific PersonalOrderId.
	 * 
	 * @param personalOrderLines  	- a list of PersonalOrderLine objects to be inserted
	 * @param personalOrderId     	- the ID of the PersonalOrder that each line is linked to
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 */
	private void insertPersonalOrderLines(List<PersonalOrderLine> personalOrderLines, int personalOrderId) throws SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		
		// Prepares a SQL statement to insert PersonalOrderLine
		statementInsertPersonalOrderLine = databaseConnection.prepareStatement(INSERT_PERSONALORDERLINE);
		
		//Adds multiple lines with a for-each loop
		for (PersonalOrderLine personalOrderLine : personalOrderLines)
		{
			// Fills in the 5 placeholders with the values from the PersonalOrderLine object.
			statementInsertPersonalOrderLine.setDouble(1, personalOrderLine.getAdditionalPrice());
			statementInsertPersonalOrderLine.setString(2, personalOrderLine.getNotes());
			statementInsertPersonalOrderLine.setInt(3, personalOrderLine.getStatus().ordinal());
			statementInsertPersonalOrderLine.setInt(4, personalOrderId);
			statementInsertPersonalOrderLine.setInt(5, personalOrderLine.getMenuItem().getMenuItemId());
			
			// Adds the filled in values to the statement batch and send them to the database
			statementInsertPersonalOrderLine.addBatch();
		}
		
		// Execute insertion of the listed data
		statementInsertPersonalOrderLine.executeBatch();
	}
	

	/**
	 * The method is use in ViewGuesTableOrder in gui layer
	 * to find all personalOrders that is listed in the currentTableOrder
	 * 
	 * @param tableOrderId 			- the Id to find matching personalOrders
	 * @throws DataAccessException 	- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException			- if a SQL operation fails
	 */
	@Override
	public List<PersonalOrder> findPersonalOrdersBytableOrderId(int tableOrderId) throws SQLException, DataAccessException 
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
			statementFindByTableOrderId = databaseConnection.prepareStatement(FIND_PERSONALORDERS_BY_TABLEORDERID_QUERY);

			// Adds the TableOrderId provided in the method's parameter to the String instead of the placeholder
			statementFindByTableOrderId.setInt(1, tableOrderId);

			// Executes the prepared statement and stores the result set
			ResultSet resultSetPersonalOrder = statementFindByTableOrderId.executeQuery();

			// Converts the result set into a list of TableOrder objects with the buildPersonalOrderObjects method
			List<PersonalOrder> resultListOfPersonalOrder = buildPersonalOrderObjects(resultSetPersonalOrder);

			//All the changes you've made since setAutoCommit(false), is manually saved into the database
			databaseConnection.commit();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);

			// Returns the list of TableOrder objects
			return resultListOfPersonalOrder;
		}

		catch (SQLException exception1)
		{

			//Undo all changes made so far in the transaction
			databaseConnection.rollback();
			
			//Restores the default behavior and turns on auto-commit
			databaseConnection.setAutoCommit(true);

			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find PersonalOrder objects in the database", exception1);
		}
	}

	/**
	 * Builds a specific PersonalOrder object from a database resultSetPersonalOrder.
	 * 
	 * @param resultSetPersonalOrder 	- the result set containing PersonalOrder data
	 * @return personalOrders			- a list of PersonalOrder objects
	 * @throws DataAccessException 		- if an error occurs during data access, such as rollback or connection issues
	 * @throws SQLException				- if a SQL operation fails
	 */
	private List<PersonalOrder> buildPersonalOrderObjects(ResultSet resultSetPersonalOrder) throws SQLException, DataAccessException
		{
			// Creates an empty list to store Employee objects within
			List<PersonalOrder> personalOrders = new ArrayList<>();

			// Iterates through the result set while there are still more rows in the database's table
			while (resultSetPersonalOrder.next())
			{
				// Converts each row into a Employee object and add it to the list
				personalOrders.add(buildPersonalOrderObject(resultSetPersonalOrder));
			}

			// Returns the populated list of Employee objects
			return personalOrders;
			
		}
}
