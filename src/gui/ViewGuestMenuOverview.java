package gui;

//Imports
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The ViewGuestMenuOverview class represents the GUI frame where a guest selects
 * which menu category to order from e.g. children's menu or the adult menu.
 *
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 *
 * The class handles basic view transitions but currently does not contain actual logic
 * for loading a children's menu, which will first be added in future implementations, 
 * but the class supports dynamically generating of buttons showing the available menu paths.
 *
 *
 * @author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 20:38
 */
public class ViewGuestMenuOverview extends JFrame
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
	 * Constructs the ViewGuestMenuOverview frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestMenuOverview()
	{
		initGUI();
	}
	
	
	/**
	 * Initializes the GUI components for this frame.
	 *
	 * Responsibilities of this method include:
	 * - Setting up the themed frame layout
	 * - Displaying navigation buttons (back and request service)
	 * - Creating a continue button that validates input and proceeds to the next view
	 * - Generating action buttons for selecting a children's or adult menu
	 *
	 * The children’s menu path currently throws an error dialog, as it is not yet implemented.
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("MENUOVERSIGT", this, "Vælg Menusektion", "Vælg det menukort som du ønsker<br>at bestille dine retter fra.");
		
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
				ViewGuestDiscountSelection previouslyOpenFrameView = new ViewGuestDiscountSelection();
				
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
		ComponentGuestButtonContinue btnChildMenu = new ComponentGuestButtonContinue("Børnemenu", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnChildMenu);
		
		// Adds an action listener for when the button is clicked
		btnChildMenu.addActionListener(event ->
		{
			// TODO: Add functionality
			try
			{
				throw new UnsupportedOperationException("Junior menu not implemented.");
			} 
			
			catch (Exception exception)
			{
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(this, 
						"Følgende menu kort:",
						"Børnemenu",
						"Kunne ikke hentes"
				);
				
				exception.printStackTrace();
			}
		});
		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnAdultMenu = new ComponentGuestButtonContinue("Voksenmenu", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnAdultMenu);
	
		// Adds an action listener for when the button is clicked
		btnAdultMenu.addActionListener(event ->
		{
			try
			{
				// Temporarily disables the button to make it visible to the user that the interaction was registered
				btnAdultMenu.setEnabled(false);
				
				// Creates the new frame that should be opened when pressing the button
				ViewGuestMenuAdult nextView = new ViewGuestMenuAdult();

				// Sets the visibility to true turning the previous view / window visible
				nextView.setVisible(true);
				
				// Closes the current frame/window
				this.dispose();
			}
			
			catch (Exception exception)
			{
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(this, 
						"Følgende menu kort:",
						"Voksenmenu",
						"Kunne ikke hentes"
				);
				
				exception.printStackTrace();
			}
			
			finally
			{
				// Reenables the button if something goes wrong
				btnAdultMenu.setEnabled(true);
			}
		});
	}
}