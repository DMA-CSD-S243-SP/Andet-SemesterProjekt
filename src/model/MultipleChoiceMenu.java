package model;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a dropdown menu within the maincourse object, it holds selection options
 * which are the different options within the dropdownmenu.
 * 
 * @author Anders Trankjær
 * @version 09-05-2025 - 11:35
 */
public class MultipleChoiceMenu
{
	private String selectionDescription;
	private List<SelectionOption> listOfSelectionOptions;
	
	/**
	 * @param selectionDescription - a short decription of the dropdown menu and what you are choosing.
	 */
	public MultipleChoiceMenu(String selectionDescription)
	{
		this.selectionDescription = selectionDescription;
		listOfSelectionOptions = new ArrayList<SelectionOption>();
	}
	
	/**
	 * @return selectionDescription
	 */
	public String getSelectionDescription()
	{
		return selectionDescription;
	}
	
	/**
	 * @param newSelectionDescription the new selectiondescription to be set
	 */
	public void setSelectionDescription(String newSelectionDescription)
	{
		this.selectionDescription = newSelectionDescription;
	}
	
	/**
	 * adds a SelectionOption object to the List of SelectionOptions
	 * 
	 * @param selectionOption - the object to be added to the collection
	 */
	public void addSelectionOption(SelectionOption selectionOption)
	{
		listOfSelectionOptions.add(selectionOption);
	}
	
	/**
	 * removes a SelectionOption object from the list of SelectionOptions
	 * 
	 * @param selectionOption - the object to be removed from the collection
	 */
	public void removeSelectionOption(SelectionOption selectionOption)
	{
		listOfSelectionOptions.remove(selectionOption);
	}
	
	/**
	 * @return a list of all the objects within listOfSelectionOptions
	 */
	public List<SelectionOption> getListOfSelectionOptions()
	{
		List<SelectionOption> returnList = new ArrayList<SelectionOption>();
		returnList.addAll(listOfSelectionOptions);
		return returnList;
	}
}
