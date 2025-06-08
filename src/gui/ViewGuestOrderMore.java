package gui;

//Imports
import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * The ViewGuestOrderMore class represents a graphical interface that allows guests
 * to select an existing customer profile or choose to create a new one in order to
 * add more items to an ongoing order, or order for someone else at the table.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * The view includes a dropdown menu with selectable customer names.
 *
 *
 * @author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 21:03
 */
public class ViewGuestOrderMore extends JFrame
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
	 * Constructs the ViewGuestOrderMore frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestOrderMore()
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
	 * - Creates a dropdown list of selectable existing customers.
	 * 
	 * NOTE: Some logic is marked as TODO for future implementation, where
	 * dynamic user-based routing will be handled.
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("BESTIL MERE", this, "Vælg Bestilling", "Vælg personens bestilling, som du gerne<br> vil tilføje nye produkter til.");
		
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
		
		
		// Creates a combobox with a title heading above it and a dropdown containing the specified options
		ComponentGuestComboBox comboBoxChoosePerson = new ComponentGuestComboBox("Vælg Tilbehør", Arrays.asList(
		        "Intet valgt",
				"Anders Olesen",
		        "Ben Dover",
		        "Will Smith",
		        "Jane Doe"
		    ));
		
		// Adds the comboboxPotato object to the primary content panel
		primaryContentPanel.add(comboBoxChoosePerson);
		
		
		
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
				ViewGuestTableOrder previouslyOpenFrameView = new ViewGuestTableOrder();
				
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
		ComponentGuestButtonContinue btnContinue = new ComponentGuestButtonContinue("Fortsæt", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnContinue);
	
		// Adds an action listener for when the button is clicked
		btnContinue.addActionListener(event ->
		{
			// TODO - 	If a new person is selected they are navigated to the ViewGuestCustoemrInformation 
			// 			if an existing person is chosen, the ViewGuestMenuOverview should be displayed and 
			// 			the person's personal order should be what is being added to.
			
			
			// Creates the new frame that should be opened when pressing the button
			ViewGuestTableOrder nextView = new ViewGuestTableOrder();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
//			this.dispose();
		});
	}
}