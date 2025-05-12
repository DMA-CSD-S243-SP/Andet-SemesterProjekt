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
 * @version 12-05-2025 - 15:12
 */
public class PotatoDish extends MenuItem
{
    private boolean isPremium;
    private double fixedPrice;

    
    /**
	 * Constructs a new PotatoDish instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * This initializes the item as either a premium or a regular potato and sets the price.
     * 
     * @param isPremium true if the dish is a premium potato based dish, else false if it is a regular potato-based dish
     * @param fixedPrice the constant price of that specific potato dish category regardless of the time of day it is
     */
    public PotatoDish(boolean isPremium, double fixedPrice)
    {
        // Calls the superclass constructor (MenuItem) to initialize inherited attributes.
        super();

        this.isPremium = isPremium;
        this.fixedPrice = fixedPrice;
    }


    /**
     * Returns whether this is a regular potato dish or if it is a premium and thereby a higher cost potato dish.
     *
     * @return true if the dish is considered 'premium' else returns false
     */
    public boolean isPremiumPotatoDish()
    {
        return this.isPremium;
    }


    /**
     * Sets whether this potato dish is a regular potato dish or if it is a premium and higher cost potato dish.
     *
     * @param isPremium true if this is perceived as a premium potato dish, else set to false for a standard/regular potato dish
     */
    public void setPremiumPotatoDish(boolean isPremium)
    {
        this.isPremium = isPremium;
    }


    /**
     * Returns the fixed price of this PotatoDish instance.
     *
     * @return the price of this PotatoDish instance
     */
    public double getFixedPrice()
    {
        return this.fixedPrice;
    }


    /**
     * Sets the fixed price of this PotatoDish instance.
     *
     * @param fixedPrice the price to assign to this instance of PotatoDish
     */
    public void setFixedPrice(double fixedPrice)
    {
        this.fixedPrice = fixedPrice;
    }
    
    
	/**
	 * Gets the lunch price of this PotatoDish instance.
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
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
	 * This method essentially retrieves the value of the instance's fixedPrice attribute.
	 *
	 * @return the price of purchasing this PotatoDish during evening time hours
	 */
	@Override
	public double getEveningPrice()
	{
		return this.fixedPrice;
	}
}
