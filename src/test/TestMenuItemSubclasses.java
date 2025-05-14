package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import model.AddOnOption;
import model.DipsAndSauces;
import model.Drink;
import model.EnumBarType;
import model.MainCourse;
import model.MultipleChoiceMenu;
import model.PotatoDish;
import model.SelectionOption;
import model.SelfServiceBar;
import model.SideDish;

/**
 * This class tests the functionality of the MenuItem subclasses
 * 
 * 
 * @author Anders Have
 * @version 14/05/2025 - 11:15
 */
public class TestMenuItemSubclasses 
{

	@Test
	public void testOfSelfServiceBar() 
	{
		SelfServiceBar salad = new SelfServiceBar(EnumBarType.SALADBAR, 15, 30);
		SelfServiceBar softice = new SelfServiceBar(EnumBarType.SOFTICEBAR, 30, 60);
		
		//test of the enum Bartype 
		assertNotEquals(salad.getBarType(), softice.getBarType());
		
		// test to see if lunch and evening price function 
		assertNotEquals(salad.getLunchPrice(), salad.getEveningPrice());
	}
	
	
	@Test
	public void testOfSauceAndDips()
	{
		DipsAndSauces garlicDip = new DipsAndSauces(false, 20);
		DipsAndSauces whiskeySauce = new DipsAndSauces(true, 30);
		
		// test of the boolean within the class
		assertTrue(whiskeySauce.isSauce());
		
		assertFalse(garlicDip.isSauce());
		
		// tests of the fixed price functions as it should
		assertNotSame(garlicDip.getFixedPrice(), whiskeySauce.getFixedPrice());
	}
	
	@Test
	public void testOfPotatoDish()
	{
		PotatoDish premiumPotato = new PotatoDish(true, 20);
		PotatoDish regularPotato = new PotatoDish(false, 0);
		
		// test of the boolean within the class
		assertNotEquals(premiumPotato.isPremiumPotatoDish(), regularPotato.isPremiumPotatoDish());
		
		// tests of the fixed price functions as it should
		assertNotEquals(premiumPotato.getFixedPrice(), regularPotato.getFixedPrice());
	}
	
	@Test
	public void testOfSideDish()
	{
		SideDish chickenTender = new SideDish(3, 20);
		SideDish cheeseBall = new SideDish(1, 20);
		
		//all classes that have a fixed class still inherite the getlunchPrice and geteveningPrice method from the superclass.
		assertEquals(chickenTender.getLunchPrice(), chickenTender.getEveningPrice(), "for a product with a fixed price the lunch and evening price methods return the same "
																	  			   + "because the price is fixed");
		
		assertNotEquals(cheeseBall.getQuantityPerServing(), chickenTender.getQuantityPerServing());
	}
	
	@Test
	public void testOfDrink()
	{
		Drink beer = new Drink(true, false, 50);
		Drink softDrink = new Drink(false, true, 30);
		Drink wine = new Drink(true, true, 100);
		
		//tests of the booleans in the class
		assertSame(beer.isAlcoholic(), wine.isAlcoholic());
		assertSame(softDrink.isRefill(), wine.isRefill());
		assertNotSame(softDrink.isAlcoholic(), beer.isAlcoholic());
	}
	
	@Test
	public void testOfMainCourse()
	{
		MainCourse ribeye = new MainCourse("a delicious martian ribeye", 50, 75);
		MainCourse pasta = new MainCourse("charcoal pasta", 30, 45);
		
		//test of the introductiondescription
		assertEquals("a delicious martian ribeye", ribeye.getIntroductionDescription());
		assertNotEquals("a delicious martian ribeye", pasta.getIntroductionDescription());
		
		assertNotEquals(ribeye.getLunchPrice(), ribeye.getEveningPrice(), "because mainCourse doesnt have a fixed price these two values should be different");
	}
	
	@Test
	public void testAddOnOptionForMainCourse()
	{
		MainCourse ribeye = new MainCourse("a delicious martian ribeye", 50, 75);
		AddOnOption option1 = new AddOnOption("add garlicbutter", "with garlic butter", 5);
		AddOnOption option2 = new AddOnOption("285 gram steak", "use larger steak", 15);
		
		ribeye.addAddOnOption(option1);
		ribeye.addAddOnOption(option2);
				
		//test of the AddOnOptionList within the MainCourse class
		assertEquals(2, ribeye.getListOfAddOnOption().size());
		assertTrue(ribeye.getListOfAddOnOption().contains(option1));
		
		ribeye.removeAddOnOption(option1);
		
		//test if the remove method functions as it should
		assertEquals(1, ribeye.getListOfAddOnOption().size());
	}
	
	@Test
	public void testMultipleChoiceMenuForMainCourse()
	{
		MainCourse pastaDish = new MainCourse("charcoal pasta", 30, 45);
		MultipleChoiceMenu cheeseTopping = new MultipleChoiceMenu("which cheese goes on your pasta?");
		
		SelectionOption parmasanCheese = new SelectionOption("parmasan Cheese", "add paramasan", 10);
		SelectionOption goudaCheese = new SelectionOption("gouda Cheese", "add gouda", 10);
		SelectionOption none = new SelectionOption("no cheese", null, 0);
		
		cheeseTopping.addSelectionOption(none);
		cheeseTopping.addSelectionOption(parmasanCheese);
		cheeseTopping.addSelectionOption(goudaCheese);
		
		pastaDish.addMultipleChoiceMenu(cheeseTopping);
		
		// test if the add method functions within MultipleChoiceMenu 
		assertEquals(3, cheeseTopping.getListOfSelectionOptions().size());
		
		// test if the add method functions within MainCourse
		assertTrue(pastaDish.getListOfMultipleChoiceMenu().contains(cheeseTopping));
		
		cheeseTopping.removeSelectionOption(goudaCheese);
		
		//tests if the remove method functions within MultipleChoiceMenu
		assertFalse(cheeseTopping.getListOfSelectionOptions().contains(goudaCheese));
		assertEquals(2, cheeseTopping.getListOfSelectionOptions().size());
	}
}
