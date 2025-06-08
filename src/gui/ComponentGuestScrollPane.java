package gui;

// Imports
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


/**
 * A custom JScrollPane component used in the guest-facing GUI to display content
 * that requires scrollable.
 * 
 * This scroll pane removes its default border and disables horizontal scrolling,
 * and changes the vertical scroll speed, and applies padding to the attached panel'
 * to avoid the content from overlapping with the edges.
 * 
 * 
 * Author: Christoffer Søndergaard
 * Version: 08/06/2025 - 13:53
 */
public class ComponentGuestScrollPane extends JScrollPane
{
    /**
     * Constructs a customized scroll pane for displaying vertically scrollable content
     * for the guest-facing GUI.
     *
     * @param panelContent the content panel to be made scrollable
     * @param attachedPanel the parent panel that holds this scroll pane
     */
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