package gui;
import application.PersonalOrderController;

public class UtilityGuestInformation
{
	private static UtilityGuestInformation instance;
	private PersonalOrderController personalOrderController;

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
}
