//Packages


//Imports
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import database.DataAccessException;
import model.TableOrder;



/**
 * TODO: Write a relatively detailed description of what this
 * class represents, and add a version number containing both date 
 * and time, matching the other classes' java documentation.
 * 
 * @author Christoffer Søndergaard & Line Bertelsen & Anders Trankjær
 * @version 01/06/2025 - 22:24
 */
public class ViewStaffTableOrderOverview extends JFrame
{
	// Added in order to suppress the warning that appears in serializable classes where no serialVersionUID is specified
	private static final long serialVersionUID = 1L;
	
	// Creates the main panel that will contain the other graphical user interface elements
	private JPanel mainPanel;

	// Used to create dialog boxes to inform the user of potentially invalid actions
	private Frame dialogBoxFrame = null;
	
	// Creates a table for use with the staffTableOrderOverviewTableModel
	private JTable table;
	private ViewStaffTableOrderOverviewTableModel orderOverviewModel;
	
	
	
	// Old example code from a previous project to show how
	// it was done using a container
	
	// Creates an EmployeeUserData object which allows us access to the single instance of EmployeeUserData holding an Employee object   
//	private EmployeeUserData employeeInstance = EmployeeUserData.getInstance(null);


	/**
	 * Create the frame.
	 */
	public ViewStaffTableOrderOverview()
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
		panelSouth.setBorder(new EmptyBorder(0, 52, 15, 52));
		mainPanel.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 20));
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(245, 243, 236));
		panelCenter.setBorder(new EmptyBorder(0, 50, 0, 50));
		mainPanel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenterNorth = new JPanel();
		panelCenterNorth.setBackground(new Color(245, 243, 236));
		panelCenter.add(panelCenterNorth, BorderLayout.NORTH);
		panelCenterNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenterNorthNorth = new JPanel();
		panelCenterNorthNorth.setBackground(new Color(245, 243, 236));
		FlowLayout flowLayout_2 = (FlowLayout) panelCenterNorthNorth.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(25);
		panelCenterNorth.add(panelCenterNorthNorth, BorderLayout.NORTH);
		
		JPanel panelCenterNorthSouth = new JPanel();
		panelCenterNorthSouth.setBackground(new Color(245, 243, 236));
		panelCenterNorthSouth.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		FlowLayout flowLayout_1 = (FlowLayout) panelCenterNorthSouth.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelCenterNorth.add(panelCenterNorthSouth, BorderLayout.SOUTH);
		
		JPanel panelCenterSouth = new JPanel();
		panelCenterSouth.setBackground(new Color(245, 243, 236));
		panelCenterSouth.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panelCenterSouth.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCenter.add(panelCenterSouth, BorderLayout.SOUTH);
		
		JPanel panelCenterCenter = new JPanel();
		panelCenterCenter.setBackground(new Color(245, 243, 236));
		panelCenterCenter.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(0, 0, 0)));
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		panelCenterCenter.setLayout(new GridLayout(0, 1, 0, 0));
		
		// JTable with column names
		initTable();
		updateTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelCenterCenter.add(scrollPane);
		scrollPane.setViewportView(table);
		TableColumnModel columnModel = table.getColumnModel();
		//columnModel.getColumn().setPreferredWidth(90);
		columnModel.getColumn(0).setPreferredWidth(110);
		columnModel.getColumn(1).setPreferredWidth(125);
		columnModel.getColumn(2).setPreferredWidth(125);
		columnModel.getColumn(3).setPreferredWidth(60);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(480);
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.setBackground(new Color(89, 95, 111));
		header.setForeground(new Color(255, 255, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        header.setBackground(new Color(89, 95, 111));
        header.setForeground(new Color(255, 255, 255));


		// Button - Preparation Finished
		ComponentStaffButton btnDelete = new ComponentStaffButton("Færdiggjort", true);
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// TODO: Make this work
			}
		});
		panelCenterSouth.add(btnDelete);


		// Button - Back
		ComponentStaffButton btnBack = new ComponentStaffButton("Tilbage", false);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				clickedBack();
			}
		});
		panelSouth.add(btnBack);
		
		
		// Labels
		JLabel lblViewHeading = new JLabel("Bestillingsoversigt");
		lblViewHeading.setForeground(new Color(62, 62, 62));
		lblViewHeading.setFont(new Font("Tahoma", Font.BOLD, 28));
		panelCenterNorthNorth.add(lblViewHeading);
	}
	
	
	
	/**
	 * sends you back to the mainMenu 
	 */
	private void clickedBack()
	{
		ViewStaffHome viewStaffHome = new ViewStaffHome();
		viewStaffHome.setVisible(true);
		
		// Closes the current window by making it invisible and disposing it afterwards to free up unused resources
		closeCurrentFrame();
	}
	
	
	/**
	 * makes this frame invisible and disposes of it
	 */
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
	
	
	/**
	 * visual elements for the tableModel
	 */
	private void initTable()
	{
		orderOverviewModel = new ViewStaffTableOrderOverviewTableModel();
		table = new JTable(orderOverviewModel);
		table.setModel(orderOverviewModel);
		updateTable();
	}
	
	
	/**
	 * updates the Tablemodel with the changes made to the Container.
	 */
	private void updateTable() 
	{
		try
		{
			List<TableOrder> data = new application.TableOrderController().findAllVisibleToKitchenTableOrders();
			orderOverviewModel.setData(data);
			
		} catch (SQLException e)
		{
			//e.printStackTrace();
		} catch (DataAccessException e)
		{
			//e.printStackTrace();
		}
	}
	
}