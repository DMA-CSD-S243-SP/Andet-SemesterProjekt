package model;

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
	
	//List
	private List<PersonalOrderLine> personalOrderLineList;
	private List<SelectionOption> listOfSelectionsOption;
	private List<AddOnOption> listOfAddOnOptions;
	
	public PersonalOrder(TableOrder tableOrder) 
	{
		
	}
}
