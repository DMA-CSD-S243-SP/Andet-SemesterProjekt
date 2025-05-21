package gui;
import java.sql.SQLException;
import java.util.List;

import application.PersonalOrderController;
import database.DataAccessException;
import model.Discount;
import model.Table;

public class UtilityGuestInformation
{
	private static UtilityGuestInformation instance;
	private PersonalOrderController personalOrderController;
	private Table table;
	private List<Discount> listOfDiscounts;

	private UtilityGuestInformation()
	{
		personalOrderController = new PersonalOrderController();
	}

	public static UtilityGuestInformation getInstance()
	{
		if (instance == null)
		{
			instance = new UtilityGuestInformation();
		}
		return instance;
	}
	
	public PersonalOrderController getPersonalOrderController()
	{
		return personalOrderController;
	}
	
	public Table enterTableCode(String tableNumber, String restaurantCode) throws DataAccessException, SQLException
	{
		table = personalOrderController.enterTableCode(tableNumber, restaurantCode);
		return table;
	}
	
	public List<Discount> enterNameAndAge(String customerName, int customerAge)
	{
		listOfDiscounts = personalOrderController.enterNameAndAge(customerName, customerAge);
		return listOfDiscounts;
	}
}
