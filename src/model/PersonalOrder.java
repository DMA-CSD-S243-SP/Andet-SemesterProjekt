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
 * @version 11-05-2025 - 21:01
 */
public class PersonalOrder
{
// OLD - 	private double totalPersonalOrderPrice;
	/*
	 * // private List<SelectionOption> listOfSelectionOption; // private
	 * List<AddOnOption> listOfAddOnOptions; //Line: Attributes i've added private
	 * TableOrder tableOrder; private MainCourse mainCourse; private MenuItem
	 * menuItem;
	 */

	// Attributes/Instance variables
	private int PersonalOrderId;
	private int customerAge;
	private String customerName;

	// Lists
	private List<Discount> listOfAllDiscounts;
	private List<PersonalOrderLine> personalOrderLineList;

	// Constructor of PersonalOrder
	public PersonalOrder(TableOrder tableOrder)
	{
		// Instantiates the list of personal order lines and the list of discounts
		this.personalOrderLineList = new ArrayList<>();
		this.listOfAllDiscounts = new ArrayList<>();
	}

	/**
	 * The get method returns the value of the variable customerAge
	 * 
	 * @return customerAge
	 */
	public int getCustomerAge()
	{
		return customerAge;
	}

	/**
	 * The set method takes a parameter customerAge and assigns it to the
	 * this.customerAge variable.
	 * 
	 * @param customerAge
	 */
	public void setCustomerAge(int customerAge)
	{
		this.customerAge = customerAge;
	}

	/**
	 * The get method returns the value of the variable customerName
	 * 
	 * @return customerName
	 */
	public String getCustomerName()
	{
		return customerName;
	}

	/**
	 * The set method takes a parameter customerName and assigns it to the
	 * this.customerName variable.
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	/**
	 * The addAllDiscount method uses a foreach-loop and the method addDiscount to
	 * add all discounts objects to the list listOfAllDiscounts
	 */
	public void addAllDiscounts(List<Discount> listOfAllDiscounts)
	{
		for (Discount discount : listOfAllDiscounts)
		{
			addDiscount(discount);
		}
	}

	/**
	 * The method addDiscount add one discount object to the list listOfAllDiscounts
	 * 
	 * @param discount
	 */
	public void addDiscount(Discount discount)
	{
		this.listOfAllDiscounts.add(discount);
	}

	/**
	 * The method removeDiscount removes a discount object from the list
	 * listOfAllDiscounts
	 * 
	 * @param discount
	 */
	public void removeDiscount(Discount discount)
	{
		this.listOfAllDiscounts.remove(discount);
	}

	/**
	 * The clearDiscont method uses a foreach-loop and the method removeDiscount to
	 * remove all discounts objects to the list listOfAllDiscounts
	 */
	public void clearDiscont()
	{
		for (Discount discount : listOfAllDiscounts)
		{
			removeDiscount(discount);
		}
	}

	/**
	 * The method addMainCourseLine, adds a main course, a list of
	 * listOfAddOnOptions and a list of listOfSelectionOption to personalOrder
	 * 
	 * @param mainCourse
	 * @param listOfAddOnOptions
	 * @param listOfSelectionOption
	 */
	public void addMainCourseLine(MainCourse mainCourse, List<AddOnOption> listOfAddOnOptions,
			List<SelectionOption> listOfSelectionOption)
	{
		// Dummy Code Block

		/*
		 * //Add a single mainCourse to the MainCourseLine this.mainCourse = mainCourse;
		 * 
		 * //Adds list of addOnOptions to the list listOfAddOnOptions
		 * this.listOfAddOnOptions.addAll(listOfAddOnOptions);
		 * 
		 * //Adds list of addOnOptions to the list listOfAddOnOptions
		 * this.listOfSelectionOption.addAll(listOfSelectionOption);
		 * 
		 * //PersonalOrderLine.addOnOption(); //PersonalOrderLine.addSelectionOption();
		 */
		
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourse);
		for (AddOnOption option: listOfAddOnOptions)
		{
			mainCourseLine.addAddOnOption(option);
		}
		for (SelectionOption option: listOfSelectionOption)
		{
			mainCourseLine.addSelectionOption(option);
		}
		addPersonalOrderLine(mainCourseLine);
		
	}

	/**
	 * Adds a all menuItems to the personal order Like potatoDish and sideOrderItem
	 * 
	 * @param menuItem
	 */
	public void addPersonalOrderLine(PersonalOrderLine personalOrderLine)
	{
		this.personalOrderLineList.add(personalOrderLine);
	}

	/**
	 * The clearDiscont method uses a foreach-loop and the method removeDiscount to
	 * remove all discounts objects to the list listOfAllDiscounts
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

		// Iterates through the list of personal order lines
		for (PersonalOrderLine personalOrderLine : personalOrderLineList)
		{
			// Adds the current PersonalOrderLine instances' price to the current value of
			// the totalPrice
			totalPrice = totalPrice + personalOrderLine.getPersonalOrderLineLunchPrice();

			// Sets
			if (personalOrderLine.isPremiumPotatoDish())
			{
				isPremiumPotato = true;
			}
		}
		// If one or more premium potatos have been found add the price of a premium
		// potato to the order.
		if (isPremiumPotato)
		{
			totalPrice = totalPrice + 20.; // TODO: Replace this hardcoded value with a reference to a static
											// premiumPotatoPrice
		}

		// TODO: Later add calculations for the various types of discount when that use
		// case is being handled
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

		// Iterates through the list of personal order lines
		for (PersonalOrderLine personalOrderLine : personalOrderLineList)
		{
			// Adds the current PersonalOrderLine instances' price to the current value of
			// the totalPrice
			totalPrice = totalPrice + personalOrderLine.getPersonalOrderLineEveningPrice();

			// Sets
			if (personalOrderLine.isPremiumPotatoDish())
			{
				isPremiumPotato = true;
			}
		}
		// If one or more premium potatos have been found add the price of a premium
		// potato to the order.
		if (isPremiumPotato)
		{
			totalPrice = totalPrice + 20.; // TODO: Replace this hardcoded value with a reference to a static
											// premiumPotatoPrice
		}

		// TODO: Later add calculations for the various types of discount when that use
		// case is being handled
		return totalPrice;
	}

	public void addMenuItemLine(MenuItem menuItem) 
	{
		PersonalOrderLine menuItemLine = new PersonalOrderLine(menuItem);
		addPersonalOrderLine(menuItemLine);
	}
	
	public List<PersonalOrderLine> getPersonalOrderLines()
	{
		return new ArrayList<>(personalOrderLineList);
	}
	
	public List<String> getNameOfItemsInList()
	{
		List<String> nameList = new ArrayList<String>();
		for (PersonalOrderLine pList : personalOrderLineList)
		{
			nameList.add(pList.getMenuItem().getName());
		}
		return nameList;
	}
	
	public int getPersonalOrderId()
	{
		return this.PersonalOrderId;
	}
	
	public void setPersonalOrderId(int personalOrderId)
	{
		this.PersonalOrderId = personalOrderId;
	}
}
