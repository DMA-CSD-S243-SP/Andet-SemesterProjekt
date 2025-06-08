// Packages
package gui;

//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * A custom JLabel used for displaying user instructions within the guest-facing GUI.
 * 
 * This component ensures that instruction text is styled consistently and centered
 * within the associated panel.
 * 
 * It also supports automatic layout spacing and constrained width by using HTML code styling.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 12:11
 */
public class ComponentGuestLabelInstruction extends JLabel
{
    /**
     * Constructs a new ComponentGuestLabelInstruction with the given instruction text.
     * It is automatically added to the specified panel.
     * 
     * @param instructionText the message to be shown as instructions for the guest user
     * @param attachedPanel the panel this label will be added to, including vertical spacing logic
     */
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

