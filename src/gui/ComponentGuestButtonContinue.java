package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * TODO: Write a thorough description of this class and also java docs
 * for the constructor and the class' methods
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 21/05/2025 - 02:33
 */
public class ComponentGuestButtonContinue extends JButton
{
	// The radius used to round the corners of the button
	private int cornerRadius = 8;

	// The thickness of the dark outer border drawn behind the button
	private int borderThickness = 1;
	
	
	// attachedPanel - The panel that this particular button is added to
	public ComponentGuestButtonContinue(String text, JPanel attachedPanel)
	{	
		super(text);
		setFocusPainted(false);
		setContentAreaFilled(false);
		
		// Disable the default focus ring
		setFocusPainted(false);

		// We'll paint the button manually
		setContentAreaFilled(false);

		// Adds vertical spacing above this button to separate it from previous components
		attachedPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Don't use the default button border
		this.setBorderPainted(false);

		// Set font to Tahoma, plain, size 16
		this.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// Center-align the button in the layout
		this.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Center text horizontally inside the button
		this.setHorizontalAlignment(SwingConstants.CENTER);

		// Center text vertically inside the button
		this.setVerticalAlignment(SwingConstants.CENTER);

		// Set preferred size for layout managers
		this.setPreferredSize(new Dimension(180, 45));

		// Limit max height to keep UI consistent
		this.setMaximumSize(new Dimension(180, 50));

		// Set default background (Bone’s red)
		this.setBackground(new Color(187, 41, 41));

		// Set text color to white
		this.setForeground(new Color(255, 255, 255));
	}

	
	@Override
	protected void paintComponent(Graphics graphics)
	{
		// Create a copy of the graphics context for custom drawing
		Graphics2D graphics2D = (Graphics2D) graphics.create();

		// Enable anti-aliasing for smooth rounded edges
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw outer border in black
		graphics2D.setColor(new Color(0, 0, 0));

		// Fills out the specified rounded corner triangle
		graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius + borderThickness, cornerRadius + borderThickness);

		// MOdifies the fill coloring of the button whenever it is pressed or unpressed
		if (getModel().isArmed())
		{
			graphics2D.setColor(getBackground().darker());
		}
		else
		{
			graphics2D.setColor(getBackground());
		}

		// Draws the main rounded button inside of the defined black border border
		graphics2D.fillRoundRect(borderThickness, borderThickness, getWidth() - (borderThickness * 2), getHeight() - (borderThickness * 2), cornerRadius, cornerRadius);

		// Paints the contents of the button on top
		super.paintComponent(graphics);

		// Disposes of the graphics object to free up resources
		graphics2D.dispose();
	}


	@Override
	public boolean contains(int xCoordinate, int yCoordinate)
	{
		// Creates a rounded shape to the visible button
		Shape roundedShape = new RoundRectangle2D.Float(borderThickness, borderThickness, getWidth() - (borderThickness * 2), getHeight() - (borderThickness * 2), cornerRadius, cornerRadius);
		
		// Returns whether the mouse click is within the shape or not
		return roundedShape.contains(xCoordinate, yCoordinate);
	}


	public void setCornerRadius(int cornerRadius)
	{
		this.cornerRadius = cornerRadius;
		
		// Changing the corner radius causes us to have to repaint the button this is why this i being done
		repaint();
	}


	public int getCornerRadius()
	{
		return cornerRadius;
	}
	
	
	public void setBorderThickness(int borderThickness)
	{
		this.borderThickness = borderThickness;
		
		// Changing the border thickness causes us to have to repaint the button this is why this i being done
		repaint();
	}


	public int getBorderThickness()
	{
		return borderThickness;
	}
}