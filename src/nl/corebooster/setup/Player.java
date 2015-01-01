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
	private static final int movementSpeed = 5;
	private int angle;
	private static final int playerSize = 64;
	private Sound footstep;
	
	/**
	 * Constructs a player at a given position.
	 * @param x The players x position
	 * @param y The players y position
	 * @throws SlickException
	 */
	public Player(int x, int y) throws SlickException
	{
		this.spritesheet = getSpriteSheet("sprites", "player_up.png", playerSize, playerSize);
		spritesheetAnimation = new Animation(spritesheet, 100);
		
		collisionbox = new CollisionBox(x, y, playerSize, playerSize);
		
		this.x = x;
		this.y = y;
		
		angle = 0;
		
		footstep = new Sound("data/soundeffects/FootstepIce.ogg");
	}
	
	/**
	 * Get collision box
	 * @return The players collision box
	 */
	public CollisionBox getCollisionBox() {
		return collisionbox;
	}
	
	/**
	 * Returns the spritesheet
	 * @return The spritesheet the player is based on
	 */
	public SpriteSheet getSpriteSheet()
	{
		return spritesheet;
	}
	
	/**
	 * Returns the X-position
	 * @return The players x position
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the Y-position
	 * @return The players y position
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Returns the rotation angle
	 * @return The angle the player is on
	 */
	public int getRotation()
	{
		return angle;
	}
	
	/**
	 * Sets a new spritesheet for the player
	 * @param spritesheet The players new spritesheet
	 */
	public void setSpriteSheet(SpriteSheet spritesheet)
	{
		this.spritesheet = spritesheet;
	}
	
	/**
	 * Sets the X-position
	 * @param x The player's new x position
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the Y-position
	 * @param y The player's new y position
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Sets a new rotation angle
	 * @param angle The player's new angle
	 */
	public void setRotation(int angle)
	{
		this.angle = angle;
	}
	
	/**
	 * Rotates the player to the given angle
	 * @param angle The player's new angle
	 * @throws SlickException 
	 */
	public void rotatePlayer(int angle) throws SlickException
	{
		switch(angle) {
			case 0:
				moveUp();
			break;
			
			case 180:
				moveDown();
			break;
			
			case 270:
				moveLeft();
			break;
			
			case 90:
				moveRight();
			break;
		}
	}
	
	/**
	 * Sets a new rotation angle and refreshes the animation
	 * @param angle The player's new angle
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
	 * @param folder The folder to look for the spritesheet
	 * @param filename The filename of the spritesheet
	 * @param width The with of the sprite
	 * @param height The height of the sprite
	 * @return The spritesheet that has been made
	 * @throws SlickException
	 */
	public SpriteSheet getSpriteSheet(String folder, String filename, int width, int height) throws SlickException
	{
		SpriteSheet spritesheet = new SpriteSheet("data/" + folder + "/" + filename, width, height);
		
		return spritesheet;
	}
	
	/**
	 * Returns true if the player can move up
	 * @param limit The vertical movement top limit
	 * @return If the limit has been reached, true or false
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
	 * @param limit The vertical movement bottom limit 
	 * @return If the limit has been reached, true or false
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
	 * @param limit The horizontal movement right limit 
	 * @return If the limit has been reached, true or false
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
	 * @param limit The horizontal movement left limit 
	 * @return If the limit has been reached, true or false
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
	
	/**
	 * Moves the player up
	 * @throws SlickException
	 */
	public void moveUp() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_up.png", playerSize, playerSize));
		
		rotateAndRefresh(0);
		
		startAnimation();
		y -= movementSpeed;
	}
	
	/**
	 * Moves the player down
	 * @throws SlickException
	 */
	public void moveDown() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_down.png", playerSize, playerSize));
		
		rotateAndRefresh(180);
		
		startAnimation();
		y += movementSpeed;
	}
	
	/**
	 * Moves the player right
	 * @throws SlickException
	 */
	public void moveRight() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_right.png", playerSize, playerSize));
		
		rotateAndRefresh(90);
		
		startAnimation();
		x += movementSpeed;
	}
	
	/**
	 * Moves the player left
	 * @throws SlickException
	 */
	public void moveLeft() throws SlickException
	{
		setSpriteSheet(getSpriteSheet("sprites", "player_left.png", playerSize, playerSize));
		
		rotateAndRefresh(270);
		
		startAnimation();
		x -= movementSpeed;
	}	
	
	/**
	 * Draws the sprite on the screen
	 * @param g The graphics to draw on
	 */
	public void drawSprite(Graphics g)
	{
		g.drawAnimation(spritesheetAnimation, x, y);
	}
	
	/**
	 * Draw collision box.
	 * @param g The graphics to draw on
	 */
	public void drawCollisionBox(Graphics g) 
	{
		collisionbox.drawBox(g, x, y);
	}
	
	/**
	 * Checks if the player collides with another collision box
	 * @param sprites The list of sprites to check collision with
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
	 * @param sprites The list of sprites to check triggers with
	 * @return The trigger box that is triggered
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
