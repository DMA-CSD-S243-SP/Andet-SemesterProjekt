package model;


/**
 * Represents an item on the menu card at one of Bone's restaurants.
 * 
 * A MenuItem is an abstract class, and therefore cannot be instantiated on its own.
 * The MenuItem defines attributes that are common and used by all of the MenuItem's
 * children / sub-classes, such as name, description, item preparation time etc and
 * so forth.
 * 
 * This class is intended to be extended by concrete menu item types.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 12-05-2025 - 15:09
 */
public abstract class MenuItem
{
    private int menuItemId;
    private int preparationTime;
    
    private String name;
    private String description;

    private boolean isMadeByKitchenStaff;


    /**
     * Constructs a new MenuItem with default values.
     * This constructor is used by the MenuItem's child and sub-classes when initializing
     * any new specific item.
     * Since this class is abstract, it cannot be instantiated directly.
     */
    public MenuItem()
    {
        // Default constructor
    }


    /**
     * Returns the unique ID of the MenuItem instance.
     *
     * @return the menu item ID
     */
    public int getMenuItemId()
    {
        return this.menuItemId;
    }


    /**
     * Sets the unique ID of the menu item.
     *
     * @param menuItemId the unique ID to assign
     */
    public void setMenuItemId(int menuItemId)
    {
        this.menuItemId = menuItemId;
    }

    
    /**
     * Returns the preparation time required for this item.
     *
     * @return preparation time in minutes
     */
    public int getPreparationTime()
    {
        return this.preparationTime;
    }


    /**
     * Sets the preparation time required for this item.
     *
     * @param preparationTime time in minutes to prepare the item
     */
    public void setPreparationTime(int preparationTime)
    {
        this.preparationTime = preparationTime;
    }
    
    
	/**
	 * Gets the price of the MenuItem's subclasses / child classes during the lunch time period.
	 *
	 * This method must be implemented by all concrete subclasses
	 * and defines context-dependent pricing.
	 *
	 * @return the price for the MenuItem subclass during the lunch hours
	 */
	public abstract double getLunchPrice();


	/**
	 * Gets the price of the MenuItem's subclass/child class during the evening time period.
	 *
	 * Because this is an abstract method, this method must be implemented by all of MenuItem's
	 * subclasses /child classes.
	 *
	 * @return the price for the MenuItem subclass during the evening hours
	 */
	public abstract double getEveningPrice();
    

    /**
     * Returns the MenuItem's name.
     *
     * @return the name of the item
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * Sets MenuItem's name.
     *
     * @param the name to assign to the menu item
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Returns the description of the menu item.
     *
     * @return the description text in the form of a string
     */
    public String getDescription()
    {
        return this.description;
    }


    /**
     * Sets the description of the menu item.
     *
     * @param description a string that descripes the item in detail
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    /**
     * Gets whether this menuItem should be prepared by the kitchen staff,
     * or if it is an item that has no need to be be sent to the kitchen.
     *
     * @return true if prepared by kitchen staff, else returns falls
     */
    public boolean isMadeByKitchenStaff()
    {
        return this.isMadeByKitchenStaff;
    }


    /**
     * Sets whether this menuItem should be prepared by the kitchen staff,
     * or if it is an item that has no need to be be sent to the kitchen.
     *
     * @param isMadeByKitchenStaff true if it should be prepared by the kitchen, else set to false
     */
    public void setMadeByKitchenStaff(boolean isMadeByKitchenStaff)
    {
        this.isMadeByKitchenStaff = isMadeByKitchenStaff;
    }
}