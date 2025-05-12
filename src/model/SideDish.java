package model;

/**
 * Represents a phyiscal item from a Bone's restaurant menu, that is considered
 * to be a side dishes.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on the item fixed price and the quantity per serving for a side dish.
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 12-05-2025 - 15:14
 */
public class SideDish extends MenuItem
{
	//Attributes/Intances variables
	private int quantiyPerServing;
	private double fixedPrice;

	
	/**
	 * Constructs a new SideDish instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item having quantity per serving and a fixed price.
	 * 
     * @param fixedPrice the constant price of that specific side dish category regardless of the time of day it is
     * @param quantiyPerServing the quantity per serving assign to this instance of SideDish 
     */
	public SideDish(int quantiyPerServing, double fixedPrice)
	{
		// Calls the superclass constructor (MenuItem) to initialize inherited attributes.
		// Isnt being used yet
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
	 * @param quantiyPerServing the quantity per serving assign to this instance of SideDish
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
	
	
	/**
	 * Gets the lunch price of this SideDish instance.
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
	 *
	 * @return the price of purchasing this SideDish during lunch time hours
	 */
	@Override
	public double getLunchPrice()
	{
		return this.fixedPrice;
	}


	/**
	 * Gets the evening price of this SideDish instance.
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
	 *
	 * @return the price of purchasing this SideDish during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}
