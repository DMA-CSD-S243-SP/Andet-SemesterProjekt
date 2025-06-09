package gui;

//Imports
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AddOnOption;
import model.MainCourse;
import model.MenuCard;
import model.MultipleChoiceMenu;
import model.PotatoDish;
import model.SelectionOption;


/**
 * The ViewGuestCustomerInformation class represents a GUI frame
 * where guests are prompted to customize their main course by selecting a 
 * potato dish, multiple choice options and add-ons.
 * 
 * This class extends JFrame and makes use of a variety of custom GUI components.
 * 
 * The class uses a custom frame theme for layout and styling,
 * and includes navigation buttons for going back, requesting service,
 * or continuing to the next step in the guest flow.
 * 
 * This class dynamically loads the options available for the current menu and
 * collects the user's selections for later submission.
 *
 *
 * @author: Christoffer Søndergaard & Lumière Schack  
 * @version: 08/06/2025 - 20:05
 */
public class ViewGuestUniversalMainMenu extends JFrame
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
	
	ComponentGuestComboBox potatoCombo;
	List<AddOnOption> listOfAddOnOptions;
	List<ComponentGuestCheckBox> addOnCheckBoxes;
	List<PotatoDish> potatoDishes;
	MainCourse mainCourse;
	List<MultipleChoiceMenu> multipleChoiceMenus;
	List<List<SelectionOption>> twodSelectionOptions;
	List<ComponentGuestComboBox> listOfMultipleChoiceBoxes;
	

	/**
	 * Constructs the ViewGuestUniversalMainMenu frame and initializes
	 * its graphical components and layout.
	 *
	 * This constructor assigns the task of GUI setup to the initGUI() method.
	 */
	public ViewGuestUniversalMainMenu()
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
	 * - Retrieving the menu data from UtilityGuestInformation
	 * - Populating combo boxes and checkboxes for potato dishes, multiple choice options, and add-ons
	 *
	 * This method is automatically called from the constructor.
	 */
	private void initGUI()
	{
		// Creates a ComponentFrameThemeGuest component
		ComponentFrameThemeGuest frameTheme = new ComponentFrameThemeGuest();

		// Changes the frame / view's theme to the universal bone's brand theme
		// - 	OBS! Since the heading and instruction text utilizes HTML for structuring
		// 		you must use the <br> tag to break up the text instead of \n 
		frameTheme.applyGeneralVisuals("TILBEREDNING", this, "Vælg Tilberedning", "Vælg hvordan du gerne vil have<br>din hovedret tilberedt.");
		
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
		
		// Fetches the active adult menu from the UtilityGuestInformation class
		MenuCard menu = UtilityGuestInformation.getInstance().getAdultMenu();

		// Gets the list of available potato dishes from the selected menu through the UtilityGuestInformation class
		potatoDishes = UtilityGuestInformation.getInstance().getPotatoDishes(menu);

		// Retrieves the selected main course from the UtilityGuestInformation class
		mainCourse = UtilityGuestInformation.getInstance().getMainCourse();
		
		
		
		//////////////////////////////
		// - Potato Dish Specific - //
		//////////////////////////////
		
		
		// Instantiates an empty list to contain various potato dish names to show in a combo box
		List<String> listOfPotatoStrings = new ArrayList<>();
		
		// Uses a for-each loop to iterate through the various PotatoDishes
		for (PotatoDish potatoDish: potatoDishes)
		{
			// Adds each potato name to the list of display strings
			listOfPotatoStrings.add(potatoDish.getName());
		}

		// Creates a combo box for potato selection with the prepared names
		potatoCombo = new ComponentGuestComboBox("Vælg kartoffel tilbehør", listOfPotatoStrings);

		// Makes the potato combo box visible
		potatoCombo.setVisible(true);
		
		// Adds the potato combo box to the main content panel
		primaryContentPanel.add(potatoCombo);
		
		
		
		///////////////////////////////////
		// - Multiple Choice  Specific - //
		///////////////////////////////////
		
		
		// Initializes an empty array list to store combo boxes for multiple choice options
		listOfMultipleChoiceBoxes = new ArrayList<>();
		
		// Gets all multiple choice menus that are associated with the selected MainCourse object
		multipleChoiceMenus = mainCourse.getListOfMultipleChoiceMenu();

		// Stores the number of multiple choice menus to iterate over
		int numberOfMultipleChoiceMenus = multipleChoiceMenus.size();

		
		// Uses a classic for loop to perform the collection iteration
		// as it is controlled manually here. 
		// This is because the order in which they are added are important,
		// as the index is used to fetch the information later on.
		for (int index = 0; index < numberOfMultipleChoiceMenus; index++)
		{
			// Retrieves the current MultipleChoiceMenu object
			MultipleChoiceMenu currentMultipleChoiceMenu = multipleChoiceMenus.get(index);

			// Prepares a list of strings that will include SelectionOption's additionalPrice and description
			List<String> listOfOptionStrings = new ArrayList<>();

			// Gets the list of SelectionOption objects of the MultipleChoiceMenu object and
			// stores them within the listOfSelectionOptions variable
			List<SelectionOption> listOfSelectionOptions = currentMultipleChoiceMenu.getListOfSelectionOptions();

			// Uses a for-each loop to iterate through the list of SelectionOption objects
			for (SelectionOption selectionOption: listOfSelectionOptions)
			{
				// Adds the selectionOption's additionalPrice and description to the optionStrings 
				listOfOptionStrings.add("(+" +selectionOption.getAdditionalPrice() + ") " + selectionOption.getDescription());
			}
			
			// Creates a ComponentGuestComboBox using the SelectionOption and its listOfOptionStrings
			ComponentGuestComboBox comboBoxMultipleChoiceMenu = new ComponentGuestComboBox(currentMultipleChoiceMenu.getSelectionDescription(), listOfOptionStrings);

			// Makes the comboBoxMultipleChoiceMenu object visible
			comboBoxMultipleChoiceMenu.setVisible(true);

			// Adds the comboBoxMultipleChoiceMenu object to the primary content panel
			primaryContentPanel.add(comboBoxMultipleChoiceMenu);

			// Adds the combo box to the internal listOfMultipleChoiceBoxes to access it later
			listOfMultipleChoiceBoxes.add(comboBoxMultipleChoiceMenu);
		}
		
		
		
		////////////////////////////////
		// - Add-On Option Specific - //
		////////////////////////////////
		
		
		// Gets all add-on options that are associated with the selected MainCourse object
		listOfAddOnOptions = mainCourse.getListOfAddOnOption();

		// Initializes an empty array list to store checkboxes for each add-on option
		addOnCheckBoxes = new ArrayList<>();

		// Uses a for-each loop to iterate through the listOflistOfAddOnOptions
		for (AddOnOption addOnOption: listOfAddOnOptions)
		{
			// Formats the checkbox's label text with the additionalPrice and description
			String optionText = "(+" + addOnOption.getAdditionalPrice() + ") " + addOnOption.getDescription();

			// Creates a new checkbox for the add-on option
			ComponentGuestCheckBox addOnOptionCheckBox = new ComponentGuestCheckBox(optionText);

			// Makes the addOnOptionCheckBox object visible
			addOnOptionCheckBox.setVisible(true);

			// Adds the comboBoxMultipleChoiceMenu object to the primary content panel
			primaryContentPanel.add(addOnOptionCheckBox);
			
			// Adds the comboBoxMultipleChoiceMenu object to the internal list to access it later
			addOnCheckBoxes.add(addOnOptionCheckBox);
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
				// - We bring the user two steps back in this case, as we know that they could be in either menu
				// TODO: this would be something to solve in a future iteration as we dive more in to menu cards
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
			// Collects the user's selected options and stores them
			enterBoxInfo();
			
			// Creates the new frame that should be opened when pressing the button
			ViewGuestMenuAdult nextView = new ViewGuestMenuAdult();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
	
	
	/**
	 * Collects the user's selected options for the main course, including:
	 * - A selected potato dish
	 * - Any selected add-ons (check boxes)
	 * - Any selected options from the multiple choice combo boxes
	 * 
	 * After collecting the selections, the method passes them to UtilityGuestInformation,
	 * which then delegates the data to the relevant controllers.
	 */
	private void enterBoxInfo()
	{
		// NOTE:
		// The options for the comboboxes are added in an ascending index order.
		// Hence the index of a given option is being used here.
		
		// Retrieves the potato dish selected in the combo box
		PotatoDish potatoDish = potatoDishes.get(potatoCombo.getIndex());
		
		// Initializes a list to store the guest's selection option choices
		List<SelectionOption> listOfSelectionOptionChoices = new ArrayList<>();
		
		// Uses a classic for loop to iterate through the multipleChoiceMenus objects
		for (int index = 0; index < multipleChoiceMenus.size(); index++)
		{
			// DO NOT TRY TO LEARN FROM THIS. PLEASE LEAVE. DO NOT LOOK BACK.
			
			// Gets the selection options for the current menu
			List<SelectionOption> selectionOptions = multipleChoiceMenus.get(index).getListOfSelectionOptions();

			// Retrieves the combo box for the current index
			ComponentGuestComboBox guestComboBox = listOfMultipleChoiceBoxes.get(index);
			
			// Adds the selected option, based on its index, to the list
			listOfSelectionOptionChoices.add(selectionOptions.get(guestComboBox.getIndex()));
		}
		
		// Initializes a list to store the selected add-on options
		List<AddOnOption> listOfAddOnOptionChoices = new ArrayList<>();
		
		// Uses a classic for loop to iterate through the addOnCheckBoxes objects
		for (int index = 0; index < addOnCheckBoxes.size(); index++)
		{
			// If the particular addOn checkbox is marked then execute this section
			if (addOnCheckBoxes.get(index).isSelected())
			{
				// Adds the selected AddOnOption, based on the current index, to the list of chosen add-ons
				listOfAddOnOptionChoices.add(listOfAddOnOptions.get(index));
			}
		}
		
		// Sends the collected user choices to the UtilityGuestInformation class for further handling of them
		UtilityGuestInformation.getInstance().enterMainCourseOptions(potatoDish, listOfAddOnOptionChoices, listOfSelectionOptionChoices);
	}
}