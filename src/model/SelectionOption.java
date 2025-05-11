// Packages
package model;


/**
 * Represents a selectable option from a MultipleChoiceMenu (dropdown menu), 
 * which is associated with a MainCourse menu item. 
 * In the restaurant system, this could be things like sizes of spareribs.
 * The SelectionOption includes a text description, a kitchen notes text, and also
 * possible additional costs associated with selecting this option.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 11-05-2025 - 19:32
 */
public class SelectionOption
{
	private String description;
	private String kitchenNotes;
	private double additionalPrice;


	/**
	 * This constructor allows for creaitng a new option for a MultipleChoiceMenu object by specifying
	 * the description guests can see when browsing the contents of the MultipleChoiceMenu, within the selected
	 * MainCourse, the kitchen instructions such as "Size: Large" and any extra price to add
	 * to the base price of the MainCourse instance.
	 * 
	 * @param description a short description in the dropdown selection, such as "Large - 300g" or "Rare"
	 * @param kitchenNotes instructions meant for kitchen staff only, like "Doneness: Rare", or "w. garlic butter"
	 * @param additionalPrice the added cost for selecting this option
	 */
	public SelectionOption(String description, String kitchenNotes, double additionalPrice)
	{
		this.description = description;
		this.kitchenNotes = kitchenNotes;
		this.additionalPrice = additionalPrice;
	}


	/**
	 * Gets the description text within the MultipleChoiceMenu's selection option
	 * that will be shown to guests in the graphical user interface.
	 *
	 * @return the description text string that is shown to the customer
	 */
	public String getDescription()
	{
		return this.description;
	}


	/**
	 * Sets the description text within the MultipleChoiceMenu's selection option
	 * that is visible to guests in the graphical user interface.
	 *
	 * @param description a text string to describe this selection option e.g. '+49  Medium'
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}


	/**
	 * Gets the internal kitchen notes attached with the SelectionOption instance.
	 * This could be things such as 'Doneness: Rare'.
	 * These notes are not shown to guests, but used internally in the kitchen.
	 *
	 * @return kitchen-specific instructions that help the kitchen staff
	 */
	public String getKitchenNotes()
	{
		return this.kitchenNotes;
	}


	/**
	 * Sets the kitchen notes for this option.
	 * This could be things such as 'Doneness: Rare'.
	 * These notes are not shown to guests, but used internally in the kitchen.
	 *
	 * @param kitchenNotes specific internal preparation notes
	 */
	public void setKitchenNotes(String kitchenNotes)
	{
		this.kitchenNotes = kitchenNotes;
	}


	/**
	 * Gets the additional price that are to be applied, when this option is
	 * selected by the customer.
	 *
	 * @return the cost added to the base price of the item
	 */
	public double getAdditionalPrice()
	{
		return this.additionalPrice;
	}


	/**
	 * Sets the additional price that are to be applied, when this option is
	 * selected by the customer.
	 *
	 * @param additionalPrice the cost added to the base price of the item
	 */
	public void setAdditionalPrice(double additionalPrice)
	{
		this.additionalPrice = additionalPrice;
	}
}