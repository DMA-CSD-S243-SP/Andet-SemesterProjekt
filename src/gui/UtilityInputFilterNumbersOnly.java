package gui;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:19
 */	
public class UtilityInputFilterNumbersOnly extends DocumentFilter
{
	// Disables the filter initially
	private boolean active = false;

	// The regex pattern that we use, which only allows matches for the following:
	// small and capital letters from a to z and also including æ, ø and å
	private static final Pattern NUMBERS_ONLY_PATTERN = Pattern.compile("[0-9]+");

	
	public void setActive(boolean active)
	{
		// Enables or disables the filtering
		this.active = active;
	}

	
	@Override
	public void insertString(FilterBypass filterByPass, int offset, String textToFilter, AttributeSet setOfAttributes) throws BadLocationException
	{
		// If the filter is inactive, or if the the text matches the regex pattern
		// pattern - Only include numbers from 0 to 9 and no spaces
		if (!active || textToFilter != null && NUMBERS_ONLY_PATTERN.matcher(textToFilter).matches())
		{
			// Replaces the supplied input
			super.insertString(filterByPass, offset, textToFilter, setOfAttributes);
		}
	}

	
	@Override
	public void replace(FilterBypass filterByPass, int offset, int length, String textToFilter, AttributeSet setOfAttributes) throws BadLocationException
	{
		// If the filter is inactive, or if the the text matches the regex pattern
		// pattern - Only include numbers from 0 to 9 and no spaces
		if (!active || textToFilter != null && NUMBERS_ONLY_PATTERN.matcher(textToFilter).matches())
		{
			// Replaces the supplied input
			super.replace(filterByPass, offset, length, textToFilter, setOfAttributes);
		}
	}
}