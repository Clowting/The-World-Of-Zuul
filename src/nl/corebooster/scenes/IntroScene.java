package nl.corebooster.scenes;

import java.util.LinkedHashMap;
import java.util.Random;

import nl.corebooster.setup.AnimatedSprite;
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
	
	private LinkedHashMap<String, Object> sprites;
	
	private Sprite background, title;
	private AnimatedSprite start;
	private StarBackground stars;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public IntroScene() throws SlickException
	{
		sprites = new LinkedHashMap<String, Object>();
		stars = new StarBackground(screenWidth, screenHeight);
		
		// Set background
		background = new Sprite("img", "intro_background.png", 0, -2160);
		
		// Set title and 'press space to start'
		title = new Sprite("img", "title.png", 153, 50);
		start = new AnimatedSprite("sprites", "press_space.png", 166, 470, 627, 29, 200);
		
		// Make sprite objects
		Sprite spaceship = new Sprite("sprites", "spaceship.png", 416, 210);
		
		sprites.put("spaceship", spaceship);
	}
	
	/**
	 * Gets a sprite from the sprites
	 */
	public Sprite getSprite(String key)
	{
		return (Sprite) sprites.get(key);
	}
	
	/**
	 * Gets an animated sprite from the sprites
	 */
	public AnimatedSprite getAnimatedSprite(String key)
	{
		return (AnimatedSprite) sprites.get(key);
	}
		
	/**
	 * Animates all elements in the scene
	 */
	public void animate()
	{
		background.animateDown(screenHeight * 4);
		
		if(background.hasMaxYReached()) {
			background.resetY();
		}
		
		stars.animateStars();
		
		// Move spaceship
		getSprite("spaceship").animateUpDown(randInt(50, 150));
		getSprite("spaceship").animateLeftRight(randInt(50, 250));
	}

	/**
	 * Renders the scene
	 * @param graphics
	 */
	public void render(Graphics g) 
	{
		background.drawSprite(g);
		stars.drawStars(g);
		title.drawSprite(g);
		start.drawSprite(g);
		
		for(Object o : sprites.values()) {
			if(o instanceof Sprite) {
				Sprite s = (Sprite) o;
				s.drawSprite(g);
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				as.drawSprite(g);
			}
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
