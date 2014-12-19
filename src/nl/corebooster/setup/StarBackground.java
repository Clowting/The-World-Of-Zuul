package nl.corebooster.setup;

import java.util.HashSet;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes a moving star in the universe
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class StarBackground {
	
	private HashSet<Sprite> stars;
	private int screenWidth, screenHeight;
	
	/**
	 * Initializes a StarBackground with the given screen size
	 * @param screenWidth
	 * @param screenHeight
	 * @throws SlickException
	 */
	public StarBackground(int screenWidth, int screenHeight) throws SlickException
	{
		stars = new HashSet<Sprite>();
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		addSprite("star.png", 50);
	}
	
	/**
	 * Generate an amount of stars
	 * @throws SlickException
	 */
	private void addSprite(String filename, int amount) throws SlickException
	{
		Random rand = new Random();
		
		for(int i = 0; i < amount; i++) {
			int x = rand.nextInt(screenWidth);
			int y = rand.nextInt(screenHeight);
			
			Sprite sprite = new Sprite("sprites", filename, x, y);
			stars.add(sprite);
		}
	}
	
	/**
	 * Animate all the stars
	 */
	public void animateStars()
	{
		Random rand = new Random();
		
		// For every star
		for(Sprite s : stars) {
			int x = rand.nextInt(screenWidth);
			int y = s.getY();
			
			if(y >= screenHeight) {
				s.setX(x);
				s.setY(0);
			}
			else {
				s.setY(y + 5);
			}
		}
	}
	
	/**
	 * Draw every star to the graphics
	 * @param g
	 */
	public void drawStars(Graphics g)
	{
		for(Sprite s : stars) {
			g.drawImage(s.getImage(), s.getX(), s.getY());
		}
	}
	
}
