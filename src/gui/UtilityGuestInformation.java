package gui;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.PersonalOrderController;
import database.DataAccessException;
import model.AddOnOption;
import model.Discount;
import model.MainCourse;
import model.MenuCard;
import model.MenuItem;
import model.PotatoDish;
import model.SelectionOption;
import model.Table;
import model.TableOrder;

public class UtilityGuestInformation
{
	private static UtilityGuestInformation instance;
	private PersonalOrderController personalOrderController;
	private Table table;
	private TableOrder tableOrder;
	private List<Discount> listOfDiscounts;
	private List<MenuCard> listOfMenuCards;
	private MainCourse mainCourse;

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
		tableOrder = table.getCurrentTableOrder();
		return table;
	}
	
	public List<Discount> enterNameAndAge(String customerName, int customerAge)
	{
		listOfDiscounts = personalOrderController.enterNameAndAge(customerName, customerAge);
		return listOfDiscounts;
	}
	
	public List<MenuCard> enterDiscounts(List<Discount> listOfDiscounts) throws DataAccessException, SQLException
	{
		listOfMenuCards = personalOrderController.enterDiscounts(listOfDiscounts);
		return listOfMenuCards;
	}
	
	public MenuCard getAdultMenu()
	{
		MenuCard adultMenu = null;
		for (MenuCard menu: listOfMenuCards)
		{
			if (menu.getName().contains("Voksen"))
			{
				adultMenu = menu;
			}
		}
		if (adultMenu == null)
		{
			throw new NullPointerException("No adult menu was found");
		}
		return adultMenu;
	}
	
	public boolean isLunchTime()
	{
		boolean isLunch = false;
		if (16<(tableOrder.getTimeOfArrival().getHour()))
		{
			isLunch = true;
		}
		return isLunch;
	}
	
	public List<MainCourse> getMainCourses(MenuCard menuCard)
	{
		List<MainCourse> mainCourses = new ArrayList<>();
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		for (MenuItem item: menuItems)
		{
			if (item instanceof MainCourse)
			{
				mainCourses.add((MainCourse)item);
			}
		}
		return mainCourses;
	}
	
	public List<PotatoDish> getPotatoDishes(MenuCard menuCard)
	{
		List<PotatoDish> potatoDishes = new ArrayList<>();
		List<MenuItem> menuItems = menuCard.getAvailableMenuItems();
		for (MenuItem item: menuItems)
		{
			if (item instanceof PotatoDish)
			{
				potatoDishes.add((PotatoDish)item);
			}
		}
		return potatoDishes;
	}
	
	public void enterMainCourse(MainCourse mainCourse)
	{
		personalOrderController.enterMainCourse(mainCourse);
		this.mainCourse = mainCourse;
	}
	
	public MainCourse getMainCourse()
	{
		return mainCourse;
	}
	
	public void enterMainCourseOptions(MenuItem potatoDish, List<AddOnOption> listOfAddOnOptionChoices, List<SelectionOption> listOfSelectionOption)
	{
		personalOrderController.enterMainCourseOptions(potatoDish, listOfAddOnOptionChoices, listOfSelectionOption);
	}
	
	public void finishPersonalOrder()
	{
		try
		{
			personalOrderController.finishPersonalOrder();
			personalOrderController.clearMenuItemList();
		} catch (DataAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TableOrder getTableOrder()
	{
		return tableOrder;
	}
	
	public void clearOrder()
	{
		personalOrderController.clearMenuItemList();
	}
}
