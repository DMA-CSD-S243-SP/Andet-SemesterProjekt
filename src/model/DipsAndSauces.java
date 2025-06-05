// Packages
package model;


/**
 * Represents a dip or a sauce menu item that can be ordered at a bone's
 * restaurant.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on whether the item is a sauce or a dip, along with a 
 * fixed price for the dip as well as the sauce.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 05/06/2025 - 11:09
 */
public class DipsAndSauces extends MenuItem
{
	// Determines if the DipsAndSauces instance is a dip or a sauce
    private boolean isSauce;
    
	// This price is the one that is being used regardless of whether
	// the getEveningPrice or getLunchPrice is being called upon
    private double fixedPrice;


    /**
	 * Constructs a new DipsAndSauces instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item as either a dip or a sauce and sets the price.
     * 
     * @param isSauce 				- determines if this item is a sauce (true), else false if the item is a dip
     * @param fixedPrice 			- the constant price of the item regardless of which type of dip, sauce or time of day it is
	 * @param menuItemId 			- the unique id of the MenuItem
	 * @param preparationTime 		- the time in seconds it takes to prepare this MenuItem
	 * @param name 					- the name of the MenuItem
	 * @param description 			- the description of the MenuItem
	 * @param isMadeByKitchenStaff	- whether this MenuItem is something the kitchen should handle or not
     */
    public DipsAndSauces(boolean isSauce, double fixedPrice, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff)
    {
        // Calls the constructor of its' super-class / parent class, MenuItem.
        // Although MenuItem doesn't take any parameters and cannot be directly instantiated,
    	// this makes it so everything defined in the MenuItem class is initialized before this class' values are set.
        super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);

        this.isSauce = isSauce;
        this.fixedPrice = fixedPrice;
    }


    /**
     * Returns whether or not this item is a sauce or dip.
     * 
     * Retrieves whether this item is considered to be a sauce or a dip.
     * If it returns true this object is perceived as a sauce, elsewise
     * returns false when it is a dip.
     *
     * @return true if it is a sauce; false if it is a dip
     */
    public boolean isSauce()
    {
        return this.isSauce;
    }


    /**
     * Sets whether this item is considered to be a sauce or a dip.
     * 
     * If it is set to true, this instance is perceived as a sauce, 
     * elsewise set it to false to indicate that it is a dip.
     *
     * @param isSauce true if the item is a sauce; false if it is a dip
     */
    public void setSauce(boolean isSauce)
    {
        this.isSauce = isSauce;
    }


	/**
	 * Returns the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular Sauce or Dip, it is called fixedPrice to emphasize
	 * that all Sauces in Bone's current business model use the same
	 * price regardless of their flavor or type. The only varying is that
	 * a dip costs less than a price, but similar to the sauces all dips
	 * costs the same regardless of their flavor or type.
	 * 
	 * @return the price of this DipsAndSauces instance
	 */
    public double getFixedPrice()
    {
        return this.fixedPrice;
    }


	/**
	 * Sets the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular Sauce or Dip, it is called fixedPrice to emphasize
	 * that all Sauces in Bone's current business model use the same
	 * price regardless of their flavor or type. The only varying is that
	 * a dip costs less than a price, but similar to the sauces all dips
	 * costs the same regardless of their flavor or type.
	 * 
	 * @param fixedPrice the price to assign to this instance of DipsAndSauces
	 */
    public void setFixedPrice(double fixedPrice)
    {
    	this.fixedPrice = fixedPrice;
    }
    
    
	/**
	 * Gets the lunch price of this DipsAndSauces instance.
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
	 *
	 * @return the price of purchasing this DipsAndSauces during lunch time hours
	 */
	@Override
	public double getLunchPrice()
	{
		return this.fixedPrice;
	}

	
	/**
	 * Gets the evening price of this DipsAndSauces instance.
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
	 *
	 * @return the price of purchasing this DipsAndSauces during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}