package nl.corebooster.setup;

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
	private SpriteSheet spritesheet;
	private Animation spritesheetAnimation;
	private int x, y;
	
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
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get collision box.
	 */
	public CollisionBox getCollisionBox() {
		return collisionbox;
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
	public void drawCollisionBox(Graphics g) {
		collisionbox.drawBox(g, x, y);
	}
	
	public void stopAnimation()
	{
		spritesheetAnimation.stop();
	}
}
