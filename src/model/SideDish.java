// Packages
package model;


/**
 * Represents a phyiscal item from a Bone's restaurant menu, that is considered
 * to be a side dish.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on the dish's fixed price and the quantity per serving
 * for the side dish, which is not to be confused with an actual quantity, but 
 * simply specifies e.g. how many hotwings or chili cheese tops each of such
 * side dish is made up of.
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 05/06/2025 - 10:42
 */
public class SideDish extends MenuItem
{
	// Instance variables
	private int quantiyPerServing;
	
	// This price is the one that is being used regardless of whether
	// the getEveningPrice or getLunchPrice is being called upon
	private double fixedPrice;

	
	/**
	 * Constructs a new SideDish instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item having quantity per serving and a fixed price.
	 * 
     * @param fixedPrice 			- the constant price of that specific side dish category regardless of the time of day it is.
     * @param quantiyPerServing 	- the quantity per serving to assign to this instance of SideDish 
	 * @param menuItemId 			- the unique id of the MenuItem
	 * @param preparationTime 		- the time in seconds it takes to prepare this MenuItem
	 * @param name 					- the name of the MenuItem
	 * @param description 			- the description of the MenuItem
	 * @param isMadeByKitchenStaff	- whether this MenuItem is something the kitchen should handle or not
     */
	public SideDish(int quantiyPerServing, double fixedPrice, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff)
	{
		// Calls the super-class / parent class constructor (MenuItem) to initialize the inherited attributes
		// which makes it so everything defined in the MenuItem class is initialized before this class' attribute values are set.
        super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
		
        // Changes the quantity per serving instance variable to what is specified the parameter list
		this.quantiyPerServing = quantiyPerServing;
		
		this.fixedPrice = fixedPrice;
	}
	

	/**
	 * Returns the value of the SideDish's instance quantityPerServing variable.
	 * 
	 * The quantityPerServing determines how many 'pieces' a side dish
	 * consists of e.g. a SideDish consisting of HotWings would consist of
	 * 6 hotwings and not just a single one.
	 * The quantityPerServing instance variable dictates how many of the 
	 * type the dish consists of, in the example above this would be set 
	 * to 6.
	 * 
	 * @return the quantityPerServing of this SideDish instance
	 */
	public int getQuantityPerServing()
	{
		return quantiyPerServing;
	}
	
	
	/**
	 * Sets the value of the SideDish's instance quantityPerServing variable
	 * 
	 * The quantityPerServing determines how many 'pieces' a side dish
	 * consists of e.g. a SideDish consisting of HotWings would consist of
	 * 6 hotwings and not just a single one.
	 * The quantityPerServing instance variable dictates how many of the 
	 * type the dish consists of, in the example above this would be set 
	 * to 6.
	 * 
	 * @param quantiyPerServing the quantity of food 'pieces' this instance
	 * of SideDish is made up of
	 */
	public void setQuantityPerserving(int quantiyPerServing)
	{
		this.quantiyPerServing = quantiyPerServing;
	}
	
	
	/**
	 * Returns the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular SideDish, it is called fixedPrice to emphasize
	 * that all SideDishes in Bone's current business model use the same
	 * price regardless of the SideDish' contents.
	 * 
	 * @return the price of this SideDish instance
	 */
	public double getfixedPrice()
	{
		return fixedPrice;
	}
	
	
	/**
	 * Sets the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular SideDish, it is called fixedPrice to emphasize
	 * that all SideDishes in Bone's current business model use the same
	 * price regardless of the SideDish' contents.
	 * 
	 * @param fixedPrice the price to assign to this instance of SideDish
	 */
	public void setfixedPrice(double fixedPrice)
	{
		this.fixedPrice = fixedPrice;
	}
	
	
	/**
	 * Gets the lunch price of this SideDish instance.
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
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
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
	 *
	 * @return the price of purchasing this SideDish during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}