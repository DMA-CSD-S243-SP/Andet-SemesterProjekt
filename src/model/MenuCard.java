//Packages
package model;

// Imports
import java.util.List;
import java.util.ArrayList;


/**
* Represents a physical menu card in the real world at a Bone's restaurant.
* 
* A MenuCard instance has a unique ID and a given name, and contains a 
* bunch of AvailabilityTracker instances, to control the menu item visibility 
* on the respective menu card, to disable / enable the possibility of ordering 
* certain menu items from the menu card, by simply making them invisible to the 
* restaurant's guests.
* 
* 
* @author Christoffer Søndergaard & Anders Trankjær
* @version 29-05-2025 - 11:55
*/
public class MenuCard
{
	 private int menuCardId;
	 private String name;
	 private List<AvailabilityTracker> listOfAvailabilityTrackers;


	 /**
	  * Constructs a new MenuCard instance using the specified menu card name parameter
	  * and initializes the availability tracker list an empty ArrayList.
	  *
	  * @param name the name given to this menu card, e.g., "Juniormenu" or "Voksenmenu"
	  */
	 public MenuCard(String name)
	 {
	     this.name = name;
	
	     // Initializes the listOfAvailabilityTrackers as an empty ArrayList.
	     this.listOfAvailabilityTrackers = new ArrayList<>();
	 }
	
	
	 /**
	  * Gets the unique ID of this menu card.
	  *
	  * @return the ID of this menu card
	  */
	 public int getMenuCard()
	 {
	     return this.menuCardId;
	 }
	
	
	 /**
	  * Sets the unique ID of this menu card.
	  *
	  * @param menuCardId the ID to be assigned to this menu card
	  */
	 public void setMenuCard(int menuCardId)
	 {
	     this.menuCardId = menuCardId;
	 }
	
	
	 /**
	  * Adds an availability tracker to the list of availability trackers that are
	  * associated with this menu card instance.
	  *
	  * @param availabilityTracker the availability tracker object to add to the list
	  */
	 public void addAvailabilityTracker(AvailabilityTracker availabilityTracker)
	 {
	     this.listOfAvailabilityTrackers.add(availabilityTracker);
	 }
	 
	 
	 /**
	  * finds all the available menuItems objects in the menuCards list of availabilitytrackers
	  * 
	  * @return a list of all menuitems which are available
	  */
	 public List<MenuItem> getAvailableMenuItems()
	 {
		 //initializes a new list which will be returned 
		 List<MenuItem> listOfMenuItems = new ArrayList<>();
		 
		 //the loop runs through all AvailabiltyTrackers in a menu and sees which MenuItems are available 
		 for (AvailabilityTracker availabilityTracker: listOfAvailabilityTrackers)
		 {
			 if (availabilityTracker.isAvailable())
			 {
				 // adds the available menuItems to the returnlist
				 listOfMenuItems.add(availabilityTracker.getMenuItem());
			 }
		 }
		 
		 return listOfMenuItems;
	 }
	 
	 
	 /**
	  *  retrieves the name variable of the MenuCard
	  *  
	  * @return name
	  */
	 public String getName()
	 {
		 return name;
	 }
}