package gui;

//Imports
import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The ViewGuestReorderPotato class provides a GUI for reordering a 
 * potato side dish.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * This frame presents a dropdown menu for the guest to select a potato dish, 
 * such as baked potatoes.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 08/06/2025 - 22:06
 */
public class ViewGuestReorderPotato extends JFrame
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
	 * Constructs the ViewGuestReorderPotato frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestReorderPotato()
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
     * - A dropdown menu to select potatoes from
     * 
     * It also adds a confirmation button that simulates adding the selected item
     * but currently this does not work, as it is a future use case that will be implemented
     * in a future iteration.
     */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("KARTOFFEL GENBESTILLING", this, "Vælg Kartoffel", "Vælg den type af kartoffelret som<br>du gerne vil have serveret.");
		
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
		ComponentGuestComboBox comboboxPotato = new ComponentGuestComboBox("Vælg Tilbehør", Arrays.asList(
		        "Intet valgt",
				"Bagt kartoffel m. hvidløgssmør",
		        "Bagt kartoffel m. creme fraiche",
		        "Bagt kartoffel uden smør",
		        "Pommes Frites"
		    ));
		
		// Adds the comboboxPotato object to the primary content panel
		primaryContentPanel.add(comboboxPotato);

		
		
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
				// Creates a new frame that should be opened when pressing the button and closes the current one, taking the user a step back in the ordering process
				returnToPreviousView();
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
		ComponentGuestButtonContinue btnContinue = new ComponentGuestButtonContinue("Tilføj", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnContinue);
	
		// Adds an action listener for when the button is clicked
		btnContinue.addActionListener(event ->
		{	
			// Creates a new frame that should be opened when pressing the button and closes the current one, taking the user a step back in the ordering process
			returnToPreviousView();
		});
	}
	
	
	// Brings the user one step back in the ordering process, by closing this view and opening the takes the user one step back in the process 
	private void returnToPreviousView()
	{
		// Create and configure the new frame (you can replace this with your actual destination window)
		ViewGuestOrderOverview previouslyOpenFrameView = new ViewGuestOrderOverview();
		
		// Sets the visibility to true turning the previous view / window visible
		previouslyOpenFrameView.setVisible(true);

		// Find and closes the current view / window
		this.dispose();
	}
}