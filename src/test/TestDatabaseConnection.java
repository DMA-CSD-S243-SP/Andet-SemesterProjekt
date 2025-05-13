package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DataBaseConnection;

/**
 * This class tests whether a connection to the database can be successfully established.
 * 
 * 
 * @author Anders Have
 * @version 13/05/2025 - 8:50
 */
public class TestDatabaseConnection 
{
    private static DataBaseConnection databaseConnection;

    
    /**
     * Sets up the database connection before running the tests.
     */
    @BeforeAll
    public static void setUp()
    {
    	databaseConnection = DataBaseConnection.getInstance();
    }

    
    /**
     * Closes the database connection after all tests have been executed.
     */
    @AfterAll
    public static void tearDown()
    {
        databaseConnection.disconnect();
    }

    
    /**
     * Tests whether a connection to the database is successfully established and remains open.
     */
    @Test
    public void testConnection()
    {
    	// sets connection to house the database connection
        Connection connection = databaseConnection.getConnection();

        // checks if the connection isnt null
        assertNotNull(connection, "Connection should not be null");

        try 
        {
        	//checks if the database is open
            assertTrue(!connection.isClosed(), "Connection should be open");
        }
        
        catch (SQLException exception) 
        {
            exception.printStackTrace();
            throw new RuntimeException("Error checking the database connection status", exception);
        }
    }
}