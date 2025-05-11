package model;


/**
 * Represents a single order line associated with a PersonalOrder.
 * A PersonalOrderLine will typically consist of a quantity, a specific MenuItem,
 * the price of said MenuItem, along with possible additional prices for that item typically
 * for MainCourse instances, along with a collection of notes for the MenuItem and a status
 * of this single PersonalOrderLine instance, to see where in the preparation/serving process
 * this particular MenuItem(s) currently is/are at.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 11-05-2025 - 20:41
 */
public class PersonalOrderLine
{
	//private double addtionalPrice;
/*	//Line: Attributes i've added
	private AddOnOption addOnOption;
	private SelectionOption selectionOption;
*/

	
	// The menu item chosen for this specific order line
	private MenuItem menuItem;

	// The quantity of the selected menu item
	private int quantityOfMenuItem;

	// The current status of this particular PersonalOrderLine instance e.g. "waiting to be prepared" 
	private EnumStatusType status;

	// Notes for kitchen staff from customization of MainCourse in the form of add-ons and selection options
	private String notes;
	
	
	/**
	 * Constructs a new PersonalOrderLine using the suppleid MenuItem in the parameter.
	 * 
	 * The quantity and status must be set separately.
	 * The constructor associates the PersonalOrderLine with the MenuItem instance.
	 * 
	 * @param menuItem the MenuItem object that this PersonalOrderLine is to be associated with
	 */
	public PersonalOrderLine(MenuItem menuItem)
	{
		this.menuItem = menuItem;
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
	 * Returns the number of MenuItems ordered in this PersonalOrderLine.
	 *
	 * @return the quantity of the specified MenuItem
	 */
	public int getQuantityOfMenuItems()
	{
		return this.quantityOfMenuItem;
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
	
	
	/**
	 * Calculates and returns the total price for this one PersonalOrderLine instance.
	 * 
	 * This price includes the BasePrice, an additional price associated with the MenuItem
	 * in question, and then it is multiplied by the quantity of the specified menuitem. 
	 * 
	 * @return the total price for this personal order line.
	 */
	public double getPersonalOrderLinePrice()
	{
		// Creates a variable named personalOrderLinePrice and sets the value of the personalOrderLinePrice variable to be equal to the MenuItem's base price, 
		// plus the additional costs associated with the price (e.g. +49 for medium spare ribs instead of small)
		double personalOrderLinePrice = menuItem.getBasePrice() + getAdditionalPrice();
		
		// Multiplies the value of the personalOrderLinePrice variable by the amount of MenuItems that has been ordered
		personalOrderLinePrice = personalOrderLinePrice * this.quantityOfMenuItem;
		
		// Returns the value stored within the personalOrderLinePrice variable
		return personalOrderLinePrice;
	}
	
	
	/**
	 * Returns any kitchen notes associated with this PersonalOrderLine.
	 * 
	 * If a guest has chosen an addOnOption, or selectionOption, the kitchenNotes is added to 
	 * the personalOrderLine. KitchenNotes are the notes that the kitchen receives if any extra
	 * option has been chosen in e.g. a MainCourse.
	 * 
	 * @return kitchenNotes the kitchen notes that was associated with a MainCourse in the form of 
	 * addOnOptions of selectionOption notes.
	 */
	public String getNotes()
	{
		return this.notes;
	}
	
	
	/**
	 * Adds an AddOnOption to this PersonalOrderLine object.
	 * 
	 * This method is intended for being used to set specific upgrades and 
	 * MenuItem customizations that affect the kitchen notes and pricing of the PersonalOrderLine. 
	 *
	 * @param addOnOption the additional option to add
	 */
	public void addAddOnOption(AddOnOption addOnOption)
	{
//		addOnOption = addOnOption;
	}
	
	
	/**
	 * Adds an SelectionOption to this PersonalOrderLine object.
	 * 
	 * This method is intended for when guests have selected an option from
	 * a MultipleChoiceMenu that affects the pricing or kitchen notes e.g. "+49   Medium"
	 * instead of small sized spareribs.
	 * 
	 * @param selectionOption the selection option to add
	 */
	public void addSelectionOption(SelectionOption selectionOption)
	{
//		selectionOption = selectionOption;
	}
	
	
	/**
	 * Returns the additional price, that should be applied on top of the associated MenuItem's base/standard
	 * price. The additional price comes from pricing that is added on top to reflect upgrades or extras often
	 * associated with the customization of MainCourse instances.
	 *
	 * @return the additional cost to add to the base price
	 */
	public double getAdditionalPrice()
	{
		//Question: Når vi henter additionalPrice i personalOrderline. Hent vi det kun fra addOnOption eller også fra selectionOptions?

		// TODO: The code for this method needs implementing
		
		return 0.00;
	}
}
