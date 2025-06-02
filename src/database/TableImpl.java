package database;

import java.sql.SQLException;

import model.Table;

/**
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 02/06/2025 - 13:54
 */
public interface TableImpl
{
	/**
	 * This method creates a clone of the table that has a given tableCode. if no matching table
	 * is found the method returns null. 
	 * 
	 * @param tableCode 	- the code used in the search
	 * @return chosenTable 	- a clone of a table that matches the search parameters
	 * @throws SQLException	- if a SQL operation fails
     */
	Table findTableByCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException;
}
