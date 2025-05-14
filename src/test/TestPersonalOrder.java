package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.EnumBarType;
import model.MainCourse;
import model.PersonalOrder;
import model.PersonalOrderLine;
import model.SelfServiceBar;
import model.SideDish;

/**
 * this class tests the functionality of personalOrder 
 * 
 * @author Anders Trankjær
 * @version 14-05-2025 - 20:05
 */
public class TestPersonalOrder 
{
	private static PersonalOrder pOrder;
	
	@BeforeAll
	public static void setUp()
	{
		pOrder = new PersonalOrder(null);

	}
	
	
	@Test
	public void testSetAndGetCustomerInfo()
	{
		pOrder.setCustomerAge(25);
		pOrder.setCustomerName("Ben Dover");
		assertEquals(25, pOrder.getCustomerAge());
		assertEquals("Ben Dover", pOrder.getCustomerName());
	}
	
	@Test
    void testGetTotalPersonalOrderLunchAndEveningPrice() {
		SelfServiceBar salad = new SelfServiceBar(EnumBarType.SALADBAR, 50, 75);
		SelfServiceBar softice = new SelfServiceBar(EnumBarType.SOFTICEBAR, 50, 75);
		MainCourse burger = new MainCourse("big fat burger", 100, 150);
		MainCourse ribeye = new MainCourse("biggest rare steak", 150, 205);
		
        PersonalOrderLine orderLine1 = new PersonalOrderLine(salad);
        PersonalOrderLine orderLine2 = new PersonalOrderLine(softice);
        PersonalOrderLine orderLine3 = new PersonalOrderLine(burger);
        PersonalOrderLine orderLine4 = new PersonalOrderLine(ribeye);
        
        pOrder.addPersonalOrderLine(orderLine1);
        pOrder.addPersonalOrderLine(orderLine2);
        pOrder.addPersonalOrderLine(orderLine3);
        pOrder.addPersonalOrderLine(orderLine4);

        assertEquals(350, pOrder.getTotalPersonalOrderLunchPrice());
        
        assertEquals(505, pOrder.getTotalPersonalOrderEveningPrice());
    }
}

