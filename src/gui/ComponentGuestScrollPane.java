package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 20/05/2025 - 13:37
 */	
public class ComponentGuestScrollPane extends JScrollPane
{
	public ComponentGuestScrollPane(JPanel panelContent, JPanel attachedPanel)
	{
		super(panelContent);
		
		// Removes the default border from the scroll pane
		this.setBorder(null);

		// Sets the background color to Bone's off-white/beige color
		this.setBackground(new Color(245, 243, 236));

		// Changes the horizontal scrolling speed to make it feel smoother
		this.getVerticalScrollBar().setUnitIncrement(16);

		// Disables the ability to scroll horizontally
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Modifies the margin in an attempt to prvent contents from touching the edges
		attachedPanel.setBorder(new EmptyBorder(10, 20, 15, 20));
		
		// Aligns content at the top
		panelContent.setAlignmentY(Component.TOP_ALIGNMENT);
		this.getViewport().setAlignmentY(Component.TOP_ALIGNMENT);
	}
}