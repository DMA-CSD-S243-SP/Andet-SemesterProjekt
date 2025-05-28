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
 * @version 28/05/2025 - 21:24
 */
public class TableOrder
{
	private int tableOrderId;
	private LocalDateTime timeOfArrival;
	private boolean isTableOrderClosed;
	private String paymentType;
	private double totalTableOrderPrice;
	private double totalAmountPaid;
	private boolean isSentToKitchen;
	private boolean isRequestingService;
	private int orderPreparationTime;

	private List<PersonalOrder> listOfPersonalOrders;

	
	/**
	 * Constructs a new TableOrder instance with tableOrderId as its identifier and
	 * initializes the personal order list as an empty ArrayList.
	 *
	 * 
	 * @param tableOrderId the unique identifier for this table order
	 * @param timeOfArrival the time when the first guest arrived
	 * @param isTableOrderClosed specifies whether the order is closed
	 * @param paymentType the type of payment method used
	 * @param totalTableOrderPrice the total price of the entire table order
	 * @param totalAmountPaid how much of the total price that has been paid for so far
	 * @param isSentToKitchen whether the order has been sent to to the kitchen for preparation
	 * @param isRequestingService whether the table has requested service
	 * @param orderPreparationTime the estimated time in seconds that the preparation of the food will take
	 */
	public TableOrder(int tableOrderId, LocalDateTime timeOfArrival, boolean isTableOrderClosed, String paymentType, double totalTableOrderPrice, double totalAmountPaid, boolean isSentToKitchen, boolean isRequestingService, int orderPreparationTime)
	{
		this.tableOrderId = tableOrderId;
		this.timeOfArrival = timeOfArrival;
		this.isTableOrderClosed = isTableOrderClosed;
		this.paymentType = paymentType;
		this.totalTableOrderPrice = totalTableOrderPrice;
		this.totalAmountPaid = totalAmountPaid;
		this.isSentToKitchen = isSentToKitchen;
		this.isRequestingService = isRequestingService;
		this.orderPreparationTime = orderPreparationTime;
		
		// Instantiates the listOfPersonalOrders array list
		this.listOfPersonalOrders = new ArrayList<>();
	}

	
	/**
	 * Sets the boolean state which indicates whether the table has requested
	 * service, and is waiting for a waiter to pay a visit to the table.
	 *
	 * @param isRequestingService true if the guests are requesting service, else it is set to false
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
	 * else it is false.
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
	 * @param isTableOrderClosed true if the table order is finalized and closed else it is set to false
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
	 * Sets the total price of the table's order, by accumulating the total prices
	 * of each PersonalOrder object associated with this TableOrder instance.
	 *
	 * @param totalTableOrderPrice the total price of a full table's order, based on 
	 * the total pricings of each personal order
	 */
	public void setTotalTableOrderPrice(double totalTableOrderPrice)
	{
		this.totalTableOrderPrice = totalTableOrderPrice;
	}

	
	/**
	 * Sets the total amount of the table order's price, that has already been paid.
	 *
	 * @param totalAmountPaid the amount received from the customer for this table order so far
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
	 * @param paymentType a string representing the payment method, "Credit Card" or "Cash Payment"
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
	 * first of the table guests started filling out their personal order details.
	 */
	public void setTimeOfArrival(LocalDateTime timeOfArrival)
	{
		this.timeOfArrival = timeOfArrival;
	}

	
	/**
	 * Returns the list of all PersonalOrder objects linked to this table order.
	 *
	 * @return list of personal orders for this table
	 */
	public List<PersonalOrder> getPersonalOrders()
	{
		// Creates a new list containing PersonalOrders stored within the returnList variable
		List<PersonalOrder> returnList = new ArrayList<PersonalOrder>();

		// Adds all of the PersonalOrder objects stored within the existing listOfPersonalOrders to the returnList 
		// essentially creating a copy of listOfPersonalOrders
		returnList.addAll(listOfPersonalOrders);

		// Returns the copy of the listOfPersonalOrders
		return returnList;
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
		// Creates a variable to store the total calculated price
		double calculatedTableOrderPrice = 0;

		// Retrieves the hour of the day when the tabel started placing orders and store it within the timeOfDay variable
		int timeOfDay = timeOfArrival.getHour();

		// Specifies when the time is perceived to be lunch time which is any time before 16:00
		boolean isLunch = (timeOfDay < 16);

		// If isLunch is true then execute this section
		if (isLunch == true)
		{
			// Loops through all of the personal orders within the listOfPersonalOrders
			for (PersonalOrder personalOrder : listOfPersonalOrders)
			{
				// Adds the personal order's lunch price to the value of the calculatedTableOrderPrice variable
				calculatedTableOrderPrice = calculatedTableOrderPrice + personalOrder.getTotalPersonalOrderLunchPrice();
			}
		}

		// If isLunch is false then execute this section
		else
		{
			// Loops through all of the personal orders within the listOfPersonalOrders
			for (PersonalOrder personalOrder : listOfPersonalOrders)
			{
				// Adds the personal order's evening price to the value of the calculatedTableOrderPrice variable
				calculatedTableOrderPrice += personalOrder.getTotalPersonalOrderEveningPrice();
			}
		}

		// Returns the total calculated table order price
		return calculatedTableOrderPrice;
	}
	
	
	/**
	 * Returns the unique identifier for this table order.
	 *
	 * @return the table order ID
	 */
	public int getTableOrderId()
	{
		return tableOrderId;
	}

	
	/**
	 * Returns the timestamp for when the first guest at the table placed an order.
	 *
	 * @return time of arrival as LocalDateTime
	 */
	public LocalDateTime getTimeOfArrival()
	{
		return timeOfArrival;
	}

	
	/**
	 * Returns whether the table order is marked as closed.
	 *
	 * @return true if the order is closed, false otherwise
	 */
	public boolean isTableOrderClosed()
	{
		return isTableOrderClosed;
	}

	
	/**
	 * Returns the payment type used for this table order.
	 *
	 * @return payment method as a String (e.g. "Credit Card", "Cash")
	 */
	public String getPaymentType()
	{
		return paymentType;
	}

	
	/**
	 * Returns the total amount already paid for this table order.
	 *
	 * @return the amount paid by the guests so far
	 */
	public double getTotalAmountPaid()
	{
		return totalAmountPaid;
	}

	
	/**
	 * Returns whether the table order has been sent to the kitchen.
	 *
	 * @return true if the order has been dispatched to the kitchen
	 */
	public boolean isSentToKitchen()
	{
		return isSentToKitchen;
	}

	
	/**
	 * Returns whether the guests at the table are requesting service.
	 *
	 * @return true if the table has requested service
	 */
	public boolean isRequestingService()
	{
		return isRequestingService;
	}


	/**
	 * Returns the total estimated preparation time for the order.
	 *
	 * @return preparation time in seconds
	 */
	public int getOrderPreparationTime()
	{
		return orderPreparationTime;
	}

	
	/**
	 * Returns the total calculated price for the table's complete order.
	 *
	 * @return total table order price
	 */
	public double getTotalTableOrderPrice()
	{
		return totalTableOrderPrice;
	}
}
