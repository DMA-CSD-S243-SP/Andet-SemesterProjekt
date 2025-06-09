package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MenuItem;


/**
 * A custom GUI component that creates a menu item section for a guest-facing GUI.
 * 
 * This component visually presents a menu item's name, description, price,
 * and a "+" button that allows the user to add the item to their order.
 * 
 * This class extends JPanel and includes embedded labels and a button 
 * to make it fully interactive and suitable for dynamic GUIs.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 12:11
 */
public class ComponentGuestMenuItem extends JPanel
{
	private JButton btnAdd;
	private model.MenuItem menuItem;

	
	/**
	 * Constructs a new ComponentGuestMenuItem with visual representation
	 * of the supplied MenuItem.
	 *
	 * It adds a title, description, price label, and an add button
	 * to the panel. The right section includes price and button, and the center 
	 * area contains item title and description.
	 *
	 * @param menuItem the MenuItem object to be displayed
	 */
	public ComponentGuestMenuItem(model.MenuItem menuItem)
	{
		this.menuItem = menuItem;
		// Set layout manager to BorderLayout for this component
		this.setLayout(new BorderLayout());

		// Make the background transparent
		this.setOpaque(false);

		
		//////////////////////////////
		// - Title & Descriptions - //
		//////////////////////////////
		
		
		// Create a new panel to hold the title and description labels
		JPanel textPanel = new JPanel();

		// Set the layout to stack components vertically (top to bottom)
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

		// Make the panel transparent (inherits parent background)
		textPanel.setOpaque(false);

		// Create the title label with uppercase text
		JLabel lblTitle = new JLabel(menuItem.getName().toUpperCase());

		// Set the font style and size for the title
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

		// Set the title text color to a dark gray
		lblTitle.setForeground(new Color(40, 40, 40));

		// Create the description label using HTML for italic styling
		JLabel lblDescription = new JLabel("<html><i>" + menuItem.getDescription() + "</i></html>");

		// Set the font style and size for the description
		lblDescription.setFont(new Font("SansSerif", Font.PLAIN, 11));

		// Set the description text color to a slightly lighter gray
		lblDescription.setForeground(new Color(70, 70, 70));

		// Add the title label to the panel
		textPanel.add(lblTitle);

		// Add the description label below the title
		textPanel.add(lblDescription);


		
		////////////////////////////
		// - Price & Add Button - //
		////////////////////////////

		// Create a panel to hold the contents
		JPanel rightPanel = new JPanel();

		// Use horizontal layout for price and button side by side
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));

		// Make the right panel transparent
		rightPanel.setOpaque(false);

		// Create the label
		double price = menuItem.getEveningPrice();
		/* TODO Fix fetched TableOrders, so time actually gets set.
		if (UtilityGuestInformation.getInstance().isLunchTime())
		{
			price = menuItem.getLunchPrice();
		}*/
		
		// Creates a label called price that takes the passed price method parameter and adds ,- behind it
		JLabel lblPrice = new JLabel(price + ",-");
		
		// Changes the font styling to the specified style
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		// Changes the font color to  black
		lblPrice.setForeground(Color.BLACK);

		// Create the "+" add button
		btnAdd = new JButton("+");

		// Set a large bold font for visibility
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 26));

		// Set the text color to white
		btnAdd.setForeground(Color.WHITE);

		// Set the background color to Bone's signature red
		btnAdd.setBackground(new Color(187, 41, 41));

		// Remove the default focus border styling
		btnAdd.setFocusPainted(false);

		// Apply custom inner padding for spacing inside the button
		btnAdd.setMargin(new Insets(3, 8, 3, 8));

		// Remove the default button border
		btnAdd.setBorder(BorderFactory.createEmptyBorder());

		// Show a hand cursor when hovering over the button
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Set the preferred size of the button to 30x30
		btnAdd.setPreferredSize(new Dimension(30, 30));

		// Ensure the button can't grow beyond this size
		btnAdd.setMaximumSize(new Dimension(30, 30));

		// Ensure the button doesn't shrink below this size
		btnAdd.setMinimumSize(new Dimension(30, 30));

		// Add spacing and then the price and button to right panel
		rightPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		rightPanel.add(lblPrice);
		
		// Adds spacing and then the + button to the right panel
		rightPanel.add(Box.createRigidArea(new Dimension(6, 0)));
		rightPanel.add(btnAdd);

		// Add text panel to the center of the layout
		this.add(textPanel, BorderLayout.CENTER);
		
		// Add right-side panel (price + button) to the right of the layout
		this.add(rightPanel, BorderLayout.EAST);

		// Ensures that this component is horizontally centered within its parent container when using a BoxLayout
		this.setAlignmentX(CENTER_ALIGNMENT);

		// Restricts the component's maximum width to the specified pixels, while allowing the height to adapt
		// to its preferred size this then prevents the component from stretching to the full width 
		// and thereby maintains its centered appearance
		this.setPreferredSize(new Dimension(340, getPreferredSize().height));
		this.setMaximumSize(new Dimension(340, getPreferredSize().height));
	}

	
	/**
	 * Returns the "+" button component used for adding the item to an order.
	 * This allows external GUI classes to attach an ActionListener to handle the on-click event.
	 *
	 * @return the add button for this menu item component
	 */
	public JButton getAddButton()
	{
		return btnAdd;
	}
	
	
	/**
	 * Returns the MenuItem object associated with the ComponentGuestMenuItem.
	 *
	 * @return the MenuItem displayed in the ComponentGuestMenuItem
	 */
	public MenuItem getMenuItem()
	{
		return menuItem;
	}
}