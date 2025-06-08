package gui;

// Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.SwingConstants;


/**
 * A custom JButton component designed for the staff-facing side of the GUI.
 *
 * It extends JButton and overrides the paintComponent method to implement
 * color changes when pressed.
 *
 *
 * Author: Christoffer Søndergaard
 * Version: 08/06/2025 - 14:21
 */
public class ComponentStaffButton extends JButton
{
    /**
	 * Constructs a custom continue button with stylized visuals and layout for the
	 * staff-facing GUI.
	 * 
     *
     * @param text The text to display on the button
     * @param isColored If true, the button uses a red color otherwise it uses a dark grey color
     */
	public ComponentStaffButton(String text, boolean isColored)
	{
		super(text);

		// Disable the default focus ring
		setFocusPainted(false);

		// We'll paint the button manually
		setContentAreaFilled(false);
		
		// Don't use the default button border
		this.setBorderPainted(false);
		
		// Set font to the specified settings
		this.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		// Center text horizontally inside the button
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Center text vertically inside the button
		this.setVerticalAlignment(SwingConstants.CENTER);
		
		// Set preferred size for layout managers
		this.setPreferredSize(new Dimension(160, 45));
		
		// Limit max height to keep UI consistent
		this.setMaximumSize(new Dimension(160, 50));	
		
		if(isColored == true)
		{
			// Set default background (Bone’s red)
			this.setBackground(new Color(187, 41, 41));
			
			// Set text color to white
			this.setForeground(new Color(255, 255, 255));
		}
		
		else
		{
			// Set default background (Bone’s dark grey)
			this.setBackground(new Color(62, 62, 62));
			
			// Set text color to white
			this.setForeground(new Color(255, 255, 255));
		}
	}

	
	/**
	 * Overrides the standard painting behavior to draw a custom rounded button with a
	 * colored background, border, and dynamic press effects.
	 *
	 * @param graphics the Graphics context used for painting
	 */
	@Override
	protected void paintComponent(Graphics graphics)
	{
		// The radius used to round the corners of the button
		int cornerRadius = 8;

		// The thickness of the dark outer border drawn behind the button
		int borderThickness = 1;
		
		// Create a copy of the graphics context for custom drawing
		Graphics2D graphics2D = (Graphics2D) graphics.create();

		// Enable anti-aliasing for smooth rounded edges
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw outer border in black
		graphics2D.setColor(new Color(0, 0, 0));
		
		// Fills out the specified rounded corner triangle
		graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius + borderThickness, cornerRadius + borderThickness);

		// Modifies the fill coloring of the button whenever it is pressed or unpressed
		if (getModel().isArmed())
		{
			// Changes the button's background color to a darker version of the color
			graphics2D.setColor(getBackground().darker());
		}
		
		else
		{
			// Changes the button's background color to the normal background color
			graphics2D.setColor(getBackground());
		}
		
		// Draws the main rounded button inside of the defined black border border
		graphics2D.fillRoundRect(borderThickness, borderThickness, getWidth() - (borderThickness * 2), getHeight() - (borderThickness * 2), cornerRadius, cornerRadius);

		// Paints the contents of the button on top
		super.paintComponent(graphics);

		// Disposes of the graphics object to free up resources
		graphics2D.dispose();
	}
}