package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 08:45
 */	
public class ComponentGuestMenuItem extends JPanel
{
	private JButton btnAdd;
	private model.MainCourse mainCourse;

	public ComponentGuestMenuItem(model.MainCourse menuItem)
	{
		mainCourse = menuItem;
		// Set layout manager to BorderLayout for this component
		this.setLayout(new BorderLayout());

		// Make the background transparent
		this.setOpaque(false);

		// Limit the width to 350px and fix height to 70px (adjustable)
		this.setMaximumSize(new Dimension(350, 70));

		
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
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));

		// Set the title text color to a dark gray
		lblTitle.setForeground(new Color(40, 40, 40));

		// Create the description label using HTML for italic styling
		JLabel lblDescription = new JLabel("<html><i>" + menuItem.getDescription() + "</i></html>");

		// Set the font style and size for the description
		lblDescription.setFont(new Font("SansSerif", Font.PLAIN, 13));

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
		
		JLabel lblPrice = new JLabel(price + ",-");
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 15));
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

		// Set the preferred size of the button to 35x35
		btnAdd.setPreferredSize(new Dimension(35, 35));

		// Ensure the button can't grow beyond this size
		btnAdd.setMaximumSize(new Dimension(35, 35));

		// Ensure the button doesn't shrink below this size
		btnAdd.setMinimumSize(new Dimension(35, 35));

		// Add spacing and then the price and button to right panel
		rightPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		rightPanel.add(lblPrice);
		
		rightPanel.add(Box.createRigidArea(new Dimension(6, 0)));
		rightPanel.add(btnAdd);

		// Add text panel to the center of the layout
		this.add(textPanel, BorderLayout.CENTER);
		
		// Add right-side panel (price + button) to the right of the layout
		this.add(rightPanel, BorderLayout.EAST);

		// Add bottom padding to separate from other components
		//this.setBorder(new EmptyBorder(0, 0, 20, 0));
	}

	/**
	 * Returns a reference to the add button.
	 * Allows external classes to register action listeners or modify the button.
	 */
	public JButton getAddButton()
	{
		return btnAdd;
	}
	
	public model.MainCourse getMainCourse()
	{
		return mainCourse;
	}
}