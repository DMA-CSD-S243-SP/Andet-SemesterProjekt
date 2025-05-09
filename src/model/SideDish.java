package model;

/**
 * Represents a phyiscal item from a Bone's restaurant menu, that is considered
 * to be a side dishes.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on the item fixed price and the quantity per serving for a side dish.
 * 
 * @author Line Bertelsen
 * @version 09.05.25 - 09:50
 */
public class SideDish extends MenuItem
{
	//Attributes/Intances variables
	private int quantiyPerServing;
	private double fixedPrice;
	
	
	public SideDish(int quantiyPerServing, double fixedPrice)
	{
		// Calls the superclass constructor (MenuItem) to initialize inherited attributes.
        super();
		
		this.quantiyPerServing = quantiyPerServing;
		this.fixedPrice = fixedPrice;
	}
	

	/**
	 * The get method returns the value of the variable quantiyPerServing
	 * @return the quantity per serving of this SideDish instance
	 */
	public int getQuantityPerServing()
	{
		return quantiyPerServing;
	}
	
	
	/**
	 * The set method takes a parameter quantiyPerServing and assigns it to the this.quantiyPerServing variable.
	 * @param quantiyPerServing
	 */
	public void setQuantityPerserving(int quantiyPerServing)
	{
		this.quantiyPerServing = quantiyPerServing;
	}
	
	/**
	 * The get method returns the value of the variable fixedPrice
	 * @return the price of this SideDish instance
	 */
	public double getfixedPrice()
	{
		return fixedPrice;
	}
	
	
	/**
	 * The set method takes a parameter fixedPrice and assigns it to the this.fixedPrice variable.
	 * @param fixedPrice the price to assign to this instance of SideDish
	 */
	public void setfixedPrice(double fixedPrice)
	{
		this.fixedPrice = fixedPrice;
	}
	
}
