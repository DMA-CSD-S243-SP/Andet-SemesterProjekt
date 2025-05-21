package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.EnumStatusType;
import model.MainCourse;
import model.PersonalOrderLine;
import model.PotatoDish;
import model.SelectionOption;

/**
 * Unit test on the PersonalOrderLine class
 * 
 * @author Line Bertelsen
 * @version 15.05.25 - 08:56
 */
class TestPersonalOrderline

{
	private PersonalOrderLine personalOrderLinePotatoDish;
	private PersonalOrderLine personalOrderLineMainCourse1;
	private PersonalOrderLine personalOrderLineMainCourse2;
	private PotatoDish potatoDish;
	private MainCourse mainCourse1;
	private MainCourse mainCourse2;
	private SelectionOption selectionOption;
	
	@BeforeEach
	void setUp()
	{
	
		potatoDish = new PotatoDish(true, 20);
		personalOrderLinePotatoDish = new PersonalOrderLine(potatoDish);
		System.out.println("A PersonalOrderLine has been created with a potatoDish item");
	
		mainCourse1 = new MainCourse("Ribeye, kødkvæg fra Uruguary", 239, 299);
		personalOrderLineMainCourse1 = new PersonalOrderLine(mainCourse1);
		System.out.println("A PersonalOrderLine1 has been created with a mainCourse1 item");
		
		mainCourse2 = new MainCourse("Ribs, Bone's Original Sparibs", 129, 189);
		personalOrderLineMainCourse2 = new PersonalOrderLine(mainCourse2);
		System.out.println("A PersonalOrderLine2 has been created with a mainCourse2 item");
		
	}

	
	@Test
	void testGetMenuItemWhereTheResultIsPotatoDish()
	{
		//ARRANGE
		//Already created a personalOrderLine in setUp()
		
		//ACT
		//var = automatic type guessing by Java (menuItem)
		//A MenuItem is abstract an cannot be instantiated on it's own
		var potatoDishResult = personalOrderLinePotatoDish.getMenuItem();
		
		//ASSERT
		assertEquals(potatoDishResult, potatoDish, "The menu item should be potatoDish which is match the one passed to constructor");
	}
	
	
	@Test
	void testGetNotesShouldBePotatoWithGarlicButter()
	{
		//ARRANGE
		personalOrderLinePotatoDish.setNotes("potato m. h-smør");
		
		//ACT
		String potatoDishNotesResult = personalOrderLinePotatoDish.getNotes();
		
		//ASSERT
		assertEquals(potatoDishNotesResult, "potato m. h-smør", "The notes should be \"potato m. h-smør\" witch is match of the setMethod");
	}
	
	
	@Test
	void testGetNotesMethodShouldBeAnEmptyString()
	{
		//ARRANGE
		//Notes Local variable in PersonalOrderLine, is an empty string
		
		//ACT
		String potatoDishNotesResult = personalOrderLinePotatoDish.getNotes();
		
		//ASSERT
		assertEquals(potatoDishNotesResult, "", "The notes should be \"\" witch is match of PersonalorderLine local variable");
	}
	
	
	@Test
	void testAddtionalPriceShouldBe20()
	{
		//ARRANGE
		personalOrderLinePotatoDish.setAdditionalPrice(20);
		
		//ACT
		double potatoDishAdditonalPrice = personalOrderLinePotatoDish.getAdditionalPrice();
		
		//ASSERT
		assertEquals(potatoDishAdditonalPrice, 20.0, "This additional price should be 0, because of the local variable in the personalOrderLine");
		
	}
	
	
	@Test
	void testAddtionalPriceIs0()
	{
		//ARRANGE
		//AdditionalPrice Local variable in PersonalOrderLine, is double 0
		
		//ACT
		double potatoDishAdditonalPrice = personalOrderLinePotatoDish.getAdditionalPrice();
		
		//ASSERT
		assertEquals(potatoDishAdditonalPrice, 0, "This additional price should be 0, because of the local variable in the personalOrderLine");
	}
	
	
	@Test
	void testGetPersonalOrderLineLunchPriceShouldBe239()
	{
		//ARRANGE
		//Main course lunch price is defined in setUp
		
		//ACT
		double lunchPriceMainCourse = personalOrderLineMainCourse1.getPersonalOrderLineLunchPrice();
		
		//ASSERT
		assertEquals(lunchPriceMainCourse, 239, "Lunch price for a ribeye should be 239");
	}
	
	
	@Test
	void testGetPersonalOrderLineLunchPriceShouldBe259()
	{
		//ARRANGE
		personalOrderLineMainCourse1.setAdditionalPrice(20);
		
		//ACT
		double lunchPriceMainCourse = personalOrderLineMainCourse1.getPersonalOrderLineLunchPrice();
		
		//ASSERT
		assertEquals(lunchPriceMainCourse, 259, "Lunch price for a ribeye plus additional price should be 239 + 20 = 259");
	}
	
	
	@Test
	void testGetPersonalOrderLineEvningPriceShouldBe368()
	{
		//ARRANGE
		personalOrderLineMainCourse1.setAdditionalPrice(69);
		
		//ACT
		double lunchPriceMainCourse = personalOrderLineMainCourse1.getPersonalOrderLineEveningPrice();
		
		//ASSERT
		assertEquals(lunchPriceMainCourse, 368, "Lunch price for a ribeye plus additional price should be 299 + 69 = 368");
	}
	
	
	@Test
	void testLunchPriceandEveningPriceShouldNotBeEqual()
	{
		//ARRANGE
		//Main course lunch price is defined in setUp
		
		//ACT
		double lunchPriceMainCourse = personalOrderLineMainCourse1.getPersonalOrderLineLunchPrice();
		double eveningPriceMainCourse = personalOrderLineMainCourse1.getPersonalOrderLineEveningPrice();
		
		//ASSERT
		assertNotEquals(lunchPriceMainCourse, eveningPriceMainCourse, "Lunch price and evening price should be the same");
	}
	
	
	@Test
	void testIsPremiumPotatoDishShouldBeTrue()
	{
		//ARRANGE
		//The potatoDish is set to true in the constructor
		
		//ACT
		boolean potatoDishPremium = personalOrderLinePotatoDish.isPremiumPotatoDish();
		
		//ASSERT
		assertEquals(potatoDishPremium, true, "The potatoDish should be true, cause it's defined as true in the constructor");
	}
	
	
	@Test
	void testIsPremiumPotatoOnMainCourseShouldBeFalse()
	{
		//ARRANGE
		//The mainCourse is instantiated in the constructor

		//ACT
		boolean mainCourseIsPotatoPremium = personalOrderLineMainCourse1.isPremiumPotatoDish();

		//ASSERT
		assertEquals(mainCourseIsPotatoPremium, false, "Its default is false, and even though a mainCourse isnt a potatoDish, the method still works and return false");
	}
	
	
	/**
	 * The addOnOption method is being tested from PersonalOrderLine
	 * and there are two parts to this method, being tested
	 * 
	 * part 1: this.additionalPrice = additionalPrice + addOnOption.getAdditionalPrice();
	 * part 2: this.notes = notes + addOnOption.getKitchenNotes() + " ";
	 */
	@Test
    void testAddOnOption() 
	{
		//ARRANGE
		personalOrderLineMainCourse1.setAdditionalPrice(20);
		selectionOption = new SelectionOption("Medium 400g", "Ribeye 400g", 69);


		//ACT
		personalOrderLineMainCourse1.addSelectionOption(selectionOption);
		double additionalPriceAddOnOption = personalOrderLineMainCourse1.getAdditionalPrice();
		String notesPlusKitchenNotes = personalOrderLineMainCourse1.getNotes();

		//ASSERT
		//part 1: this.additionalPrice = additionalPrice + addOnOption.getAdditionalPrice();
		assertEquals(additionalPriceAddOnOption, 89, "Expected total additionalPrice to be 69 + 20 = 69");

		//part 2: this.notes = notes + addOnOption.getKitchenNotes() + " ";
		assertEquals(notesPlusKitchenNotes,("Ribeye 400g "), "Empty String(notes) + kitchenNotes + String with space");
	}	
	
	
	/**
	 * The addSelectionOption method is being tested from PersonalOrderLine
	 * and there are two parts to this method, being tested
	 * 
	 * part 1: this.additionalPrice = additionalPrice + selectionOption.getAdditionalPrice();
	 * part 2: this.notes = notes + selectionOption.getKitchenNotes() + " ";
	 */
	@Test
	void testAddSelectionOption()
	{
		//ARRANGE
		personalOrderLineMainCourse2.setAdditionalPrice(20);
		selectionOption = new SelectionOption("Medium 400g", "Spareribs 400g", 49);
		
		
		//ACT
		personalOrderLineMainCourse2.addSelectionOption(selectionOption);
		double additionalPriceSelectionOption = personalOrderLineMainCourse2.getAdditionalPrice();
		String notesPlusKitchenNotes = personalOrderLineMainCourse2.getNotes();
		
		//ASSERT
		//part 1: this.additionalPrice = additionalPrice + selectionOption.getAdditionalPrice();
		assertEquals(additionalPriceSelectionOption, 69, "Expected total additionalPrice to be 20 + 49 = 69");
		
		//part 2: this.notes = notes + selectionOption.getKitchenNotes() + " ";
		assertEquals(notesPlusKitchenNotes,("Spareribs 400g "), "Empty String(notes) + kitchenNotes + String with space");
	}
	
	
	@Test
    void testStatusDefaultAndSet() 
	{
        // Assert default
        assertEquals(EnumStatusType.WAITINGTOBEPREPARED, personalOrderLineMainCourse1.getStatus());

        // Act
        personalOrderLineMainCourse1.setStatus(EnumStatusType.READYTOBESERVED);

        // Assert new status
        assertEquals(EnumStatusType.READYTOBESERVED, personalOrderLineMainCourse1.getStatus());
    }
	
}
