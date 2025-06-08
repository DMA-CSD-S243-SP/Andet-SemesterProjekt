package gui;

//Imports
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The ViewGuestPaymentMethod class displays the GUI where guests can select their
 * preferred method of payment for the current table order.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * This view allows the user to choose between different payment options such as
 * cash or credit card. Once selected, the user proceeds to a tipping screen.
 * 
 * 
 * @author: Christoffer Søndergaard
 * @version: 08/06/2025 - 21:48
 */
public class ViewGuestPaymentMethod extends JFrame
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
	 * Constructs the ViewGuestPaymentMethod frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestPaymentMethod()
	{
		initGUI();
	}
	
	
    /**
	 * Initializes the GUI components for this frame.
	 * 
	 * This includes:
	 * - Setting up the themed frame layout
	 * - Displaying navigation buttons (back and request service)
	 * - Creating a continue button that validates input and proceeds to the next view
	 * - Buttons for selecting the preferred payment method.
     */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("BETALINGSMETODE", this, "Vælg Betalingsmetode", "Vælg betalingsmetoden du vil bruge<br> til at betale ved kassen.");
		
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
				ViewGuestOrderOverview previouslyOpenFrameView = new ViewGuestOrderOverview();
				
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
		ComponentGuestButtonContinue btnCashPayment = new ComponentGuestButtonContinue("Kontantbetaling", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnCashPayment);
	
		// Adds an action listener for when the button is clicked
		btnCashPayment.addActionListener(event ->
		{
			// TODO: Modifies the TableOrder object's paymentType attribute
			continueToNextView();
		});
		
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnCreditCard = new ComponentGuestButtonContinue("Betalingskort", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnCreditCard);
	
		// Adds an action listener for when the button is clicked
		btnCreditCard.addActionListener(event ->
		{
			// TODO: Modifies the TableOrder object's paymentType attribute
			
			// Creates the new frame and disposes of the current one
			continueToNextView();
		});
	}
	
	
    /**
     * Launches the tipping view and closes the current frame.
     * 
     * This method is called when the user has selected a payment method and is ready
     * to proceed to tipping.
     */
	private void continueToNextView()
	{
		// Creates the new frame that should be opened when pressing the button
		ViewGuestTipping nextView = new ViewGuestTipping();

		// Sets the visibility to true turning the previous view / window visible
		nextView.setVisible(true);
		
		// Closes the current frame/window
		this.dispose();
	}
}