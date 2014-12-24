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
	
	private CollisionBox collisionbox;
	private Image image;
	private boolean maxXReached, maxYReached;
	private int x, y, newX, newY;
	
	/**
	 * Constructs a new sprite from an image
	 * @throws SlickException 
	 */
	public Sprite(String folder, String filename, boolean isCollidable, int x, int y) throws SlickException
	{
		this.image = getImage(folder, filename);
		
		maxXReached = false;
		maxYReached = false;
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, image.getWidth(), image.getHeight());
		}
		else {
			collisionbox = null;
		}
		
		this.x = x;
		this.y = y;
		this.newX = x;
		this.newY = y;
	}

	/**
	 * Get collision box.
	 */
	public CollisionBox getCollisionBox() {
		return collisionbox;
	}
	
	/**
	 * Returns the image
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Returns true if the sprite has reached the maximum X-position
	 */
	public boolean hasMaxXReached()
	{
		return maxXReached;
	}
	
	/**
	 * Returns true if the sprite has reached the maximum Y-position
	 */
	public boolean hasMaxYReached()
	{
		return maxYReached;
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
	 * Returns the new X-position
	 */
	public int getNewX()
	{
		return newX;
	}
	
	/**
	 * Returns the new Y-position
	 */
	public int getNewY()
	{
		return newY;
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
	 * Sets the new X-position
	 */
	public void setNewX(int newX)
	{
		this.newX = newX;
	}
	
	/**
	 * Sets the new Y-position
	 */
	public void setNewY(int newY)
	{
		this.newY = newY;
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
	 * Moves the sprite slowly up
	 * @param yChange
	 */
	public void animateUp(int yChange)
	{
		int minY = newY - yChange;
		
		if(maxYReached && y > minY) {
			y -= 1;
		}
		else {
			maxYReached = false;
		}
	}
	
	/**
	 * Moves the sprite slowly down
	 * @param yChange
	 */
	public void animateDown(int yChange)
	{
		int maxY = newY + yChange;
		
		if(!maxYReached && y < maxY) {
			y += 1;
		}
		else {
			maxYReached = true;
		}
	}
	
	/**
	 * Moves the sprite slowly to left
	 * @param xChange
	 */
	public void animateLeft(int xChange)
	{
		int minX = newX - xChange;
		
		if(maxXReached && x > minX) {
			x -= 1;
		}
		else {
			maxXReached = false;
		}
	}
	
	/**
	 * Moves the sprite slowly to right
	 * @param xChange
	 */
	public void animateRight(int xChange)
	{
		int maxX = newX + xChange;
		
		if(!maxXReached && x < maxX) {
			x += 1;
		}
		else {
			maxXReached = true;
		}
	}
	
	/**
	 * Moves the sprite slowly up and down
	 */
	public void animateUpDown(int yChange)
	{
		animateUp(yChange);
		animateDown(yChange);
	}
	
	/**
	 * Moves the sprite slowly left and right
	 */
	public void animateLeftRight(int xChange)
	{
		animateLeft(xChange);
		animateRight(xChange);
	}
	
	/**
	 * Reset the X-position to the default
	 */
	public void resetX()
	{
		x = newX;
		maxXReached = false;
	}
	
	/**
	 * Reset the Y-position to the default
	 */
	public void resetY()
	{
		y = newY;
		maxYReached = false;
	}
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawImage(image, x, y);
	}
	
	/**
	 * Draws the collision box
	 */
	public void drawCollisionBox(Graphics g) {
		collisionbox.drawBox(g, x, y);
	}
}
