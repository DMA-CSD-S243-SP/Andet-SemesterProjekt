package gui;

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
import database.PersonalOrderDB;
import database.PersonalOrderImpl;
import database.TableOrderImpl;
import model.PersonalOrder;
import model.TableOrder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard & Anders Trankjær
 * @version 28/05/2025 - 09:40
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
	
	private List<PersonalOrder> personalOrderList;
	private TableOrder currentTableOrder;
	private PersonalOrderImpl daoPO;
	private PersonalOrderController personalController;
	
	
	/**
	 * Create the frame.
	 */
	public ViewGuestTableOrder()
	{
		personalOrderList = new ArrayList<PersonalOrder>();
		code();
		initGUI();
	}
	
	
	private void code() {
		
		//tableOrder code
		currentTableOrder = UtilityGuestInformation.getInstance().getTableOrder();
		currentTableOrder.setTimeOfArrival(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
		
		//personalOrder code
		daoPO = new PersonalOrderDB();
		try {
			//findPersonalOrderByTableOrderId retrieves a list of all personalOrders in a given tableOrder the return type is List
			//it then adds it to personalOrderList
			personalOrderList.addAll(personalController.findPersonalOrdersBytableOrderId(currentTableOrder.getTableOrderId()));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}


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
		
		/*
		// Creates a customized input field object with a placeholder text accepting only numbers in it
		ComponentGuestInputField inputFieldFirstName = new ComponentGuestInputField("Fornavn", "onlyNumbers");
		
		// Adds the first name input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldFirstName);
		
		// Adds some spacing between the component above and below
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Creates a customized input field object with a placeholder text accepting only letters in it
		ComponentGuestInputField inputFieldAge = new ComponentGuestInputField("Alder i antal år", "onlyLetters");

		// Adds the age input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldAge);
		*/
		
		// makes a dummyTableOrder which for the purpose of calculating totalPrice 
		TableOrder dummyTableOrder = new TableOrder(currentTableOrder.getTableOrderId());
		dummyTableOrder.setTimeOfArrival(currentTableOrder.getTimeOfArrival());
		//this loop displays each personalOrder from the currentTableOrder
		for (PersonalOrder pO : personalOrderList) 
		{
			// adds the name of the customer whoes personalOrder it is 
			primaryContentPanel.add(new ComponentGuestOrderSummary(pO.getCustomerName(), pO.getTotalPersonalOrderEveningPrice(), pO.getNameOfItemsInList()));

			// Adds the panel that holds the order information
			primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 45)));
			//adds the personalOrder to the tableOrder
			dummyTableOrder.addPersonalOrder(pO);
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
			// TODO: Add functionality
			try
			{
				throw new UnsupportedOperationException("Bestil Mere is not implemented.");
			}
			
			catch (Exception exception)
			{
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
//				ViewGuestOrderMore nextView = new ViewGuestOrderMore();
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
			// Creates the new frame that should be opened when pressing the button the new Jframe carries over the tableOrder which was edited in this Jframe
			ViewGuestTableOrderConfirmation nextView = new ViewGuestTableOrderConfirmation(currentTableOrder);

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
}