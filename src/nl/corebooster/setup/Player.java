package nl.corebooster.setup;

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

	private SpriteSheet spritesheet;
	private Animation spritesheetAnimation;
	private int x, y;
	private int movementSpeed;
	
	public Player(int x, int y) throws SlickException
	{
		this.spritesheet = getSpriteSheet("sprites", "player.png", 64, 64);
		spritesheetAnimation = new Animation(spritesheet, 100);
		
		spritesheet.setCenterOfRotation(32, 32);
		
		this.x = x;
		this.y = y;
		
		movementSpeed = 3;
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
	 * Retruns the movementSpeed
	 * @return
	 */
	public int getMovementSpeed()
	{
		return movementSpeed;
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
	
	public void moveUp()
	{
		spritesheetAnimation.getCurrentFrame().setRotation(0);
		startAnimation();
		y -= movementSpeed;
	}
	
	public void moveDown()
	{
		spritesheetAnimation.getCurrentFrame().setRotation(180);
		startAnimation();
		y += movementSpeed;
	}
	
	public void moveRight()
	{
		spritesheetAnimation.getCurrentFrame().setRotation(90);
		startAnimation();
		x += movementSpeed;
	}
	
	public void moveLeft()
	{
		spritesheetAnimation.getCurrentFrame().setRotation(270);
		startAnimation();
		x -= movementSpeed;
	}
	
	
	/**
	 * Draws the sprite on the screen
	 */
	public void drawSprite(Graphics g)
	{
		//g.drawAnimation(spritesheetAnimation, x, y);
		g.drawImage(spritesheetAnimation.getCurrentFrame(), x, y);
	}
	
	public void startAnimation()
	{
		spritesheetAnimation.update(100);
	}
	
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
		spritesheetAnimation.setCurrentFrame(0);
	}
}
