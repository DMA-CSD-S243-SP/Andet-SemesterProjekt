package model;


/**
 * @author Line Bertelsen
 * @date 08.05.25 14.14
 */
public class PersonalOrderLine
{
	//private double addtionalPrice;
/*	//Line: Attributes i've added
	private AddOnOption addOnOption;
	private SelectionOption selectionOption;
*/
	//Attributes/Instance variables
	private MenuItem menuItem;
	private int quantityOfMenuItem;
	private EnumStatusType status;
	private String notes;
	
	
	public PersonalOrderLine(MenuItem menuItem)
	{
		this.menuItem = menuItem;
	}
	
	
	public MenuItem getMenuItem()
	{
		return this.menuItem;
	}
	
	
	public int getQuantityOfMenuItems()
	{
		return this.quantityOfMenuItem;
	}
	
	
	public EnumStatusType getStatus()
	{
		return this.status;
	}
	
	
	/**
	 * The getNotes method collect kitchenNotes
	 * 
	 * If a customer has chosen an addOnOption or selectionOption, the kitchenNotes is added in the personalOrderLine.
	 * KitchenNotes is the notes which the kitchen receives if an extra option has been chosen.
	 * If no extra options has been added, this will be empty.
	 * 
	 * @return kitchenNotes from addOnOptions of selectionOption
	 */
	public String getNotes()
	{
		return this.notes;
	}
	
	
	public double getAdditionalPrice()
	{
		//Question: Når vi henter additionalPrice i personalOrderline. Hent vi det kun fra addOnOption eller også fra selectionOptions?
		return this.getAdditionalPrice();
	}
	
	
	public void addAddOnOption(AddOnOption addOnOption)
	{
//		addOnOption = addOnOption;
	}
	
	
	public void addSelectionOption(SelectionOption selectionOption)
	{
//		selectionOption = selectionOption;
	}
}
