package gui;

//Imports
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * A custom-styled JCheckBox component for guest-facing interfaces in the Bone's restaurant system.
 *
 * The class also includes a method that wraps the checkbox in a pre-styled JPanel to assist with
 * alignment and layout consistency in the GUI, this is currently useed in teh ViewGuestDiscountSelection
 * class as well.
 *
 *
 * Author: Christoffer Søndergaard  
 * @version 07/06/2025 - 20:55
 */
public class ComponentGuestCheckBox extends JCheckBox
{
	/**
	 * Constructs a customized JCheckBox component with Bone’s styling.
	 *
	 * Applies transparent background, custom font, color, padding, and alignment settings
	 * to match the look and feel used in the guest interface.
	 *
	 * @param text the label text shown next to the checkbox
	 */
	public ComponentGuestCheckBox(String text)
	{
		super(text);

		// Turns the background of this opague /transparent
		setOpaque(false);
		
		// Sets the font to the specified style type and size
		setFont(new Font("SansSerif", Font.ITALIC, 16));
		
		// Sets the text color to a dark medium grey
		setForeground(new Color(62, 62, 62));

		// Adds the specified padding to the checkboxes
		setBorder(new EmptyBorder(12, 0, 12, 0));
		
		// Align text to the left of the checkbox
		setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	
	/**
	 * Wraps this checkbox inside a styled JPanel with layout and spacing adjustments.
	 *
	 * This method is useful for maintaining consistent layout across different UI sections
	 * where checkboxes need to be centered and evenly spaced.
	 *
	 * @return the JPanel that contains the stylized checkbox
	 */
	public JPanel applyWrapperStyling()
	{
		// Create a new panel to help fix the location of the checkboxes
		JPanel panelStyleAdjusting = new JPanel();

		// Changes the layout type to a BoxLayout and make components align horizontally
		panelStyleAdjusting.setLayout(new BoxLayout(panelStyleAdjusting, BoxLayout.X_AXIS));

		// Turns the background of this opague /transparent
		panelStyleAdjusting.setOpaque(false);

		// Put a limits on the maximum size of the panel to force a max width
		panelStyleAdjusting.setMaximumSize(new Dimension(300, 40));

		// Aligns the panel horizontally within the parent container
		panelStyleAdjusting.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Adds the custom checkbox component to the panel
		panelStyleAdjusting.add(this);
		
		// Adds additional vertical space between each of the checkboxes
		panelStyleAdjusting.add(Box.createRigidArea(new Dimension(0, 10)));

		return panelStyleAdjusting;
	}
}