/**
 * Program Name: CeilingFan.java
 * Purpose: An object class to simulate a ceiling fan and the various options associated with it.
 * Coder: Cameron Hughes
 * Date: July 27, 2022
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CeilingFan extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	final int MAX_SPEED = 3;
	
	JButton speedBtn, directionBtn;
	JLabel speedSetting, directionSetting;
	JPanel centerPanel;
	
	BufferedImage fanImage;
	Timer timer = new Timer(0, null);
	double rotateDegree = 0.0d;
	
	// Default fan values
	int speed = 0;
	String direction = "Clockwise";
	
	// Method for rotating the image
	// Credits for this math go to a mysterious stranger on Stack Overflow many years ago
	public BufferedImage rotate(BufferedImage image, Double degrees)
	{
	    // Calculate the new size of the image based on the angle of rotation
	    double radians = Math.toRadians(degrees);
	    double sin = Math.abs(Math.sin(radians));
	    double cos = Math.abs(Math.cos(radians));
	    int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
	    int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

	    // Create a new image
	    BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = rotated.createGraphics();
	    
	    // Calculate the center point around which the image will be rotated
	    int x = (newWidth - image.getWidth()) / 2;
	    int y = (newHeight - image.getHeight()) / 2;
	    
	    // Transform the origin point around the center point
	    AffineTransform at = new AffineTransform();
	    at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
	    at.translate(x, y);
	    g2d.setTransform(at);
	    
	    // Paint the original image
	    g2d.drawImage(image, 0, 0, null);
	    return rotated;
	}
	
	// Constructor
	public CeilingFan()
	{
		super("Ceiling Fan Controls");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,700);
		this.setLocationRelativeTo(null);
		
		// Create topPanel to hold the fan
		JPanel topPanel = new JPanel();
		
		JLabel signature = new JLabel("Programmed by Cameron Hughes");
		topPanel.add(signature);
		
		this.add(topPanel, BorderLayout.NORTH);
		
		// Create centerPanel to hold fan attribute info
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		try
		{
			fanImage = ImageIO.read(getClass().getResource("fan.png"));
			centerPanel.add(new JLabel(new ImageIcon(fanImage)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.add(centerPanel, BorderLayout.CENTER);
				
		// Add bottom panel to bottom
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,2));
		bottomPanel.setBackground(new Color (210,210,210) );
		
		speedSetting = new JLabel("Speed: " + speed);
		directionSetting = new JLabel("Direction: " + direction);
		speedSetting.setHorizontalAlignment(SwingConstants.CENTER);
		directionSetting.setHorizontalAlignment(SwingConstants.CENTER);
		bottomPanel.add(speedSetting);
		bottomPanel.add(directionSetting);
		
		speedBtn = new JButton("Change Speed");
		directionBtn = new JButton("Change Direction");
		
		bottomPanel.add(speedBtn, BorderLayout.CENTER);
		bottomPanel.add(directionBtn, BorderLayout.SOUTH);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		// Register a listener for the buttons
		ButtonListener listener = new ButtonListener();
		speedBtn.addActionListener(listener);
		directionBtn.addActionListener(listener);
		
		//this.pack();
		this.setVisible(true);		
	}//end constructor
	
	// Inner Class
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			// Change speed button pressed
			if(ev.getSource().equals(speedBtn))
			{
				if (speed == MAX_SPEED)
					speed = 0;
				else
					speed++;
			}
			// Change direction button pressed
			else if(ev.getSource().equals(directionBtn) )
			{
				if (direction == "Clockwise")
					direction = "Counter-Clockwise";
				else if (direction == "Counter-Clockwise")
					direction = "Clockwise";
			}
			
			// Update text fields
			speedSetting.setText("Speed: " + speed);
			directionSetting.setText("Direction: " + direction);
			
			// Spin the fan
			timer.stop();
			
			if (direction == "Counter-Clockwise")
			{
				timer = new Timer(31 - speed * 10, new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	centerPanel.removeAll();
		            	centerPanel.add(new JLabel(new ImageIcon(rotate(fanImage, rotateDegree))));
		            	revalidate();
		            	rotateDegree = rotateDegree - (speed + 2);
		            }
		        });
			}
			else if (direction == "Clockwise")
			{
				timer = new Timer(31 - speed * 10, new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	centerPanel.removeAll();
		            	centerPanel.add(new JLabel(new ImageIcon(rotate(fanImage, rotateDegree))));
		            	revalidate();
		            	rotateDegree = rotateDegree + (speed + 2);
		            }
		        });
			}
			
			if (speed > 0)
				timer.start();
			
		}//end actionPerformed	
	}//end inner class
	
	public static void main(String[] args) throws IOException
	{
		new CeilingFan();
	}//end main
	
}//end class
