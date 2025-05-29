package test; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.TableOrderDB;
import model.TableOrder;
/**
 * this class tests the functionality of personalOrder 
 * 
 * @author Line
 * @version 29/05/25 - 15.12
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
}


