package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * A custom GUI component that creates an area that serves as a navigation bar which
 * will be displayed at the upper part of the guest-facing view in the application.
 * 
 * The component is flexible and can optionally include or exclude a Tilbage button
 * and/or a Anmod Om Service button depending on the needs for the current window.
 * 
 * It is typically used to allow users to return to the previous screen or
 * request service.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 12:28
 */
public class ComponentGuestNavigation extends JPanel
{
    /**
     * Constructs the guest navigation bar with optional "Back" and "Request Service" buttons.
     *
     * @param parentPanel the panel this navigation component should be added to.
     * @param currentFrame the currently displayed JFrame that should be closed when navigating back.
     * @param showServiceButton determines whether the "Anmod Om Service" button is shown.
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