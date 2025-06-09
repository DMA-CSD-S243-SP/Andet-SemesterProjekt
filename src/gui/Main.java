//Packages
package gui;


/**
 * The Main class serves as the entry point for the system.
 * 
 * This class initializes and displays two primary views:
 * - ViewStaffLogin: which is a log-in view for the restaurant's staff
 * - ViewGuestTableInformation which is a view that lets guests enter their table number and start their ordering flow
 * 
 * The main method launches both GUIs, making them available for user interaction
 * and showcasing a demo, in a final product these two graphical user interfaces
 * would be clearly separated.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * @version: 09/06/2025 - 14:04
 */
public class Main
{
    /**
     * The main method that starts the system.
     * 
     * This method creates and displays the staff's login view and the
     * customers' table information information view.
     * 
     * @param args command-line arguments
     */
	public static void main(String[] args)
	{
		// Tries to run the code within the braces
		try
		{
			// Creates a ViewStaffLogin instance and stores it within the viewStaffLogin variable
			ViewStaffLogin viewStaffLogin = new ViewStaffLogin();

			// Changes the visibility of the LoginView window to be visible
			viewStaffLogin.setVisible(true);
			
			// Creates a ViewGuestTableInformation instance and stores it within the viewGuestTableInformation variable
			ViewGuestTableInformation  viewGuestTableInformation = new ViewGuestTableInformation();
			
			// Changes the visibility of the LoginView window to be visible
			viewGuestTableInformation.setVisible(true);
		}
		
		// Catches any type of exception that may occur while running the above code
		catch (Exception exception)
		{
			// Prints the throwable and its related error message to the terminal 
			exception.printStackTrace();
		}
	}
	
}
