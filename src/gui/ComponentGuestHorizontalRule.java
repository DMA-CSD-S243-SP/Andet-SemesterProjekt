package gui;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:14
 */
public class ComponentGuestHorizontalRule extends JPanel
{
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