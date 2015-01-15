package nl.corebooster.setup;

import nl.corebooster.setup.TriggerBox.TriggerType;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Describes a tile from the game with an X and Y position
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Sprite {
	
	private String spriteName;
	private CollisionBox collisionbox;
	private TriggerBox triggerbox;
	private Image image;
	private boolean maxXReached, maxYReached;
	private int x, y, newX, newY;
	
	/**
	 * Constructs a new sprite from an image
	 * @param folder The folder where the sprite is located
	 * @param filename The filename of the sprite
	 * @param isCollidable Depends if you can collide with the sprite or not, true/false
	 * @param x The initial x position of the sprite
	 * @param y The initial y position of the sprite
	 * @throws SlickException Indicates a failure to initialize the display 
	 */
	public Sprite(String spriteName, String folder, String filename, boolean isCollidable, int x, int y) throws SlickException
	{
		this.spriteName = spriteName;
		this.image = getImage(folder, filename);
		
		maxXReached = false;
		maxYReached = false;
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, image.getWidth(), image.getHeight());
		}
		else {
			collisionbox = null;
		}
		
		this.triggerbox = null;
		
		this.x = x;
		this.y = y;
		this.newX = x;
		this.newY = y;
	}
	
	/**
	 * Constructs a new sprite from an image with a trigger
	 * @param folder The folder where the sprite is located
	 * @param filename The filename of the sprite
	 * @param isCollidable Depends if you can collide with the sprite or not, true/false
	 * @param triggerType The type of trigger called when collided, SCENESWITCH/MESSAGE/ANIMATE
	 * @param triggerDirection The direction the player has to approach from for the trigger to work
	 * @param alternateX The x position to use if the normal position is blocked
	 * @param alternateY The y position to use if the normal position is blocked
	 * @param triggerValue The value of the trigger called when collided
	 * @param triggerMargin The margin of the trigger box
	 * @param x The initial x position of the sprite
	 * @param y The initial y position of the sprite
	 * @throws SlickException Indicates a failure to initialize the display 
	 */
	public Sprite(String spriteName, String folder, String filename, boolean isCollidable, TriggerType triggerType, int triggerDirection, int alternateX, int alternateY, String triggerValue, int triggerMargin, int x, int y) throws SlickException
	{
		this.spriteName = spriteName;
		this.image = getImage(folder, filename);
		
		maxXReached = false;
		maxYReached = false;
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, image.getWidth(), image.getHeight());
		}
		else {
			collisionbox = null;
		}
		
		this.triggerbox = new TriggerBox(spriteName, triggerType, triggerDirection, alternateX, alternateY, triggerValue, x, y, image.getWidth(), image.getHeight(), triggerMargin);
		
		this.x = x;
		this.y = y;
		this.newX = x;
		this.newY = y;
	}
	
	/**
	 * Returns the name of the sprite
	 * @return The name of the sprite
	 */
	public String getSpriteName()
	{
		return spriteName;
	}

	/**
	 * Get collision box.
	 * @return The collision box of the animated sprite
	 */
	public CollisionBox getCollisionBox() 
	{
		return collisionbox;
	}
	
	/**
	 * Returns the trigger box
	 * @return The trigger box of the animated sprite
	 */
	public TriggerBox getTriggerBox()
	{
		return triggerbox;
	}
	
	/**
	 * Returns the image
	 * @return The image the sprite is based on
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Returns true if the sprite has reached the maximum x-position
	 * @return The maximum x-position of the sprite
	 */
	public boolean hasMaxXReached()
	{
		return maxXReached;
	}
	
	/**
	 * Returns true if the sprite has reached the maximum y-position
	 * @return The maximum y-position of the sprite
	 */
	public boolean hasMaxYReached()
	{
		return maxYReached;
	}
	
	/**
	 * Returns the x-position
	 * @return The x-position of the sprite
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the y-position
	 * @return The y-position of the sprite
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Returns the new x-position
	 * @return The new x-position of the sprite
	 */
	public int getNewX()
	{
		return newX;
	}
	
	/**
	 * Returns the new y-position
	 * @return The new y-position of the sprite
	 */
	public int getNewY()
	{
		return newY;
	}
	
	/**
	 * Sets a new name for the sprite
	 * @param spriteName The new name
	 */
	public void setSpriteName(String spriteName)
	{
		this.spriteName = spriteName;
	}
	
	/**
	 * Sets the x-position
	 * @param x The x-position of the sprite
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y-position
	 * @param y The y-position of the sprite
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Sets the new x-position
	 * @param newX The new x-position of the sprite
	 */
	public void setNewX(int newX)
	{
		this.newX = newX;
	}
	
	/**
	 * Sets the new y-position
	 * @param newY The new y-position of the sprite
	 */
	public void setNewY(int newY)
	{
		this.newY = newY;
	}
	
	/**
	 * Gets the image from the specified location
	 * @param folder The folder where the sprite is located
	 * @param filename The filename of the sprite
	 * @return The newly created image
	 * @throws SlickException Indicates a failure to initialize the display 
	 */
	private Image getImage(String folder, String filename) throws SlickException
	{
		Image image = new Image("data/" + folder + "/" + filename);
		
		return image;
	}
	
	/**
	 * Moves the sprite slowly up
	 * @param yChange The max amount of pixels the sprite can move up on the y-access
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
	 * @param yChange The max amount of pixels the sprite can move down on the y-access
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
	 * @param xChange The max amount of pixels the sprite can move left on the x-access
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
	 * @param xChange The max amount of pixels the sprite can move right on the x-access
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
	 * @param yChange The max amount of pixels the sprite can move on the y-access
	 */
	public void animateUpDown(int yChange)
	{
		animateUp(yChange);
		animateDown(yChange);
	}
	
	/**
	 * Moves the sprite slowly left and right
	 * @param xChange The max amount of pixels the sprite can move on the x-access
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
	 * @param g The graphics to draw the sprite on
	 */
	public void drawSprite(Graphics g)
	{
		g.drawImage(image, x, y);
	}
	
	/**
	 * Draws the collision box and trigger box (if they exist)
	 * @param g The graphics to draw the collision/trigger box on
	 */
	public void drawBoxes(Graphics g) {
		if(collisionbox != null) {
			collisionbox.drawBox(g, x, y);
		}
		
		if(triggerbox != null) {
			triggerbox.drawBox(g, x, y);
		}
	}
	
	/**
	 * Fades out a sprite
	 * @param duration The time it takes to fade out
	 */
	public void fadeOut(int duration) {
		float alpha = image.getAlpha();
		float alphaStep = 1f / duration;
		
		if(alpha > 0) {
				
			alpha -= alphaStep;
			
			image.setAlpha(alpha);
		}
	}
	
	/**
	 * Fades in a sprite
	 * @param duration The time it takes to fade in in ms
	 */
	public void fadeIn(int duration) {
		float alpha = image.getAlpha();
		float alphaStep = 1f / duration;
		
		if(alpha < 1) {
				
			alpha += alphaStep;
			
			image.setAlpha(alpha);
		}
	}
}
