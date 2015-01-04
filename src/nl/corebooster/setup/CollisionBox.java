package nl.corebooster.setup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * This class draws a collision box to the screen
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class CollisionBox {

	private Rectangle box;
	
	/**
	 * Initializes a new collision box
	 * @param x The initial x position of the collision box
	 * @param y The initial y position of the collision box
	 * @param width The width of the collision box
	 * @param height The height of the collision box
	 */
	public CollisionBox(int x, int y, int width, int height) 
	{
		box = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Returns the rectangle of the collision box
	 * @return The rectangle shape of the collision box
	 */
	public Rectangle getShape() 
	{
		return box;
	}
	
	/**
	 * Draws the box on the given graphics
	 * @param g The graphics to draw the collision box on
	 * @param x The new x-position of the collision box
	 * @param y The new y-position of the collision box
	 */
	public void drawBox(Graphics g, int x, int y) 
	{
		box.setX(x);
		box.setY(y);
		g.setColor(null);
		g.draw(box);
	}
	
	/**
	 * Returns true if the ColissionBox is colliding with another ColissionBox
	 * @param boxToCheck The shape to check collision with
	 * @return Whether or not the two shapes collide, true/false
	 */
	public boolean isColliding(Rectangle boxToCheck) 
	{
		
		if(box.intersects(boxToCheck)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
