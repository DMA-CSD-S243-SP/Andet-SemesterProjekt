package gui;

//Imports
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The ViewGuestTableInformation class provides the GUI for guests 
 * to input their table code. The code is composed of a 3 digit restaurant code
 * and a 4 digit table number, and is used to identify the restaurant and table
 * the guest is sitting at and the related TableOrder.
 *
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 *
 * Upon valid input, the user is navigated to the next step in the 
 * dining process where personal customer information is requested.
 *
 *
 * Author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 22:22
 */
public class ViewGuestTableInformation extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes
	// where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;

	// The Jframes that will be retrieved from the guest frame theme and used for
	// manipulating the contents
	private JPanel navigationPanel;
	private JPanel primaryContentPanel;
	private JPanel bottomPanel;

	// Determines whether or not this frame should have the "tilbage" button enabled
	// in the navigational panel
	boolean isPreviousViewEnabled = false;

	// Determines whether or not the 'Anmod om service' button is enabled in the
	// navigational panel
	boolean isServiceEnabled = false;

	ComponentGuestInputField inputField;
    

	/**
	 * Constructs the ViewGuestTableInformation frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestTableInformation()
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
	 * - An input field restricted to numeric values only to receive the 7 digit table code
	 * 
     */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("BORDINFORMATION", this, "Indlæs Bordinformation", "Udfyld informationsboksen nedenfor med<br> indholdet som står på skiltet tilhørende dit bord");
		
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
		
		// Creates an inputField that only accepts numeric values as inputs
		inputField = new ComponentGuestInputField("Bordnummer", "onlyNumbers");
		
		// Adds the inputField to the frameTheme
		frameTheme.getPrimaryContentPanel().add(inputField);
		
		
		
		////////////////////////////////
		// - Navigation Back Button - //
		////////////////////////////////

		
		
		///////////////////////////////////
		// - Navigation Service Button - //
		///////////////////////////////////
		

		
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
				
				// Retrieves the supplied information from the table code text field
				String tableCode = inputField.getText();
				
				// If the tableCode's length is anything else than 7 digits long then execute this section
				if (tableCode.length() != 7)
				{
					// Creates a dialog box, with the "Ok" option, containing a specific and detailed error message			
					throw new IllegalArgumentException("Invalid table code");
				}
				
				// Retrieves the singleton instance of the UtilityGuestInformation
				UtilityGuestInformation info = UtilityGuestInformation.getInstance();
				
				// Splits the table string up in to the 4 last digits (table number)
				// and in to the 3 first digits (restaurant code) and stores the extracted
				// data where it then gets used to supply the personalorder controller with 
				// information within the UtilityGuestInformation class.
				info.enterTableCode(tableCode.substring(3,7), tableCode.substring(0,3));
				
				// Creates the new frame that should be opened when pressing the button
				ViewGuestCustomerInformation nextView = new ViewGuestCustomerInformation();

				// Sets the visibility to true turning the previous view / window visible
				nextView.setVisible(true);
				
				// Closes the current frame/window
				this.dispose();
			}
			
			catch (IllegalArgumentException illegalArgumentException)
			{
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(this, 
						"Følgende er ikke udfyldt korrekt:",
						"Bordnummer",
						"Indtast et gyldigt bordnummer (7 cifre)"
				);
				
				illegalArgumentException.printStackTrace();
			}
			
			catch (Exception exception)
			{
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(this, 
						"Der skete en uventet fejl:",
						"",
						"Prøv igen."
				);
				
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