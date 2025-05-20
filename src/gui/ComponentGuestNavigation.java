package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:29
 */
public class ComponentGuestNavigation extends JPanel
{
    /**
     * Constructs the guest navigation bar with optional "Back" and "Request Service" buttons.
     *
     * @param parentPanel     		The panel this navigation component should be added to.
     * @param currentFrame    		The currently displayed JFrame that should be closed when navigating back.
     * @param showServiceButton 	Whether or not to display the "Anmod Om Service" button.
     */
	public ComponentGuestNavigation(JPanel parentPanel, JFrame currentFrame, boolean showServiceButton)
	{
		super();
		
		////////////////////////////////////
		// - Navigation Container Panel - //
		////////////////////////////////////
		
		// Set the layout manager of this panel to BorderLayout
		this.setLayout(new BorderLayout());

		// Adds the specified amount of pixels as top and bottom padding
		this.setBorder(new EmptyBorder(8, 0, 8, 0));
		
		// Set the background color to Bone's beige/off-white color
		this.setBackground(new Color(245, 243, 236));

		// Add this navigation bar to the specified parent panel
		parentPanel.add(this, BorderLayout.SOUTH);
	}
}