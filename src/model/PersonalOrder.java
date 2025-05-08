package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Line Bertelsen
 * @date 08.05.25 10.14
 */

public class PersonalOrder
{
	//Attributes/Instance variables
	private int PersonalOrderId;
	private double totalPersonalOrderPrice;
	private String customerName;
	private int customerAge;
	
	//Lists
	private List<Discount> listOfAllDiscounts;
	private List<PersonalOrderLine> personalOrderLineList;
	private List<SelectionOption> listOfSelectionOption;
	private List<AddOnOption> listOfAddOnOptions;
	
	//TODO: TableOrder tableOrder;
	//TODO: MainCourse mainCourse;
	//TODO: MenuItem menuItem
	
	//Constructor of PersonalOrder
	public PersonalOrder(TableOrder tableOrder) 
	{
		
		listOfAllDiscounts = new ArrayList<>();
		personalOrderLineList = new ArrayList<>();
		listOfSelectionOption = new ArrayList<>();
		listOfAddOnOptions = new ArrayList<>();
		//TODO: this.tableOrder = tablerOrder
	}
	
	
	/**
	 * The get method returns the value of the variable customerName
	 * @return customerName
	 */
	public String getCustomerName()
	{
		return customerName;
	}
	
	
	/**
	 * The set method takes a parameter customerName and assigns it to the this.customerName variable. 
	 * @param customerName
	 */
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	
	/**
	 * The get method returns the value of the variable customerAge
	 * @return customerAge
	 */
	public int getCustomerAge() 
	{
		return customerAge;
	}

	
	/**
	 * The set method takes a parameter customerAge and assigns it to the this.customerAge variable.
	 * @param customerAge
	 */
	public void setCustomerAge(int customerAge) 
	{
		this.customerAge = customerAge;
	}
	
	
	/**
	 * The addAllDiscount method uses a foreach-loop and the method addDiscount  
	 * to add all discounts objects to the list listOfAllDiscounts
	 */
	public void addAllDiscounts(List<Discount> listOfAllDiscounts)
	{
		for(Discount discount : listOfAllDiscounts)
		{
			addDiscount(discount);
		}
	}
	
	/**
	 * The method addDiscount add one discount object to the list listOfAllDiscounts
	 * @param discount
	 */
	public void addDiscount(Discount discount)
	{
		this.listOfAllDiscounts.add(discount);
	}
	
	
	/**
	 * The method removeDiscount removes a discount object from the list listOfAllDiscounts
	 * @param discount
	 */
	public void removeDiscount(Discount discount)
	{
		this.personalOrderLineList.remove(discount);
	}
	
	
	/**
	 * The clearDiscont method uses a foreach-loop and the method removeDiscount  
	 * to remove all discounts objects to the list listOfAllDiscounts
	 */
	public void clearDiscont()
	{
		for(Discount discount : listOfAllDiscounts)
		{
			removeDiscount(discount);
		}
	}
	
	
	/**
	 * The method addMainCourseLine, adds a main course, a list of listOfAddOnOptions and a list of listOfSelectionOption to personalOrder
	 * 
	 * @param mainCourse
	 * @param listOfAddOnOptions
	 * @param listOfSelectionOption
	 */
	public void addMainCourseLine(MainCourse mainCourse, List<AddOnOption> listOfAddOnOptions, List<SelectionOption> listOfSelectionOption)
	{
		//Add a single mainCourse to the MainCourseLine
		this.mainCourse = mainCourse;
		
		//Adds list of addOnOptions to the list listOfAddOnOptions
		this.listOfAddOnOptions.addAll(listOfAddOnOptions);
		
		//Adds list of addOnOptions to the list listOfAddOnOptions
		this.listOfSelectionOption.addAll(listOfSelectionOption);
	}
	
	public void addMenuItemLine(MenuItem menuItem)
	{
		this.menuItem = menuItem;
	}
	
	/**
	 * The clearDiscont method uses a foreach-loop and the method removeDiscount  
	 * to remove all discounts objects to the list listOfAllDiscounts
	 */
	public void clearMenuItemLine()
	{
		for(MenuItem menuItem : menuItemLine)
		{
			menuItemLine.remove(menuItem);
		}
	}
}
