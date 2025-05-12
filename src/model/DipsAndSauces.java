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
 * @version 12-05-2025 - 15:09
 */
public class DipsAndSauces extends MenuItem
{
    private boolean isSauce;
    private double fixedPrice;


    /**
	 * Constructs a new DipsAndSauces instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item as either a dip or a sauce and sets the price.
     * 
     * @param isSauce true if this item is a sauce, else false if the item is a dip
     * @param fixedPrice the constant price of the item regardless of which type of dip, sauce or time of day it is
     */
    public DipsAndSauces(boolean isSauce, double fixedPrice)
    {
        // Calls the constructor of its' super-class / parent class, MenuItem.
        // Although MenuItem doesn't take any parameters and cannot be directly instantiated,
    	// this makes it so everything defined in the MenuItem class is initialized before this class' values are set.
        super();

        this.isSauce = isSauce;
        this.fixedPrice = fixedPrice;
    }


    /**
     * Indicates whether this item is considered to be a sauce.
     *
     * @return true if it is a sauce; false if it is a dip
     */
    public boolean isSauce()
    {
        return this.isSauce;
    }


    /**
     * Sets whether this item is considered to be a sauce.
     *
     * @param isSauce true if the item is a sauce; false if it is a dip
     */
    public void setSauce(boolean isSauce)
    {
        this.isSauce = isSauce;
    }


    /**
     * Returns the fixed price of the dip or sauce item.
     *
     * @return the price assigned to the item
     */
    public double getFixedPrice()
    {
        return this.fixedPrice;
    }


    /**
     * Sets the fixed price of the dip or sauce item.
     *
     * @param fixedPrice the new fixed price to apply
     */
    public void setFixedPrice(double fixedPrice)
    {
    	this.fixedPrice = fixedPrice;
    }
    
    
	/**
	 * Gets the lunch price of this DipsAndSauces instance.
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
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
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
	 *
	 * @return the price of purchasing this DipsAndSauces during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}