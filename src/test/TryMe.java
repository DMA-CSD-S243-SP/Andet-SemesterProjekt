package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.PersonalOrderController;
import database.DataAccessException;
import model.AddOnOption;
import model.Discount;
import model.Drink;
import model.EnumBarType;
import model.MainCourse;
import model.PotatoDish;
import model.SelectionOption;
import model.SelfServiceBar;
import model.SideDish;

/**
 * TryMe test class used for testing and as a proof of concept
 * 
 * 
 * @author Anders Trankjær & Line
 * @version 05/06/2025 - 14:00
 */
public class TryMe 
{

    public static void main(String[] args) throws SQLException
    {
    	
    	//ARRANGE
    	List<Discount> listOfDiscounts = new ArrayList<Discount>();
    		
    	//MainCourse object and its addons
    	MainCourse ribeye = new MainCourse("Big juicy ribeye", 100, 125, 11, 25, "285g ribeye", "en stor ribeye fra Argentinas bedste", false);
    	AddOnOption garlicButter = new AddOnOption("Garlic butter", "with garlic butter", 10);
    	AddOnOption upgradedRibeye = new AddOnOption("285g steak", "285g steak", 45);
    	
    	ribeye.addAddOnOption(garlicButter);
    	ribeye.addAddOnOption(upgradedRibeye);
    	
    	// Potato
    	PotatoDish potatoDish = new PotatoDish(true, 0, 1, 69, "Special Fries", "steak pommefritter", true);
    	
    	//sidedishes
    	Drink softdrink = new Drink(false, true, 30, 5, 0, "Tag-Selv sodavand", null, false);
    	SelfServiceBar salad = new SelfServiceBar(EnumBarType.SALADBAR, 49, 55, 3, 0, "Tag-Selv saladbar", null, false);
    	SideDish chickenBall = new SideDish(2, 25, 7, 5, "kylling formet som en kulge", "lavet på hele kyllinger", true);
    	
    	//controller for making a personalOrder
    	PersonalOrderController controller = new PersonalOrderController();
    	
    	//ACT
    	//Step: 1 
    	System.out.println("Step 1");
    	try 
    	{
        	controller.enterTableCode("0001", "001");
    	} 
    	
    	catch (DataAccessException exception)
    	{
    		// This is a generic exception usually thrown when there is an issue accessing the database
    		exception.printStackTrace();
    	}
    	
    	catch (SQLException exception)
    	{
    		// This is typically thrown when an SQL operation fails for various reasons
    		exception.printStackTrace();
    	}
    	
    	
    	//Step: 2
    	System.out.println("Step 2");
    	controller.enterNameAndAge("Ben Dover", 45);
    	
    	
    	//Step: 3
    	System.out.println("Step 3");
    	try 
    	{
    	controller.enterDiscounts(listOfDiscounts);
    	}
    	
    	catch (DataAccessException exception)
    	{
    		// This is a generic exception usually thrown when there is an issue accessing the database
    		exception.printStackTrace();
    	}
    	
    	catch (SQLException exception)
    	{
    		// This is typically thrown when an SQL operation fails for various reasons
    		exception.printStackTrace();
    	}
    	
    	//Step: 4
    	System.out.println("Step 4");
    	controller.enterMainCourse(ribeye);
    	
    	//Step 5
    	System.out.println("Step 5");
    	List<AddOnOption> listOfAddOnOptions = new ArrayList<AddOnOption>() ;
    	listOfAddOnOptions.add(garlicButter);
    	listOfAddOnOptions.add(upgradedRibeye);
    	List<SelectionOption>listOfSelectionOptions = new ArrayList<>();
    	controller.enterMainCourseOptions(potatoDish, listOfAddOnOptions, listOfSelectionOptions);
    	
    	//Step: 6
    	System.out.println("Step 6");
    	controller.enterSideOrder(salad);
    	controller.enterSideOrder(softdrink);
    	controller.enterSideOrder(chickenBall);
    	
    	//step: 7
    	System.out.println("Step 7");
    	try
    	{
    	controller.finishPersonalOrder();
    	}
    	
    	catch (DataAccessException exception)
    	{
    		// This is a generic exception usually thrown when there is an issue accessing the database
    		exception.printStackTrace();
    	}
    	
 
    	//ASSERT
    	System.out.println("TEST AFSLUTTET – tjek i database om ordren blev oprettet korrekt.");
    }
}
