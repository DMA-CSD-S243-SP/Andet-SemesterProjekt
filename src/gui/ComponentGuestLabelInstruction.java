package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:25
 */
public class ComponentGuestLabelInstruction extends JLabel
{
	public ComponentGuestLabelInstruction(String instructionText, JPanel attachedPanel)
	{
		super();
		
		// Removes the specified amount of pixels between the above and below components
		attachedPanel.add(Box.createRigidArea(new Dimension(0, -2)));
		
		// Sets the text of the label to use HTML to center align the string specified in the method parameter
		this.setText("<html><div style='text-align: center;'>" + instructionText + "</div></html>");
		
		// Sets the font to the specified style type and size
		this.setFont(new Font("SansSerif", Font.PLAIN, 14));

		// Sets the text color to a dark medium grey
		this.setForeground(new Color(62, 62, 62));

		// Aligns the label vertically to the center within its container
		this.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		// Aligns the label horizontally to the center within its container
		this.setHorizontalAlignment(SwingConstants.CENTER);

		// Sets a maximum width while allowing the height to adjust automatically
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	}
}

