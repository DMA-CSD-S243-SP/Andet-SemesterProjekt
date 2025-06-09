package gui;

//Imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.PersonalOrderController;
import database.DataAccessException;
import model.PersonalOrder;
import model.TableOrder;


/**
 * The ViewGuestTableOrder class displays the full overview of all personal orders 
 * associated with the current table order.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * The purpose of this view is to give the guests a final look at their entire 
 * group's order before submitting it for preparation. The interface lists 
 * each individual's order along with pricing and provides a total for the table.
 * 
 * 
 * @author Christoffer Søndergaard & Anders Trankjær 
 * @version: 08/06/2025 - 21:27
 */	
public class ViewGuestTableOrder extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// The Jframes that will be retrieved from the guest frame theme and used for manipulating the contents
	private JPanel navigationPanel;
	private JPanel primaryContentPanel;
	private JPanel bottomPanel;
	
	// Determines whether or not this frame should have the "tilbage" button enabled in the navigational panel
	boolean isPreviousViewEnabled = true;
	
	// Determines whether or not the 'Anmod om service' button is enabled in the navigational panel
	boolean isServiceEnabled = true;
	
	//a list of personalOrders used for displaying what the customer has added to their order
	private List<PersonalOrder> personalOrderList;
	
	// a tableOrder which will be carried over to the next Jframe 
	private TableOrder currentTableOrder;
	
	private PersonalOrderController personalController;
	
	
	/**
	 * Constructs the ViewGuestTableOrder frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestTableOrder()
	{
		personalController = new PersonalOrderController();
		personalOrderList = new ArrayList<PersonalOrder>();
		
		initGUI();
	}


    /**
	 * Initializes the GUI components for this frame.
	 * 
	 * This includes:
	 * - Setting up the themed frame layout
	 * - Displaying navigation buttons (back and request service)
	 * - Creating a continue button that validates input and proceeds to the next view
	 * - Creates UI components dynamically based on the contents of the personalOrderList
     */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("BORDBESTILLING", this, "Hele Bordets Bestilling", "Her ses hele bordets bestilling. Send<br> først bestillingen afsted,	når alle<br> ved bordet har bestilt.");
		
		// Retrieves the navigationPanel component from the frameTheme
		navigationPanel = frameTheme.getNavigationPanel();
		
		// Retrieves the primary content panel component from the the frameTheme
		// - This is the panel that we add our varying contents to in the different view / windows
		primaryContentPanel = frameTheme.getPrimaryContentPanel();
		
		// Retrieves the bottomContentPanel component from the frameTheme
		bottomPanel = frameTheme.getBottomContentPanel();


		
		///////////////////////////////
		// - Primary Content Panel - //
		///////////////////////////////
		//   THIS IS WHERE CONTENT   //
		//   SHOULD BE INSERTED IN   //
		///////////////////////////////
		
		// Retrieves the current table order from the UtilityGuestInformation singleton,
	    // sets the arrival time, and fetches all personal orders associated with the TableOrder
	    // from the database using the PersonalOrderController.
		retrieveTableOrderContentsAndSetTimeOfArrival();
		
		// Makes a dummyTableOrder which for the purpose of calculating totalPrice 
		TableOrder dummyTableOrder = new TableOrder(currentTableOrder.getTableOrderId(), currentTableOrder.getTimeOfArrival(), false, "not decided", 0, 0, false, false, 0);
		
		// This loop displays each personalOrder from the currentTableOrder
		for (PersonalOrder personalOrder : personalOrderList) 
		{
			// Adds the name of the customer whose personalOrder it is aswell as the price of the individual personalOrder and a list of each item they have ordered. 
			primaryContentPanel.add(new ComponentGuestOrderSummary(personalOrder.getCustomerName(), personalOrder.getTotalPersonalOrderEveningPrice(), personalOrder.getNameOfItemsInList()));

			// Adds the panel that holds the order information
			primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 45)));
			
			// Adds the personalOrder to the tableOrder
			dummyTableOrder.addPersonalOrder(personalOrder);
		}
		
		//displays the total price of all the personalOrders within the TableOrder
		primaryContentPanel.add(new ComponentGuestOrderTotalPrice("Total Pris:", dummyTableOrder.calculateTotalTableOrderPrice()));
		
		
		
		////////////////////////////////
		// - Navigation Back Button - //
		////////////////////////////////
		
		
		// Creates a button with the specified text
		ComponentGuestNavigationButton btnBack = new ComponentGuestNavigationButton("← Tilbage");
		
		// If the isPreviousViewEnabled variable is set to true then execute this section
		if(isPreviousViewEnabled == true)
		{
			// Moves the button to the west side of the panel it is attached to
			navigationPanel.add(btnBack, BorderLayout.WEST);
			
			// Adds an action listener for when the button is clicked
			btnBack.addActionListener(event ->
			{
				// Create and configure the new frame (you can replace this with your actual destination window)
				ViewGuestMenuAdult previouslyOpenFrameView = new ViewGuestMenuAdult();
				
				// Sets the visibility to true turning the previous view / window visible
				previouslyOpenFrameView.setVisible(true);

				// Find and closes the current view / window
				this.dispose();
			});
		}
		
		
		///////////////////////////////////
		// - Navigation Service Button - //
		///////////////////////////////////
		
		// If the isServiceEnabled variable is set to true then execute this section
		if(isServiceEnabled == true)
		{
			// Creates a button with the specified text
			ComponentGuestNavigationButton btnRequestService = new ComponentGuestNavigationButton("Anmod Om Service 🔔");
			
			// Moves the button to the east side of the panel it is attached to
			navigationPanel.add(btnRequestService, BorderLayout.EAST);
		}
		

		//////////////////////////////
		// - Bottom Panel Buttons - //
		//////////////////////////////
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnOrderMore = new ComponentGuestButtonContinue("Bestil Mere", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnOrderMore);
	
		// Adds an action listener for when the button is clicked
		btnOrderMore.addActionListener(event ->
		{
			try
			{
				// TODO: Add functionality
				throw new UnsupportedOperationException("Bestil Mere is not implemented.");
			}
			
			catch (Exception exception)
			{
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(this, 
						"Følgende feature",
						"Bestil mere",
						"Er endnu ikke integreret i systemet"
				);
				
				exception.printStackTrace();
			}
			
			finally
			{
				// Creates the new frame that should be opened when pressing the button
				ViewGuestMenuAdult nextView = new ViewGuestMenuAdult();

				// Sets the visibility to true turning the previous view / window visible
				nextView.setVisible(true);
				
				// Closes the current frame/window
				this.dispose();				
			}
		});
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnSendToKitchen = new ComponentGuestButtonContinue("Start Tilberedningen", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnSendToKitchen);
		
		// Adds an action listener for when the button is clicked
		btnSendToKitchen.addActionListener(event ->
		{
			// Creates the new frame that should be opened when pressing the button the new Jframe carries over the tableOrder from this Jframe
			ViewGuestTableOrderConfirmation nextView = new ViewGuestTableOrderConfirmation();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
	
	
    /**
     * Retrieves the current table order from the UtilityGuestInformation singleton,
     * sets the arrival time, and fetches all personal orders associated with the TableOrder
     * from the database using the PersonalOrderController.
     * 
     * The resulting personal orders are stored in the personalOrderList field
     * for display and further processing.
     */
	private void retrieveTableOrderContentsAndSetTimeOfArrival()
	{
		// tableOrder code
		currentTableOrder = UtilityGuestInformation.getInstance().getTableOrder();
		
		// 
		currentTableOrder.setTimeOfArrival(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
		
		//personalOrder code
		try
		{
			// findPersonalOrderByTableOrderId retrieves a list of all personalOrders in a given tableOrder the return type is List
			// it then adds it to personalOrderList
			personalOrderList.addAll(personalController.findPersonalOrdersBytableOrderId(currentTableOrder.getTableOrderId()));
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		
		catch (DataAccessException e)
		{
			e.printStackTrace();
		}
	}
}