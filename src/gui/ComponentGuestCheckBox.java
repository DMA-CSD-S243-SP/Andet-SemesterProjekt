package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 02:33
 */
public class ComponentGuestCheckBox extends JCheckBox
{
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
		
		// Adds some spacing between the component above and below
//		this.add(Box.createRigidArea(new Dimension(0, 15)));
	}
	
	

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