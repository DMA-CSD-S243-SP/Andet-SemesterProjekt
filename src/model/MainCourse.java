// Packages
package model;

// Imports
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a MainCourse menu item that can be ordered at a bone's restaurant.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and
 * includes information on whether the item is a MainCourse, along with a
 * description, a lunch- and evening price and a list of their options.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 05/06/2025 - 14:03
 */
public class MainCourse extends MenuItem
{
	//Attributes/Instance variables
	private String introductionDescription;
	private double lunchPrice;
	private double eveningPrice;
	
	//Lists
	private List<MultipleChoiceMenu> listOfMultipleChoiceMenus;
	private List<AddOnOption> listOfAddOnOptions;

	/**
	 * Constructs a new MainCourse instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * Unlike other MenuItems the MainCourse also contains SelectionOptions and MultipleChoiceMenu
	 * objects to help manage the add-ons that customers can set for their main courses.
	 * 
	 * @param introductionDescription 	- a short description of the item
	 * @param lunchPrice              	- the price during lunchtime
	 * @param eveningPrice            	- the price during evening time
	 * @param menuItemId 				- the unique id of the MenuItem
	 * @param preparationTime 			- the time in seconds it takes to prepare this MenuItem
	 * @param name 						- the name of the MenuItem
	 * @param description 				- the description of the MenuItem
	 * @param isMadeByKitchenStaff		- whether this MenuItem is something the kitchen should handle or not
	 */
	public MainCourse(String introductionDescription, double lunchPrice, double eveningPrice, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff) 
	{
		// Calls the super-class / parent class constructor (MenuItem) to initialize the inherited attributes
		// which makes it so everything defined in the MenuItem class is initialized before this class' attribute values are set.
		super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		
		//Set the variables for this instance of mainCourse 
		this.introductionDescription = introductionDescription;
		this.lunchPrice = lunchPrice;
		this.eveningPrice = eveningPrice;
		
		//Instantiates the list of multiple choices and the list of add on options
		listOfMultipleChoiceMenus = new ArrayList<MultipleChoiceMenu>();
		listOfAddOnOptions = new ArrayList<AddOnOption>();
	}

	
	/**
	 * The get method returns the value of the variable introductionDescription 
	 * 
	 * The introductionDescription, is not the same as description in the abstract class MenuItem
	 * description is a short overview of the mainCourse, but when the customer choose an mainCourse
	 * a frame/window will appear showing another description which is this introductionDescription.  
	 * 
	 * @return introductionDescription
	 */
	public String getIntroductionDescription() 
	{
		return introductionDescription;
	}
	

	/**
	 * The set method takes a parameter introductionDescription and assigns it to the 
	 * this.introductionDescription variable
	 * 
	 * @param introductionDescription the new this.introductionDescription to be set
	 */
	public void setIntroductionDescription(String introductionDescription) 
	{
		this.introductionDescription = introductionDescription;
	}

	
	/**
	 * The get method returns the value of the variable lunchPrice
	 * 
	 * Every Bone's main course has a lunch price, and this method
	 * access the lunch price of the instance of main course
	 * 
	 * @return lunchPrice
	 */
	public double getLunchPrice() 
	{
		return lunchPrice;
	}

	
	/**
	 * The set method takes a parameter lunchPrice and assigns it to the 
	 * this.lunchPrice variable
	 * 
	 * @param newLunchPrice the new lunchPrice to be set
	 */
	public void setLunchPrice(double lunchPrice) 
	{
		this.lunchPrice = lunchPrice;
	}

	
	/**
	 * The get method returns the value of the variable eveningPrice
	 * 
	 * Every Bone's main course has a evening price, and this method
	 * access the evening price of the instance of main course
	 * 
	 * @return eveningPrice
	 */
	public double getEveningPrice() 
	{
		return eveningPrice;
	}

	
	/**
	 * The set method takes a parameter eveningPrice and assigns it to the 
	 * this.eveningPrice variable
	 * 
	 * @param eveningPrice the new eveningPrice to be set
	 */
	public void setEveningPrice(double eveningPrice) 
	{
		this.eveningPrice = eveningPrice;
	}

	
	/**
	 * adds a multipleChoiceMenu object to the list of MultipleChoiceMenu
	 * 
	 * @param multipleChoiceMenu - the multipleChoiceMenu to be added to the
	 *                           collection
	 */
	public void addMultipleChoiceMenu(MultipleChoiceMenu multipleChoiceMenu) 
	{
		listOfMultipleChoiceMenus.add(multipleChoiceMenu);
	}

	
	/**
	 * removes a multipleChoiceMenu object from the list of MultipleChoiceMenus
	 * 
	 * @param multipleChoiceMenu
	 */
	public void removeMultipleChoiceMenu(MultipleChoiceMenu multipleChoiceMenu) 
	{
		listOfMultipleChoiceMenus.remove(multipleChoiceMenu);
	}

	
	/**
	 * gets the list of MultipleChoicMenus
	 * 
	 * @return a list of all the objects inside ListOfMultipleChoiceMenus
	 */
	public List<MultipleChoiceMenu> getListOfMultipleChoiceMenu() 
	{
		//Create an empty instance of multipleChoiceMenu called returnList that holds
		List<MultipleChoiceMenu> returnList = new ArrayList<MultipleChoiceMenu>();
		
		//Add all the instance of MultipleChoiceMenu into returnList variable
		returnList.addAll(listOfMultipleChoiceMenus);
		
		//returns the list of MultipleChoiceMenu
		return returnList;
	}

	
	/**
	 * adds a AddOnOption object to the list of AddOnOptions
	 * 
	 * @param addOnOption - the AddOnOption to be added to the collection
	 */
	public void addAddOnOption(AddOnOption addOnOption) 
	{
		listOfAddOnOptions.add(addOnOption);
	}
	

	/**
	 * removes a AddOnOption object from the list of AddOnOptions
	 * 
	 * @param addOnOption - the AddOnOption to be removed from the collection
	 */
	public void removeAddOnOption(AddOnOption addOnOption) 
	{
		listOfAddOnOptions.remove(addOnOption);
	}

	
	/**
	 * @return a list of all the objects inside ListOfAddOnOptions
	 */
	public List<AddOnOption> getListOfAddOnOption() 
	{
		//Create an empty instance of AddOnOption called returnList that holds
		List<AddOnOption> returnList = new ArrayList<AddOnOption>();
		
		//Add all the instance of AddOnOptions into returnList variable
		returnList.addAll(listOfAddOnOptions);
		
		//returns the list of addOnOptions
		return returnList;
	}
}