package model;

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
 * @author Anders Trankjær
 * @version 09-05-2025 - 10:30
 */
public class MainCourse extends MenuItem
{
	private String introductionDescription;
	private double lunchPrice;
	private double eveningPrice;
	private List<MultipleChoiceMenu> listOfMultipleChoiceMenus;
	private List<AddOnOption> listOfAddOnOptions;

	/**
	 * the constructor for this subclass calls the constructor for the superclass to
	 * initialize it
	 * 
	 * @param introductionDescription - a short description of the item
	 * @param lunchPrice              - the price during lunchtime
	 * @param eveningPrice            - the price during evening time
	 */
	public MainCourse(String introductionDescription, double lunchPrice, double eveningPrice) 
	{
		super();

		this.introductionDescription = introductionDescription;
		this.lunchPrice = lunchPrice;
		this.eveningPrice = eveningPrice;
		listOfMultipleChoiceMenus = new ArrayList<MultipleChoiceMenu>();
		listOfAddOnOptions = new ArrayList<AddOnOption>();
	}

	/**
	 * @return introductionDescription
	 */
	public String getIntroductionDescription() 
	{
		return introductionDescription;
	}

	/**
	 * @param newIntroductionDescription the new introductiondescription to be set
	 */
	public void setIntroductionDescription(String newIntroductionDescription) 
	{
		this.introductionDescription = newIntroductionDescription;
	}

	/**
	 * @return lunchPrice
	 */
	public double getLunchPrice() 
	{
		return lunchPrice;
	}

	/**
	 * @param newLunchPrice the new lunchPrice to be set
	 */
	public void setLunchPrice(double newLunchPrice) 
	{
		this.lunchPrice = newLunchPrice;
	}

	/**
	 * @return eveningPrice
	 */
	public double getEveningPrice() 
	{
		return eveningPrice;
	}

	/**
	 * @param newEveningPrice the new eveningPrice to be set
	 */
	public void setEveningPrice(double newEveningPrice) 
	{
		this.eveningPrice = newEveningPrice;
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
	 * @return a list of all the objects inside ListOfMultipleChoiceMenus
	 */
	public List<MultipleChoiceMenu> getListOfMultipleChoiceMenu() 
	{
		List<MultipleChoiceMenu> returnList = new ArrayList<MultipleChoiceMenu>();
		returnList.addAll(listOfMultipleChoiceMenus);
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
		List<AddOnOption> returnList = new ArrayList<AddOnOption>();
		returnList.addAll(listOfAddOnOptions);
		return returnList;
	}
}
