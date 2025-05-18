package application;

import java.sql.SQLException;

import database.DataAccessException;
import database.TableDB;
import database.TableImpl;
import model.Table;
import model.TableOrder;

/**
 * a controller class that bridges the logic from the model layer and the user interface from the gui layer
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 18/05/2025 - 16:53
 */
public class TableController
{
	/**
	 * this method asks the database class its associatied with to retrieve a Table object
	 * 
	 * @param tableCode - the searchcriteria
	 * @return the table with the given tableCode or null if non was found.
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException 
	{
		TableImpl dao = new TableDB();

		// TODO: Is this intended, given that tableCode is not what it takes now but tableNumber?
//		String tableCode = restaurantCode + tableNumber;
//		return dao.findTableByCode(tableNumber, tableCode);
		return dao.findTableByCode(tableNumber, restaurantCode);
	}
	
	/**
	 * this method retrieves the TableOrder object that is currently attached to the table
	 * 
	 * @param table the table we want to find the tableOrder of
	 * @return the tableOrder
	 */
	public TableOrder getCurrentTableOrder(Table table)
	{
		return table.getCurrentTableOrder();
	}
}
