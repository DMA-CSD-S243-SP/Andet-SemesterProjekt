package gui;

//Imports
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * ViewGuestOrderOverview is responsible for displaying the order confirmation
 * screen to the guest after their table order has been submitted.
 *
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 *
 * This screen informs the guest of the estimated preparation time and offers
 * follow-up actions such as creating a new order, reordering a potato dish, or
 * proceeding to payment.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 08/06/2025 - 21:07
 */	
public class ViewGuestOrderOverview extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// The Jframes that will be retrieved from the guest frame theme and used for manipulating the contents
	private JPanel navigationPanel;
	private JPanel primaryContentPanel;
	private JPanel bottomPanel;
	
	// Determines whether or not this frame should have the "tilbage" button enabled in the navigational panel
	boolean isPreviousViewEnabled = false;
	
	// Determines whether or not the 'Anmod om service' button is enabled in the navigational panel
	boolean isServiceEnabled = true;
	
	
	/**
	 * Constructs the ViewGuestOrderOverview frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestOrderOverview()
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
     * - Options for creating a new order, reordering a potato dish and proceeding to payment
     *
     * Each button is tied to an action listener that opens the relevant view
     * and closes the current frame.
     */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("ORDREOVERSIGT", this, "Forventet Behandlingstid", "Bordets ordre er sendt ud til køkkenet og<br> tilberedes straks og leveres snarest.<br><br>Ordren forventes at være færdigtilberedt<br>og klar til servering inden for<br><br>5 Minutter");
		
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
				ViewGuestTableOrderConfirmation previouslyOpenFrameView = new ViewGuestTableOrderConfirmation();
				
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
		ComponentGuestButtonContinue btnCreateNewOrder = new ComponentGuestButtonContinue("Opret Ny Bestilling", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnCreateNewOrder);
	
		// Adds an action listener for when the button is clicked
		btnCreateNewOrder.addActionListener(event ->
		{
			// Creates a dialog box informing about the action that went wrong
			new ComponentGuestErrorDialog(this, 
					"Følgende feature",
					"Opret Ny Bestilling",
					"Er endnu ikke integreret i systemet"
			);
		});
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnReorderPotato = new ComponentGuestButtonContinue("Bestil Ny Kartoffel", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnReorderPotato);
		
		// Adds an action listener for when the button is clicked
		btnReorderPotato.addActionListener(event ->
		{
			// Creates the new frame that should be opened when pressing the button
			ViewGuestReorderPotato nextView = new ViewGuestReorderPotato();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnPay = new ComponentGuestButtonContinue("Betal", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnPay);
		
		// Adds an action listener for when the button is clicked
		btnPay.addActionListener(event ->
		{
			// Creates the new frame that should be opened when pressing the button
			ViewGuestPaymentMethod nextView = new ViewGuestPaymentMethod();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
}