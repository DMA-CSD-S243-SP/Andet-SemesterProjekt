package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * A custom GUI component that displays a summary of the total price for a guest's PersonalOrder.
 *
 * This component shows the guest's name and their total order price on each side.
 * It is designed for use in guest-facing GUI to highlight the final cost of their PersonalOrder.
 *
 *
 * Author: Christoffer Søndergaard
 * Version: 08/06/2025 - 13:38
 */
public class ComponentGuestOrderTotalPrice extends JPanel
{
    /**
     * Constructs a new ComponentGuestOrderTotalPrice.
     *
     * Displays the guest's name on the left and the total price on the right.
     *
     * @param guestName the name of the guest/customer who's total price should be shown
     * @param price the final total amount for the guest's order
     */
	public ComponentGuestOrderTotalPrice(String guestName, double price)
	{
		super();
		
		// Set the layout of the outer panel to BorderLayout
		setLayout(new BorderLayout());

		// Make the background transparent
		setOpaque(false);

		// This line creates vertical spacing but does not add it to the layout – likely a mistake or leftover
		Box.createRigidArea(new Dimension(0, 35));

		// Sets a maximum width of 300px and flexible height honestly using 999 
		// because i was unable to find something along the lines of dimensions.heightMaxSize
		// but it works for our purpose, so don't change this for the time being, this is a TODO for later
		setMaximumSize(new Dimension(300, 999));

		// Center this component horizontally in its parent container
		setAlignmentX(Component.CENTER_ALIGNMENT);

		
		
		////////////////////////
		// - Panel & Labels - //
		////////////////////////
		
		
		// Create a new panel with BorderLayout to hold name and price
		JPanel topPanel = new JPanel(new BorderLayout());

		// Make the top panel transparent
		topPanel.setOpaque(false);

		// Create a label to display the guest's name in uppercase
		JLabel labelName = new JLabel(guestName.toUpperCase());

		// Set bold font and size for the guest name
		labelName.setFont(new Font("SansSerif", Font.BOLD, 20));

		// Set a dark gray color for the guest name
		labelName.setForeground(new Color(40, 40, 40));

		// Create a label to display the price
		JLabel labelPrice = new JLabel(price + ",-");

		// Set plain font and size for the price
		labelPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));

		// Set the same dark gray color for the price
		labelPrice.setForeground(new Color(40, 40, 40));

		// Align the price to the right within the top panel
		labelPrice.setHorizontalAlignment(SwingConstants.RIGHT);

		// Add the name label to the left side of the top panel
		topPanel.add(labelName, BorderLayout.WEST);

		// Add the price label to the right side of the top panel
		topPanel.add(labelPrice, BorderLayout.EAST);

		// Add the top panel to the top section of this component
		add(topPanel, BorderLayout.NORTH);
	}
}