package gui;

// Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * A custom GUI component that creates a button for a guest-facing GUI.
 * Unlike other buttons the visuals are made to look like a clickable hyperlink.
 * 
 * These are currently used for styling Anmod Om Service and Tilbage in the 
 * navigation section at the top of the GUI.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 13:02
 */
public class ComponentGuestNavigationButton extends JButton
{
	public ComponentGuestNavigationButton(String text)
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
		
		
		// Adds an action listener for when the button is clicked
		this.getModel().addActionListener(event ->
		{
			// If the button that was pressed contains the text "Anmod Om Service" button then execute this section
			if(this.getText().equals("Anmod Om Service 🔔"))
			{
				// Returns the parent window / jframe as a JFrame and stores it within the parentFrame variable
				JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				// Creates a dialog box informing about the action that went wrong
				new ComponentGuestErrorDialog(parentFrame, 
						"Følgende feature",
						"Anmod Om Service",
						"Er endnu ikke integreret i systemet"
				);
			}
		});
	}
}