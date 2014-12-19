package nl.corebooster.setup;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes the World of Zuul game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Game {
	
	private LinkedHashMap<String, Sprite> sprites;
	
	private Sprite background;
	private StarBackground stars;
	
	private boolean yWasTouched, pointYReached, xWasTouched, pointXReached;
	private int y, x;
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	/**
	 * Initializes the game
	 * @throws SlickException 
	 */
	public Game() throws SlickException
	{
		sprites = new LinkedHashMap<String, Sprite>();
		yWasTouched = false;
		pointYReached = false;
		xWasTouched = false;
		pointXReached = false;
		y = 0;
		x = 0;
		
		stars = new StarBackground(screenWidth, screenHeight);
		displayIntro();
	}
	
	/**
	 * Starts the game with the start screen
	 * @throws SlickException 
	 */
	private void displayIntro() throws SlickException
	{
		// Set background
		background = new Sprite("img", "intro_background.png", 0, 0);
		
		// Make sprite objects
		Sprite spaceship = new Sprite("sprites", "spaceship.png", 466, 220);
		
		sprites.put("spaceship", spaceship);
	}
	
	/**
	 * Moves a sprite slowly up and down
	 */
	private void animateVertical(Sprite s, int yChange)
	{
		if(!yWasTouched) {
			y = s.getY();
			yWasTouched = true;
		}
		
		if(!pointYReached && s.getY() > y - yChange) {
			s.setY(s.getY() - 1);
		}
		else if(s.getY() < y) {
			pointYReached = true;
			s.setY(s.getY() + 1);
		}
		else {
			pointYReached = false;
		}
	}
	
	/**
	 * Moves a sprite slowly left and right
	 */
	private void animateHorizontal(Sprite s, int xChange)
	{
		if(!xWasTouched) {
			x = s.getX();
			xWasTouched = true;
		}
		
		if(!pointXReached && s.getX() > x - xChange) {
			s.setX(s.getX() - 1);
		}
		else if(s.getX() < x) {
			pointXReached = true;
			s.setX(s.getX() + 1);
		}
		else {
			pointXReached = false;
		}
	}
		
	/**
	 * Animates all elements in the game
	 */
	public void animate()
	{
		stars.animateStars();
		
		animateVertical(sprites.get("spaceship"), randInt(25, 100));
		animateHorizontal(sprites.get("spaceship"), randInt(50, 250));
	}

	/**
	 * Renders the game
	 * @param graphics
	 */
	public void render(Graphics g) 
	{
		background.drawSprite(g);
		stars.drawStars(g);
		
		for(Sprite s : sprites.values()) {
			s.drawSprite(g);
		}
	}
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	private static int randInt(int min, int max) 
	{

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
