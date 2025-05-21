package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 02:33
 */
public class ComponentFrameThemeGuest
{
	private JPanel mainPanel;
	private JPanel panelPrimaryContent;
	
	private ComponentGuestPanel bottomPanel;
	private ComponentGuestNavigation navigationPanel;
	
	
	public void applyGeneralVisuals(String bannerTitle, JFrame currentFrame, String headingText, String instructionText)
	{
		// Sets the operation that will occur when the close window button (x) is clicked to exit the application altogether 
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets a title shown in the top left corner of the window
		currentFrame.setTitle("Bone's");
		
		// Modifies the visual appearance of the main panel
		modifyMainPanel(currentFrame);
		
		// Sets a minimum width and height for the window's dimensions and makes the window launch in this size (1280 x 720)
		// Aspect ratio 1 : 2,16533 (Width to height)
		adjustWindowSize(currentFrame, 380, 822);
		
		// Sets the favorite icon of the application to the specified image
		setFavIcon(currentFrame, "/favIcon.png");
		
		// Creates a panel containing a header in the form of an image in a northern panel 
		createPanelHeader(currentFrame, "/bones_logo.png", bannerTitle);
		
		// Creates a content container, with a heading and an instruction text
		// along with the primary content panel
		createContentContainer(headingText, instructionText);		
		
		
		
		createBottomButtons();
	}


	private void modifyMainPanel(JFrame frame)
	{
		// Creates a new panel and stores it as the mainPanel this is the panel that will contain all other panels
		mainPanel = new JPanel();
		
		// Changes the background to Bone's off-white / beige background color
		mainPanel.setBackground(new Color(245, 243, 236));
		
		// Changes the width of the border of the panel to be 0 pixels on all of the sides
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		// Changes the layout for the panel to use the border layout
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		// Sets the mainPanel to act as the main area holding other components such as panels, textfields, buttons, label and so forth
		frame.setContentPane(mainPanel);
	}
	
	
	private void createPanelHeader(JFrame frame, String headerLogoPath, String headerTitle)
	{
		// Creates a custom component that sets the header's color, logo and specified text
		ComponentGuestHeaderTitle panelHeader = new ComponentGuestHeaderTitle(mainPanel, headerLogoPath, headerTitle);

		// Instantiates the navigationPanel that holds the navigation hyperlink buttons
		navigationPanel = new ComponentGuestNavigation(panelHeader, frame, true);
	}

	
	private void createContentContainer(String headingText, String instructionText)
	{
		///////////////////////////////////////
		// - Outer Content Container Panel - //
		///////////////////////////////////////
		
		// Creates a new panel that serves as the outer container for scrollable content panel
		JPanel panelContent = new JPanel();

		// Sets the background color to match the Bone's theme (light beige)
		panelContent.setBackground(new Color(245, 243, 236));

		// Uses BorderLayout to allow placement of inner content at specific positions (e.g. NORTH)
		panelContent.setLayout(new BorderLayout());

		// Adds padding around the content: 30px top/bottom, 20px left/right
		panelContent.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		
		
		///////////////////////////////////////
		// - Inner Content Container Panel - //
		///////////////////////////////////////
		
		// Creates the inner panel that holds all main content components in a vertical stack
		JPanel panelInnerContent = new JPanel();

		// Sets the background color to match the Bone's UI theme
		panelInnerContent.setBackground(new Color(245, 243, 236));

		// Applies a vertical BoxLayout so components are added from top to bottom
		panelInnerContent.setLayout(new BoxLayout(panelInnerContent, BoxLayout.Y_AXIS));

		// Adds padding: 30px top/bottom and 20px left/right for spacing around content
		panelInnerContent.setBorder(new EmptyBorder(0, 20, 0, 20));

		
		
		/////////////////////////////
		// - Instruction Heading - //
		/////////////////////////////
		
		// Creates a heading label with the specified text
		ComponentGuestLabelHeading labelHeading = new ComponentGuestLabelHeading(headingText);

		// Adds the heading to the inner content panel
		panelInnerContent.add(labelHeading);
		
		
		
		//////////////////////////
		// - Instruction Text - //
		//////////////////////////
		
		// Creates a component of centered text meant to contain instructions for the user to follow
		ComponentGuestLabelInstruction labelInstructionText = new ComponentGuestLabelInstruction(instructionText, panelInnerContent);

		// Adds the instruction text component to the inner content panel
		panelInnerContent.add(labelInstructionText);

		
		
		//////////////////////////
		// - Horizontal Ruler - //
		//////////////////////////
		
		// Adds the specified amount of pixels between the above and below components
		panelInnerContent.add(Box.createRigidArea(new Dimension(0, 20)));

		// Creates a custom 'horizontal rule' component in the form of panel to visually separate sections
		ComponentGuestHorizontalRule horizontalRulePanel = new ComponentGuestHorizontalRule();
		
		// Adds the 'horizontal rule' component to the inner content panel
		panelInnerContent.add(horizontalRulePanel);
		
		
		
		///////////////////////////////
		// - Primary Content Panel - //
		///////////////////////////////

		// Initializes the panel used for adding dynamic, window-specific content
		panelPrimaryContent = new JPanel();

		// Sets the background color to Bone's off-white/beige color
		panelPrimaryContent.setBackground(new Color(245, 243, 236));

		// Changes the layout to the a vertical oriented box layout so added components stack from top to bottom
		panelPrimaryContent.setLayout(new BoxLayout(panelPrimaryContent, BoxLayout.Y_AXIS));

		// Adds the specified amount of vertical padding in pixels at the top of the panel
		panelPrimaryContent.setBorder(new EmptyBorder(35, 0, 0, 0));
		
		// Adds the dynamic content panel to the inner vertical container
		panelInnerContent.add(panelPrimaryContent);
		
		
		
		///////////////////////////////
		// - THIS IS WHERE CONTENT - //
		//   SHOULD BE INSERTED IN   //
		///////////////////////////////
		
		/* The content in this section is not directly
		 * added in to this section, but is rather being
		 * placed in here from their respective 
		 * view / frames by accessing the primary content panel
		 * and adding components to it
		 * 
		 */

		
		
		////////////////////////
		// - Scrolling Pane - //
		////////////////////////

		// Creates a scroll pane that only shows vertical scroll when needed
		ComponentGuestScrollPane scrollPane = new ComponentGuestScrollPane(panelContent, panelInnerContent);

		// Adds the inner content to the top section of the scrollable area
		panelContent.add(panelInnerContent, BorderLayout.NORTH);

		// Places the scroll pane in the center of the main layout
		mainPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	
	private void createBottomButtons()
	{
		// Instantiates the bottomPanel that holds content
		bottomPanel = new ComponentGuestPanel();

		// Uses a vertical BoxLayout to stack buttons from top to bottom
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		// Adds vertical padding of 20 pixels at the top and bottom of the panel
		bottomPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

		// Adds an invisible border to the bottom panel, with a width of 60 pixel to add some space below the panel
		bottomPanel.setBorder(new EmptyBorder(0, 0, 60, 0));
		
		// Adds the entire panel to the mainPanel at the bottom
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	private void adjustWindowSize(JFrame frame, int width, int height)
	{
		// Changes the window's dimensions to be set to the value of width x height when the window is launched
		frame.setBounds(100, 100, width, height);
		
		// Sets a minimum size of the window preventing it from becoming smaller than width x height
		frame.setMinimumSize(new Dimension(width, height));
		
		// Defines where on the screen the window will be positioned and sets it to be in the center of the screen - NOTE: That this must be set after sizings has adjusted 
		frame.setLocationRelativeTo(null);
	}
	
	
	private void setFavIcon(JFrame frame, String favIconPath)
	{
		// Finds the resource with the name specified in the method's parameter and stores its url destination in the local imageUrlPath variable 
		URL imageUrlPath = getClass().getResource(favIconPath);
		
		// If the imageUrlPath is not null then execute this section
		if (imageUrlPath != null)
		{
			// Creates and loads an ImageIcon by using the resource file found at the provided url path
			ImageIcon favoriteIcon = new ImageIcon(imageUrlPath);
			
			// Retrieves the icon's image and store it within the favoriteIconImage variable
			Image favoriteIconImage = favoriteIcon.getImage();
			
			// Sets the window's image icon to the image stored within the favoriteIconImage variable
			frame.setIconImage(favoriteIconImage);
		}
	}
	
	
	public JPanel getPrimaryContentPanel()
	{
		return panelPrimaryContent;
	}

	
	public JPanel getBottomContentPanel()
	{
		return bottomPanel;
	}
	
	
	public JPanel getMainPanel()
	{
		return mainPanel;
	}
	
	
	public JPanel getNavigationPanel()
	{
		return navigationPanel;
	}
}