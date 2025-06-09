package gui;

// Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * A custom JLabel component used for displaying section headings in the
 * guest-facing GUI.
 * 
 * Simplifies the creation of large, centered text used to describe the essence
 * of the current frame or window.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 09/06/2025 - 07:42
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
		
		// Removes the specified amount of pixels between the above and below components
		this.add(Box.createRigidArea(new Dimension(0, -2)));
		
		// Aligns the label horizontally to the center within its container
		this.setHorizontalAlignment(SwingConstants.CENTER);

		// Sets a maximum width while allowing the height to adjust automatically
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		// Ensures that this component is horizontally centered within its parent container when using a BoxLayout
		this.setAlignmentX(CENTER_ALIGNMENT);

		// Restricts the component's maximum width to the specified pixels, while allowing the height to adapt
		// to its preferred size this then prevents the component from stretching to the full width 
		// and thereby maintains its centered appearance
		this.setMaximumSize(new Dimension(340, getPreferredSize().height));
	}
}