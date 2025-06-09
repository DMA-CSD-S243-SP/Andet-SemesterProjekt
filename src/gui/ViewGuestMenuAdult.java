package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MainCourse;
import model.MenuCard;
import model.MenuItem;


/**
 * The ViewGuestMenuAdult class represents the GUI shown to the guest when 
 * interacting with the adult menu at the restaurant. This view allows users to 
 * browse through various categories such as main courses, side dishes, drinks, sauces,
 * and self-service options.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * Users can add items to their order, clear their selections, or proceed to order
 * confirmation.
 * 
 * The class also constructs the UI based on the menu data retrieved through the 
 * UtilityGuestInformation class.
 * 
 * 
 * @author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 20:46
 */
public class ViewGuestMenuAdult extends JFrame
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
	boolean isPreviousViewEnabled = true;

	// Determines whether or not the 'Anmod om service' button is enabled in the
	// navigational panel
	boolean isServiceEnabled = true;

	
	/**
	 * Constructs the ViewGuestMenuAdult frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestMenuAdult()
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
	 * - Dynamically populates menu item categories including:
	 *   Main Courses, Self-Service Bars, Side Dishes, Drinks, and Dips/Sauces.
	 * - Configures action buttons for clearing the current order or completing it.
	 * 
	 * Menu data is fetched from the UtilityGuestInformation class, which uses a singleton
	 * pattern.
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - OBS! Since the heading and instruction text utilizes HTML for structuring
		// you must use the <br> tag to break up the text instead of \n
		frameTheme.applyGeneralVisuals("VOKSEN MENUKORT", this, "Tilføj Retter", "Vælg alle de retter og drikkevare som du<br>ønsker at tilføje til din ordrebestilling.");

		// Retrieves the navigationPanel component from the frameTheme
		navigationPanel = frameTheme.getNavigationPanel();

		// Retrieves the primary content panel component from the the frameTheme
		// - This is the panel that we add our varying contents to in the different view / windows
		primaryContentPanel = frameTheme.getPrimaryContentPanel();

		// Creates a ComponentGuestPanel class to serve as a wrapper panel for the generated contents
		ComponentGuestPanel centeredWrapperPanel = new ComponentGuestPanel();
		
		// Specifies that the boxlayout used should be from top to bottom
		centeredWrapperPanel.setLayout(new BoxLayout(centeredWrapperPanel, BoxLayout.Y_AXIS));

		// Sets the layout of the primaryContentPanel to use a centered flowlayout
		primaryContentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		// Adds the wrapper panel to the primaryContentPanel ComponentGuestPanel object
		primaryContentPanel.add(centeredWrapperPanel);
		
		// Retrieves the bottomContentPanel component from the frameTheme
		bottomPanel = frameTheme.getBottomContentPanel();

		
		
		///////////////////////////////
		// - Primary Content Panel - //
		///////////////////////////////
		//   THIS IS WHERE CONTENT   //
		//   SHOULD BE INSERTED IN   //
		///////////////////////////////

		// Retrieves the singleton instance of the UtilityGuestInformation
		// and inserts the list of discounts, which are currently empty
		MenuCard adultMenu = UtilityGuestInformation.getInstance().getAdultMenu();

		
		//////////////////////////////
		// - MainCourse MenuItems - //
		//////////////////////////////
		
		// Creates a custom ComponentGuestLabelHeading to make a heading with the specified title
		centeredWrapperPanel.add(new ComponentGuestLabelHeading("Hovedretter"));
		
		// Adds additional vertical space between the heading and the first menu item
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Creates a list that contains the retrieved MainCourse objects that are available from the adultMenu
		List<MainCourse> listOfMainCourses = UtilityGuestInformation.getInstance().getMainCourses(adultMenu);

		// Uses a for-each loop to iterate through all the MainCourse objects within the retrieved list
		for (MainCourse mainCourse : listOfMainCourses)
		{
			// Creates a custom ComponentGuestMenuItem object that visually displays the specified type of menu item
			ComponentGuestMenuItem menuItemBox = new ComponentGuestMenuItem(mainCourse);

		    // Add the created menu item component to the wrapper panel
		    centeredWrapperPanel.add(menuItemBox);
		    
		    // Adds additional vertical space below the menu item to visually separate them from one another
			centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			// Adds an action listener for when the "+" button is clicked
			menuItemBox.getAddButton().addActionListener(event ->
			{
				// When the + is clicked the selected menu item is associated with the customer's personal order
				UtilityGuestInformation.getInstance().enterMainCourse((MainCourse) menuItemBox.getMenuItem());
				
		        // Launches the view / window where the user can choose how their main course should be
		        openUniversalMainMenu();
			});
		}

		
		
		//////////////////////////////////
		// - SelfServiceBar MenuItems - //
		//////////////////////////////////
		
		// Adds additional vertical space between the last menu item of the former category and the new heading
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		// Creates a custom ComponentGuestLabelHeading to make a heading with the specified title
		centeredWrapperPanel.add(new ComponentGuestLabelHeading("Self-Service Bars"));
		
		// Adds additional vertical space between the heading and the first menu item
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Creates a list that contains the retrieved SelfServiceBar objects that are available from the adultMenu
		List<MenuItem> listOfSelfServiceBars = UtilityGuestInformation.getInstance().getSelfServiceBars(adultMenu);

		// Uses a for-each loop to iterate through all the SelfServiceBar objects within the retrieved list
		for (MenuItem selfService : listOfSelfServiceBars)
		{
			// Creates a custom ComponentGuestMenuItem object that visually displays the specified type of menu item
			ComponentGuestMenuItem menuItemBox = new ComponentGuestMenuItem(selfService);
			
			// Add the created menu item component to the wrapper panel
			centeredWrapperPanel.add(menuItemBox);
			
			// Adds additional vertical space below the menu item to visually separate them from one another
			centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			// Adds an action listener for when the "+" button is clicked
			menuItemBox.getAddButton().addActionListener(event ->
			{
				// When the + is clicked the selected menu item is associated with the customer's personal order in the form of a PersonalOrderLine
				UtilityGuestInformation.getInstance().enterSideOrder(menuItemBox.getMenuItem());
			});
		}

		
		
		////////////////////////////
		// - SideDish MenuItems - //
		////////////////////////////
		
		// Adds additional vertical space between the last menu item of the former category and the new heading
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		// Creates a custom ComponentGuestLabelHeading to make a heading with the specified title
		centeredWrapperPanel.add(new ComponentGuestLabelHeading("Side Retter"));
		
		// Adds additional vertical space between the heading and the first menu item
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Creates a list that contains the retrieved SideDish objects that are available from the adultMenu
		List<MenuItem> listOfSideDishes = UtilityGuestInformation.getInstance().getSideDishes(adultMenu);

		// Uses a for-each loop to iterate through all the SideDish objects within the retrieved list
		for (MenuItem sideDish : listOfSideDishes)
		{
			// Creates a custom ComponentGuestMenuItem object that visually displays the specified type of menu item
			ComponentGuestMenuItem menuItemBox = new ComponentGuestMenuItem(sideDish);
			
			// Add the created menu item component to the wrapper panel
			centeredWrapperPanel.add(menuItemBox);
			
			// Adds additional vertical space below the menu item to visually separate them from one another
			centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			// Adds an action listener for when the "+" button is clicked
			menuItemBox.getAddButton().addActionListener(event ->
			{
				// When the + is clicked the selected menu item is associated with the customer's personal order in the form of a PersonalOrderLine
				UtilityGuestInformation.getInstance().enterSideOrder(menuItemBox.getMenuItem());
			});
		}

		
		
		/////////////////////////
		// - Drink MenuItems - //
		/////////////////////////
		
		// Adds additional vertical space between the last menu item of the former category and the new heading
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		// Creates a custom ComponentGuestLabelHeading to make a heading with the specified title
		centeredWrapperPanel.add(new ComponentGuestLabelHeading("Drikkevarer"));
		
		// Adds additional vertical space between the heading and the first menu item
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Creates a list that contains the retrieved Drink objects that are available from the adultMenu
		List<MenuItem> listOfDrinks = UtilityGuestInformation.getInstance().getDrinks(adultMenu);

		// Uses a for-each loop to iterate through all the Drink objects within the retrieved list
		for (MenuItem drink : listOfDrinks)
		{
			// Creates a custom ComponentGuestMenuItem object that visually displays the specified type of menu item
			ComponentGuestMenuItem menuItemBox = new ComponentGuestMenuItem(drink);

			// Add the created menu item component to the wrapper panel
			centeredWrapperPanel.add(menuItemBox);
			
			// Adds additional vertical space below the menu item to visually separate them from one another
			centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			// Adds an action listener for when the "+" button is clicked
			menuItemBox.getAddButton().addActionListener(event ->
			{
				// When the + is clicked the selected menu item is associated with the customer's personal order in the form of a PersonalOrderLine
				UtilityGuestInformation.getInstance().enterSideOrder(menuItemBox.getMenuItem());
			});
		}
		
		
		
		////////////////////////////////
		// - DipsAndSauce MenuItems - //
		////////////////////////////////
		
		// Adds additional vertical space between the last menu item of the former category and the new heading
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		
		// Creates a custom ComponentGuestLabelHeading to make a heading with the specified title
		centeredWrapperPanel.add(new ComponentGuestLabelHeading("Dips og Sovse"));
		
		// Adds additional vertical space between the heading and the first menu item
		centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Creates a list that contains the retrieved DipsAndSauces objects that are available from the adultMenu
		List<MenuItem> listOfDipsAndSauces = UtilityGuestInformation.getInstance().getDipsAndSauces(adultMenu);

		// Uses a for-each loop to iterate through all the DipsAndSauces objects within the retrieved list
		for (MenuItem dipsAndSauces :listOfDipsAndSauces)
		{
			// Creates a custom ComponentGuestMenuItem object that visually displays the specified type of menu item
			ComponentGuestMenuItem menuItemBox = new ComponentGuestMenuItem(dipsAndSauces);
			
			// Add the created menu item component to the wrapper panel
			centeredWrapperPanel.add(menuItemBox);
			
			// Adds additional vertical space below the menu item to visually separate them from one another
			centeredWrapperPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			// Adds an action listener for when the "+" button is clicked
			menuItemBox.getAddButton().addActionListener(event ->
			{
				// When the + is clicked the selected menu item is associated with the customer's personal order in the form of a PersonalOrderLine
				UtilityGuestInformation.getInstance().enterSideOrder(menuItemBox.getMenuItem());
			});
		}

		
		// Restricts the component's maximum width to the specified pixels, while allowing the height to adapt
		// to its preferred size this then prevents the component from stretching to the full width 
		// and thereby maintains its centered appearance
		centeredWrapperPanel.setPreferredSize(new Dimension(340, centeredWrapperPanel.getPreferredSize().height));
		centeredWrapperPanel.setMaximumSize(new Dimension(340, getPreferredSize().height));
		
		
		
		////////////////////////////////
		// - Navigation Back Button - //
		////////////////////////////////

		// Creates a button with the specified text
		ComponentGuestNavigationButton btnBack = new ComponentGuestNavigationButton("← Tilbage");

		// If the isPreviousViewEnabled variable is set to true then execute this section
		if (isPreviousViewEnabled == true)
		{
			// Moves the button to the west side of the panel it is attached to
			navigationPanel.add(btnBack, BorderLayout.WEST);

			// Adds an action listener for when the button is clicked
			btnBack.addActionListener(event ->
			{
				// Create and configure the new frame (you can replace this with your actual
				// destination window)
				ViewGuestMenuOverview previouslyOpenFrameView = new ViewGuestMenuOverview();

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
		if (isServiceEnabled == true)
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
		ComponentGuestButtonContinue btnEmptyOrder = new ComponentGuestButtonContinue("Tøm Bestilling", bottomPanel);

		// Adds the customized button to the panel
		bottomPanel.add(btnEmptyOrder);

		// Adds an action listener for when the button is clicked
		btnEmptyOrder.addActionListener(event -> 
		{
			UtilityGuestInformation.getInstance().clearOrder();
		});

		
		
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnCompleteOrder = new ComponentGuestButtonContinue("Færdiggør Bestilling", bottomPanel);

		// Adds the customized button to the panel
		bottomPanel.add(btnCompleteOrder);

		// Adds an action listener for when the button is clicked
		btnCompleteOrder.addActionListener(event ->
		{
			// Gets the instance UtilityGuestInformation singleton's instance and calls upon the finishPersonalOrder method
			UtilityGuestInformation.getInstance().finishPersonalOrder();
			
			// Creates the new frame that should be opened when pressing the button
			ViewGuestTableOrder nextView = new ViewGuestTableOrder();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);

			// Closes the current frame/window
			this.dispose();
		});
		
		
		// TODO: This is a very dirty fix for displaying the graphical user interface
		// contents without spending too much time on adjusting them, this
		// would be added to the backlog and revisited in a later iteration.
		// This is still the case, but minor changes has made it not as bad as 
		// in the previous version, but in order to completely fix this, many 
		// aspects of parent GUI components would have to be adjusted along with
		// their used components which time simply does not allow for.
		
		// Changes the size of the to the specified screen size with the calculated height
		setSize(500, getHeight());

		// Changes the view / window's position to the middle of the screen
		setLocationRelativeTo(null);
	}

	
	/**
	 * Launches the ViewGuestUniversalMainMenu frame, which allows the guest to
	 * select the preparation methods and additional options for their chosen 
	 * main course.
	 *
	 * This method is triggered when a guest selects a main course and chooses to
	 * configure it further before then finally adding it to their PersonalOrder.
	 */
	private void openUniversalMainMenu()
	{
		// Creates the new frame that should be opened when pressing the button
		JFrame nextView = new ViewGuestUniversalMainMenu();

		// Sets the visibility to true turning the previous view / window visible
		nextView.setVisible(true);

		// Closes the current frame/window
		this.dispose();
	}
}