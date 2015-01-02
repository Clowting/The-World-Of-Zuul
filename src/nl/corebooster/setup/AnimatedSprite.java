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
	 * @param folder
	 * @param filename
	 * @param isCollidable
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param interval
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
	 * @param folder
	 * @param filename
	 * @param isCollidable
	 * @param triggerType
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param interval
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
	 */
	public CollisionBox getCollisionBox() 
	{
		return collisionbox;
	}
	
	/**
	 * Get the triggerbox
	 */
	public TriggerBox getTriggerBox()
	{
		return triggerbox;
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
	 * Gets a spritesheet image from the given location
	 * @param folder
	 * @param filename
	 * @param width
	 * @param height
	 * @return
	 * @throws SlickException
	 */
	public SpriteSheet getSpriteSheet(String folder, String filename, int width, int height) throws SlickException
	{
		SpriteSheet spritesheet = new SpriteSheet("data/" + folder + "/" + filename, width, height);
		
		return spritesheet;
	}
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawAnimation(spritesheetAnimation, x, y);
	}
	
	/**
	 * Draws the collision box
	 */
	public void drawBoxes(Graphics g) {
		if(collisionbox != null) {
			collisionbox.drawBox(g, x, y);
		}
		
		if(triggerbox != null) {
			triggerbox.drawBox(g, x, y);
		}
	}
	
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
	}
}
