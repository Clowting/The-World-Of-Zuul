package nl.corebooster.setup;

import nl.corebooster.setup.TriggerBox.TriggerType;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Describes an animated tile from the game with an X and Y position
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class AnimatedSprite {

	private CollisionBox collisionbox;
	private TriggerBox triggerbox;
	private SpriteSheet spritesheet;
	private Animation spritesheetAnimation;
	private int x, y;
	
	/**
	 * Constructs a new animated sprite from a spritesheet
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param isCollidable Depends if you can collide with the animated sprite or not, true/false
	 * @param x The initial x position of the animated sprite
	 * @param y The initial y position of the animated sprite
	 * @param width The width of the animated sprite
	 * @param height The height of the animated sprite
	 * @param interval The animation interval
	 * @throws SlickException
	 */
	public AnimatedSprite(String folder, String filename, boolean isCollidable, int x, int y, int width, int height, int interval) throws SlickException
	{
		this.spritesheet = getSpriteSheet(folder, filename, width, height);
		spritesheetAnimation = new Animation(spritesheet, interval);
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, width, height);
		}
		else {
			collisionbox = null;
		}
		
		this.triggerbox = null;
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a new animated sprite from a spritesheet with a trigger
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param isCollidable Depends if you can collide with the animated sprite or not, true/false
	 * @param triggerType The type of trigger called when collided, SCENESWITCH/MESSAGE
	 * @param triggerValue The value of the trigger called when collided
	 * @param triggerMargin The margin of the trigger box
	 * @param x The initial x position of the animated sprite
	 * @param y The initial y position of the animated sprite
	 * @param width The width of the animated sprite
	 * @param height The height of the animated sprite
	 * @param interval The animation interval
	 * @throws SlickException
	 */
	public AnimatedSprite(String folder, String filename, boolean isCollidable, TriggerType triggerType, String triggerValue, int triggerMargin, int x, int y, int width, int height, int interval) throws SlickException
	{
		this.spritesheet = getSpriteSheet(folder, filename, width, height);
		spritesheetAnimation = new Animation(spritesheet, interval);
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, width, height);
		}
		else {
			collisionbox = null;
		}
		
		this.triggerbox = new TriggerBox(triggerType, triggerValue, x, y, width, height, triggerMargin);
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the collision box.
	 * @return The collision box of the animated sprite
	 */
	public CollisionBox getCollisionBox() 
	{
		return collisionbox;
	}
	
	/**
	 * Get the trigger box
	 * @return The trigger box of the animated sprite
	 */
	public TriggerBox getTriggerBox()
	{
		return triggerbox;
	}
	
	/**
	 * Returns the x-position
	 * @return The x-position of the animated sprite
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the y-position
	 * @return The y-position of the animated sprite
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Sets the x-position
	 * @param x The new x-position of the animated sprite
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y-position
	 * @param y The new y-position of the animated sprite
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Gets a spritesheet image from the given location
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param width The width of the animated sprite that can be created out of the spritesheet
	 * @param height The height of the animated sprite that can be created out of the spritesheet
	 * @return The newly created spritesheet
	 * @throws SlickException
	 */
	public SpriteSheet getSpriteSheet(String folder, String filename, int width, int height) throws SlickException
	{
		SpriteSheet spritesheet = new SpriteSheet("data/" + folder + "/" + filename, width, height);
		
		return spritesheet;
	}
	
	/**
	 * Draws the sprite on the screen
	 * @param g The graphics to draw the animated sprite on
	 */
	public void drawSprite(Graphics g)
	{
		g.drawAnimation(spritesheetAnimation, x, y);
	}
	
	/**
	 * Draws the collision box
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
	 * Stops the animation of the animated sprite
	 */
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
	}
}
