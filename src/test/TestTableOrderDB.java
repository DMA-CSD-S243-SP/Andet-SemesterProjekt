package test; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.TableOrderDB;
import model.TableOrder;
/**
 * this class tests the functionality of personalOrder 
 * 
 * @author Line
 * @version 04/06/25 - 11.40
 */
public class TestTableOrderDB 
{
    private TableOrderDB tableOrderDB;

    @BeforeEach
    public void setup() throws Exception 
    {
        tableOrderDB = new TableOrderDB();
    }

    @Test
    public void testUpdateTableOrder() throws Exception 
    {
    	//ARRANGE
    	// Step 0: Change the Test TableOrder such that it shows whether an update actually occurs.
    	TableOrder tableOrderRefresh = new TableOrder(100009, LocalDateTime.now(), false, "CASH", 0, 150, false, true, 20); 
        new TableOrderDB().updateTableOrder(tableOrderRefresh);
    	
    	// Step 1: Create a TableOrder object with known tableId that already exists
        TableOrder tableOrder = new TableOrder(100009, LocalDateTime.now(), true, "CARD", 0, 200, true, false, 15); 

        //ASSERT
        // Step 3: Call the method
        tableOrderDB.updateTableOrder(tableOrder);

        // Step 4: Retrieve updated object
        TableOrder updated = tableOrderDB.findTableOrderByTableOrderId(100009);

        //ASSERT
        // Step 5: Assert changes
        assertNotNull(updated);
        assertEquals("CARD", updated.getPaymentType());
        assertTrue(updated.isTableOrderClosed());
        assertEquals(200, updated.getTotalAmountPaid());
        assertTrue(updated.isSentToKitchen());
        assertFalse(updated.isRequestingService());
        assertEquals(15, updated.getOrderPreparationTime());
    }
    
    
    @Test
    public void testFindAllVisibleToKitchenTableOrdersFound() throws Exception
    {
    	//ARRANGE
    	TableOrder tableOrder2 = new TableOrder(100000, LocalDateTime.now(), 
    			false, "CARD", 200, 0, true, false, 15);
    	
    	tableOrderDB.updateTableOrder(tableOrder2);
    	
    	//ACT
    	List<TableOrder> listOfVisibleTableOrdersSentToKitchen = 
    			tableOrderDB.findAllVisibleToKitchenTableOrders();
    	
    	//ASSERT
    	// Looks for the specific tableOrderId 10000
    	boolean isFound = false;
        for (TableOrder tableOrder : listOfVisibleTableOrdersSentToKitchen) 
        {
            // Makes sure that every order meets the criteria
            assertTrue(tableOrder.isSentToKitchen());
            assertFalse(tableOrder.isTableOrderClosed());

            // Look for our specific test object
            if (tableOrder.getTableOrderId() == 100000) 
            {
            	isFound = true;
            }
        }

        // Ensure our test object is included in the result
        assertTrue(isFound);
    }
    
    
    @Test
    public void testFindAllVisibleToKitchenTableOrdersNotFound() throws Exception
    {
    	//ARRANGE
    	TableOrder tableOrder3 = new TableOrder(100009, LocalDateTime.now(), false, "CARD", 200, 0, false, false, 15); 
    	tableOrderDB.updateTableOrder(tableOrder3);
    	
    	//ACT
    	List<TableOrder> listOfVisibleTableOrdersSentToKitchen = tableOrderDB.findAllVisibleToKitchenTableOrders();
    	
    	//ASSERT    	
    	// Looks for the specific tableOrderId 10009
    	boolean isFound = false;
    	
    	
        for (TableOrder tableOrder : listOfVisibleTableOrdersSentToKitchen) 
        {
            // Make sure every order meets the criteria
            assertTrue(tableOrder.isSentToKitchen());
            assertFalse(tableOrder.isTableOrderClosed());

            // Look for our specific test object
            if (tableOrder.getTableOrderId() == 100009) 
            {
            	isFound = true;
            }
        }

        // Ensure our test object is included in the result
        assertFalse(isFound);
    }
}
