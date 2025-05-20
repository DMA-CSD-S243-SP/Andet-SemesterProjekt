package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:19
 */	
public class ComponentGuestInputField extends JTextField
{
	// Used to store the placeholder text input (not actually user input)
	private String placeholderInput;

	// Declare filter instance to toggle on/off dynamically
	private UtilityInputFilterNumbersOnly onlyNumbersFilter;
	private UtilityInputFilterLettersOnly onlyLettersFilter;
	
	
	/**
	 * Constructs the input field with a placeholder and an optional input filter.
	 *
	 * @param placeholderText The placeholder text to display when the field is empty.
	 * @param filterType The type of input restriction (e.g., "onlyNumbers"), or null for no filter.
	 */
	public ComponentGuestInputField(String placeholderText, String filterType)
	{
		super();
		this.placeholderInput = placeholderText;

		// Apply numeric input filter only once, but deactivate it initially
		if ("onlyNumbers".equals(filterType))
		{
			onlyNumbersFilter = new UtilityInputFilterNumbersOnly();
			
			AbstractDocument doc = (AbstractDocument) getDocument();
			doc.setDocumentFilter(onlyNumbersFilter);
			
			// Initially inactive to allow placeholder
			onlyNumbersFilter.setActive(false);
		}
		
		else if ("onlyLetters".equals(filterType))
		{
			onlyLettersFilter = new UtilityInputFilterLettersOnly();
			
			AbstractDocument doc = (AbstractDocument) getDocument();
			doc.setDocumentFilter(onlyLettersFilter);
			
			// Initially inactive to allow placeholder
			onlyLettersFilter.setActive(false);
		}
		

		// Sets the font to the specified style type and size
		setFont(new Font("SansSerif", Font.PLAIN, 14));

		// Sets the text color to a dark medium grey
		setForeground(new Color(139, 139, 139));

		// Sets the background color to a lightly dimmed white
		setBackground(new Color(250, 250, 250));

		// Creates an a black outer border with a border witdh of the specified pixel 
		Border outerBorder = new LineBorder(new Color(0, 0, 0), 1, true);

		// Adds the specified amount of inner padding to the input field
		Border innerPadding = new EmptyBorder(10, 10, 10, 10);

		// Applies the outer border and inner padding settings to the input field's border
		setBorder(new CompoundBorder(outerBorder, innerPadding));

		// Insert placeholder text in the input field
		setText(placeholderInput);

		// Make placeholder text appear in italic
		setFont(getFont().deriveFont(Font.ITALIC));

		// Add a focus listener to handle placeholder visibility and input filter behavior
		addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent event)
			{
				// If the field currently shows the placeholder
				if (getText().equals(placeholderInput))
				{
					// Clear the placeholder text
					setText("");

					// Sets the text color to a somewhat black color for user input
					setForeground(new Color(62, 62, 62));

					// Sets the font style to plain for user input
					setFont(getFont().deriveFont(Font.PLAIN));
				}
				
				
				// If the onlyNumbersFilter is not null and the filter type used is onlyNumbers then execute this section
				if (onlyNumbersFilter != null && "onlyNumbers".equals(filterType))
				{
					// Changes the active state of the onlyNumbers filter to true
					onlyNumbersFilter.setActive(true);
				}
				
				// If the onlyLettersFilter is not null and the filter type used is onlyLetters then execute this section
				if (onlyLettersFilter != null && "onlyLetters".equals(filterType))
				{
					// Changes the active state of the onlyLetters filter to true
					onlyLettersFilter.setActive(true);
				}
			}

			
			@Override
			public void focusLost(FocusEvent event)
			{
				// If the field is empty when focus is lost
				if (getText().isEmpty())
				{
					// If the onlyNumbersFilter is not null and the filter type used is onlyNumbers then execute this section
					if(onlyNumbersFilter != null && "onlyNumbers".equals(filterType))
					{
						// Changes the active state of the only numbers filter to false
						onlyNumbersFilter.setActive(false);
					}
					
					// If the onlyLettersFilter is not null and the filter type used is onlyLetters then execute this section
					else if(onlyLettersFilter != null && "onlyLetters".equals(filterType))
					{
						// Changes the active state of the only letters filter to false
						onlyLettersFilter.setActive(false);
					}

					// Resets the placeholder text back to the supplied text
					setText(placeholderInput);

					// Sets the text color to a grey color for the user input
					setForeground(new Color(139, 139, 139));

					// Set the font style back to italic for placeholder
					setFont(getFont().deriveFont(Font.ITALIC));
				}
			}
		});
	}


	/**
	 * Returns the real user input, excluding placeholder text.
	 *
	 * @return The input text or an empty string if only placeholder is present.
	 */
	public String getRealText()
	{
		// Create a variable holding the current field text
		String currentInputFieldText = getText();

		// Return empty if placeholder is visible
		if (currentInputFieldText.equals(placeholderInput))
		{
			return "";
		}
		else
		{
			// Return actual user input
			return currentInputFieldText;
		}
	}
}