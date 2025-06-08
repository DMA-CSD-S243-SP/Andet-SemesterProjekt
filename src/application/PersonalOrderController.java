package application;

// Imports
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataAccessException;
import database.PersonalOrderDB;
import database.PersonalOrderImpl;
import model.AddOnOption;
import model.Discount;
import model.MainCourse;
import model.MenuCard;
import model.MenuItem;
import model.PersonalOrder;
import model.SelectionOption;
import model.Table;
import model.TableOrder;


/**
 * The PersonalOrderController acts as a bridge between the GUI layer and the data access layer (DAO).
 * The controller coordinates the requests and responses related to PersonalOrder entities, by delegating
 * the database operations to the DAO implementation PersonalOrderDB.
 *
 * This separation helps to ensure low coupling between GUI and data persistence, and thereby improve
 * the system's maintainability and possibility to scale.
 *
 *
 * @author Line Bertelsen, Anders Trankjær & Christoffer Søndergaard
 * @version 08/06/2025 - 19:31
 */
public class PersonalOrderController
{
	// Attributes / instance variables
	private MainCourse mainCourse; 
	private PersonalOrder personalOrder; 
	private TableOrder tableOrder;
	private Table chosenTable;
	
	
	// Creates an empty constructor for this instance
	public PersonalOrderController()
	{
		
	}
	
	
	/**
	 * Looks up a table in the database using the specified table number and 
	 * restaurant code.
	 *
	 * This method constructs a unique table code from the restaurant code and table number
	 * then uses it to find the corresponding Table object through the TableController and then
	 * retrieves the associated TableOrder, and creates a new PersonalOrder and associates
	 * it to the TableOrder, and stores the found table in the chosenTable attribute and returns it.
	 *
	 * This method is called from the GUI class ViewGuestTableInformation after the
	 * user has input the 7-digit table number, which happens as the first step in the 
	 * customer's ordering process.
	 *
	 * @param tableNumber the number identifying the table within the restaurant
	 * @param restaurantCode the code identifying the restaurant
	 * @return the Table object found via the table code combination
     * @throws DataAccessException if a database access issue occurs somewhere at the DAO level
     * @throws SQLException if an SQL query execution fails
	 */
	public Table enterTableCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException
	{
		// Creates a uniquely identifiable tableCode by concatenating restaurantCode and tableNumber
		String tableCode = restaurantCode + tableNumber;
		
		//Attempt to execute the code within the braces
		try
		{
			// Creates a TableController instance and store it within the tableController variable
			TableController tableController = new TableController();

			//Assigns to tableController instance to find a table by the given tableCode
			chosenTable = tableController.findTableByCode(tableNumber, restaurantCode);
			
			// If the chosenTable is null then execute this esction
			if (chosenTable == null) 
			{
	            throw new IllegalArgumentException("No table found with tableCode: " + tableCode);
	        }
			
			// this.tableOrder = tableController.getCurrentTableOrder(chosenTable);
			tableOrder = tableController.getCurrentTableOrder(chosenTable);
			
			// Instantiates the personalOrder and sets it to be associated with the specified TableOrder
			personalOrder = new PersonalOrder(tableOrder);
			
			return chosenTable;
		} 
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// If an SQL error occurs a exception is thrown with the specified details
			throw new DataAccessException("Unable to find Table objects in the database with a table code " + tableCode, exception);
		}
	}
	
	
	/**
	 * Sets the guest / customer's name and age in the current PersonalOrder object.
	 * 
	 * This method is called from the GUI class ViewGuestCustomerInformation after the
	 * user has input their personal details, which happens as the second step in the 
	 * customer's ordering process.
	 * 
	 * The method is structured to potentially return a list of applicable discounts
	 * but currently returns null until that functionality is implemented in a later use case.
	 *
	 * @param customerName the name of the customer placing the order
	 * @param customerAge the age of the customer placing the order
	 * @return a list of applicable Discount objects (currently returns null)
	 */
	public List<Discount> enterNameAndAge(String customerName, int customerAge)
	{
		// Adds customer name to personalOrder 
		personalOrder.setCustomerName(customerName);
		
		// Adds customer age to personalOrder
		personalOrder.setCustomerAge(customerAge);
		
		// TODO: need to return a list of discount in GUI
		// return discount.getListOfDiscount();
		return null;
	}
	
	
	/**
	 * Adds a list of Discount objects to the current PersonalOrder and retrieves
	 * all MenuCard objects available for the restaurant associated with the selected table.
	 *
	 * This method gets called after selecting one or more discounts for the customer,
	 * and will update the PersonalOrder before fetching the specified restaurant's menu cards
	 * for the user to select between in the ViewGuestMenuOverview class.
	 *
	 * This method is called from the GUI class ViewGuestDiscountSelection after the
	 * user has chosen the discounts they are entitled to, which happens as the third step in the
	 * customer's ordering process.
	 *
	 * @param listOfDiscounts a list of Discount objects selected by the customer
	 * @return a list of MenuCard objects associated with the customer's restaurant
	 * @throws DataAccessException if a database error occurs during discount saving or menu card retrieval
	 * @throws SQLException if an SQL query execution fails
	 */
	public List<MenuCard> enterDiscounts(List<Discount> listOfDiscounts) throws DataAccessException, SQLException
	{
		try
		{
			// Creates a MenuCardController instance and store it within the menuCardController variable
			MenuCardController menuCardController = new MenuCardController();
			
			// Adds all the Discount objects to the PersonalOrder object
			personalOrder.addAllDiscounts(listOfDiscounts);		
			
			// Retrieves the restaurantCode based off of the first 3 digits in the chosenTable's tableCode and stores it within the restaurantCode variable
			String restaurantCode = chosenTable.getTableCode().substring(0,3);
			
			// Returns a list of MenuCard objects from the Restaurant instance that matches the supplied restaurantCode
			return menuCardController.findMenuCardsByRestaurantCode(restaurantCode);
		}
		
		catch (Exception exception)
		{
			// If a SQL error occurs an exception is thrown with the specified details
			throw new DataAccessException("Unable to find restaurant objects in the database" , exception);
		}
	}
	
	
	/**
	 * Adds a MainCourse object to the personalOrder
	 * 
	 * This method is called from the GUI class ViewGuestMenuAdult after the
	 * user has chosen the discounts they are entitled to, which happens as the third step in the
	 * customer's ordering process.
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
	 * @param potatoDish the MenuItem of the PotatoDish type to add to the PersonalOrder
	 * @param listOfAddOnOptionChoices the list of selected Add-on options 
	 * @param listOfSelectionOption the list of selection options
	 */
	public void enterMainCourseOptions(MenuItem potatoDish, List<AddOnOption> listOfAddOnOptionChoices, List<SelectionOption> listOfSelectionOption)
	{
		// Adds a MenuItem object of the PotatoDish type to the list of PersonalOrderLines
		personalOrder.addMenuItemLine(potatoDish);

		// Adds a MainCourse object along with a list of AddOn option choices and
		// a list of menu selection option choices
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
	
	
	/**
	 * Sets the current PersonalOrder instance.
	 *
	 * @param personalOrder the PersonalOrder object to assign
	 */
	public void setPersonalOrder(PersonalOrder personalOrder)
	{
		this.personalOrder = personalOrder;
		
	}


	/**
	 * Sets the current TableOrder instance.
	 *
	 * @param currentTableOrder the TableOrder to associate with the current personal order
	 */
	public void setTableOrder(TableOrder currentTableOrder)
	{
		this.tableOrder = currentTableOrder;	
	}

	
	/**
	 * Finalizes the current personal order by inserting it into the database.
	 * 
	 * This method checks if the current PersonalOrder instance contains any order lines.
	 * If it does then it creates a DAO object (PersonalOrderDB) and calls the insert method on
	 * it to store the personal order in the database, thereby linking it to the current table order.
	 * 
	 * @throws DataAccessException if an error occurs while trying to insert the personal order into the database
	 */
	public void finishPersonalOrder() throws DataAccessException
	{
		try 
		{
	        // Instantiates the DAO that handles database operations related to personal orders
	        PersonalOrderImpl personalOrderDB = new PersonalOrderDB();
	        
	        // Checks if the personal order contains at least one item before inserting it
	        if (!personalOrder.getPersonalOrderLines().isEmpty())
			{
	            // Inserts the personal order into the database and links it to the current table order's ID
				personalOrderDB.insertPersonalOrder(personalOrder, tableOrder.getTableOrderId());
			}
		}
		
		catch (DataAccessException exception) 
		{
			// If an SQL error occurs a exception is thrown with the specified details
			throw new DataAccessException("Unable to insert Personalorder to PersonalOrderDB" , exception);
		}
	}
	
	
	/**
	 * Clears all MenuItem lines from the personal order's list.
	 */
	public void clearMenuItemList()
	{
		personalOrder.clearMenuItemLine();
	}
	
	
	/**
	 * This method calls PersonalOrderDB and retrieves a list of all PersonalOrders 
	 * that are linked to the specified tableOrder.
	 *
	 * @param tableOrderId the ID of the table order to retrieve personal orders from
	 * @return a list of PersonalOrders associated with the specified table order
     * @throws DataAccessException if a database access issue occurs somewhere at the DAO level
     * @throws SQLException if an SQL query execution fails
	 */
	public List<PersonalOrder> findPersonalOrdersBytableOrderId(int TableOrderId) throws SQLException, DataAccessException
	{
	    // Creates an empty list that will hold the result of retrieved PersonalOrder objects
	    List<PersonalOrder> returnList = new ArrayList<PersonalOrder>();
	    
	    // Instantiates the DAO implementation to access PersonalOrder data from the database
	    PersonalOrderImpl personalOrderDB = new PersonalOrderDB();
	    
	    // Retrieves all personal orders by the given table order ID and adds them to the result list
	    returnList.addAll(personalOrderDB.findPersonalOrdersBytableOrderId(TableOrderId));
	    
	    // Returns the list of PersonalOrder objects
	    return returnList;
	}
}
