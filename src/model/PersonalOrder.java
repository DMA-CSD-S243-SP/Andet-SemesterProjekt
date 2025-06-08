// Packages
package model;

// Imports
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a guest’s individual and personal order, at a Bone’s restaurant.
 * 
 * A PersonalOrder of one or usually multiple PersonalOrderLine instances, which
 * each represents a specific MenuItem that the guest has ordered, along with
 * the quantity of said MenuItem instance, possible customized options, and
 * preparation status for the specific PersonalOrderLine instance.
 * 
 * The PersonalOrder class is used to contain all of a single guest's food and
 * drink MenuItems in seperation from other guests' at the table. The total cost
 * of a PersonalOrder is calculated by summing up each PersonalOrderLine
 * instance cost.
 * 
 * Each PersonalOrder instance are associated with a TableOrder, which holds all
 * of the PersonalOrders for the guests at the specific table.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 07-06-2025 - 20:19
 */
public class PersonalOrder
{
	// Attributes/Instance variables
	private int PersonalOrderId;
	private int customerAge;
	private String customerName;

	// Lists
	private List<Discount> listOfAllDiscounts;
	private List<PersonalOrderLine> personalOrderLineList;

	
	/**
	 * Constructs a new PersonalOrder instance that is associated with the specified 
	 * TableOrder object.
	 * 
	 * When a PersonalOrder is created, it initializes an empty list of PersonalOrderLine 
	 * objects and an empty list of applied Discount objects.
	 * These lists are used to store the guest's selected menu items and any of the possible
	 * applicable discounts.
	 * 
	 * @param tableOrder the TableOrder that this PersonalOrder is part of
	 */
	public PersonalOrder(TableOrder tableOrder)
	{
		// Instantiates the list of personal order lines and the list of discounts
		this.personalOrderLineList = new ArrayList<>();
		this.listOfAllDiscounts = new ArrayList<>();
	}
	
	
	/**
	 * The get method returns the value of the variable customerAge
	 * 
	 * @return customerAge the age of the customer
	 */
	public int getCustomerAge()
	{
		return customerAge;
	}

	
	/**
	 * The set method takes a parameter customerAge and assigns it to the
	 * this.customerAge variable.
	 * 
	 * @param customerAge the age of the customer
	 */
	public void setCustomerAge(int customerAge)
	{
		this.customerAge = customerAge;
	}

	
	/**
	 * The get method returns the value of the variable customerName
	 * 
	 * @return customerName the name of the customer
	 */
	public String getCustomerName()
	{
		return customerName;
	}

	
	/**
	 * The set method takes a parameter customerName and assigns it to the
	 * this.customerName variable.
	 * 
	 * @param customerName the name of the customer
	 */
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	
	/**
	 * Adds a list of Discount objects to this PersonalOrder instance.
	 * This method uses the addDiscount method to add each discount to
	 * the listOfAllDiscounts individually.
	 *
	 * @param listOfAllDiscounts the list of Discount objects to add
	 */
	public void addAllDiscounts(List<Discount> listOfAllDiscounts)
	{
		// Uses a for-each loop to iterate through all the Discount objects in the listOfAllDiscounts
		for (Discount discount : listOfAllDiscounts)
		{
			// Adds a Discount object to the list of Discount objects
			addDiscount(discount);
		}
	}

	
	/**
	 * The method addDiscount add one discount object to the list listOfAllDiscounts
	 * 
	 * @param discount the discount object to add to the list of discounts
	 */
	public void addDiscount(Discount discount)
	{
		this.listOfAllDiscounts.add(discount);
	}

	
	/**
	 * The method removeDiscount removes a discount object from the list
	 * listOfAllDiscounts
	 * 
	 * @param discount the discount object to remove from the list of discounts
	 */
	public void removeDiscount(Discount discount)
	{
		this.listOfAllDiscounts.remove(discount);
	}

	
	/**
	 * Removes all discounts that are associated with this PersonalOrder instance.
	 *
	 * This clears the listOfAllDiscounts by individually removing each Discount
	 * object individually from the list of discounts.
	 */
	public void clearDisconts()
	{
		// Uses a for-each loop to iterate through all the Discount objects in the listOfAllDiscounts
		for (Discount discount : listOfAllDiscounts)
		{
			// Removes a Discount object from the list of Discount objects
			removeDiscount(discount);
		}
	}

	
	/**
	 * The method addMainCourseLine, adds a main course, a list of
	 * listOfAddOnOptions and a list of listOfSelectionOption to personalOrder
	 * 
	 * @param mainCourse the MainCourse object to associate this OrderLine with
	 * @param listOfAddOnOptions the list of AddOn options for this MainCourse
	 * @param listOfSelectionOption the list of Selection options for this MainCourse
	 */
	public void addMainCourseLine(MainCourse mainCourse, List<AddOnOption> listOfAddOnOptions, List<SelectionOption> listOfSelectionOption)
	{
		// Creates a PersonalOrderLine object and stores it within the mainCourseLine variable
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourse);

		// Uses a for-each loop to iterate through the supplied listOfAddonOptions
		for (AddOnOption addonOption: listOfAddOnOptions)
		{
			// Adds the addOnOption to the mainCourseLine variable's PersonalOrderLine instance
			mainCourseLine.addAddOnOption(addonOption);
		}
		
		// Uses a for-each loop to iterate through the supplied listOfSelectionOption
		for (SelectionOption selectionOption: listOfSelectionOption)
		{
			// Adds the selection option to the mainCourseLine variable's PersonalOrderLine instance
			mainCourseLine.addSelectionOption(selectionOption);
		}
		
		// Adds the PersonalOrderLine object containing the mainCourseLine information to the PersonalOrder
		addPersonalOrderLine(mainCourseLine);
	}

	
	/**
	 * Adds a PersonalOrderLine object to the list of PersonalOrderLines
	 * 
	 * @param PersonalOrderLine the PersonalOrderLine object to add to the list of PersonalOrderLine objects
	 */
	public void addPersonalOrderLine(PersonalOrderLine personalOrderLine)
	{
		this.personalOrderLineList.add(personalOrderLine);
	}

	
	/**
	 * The clearMenuItemLine method simply instantiates a new and empty ArrayList 
	 * to clear the contents of the PersonalOrderLineList 
	 */
	public void clearMenuItemLine()
	{
		personalOrderLineList = new ArrayList<>();
	}
	

	/**
	 * Calculates and returns the total price of all the PersonalOrderLines that are
	 * associated with this PersonalOrder instance.
	 * 
	 * This method iterates through every PersonalOrderLine that is contained within
	 * this Personal Order instance's personalOrderLineList, and calculates the sum
	 * of them all. The total is used to determine how much each guest's personal
	 * order costs, and will also be used to summarize the total cost of a
	 * TableOrder instance.
	 *
	 * @return the total summed price for this guest’s personal order
	 */
	public double getTotalPersonalOrderLunchPrice()
	{
		// Creates a variable called totalPrice and sets its value to 0.00
		double totalPrice = 0.00;

		// Guests should only pay for premium potatoes once. So while it sums the price,
		// it also keeps track of whether a premium potato has been found.
		boolean isPremiumPotato = false;

		// Uses a for-each loop to iterate through the list of personal order lines
		for (PersonalOrderLine personalOrderLine : personalOrderLineList)
		{
			// Adds the current PersonalOrderLine instances' price to the 
			// current value of the totalPrice
			totalPrice = totalPrice + personalOrderLine.getPersonalOrderLineLunchPrice();

			// If the personalOrderLine contains a premium potato dish then execute this section
			if (personalOrderLine.isPremiumPotatoDish())
			{
				// Sets the isPremiumPotato variable to true
				isPremiumPotato = true;
			}
		}
		
		// If one or more premium potatos have been found then add the price of a premium
		// potato to the order.
		if (isPremiumPotato)
		{
			// Adds +20 to the price for having ordered a premium potato dish
			totalPrice = totalPrice + 20.0; 
			
			// TODO: Replace this hardcoded value with a reference to a static
			// premiumPotatoPrice
		}

		// TODO: Later add calculations for the various types of discount when that use
		// case is being handled
		
		// Returns the total price during lunch hours
		return totalPrice;
	}
	
	
	/**
	 * Calculates and returns the total price of all the PersonalOrderLines that are
	 * associated with this PersonalOrder instance.
	 * 
	 * This method iterates through every PersonalOrderLine that is contained within
	 * this Personal Order instance's personalOrderLineList, and calculates the sum
	 * of them all. The total is used to determine how much each guest's personal
	 * order costs, and will also be used to summarize the total cost of a
	 * TableOrder instance.
	 *
	 * @return the total summed price for this guest’s personal order
	 */
	public double getTotalPersonalOrderEveningPrice()
	{
		// Creates a variable called totalPrice and sets its value to 0.00
		double totalPrice = 0.00;

		// Guests should only pay for premium potatoes once. So while it sums the price,
		// it also keeps track of whether a premium potato has been found.
		boolean isPremiumPotato = false;

		// Uses a for-each loop to iterate through the list of personal order lines
		for (PersonalOrderLine personalOrderLine : personalOrderLineList)
		{
			// Adds the current PersonalOrderLine instances' price to the current value of
			// the totalPrice
			totalPrice = totalPrice + personalOrderLine.getPersonalOrderLineEveningPrice();

			// If the personalOrderLine contains a premium potato dish then execute this section
			if (personalOrderLine.isPremiumPotatoDish())
			{
				// Sets the isPremiumPotato variable to true
				isPremiumPotato = true;
			}
		}
		
		// If one or more premium potatos have been found then add the price of a premium
		// potato to the order.
		if (isPremiumPotato)
		{
			// Adds +20 to the price for having ordered a premium potato dish
			totalPrice = totalPrice + 20.0; 
			
			// TODO: Replace this hardcoded value with a reference to a static
			// premiumPotatoPrice
		}

		// TODO: Later add calculations for the various types of discount when that use
		// case is being handled
		
		// Returns the total price during evening hours
		return totalPrice;
	}

	
	/**
	 * Adds a MenuItem object to the list of PersonalOrderLines as a single line item.
	 *
	 * @param menuItem the menu item to add to this guest's personal order
	 */
	public void addMenuItemLine(MenuItem menuItem) 
	{
		PersonalOrderLine menuItemLine = new PersonalOrderLine(menuItem);
		addPersonalOrderLine(menuItemLine);
	}
	
	
	/**
	 * Retrieves a copy of the list containing all PersonalOrderLine objects in this personal order.
	 *
	 * @return a new List containing the current PersonalOrderLine instances
	 */
	public List<PersonalOrderLine> getPersonalOrderLines()
	{
		return new ArrayList<>(personalOrderLineList);
	}
	
	
	/**
	 * Retrieves the names of all menu items in this personal order.
	 *
	 * @return a list of Strings, each representing the name of a MenuItem in the order
	 */
	public List<String> getNameOfItemsInList()
	{
	    // Instantiates a new list to store the names of the MenuItems
	    List<String> listOfMenuItemNames = new ArrayList<String>();

	    // Uses a for-each loop to iterate through the list of personal order lines
	    for (PersonalOrderLine personalOrderLine : personalOrderLineList)
	    {
	        // Retrieves the name of the MenuItem from the current PersonalOrderLine and adds it to the list of menu item names
	    	listOfMenuItemNames.add(personalOrderLine.getMenuItem().getName());
	    }

	    // Returns the list of MenuItem names
	    return listOfMenuItemNames;
	}
	
	
	/**
	 * Returns the unique ID associated with this PersonalOrder.
	 *
	 * @return the ID of this personal order
	 */
	public int getPersonalOrderId()
	{
		return this.PersonalOrderId;
	}
	
	
	/**
	 * Sets the unique ID for this PersonalOrder.
	 *
	 * @param personalOrderId the unique identifier to assign to this personal order
	 */
	public void setPersonalOrderId(int personalOrderId)
	{
		this.PersonalOrderId = personalOrderId;
	}
}
