package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Discount;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard & Lumière Schack
 * @version 21/05/2025 - 13:31
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
	 * Create the frame.
	 */
	public ViewGuestDiscountSelection()
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
				btnContinue.setEnabled(true);
			}
			
		});
	}
}