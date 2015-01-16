package nl.corebooster.setup;

import nl.corebooster.setup.TriggerBox.TriggerType;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Describes an animated tile from the game with an X and Y position
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class AnimatedSprite {

	private String spriteName;
	private CollisionBox collisionbox;
	private TriggerBox triggerbox;
	private SpriteSheet spritesheet;
	private Animation spritesheetAnimation;
	private int x, y, width, height;
	private int alpha;
	
	/**
	 * Constructs a new animated sprite from a spritesheet
	 * @param spriteName The name of the sprite
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param isCollidable Depends if you can collide with the animated sprite or not, true/false
	 * @param x The initial x position of the animated sprite
	 * @param y The initial y position of the animated sprite
	 * @param width The width of the animated sprite
	 * @param height The height of the animated sprite
	 * @param interval The animation interval
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public AnimatedSprite(String spriteName, String folder, String filename, boolean isCollidable, int x, int y, int width, int height, int interval) throws SlickException
	{
		this.spriteName = spriteName;
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
		this.width = width;
		this.height = height;
		alpha = 255;
	}
	
	/**
	 * Constructs a new animated sprite from a spritesheet with a trigger
	 * @param spriteName The name of the sprite
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param isCollidable Depends if you can collide with the animated sprite or not, true/false
	 * @param triggerType The type of trigger called when collided, SCENESWITCH/MESSAGE/ANIMATE
	 * @param triggerDirection The direction the player has to approach from for the trigger to work
	 * @param alternateX The x position to use if the normal position is blocked
	 * @param alternateY The y position to use if the normal position is blocked
	 * @param triggerValue The value of the trigger called when collided
	 * @param triggerMargin The margin of the trigger box
	 * @param x The initial x position of the animated sprite
	 * @param y The initial y position of the animated sprite
	 * @param width The width of the animated sprite
	 * @param height The height of the animated sprite
	 * @param interval The animation interval
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public AnimatedSprite(String spriteName, String folder, String filename, boolean isCollidable, TriggerType triggerType, int triggerDirection, int alternateX, int alternateY, String triggerValue, int triggerMargin, int x, int y, int width, int height, int interval) throws SlickException
	{
		this.spriteName = spriteName;
		this.spritesheet = getSpriteSheet(folder, filename, width, height);
		spritesheetAnimation = new Animation(spritesheet, interval);
		
		if(isCollidable) {
			collisionbox = new CollisionBox(x, y, width, height);
		}
		else {
			collisionbox = null;
		}
		
		this.triggerbox = new TriggerBox(spriteName, triggerType, triggerDirection, alternateX, alternateY, triggerValue, x, y, width, height, triggerMargin);
		
		if(triggerType == TriggerType.TRAPDOOR) {
			stopAnimation();
		}
		
		this.x = x;
		this.y = y;
		alpha = 255;
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
	 * Get Animation
	 * @return The Animation
	 */
	public Animation getAnimation() {
		return spritesheetAnimation;
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
	 * Returns the width of the sprite
	 * @return The width of the sprite
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns the height of the sprite
	 * @return The height of the sprite
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Returns the alpha value of the animation
	 * @return The alpha value
	 */
	public float getAlpha()
	{
		return alpha;
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
	 * Updates the alpha of the sprite to the given alpha float value
	 * @param alpha The new alpha value
	 */
	public void setAlpha(int alpha)
	{
		if(alpha >= 0 && alpha <= 255) {
			this.alpha = alpha;
		}
	}
	
	/**
	 * Gets a spritesheet image from the given location
	 * @param folder The folder where the spritesheet is located
	 * @param filename The filename of the spritesheet
	 * @param width The width of the animated sprite that can be created out of the spritesheet
	 * @param height The height of the animated sprite that can be created out of the spritesheet
	 * @return The newly created spritesheet
	 * @throws SlickException Indicates a failure to initialize the display
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
		Color alphaFilter = new Color(255, 255, 255, alpha);
			
		g.drawAnimation(spritesheetAnimation, x, y, alphaFilter);
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
	 * Starts the animation of the animated sprite
	 */
	public void startAnimation()
	{
		spritesheetAnimation.start();
	}
	
	/**
	 * Stops the animation of the animated sprite
	 */
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
	}
	
	/**
	 * Resets the animation to the first frame
	 */
	public void resetAnimation()
	{
		spritesheetAnimation.setCurrentFrame(0);
	}
	
	/**
	 * Checks if the animation is running
	 * @return False when running, True when stopped
	 */
	public boolean isStopped() {
		return spritesheetAnimation.isStopped();
	}
	
	/**
	 * Plays through animation cycle once
	 */
	public void playAnimationOnce() {
		spritesheetAnimation.setPingPong(true);
		spritesheetAnimation.stopAt(spritesheetAnimation.getFrameCount() - 1);
		spritesheetAnimation.start();
	}
}
