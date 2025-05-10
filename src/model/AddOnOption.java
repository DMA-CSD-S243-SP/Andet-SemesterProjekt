package model;

/**
 * Represent a checkmark box in the GUI layer, where you can choose additional options.
 * 
 * AddOnOption is a class what a option 
 * includes information on the option description, kitchen note and additional price. 
 * 
 * 
 * @author Line Bertelsen
 * @version 09.05.25 - 10.03
 */

public class AddOnOption
{
	//Attributes/instance variables
	private String description;
	private String kitchenNotes;
	private double additionalPrice;
	
	//Constructor
	public AddOnOption(String description, String kitchenNotes, double additionalPrice)
	{
		this.setDescription(description);
		this.setKitchenNotes(kitchenNotes);
		this.setAdditionalPrice(additionalPrice);
	}

	
	/**
	 * The get method returns the value of the variable description
	 * 
	 * The description if for the customer, so they are able to read 
	 * the description of the add on option. 
	 * 
	 * @return the description of this AddOnOption instance
	 */
	public String getDescription()
	{
		return description;
	}
	
	
	/**
	 * The set method takes a parameter description and assigns it to the this.description variable. 
	 * 
	 * @param description is a description of the options.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * The get method returns the value of the variable kitchenNotes
	 * 
	 * The value kitchenNotes is notes which the kitchen receives. 
	 * The notes explain what the option is, which the kitchen uses
	 * to see which extra options needs to be prepared with the main course.
	 * 
	 * The value kitchenNotes is notes which the kitchen receives when AddOneOption is added to the main course,
	 * when an addOnOption.getAdditionalPrice is associated with personalOrderLine
	 * 
	 * Example: If a customer want garlic butter, with their main course Ribeye steak
	 * The kitchenNote: m. h-smør
	 * 
	 * @return the notes for this instance of an option.
	 */
	public String getKitchenNotes()
	{
		return kitchenNotes;
	}

	
	/**
	 * The set method takes a parameter kitchenNotes and assigns it to the this.kitchenNotes variable.
	 * 
	 * @param kitchenNotes is notes assign to this instance of kitchen.
	 */
	public void setKitchenNotes(String kitchenNotes)
	{
		this.kitchenNotes = kitchenNotes;
	}

	
	/**
	 * The get method returns the value of the variable additionalPrice
	 * 
	 * The value additionalPrice is additional price added to personalOrderLine, 
	 * this happens when addOnOption is associated with personalOrderLine
	 * 
	 * @return the additional price for this instance of an option.
	 */
	public double getAdditionalPrice()
	{
		return additionalPrice;
	}

	
	/**
	 * The set method takes a parameter additionalPrice and assigns it to the this.additionalPrice variable. 
	 * 
	 * @param additionalPrice is additional price assigned to this instance of option.
	 */
	public void setAdditionalPrice(double additionalPrice)
	{
		this.additionalPrice = additionalPrice;
	}
	
	
}
