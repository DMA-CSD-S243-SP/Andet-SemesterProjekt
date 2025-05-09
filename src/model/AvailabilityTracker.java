package model;

/*
 * The availabilityTracker class helps show the availabilty of certain items.
 * 
 * @Author Anders Trankjær
 * @Version 2025/09/05/9:00
 */
public class AvailabilityTracker
{
	private boolean isAvailable;
	private MenuItem menuItem;
	private int availabilityTrackerId;
	
	public AvailabilityTracker(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}
	
	/**
	 * @return the menuItem
	 */
	public MenuItem getMenuItem()
	{
		return menuItem;
	}
	
	/**
	 * @param newMenuItem the new MenuItem to be set
	 */
	public void setMenuItem(MenuItem newMenuItem)
	{
		this.menuItem = newMenuItem;
	}
	
	/**
	 * @return isAvailable
	 */
	public boolean isAvailable()
	{
		return isAvailable;
	}
	
	/**
	 * @param isAvailable the new isAvailable to be set
	 */
	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}
	
	/**
	 * @return availabilityTrackerId
	 */
	public int getAvailabilityTrackerId()
	{
		return availabilityTrackerId;
	}
	
	/**
	 * @param newAvailabilityTrackerId the new availabilityTrackerId to be set
	 */
	public void setAvailabilityTrackerId(int newAvailabilityTrackerId)
	{
		this.availabilityTrackerId = newAvailabilityTrackerId;
	}
}
