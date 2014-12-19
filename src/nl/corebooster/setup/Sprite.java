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
	private boolean pointYReached, pointXReached;
	private int x, y, newX, newY;
	
	/**
	 * Constructs a new tile
	 * @throws SlickException 
	 */
	public Sprite(String folder, String filename, int x, int y) throws SlickException
	{
		this.image = getImage(folder, filename);
		
		pointYReached = false;
		pointXReached = false;
		
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
		if(y <= newY + yChange) {
			y += 10;
		}
		else {
			pointYReached = true;
		}
	}
	
	/*public void animateVertical(int yChange)
	{
		if(!pointYReached && y > newY - yChange) {
			y = y - 1;
		}
		else if(y < newY) {
			pointYReached = true;
			y = y + 1;
		}
		else {
			pointYReached = false;
		}
	}*/
	
	/**
	 * Moves the sprite slowly left and right
	 */
	public void animateHorizontal(int xChange)
	{
		if(!pointXReached && x > newX - xChange) {
			x = x - 1;
		}
		else if(x < newX) {
			pointXReached = true;
			x = x + 1;
		}
		else {
			pointXReached = false;
		}
	}
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawString("pointYReached: " + pointYReached, x, y - 60);
		g.drawString("y: " + y, x, y - 40);
		g.drawString("newY: " + newY, x, y - 20);
		
		g.drawImage(image, x, y);
	}
}
