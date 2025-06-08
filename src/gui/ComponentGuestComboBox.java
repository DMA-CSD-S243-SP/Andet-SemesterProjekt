package gui;

// Imports
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * A custom-styled combo box component for guest-facing UI in Bone's restaurant system.
 * 
 * The component is intended for use in MainCourses to select Doneness and sizes,
 * in the form of a dropdown-based menu selection.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * @version 07/06/2025 - 21:01
 */
public class ComponentGuestComboBox extends JPanel
{
	private JComboBox<String> comboBox;
	
	
	/**
	 * Constructs a customized combo box component with label heading and styled dropdown.
	 * 
	 * The constructor takes a descriptive label to display above the combo box and a list of
	 * options that the user can choose from. Styling is applied to both the label and combo box.
	 * 
	 * @param labelHeadingText the text to show as a heading above the combo box
	 * @param options the list of string options to populate the combo box with
	 */
	public ComponentGuestComboBox(String labelHeadingText, List<String> options)
	{
		super();
		
		//////////////////////////
		// - JPanel Modifying - //
		//////////////////////////
		
		// Sets the layout being used to a boxlayout where items are added downwards first
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Aligns the components to center
		this.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Sets the background to be opague / transparent
		this.setOpaque(false);
		
		
		//////////////////////
		// - Heading Text - //
		//////////////////////
		
		// Creates a new label object
		JLabel lblHeadingTitle = new JLabel(labelHeadingText);
		
		// Sets the font to the specified style type and size
		lblHeadingTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		// Sets the text color to a dark medium grey
		lblHeadingTitle.setForeground(new Color(62, 62, 62));
		
		// Align the label horizontally in the center within the container
		lblHeadingTitle.setAlignmentX(lblHeadingTitle.CENTER_ALIGNMENT);

		// Adds the label to the JPanel to make it be displayed
		this.add(lblHeadingTitle);

		
		//////////////////
		// - Combobox - //
		//////////////////
		
		// Space between label and dropdown
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		// Convert list to array and create combo box
		comboBox = new JComboBox<>(options.toArray(new String[0]));
		
		// Sets the font to the specified style type and size
		comboBox.setFont(new Font("SansSerif", Font.ITALIC, 14));
		
		// Limit max size of the UI component
		comboBox.setMaximumSize(new Dimension(320, 40));

		// Align the label horizontally in the center within the container
		comboBox.setAlignmentX(comboBox.CENTER_ALIGNMENT);

		// Sets the text color to a dark medium grey
		comboBox.setForeground(new Color(62, 62, 62));
		
		// Sets the background color to a dimmed white
		comboBox.setBackground(new Color(246, 246, 246));

		// Adds the combobox to the panel
		this.add(comboBox);
		
		// Adds some spacing between the component above and below
		this.add(Box.createRigidArea(new Dimension(0, 25)));
	}

	
	/**
	 * Returns the currently selected option from the dropdown.
	 * 
	 * @return The selected option as a String
	 */
	public String getSelectedOption()
	{
		return (String) comboBox.getSelectedItem();
	}
	
	
	/**
	 * Returns the index of the currently selected item.
	 *
	 * @return the index position of the selected item
	 */
	public int getIndex()
	{
		return comboBox.getSelectedIndex();
	}
}