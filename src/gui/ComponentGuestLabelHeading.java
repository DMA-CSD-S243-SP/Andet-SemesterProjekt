package gui;

// Imports
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


/**
 * A custom JLabel component used for displaying section headings in the
 * guest-facing GUI.
 * 
 * Simplifies the creation of large, centered text used to describe the essence
 * of the current frame or window.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 11:41
 */
public class ComponentGuestLabelHeading extends JLabel
{
    /**
     * Constructs a new ComponentGuestLabelHeading with a specified heading text.
     * 
     * @param heading the text content to display in the label
     */
	public ComponentGuestLabelHeading(String heading)
	{
		super(heading);
		
		// Sets the font to the specified style type and size
		this.setFont(new Font("SansSerif", Font.PLAIN, 23));

		// Sets the text color to a dark medium grey
		this.setForeground(new Color(62, 62, 62));

		// Align the label horizontally in the center within the container
		this.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	}
}