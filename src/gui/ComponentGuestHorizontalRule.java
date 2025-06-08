package gui;


// Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;


/**
 * A custom horizontal rule component used to visually separate sections in the guest-facing GUI.
 *
 * This class creates a horizontal red line (using a Swing JSeparator) and wraps it inside
 * a transparent JPanel with a centered layout.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 09:49
 */
public class ComponentGuestHorizontalRule extends JPanel
{
    /**
     * Constructs a horizontal rule styled as a red line separator.
     * 
     * The separator is centered within a transparent container, with a fixed width
     * and height to ensure a consistent appearance across different views.
     */
	public ComponentGuestHorizontalRule()
	{
		super();
		
		// Disables the background
		this.setOpaque(false);
		
		// Uses a centered flow layout
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// Create sthe seperator object
		JSeparator separator = new JSeparator();
		
		// Sets the width of it to about half the screen width
		separator.setPreferredSize(new Dimension(180, 2));
		
		// Changes the color to the bone's light red color
		separator.setBackground(new Color(187, 41, 41));
		
		// Adds the sepeartor
		this.add(separator);
	}
}