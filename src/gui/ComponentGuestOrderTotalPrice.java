package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;



/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 01:08
 */	
public class ComponentGuestOrderTotalPrice extends JPanel
{
	public ComponentGuestOrderTotalPrice(String guestName, double price)
	{
		super();
		
		// Set the layout of the outer panel to BorderLayout
		setLayout(new BorderLayout());

		// Make the background transparent
		setOpaque(false);

		// This line creates vertical spacing but does not add it to the layout – likely a mistake or leftover
		Box.createRigidArea(new Dimension(0, 35));

		// Set a maximum width of 300px and flexible height
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

		// Add the top panel to the top (NORTH) section of this component
		add(topPanel, BorderLayout.NORTH);
	}
}