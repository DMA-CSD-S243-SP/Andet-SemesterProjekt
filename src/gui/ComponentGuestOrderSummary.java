package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * A custom GUI component that displays a summary of a guest's order in the 
 * guest-facing GUI.
 *
 * This panel shows the guest's name, the total price of their order, and a 
 * list of ordered menu items.
 *
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 13:20
 */
public class ComponentGuestOrderSummary extends JPanel
{
    /**
     * Creates a new ComponentGuestOrderSummary with the given guest name, 
     * total price, and list of menu items.
     *
     * @param guestName the name of the guest/customer who the order is being summarized for
     * @param price the total price of the guest's order
     * @param items a list of item names representing the guest's order contents
     */
	public ComponentGuestOrderSummary(String guestName, double price, List<String> menuItems)
	{
		// Set the layout manager to BorderLayout
		setLayout(new BorderLayout());
		
		// Make the background transparent
		setOpaque(false);
		
		// Limit the width to 300px, height is flexible
		setMaximumSize(new Dimension(300, 999));
		
		// Center the panel horizontally within its container
		setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// ----------------------------
		// Top panel with name & price
		// ----------------------------
		
		// Create a subpanel using BorderLayout for left/right alignment
		JPanel topPanel = new JPanel(new BorderLayout());
		
		// Make the top panel transparent
		topPanel.setOpaque(false);

		// Create label for guest's name in uppercase
		JLabel labelName = new JLabel(guestName.toUpperCase());
		
		// Set bold font and size for name
		labelName.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		// Set dark gray color for name text
		labelName.setForeground(new Color(40, 40, 40));
		
		// Create label for the price
		JLabel labelPrice = new JLabel(price + ",-");
		
		// Set regular font and size for price
		labelPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		// Set dark gray color for price
		labelPrice.setForeground(new Color(40, 40, 40));
		
		// Align the price text to the right
		labelPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Add name label to the left of top panel
		topPanel.add(labelName, BorderLayout.WEST);
		
		// Add price label to the right of top panel
		topPanel.add(labelPrice, BorderLayout.EAST);

		// ----------------------
		// Panel for item lines
		// ----------------------
		
		// Create panel to hold all item labels vertically
		JPanel itemPanel = new JPanel();
		
		// Use vertical layout
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		
		// Make the menuItems panel transparent
		itemPanel.setOpaque(false);
		
		// Add top padding (10px) to create space between name and menuItems
		itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		// Loop through the list of item strings
		for (String menuitem : menuItems)
		{
			// Create a label for each item with italic font
			JLabel lblItem = new JLabel("1x " + menuitem);
			
			// Sets the font to the specified style type and size
			lblItem.setFont(new Font("SansSerif", Font.ITALIC, 16));
			
			// Changes the color of the text to dark grey
			lblItem.setForeground(new Color(70, 70, 70));
			
			// Add the item label to the panel
			itemPanel.add(lblItem);
			
			// Add small vertical spacing after each item
			itemPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

		// Add the top panel to the top of the main layout
		add(topPanel, BorderLayout.NORTH);
		
		// Add the item panel to the center of the main layout
		add(itemPanel, BorderLayout.CENTER);
	}
}