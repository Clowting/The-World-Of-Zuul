package nl.corebooster.setup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * This class draws a collision box to the screen
 * @author Thijs Clowting
 * @version 1.0
 */
public class CollisionBox {

	private Rectangle box;
	
	public CollisionBox(int x, int y, int width, int height) {
		box = new Rectangle(x, y, width, height);
	}
	
	public void drawBox(Graphics g, int x, int y) {
		box.setX(x);
		box.setY(y);
		g.draw(box);
	}
	
	public Rectangle getShape() {
		return box;
	}
	
	public boolean isColliding(Rectangle boxToCheck) {
		
		if(box.intersects(boxToCheck)) {
			return true;
		} else {
			return false;
		}
	}
	
}
