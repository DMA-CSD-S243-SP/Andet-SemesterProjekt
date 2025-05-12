package database;

import model.TableOrder;

/**
 * @author Line Bertelsen
 * @version 12.05.25 - 17.30
 */

public interface TableOrderImpl
{
	TableOrder findTableOrderById(int TableOrderId) throws DataAccessException;
}
