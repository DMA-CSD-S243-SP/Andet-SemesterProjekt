package model;

/**
 * Represents a single order line associated with a PersonalOrder. A
 * PersonalOrderLine will typically consist of a quantity, a specific MenuItem,
 * the price of said MenuItem, along with possible additional prices for that
 * item typically for MainCourse instances, along with a collection of notes for
 * the MenuItem and a status of this single PersonalOrderLine instance, to see
 * where in the preparation/serving process this particular MenuItem(s)
 * currently is/are at.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 12-05-2025 - 15:17
 */
public class PersonalOrderLine
{
	// The menu item chosen for this specific order line
	private MenuItem menuItem;
	
	// Notes for kitchen staff from customization of MainCourse in the form of
	// add-ons and selection options
	private String notes;

	// The additional price to add to a MenuItem's base cost, if certain additional
	// choices are made
	private double additionalPrice;

	// The current status of this particular PersonalOrderLine instance e.g.
	// "waiting to be prepared"
	private EnumStatusType status;



	
	/**
	 * Constructs a new PersonalOrderLine using the supplied MenuItem in the
	 * parameter.
	 * 
	 * The additionalPrice, Notes and status must be set separately. The constructor
	 * associates the PersonalOrderLine with the MenuItem instance.
	 * 
	 * @param menuItem the MenuItem object that this PersonalOrderLine is to be
	 *                 associated with
	 */
	public PersonalOrderLine(MenuItem menuItem)
	{
		this.menuItem = menuItem;
		notes = "";
		additionalPrice = 0;
		status = EnumStatusType.WAITINGTOBEPREPARED;
	}

	
	/**
	 * Returns the MenuItem associated with this personal order line.
	 *
	 * @return the menu item object
	 */
	public MenuItem getMenuItem()
	{
		return this.menuItem;
	}


	/**
	 * Returns any kitchen notes associated with this PersonalOrderLine.
	 * 
	 * If a guest has chosen an addOnOption, or selectionOption, the kitchenNotes is
	 * added to the personalOrderLine. KitchenNotes are the notes that the kitchen
	 * receives if any extra option has been chosen in e.g. a MainCourse.
	 * 
	 * @return kitchenNotes the kitchen notes that was associated with a MainCourse
	 *         in the form of addOnOptions of selectionOption notes.
	 */
	public String getNotes()
	{
		return this.notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	/**
	 * Returns the additional price, that should be applied on top of the associated
	 * MenuItem's base/standard price. The additional price comes from pricing that
	 * is added on top to reflect upgrades or extras often associated with the
	 * customization of MainCourse instances.
	 *
	 * @return the additional cost to add on top of the MenuItem's base price
	 */
	public double getAdditionalPrice()
	{
		return this.additionalPrice;
	}
	
	public void setAdditionalPrice(double additionalPrice)
	{
		this.additionalPrice = additionalPrice;
	}
	
	/**
	 * Calculates and returns the total lunch price for this one PersonalOrderLine
	 * instance.
	 * 
	 * This price is the sum of the MenuItem's lunch price, and any additional price
	 * from options.
	 * 
	 * @return the total lunch price for this personal order line.
	 */
	public double getPersonalOrderLineLunchPrice()
	{
		// TODO: Update comment
		// Creates a variable named personalOrderLinePrice and sets the value of the
		// personalOrderLinePrice variable to be equal to the MenuItem's base lunch,
		// plus the additional costs associated with the price (e.g. +49 for medium
		// spare ribs instead of small)
		double personalOrderLinePrice = menuItem.getLunchPrice() + getAdditionalPrice();

		// Returns the value stored within the personalOrderLinePrice variable
		return personalOrderLinePrice;
	}

	
	
	/**
	 * Calculates and returns the total evening price for this one PersonalOrderLine
	 * instance.
	 * 
	 * This price is the sum of the MenuItem's evening price, and any additional
	 * price from options.
	 * 
	 * @return the total lunch evening for this personal order line.
	 */
	public double getPersonalOrderLineEveningPrice()
	{
		double personalOrderLinePrice = menuItem.getEveningPrice() + getAdditionalPrice();

		return personalOrderLinePrice;
	}
	

	/**
	 * Returns whether or not this PersonalOrderLine contains a premium PotatoDish.
	 * @return whether or not this PersonalOrderLine contains a premium PotatoDish.
	 */
	public boolean isPremiumPotatoDish()
	{
		boolean result = false; // If it's not even a PotatoDish, then it surely isn't a premium PotatoDish.
		if (menuItem instanceof PotatoDish) 
		{
			result = ((PotatoDish) menuItem).isPremiumPotatoDish();
		}
		return result;
	}

	
	/**
	 * Adds an AddOnOption to this PersonalOrderLine objectwith the intent of
	 * mutating the contents of the PersonalOrderLine instance's notes and
	 * additionalPrice attributes.
	 * 
	 * This method is intended for being used to set specific upgrades and MenuItem
	 * customizations that affect the kitchen notes and pricing of the
	 * PersonalOrderLine.
	 *
	 * @param addOnOption the additional option to add
	 */
	public void addAddOnOption(AddOnOption addOnOption)
	{
		// Adds the additionalPrice from the SelectionOption instance on top of
		// PersonalOrderLine instance's current value of additionalPrice
		this.additionalPrice = additionalPrice + addOnOption.getAdditionalPrice();

		// Adds the AddOnOption's kitchen notes to the PersonalOrderLine instance's
		// current contents of the notes string,
		// with a space behind each note to make it easily readable for the kitchen
		// staff
		this.notes = notes + addOnOption.getKitchenNotes() + " ";
	}

	/**
	 * Adds an SelectionOption to this PersonalOrderLine object, with the intent of
	 * mutating the contents of the PersonalOrderLine instance's notes and
	 * additionalPrice attributes.
	 * 
	 * This method is intended for when guests have selected an option from a
	 * MultipleChoiceMenu that affects the pricing or kitchen notes e.g. "+49
	 * Medium" instead of small sized spareribs.
	 * 
	 * @param selectionOption the selection option to add
	 */
	public void addSelectionOption(SelectionOption selectionOption)
	{
		// Adds the additionalPrice from the SelectionOption instance on top of
		// PersonalOrderLine instance's current value of additionalPrice
		this.additionalPrice = additionalPrice + selectionOption.getAdditionalPrice();

		// Adds the SelectionOption's kitchen notes to the PersonalOrderLine instance's
		// current contents of the notes string,
		// with a space behind each note to make it easily readable for the kitchen
		// staff
		this.notes = notes + selectionOption.getKitchenNotes() + " ";
	}

	
	
	/**
	 * Returns the current status of this/these MenuItems on this PersonalOrderLine.
	 *
	 * This is used to track whether the MenuItem has been sent to the kitchen, is
	 * currently being prepared by kitchen personel, is ready to be picked up and
	 * served by the waiters, or have already been served.
	 *
	 * @return the current status of this particular PersonalOrderLine object.
	 */
	public EnumStatusType getStatus()
	{
		return this.status;
	}

	public void setStatus(EnumStatusType status)
	{
		this.status = status;
	}


}
