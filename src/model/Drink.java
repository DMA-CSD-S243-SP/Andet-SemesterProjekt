package model;

/**
 * Represents a drink menu item that can be ordered at a bone's
 * restaurant.
 * 
 * This class is a child/sub-class extending the abstract MenuItem class, and 
 * includes information on whether the item is a drink, along with whether the
 * drink is alcoholic and is refillable and the price.
 * 
 * 
 * @author Anders Trankjær
 * @version 09-05-2025 - 9:30
 */
public class Drink extends MenuItem
{
private boolean isAlcoholic;
private boolean isRefill;
private double price;


/**
 * the constructor for this subclass calls the constructor for the superclass to initialize it.
 * 
 * @param isAlcoholic - whether the drink is alcoholic.
 * @param isRefill - whether the drink is allowed to be refilled
 * @param price - the price of the item
 */
public Drink(boolean isAlcoholic, boolean isRefill, double price)
{
	super();
	
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
}
