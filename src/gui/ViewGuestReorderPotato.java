package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 23:53
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
	 * Create the frame.
	 */
	public ViewGuestReorderPotato()
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
			System.out.println("Kartoflen blev tilføjet");
			
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