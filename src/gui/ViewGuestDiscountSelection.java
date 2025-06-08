package gui;

//Imports
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Discount;


/**
 * The ViewGuestDiscountSelection class represents a GUI frame where guests 
 * can select discounts that are applicable to them, e.g. student or pensionist.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * This view is part of the customer ordering flow and allows the user to check one or more
 * predefined discount options. These will later be validated and used to apply pricing logic
 * to the personal order, once the functionality is developed in a future use case.
 * 
 *
 * @author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 20:26
 */
public class ViewGuestDiscountSelection extends JFrame
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
	 * Constructs the ViewGuestDiscountSelection frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestDiscountSelection()
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
	 * - Creating checkboxes for each available discount category
	 *
	 * Currently, the actual selection of discount checkboxes is not captured or passed forward,
	 * but the structure allows for future implementation of applying discounts.
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("RABATTER", this, "Vælg Dine Rabatter", "Afkryds alle bokse nedenfor, som gælder for<br>dig. Den højeste rabat vil blive brugt.<br>Fremvisning af legimation kan påkræves.");
		
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
		
		// Add the options for the various checkboxes
		String[] checkboxOptionList = 
		{
		    "Jeg er studerende",
		    "Jeg er pensionist",
		    "Jeg er i hjemmeværnet",
		    "Jeg er blevet konfirmand i år",
		    "Jeg har fødselsdag i dag"
		};
		
		// Iterates through the list of checkbox options using a for each loop
		for (String checkboxText : checkboxOptionList)
		{
			// Creates a new custom checkbox object
		    ComponentGuestCheckBox checkBox = new ComponentGuestCheckBox(checkboxText);
		    
		    // Applies an additional styling to the checkbox in the form of a wrapper, in order 
		    // to be possible to add in to the UI without disrupting the flow
		    primaryContentPanel.add(checkBox.applyWrapperStyling());
		}
		
		
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
				ViewGuestCustomerInformation previouslyOpenFrameView = new ViewGuestCustomerInformation();
				
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
			try
			{
				// Temporarily disables the button to show something is happening and
				// also to prevent user spamming the database
				btnContinue.setEnabled(false);
				
				// TODO: Make EnterDiscounts actually enter the selected discounts
				// 		 Note this is in a different use case in a future iteration
				
				// Retrieves the singleton instance of the UtilityGuestInformation
				// and inserts the list of discounts, which are currently empty
				UtilityGuestInformation.getInstance().enterDiscounts(new ArrayList<Discount>());
				
				// Creates the new frame that should be opened when pressing the button
				ViewGuestMenuOverview nextView = new ViewGuestMenuOverview();

				// Sets the visibility to true turning the previous view / window visible
				nextView.setVisible(true);
				
				// Closes the current frame/window
				this.dispose();
			}
			
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			
			finally
			{
				// Reenables the button if something goes wrong
				btnContinue.setEnabled(true);
			}
		});
	}
}