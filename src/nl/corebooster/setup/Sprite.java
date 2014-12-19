package nl.corebooster.setup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Describes a tile from the game with an X and Y position
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Sprite {

	private Image image;
	private int x, y;
	
	/**
	 * Constructs a new tile
	 * @throws SlickException 
	 */
	public Sprite(String folder, String filename, int x, int y) throws SlickException
	{
		this.image = getImage(folder, filename);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the image
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Returns the X-position
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the Y-position
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Sets the X-position
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the Y-position
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Gets the image from the specified location
	 * @throws SlickException 
	 */
	private Image getImage(String folder, String filename) throws SlickException
	{
		Image image = new Image("data/" + folder + "/" + filename);
		
		return image;
	}
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawImage(image, x ,y);
	}
	
}
