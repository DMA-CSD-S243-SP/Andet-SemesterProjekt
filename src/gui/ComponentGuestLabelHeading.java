package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:25
 */
public class ComponentGuestLabelHeading extends JLabel
{
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