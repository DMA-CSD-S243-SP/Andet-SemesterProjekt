package application;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.MenuCardDB;
import database.MenuCardImpl;
import model.MenuCard;

/**
 * a controller class that bridges the logic from the model layer and the user interface from the gui layer
 * 
 * @author Anders Trankjær
 * @version 2025/13/05/13:10
 */
public class MenuCardController
{

	/**
	 * this method asks the Database to retrieve a list of MenuCard object from a restaurant with a given restaurantCode
	 * 
	 * @param restaurantCode - the searchcriteria which we look for a restaurant with
	 * @return a list of all MenuCards from a specific restaurant.
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	public List<MenuCard> findMenuCardsByRestaurantCode(String restaurantCode) throws DataAccessException, SQLException
	{
		MenuCardImpl dao = new MenuCardDB();
		return dao.findMenuCardsByRestaurantCode(restaurantCode);
	}
}
