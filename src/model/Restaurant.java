package model;

import java.util.ArrayList;
import java.util.List;

/*
 * The Restaurant class represents a physical location.
 * @Author Lumière Schack
 * @Version 2025/08/05/9:45
 */
public class Restaurant
{
	private String restaurantCode;
	private String name;
	private String city;
	private String streetName;
	private List<Table> listOfTables;
	private List<MenuCard> listOfMenuCards;

	public Restaurant(String name, String city, String streetName)
	{
		listOfTables = new ArrayList<>();
		listOfMenuCards = new ArrayList<>();
		this.name = name;
		this.city = city;
		this.streetName = streetName;
	}

	/**
	 * @return the restaurantCode
	 */
	public String getRestaurantCode()
	{
		return restaurantCode;
	}

	/**
	 * @param restaurantCode the restaurantCode to set
	 */
	public void setRestaurantCode(String restaurantCode)
	{
		this.restaurantCode = restaurantCode;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * Adds a Table to the Restaurant.
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
	 * Removes a Table from the Restaurant. If the Table isn't associated with the
	 * Restaurant, nothing happens.
	 * 
	 * @param table - The Table to be removed.
	 */
	public void removeTable(Table table)
	{
		//table.setTableCode(0, "000");
		listOfTables.remove(table);
	}

	/**
	 * Gets a List of every Table associated with this Restaurant.
	 * @return a List of Table objects.
	 */
	public List<Table> getTables()
	{
		// A new list is made to encapsulate the true list from outside interference.
		return new ArrayList<Table>(listOfTables);
	}

	/**
	 * Adds a MenuCard to the Restaurant.
	 * @param menuCard the MenuCard to be added.
	 */
	public void addMenuCard(MenuCard menuCard)
	{
		listOfMenuCards.add(menuCard);
	}

	/**
	 * Removes a MenuCard from the Restaurant.
	 * @param menuCard - The MenuCard to be removed.
	 */
	public void removeMenuCard(MenuCard menuCard)
	{
		listOfMenuCards.remove(menuCard);
	}

	/**
	 * Gets a List of every MenuCard associated with this Restaurant.
	 * @return a List of MenuCard objects.
	 */
	public List<MenuCard> getMenuCards()
	{
		// A new list is made to encapsulate the true list from outside interference.
		return new ArrayList<MenuCard>(listOfMenuCards);
	}
}
