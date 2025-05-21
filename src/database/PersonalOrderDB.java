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
import model.TableOrder;

/**
 * This class is responsible for accessing and managing PersonalOrders objects
 * stored in a database.
 * 
 * It implements the PersonalOrderImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen, Anders Trankjær & Lumière Schack
 * @version 17.05.25 - 10.30
 */
public class PersonalOrderDB implements PersonalOrderImpl
{
	// Selects a row from the table PersonalOrder in the database, based on the
	// given personalOrderId
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
		
	}

	/**
	 * Finds a PersonaOrder object by searching for a PersonalOrder with a matching
	 * Id.
	 * 
	 * @param personalOrderId the id of the personalOrderId to search for
	 * @return the corresponding PersonaOrder object, or null if not found
	 * @throws DataAccessException if retrieval fails
	 */
	@Override
	public PersonalOrder findPersonalOrderById(int personalOrderId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		try
		{
			databaseConnection.setAutoCommit(false);
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			// Prepares a SQL statement to find and retrieve an PersonalOrder with a
			// matching personalOrderId
			statementFindByPersonalOrderId = databaseConnection
					.prepareStatement(FIND_PERSONALORDER_BY_PERSONALORDERID_QUERY);

			// Adds the personalOrderId provided in the method's parameter to the String
			// instead of the placeholder
			statementFindByPersonalOrderId.setInt(1, personalOrderId);

			// Executes the query, and stores the retrieved data in the variable named
			// resultSet, which is a ResultSet object
			ResultSet resultSet = statementFindByPersonalOrderId.executeQuery();

			// Creates and initializes an PersonalOrder object as null, which will later be
			// populated with PersonalOrder specific data
			PersonalOrder personalOrder = null;

			// Iterates through the resultSet while there are still more rows in the
			// database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an PersonalOrder object using the
				// buildPersonalOrderObject method
				personalOrder = buildPersonalOrderObject(resultSet);
			}

			// Returns the personalOrder with a matching personalOrderId or null if no
			// PersonalOrder has the specified personalOrderId
			databaseConnection.setAutoCommit(true);
			return personalOrder;
		}

		catch (SQLException exception)
		{
			try
			{
				databaseConnection.setAutoCommit(true);
			} catch (SQLException e)
			{
			}
			// If an SQL error occurs a custom exception is thrown with the specified
			// details
			throw new DataAccessException(
					"Unable to find an AvailabilityTracker object with an choiceMenuId matching Id: " + personalOrderId,
					exception);
		}
	}

	/**
	 * Builds a specific PersonalOrder object from a database resultSet.
	 * 
	 * @param resultSet the result set containing PersonalOrder data
	 * @return a PersonalOrder object with the extracted data
	 * @throws SQLException        if accessing the resultSet fails
	 * @throws DataAccessException
	 */
	private PersonalOrder buildPersonalOrderObject(ResultSet resultSet) throws SQLException, DataAccessException
	{
		//TableOrder tableOrder = new TableOrder(resultSet.getInt("tableOrderId"));

		PersonalOrder personalOrder = new PersonalOrder(null);
		personalOrder.setCustomerAge(resultSet.getInt("customerAge"));
		personalOrder.setCustomerName(resultSet.getString("customerName"));
		List<PersonalOrderLine> listOfLines = findPersonalOrderLinesByPersonalOrderLineId(
				resultSet.getInt("personalOrderId"));
		for (PersonalOrderLine line : listOfLines)
		{
			personalOrder.addPersonalOrderLine(line);
		}
		// TODO: Add Discounts

		return personalOrder;
	}

	private List<PersonalOrderLine> findPersonalOrderLinesByPersonalOrderLineId(int personalOrderLineId)
			throws DataAccessException
	{
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		List<PersonalOrderLine> lineList = new ArrayList<>();
		try
		{
			statementFindLinesByPersonalOrderId = databaseConnection
					.prepareStatement(FIND_PERSONALORDERLINES_BY_PERSONALORDERID_QUERY);
			statementFindLinesByPersonalOrderId.setInt(1, personalOrderLineId);
			ResultSet lineResult = statementFindLinesByPersonalOrderId.executeQuery();
			while (lineResult.next())
			{
				lineList.add(buildPersonalOrderLineObject(lineResult));
			}
		} catch (SQLException e)
		{
		}
		return lineList;
	}

	/**
	 * Builds a specific PersonalOrder object from a database resultSet.
	 * 
	 * @param resultSet the result set containing PersonalOrder data
	 * @return a PersonalOrder object with the extracted data
	 * @throws SQLException        if accessing the resultSet fails
	 * @throws DataAccessException
	 */
	private PersonalOrderLine buildPersonalOrderLineObject(ResultSet resultSet) throws SQLException, DataAccessException
	{
		MenuItem menuItem = new MenuItemDB().findMenuItemByMenuItemId(resultSet.getInt("menuItemId"));
		PersonalOrderLine personalOrderLine = new PersonalOrderLine(menuItem);
		personalOrderLine.setNotes(resultSet.getString("notes"));
		personalOrderLine.setAdditionalPrice(resultSet.getDouble("additionalPrice"));
		personalOrderLine.setStatus(EnumStatusType.values()[resultSet.getInt("status")]);

		return personalOrderLine;
	}

	@Override
	public PersonalOrder insertPersonalOrder(PersonalOrder personalOrder, int tableOrderId) throws DataAccessException
	{
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		try
		{
			databaseConnection.setAutoCommit(false);
			databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			statementInsertPersonalOrder = databaseConnection.prepareStatement(INSERT_PERSONALORDER,
					Statement.RETURN_GENERATED_KEYS);
			// Fill customer information
			statementInsertPersonalOrder.setInt(1, personalOrder.getCustomerAge());
			statementInsertPersonalOrder.setString(2, personalOrder.getCustomerName());
			statementInsertPersonalOrder.setInt(3, tableOrderId);

			statementInsertPersonalOrder.executeUpdate();

			ResultSet generatedKey = statementInsertPersonalOrder.getGeneratedKeys();
			if (generatedKey.next())
			{
				// int personalOrderId = generatedKey.getInt("personalOrderId");
				int personalOrderId = generatedKey.getInt(1);
				insertPersonalOrderLines(personalOrder.getPersonalOrderLines(), personalOrderId);
			}

			databaseConnection.commit();
			databaseConnection.setAutoCommit(true);
		} catch (SQLException e)
		{
			e.printStackTrace();
			try
			{
				databaseConnection.rollback();
				databaseConnection.setAutoCommit(true);
			} catch (SQLException rollbackException)
			{
			}
			throw new DataAccessException("Failed to insert PersonalOrder", e);
		}

		return personalOrder;
	}

	private void insertPersonalOrderLines(List<PersonalOrderLine> personalOrderLines, int personalOrderId)
			throws SQLException
	{
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		statementInsertPersonalOrderLine = databaseConnection.prepareStatement(INSERT_PERSONALORDERLINE);
		for (PersonalOrderLine line : personalOrderLines)
		{
			// Fills in the values for given PersonalOrderLine
			statementInsertPersonalOrderLine.setDouble(1, line.getAdditionalPrice());
			statementInsertPersonalOrderLine.setString(2, line.getNotes());
			statementInsertPersonalOrderLine.setInt(3, line.getStatus().ordinal());
			statementInsertPersonalOrderLine.setInt(4, personalOrderId);
			statementInsertPersonalOrderLine.setInt(5, line.getMenuItem().getMenuItemId());
			// Adds the filled in values to the statement batch
			statementInsertPersonalOrderLine.addBatch();
		}
		statementInsertPersonalOrderLine.executeBatch();
	}

	@Override
	public List<PersonalOrder> findPersonalOrderBytableOrderId(int TableOrderId) throws SQLException, DataAccessException 
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
					statementFindByTableOrderId = databaseConnection.prepareStatement(FIND_PERSONALORDERS_BY_TABLEORDERID_QUERY);
					
					
					statementFindByTableOrderId.setInt(1, TableOrderId);
					
					// Executes the prepared statement and stores the result set
					ResultSet resultSetPersonalOrder = statementFindByTableOrderId.executeQuery();

					// Converts the result set into a list of TableOrder objects
					List<PersonalOrder> resultListOfPersonalOrder = buildPersonalOrderObjects(resultSetPersonalOrder);

					databaseConnection.commit();
					databaseConnection.setAutoCommit(true);
					
					// Returns the list of TableOrder objects
					return resultListOfPersonalOrder;
				}

				catch (SQLException exception1)
				{
					
					databaseConnection.rollback();
					databaseConnection.setAutoCommit(true);
					
					// If an SQL error occurs a custom exception is thrown with the specified details
					throw new DataAccessException("Unable to find PersonalOrder objects in the database", exception1);
				}
	}

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
