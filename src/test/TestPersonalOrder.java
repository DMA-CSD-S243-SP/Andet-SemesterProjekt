package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DipsAndSauces;
import model.Drink;
import model.EnumBarType;
import model.MainCourse;
import model.PersonalOrder;
import model.PersonalOrderLine;
import model.PotatoDish;
import model.SelfServiceBar;
import model.SideDish;

/**
 * this class tests the functionality of personalOrder 
 * 
 * @author Anders Trankjær & Line Bertelsen
 * @version 29-05-2025 - 15.35
 */
public class TestPersonalOrder 
{
	//Customers personalOrder in test number they belong to
	private static PersonalOrder pOrder;
	
	private static PersonalOrder personalOrderTest1;
	private static PersonalOrder personalOrderTest2;
	private static PersonalOrder personalOrderTest3;
	private static PersonalOrder personalOrderTest4;
	private static PersonalOrder personalOrderTest5;
	
	//MainCourse with small, medium and larges sparibs
	private static MainCourse mainCourseSmallSparerib;
	private static MainCourse mainCourseMediumSparerib;
	private static MainCourse mainCourseLargeSparerib;
	
	//PotatoDishes
	private static PotatoDish bakedPotatoDishWithGarliceButter;
	private static PotatoDish bakedPotatoDishWithButter;
	private static PotatoDish specialFriesPotatoDish;
	private static PotatoDish friesPotatoDish;
	private static PotatoDish sweetPotatoDish;
	
	//SideOrder
	private static DipsAndSauces spareribsSauce;
	private static SelfServiceBar salatBar;
	private static SideDish chiliCheeseTops;
	private static DipsAndSauces aioliDip;
	private static SelfServiceBar softiceBar;
	
	//Drinks
	private static Drink smallGlassSoda;
	private static Drink royalClassic;
	private static Drink sodaAllYouCanDrink;
	private static Drink glassRosevin;
	private static Drink cappucino;
	
	@BeforeAll
	public static void setUp()
	{

		// Create food items used for test 1-5
		mainCourseSmallSparerib = new MainCourse("'Spareribs', 'Small', 'Bone’s Original Spareribs – gæsternes absolutte favorit.' ", 129.0, 189.0, 11, 900, 
				"Spareribs lille", null, true);
		mainCourseMediumSparerib = new MainCourse("'Spareribs' 'Medium', 'Bone’s Original Spareribs – gæsternes absolutte favorit.' ", 178.0, 299.0, 11, 900, 
				"Spareribs medium", null, true);
		mainCourseLargeSparerib = new MainCourse("'Spareribs' 'Large', 'Bone’s Original Spareribs – gæsternes absolutte favorit.'", 198.0, 329.0, 11, 900, 
				"Spareribs stor", null, true);
		
		//PotatoDish
		bakedPotatoDishWithGarliceButter = new PotatoDish(false, 0, 22, 50, "bagt kartofle med hvidløgssmør", null, true); 
		bakedPotatoDishWithButter = new PotatoDish (false, 0, 23, 50, "bagt kartofle med smør", null, true);
		specialFriesPotatoDish = new PotatoDish(true, 0, 2, 69, "Special fries", null, true);
		friesPotatoDish = new PotatoDish(false, 0, 1, 0, "pommes frittes", null, true); 
		sweetPotatoDish = new PotatoDish(true, 0, 24, 45, "sweet potato pommes frittes", null, true);
		
		//SideOrder
		spareribsSauce = new DipsAndSauces(true, 20.0, 25, 0, "Bone's Sparerib sauce", null, true);
		salatBar = new SelfServiceBar(EnumBarType.SALADBAR, 49, 69, 4, 0, "tag-Selv saladbar", null, false);
		chiliCheeseTops = new SideDish(1, 35.0, 7, 2, "chill cheese tops", "hjemmelavet chill cheese tops", true);
		aioliDip = new DipsAndSauces(true, 15, 10, 0, "hvidløgs aioli", null, false);
		softiceBar = new SelfServiceBar(EnumBarType.SOFTICEBAR, 49, 69, 3, 0, "tag-Selv softice", null, false);
		
		//Drinks
		smallGlassSoda = new Drink(false, false, 49.0, 25, 0, "lille glas sodavand", null, false);
		royalClassic = new Drink(true, false, 69.0, 26, 0, "royal Classic", null, false);
		sodaAllYouCanDrink = new Drink(false, true, 69.0, 5, 0, "tag-Selv sodavand", null, false);
		glassRosevin = new Drink(true, false, 69.0, 27, 0, "glas rødvin", null, false);
		cappucino = new Drink(false, false, 39.0, 28, 0, "cappucino lavet i hus", null, true);
		

	}
	
	// Moved to @BeforeEach to ensure each test starts with a fresh PersonalOrder instance
	// This avoids shared state between tests and improves test isolation
	@BeforeEach
	void initEach()
	{
		//PersonalOrder
		pOrder = new PersonalOrder(null);
		
		personalOrderTest1 = new PersonalOrder(null);
		personalOrderTest2 = new PersonalOrder(null);
		personalOrderTest3 = new PersonalOrder(null);
		personalOrderTest4 = new PersonalOrder(null);
		personalOrderTest5 = new PersonalOrder(null);
	}
	
	@Test
	public void testSetAndGetCustomerInfo()
	{
		//ACT
		//p0rder is defined in setUp()
		
		//ARRANGE
		pOrder.setCustomerAge(25);
		pOrder.setCustomerName("Ben Dover");
		
		//ASSERT
		assertEquals(25, pOrder.getCustomerAge());
		assertEquals("Ben Dover", pOrder.getCustomerName());
	}
	
	@Test
    void testGetTotalPersonalOrderLunchAndEveningPrice() {
		//ACT
		SelfServiceBar salad = new SelfServiceBar(EnumBarType.SALADBAR, 50, 75, 4, 0, "tag-Selv saladbar", null, false);
		SelfServiceBar softice = new SelfServiceBar(EnumBarType.SOFTICEBAR, 50, 75, 3, 0, "tag-Selv softice", null, false);
		MainCourse burger = new MainCourse("big fat burger", 100, 150, 28, 0, "en giga burger 2kg kød", null, true);
		MainCourse ribeye = new MainCourse("biggest rare steak", 150, 205, 29, 0, "en næste rå 2 kg ribeye", null, true);
		
        PersonalOrderLine orderLine1 = new PersonalOrderLine(salad);
        PersonalOrderLine orderLine2 = new PersonalOrderLine(softice);
        PersonalOrderLine orderLine3 = new PersonalOrderLine(burger);
        PersonalOrderLine orderLine4 = new PersonalOrderLine(ribeye);
        
        //ARRANGE
        pOrder.addPersonalOrderLine(orderLine1);
        pOrder.addPersonalOrderLine(orderLine2);
        pOrder.addPersonalOrderLine(orderLine3);
        pOrder.addPersonalOrderLine(orderLine4);

        //ASSERT
        assertEquals(350, pOrder.getTotalPersonalOrderLunchPrice());
        
        assertEquals(505, pOrder.getTotalPersonalOrderEveningPrice());
    }
	
	/**
	 * Test case no. 1
	 * 
	 * Verifies that the method getTotalPersonalOrderLunchPrice() correctly calculates the total price 
	 * of a lunch order composed of multiple menu items.
	 * Expected total: 198.0
	 */
	@Test
	void getTotalPersonalOrderLunchPriceWhereTotalPriceIs198()
	{
		//ACT
		// All test items (MainCourse, PotatoDish, etc.) inherit from our MenuItem class and are defined in setUp()
		
		//PersonalOrderLine 
		// Create PersonalOrderLine objects representing individual order items
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourseSmallSparerib);
		PersonalOrderLine potatoDishLine = new PersonalOrderLine(bakedPotatoDishWithGarliceButter);
		PersonalOrderLine dipAndSauceLine = new PersonalOrderLine(spareribsSauce);
		PersonalOrderLine drinkLine = new PersonalOrderLine(smallGlassSoda);

		//ARRANGE
		// Add all items to the personal order
		personalOrderTest1.addPersonalOrderLine(mainCourseLine);
		personalOrderTest1.addPersonalOrderLine(potatoDishLine);
		personalOrderTest1.addPersonalOrderLine(dipAndSauceLine);
		personalOrderTest1.addPersonalOrderLine(drinkLine);
		
		//ASSERT
		// Verify the calculated total lunch price matches the expected value (129 + 0 + 20 + 49 = 198)
		assertEquals(personalOrderTest1.getTotalPersonalOrderLunchPrice(), 198.0, "The total price is 198 of this customer personal order");
	}
	
	/**
	 * Test case no. 2
	 * 
	 * Verifies that the method getTotalPersonalOrderLunchPrice() correctly calculates the total price 
	 * of a lunch order composed of multiple menu items.
	 * Expected total: 247.0
	 */
	@Test
	void getTotalPersonalOrderLunchPriceWhereTotalPriceIs247()
	{
		//ACT
		// All test items (MainCourse, PotatoDish, etc.) inherit from our MenuItem class and are defined in setUp()
		
		//PersonalOrderLine 
		// Create PersonalOrderLine objects representing individual order items
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourseSmallSparerib);
		PersonalOrderLine potatoDishLine = new PersonalOrderLine(bakedPotatoDishWithButter);
		PersonalOrderLine selfServeBarLine = new PersonalOrderLine(salatBar);
		PersonalOrderLine drinkLine = new PersonalOrderLine(royalClassic);

		//ARRANGE
		// Add all items to the personal order
		personalOrderTest2.addPersonalOrderLine(mainCourseLine);
		personalOrderTest2.addPersonalOrderLine(potatoDishLine);
		personalOrderTest2.addPersonalOrderLine(selfServeBarLine);
		personalOrderTest2.addPersonalOrderLine(drinkLine);
		
		//ASSERT
		// Verify the calculated total lunch price matches the expected value (129 + 0 + 49 + 69 = 247)
		assertEquals(personalOrderTest2.getTotalPersonalOrderLunchPrice(), 247, "The total price is 247 of this customer personal order");
	}
	
	/**
	 * test case no. 3
	 *
	 * Verifies that the method getTotalPersonalOrderLunchPrice() correctly calculates the total price 
	 * of a lunch order composed of multiple menu items.
	 * Expected total: 253.0
	 */
	@Test
	void getTotalPersonalOrderLunchPriceWhereTotalPriceIs253()
	{
		//ACT
		// All test items (MainCourse, PotatoDish, etc.) inherit from our MenuItem class and are defined in setUp()
		
		//PersonalOrderLine 
		// Create PersonalOrderLine objects representing individual order items
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourseSmallSparerib);
		PersonalOrderLine potatoDishLine = new PersonalOrderLine(specialFriesPotatoDish);
		PersonalOrderLine sideDishLine = new PersonalOrderLine(chiliCheeseTops);
		PersonalOrderLine drinkLine = new PersonalOrderLine(sodaAllYouCanDrink);

		//ARRANGE
		// Add all items to the personal order
		personalOrderTest3.addPersonalOrderLine(mainCourseLine);
		personalOrderTest3.addPersonalOrderLine(potatoDishLine);
		personalOrderTest3.addPersonalOrderLine(sideDishLine);
		personalOrderTest3.addPersonalOrderLine(drinkLine);
		
		//ASSERT
		// Verify the calculated total lunch price matches the expected value (129 + 20 + 35 + 69 = 253)
		// This added potatoDish isPremium and the getTotalPersonaOrderLunch adds automatically 20+
		assertEquals(personalOrderTest3.getTotalPersonalOrderLunchPrice(), 253, "The total price is 253 of this customer personal order");
	}
	
	/**
	 * test case no. 4
	 * 
	 * Verifies that the method getTotalPersonalOrderLunchPrice() correctly calculates the total price 
	 * of a lunch order composed of multiple menu items.
	 * Expected total: 262.0
	 */
	@Test
	void getTotalPersonalOrderLunchPriceWhereTotalPriceIs262()
	{
		//ACT
		// All test items (MainCourse, PotatoDish, etc.) inherit from our MenuItem class and are defined in setUp()
		
		//PersonalOrderLine 
		// Create PersonalOrderLine objects representing individual order items
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourseMediumSparerib);
		PersonalOrderLine potatoDishLine = new PersonalOrderLine(friesPotatoDish);
		PersonalOrderLine dipAndSaucesBarLine = new PersonalOrderLine(aioliDip);
		PersonalOrderLine drinkLine = new PersonalOrderLine(glassRosevin);

		//ARRANGE
		// Add all items to the personal order
		personalOrderTest4.addPersonalOrderLine(mainCourseLine);
		personalOrderTest4.addPersonalOrderLine(potatoDishLine);
		personalOrderTest4.addPersonalOrderLine(dipAndSaucesBarLine);
		personalOrderTest4.addPersonalOrderLine(drinkLine);
		
		//ASSERT
		// Verify the calculated total lunch price matches the expected value (178 + 0 + 15 + 69 = 262)
		assertEquals(personalOrderTest4.getTotalPersonalOrderLunchPrice(), 262, "The total price is 262 of this customer personal order");
	}
	
	/**
	 * Test case no. 5
	 * 
	 * Verifies that the method getTotalPersonalOrderLunchPrice() correctly calculates the total price 
	 * of a lunch order composed of multiple menu items.
	 * Expected total: 306.0
	 */
	@Test
	void getTotalPersonalOrderLunchPriceWhereTotalPriceIs306()
	{
		//ACT
		// All test items (MainCourse, PotatoDish, etc.) inherit from our MenuItem class and are defined in setUp()
		
		//PersonalOrderLine 
		// Create PersonalOrderLine objects representing individual order items
		PersonalOrderLine mainCourseLine = new PersonalOrderLine(mainCourseLargeSparerib);
		PersonalOrderLine potatoDishLine = new PersonalOrderLine(sweetPotatoDish);
		PersonalOrderLine selfServeBarLine = new PersonalOrderLine(softiceBar);
		PersonalOrderLine drinkLine = new PersonalOrderLine(cappucino);

		//ARRANGE
		// Add all items to the personal order
		personalOrderTest5.addPersonalOrderLine(mainCourseLine);
		personalOrderTest5.addPersonalOrderLine(potatoDishLine);
		personalOrderTest5.addPersonalOrderLine(selfServeBarLine);
		personalOrderTest5.addPersonalOrderLine(drinkLine);
		
		//ASSERT
		// Verify the calculated total lunch price matches the expected value (198 + 20 + 49 + 39 = 306)
		assertEquals(personalOrderTest5.getTotalPersonalOrderLunchPrice(), 306, "The total price is 306 of this customer personal order");
	}

}

