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
import model.MenuItem;
import model.SelfServiceBar;
import model.SideDish;

/**
 * TryMe test class used for testing and as a proof of concept
 * 
 * 
 * @author Anders Trankjær
 * @version 15/05/2025 - 11:00
 */
public class TryMe 
{

    public static void main(String[] args) throws SQLException
    {
    	
    	List<Discount> listOfDiscounts = new ArrayList<Discount>();
    		
    	//MainCourse object and its addons
    	MainCourse ribeye = new MainCourse("Big juicy ribeye", 100, 125);
    	AddOnOption garlicButter = new AddOnOption("Garlic butter", "with garlic butter", 10);
    	AddOnOption upgradedRibeye = new AddOnOption("285g steak", "285g steak", 45);
    	
    	ribeye.addAddOnOption(garlicButter);
    	ribeye.addAddOnOption(upgradedRibeye);
    	
    	//sidedishes
    	Drink softdrink = new Drink(false, true, 30);
    	SelfServiceBar salad = new SelfServiceBar(EnumBarType.SALADBAR, 50, 65);
    	SideDish chickenBall = new SideDish(2, 25);
    	
    	//controller for making a personalOrder
    	PersonalOrderController controller = new PersonalOrderController();
    	
    	
    	//Step: 1 
    	try 
    	{
        	controller.enterTableCode("0010001");
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
    	controller.enterNameAndAge("Ben Dover", 45);
    	
    	
    	//Step: 3
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
    	controller.enterMainCourse(ribeye);
    	
    	
    	//Step: 5
    	controller.enterSideOrder(salad);
    	controller.enterSideOrder(softdrink);
    	controller.enterSideOrder(chickenBall);
    	
    	//step: 6
    	try
    	{
    	controller.finishPersonalOrder();
    	}
    	
    	catch (DataAccessException exception)
    	{
    		// This is a generic exception usually thrown when there is an issue accessing the database
    		exception.printStackTrace();
    	}
    }
}
