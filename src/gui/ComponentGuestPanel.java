package gui;

// Imports
import java.awt.Color;

import javax.swing.JPanel;


/**
 * A custom JPanel GUI component used to hold contents with an already
 * predefined background color that is off-white or beige. 
 *
 *
 * Author: Christoffer Søndergaard
 * Version: 08/06/2025 - 13:48
 */
public class ComponentGuestPanel extends JPanel
{
    /**
     * Constructs a new ComponentGuestPanel.
     *
     * The panel is initialized with a predefined off-white /beige-ish background color.
     */
	public ComponentGuestPanel()
	{
		super();
		
		// Sets the background color to Bone's off-white/beige color
		this.setBackground(new Color(245, 243, 236));
	}
}