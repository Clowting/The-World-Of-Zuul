package nl.corebooster.setup;

import java.util.LinkedHashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 * Describes the player with all his functions in the game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Player {

	private CollisionBox collisionbox;
	private SpriteSheet spritesheet;
	private Animation spritesheetAnimation;
	private int x, y;
	private int movementSpeed;
	private int angle;
	private static final int playerSize = 64;
	private Sound footstep;
	
	public Player(int x, int y) throws SlickException
	{
		this.spritesheet = getSpriteSheet("sprites", "player_up.png", playerSize, playerSize);
		spritesheetAnimation = new Animation(spritesheet, 100);
		
		collisionbox = new CollisionBox(x, y, playerSize, playerSize);
		
		this.x = x;
		this.y = y;
		
		movementSpeed = 5;
		angle = 0;
		
		footstep = new Sound("data/soundeffects/FootstepIce.ogg");
	}
	
	/**
	 * Get collision box.
	 */
	public CollisionBox getCollisionBox() {
		return collisionbox;
	}
	
	/**
	 * Returns the spritesheet
	 */
	public SpriteSheet getSpriteSheet()
	{
		return spritesheet;
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
	 * Returns the movementSpeed
	 * @return
	 */
	public int getMovementSpeed()
	{
		return movementSpeed;
	}
	
	/**
	 * Returns the rotation angle
	 * @return
	 */
	public int getRotation()
	{
		return angle;
	}
	
	/**
	 * Sets a new spritesheet for the player
	 */
	public void setSpriteSheet(SpriteSheet spritesheet)
	{
		
		this.spritesheet = spritesheet;
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
	 * Sets the movement speed
	 * @param movementSpeed
	 */
	public void setMovementSpeed(int movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}
	
	/**
	 * Sets a new rotation angle
	 * @param angle
	 */
	public void setRotation(int angle)
	{
		this.angle = angle;
	}
	
	/**
	 * Sets a new rotation angle and refreshes the animation
	 * @param angle
	 */
	public void rotateAndRefresh(int angle)
	{
		if(this.angle != angle) {
			setRotation(angle);
			refreshAnimation();
		}
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
	 * Returns true if the player can move up
	 * @param maxValue
	 * @return
	 */
	public boolean canMoveUp(int limit)
	{
		if(y - movementSpeed > limit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if the player can move down
	 * @param maxValue
	 * @return
	 */
	public boolean canMoveDown(int limit)
	{
		if(y + playerSize + movementSpeed < limit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if the player can move right
	 * @param maxValue
	 * @return
	 */
	public boolean canMoveRight(int limit)
	{
		if(x + playerSize + movementSpeed < limit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if the player van move left
	 * @param limit
	 * @return
	 */
	public boolean canMoveLeft(int limit)
	{
		if(x - movementSpeed > limit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void moveUp() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_up.png", playerSize, playerSize));
		
		rotateAndRefresh(0);
		
		startAnimation();
		y -= movementSpeed;
	}
	
	public void moveDown() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_down.png", playerSize, playerSize));
		
		rotateAndRefresh(180);
		
		startAnimation();
		y += movementSpeed;
	}
	
	public void moveRight() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_right.png", playerSize, playerSize));
		
		rotateAndRefresh(90);
		
		startAnimation();
		x += movementSpeed;
	}
	
	public void moveLeft() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_left.png", playerSize, playerSize));
		
		rotateAndRefresh(270);
		
		startAnimation();
		x -= movementSpeed;
	}	
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		g.drawAnimation(spritesheetAnimation, x, y);
	}
	
	/**
	 * Draw collision box.
	 */
	public void drawCollisionBox(Graphics g) 
	{
		collisionbox.drawBox(g, x, y);
	}
	
	/**
	 * Checks if the player collides with another collision box
	 */
	public boolean isCollidingWith(LinkedHashMap<String, Object> sprites)
	{
		for(Object o : sprites.values()) {
			if(o instanceof Sprite) {
				Sprite s = (Sprite) o;
				CollisionBox spriteCollisionbox = s.getCollisionBox();
				
				if(spriteCollisionbox != null) {
					boolean isColliding = spriteCollisionbox.isColliding(collisionbox.getShape());
					
					if(isColliding) {
						return true;
					}
				}
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				CollisionBox spriteCollisionbox = as.getCollisionBox();
				
				if(spriteCollisionbox != null) {
					boolean isColliding = spriteCollisionbox.isColliding(collisionbox.getShape());
					
					if(isColliding) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the TriggerBox when a player is touching a trigger box, null if not triggering
	 * @param sprites
	 * @return
	 */
	public TriggerBox getCurrentTriggerBox(LinkedHashMap<String, Object> sprites)
	{
		for(Object o : sprites.values()) {
			if(o instanceof Sprite) {
				Sprite s = (Sprite) o;
				TriggerBox spriteTriggerbox = s.getTriggerBox();
				
				if(spriteTriggerbox != null) {
					boolean isTriggering = spriteTriggerbox.isTriggering(collisionbox.getShape());
					
					if(isTriggering) {
						return spriteTriggerbox;
					}
				}
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				TriggerBox spriteTriggerbox = as.getTriggerBox();
				
				if(spriteTriggerbox != null) {
					boolean isTriggering = spriteTriggerbox.isTriggering(collisionbox.getShape());
					
					if(isTriggering) {
						return spriteTriggerbox;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Starts the animation
	 */
	public void startAnimation()
	{
		spritesheetAnimation.start();
	}
	
	/**
	 * Refreshes the animation
	 */
	public void refreshAnimation()
	{
		spritesheetAnimation = new Animation(spritesheet, 100);
	}
	
	/**
	 * Stops the animation
	 */
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
		spritesheetAnimation.setCurrentFrame(0);
	}
	
	/**
	 * Plays footstep sound
	 */
	public void playFootstepSound() {
		footstep.loop(1f, 0.1f);
	}
	
	/**
	 * Stops playing footstep sound
	 */
	public void stopFootstepSound() {
		if(footstep.playing()) {
			footstep.stop();
		}
	}
}
