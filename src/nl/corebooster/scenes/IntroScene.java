package nl.corebooster.scenes;

import java.util.LinkedHashMap;
import java.util.Random;

import nl.corebooster.setup.Sprite;
import nl.corebooster.setup.StarBackground;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes the intro scene
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class IntroScene {
	
	private LinkedHashMap<String, Sprite> sprites;
	
	private Sprite background;
	private StarBackground stars;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public IntroScene() throws SlickException
	{
		sprites = new LinkedHashMap<String, Sprite>();
		
		stars = new StarBackground(screenWidth, screenHeight);
		displayIntro();
	}
	
	/**
	 * Initializes a intro scene
	 * @throws SlickException 
	 */
	private void displayIntro() throws SlickException
	{
		// Set background
		background = new Sprite("img", "intro_background.png", 0, -1080);
		
		// Make sprite objects
		Sprite spaceship = new Sprite("sprites", "spaceship.png", 466, 220);
		
		sprites.put("spaceship", spaceship);
	}
		
	/**
	 * Animates all elements in the scene
	 */
	public void animate()
	{
		background.animateVertical(screenHeight * 3);
		stars.animateStars();
		
		// Move spaceship
		sprites.get("spaceship").animateVertical(100);
		//sprites.get("spaceship").animateHorizontal(randInt(50, 250));
	}

	/**
	 * Renders the scene
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
