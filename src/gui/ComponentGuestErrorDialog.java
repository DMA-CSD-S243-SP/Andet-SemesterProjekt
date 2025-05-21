package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 11:12
 */
public class ComponentGuestErrorDialog extends JDialog
{
    public ComponentGuestErrorDialog(Frame parentFrame, String explanationText, String nameOfComponentText, String suggestedSolutionText)
    {
    	// Modal dialog window and not a Jframe like the other views
    	super(parentFrame, "Fejludfyldning", true);
    	
    	// Sets the default closing operation to dispose when being closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Sets the size of the viewport / window
        setSize(320, 275);
        
        // Sets the location to be relative to the parent frame
        setLocationRelativeTo(parentFrame);
        
        // Makes the viewport non-resizeable
        setResizable(false);

        
		///////////////////////
		// - Content Panel - //
		///////////////////////
        
        // Creates a main panel to hold the content
        JPanel mainPanel = new JPanel();
        
        // Sets the layout type to be a box layout that goes downward from the top
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
		// Sets the background color to Bone's off-white/beige color
        mainPanel.setBackground(new Color(245, 243, 236));
        
        // Adds the mainPanel to the Jdialog
        add(mainPanel);
        
        // Adds the specified amount of vertical spacing 
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));


        
		//////////////////////
		// - Heading Text - //
		//////////////////////
		
        // Creates a label with the specified text
        JLabel labelHeading = new JLabel("Forkert Udfyldning");

        // Sets the font to the specified style type and size
        labelHeading.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        // Changes the text color to 
        labelHeading.setForeground(new Color(40, 40, 40));
        
        // Sets the alignment to be centered
        labelHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Adds the component to the main panel
        mainPanel.add(labelHeading);

        // Adds the specified amount of vertical spacing
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        
		///////////////////////
		// - Error Message - //
		///////////////////////
		
        // Creates a new panel to hold content
        JPanel messagePanel = new JPanel();
        
        // Renders the message panel opague / transparent
        messagePanel.setOpaque(false);
        
        // Sets the layout type to be a box layout that goes downward from the top
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        
        // Creates a new label
        JLabel lblExplanationText = new JLabel(explanationText);
        
        // Applies the design to the specified label
        setLabelDesign(lblExplanationText);
        
        // Adds the component to the message panel
        messagePanel.add(lblExplanationText);
        
        // Creates a new label
        JLabel lblNameOfComponent = new JLabel(nameOfComponentText);
        
        // Applies the design to the specified label
        setLabelDesign(lblNameOfComponent);
        
        // Adds the component to the message panel
        messagePanel.add(lblNameOfComponent);
        
        // Creates a new label
        JLabel lblSuggestedSolutionText = new JLabel(suggestedSolutionText);
        
        // Applies the design to the specified label
        setLabelDesign(lblSuggestedSolutionText);
        
        // Adds the component to the message panel
        messagePanel.add(lblSuggestedSolutionText);
        
        // Adds the component to the panel
        mainPanel.add(messagePanel);
        
 
        
		////////////////
		// - Button - //
		////////////////
        
        // Adds the specified amount of vertical spacing
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
		// Creates a customized button component with the supplied information
		ComponentGuestButtonContinue btnContinue = new ComponentGuestButtonContinue("Ok", mainPanel);
		btnContinue.addActionListener(event -> 
		{
			dispose();
		});
        
        // Adds the component to the panel
        mainPanel.add(btnContinue);

        // Adds the specified amount of vertical spacing
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Sets the Jdialog to be visible
        this.setVisible(true);
    }
    
    
    private void setLabelDesign(JLabel label)
    {
    	// Sets the font to the specified style type and size
    	label.setFont(new Font("SansSerif", Font.PLAIN, 14));

		// Sets the text color to a dark medium grey
    	label.setForeground(new Color(62, 62, 62));

    	// Sets the alignment to be centered
    	label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}