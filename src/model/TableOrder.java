// Packages
package model;

// Imports
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents the order of an entire table of guests sitting in one of Bone´s
 * restaurants, a table order consists of everybody at the table's individual
 * orders.
 * 
 * A table order consists of a list of personal orders, and various information
 * regarding the entire table's order, such as the first table's guest's time of
 * arrival, the chosen type of payment, status of the order status, payment
 * type, including arrival time, the price of the entire table order and how
 * much of this is already paid by the guests at the table.
 * 
 * Each TableOrder is uniquely identifiable by their tableOrderId.
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 09-05-2025 - 09:10
 */
public class TableOrder
{
	private LocalDateTime timeOfArrival;
	private boolean isTableOrderClosed;
	private String paymentType;
	private double totalTableOrderPrice;
	private double totalAmountPaid;
	private boolean isSentToKitchen;
	private boolean isRequestingService;
	private int orderPreparationTime;
	

	private int tableOrderId;	

	private List<PersonalOrder> listOfPersonalOrders;

	/**
	 * Constructs a new TableOrder instance with tableOrderId as its identifier and
	 * initializes the personal order list as an empty ArrayList.
	 *
	 * @param tableOrderId the unique identifier for this table order
	 */
	public TableOrder(int tableOrderId)
	{
		this.tableOrderId = tableOrderId;

		this.listOfPersonalOrders = new ArrayList<>();
	}

	/**
	 * Sets the boolean state which indicates whether the table has requested
	 * service, and is waiting for a waiter to pay a visit to the table.
	 *
	 * @param isRequestingService true if the guests are requesting service, else it
	 *                            is set to false
	 */
	public void setRequestingService(boolean isRequestingService)
	{
		this.isRequestingService = isRequestingService;
	}

	/**
	 * Sets the boolean of the table order, to clearly show whether a table of
	 * guests have sent their first table order out in the kitchen. Once a table
	 * order has been sent to the kitchen the status is changed to true and will
	 * remain in this state, for the rest of the table's guests dining session.
	 *
	 * @param isSentToKitchen is set to true if the table has sent out the table
	 *                        else it is false.
	 */
	public void setSentToKitchen(boolean isSentToKitchen)
	{
		this.isSentToKitchen = isSentToKitchen;
	}

	/**
	 * Sets the closed status of the table order. When the isTableOrderClosed
	 * boolean is set to true, it means that the table has finished their dining
	 * session and no longer wants to order, and are ready to begin the payment
	 * process of their dining session.
	 *
	 * @param isTableOrderClosed true if the table order is finalized and closed;
	 *                           false otherwise
	 */
	public void setTableOrderClosed(boolean isTableOrderClosed)
	{
		this.isTableOrderClosed = isTableOrderClosed;
	}

	/**
	 * Sets the estimated preparation time required for the table's full order, this
	 * is being set in the form of an integer to illustrate seconds, the reason for
	 * this is that seconds level of precision is perceived as a fine enough level
	 * of estimation for this type of task.
	 *
	 * @param orderPreparationTime the estimated preparation time in minutes
	 */
	public void setOrderPreparationTime(int orderPreparationTime)
	{
		this.orderPreparationTime = orderPreparationTime;
	}

	/**
	 * calculates the estimated time in seconds it would take to prepare the entire
	 * table's order, by summarizing each PersonalOrder instance's preparation time,
	 * adding the expected delay from before the kitchen receives the order in their
	 * system, and accounts for starting on a new personal order, by adding 15
	 * seconds per PersonalOrder.
	 *
	 * @return the estimated time it takes for the table order to be prepared.
	 */
	/*
	 * TODO: Uncomment this line, when working more detailed on preparation time,
	 * and reference it in the getOrderPreparationTime method public int
	 * calculateTableOrderPreparationTime() { // The maximum of time in seconds we
	 * expect it take before the information is relayed and displayed in the kitchen
	 * int potentialQueueTime = 30;
	 * 
	 * // The time in seconds that is expected to be added for each individual
	 * personal order that will be prepared int personalOrderMultiplier = 0;
	 * 
	 * // The time in seconds of the personal order that takes the longest to
	 * prepare int longestPersonalOrderPreparationTime = 0;
	 * 
	 * // The time it is expected to take to prepare the entire table's meal int
	 * tableOrderPreparationTime = 0;
	 * 
	 * // Iterates through the list of personal orders for (PersonalOrder
	 * personalOrder : listOfPersonalOrders) { // If the personalOrder takes longer
	 * time to prepare than the current value of longestPersonalOrderPreparationTime
	 * then execute this section if(personalOrder.getPreparationTime() >
	 * longestPersonalOrderPreparationTime) { // Sets the value of the
	 * longestPersonalOrderPreparationTime variable to be the same as the
	 * personalOrder's preparation time longestPersonalOrderPreparationTime =
	 * personalOrder.getPreparationTime(); } }
	 * 
	 * // Adds additional seconds to the preparation time based on each personal
	 * order in the list personalOrderMultiplier = listOfPersonalOrders.size() * 15;
	 * 
	 * // Adds the time values in seconds together to find the total time in seconds
	 * it will take before the meal is ready tableOrderPreparationTime =
	 * tableOrderPreparationTime + potentialQueueTime + personalOrderMultiplier;
	 * 
	 * return tableOrderPreparationTime; }
	 */

	/**
	 * Sets the total price of the table's order, by accumulating the total prices
	 * of each PersonalOrder object associated with this TableOrder instance.
	 *
	 * @param totalTableOrderPrice the total price of a full table's order, based on
	 *                             the total pricings of each personal order
	 */
	public void setTotalTableOrderPrice(double totalTableOrderPrice)
	{
		this.totalTableOrderPrice = totalTableOrderPrice;
	}

	/**
	 * Sets the total amount of the table order's price, that has already been paid.
	 *
	 * @param totalAmountPaid the amount received from the customer for this table
	 *                        order so far
	 */
	public void setTotalAmountPaid(double totalAmountPaid)
	{
		this.totalAmountPaid = totalAmountPaid;
	}

	/**
	 * Sets the payment method that the paying guest has chosen to pay for this
	 * table order. Representing payment choices such as credit card payment or cash
	 * payment.
	 *
	 * @param paymentType a string representing the payment method, 'Credit Card' og
	 *                    'Cash Payment'
	 */
	public void setPaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}

	/**
	 * Sets the time of arrival based on the first guest of the table, to have their
	 * personal order associated with the table order.
	 *
	 * @param timeOfArrival the LocalDateTime timestamp which indicates when the
	 *                      first of the table guests started filling out their
	 *                      personal order details.
	 */
	public void setTimeOfArrival(LocalDateTime timeOfArrival)
	{
		this.timeOfArrival = timeOfArrival;
	}

	/**
	 * Adds a PersonalOrder object to the list of the individual personal orders,
	 * that are associated with this TableOrder instance.
	 *
	 * @param personalOrder the personal order to add to the list
	 */
	public void addPersonalOrder(PersonalOrder personalOrder)
	{
		this.listOfPersonalOrders.add(personalOrder);
	}
	
	public List<PersonalOrder> getPersonalOrders()
	{
		List<PersonalOrder> returnList = new ArrayList<PersonalOrder>();
		returnList.addAll(listOfPersonalOrders);
		return returnList;
	}

	/**
	 * Uses the timeOfArrival to determine whether or not it should use lunch or
	 * evening prices. Iterates through and summarizes the total price either by the
	 * getTotalPersonalOrderLunchPrice method, or by the
	 * getTotalPersonalOrderEveningPrice method.
	 * 
	 * @return the calculated price of the entire TableOrder
	 */
	public double calculateTotalTableOrderPrice()
	{
		double calculatedTableOrderPrice = 0;
		int timeOfDay = timeOfArrival.getHour();
		boolean isLunch = (timeOfDay < 16); // Lunch prices are given if guests arrive before 16:00

		if (isLunch)
		{
			// Calculate by using lunch price, if TableOrder started at lunch time.
			for (PersonalOrder personalOrder : listOfPersonalOrders)
			{
				calculatedTableOrderPrice = calculatedTableOrderPrice + personalOrder.getTotalPersonalOrderLunchPrice();
			}
		} else
		{
			// Otherwise use evening price.
			for (PersonalOrder personalOrder : listOfPersonalOrders)
			{
				calculatedTableOrderPrice = calculatedTableOrderPrice
						+ personalOrder.getTotalPersonalOrderEveningPrice();
			}
		}
		return calculatedTableOrderPrice;
	}
	
	public int getTableOrderId()
	{
		return tableOrderId;
	}

	public LocalDateTime getTimeOfArrival()
	{
		return timeOfArrival;
	}

	public boolean isTableOrderClosed()
	{
		return isTableOrderClosed;
	}

	public String getPaymentType()
	{
		return paymentType;
	}

	public double getTotalAmountPaid()
	{
		return totalAmountPaid;
	}

	public boolean isSentToKitchen()
	{
		return isSentToKitchen;
	}

	public boolean isRequestingService()
	{
		return isRequestingService;
	}

	public int getOrderPreparationTime()
	{
		return orderPreparationTime;
	}
	
	public double getTotalTableOrderPrice()
	{
		return totalTableOrderPrice;
	}
}
