//Packages
package gui;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 12:32
 */	
public class Main
{
	public static void main(String[] args)
	{
		// Tries to run the code within the braces
		try
		{
			// Creates a ViewGuestTableInformation instance and stores it within the viewGuestTableInformation variable
			ViewGuestTableInformation  viewGuestTableInformation = new ViewGuestTableInformation();
			
			
			
			// Changes the visibility of the LoginView window to be visible
			viewGuestTableInformation.setVisible(true);
			
			ViewStaffLogin viewStaffLogin = new ViewStaffLogin();

			// Changes the visibility of the LoginView window to be visible
			viewStaffLogin.setVisible(true);
		}
		
		// Catches any type of exception that may occur while running the above code
		catch (Exception exception)
		{
			// Prints the throwable and its related error message to the terminal 
			exception.printStackTrace();
		}
	}
	
}
