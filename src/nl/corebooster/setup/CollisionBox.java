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
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public CollisionBox(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Draws the box on the given graphics
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawBox(Graphics g, int x, int y) {
		box.setX(x);
		box.setY(y);
		g.setColor(null);
		g.draw(box);
	}
	
	/**
	 * Returns the rectangle of the collision box
	 * @return
	 */
	public Rectangle getShape() {
		return box;
	}
	
	/**
	 * Returns true if the ColissionBox is colliding with another ColissionBox
	 * @param boxToCheck
	 * @return
	 */
	public boolean isColliding(Rectangle boxToCheck) {
		
		if(box.intersects(boxToCheck)) {
			return true;
		} else {
			return false;
		}
	}
	
}
