package application;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.PersonalOrderDB;
import model.AddOnOption;
import model.Discount;
import model.MainCourse;
import model.MenuCard;
import model.MenuItem;
import model.PersonalOrder;
import model.Restaurant;
import model.SelectionOption;
import model.Table;
import model.TableOrder;

/**
 * 
 * @author Line Bertelsen, Anders trankjær & Christoffer Søndergaard
 * @version 18/05/2025 - 16:53
 */

public class PersonalOrderController
{
	//Attributes/instance variables
	private MainCourse mainCourse; 
	private PersonalOrder personalOrder; 
	private TableOrder tableOrder;
	private Table table;
	
	//Constructor
	public PersonalOrderController()
	{
		
	}
	
	
	
	/**
	 * The enterTableCode method assigning the task to tableController 
	 * to find a table with a matching tableCode
	 * 
	 * @param tableNumber is the code of a table to search for
	 * @param restaurantCode is the code of the restaurant connect to the table
	 * @return a matching table matching tableCode or null if not found
	 * @throws DataAccessException if retrieval fails
	 * @throws SQLException if accessing the tableCode fails 
	 */
	public Table enterTableCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException
	{
		String tableCode = restaurantCode + tableNumber;
		//Attempt to execute the code within the braces
		try
		{
			TableController tableController = new TableController();
			//Assigns to tableController instance to find a table by the given tableCode
			table = tableController.findTableByCode(tableNumber, restaurantCode);
			
			if (table == null) 
			{
	            throw new IllegalArgumentException("No table found with tableCode: " + tableCode);
	        }
			
			this.tableOrder = tableController.getCurrentTableOrder(table);
			personalOrder = new PersonalOrder(tableOrder);
			return table;
		} 
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// If an SQL error occurs a exception is thrown with the specified details
			throw new DataAccessException("Unable to find Table objects in the database with a table code " + tableCode, exception);
		}
	}
	
	
	public List<Discount> enterNameAndAge(String customerName, int customerAge)
	{
		//Adds customer name to personalOrder 
		personalOrder.setCustomerName(customerName);
		
		//Adds customer age to personalOrder
		personalOrder.setCustomerAge(customerAge);
		
		// TODO: need to return a list of discount in GUI
		// return discount.getListOfDiscount();
		return null;
		
	}
	
	public List<MenuCard> enterDiscounts(List<Discount> listOfDiscounts) throws DataAccessException, SQLException
	{
		try
		{
			MenuCardController menuCardController = new MenuCardController();
			//Adds all the discount object to personalOrder
			personalOrder.addAllDiscounts(listOfDiscounts);	
			
			
			//returns all the avalible menuCards for this restaurant by the given restaurantCode
			return menuCardController.findMenuCardsByRestaurantCode(table.getTableCode().substring(0,3));
			
		} catch (Exception exception)
		{
			// If an SQL error occurs a exception is thrown with the specified details
			throw new DataAccessException("Unable to find restaurant objects in the database" , exception);
					
		}
	}
	
	/**
	 * this method adds a mainCourse object to the personalOrder
	 * 
	 * @param mainCourse - the mainCourse object that is going to be added
	 */
	public void enterMainCourse(MainCourse mainCourse)
	{
		this.mainCourse = mainCourse;
	}
	
	/**
	 * The method enterMainCourseOptions calls on the method addMainCourseLine 
	 * and adds a main course, a list of listOfAddOnOptions 
	 * and a list of listOfSelectionOption to personalOrder
	 * 
	 * @param potatoDish
	 * @param listOfAddOnOptionChoices
	 * @param listOfSelectionOption
	 */
	public void enterMainCourseOptions(MenuItem potatoDish, List<AddOnOption> listOfAddOnOptionChoices, List<SelectionOption> listOfSelectionOption)
	{
		personalOrder.addMenuItemLine(potatoDish);
		
		personalOrder.addMainCourseLine(mainCourse, listOfAddOnOptionChoices, listOfSelectionOption);
	}
	
	/**
	 * Adds side order items to the personalOrder
	 * Side order can be side dish, drink DipsAndSauces or SelfServeBar
	 *  
	 * @param sideOrderItem 
	 */
	public void enterSideOrder(MenuItem sideOrderItem) 
	{
		personalOrder.addMenuItemLine(sideOrderItem);
	}
	
	public void finishPersonalOrder() throws DataAccessException
	{
		try 
		{
			PersonalOrderDB personalOrderDB = new PersonalOrderDB();
			//Insert personalOrder to PersonalOrderDB
			if (!personalOrder.getPersonalOrderLines().isEmpty())
			{
				personalOrderDB.insertPersonalOrder(personalOrder, tableOrder.getTableOrderId());
			}
		}
		catch (DataAccessException exception) 
		{
			// If an SQL error occurs a exception is thrown with the specified details
			throw new DataAccessException("Unable to insert Personalorder to PersonalOrderDB" , exception);
		}
	}
	
	public void clearMenuItemList()
	{
		personalOrder.clearMenuItemLine();
	}
}
