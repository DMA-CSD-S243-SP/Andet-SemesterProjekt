// Packages
package model;

// Imports
import java.util.ArrayList;
import java.util.List;


/**
 * @author Line Bertelsen
 * @date 08.05.25 10.14
 */
public class PersonalOrder
{
	/*
//	private List<SelectionOption> listOfSelectionOption;
//	private List<AddOnOption> listOfAddOnOptions;
	
	//Line: Attributes i've added
	private TableOrder tableOrder;
	private MainCourse mainCourse;
	private MenuItem menuItem;
*/
	
	
	
	//Attributes/Instance variables
	private int PersonalOrderId;
	private int customerAge;
	private String customerName;
	private double totalPersonalOrderPrice;
	
	//Lists
	private List<Discount> listOfAllDiscounts;
	private List<PersonalOrderLine> personalOrderLineList;
	
	//Constructor of PersonalOrder
	public PersonalOrder(TableOrder tableOrder) 
	{
		// Instantiates the list of personal order lines and the list of discounts
		this.personalOrderLineList = new ArrayList<>();
		this.listOfAllDiscounts = new ArrayList<>();
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
		this.listOfAllDiscounts.remove(discount);
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
		// Dummy Code Block
		
		
/*		//Add a single mainCourse to the MainCourseLine
		this.mainCourse = mainCourse;
		
		//Adds list of addOnOptions to the list listOfAddOnOptions
		this.listOfAddOnOptions.addAll(listOfAddOnOptions);
		
		//Adds list of addOnOptions to the list listOfAddOnOptions
		this.listOfSelectionOption.addAll(listOfSelectionOption);
		
		//PersonalOrderLine.addOnOption();
		//PersonalOrderLine.addSelectionOption();
 */
	}
	
	
	/**
	 * Adds a all menuItems to the personal order
	 * Like potatoDish and sideOrderItem
	 * 
	 * @param menuItem
	 */
	public void addMenuItemLine(MenuItem menuItem)
	{
		// Dummy Code Block
		
		
		
/*
 * 		this.personalOrderLineList.add(menuItem);
 */
	}
	
	
	/**
	 * The clearDiscont method uses a foreach-loop and the method removeDiscount  
	 * to remove all discounts objects to the list listOfAllDiscounts
	 */
	public void clearMenuItemLine()
	{
		// Dummy Code Block
		
		
		
/*		for(MenuItem menuItem : menuItemLine)
		{
			menuItemLine.remove(menuItem);
		}
*/
	}
}
