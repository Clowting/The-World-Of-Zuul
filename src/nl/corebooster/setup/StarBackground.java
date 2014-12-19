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
	
	public StarBackground(int screenWidth, int screenHeight) throws SlickException
	{
		stars = new HashSet<Sprite>();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		addStars();
	}
	
	private void addStars() throws SlickException
	{
		Random rand = new Random();
		
		for(int i = 0; i < 50; i++) {
			int x = rand.nextInt(screenWidth);
			int y = rand.nextInt(screenHeight);
			
			stars.add(new Sprite("sprites", "star.png", x, y));
		}
	}
	
	public void animateStars()
	{
		Random rand = new Random();
		
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
	
	public void drawStars(Graphics g)
	{
		for(Sprite s : stars) {
			g.drawImage(s.getImage(), s.getX(), s.getY());
		}
	}
	
}
