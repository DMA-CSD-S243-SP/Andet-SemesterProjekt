package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.TableOrderController;
import database.DataAccessException;
import database.PersonalOrderDB;
import database.PersonalOrderImpl;
import database.TableOrderDB;
import database.TableOrderImpl;
import model.PersonalOrder;
import model.TableOrder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 02:14
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
	
	private TableOrderImpl daoTO;
	private PersonalOrderImpl daoPO;
	private TableOrderController tableOrderController;
	
	private TableOrder currentTableOrder;
	
	/**
	 * Create the frame.
	 * @param currentTableOrder 
	 */
	public ViewGuestTableOrderConfirmation(TableOrder currentTableOrder)
	{
		try {
			daoTO = new TableOrderDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		daoPO = new PersonalOrderDB();
		tableOrderController = new TableOrderController();
		this.currentTableOrder = currentTableOrder;
		initGUI();
	}
	
	
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
		
		/*
		// Creates a customized input field object with a placeholder text accepting only numbers in it
		ComponentGuestInputField inputFieldFirstName = new ComponentGuestInputField("Fornavn", "onlyNumbers");
		
		// Adds the first name input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldFirstName);
		
		// Adds some spacing between the component above and below
		primaryContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Creates a customized input field object with a placeholder text accepting only letters in it
		ComponentGuestInputField inputFieldAge = new ComponentGuestInputField("Alder i antal år", "onlyLetters");

		// Adds the age input field to the primary content panel
		frameTheme.getPrimaryContentPanel().add(inputFieldAge);
		*/
		
		
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
			tableOrderController.sendToKitchen(currentTableOrder);
			try {
				daoTO.updateTableOrder(currentTableOrder);
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (PersonalOrder pList : currentTableOrder.getPersonalOrders())
			{
				try {
					daoPO.insertPersonalOrder(pList, currentTableOrder.getTableOrderId());
				} catch (DataAccessException e) {
					e.printStackTrace();
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