package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AddOnOption;
import model.MainCourse;
import model.MenuCard;
import model.MultipleChoiceMenu;
import model.PotatoDish;
import model.SelectionOption;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard & Lumière Schack
 * @version 21/05/2025 - 12:47
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
	List<AddOnOption> addOnOptions;
	List<ComponentGuestCheckBox> addOnCheckBoxes;
	List<PotatoDish> potatoDishes;
	MainCourse mainCourse;
	List<MultipleChoiceMenu> multipleChoiceMenus;
	List<List<SelectionOption>> twodSelectionOptions;
	List<ComponentGuestComboBox> multipleChoiceBoxes;
	
	/**
	 * Create the frame.
	 */
	public ViewGuestUniversalMainMenu()
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
		
		MenuCard menu = UtilityGuestInformation.getInstance().getAdultMenu();
		potatoDishes = UtilityGuestInformation.getInstance().getPotatoDishes(menu);
		mainCourse = UtilityGuestInformation.getInstance().getMainCourse();
		
		// Potato selection
		List<String> potatoStrings = new ArrayList<>();
		for (PotatoDish potato: potatoDishes)
		{
			potatoStrings.add(potato.getName());
		}
		potatoCombo = new ComponentGuestComboBox("Vælg kartoffel tilbehør", potatoStrings);
		potatoCombo.setVisible(true);
		primaryContentPanel.add(potatoCombo);
		
		// All the multiple choice selections
		// TODO Fix retrieval of maincourse options
		//multipleChoiceMenus = mainCourse.getListOfMultipleChoiceMenu();
		
		// Code for showing functionality, despite broken fetch.
		multipleChoiceMenus = new ArrayList<>();
		MultipleChoiceMenu choice1 = new MultipleChoiceMenu("Size");
		choice1.addSelectionOption(new SelectionOption("Small", "size-small", 0));
		choice1.addSelectionOption(new SelectionOption("Medium", "size-medium", 39));
		choice1.addSelectionOption(new SelectionOption("Large", "size-large", 69));
		multipleChoiceMenus.add(choice1);
		MultipleChoiceMenu choice2 = new MultipleChoiceMenu("Doneness");
		choice2.addSelectionOption(new SelectionOption("Rare", "rare-done", 0));
		choice2.addSelectionOption(new SelectionOption("Well Done", "well-done", 0));
		choice2.addSelectionOption(new SelectionOption("Medium", "medium-done", 0));
		multipleChoiceMenus.add(choice2);
		
		
		multipleChoiceBoxes = new ArrayList<>();
		int size = multipleChoiceMenus.size();
		// Collection iteration is controlled manually here.
		// This is because the order in which they are added is important,
		// as the index is used to fetch later.
		for (int i = 0; i < size; i++)
		{
			MultipleChoiceMenu currentMenu = multipleChoiceMenus.get(i);
			List<SelectionOption> options = currentMenu.getListOfSelectionOptions();
			List<String> optionStrings = new ArrayList<>();
			for (SelectionOption option: options)
			{
				optionStrings.add("(+"+option.getAdditionalPrice()+") "+option.getDescription());
			}
			ComponentGuestComboBox combobox = new ComponentGuestComboBox(currentMenu.getSelectionDescription(), optionStrings);
			combobox.setVisible(true);
			primaryContentPanel.add(combobox);
			multipleChoiceBoxes.add(combobox);
		}
		
		// AddOns
		// TODO Fix fetching of options
		//addOnOptions = mainCourse.getListOfAddOnOption();
		
		addOnOptions = new ArrayList<AddOnOption>();
		addOnOptions.add(new AddOnOption("Garlic butter", "+garlic", 10));
		addOnOptions.add(new AddOnOption("No herbs", "-herb", 0));
		
		addOnCheckBoxes = new ArrayList<>();
		for (AddOnOption option: addOnOptions)
		{
			String optionText = "(+" + option.getAdditionalPrice()+") "+option.getDescription();
			ComponentGuestCheckBox addOnBox = new ComponentGuestCheckBox(optionText);
			addOnBox.setVisible(true);
			addOnCheckBoxes.add(addOnBox);
			primaryContentPanel.add(addOnBox);
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
			enterBoxInfo();
			
			// Creates the new frame that should be opened when pressing the button
			ViewGuestMenuAdult nextView = new ViewGuestMenuAdult();

			// Sets the visibility to true turning the previous view / window visible
			nextView.setVisible(true);
			
			// Closes the current frame/window
			this.dispose();
		});
	}
	
	private void enterBoxInfo()
	{
		// The options for the comboboxes are added in ascending index order.
		// Hence the index of a given option
		PotatoDish potatoDish = potatoDishes.get(potatoCombo.getIndex());
		
		List<SelectionOption> listOfSelectionOptionChoices = new ArrayList<>();
		for (int i = 0; i < multipleChoiceMenus.size(); i++)
		{
			// DO NOT TRY TO LEARN FROM THIS. PLEASE LEAVE. DO NOT LOOK BACK.
			List<SelectionOption> selectionOptions = multipleChoiceMenus.get(i).getListOfSelectionOptions();
			ComponentGuestComboBox box = multipleChoiceBoxes.get(i);
			listOfSelectionOptionChoices.add(selectionOptions.get(box.getIndex()));
		}
		
		List<AddOnOption> listOfAddOnOptionChoices = new ArrayList<>();
		for (int i = 0; i < addOnCheckBoxes.size(); i++)
		{
			if (addOnCheckBoxes.get(i).isSelected())
			{
				listOfAddOnOptionChoices.add(addOnOptions.get(i));
			}
		}
		
		UtilityGuestInformation.getInstance().enterMainCourseOptions(potatoDish, listOfAddOnOptionChoices, listOfSelectionOptionChoices);
	}
}