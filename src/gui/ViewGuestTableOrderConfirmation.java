package gui;

//Imports
import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.PersonalOrderController;
import application.TableOrderController;
import database.DataAccessException;
import model.PersonalOrder;
import model.TableOrder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard & Anders trankjær
 * @version 28/05/2025 - 13:20
 */	
public class ViewGuestTableOrderConfirmation extends JFrame
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
	
	private TableOrderController tableOrderController;
	private PersonalOrderController personalOrderController;
	
	private TableOrder currentTableOrder;
	
	
	/**
	 * Constructs the ViewGuestTableOrderConfirmation frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestTableOrderConfirmation()
	{
		// Instantiates a new controller for handling table order related tasks
		tableOrderController = new TableOrderController();

		// Retrieves the current table order from the UtilityGuestInformation singleton and stores it within the currentTableOrder instance variable
		this.currentTableOrder = UtilityGuestInformation.getInstance().getTableOrder();

		initGUI();
	}
	
	
	/**
	 * Initializes the GUI components for this frame.
	 *
	 * This includes:
	 * - Setting up the themed frame layout
	 * - Displaying navigation buttons (back and request service)
	 * - Creating a continue button that validates input and proceeds to the next view
	 * - Finalizing and associating the PersonalOrder the table order
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("HANDLINGSBEKRÆFTELSE", this, "Er Du Sikker?", "Skal bestillingen afsendes?<br> OBS: Når den først forberedes<br> kan den ikke trækkes tilbage.");
		
		
		
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
		ComponentGuestButtonContinue btnConfirm = new ComponentGuestButtonContinue("Ja, Send Til Køkken", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnConfirm);
	
		// Adds an action listener for when the button is clicked
		btnConfirm.addActionListener(event ->
		{
			// Prepares the tableOrder for being send to the kitchen 
			tableOrderController.sendToKitchen(currentTableOrder);

			try
			{
				//updates a tableOrder in the database.
				tableOrderController.updateTableOrder(currentTableOrder);
			} 
			
			//DataAccessException is thrown in TableOrderController
			catch (DataAccessException exception)
			{
				exception.printStackTrace();
			} 
			
			//SQLException is thrown in TableOrderController
			catch (SQLException exception) 
			{
				exception.printStackTrace();
			}
			
			// Uses a for-each loop to iterate through every PersonalOrder object associated with the TableOrder object
			for (PersonalOrder personalOrder : currentTableOrder.getPersonalOrders())
			{
			    try 
			    {
					// Sets the current PersonalOrder object in the PersonalOrderController
					personalOrderController.setPersonalOrder(personalOrder);

					// Sets the associated TableOrder object in the personalOrderController
					personalOrderController.setTableOrder(currentTableOrder);

					// Finalizes the PersonalOrder order by inserting it into the database.
					personalOrderController.finishPersonalOrder();
			    }
			    
			    catch (DataAccessException exception) 
			    {
			    	exception.printStackTrace();
			    }
			}
			
			
			// Creates the new frame that should be opened when pressing the button
			ViewGuestOrderOverview nextView = new ViewGuestOrderOverview();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
		

		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnDecline = new ComponentGuestButtonContinue("Nej, Ikke Endnu", bottomPanel);
		
		// Adds the customized button to the panel
		bottomPanel.add(btnDecline);
	
		// Adds an action listener for when the button is clicked
		btnDecline.addActionListener(event ->
		{
			// Creates a new frame that should be opened when pressing the button and closes the current one, taking the user a step back in the ordering process
			returnToPreviousView();
		});
	}
	
	
	// Brings the user one step back in the ordering process, by closing this view and opening the takes the user one step back in the process 
	private void returnToPreviousView()
	{
		// Create and configure the new frame (you can replace this with your actual destination window)
		ViewGuestTableOrder previouslyOpenFrameView = new ViewGuestTableOrder();
		
		// Sets the visibility to true turning the previous view / window visible
		previouslyOpenFrameView.setVisible(true);

		// Find and closes the current view / window
		this.dispose();
	}
}