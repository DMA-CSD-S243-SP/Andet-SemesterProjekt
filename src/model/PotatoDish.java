// Packages
package model;


/**
 * Represents a phyiscal item from a Bone's restaurant menu, that is considered
 * to be a potato based dish.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on whether the item is a 'premium' potato based dish 
 * which costs more than 'non-premium / regular' potato based dishes, both 
 * premium and regular dishes have a fixed price for their respective
 * categories.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 05/06/2025 - 13:42
 */
public class PotatoDish extends MenuItem
{
    private boolean isPremium;
    
	// This price is the one that is being used regardless of whether
	// the getEveningPrice or getLunchPrice is being called upon
    private double fixedPrice;

    
    /**
	 * Constructs a new PotatoDish instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item as either a premium or a regular potato and sets the price.
     * 
     * @param isPremium 			- determines if the dish is a premium potato based dish (true), else false if it is a regular potato-based dish
     * @param fixedPrice 			- the constant price of that specific potato dish category regardless of the time of day it is
	 * @param menuItemId 			- the unique id of the MenuItem
	 * @param preparationTime 		- the time in seconds it takes to prepare this MenuItem
	 * @param name 					- the name of the MenuItem
	 * @param description 			- the description of the MenuItem
	 * @param isMadeByKitchenStaff	- whether this MenuItem is something the kitchen should handle or not
     */
    public PotatoDish(boolean isPremium, double fixedPrice, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff)
    {
		// Calls the super-class / parent class constructor (MenuItem) to initialize the inherited attributes
		// which makes it so everything defined in the MenuItem class is initialized before this class' attribute values are set.
        super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);

        this.isPremium = isPremium;
        
    	// This price is the one that is being used regardless of whether
    	// the getEveningPrice or getLunchPrice is being called upon
        this.fixedPrice = fixedPrice;
    }


    /**
     * Returns whether this is a regular potato dish or if it is perceived
     * as a premium potato dish and thereby a higher cost potato dish.
     * 
     * In Bone's current business model the potato dishes are seperated in 
     * to two groups, each group has a fixed price that differs from the other
     * hence the potato dishes are divided in to premium and non-premium groups.
     *
     * @return true if the dish is considered 'premium' else returns false
     */
    public boolean isPremiumPotatoDish()
    {
        return this.isPremium;
    }


    /**
     * Sets whether this is a regular potato dish or if it is perceived
     * as a premium potato dish and thereby a higher cost potato dish.
     * 
     * In Bone's current business model the potato dishes are seperated in 
     * to two groups, each group has a fixed price that differs from the other
     * hence the potato dishes are divided in to premium and non-premium groups.
     *
     * @param isPremium true if this is perceived as a premium potato dish,
     * else set to false for a standard/regular potato dish
     */
    public void setPremiumPotatoDish(boolean isPremium)
    {
        this.isPremium = isPremium;
    }


    /**
	 * Returns the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular PotatoDish, it is called fixedPrice to emphasize
	 * that all PotatoDishes in the non-premium group uses the same price
	 * and all the PotatoDishes in the premium group uses the same price
	 * in Bone's current business regardless of the contents of the PotatoDish
	 * or time of day.
	 * 
	 * @return the price of this PotatoDish instance
     */
    public double getFixedPrice()
    {
        return this.fixedPrice;
    }

    
	/**
	 * Sets the value of the variable fixedPrice.
	 * 
	 * The fixedPrice attribute / instance variable, determines the price
	 * for this particular PotatoDish, it is called fixedPrice to emphasize
	 * that all PotatoDishes in the non-premium group uses the same price
	 * and all the PotatoDishes in the premium group uses the same price
	 * in Bone's current business regardless of the contents of the PotatoDish
	 * or time of day.
	 * 
	 * @param fixedPrice the price to assign to this instance of PotatoDish
	 */
    public void setFixedPrice(double fixedPrice)
    {
        this.fixedPrice = fixedPrice;
    }
    
    
	/**
	 * Gets the lunch price of this PotatoDish instance.
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
	 *
	 * @return the price of purchasing this PotatoDish during lunch time hours
	 */
	@Override
	public double getLunchPrice()
	{
		return this.fixedPrice;
	}


	/**
	 * Gets the evening price of this PotatoDish instance.
	 * 
	 * This method essentially retrieves the value of the instance's fixedPrice attribute
	 * as Bone's current business model does not have varying prices for evening or lunch
	 * periods for this particular menu item, but to future proof the application this has
	 * been added.
	 *
	 * @return the price of purchasing this PotatoDish during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}