package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


/**
 * A custom GUI JButton component designed for guest users to request service.
 * 
 * This button has removed the default borders and background to make it appear
 * as an interactable hyperlink.
 * 
 * 
 * Author: Christoffer Søndergaard
 * Version: 08/06/2025 - 13:59
 */	
public class ComponentGuestServiceButton extends JButton
{
    /**
     * Constructs a new service request button with customized styling
     *
     * @param text the text to display inside of the button
     */
	public ComponentGuestServiceButton(String text)
	{	
		super(text);

		// Define the normal font color (Bone's red)
		Color normalColor = new Color(187, 41, 41);

		// Define a darker red color to use when the button is pressed
		Color pressedColor = new Color(130, 20, 20);

		// Set the default text color
		this.setForeground(normalColor);
		
		// Turns off the focus border when the button is being selected
		this.setFocusPainted(false);

		// Disables the painting on the button's border
		this.setBorderPainted(false);
		
		// makes the button's background transparent
		this.setContentAreaFilled(false);

		// Align the button's text to the right within the button
		this.setHorizontalAlignment(JButton.LEFT);

		// Sets the font to the specified style type and size
		this.setFont(new Font("SansSerif", Font.PLAIN, 12));

		// Add a ChangeListener to monitor the model state of the button whether it is being pressed or not
		this.getModel().addChangeListener(event -> 
		{
			// If the button is being pressed then execute this section
			if (this.getModel().isPressed())
			{
				// Changes the button's text color to the dark red
				this.setForeground(pressedColor);
			}
			
			// If the button is not being pressed then execute this section
			else
			{
				// Changes the button's text color to the light red bone's color
				this.setForeground(normalColor);
			}
		});
	}
}