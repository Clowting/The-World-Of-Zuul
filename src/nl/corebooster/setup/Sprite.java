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
	private boolean maxYReached, maxXReached;
	private int x, y, newX, newY;
	
	/**
	 * Constructs a new tile
	 * @throws SlickException 
	 */
	public Sprite(String folder, String filename, int x, int y) throws SlickException
	{
		this.image = getImage(folder, filename);
		
		maxYReached = false;
		maxXReached = false;
		
		this.x = x;
		this.y = y;
		this.newX = x;
		this.newY = y;
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
	 * Moves the sprite slowly up and down
	 */
	public void animateVertical(int yChange)
	{
		int minY = newY - yChange;
		int maxY = newY + yChange;
		
		if(!maxYReached && y < maxY) {
			y += 1;
		}
		else {
			maxYReached = true;
		}
		
		if(maxYReached && y > minY) {
			y -= 1;
		}
		else {
			maxYReached = false;
		}
	}
	
	/**
	 * Moves the sprite slowly left and right
	 */
	public void animateHorizontal(int yChange)
	{
		int minX = newX - yChange;
		int maxX = newX + yChange;
		
		if(!maxXReached && x < maxX) {
			x += 1;
		}
		else {
			maxXReached = true;
		}
		
		if(maxXReached && x > minX) {
			x -= 1;
		}
		else {
			maxXReached = false;
		}
	}
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawImage(image, x, y);
	}
}
