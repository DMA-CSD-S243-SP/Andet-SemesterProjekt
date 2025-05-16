package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AvailabilityTracker;
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
 * @author Line Bertelsen & Anders Trankjær
 * @version 13.05.25 - 16:55
 */
public class PersonalOrderDB implements PersonalOrderImpl
{
	// Selects a row from the table PersonalOrder in the database, based on the
	// given personalOrderId
	private static final String FIND_PERSONALORDER_BY_PERSONALORDERID_QUERY = "SELECT * FROM PersonalOrder WHERE personalOrderId = ?";

	private static final String FIND_PERSONALORDERLINES_BY_PERSONALORDERID_QUERY = "SELECT * FROM PersonalOrderLine WHERE personalOrderId = ?";
	
	private static final String INSERT_PERSONALORDER_AND_GET_ID = "INSERT INTO PersonalOrder (customerAge, customerName, tableOrderId) VALUES (?, ?, ?); DECLARE @id INT = SCOPE_IDENTITY(); SELECT @id";

	// PreparedStatement for retrieving PersonalOrder based on the personalOrderId
	private PreparedStatement statementFindByPersonalOrderId;

	private PreparedStatement statementFindLinesByPersonalOrderId;
	
	private PreparedStatement statementInsertPersonalOrderAndGetId;

	// Constructor
	public PersonalOrderDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving a PersonalOrder by its
		// personalOrderId
		statementFindByPersonalOrderId = DataBaseConnection.getInstance().getConnection()
				.prepareStatement(FIND_PERSONALORDER_BY_PERSONALORDERID_QUERY);
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
	 * @throws SQLException if accessing the resultSet fails
	 * @throws DataAccessException 
	 */
	private PersonalOrder buildPersonalOrderObject(ResultSet resultSet) throws SQLException, DataAccessException
	{
		TableOrder tableOrder = new TableOrder(resultSet.getInt("tableOrderId"));

		PersonalOrder personalOrder = new PersonalOrder(tableOrder);
		personalOrder.setCustomerAge(resultSet.getInt("customerAge"));
		personalOrder.setCustomerName(resultSet.getString("customerName"));
		List<PersonalOrderLine> listOfLines = findPersonalOrderLinesByPersonalOrderLineId(resultSet.getInt("personalOrderId"));
		for (PersonalOrderLine line: listOfLines)
		{
			personalOrder.addPersonalOrderLine(line);
		}
		// TODO: Add Discounts

		return personalOrder;
	}

	private List<PersonalOrderLine> findPersonalOrderLinesByPersonalOrderLineId(int personalOrderLineId) throws DataAccessException
	{
		Connection databaseConnection = DataBaseConnection.getInstance().getConnection();
		List<PersonalOrderLine> lineList = new ArrayList<>();
		try 
		{
			statementFindLinesByPersonalOrderId = databaseConnection
					.prepareStatement(FIND_PERSONALORDERLINES_BY_PERSONALORDERID_QUERY);
			statementFindLinesByPersonalOrderId.setInt(1, personalOrderLineId);
			ResultSet lineResult = statementFindLinesByPersonalOrderId.executeQuery();
			while(lineResult.next())
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
	 * @throws SQLException if accessing the resultSet fails
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
	public PersonalOrderLine insertPersonalOrderLineToDatabase(PersonalOrderLine personalOrderLine) throws DataAccessException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonalOrder insertPersonalOrder(PersonalOrder personalOrder) throws DataAccessException
	{
		// TODO Auto-generated method stub
		return null;
	}
}
