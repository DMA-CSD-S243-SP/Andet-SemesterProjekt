package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard & Lumière Schack
 * @version 21/05/2025 - 13:28
 */	
public class ViewGuestCustomerInformation extends JFrame
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
	
	private ComponentGuestInputField inputFieldFirstName;
	private ComponentGuestInputField inputFieldAge;
	
	
	/**
	 * Create the frame.
	 */
	public ViewGuestCustomerInformation()
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
		frameTheme.applyGeneralVisuals("KUNDEINFORMATION", this, "Udfyld Kundeinformation", "Udfyld informationsboksene nedenfor med de<br> påkrævede personlige oplysninger<br>  Fremvisning af legimation kan påkræves.");
		
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
		
		
		// Creates a customized input field object with a placeholder text accepting only numbers in it
        inputFieldFirstName = new ComponentGuestInputField("Fornavn", "onlyLetters");
		
		// Adds the first name input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldFirstName);
		
		// Adds some spacing between the component above and below
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Creates a customized input field object with a placeholder text accepting only letters in it
		inputFieldAge = new ComponentGuestInputField("Alder i antal år", "onlyNumbers");

		// Adds the age input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldAge);
		
		
		
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
				ViewGuestTableInformation previouslyOpenFrameView = new ViewGuestTableInformation();
				
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
				
				// Retrieves the name inputted name from the input field
				String firstName = inputFieldFirstName.getText().strip();
				
				// If the inputted name is empty then execute this section
				if (firstName.isEmpty())
				{
					// Creates a dialog box, with the "Ok" option, containing a specific and detailed error message
					new ComponentGuestErrorDialog(this, 
							"Følgende er ikke udfyldt korrekt:",
							"Fornavn",
							"Feltet skal have indhold."
					);
					
					throw new IllegalArgumentException("A name must be filled in");
				}
				
				// If the inputted name is longer than 30 letters then execute this section
				else if (firstName.length() > 30)
				{
					// Creates a dialog box, with the "Ok" option, containing a specific and detailed error message
					new ComponentGuestErrorDialog(this, 
							"Følgende er ikke udfyldt korrekt:",
							"Fornavn",
							"Må maks indeholde 30 tegn."
					);
				}
				
				// Retrieves the inputted integer from the input field
				int age = Integer.valueOf(inputFieldAge.getText());
				
				// If the age input is above 200 or below 0 then execute this section
				if (age > 200 || age < 0)
				{
					// Creates a dialog box, with the "Ok" option, containing a specific and detailed error message
					throw new IllegalArgumentException("Age can't exceed 200, or fall below 0");
				}
				
				// Retrieves the singleton instance of the UtilityGuestInformation
				UtilityGuestInformation info = UtilityGuestInformation.getInstance();
				
				// Stores the data from the input fields and supplies it to the to the
				// personal order controller, to set the name and age of the guest, this
				// is done within the UtilityGuestInformation class.
				info.enterNameAndAge(firstName, age);
				
				// Creates the new frame that should be opened when pressing the button
				ViewGuestDiscountSelection nextView = new ViewGuestDiscountSelection();

				// Sets the visibility to true turning the previous view / window visible
				nextView.setVisible(true);
				
				// Closes the current frame/window
				this.dispose();
			}
			catch (IllegalArgumentException ie)
			{
				new ComponentGuestErrorDialog(this, 
						"Følgende er ikke udfyldt korrekt:",
						"Alder",
						"Alderen skal være over 0 og under 200."
				);
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