// Packages
package model;


/**
 * Represents a physical item from a Bone's restaurant menu, that is considered
 * to be a self-service type of dish such as a soft-ice bar and salat bar that are
 * ad libitum.
 * 
 * Represents the item on the menu in one of Bone's many restaurants, that
 * allows for a guest to go to either a salad bar, or softice bar, where they
 * can eat as much as they would like to of the respective menu item that was
 * chosen. 
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes prices for lunch and evening as well as the type of self service bar.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 05/06/2025 - 14:31
 */
public class SelfServiceBar extends MenuItem
{
    // Used to determine the type of self service bar this is
	private EnumBarType barType;
    
    private double lunchPrice;
    private double eveningPrice;
    
	/**
	 * Constructs a new SelfServiceBar instance, which uses the specified parameters of
	 * its' super-class / parent class MenuItem, along with its own unique parameters aswell.
	 * 
	 * @param barType				- the type of the self service bar this is, options are SALADBAR or SOFTICEBAR
	 * @param lunchPrice  			- the price of this item during the specified lunch hours
	 * @param eveningPrice 			- the price of this item during the specified evening hours
	 * @param menuItemId 			- the unique id of the MenuItem
	 * @param preparationTime 		- the time in seconds it takes to prepare this MenuItem
	 * @param name 					- the name of the MenuItem
	 * @param description 			- the description of the MenuItem
	 * @param isMadeByKitchenStaff	- whether this MenuItem is something the kitchen should handle or not
	 */
    public SelfServiceBar(EnumBarType barType, double lunchPrice, double eveningPrice, int menuItemId, int preparationTime, String name, String description, boolean isMadeByKitchenStaff)
    {
    	super(menuItemId, preparationTime, name, description, isMadeByKitchenStaff);
        // Calls the constructor of its' super-class / parent class, MenuItem.
        // Although MenuItem doesn't take any parameters and cannot be directly instantiated,
    	// this makes it so everything defined in the MenuItem class is initialized before this class' values are set.
        this.barType = barType;
        
        this.lunchPrice = lunchPrice;
        this.eveningPrice = eveningPrice;
    }


    /**
     * Gets the type of self service bar this instance is.
     * 
     * This method is used to determine the category of the
     * self service bar item in question, in Bone's current business
     * model it can be either SALADBAR to SOFTICEBAR, but to future
     * proof the application an enumerator is being used, in case Bone's
     * wants to implement a third type of self-service then it is easy
     * to implement.
     *
     * @return the bar type as defined in the EnumBarType class
     */
    public EnumBarType getBarType()
    {
        return this.barType;
    }


    /**
     * Sets the type of the self service bar this instance is.
     *
     * This method is used to determine the category of the
     * self service bar item in question, in Bone's current business
     * model it can be either SALADBAR to SOFTICEBAR, but to future
     * proof the application an enumerator is being used, in case Bone's
     * wants to implement a third type of self-service then it is easy
     * to implement.
     *
     * @param barType followed by the new type of bar
     */
    public void setBarType(EnumBarType barType)
    {
        this.barType = barType;
    }


    /**
     * Gets the price of of this item during the lunch time period.
     *
     * Allow for the restaurant to change the prices based on whether
     * the time of day is in the restaurant's defined lunch hours.
     *
     * @return the lunch price of this item
     */
    public double getLunchPrice()
    {
        return this.lunchPrice;
    }


    /**
     * Sets the price of the bar during the lunch time period.
     *
     * Allows for the restaurant to change the prices based on whether
     * the time of day is in the restaurant's defined lunch hours.
     *
     * @param lunchPrice the new lunch price of this item
     */
    public void setLunchPrice(double lunchPrice)
    {
        this.lunchPrice = lunchPrice;
    }


    /**
     * Gets the price of this item during the evening time period.
     * 
     * Allow for the restaurant to change the prices based on whether
     * the time of day is in the restaurant's defined evening hours.
     *
     * @return the evening price of this item
     */
    public double getEveningPrice()
    {
        return this.eveningPrice;
    }


    /**
     * Sets the price of this item during the evening time period.
     *
     * Allows for the restaurant to change the prices based on whether
     * the time of day is in the restaurant's defined evening hours.
     *
     * @param eveningPrice the new evening price of this item
     */
    public void setEveningPrice(double eveningPrice)
    {
        this.eveningPrice = eveningPrice;
    }
}