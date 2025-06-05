// Packages
package model;


/**
 * Represents a drink menu item that can be ordered at a bone's restaurant.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and
 * includes information on whether the item is a drink, along with whether the
 * drink is alcoholic and is refillable and the price.
 * 
 * 
 * @author Anders Trankjær & Christoffer Søndergaard
 * @version 05/06/2025 - 13:57
 */
public class Drink extends MenuItem 
{
	private boolean isAlcoholic;
	private boolean isRefill;
	private double price;


	/**
	 * Constructs a new Drink instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * 
	 * @param isAlcoholic 			- whether the drink is alcoholic
	 * @param isRefill    			- whether the drink is allowed to be refilled
	 * @param price       			- the price of the drink
	 * @param menuItemId 			- the unique id of the MenuItem
	 * @param preparationTime 		- the time in seconds it takes to prepare this MenuItem
	 * @param name 					- the name of the MenuItem
	 * @param description 			- the description of the MenuItem
	 * @param isMadeByKitchenStaff	- whether this MenuItem is something the kitchen should handle or not
	 */
	public Drink(boolean isAlcoholic, boolean isRefill, double price, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff) 
	{
		// Calls the super-class / parent class constructor (MenuItem) to initialize the inherited attributes
		// which makes it so everything defined in the MenuItem class is initialized before this class' attribute values are set.
		super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);

		this.isAlcoholic = isAlcoholic;
		this.isRefill = isRefill;
		this.price = price;
	}

	
	/**
	 * @return isAlcoholic
	 */
	public boolean isAlcoholic() 
	{
		return isAlcoholic;
	}

	
	/**
	 * @param isAlcoholic the new isAlcoholic to be set
	 */
	public void setAlcoholic(boolean isAlcoholic) 
	{
		this.isAlcoholic = isAlcoholic;
	}
	

	/**
	 * @return isRefill
	 */
	public boolean isRefill() 
	{
		return isRefill;
	}

	
	/**
	 * @param isRefill the new isRefill to be set
	 */
	public void setRefill(boolean isRefill) 
	{
		this.isRefill = isRefill;
	}

	
	/**
	 * @return price
	 */
	public double getPrice() 
	{
		return price;
	}

	
	/**
	 * @param newPrice the new price to be set
	 */
	public void setPrice(double newPrice) 
	{
		this.price = newPrice;
	}
	
	
	/**
	 * Gets the lunch price of this Drink instance.
	 * 
	 * This method essentially retrieves the value of the instance's price attribute.
	 *
	 * @return the price of purchasing this Drink during lunch time hours
	 */
	@Override
	public double getLunchPrice()
	{
		return this.price;
	}


	/**
	 * Gets the evening price of this Drink instance.
	 * 
	 * This method essentially retrieves the value of the instance's price attribute.
	 *
	 * @return the price of purchasing this Drink during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.price;
	}
}
