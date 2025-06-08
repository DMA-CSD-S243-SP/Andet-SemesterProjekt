package gui;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


/**
 * Serves as a customized input filter that can be applied to GUI components
 * that are either JTextField or extensions of the JTextField class.
 * 
 * This class allows for an input filter to be applied to the JTextField input
 * fields, that makes it so the registered inputs will only be added to the
 * input field if the inputted contents are either letters ranging from
 * A to Z including ÆØÅ both capital and non-capital letters are accepted.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 05/06/2025 - 15:11
 */	
public class UtilityInputFilterLettersOnly extends DocumentFilter
{
	// Disables the filter initially
	private boolean isFilterActive = false;
	
	// The regex pattern that we use, which only allows matches for the following:
	// small and capital letters from a to z and also including æ, ø and å and
	// as the + indicates they are allowed to be repeated multiple times
	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("[a-zA-ZÆØÅæøå]+");

	// Determines whether or not the filtering is currently active to deactivate it
	// before the user types to allow for the placeholder text to be there
	public void setActive(boolean isFilterActive)
	{
		// Enables or disables the filtering
		this.isFilterActive = isFilterActive;
	}

	
	// Overrides the methods with the same name in the DocumentFilter super class 
	// allowing for adding additional validation and catching the user's input 
	// upon inserting information and preventing certain things from being added  
	@Override
	public void insertString(FilterBypass filterByPass, int offset, String textToFilter, AttributeSet setOfAttributes) throws BadLocationException
	{
		// If the filter is inactive, or if the the text matches the regex pattern
		// Pattern - Only include small and capital letters from a to z, no spaces, and æ,ø and å
		if(!isFilterActive || textToFilter != null && LETTERS_ONLY_PATTERN.matcher(textToFilter).matches())
		{
			// Replaces the supplied input
			super.insertString(filterByPass, offset, textToFilter, setOfAttributes);
		}
	}

	
	// Overrides the methods with the same name in the DocumentFilter super class 
	// allowing for adding additional validation and catching the user's input 
	// upon replacing information and preventing certain things from being added
	@Override
	public void replace(FilterBypass filterByPass, int offset, int length, String textToFilter, AttributeSet setOfAttributes) throws BadLocationException
	{
		// If the filter is inactive, or if the the text matches the regex pattern
		// Pattern - Only include small and capital letters from a to z, no spaces, and æ,ø and å
		if (!isFilterActive || textToFilter != null && LETTERS_ONLY_PATTERN.matcher(textToFilter).matches())
		{
			// Replaces the supplied input
			super.replace(filterByPass, offset, length, textToFilter, setOfAttributes);
		}
	}
}