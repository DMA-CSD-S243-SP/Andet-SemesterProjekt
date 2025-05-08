// Packages
package database;


/**
 * Isolated code, being used to handle database errors. 
 * This class inherit from exception. 
 * 
 * Checked exception:	try-catch or declare throws in the method signature
 * Throw: 				If a method does not want to handle the error, 
 * 						it can send it can declare throw into its method signature
 * 
 * Method signature: 	Is the line where the method is defined
 * Isolated: 			because its being used many time through out the code and to avoid repetitions. 
 * Exception:			is a part of java's standard library and is found in java.lang
 * 
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 08-05-2025 - 12:21
 */
public class DataAccessException extends Exception 
{
	/**
	 * SerialVersionUID 	- unique identifier for a class, that implements Serialization through java 
	 * No SerialVersionUID 	- If missing, the java would make a unique id	
	 * static				- This make the id belong to the class and not the objects
	 * final 				- Prevents the id changes
	 * long 				- A datatype which can be super long. 
	 * 1L					- L stands for long, if you only wrote 1, java would believe it to be an int.
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * The constructor takes two exceptions:
	 *  
	 * 		1. errors message (message) 
	 * 		2. exception (throwable exception)
	 * 
	 * This is send to super (which means exception-class)
	 * This is very helpful when a database errors occurs.
	 */
	public DataAccessException(String message, Throwable exception) 
	{
		super(message, exception);
	}
}
