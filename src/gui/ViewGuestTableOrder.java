package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 02:11
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
	
	
	/**
	 * Create the frame.
	 */
	public ViewGuestTableOrder()
	{
		initGUI();
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
		
		
		
		
		// Creates a list of menu items selected by the customer
		List<String> sanneItems = List.of(
		    "Spareribs, Lille.",
		    "Bagt Kartoffel m. Hvidløgssmør",
		    "Sodavand, Refill"
		);

		// Adds vertical spacing above the item
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		// Adds the panel that holds the order information
		primaryContentPanel.add(new ComponentGuestOrderSummary("Sanne", 129, sanneItems));

		// Adds vertical spacing before the next order
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		
		
		// Creates a list of menu items selected by the customer
		List<String> borgeItems = List.of(
		    "Bag Kartoffel m. Creme Fraiche",
		    "Softice Ad Libitum",
		    "Øl, Refill"
		);
		
		// Adds vertical spacing above the item
		primaryContentPanel.add(new ComponentGuestOrderSummary("Børge", 239, borgeItems));

		// Adds the panel that holds the order information
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 45)));

		// Adds vertical spacing before the next order
		primaryContentPanel.add(new ComponentGuestOrderTotalPrice("Total Pris:", 239));
		
		
		
		
		
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
			// Creates the new frame that should be opened when pressing the button
			ViewGuestOrderMore nextView = new ViewGuestOrderMore();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnSendToKitchen = new ComponentGuestButtonContinue("Start Tilberedningen", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnSendToKitchen);
		
		// Adds an action listener for when the button is clicked
		btnSendToKitchen.addActionListener(event ->
		{
			// Creates the new frame that should be opened when pressing the button
			ViewGuestTableOrderConfirmation nextView = new ViewGuestTableOrderConfirmation();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
}