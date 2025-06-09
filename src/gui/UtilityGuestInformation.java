package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.PersonalOrderController;
import database.DataAccessException;
import model.AddOnOption;
import model.DipsAndSauces;
import model.Discount;
import model.Drink;
import model.MainCourse;
import model.MenuCard;
import model.MenuItem;
import model.PotatoDish;
import model.SelectionOption;
import model.SelfServiceBar;
import model.SideDish;
import model.Table;
import model.TableOrder;


/**
 * UtilityGuestInformation is a singleton class that is responsible for managing and
 * storing guest-specific information throughout the ordering process.
 * It acts as a bridge between the GUI and the business logic, particularly in relation to
 * tables, table orders, discounts, and menu selections.
 * 
 * This class holds information such as the current table, table order,
 * selected main course, available menu cards, and discounts. It delegates
 * business logic to the PersonalOrderController.
 * 
 * This class is instantiated only once during a guest session and should be
 * accessed via getInstance() as it is using a singleton pattern.
 * 
 * 
 * @author Christoffer Søndergaard & Lumière Schack
 * @version: 09/06/2025 - 06:50
 */
public class UtilityGuestInformation
{
	// Used by the class for the singleton pattern that it utilizes
	private static UtilityGuestInformation instance;

	private PersonalOrderController personalOrderController;
	private Table table;
	private TableOrder tableOrder;
	private List<Discount> listOfDiscounts;
	private List<MenuCard> listOfMenuCards;
	private MainCourse mainCourse;

	
	/**
	 * Private constructor as this is using a singleton pattern.
	 * Initializes the personalOrderController.
	 */
	private UtilityGuestInformation()
	{
		personalOrderController = new PersonalOrderController();
	}


	/**
	 * Returns the singleton instance of UtilityGuestInformation.
	 * If no instance exists, a new one is created.
	 * 
	 * @return the single instance of UtilityGuestInformation
	 */
	public static UtilityGuestInformation getInstance()
	{
		// If the instance of the UtilityGuestInformation object is currently set to null then execute this section
		if (instance == null)
		{
			// Instantiates the UtilityGuestInformation object and stores it within the instance variable
			instance = new UtilityGuestInformation();
		}
		
		// Returns the instance of the UtilityGuestInformation object
		return instance;
	}
	
	
	/**
	 * Returns the PersonalOrderController.
	 * 
	 * @return PersonalOrderController instance
	 */
	public PersonalOrderController getPersonalOrderController()
	{
		return personalOrderController;
	}
	
	
	/**
	 * Retrieves and stores the Table and TableOrder based on the supplied table number
	 * and restaurant code.
	 * 
	 * @param tableNumber the 4-digit number identifying the table
	 * @param restaurantCode the 3-digit code identifying the restaurant
	 * @return the Table object that was retrieved
     * @throws DataAccessException if a database access issue occurs
     * @throws SQLException if an SQL query execution fails
	 */
	public Table enterTableCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException
	{
		// Looks up a table in the database using the specified table number and restaurant code, and assigns the found table to the table variable
		table = personalOrderController.enterTableCode(tableNumber, restaurantCode);
		
		// Returns the TableOrder instance that is associated with this particular Table instance and assigns it to the tableOrder variable
		tableOrder = table.getCurrentTableOrder();
		
		// Returns the table object with a matching code or null if no table was found
		return table;
	}
	
	
	/**
	 * Sets the customer's name and the age and stores it within their PersonalOrder object
	 * and then returns a list of discounts.
	 * 
	 * @param customerName the name of the customer
	 * @param customerAge the age of the customer
	 * @return list a list of Discount objects to associate with the PersonalOrder
	 */
	public List<Discount> enterNameAndAge(String customerName, int customerAge)
	{
		// Sets the customerName and customerAge attributes of the PersonalOrder object to the specified values and retrieves a
		// list of Discounts
		listOfDiscounts = personalOrderController.enterNameAndAge(customerName, customerAge);
		
		return listOfDiscounts;
	}
	
	
	/**
	 * Processes selected discounts and returns the list of available menu cards.
	 * 
	 * @param listOfDiscounts the selected discounts
	 * @return list of MenuCard objects tailored to the selected discounts
     * @throws DataAccessException if a database access issue occurs
     * @throws SQLException if an SQL query execution fails
	 */
	public List<MenuCard> enterDiscounts(List<Discount> listOfDiscounts) throws DataAccessException, SQLException
	{
		// Adds a list of Discount objects to the current PersonalOrder (currently not being applied, that will be done in another use case)
		// and retrieves all MenuCard objects available for the restaurant associated with the PersonalOrder's selected table 
		listOfMenuCards = personalOrderController.enterDiscounts(listOfDiscounts);
		
		return listOfMenuCards;
	}
	
	
	/**
	 * Returns the menu card identified as the adult menu.
	 * 
	 * @return MenuCard the MenuCard object for adults
	 * @throws NullPointerException if no adult menu is found
	 */
	public MenuCard getAdultMenu()
	{
		// Creates a MenuCard object and initializes it as a null value
		MenuCard adultMenuCard = null;
		
		// Uses a for-each loop to iterate through the list MenuCard objects
		for (MenuCard menuCard: listOfMenuCards)
		{
			// If a MenuCard object's name contains the word "Voksen" then execute this section
			if (menuCard.getName().contains("Voksen"))
			{
				// Sets the MenuCard that was found to be the menu card used for adults
				adultMenuCard = menuCard;
			}
		}
		
		// If there were no adult menu card found within the list of MenuCard objects then execute this section
		if (adultMenuCard == null)
		{
			throw new NullPointerException("No adult menu was found");
		}
		
		// Returns the MenuCard object that was found or null if none were found
		return adultMenuCard;
	}
	
	
	/**
	 * Determines whether the current time is considered lunch time according to Bone's
	 * current business model.
	 * 
	 * @return true if current time is before or at 16:00, false otherwise
	 */
	public boolean isLunchTime()
	{
		// Creates a boolean variable called isLunch and initializes it's value to be false
		boolean isLunch = false;
		
		// If the TimeStamp for when the first guest arrived at the table is earlier than 16:00 then execute this section
		if (16 < (tableOrder.getTimeOfArrival().getHour()))
		{
			// Sets the isLunch variable to true to indicate this is within lunch hours
			isLunch = true;
		}
		
		return isLunch;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class MainCourse
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for MainCourses within
	 * @return list the list containing all of the found MainCourse objects
	 */
	public List<MainCourse> getMainCourses(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass MainCourse
		List<MainCourse> listOfMainCourses = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects
		// and store them within the listOfMenuItems variable
		List<MenuItem> listOfMenuItems = menuCard.getAvailableMenuItems();

		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: listOfMenuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type MainCourse then execute this section
			if (menuItem instanceof MainCourse)
			{
				// Casts the MenuItem object to a MainCourse object and adds the converted MainCourse object to the list
				listOfMainCourses.add((MainCourse)menuItem);
			}
		}
		
		return listOfMainCourses;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class PotatoDish
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for PotatoDish within
	 * @return list the list containing all of the found PotatoDish objects
	 */
	public List<PotatoDish> getPotatoDishes(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass PotatoDish
		List<PotatoDish> listOfPotatoDishes = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects 
		// and store them within the listOfMenuItems variable
		List<MenuItem> listOfMenuItems = menuCard.getAvailableMenuItems();
		
		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: listOfMenuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type PotatoDish then execute this section
			if (menuItem instanceof PotatoDish)
			{
				// Casts the MenuItem object to a PotatoDish object and adds the converted PotatoDish object to the list
				listOfPotatoDishes.add((PotatoDish)menuItem);
			}
		}
		
		return listOfPotatoDishes;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class Drink
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for Drink within
	 * @return list the list containing all of the found Drink objects
	 */
	public List<MenuItem> getDrinks(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass Drink
		List<MenuItem> listOfDrinks = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects
		// and store them within the listOfMenuItems variable
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		
		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: menuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type Drink then execute this section
			if (menuItem instanceof Drink)
			{
				// Casts the MenuItem object to a Drink object and adds the converted Drink object to the list
				listOfDrinks.add((Drink)menuItem);
			}
		}
		
		return listOfDrinks;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class SideDish
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for SideDish within
	 * @return list the list containing all of the found SideDish objects
	 */
	public List<MenuItem> getSideDishes(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass SideDish
		List<MenuItem> listOfSideDishes = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects
		// and store them within the listOfMenuItems variable
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		
		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: menuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type SideDish then execute this section
			if (menuItem instanceof SideDish)
			{
				// Casts the MenuItem object to a SideDish object and adds the converted SideDish object to the list
				listOfSideDishes.add((SideDish)menuItem);
			}
		}
		
		return listOfSideDishes;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class DipsAndSauces
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for DipsAndSauces within
	 * @return list the list containing all of the found DipsAndSauces objects
	 */
	public List<MenuItem> getDipsAndSauces(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass DipsAndSauce
		List<MenuItem> listOfDipsAndSauces = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects
		// and store them within the listOfMenuItems variable
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		
		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: menuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type DipsAndSauces then execute this section
			if (menuItem instanceof DipsAndSauces)
			{
				// Casts the MenuItem object to a DipsAndSauces object and adds the converted DipsAndSauces object to the list
				listOfDipsAndSauces.add((DipsAndSauces)menuItem);
			}
		}
		
		return listOfDipsAndSauces;
	}
	
	
	/**
	 * Retrieves a list containing all of the MenuItems that are of the subclass / child class SelfServiceBar
	 * from the specified MenuCard object, if the availabilityTracker object determines that the MenuItem
	 * object is currently available on the MenuCard.
	 * 
	 * @param menuCard the MenuCard object to search for SelfServiceBar within
	 * @return list the list containing all of the found SelfServiceBar objects
	 */
	public List<MenuItem> getSelfServiceBars(MenuCard menuCard)
	{
		// Creates an array list that contains MenuItem objects of the subclass SelfServiceBar
		List<MenuItem> listOfSelfServiceBars = new ArrayList<>();
		
		// Finds all of the available menuItem objects in the MenuCard object's list of availabilitytrackers to only get the currently enabled MenuItem objects
		// and store them within the listOfMenuItems variable
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		
		// Uses a for-each loop to iterate through the list of MenuItem objects
		for (MenuItem menuItem: menuItems)
		{
			// If the MenuItem object that we are currently looking at is an instance of the subclass type SelfServiceBar then execute this section
			if (menuItem instanceof SelfServiceBar)
			{
				// Casts the MenuItem object to a SelfServiceBar object and adds the converted SelfServiceBar object to the list
				listOfSelfServiceBars.add((SelfServiceBar)menuItem);
			}
		}
		
		return listOfSelfServiceBars;
	}
	
	
	/**
	 * Adds the specified MainCourse object to the personalOrder through the PersonalOrderController 
	 * and stores the MainCourse object reference in the mainCourse instance variable.
	 * 
	 * @param mainCourse the the specified MainCourse object to add to the PersonalOrder
	 */
	public void enterMainCourse(MainCourse mainCourse)
	{
		personalOrderController.enterMainCourse(mainCourse);
		
		this.mainCourse = mainCourse;
	}
	
	
	/**
	 * Returns the MainCourse object that .
	 * 
	 * @return MainCourse that was stored
	 */
	public MainCourse getMainCourse()
	{
		return mainCourse;
	}
	
	
	/**
	 * Stores the options that the guest / customer selected when they customized their main course,
	 * including their chosen type of potato dish, main course add-ons as well as selection options.
	 * 
	 * @param potatoDish the selected potato dish
	 * @param listOfAddOnOptionChoices the selected add-on options
	 * @param listOfSelectionOption the selected choice options
	 */
	public void enterMainCourseOptions(MenuItem potatoDish, List<AddOnOption> listOfAddOnOptionChoices, List<SelectionOption> listOfSelectionOption)
	{
		personalOrderController.enterMainCourseOptions(potatoDish, listOfAddOnOptionChoices, listOfSelectionOption);
	}
	
	
	/**
	 * Adds the specified SideOrder to the customer's PersonalOrder through the PersonalOrderController
	 * a SideOrder can be a DipsAndSauces, Drink, SelfServiceBar, SideDish or any other MenuItem child
	 * class, aside from a MainCourse.
	 * 
	 * @param menuItem the specified MenuItem sub class of the above mentioned types
	 */
	public void enterSideOrder(MenuItem menuItem)
	{
		personalOrderController.enterSideOrder(menuItem);
	}
	
	
	/**
	 * Finalizes the customer's PersonalOrder order by inserting it into the database.
	 * Thereafter clears the list of MenuItem objects.
	 * 
	 * Catches and prints any DataAccessExceptions that may occur.
	 */
	public void finishPersonalOrder()
	{
		try
		{
			// Finalizes the current personal order by inserting it into the database and linking it to the TableOrder object
			// Also checks if the current PersonalOrder instance contains any order lines
			personalOrderController.finishPersonalOrder();
			
			// Clears the list of menu items that are currently added to the PersonalOrder object
			personalOrderController.clearMenuItemList();
		}
		
		catch (DataAccessException exception)
		{
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * Returns the TableOrder object that the customer is associating their PersonalOrder with.
	 * 
	 * @return TableOrder the TableOrder object stored in the class' tableOrder attribute 
	 */
	public TableOrder getTableOrder()
	{
		return tableOrder;
	}
	
	
	/**
	 * Clears the list of selected MenuItems from the PersonalOrder object's list of menu items.
	 */
	public void clearOrder()
	{
		personalOrderController.clearMenuItemList();
	}
}
