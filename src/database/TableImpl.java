package database;

import java.sql.SQLException;

import model.Table;

/**
 * 
 * @author Anders Trankjær
 * @Version 2025/08/05/11:30 
 */
public interface TableImpl
{
	/**
	 * this method creates a clone of the table that has a given tableCode. if no matching table
	 * is found the method returns null. 
	 * 
	 * @param tableCode the code used in the search
	 * @return a clone of a table that matches the search parameters
	 * @throws SQLException 
	 */
	Table findTableByCode(int tableNumber, String restaurant) throws DataAccessException, SQLException;
}
