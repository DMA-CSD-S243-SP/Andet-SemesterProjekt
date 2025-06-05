// Packages
package model;

//Imports
import java.util.ArrayList;
import java.util.List;


/*
 * The Restaurant class represents a physical Restaurant and its 
 * location in the world.
 * 
 * This class is used to uniquely identify the Restaurant in question
 * and hold a list of the specific Restaurant's MenuCards and tables.
 * 
 * @author Lumière Schack & Christoffer Søndergaard
 * @version 04/06/2025 - 10:07
 */
public class Restaurant
{
	private String restaurantCode;
	private String name;
	private String city;
	private String streetName;
	private List<Table> listOfTables;
	private List<MenuCard> listOfMenuCards;

	
	/**
	 * Constructs a new Restaurant object with the specified name, city, street name,
	 * and initializes empty lists to store Tables and MenuCards.
	 *
	 * @param name        the name of the restaurant
	 * @param city        the city where the restaurant is located
	 * @param streetName  the street name where the restaurant is located
	 */
	public Restaurant(String name, String city, String streetName)
	{
		this.name = name;
		this.city = city;
		this.streetName = streetName;
		
		// Initializes the lists to hold the Restaurant's associated tables and MenuCard objects
		listOfTables = new ArrayList<>();
		listOfMenuCards = new ArrayList<>();
	}

	
	/**
	 * Gets the uniquely identifiable code for this specific Restaurant instance.
	 * 
	 * @return the restaurantCode
	 */
	public String getRestaurantCode()
	{
		return restaurantCode;
	}

	
	/**
	 * Sets the uniquely identifiable code for this specific Restaurant instance.
	 * 
	 * @param restaurantCode the restaurantCode to set
	 */
	public void setRestaurantCode(String restaurantCode)
	{
		this.restaurantCode = restaurantCode;
	}

	
	/**
	 * Gets the name of this specific Restaurant.
	 * 
	 * Making it easily identifiable in the rare case where there are
	 * two Bone's located on the same street in the same city.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	
	/**
	 * Sets the name of this specific Restaurant.
	 * 
	 * Making it easily identifiable in the rare case where there are
	 * two Bone's located on the same street in the same city.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	
	/**
	 * Gets the name of the city that this Restaurant is within.
	 * 
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	
	/**
	 * Sets the name of the city that this Restaurant is within.
	 * 
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	
	/**
	 * Gets the name of the street that this Restaurant is located at.
	 * 
	 * @return the streetName
	 */
	public String getStreetName()
	{
		return streetName;
	}

	
	/**
	 * Sets the name of the street that this Restaurant is located at.
	 * 
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	
	/**
	 * Adds a Table to the specific Restaurant object's list of tables.
	 * 
	 * @param table - The Table to be added
	 */
	public void addTable(Table table)
	{
		//int tableNumber = table.getTableNumber();
		//table.setTableCode(tableNumber, restaurantCode);
		listOfTables.add(table);
	}

	
	/**
	 * Removes a Table from the Restaurant.
	 * 
	 * If the Table isn't associated with the Restaurant, nothing happens.
	 * 
	 * @param table - The Table to be removed.
	 */
	public void removeTable(Table table)
	{
		//table.setTableCode(0, "000");
		listOfTables.remove(table);
	}

	
	/**
	 * Gets a List that contains every table Table object that is 
	 * associated with the specific Restaurant.
	 * 
	 * @return a List of Table objects.
	 */
	public List<Table> getTables()
	{
		// A new list is made to encapsulate the true list from outside interference.
		return new ArrayList<Table>(listOfTables);
	}

	
	/**
	 * Adds a MenuCard to the specific Restaurant's list of menu cards.
	 * 
	 * @param menuCard the MenuCard to be added.
	 */
	public void addMenuCard(MenuCard menuCard)
	{
		listOfMenuCards.add(menuCard);
	}

	
	/**
	 * Removes a MenuCard to the specific Restaurant's list of menu cards.
	 * 
	 * @param menuCard - The MenuCard to be removed.
	 */
	public void removeMenuCard(MenuCard menuCard)
	{
		listOfMenuCards.remove(menuCard);
	}

	
	/**
	 * Gets a List containing every MenuCard that is associated with this
	 * specific Restaurant.
	 * 
	 * @return a List of MenuCard objects.
	 */
	public List<MenuCard> getMenuCards()
	{
		// A new list is made to encapsulate the true list from outside interference.
		return new ArrayList<MenuCard>(listOfMenuCards);
	}
}
