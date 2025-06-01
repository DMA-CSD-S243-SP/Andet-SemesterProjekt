package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class ViewStaffLogin extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// Creates the main panel that will contain the other graphical user interface elements
	private JPanel mainPanel;
		
	// Used to create dialog boxes to inform the user of potentially invalid actions
	private Frame dialogBoxFrame = null;

	// Visual graphic user interface components
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel lblErrorMessage;

	
	/**
	 * Create the frame.
	 */
	public ViewStaffLogin()
	{
		initGUI();
	}
	
	
	private void initGUI()
	{
		setGeneralBehavior();
		setGeneralVisuals();
		
		// Panels & Layout
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 243, 236));
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		mainPanel.add(panel, BorderLayout.CENTER);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(245, 243, 236));
		
		
		panel.add(panelCenter);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] {0};
		gbl_panelCenter.rowHeights = new int[] {60, 30, 0, 30, 0, 0, 15, 0, 30, 30, 0, 45, 0, 30};
		gbl_panelCenter.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelCenter.setLayout(gbl_panelCenter);
		
		JLabel lblViewHeading = new JLabel("Medarbejderlogin");
		lblViewHeading.setForeground(new Color(62, 62, 62));
		lblViewHeading.setBackground(new Color(89, 95, 111));
		lblViewHeading.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GridBagConstraints gbc_lblViewHeading = new GridBagConstraints();
		gbc_lblViewHeading.insets = new Insets(0, 0, 5, 0);
		gbc_lblViewHeading.gridx = 3;
		gbc_lblViewHeading.gridy = 2;
		panelCenter.add(lblViewHeading, gbc_lblViewHeading);
		
		
		// Labels
		JLabel lblUsername = new JLabel("Medarbejder ID:");
		lblUsername.setForeground(new Color(62, 62, 62));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 3;
		gbc_lblUsername.gridy = 4;
		panelCenter.add(lblUsername, gbc_lblUsername);
		
		JLabel lblPassword = new JLabel("Adgangskode:");
		lblPassword.setForeground(new Color(62, 62, 62));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 7;
		panelCenter.add(lblPassword, gbc_lblPassword);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrorMessage.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblErrorMessage = new GridBagConstraints();
		gbc_lblErrorMessage.insets = new Insets(0, 0, 5, 0);
		gbc_lblErrorMessage.gridx = 3;
		gbc_lblErrorMessage.gridy = 9;
		panelCenter.add(lblErrorMessage, gbc_lblErrorMessage);
		

		// Text fields
		textFieldUsername = new JTextField();
		textFieldUsername.setText("100000");
		textFieldUsername.setForeground(new Color(192, 192, 192));
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.anchor = GridBagConstraints.WEST;
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.gridx = 3;
		gbc_textFieldUsername.gridy = 5;
		panelCenter.add(textFieldUsername, gbc_textFieldUsername);
		
		passwordField = new JPasswordField();
		passwordField.setText("Password");
		passwordField.setForeground(new Color(192, 192, 192));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 8;
		panelCenter.add(passwordField, gbc_passwordField);

		
		// Button - Log In
		ComponentStaffButton btnLogin = new ComponentStaffButton("Log På", true);

		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 11;
		panelCenter.add(btnLogin, gbc_btnLogin);
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				clickedLogin();
			}
		});

		// Sets the default button in this view to be the specified button object
		mainPanel.getRootPane().setDefaultButton(btnLogin);
	}


	private void clickedLogin()
	{
		// Takes the data that was added in the text field, trims it thereby removing spaces, and then stores it within a variable
	    String inputEmployeeID = textFieldUsername.getText().trim();
	    String inputPassword = passwordField.getText().trim();

		// If one or more of the text fields are missing inputted information then execute this section
		if (inputEmployeeID.isEmpty() || inputPassword.isEmpty())
		{	
			JOptionPane.showMessageDialog(dialogBoxFrame, "Én eller flere felter er ikke udfyldt, udfyld først alle felter før du trykker på 'login' knappen.", "Information", JOptionPane.INFORMATION_MESSAGE);
			
			return;
	    }
		
		// Creates a variable to store the Employee object's EmployeeID within 
		int employeeIDNumber;
		
		try
		{
			// Converts the string value of the input to an integer value which we can give to the controller to find any existing employee with a matching EmployeID within the container
			employeeIDNumber = Integer.valueOf(inputEmployeeID);
		}
		
		catch (NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(dialogBoxFrame, "Det indtastede medarbejder ID er ugyldigt da det indeholder andet end tal.", "Information", JOptionPane.INFORMATION_MESSAGE);

			return;
		}
		
		
		// Creates and launches a new staffLoginView window for the user to interact with
		ViewStaffHome viewStaffHome = new ViewStaffHome();
		viewStaffHome.setVisible(true);
		
		// Closes the current window by making it invisible and disposing it afterwards to free up unused resources
		closeCurrentFrame();
	}
	
	
	private void closeCurrentFrame()
	{
		this.setVisible(false);
		this.dispose();
	}
	



	private void setGeneralBehavior()
	{
		// Sets the operation that will occur when the close window button (x) is clicked to exit the application altogether 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void setGeneralVisuals()
	{
		// Modifies the visual appearance of the main panel
		modifyMainPanel();
		
		// Sets a title shown in the top left corner of the window
		setTitle("Vestbjerg Byggecenter A/S");
		
		// Sets a minimum width and height for the window's dimensions and makes the window launch in this size (500 x 600)
		adjustWindowSize(500, 600);
		
		// Sets the favorite icon of the application to the specified image
		setFavIcon("/favIcon.png");
		
		// Creates a panel containing a header in the form of an image in a northern panel 
		createPanelHeader("/headerLogo.png");
	}
	

	private void modifyMainPanel()
	{
		// Creates a new panel and stores it as the mainPanel this is the panel that will contain all other panels
		mainPanel = new JPanel();
		
		// Changes the width of the border of the panel to be 0 pixels on all of the sides
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		// Changes the layout for the panel to use the border layout
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		// Sets the mainPanel to act as the main area holding other components such as panels, textfields, buttons, label and so forth
		setContentPane(mainPanel);
	}


	private void adjustWindowSize(int width, int height)
	{
		// Changes the window's dimensions to be set to the value of width x height when the window is launched
		setBounds(100, 100, width, height);
		
		// Sets a minimum size of the window preventing it from becoming smaller than width x height
		setMinimumSize(new Dimension(width, height));
		
		// Defines where on the screen the window will be positioned and sets it to be in the center of the screen - NOTE: That this must be set after sizings has adjusted 
		setLocationRelativeTo(null);
	}

	
	private void setFavIcon(String favIconPath)
	{
		// Finds the resource with the name specified in the method's parameter and stores its url destination in the local urlPath variable 
		URL urlPath = this.getClass().getResource(favIconPath);
		
		System.out.println(urlPath);
		
		// Creates and loads an ImageIcon by using the resource file found at the provided url path
		ImageIcon favoriteIcon = new ImageIcon(urlPath);
		
		// Retrieves the icon's image and store it within the favoriteIconImage variable
		Image favoriteIconImage = favoriteIcon.getImage();
		
		// Sets the window's image icon to the image stored within the favoriteIconImage variable
		setIconImage(favoriteIconImage);
	}
	
	
	private void createPanelHeader(String headerLogoPath)
	{
		// Creates a new panel and stores it as the panelNorth variable
		JPanel panelNorth = new JPanel();
		
		// Changes the panel to use the flow layout as its type of layout
		FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();

		// Changes the alignment of the contents of the panel to be left aligning
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		// Adds a border with two pixels width at the bottom part of the panel with a black color
		panelNorth.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));

		// Changes the color of the background to a Bone's dark red
		panelNorth.setBackground(new Color(157, 33, 38));
		
		// Adds the panel to the north section of the main panel's border layout
		mainPanel.add(panelNorth, BorderLayout.NORTH);

		// Creates a new label with no text as it will be used for placing the image
		JLabel lblHeaderLogo = new JLabel("");

		// Finds the resource with the name specified in the method's parameter and stores its url destination in the local urlPath variable 
		URL urlPath = this.getClass().getResource(headerLogoPath);
		
		// Creates and loads an ImageIcon by using the resource file found at the provided url path
		ImageIcon imageIcon = new ImageIcon(urlPath);
	
		// Sets the image icon to be used by the empty label and show at its location in the viewport
		lblHeaderLogo.setIcon(imageIcon);
		
		// Adds the label to the panel
		panelNorth.add(lblHeaderLogo);
	}
}
