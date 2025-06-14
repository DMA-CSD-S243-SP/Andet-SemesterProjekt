package gui;

// Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * A custom GUI component that creates a header section for a guest-facing panel.
 *
 * This header consists of a logo and a title overlaid on top of one another,
 * It is added to the top (NORTH) of a parent panel and uses an overlay layout
 * to render the logo and header title in a layered way.
 * 
 * 
 * Author: Christoffer Søndergaard  
 * Version: 08/06/2025 - 09:22
 */
public class ComponentGuestHeaderTitle extends JPanel
{
    /**
     * Constructs a header component with an image and title overlay.
     * 
     * The header is placed at the top of the given parent panel. The header includes
     * a background image/logo and a centered title text.
     *
     * @param parentPanel the panel this header will be added to
     * @param headerLogoPath the path to the logo image resource that should be shown
     * @param headerTitle the text title to be added on top of the logo
     */
	public ComponentGuestHeaderTitle(JPanel parentPanel, String headerLogoPath, String headerTitle)
	{
		super();
		
		////////////////////////////////
		// - Header Container Panel - //
		////////////////////////////////
		
		// Changes the layout type of the panel to use a BorderLayout
		this.setLayout(new BorderLayout());

		// Set the background color to Bone's type of red
		this.setBackground(new Color(157, 33, 38));

		// Adds the panel as a component in the north of the supplied parent panel's border layout
		parentPanel.add(this, BorderLayout.NORTH);

		
		
		//////////////////////
		// - Header Image - //
		//////////////////////

		// Create a new panel that uses OverlayLayout to layer components
		JPanel panelHeaderOverlay = new JPanel();
		panelHeaderOverlay.setLayout(new OverlayLayout(panelHeaderOverlay));

		// Set a fixed height of 100 pixels for the header
		panelHeaderOverlay.setPreferredSize(new Dimension(380, 100));

		// Make the overlay panel transparent so background shows through
		panelHeaderOverlay.setOpaque(false);

		// Load and scale the header image
		URL urlPath = this.getClass().getResource(headerLogoPath);
		ImageIcon originalIcon = new ImageIcon(urlPath);
		Image scaledImage = originalIcon.getImage().getScaledInstance(380, 100, Image.SCALE_SMOOTH);

		// Create a JLabel with the scaled image as its icon
		JLabel labelHeaderLogo = new JLabel(new ImageIcon(scaledImage));

		// Center the image horizontally and vertically in the overlay panel
		labelHeaderLogo.setAlignmentX(0.5f);
		labelHeaderLogo.setAlignmentY(0.5f);
		
		
		
		///////////////////////////
		// - Header Title Text - //
		///////////////////////////
		
		// Creates a labelText object that will be used as text over the header
		JLabel labelText = new JLabel(headerTitle);

		// Set text color to white for contrast in front of bone's red background
		labelText.setForeground(Color.WHITE);

		// Sets the font to the specified style type and size
		labelText.setFont(new Font("SansSerif", Font.BOLD, 24));

		// Aligns the label horizontally to the center within its container
		labelText.setHorizontalAlignment(SwingConstants.CENTER);

		// Aligns the label vertically to the center within its container
		labelText.setVerticalAlignment(SwingConstants.CENTER);

		// Align the label within the overlay layout both horizontally and vertically respectfully
		labelText.setAlignmentX(0.5f);
		labelText.setAlignmentY(0.5f);

		// Changes the label's background to be transparent
		labelText.setOpaque(false);

		// Adds the specified amount of pixels as left padding to the text to avoid overlapping the logo
		labelText.setBorder(new EmptyBorder(0, 70, 0, 0));

		// Adds the text label first so it will appear above the image
		panelHeaderOverlay.add(labelText);

		// Adds the label containing the header logo image below the text in the overlay
		panelHeaderOverlay.add(labelHeaderLogo);

		// Adds the overlay panel to the center of the top (header) panel
		this.add(panelHeaderOverlay, BorderLayout.CENTER);
	}
}