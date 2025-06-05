package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Creates a customized version of Swing's JTextField class, to make it
 * an individual component and increase the ease-of-use multiple places
 * in the system.
 * This GUI component is stylized in the theme colors that Bone's uses
 * to match their brand identity.
 * 
 * The component also allow for easily defining filtering of user inputs
 * to easily limit the inputs to be either letters only or numbers only.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 05/06/2025 - 15:06
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
	 * @param placeholderText The placeholder text to display when the input field is empty.
	 * @param filterType The type of input restriction (e.g., "onlyNumbers"), or null for no filter.
	 */
	public ComponentGuestInputField(String placeholderText, String filterType)
	{
		super();
		this.placeholderInput = placeholderText;

		// Apply numeric input filter only once, but deactivate it initially
		if ("onlyNumbers".equals(filterType))
		{
			// Creates a new instance of the UtilityInputFilterNumbersOnly class which is a custom subclass of Java Swing's DocumentFilter class
			onlyNumbersFilter = new UtilityInputFilterNumbersOnly();
			
			// Creates a new instance of AbstractDocument and stores it within the abstractDocument variable
			// the reason why casting to an AbstractDocument is being used here is because the getDocument() do not
			// have built-in support for the setDocumentFilter method
			AbstractDocument abstractDocument = (AbstractDocument) getDocument();
			
			// Applies the previously specified onlyLettersFilter as a document filter which makes it so that any
			// input in form of insertion or replacing is first being filteret and determined to be a valid input
			// before it is allowed to be added in to the input text field, if it is invalid it is never added to the input field
			abstractDocument.setDocumentFilter(onlyNumbersFilter);
			
			// Sets the active state to initially be inactive to allow placeholder text in the input field without being affected by the regex pattern
			onlyNumbersFilter.setActive(false);
		}
		
		else if ("onlyLetters".equals(filterType))
		{
			// Creates a new instance of the UtilityInputFilterLettersOnly class which is a custom subclass of Java Swing's DocumentFilter class
			onlyLettersFilter = new UtilityInputFilterLettersOnly();
			
			// Creates a new instance of AbstractDocument and stores it within the abstractDocument variable
			// the reason why casting to an AbstractDocument is being used here is because the getDocument() do not
			// have built-in support for the setDocumentFilter method
			AbstractDocument abstractDocument = (AbstractDocument) getDocument();
			
			// Applies the previously specified onlyLettersFilter as a document filter which makes it so that any
			// input in form of insertion or replacing is first being filteret and determined to be a valid input
			// before it is allowed to be added in to the input text field, if it is invalid it is never added to the input field
			abstractDocument.setDocumentFilter(onlyLettersFilter);
			
			// Sets the active state to initially be inactive to allow placeholder text in the input field without being affected by the regex pattern
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