package model;

/**
 * @author Line Bertelsen
 * @date 08.05.25 14.14
 */


public class PersonalOrderLine
{
	//Attributes/Instance variables
	private MenuItem menuItem;
	private int quantityOfMenuItem;
	//private double addtionalPrice;
	private EnumStatusType status;
	private String notes;
	
	//Line: Attributes i've added
	private AddOnOption addOnOption;
	private SelectionOption selectionOption;
	
	public PersonalOrderLine(MenuItem menuitem)
	{
		this.menuItem = menuItem;
	}
	
	public int getMenuItemId()
	{
		return this.menuItem.getMenuItemId();
	}
	
	public int getQuantityOfMenuItems()
	{
		return quantityOfMenuItem;
	}
	
	public int getAdditionalPrice()
	{
		return this.addOnOption.getAdditionalPrice;
		//Question: Når vi henter additionalPrice i personalOrderline. Hent vi det kun fra addOnOption eller også fra selectionOptions
	}
	
	public EnumStatusType getStatus()
	{
		return status;
		//TODO:
	}
	
	public void addAddOnOption(AddOnOption addOnOption)
	{
		this.addOnOption = addOnOption;
	}
	
	
	public void addSelectionOption(SelectionOption selectionOption)
	{
		this.selectionOption = selectionOption;
	}
}
