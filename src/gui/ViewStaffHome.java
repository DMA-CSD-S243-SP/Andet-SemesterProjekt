//Packages
package gui;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


/**
 * TODO: Write a relatively detailed description of what this
 * class represents, and add a version number containing both date 
 * and time, matching the other classes' java documentation.
 *
 * @author Christoffer Søndergaard
 * @version 01/06/2025 - 22:24
 */
public class ViewStaffHome extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// Creates the main panel that will contain the other graphical user interface elements
	private JPanel mainPanel;
	
	
	/**
	 * Create the frame.
	 */
	public ViewStaffHome()
	{
		initGUI();
	}
	
	
	private void initGUI()
	{
		setGeneralBehavior();
		setGeneralVisuals();
		
		// Panels & Layout Structure
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(245, 243, 236));
		mainPanel.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		
		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(245, 243, 236));
		panelWest.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		mainPanel.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(245, 243, 236));
		panelCenter.setBorder(new EmptyBorder(0, 52, 0, 52));
		mainPanel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenterNorth = new JPanel();
		panelCenterNorth.setBackground(new Color(245, 243, 236));
		FlowLayout flowLayout_1 = (FlowLayout) panelCenterNorth.getLayout();
		flowLayout_1.setVgap(0);
		panelCenter.add(panelCenterNorth, BorderLayout.NORTH);
		
		JPanel panelCenterSouth = new JPanel();
		panelCenterSouth.setBackground(new Color(245, 243, 236));
		FlowLayout flowLayout = (FlowLayout) panelCenterSouth.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCenter.add(panelCenterSouth, BorderLayout.SOUTH);
		
		JPanel panelCenterCenter = new JPanel();
		panelCenterCenter.setBackground(new Color(245, 243, 236));
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		panelCenterCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		// Button - Table Order Overview
		JButton btnSales = new JButton("Bestillinger");
		btnSales.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				clickedTableOrders();
			}
		});
		setDesignSideBarButton(btnSales);
		panelWest.add(btnSales);
		

		// Button - Request Service Button - Not functional
		JButton btnServiceQueue = new JButton("Service Kø");
		btnServiceQueue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				clickedServiceQueue();
			}
		});
		setDesignSideBarButton(btnServiceQueue);
		panelWest.add(btnServiceQueue);
		
		
		// Button - Log Out
		ComponentStaffButton btnLogout = new ComponentStaffButton("Log Ud", false);
		panelCenterSouth.add(btnLogout);
		btnLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				clickedLogout();
			}
		});
		
		
		// Panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 243, 236));
		
		panelCenterCenter.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{372, 0};
		gbl_panel.rowHeights = new int[] {180, 30, 30};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panel.add(verticalStrut, gbc_verticalStrut);
		
		
		// Labels
		JLabel lblWelcomeUser = new JLabel("Velkommen");
		GridBagConstraints gbc_lblWelcomeUser = new GridBagConstraints();
		gbc_lblWelcomeUser.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcomeUser.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWelcomeUser.gridx = 0;
		gbc_lblWelcomeUser.gridy = 1;
		panel.add(lblWelcomeUser, gbc_lblWelcomeUser);
		lblWelcomeUser.setForeground(new Color(62, 62, 62));
		lblWelcomeUser.setFont(new Font("Tahoma", Font.PLAIN, 72));
		lblWelcomeUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		JLabel lblUserName = new JLabel("Jacob");
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.WEST;
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 2;
		panel.add(lblUserName, gbc_lblUserName);
		lblUserName.setForeground(new Color(62, 62, 62));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblUserName.setAlignmentX(0.5f);
	}

	
	private void clickedTableOrders()
	{
		//TODO:
		// Launches a frame view for either adding or editing an order
		ViewStaffTableOrderOverview viewStaffTableOrderOverview = new ViewStaffTableOrderOverview();
		viewStaffTableOrderOverview.setVisible(true);

		// Closes the current window by making it invisible and disposing it afterwards to free up unused resources
		closeCurrentFrame();
	}
	
	
	private void clickedServiceQueue()
	{
		new ComponentGuestErrorDialog(this, 
				"Følgende funktion",
				"Service Kø",
				"Er endnu ikke implementeret"
		);
	}
	
	
	private void clickedLogout()
	{
		// Creates and launches a new viewStaffLogin window for the user to interact with
		ViewStaffLogin viewStaffLogin = new ViewStaffLogin();
		viewStaffLogin.setVisible(true);

		// Closes the current window by making it invisible and disposing it afterwards to free up unused resources
		closeCurrentFrame();
	}
	
	
	private void closeCurrentFrame()
	{
		this.setVisible(false);
		this.dispose();
	}


	private void setDesignSideBarButton(JButton button)
	{
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		button.setBorderPainted(false);
		button.setBackground(new Color(245, 243, 236));
		button.setForeground(new Color(187, 41, 41));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setPreferredSize(new Dimension(160, 45));
		button.setMaximumSize(new Dimension(160, 50));
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
		setTitle("Bone's Personale");
		
		// Sets a minimum width and height for the window's dimensions and makes the window launch in this size (1280 x 720)
		adjustWindowSize(1280, 720);
		
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

		// Set the background color to Bone's type of red
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
