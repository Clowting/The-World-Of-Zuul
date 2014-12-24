package nl.corebooster.setup;

import java.util.LinkedHashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
	
	public Player(int x, int y) throws SlickException
	{
		this.spritesheet = getSpriteSheet("sprites", "player_up.png", playerSize, playerSize);
		spritesheetAnimation = new Animation(spritesheet, 100);
		
		collisionbox = new CollisionBox(x, y, playerSize, playerSize);
		
		this.x = x;
		this.y = y;
		
		movementSpeed = 5;
		angle = 0;
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
		boolean isColliding = false;
		
		for(Object o : sprites.values()) {
			if(o instanceof Sprite) {
				Sprite s = (Sprite) o;
				CollisionBox spriteCollisionbox = s.getCollisionBox();
				
				if(spriteCollisionbox != null) {
					isColliding = spriteCollisionbox.isColliding(collisionbox.getShape());
				}
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				CollisionBox spriteCollisionbox = as.getCollisionBox();
				
				if(spriteCollisionbox != null) {
					isColliding = spriteCollisionbox.isColliding(collisionbox.getShape());
				}
			}
		}
		
		return isColliding;
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
}
