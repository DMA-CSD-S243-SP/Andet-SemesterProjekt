package database;

import java.util.List;

import model.TableOrder;

/**
 * @author Line Bertelsen
 * @version 12.05.25 - 17.30
 */

public interface TableOrderImpl
{
	List <TableOrder> findAllTableOrders() throws DataAccessException;

	TableOrder findTableOrderById(int TableOrderId) throws DataAccessException;
}
