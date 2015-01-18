package nl.corebooster.scenes;

import java.util.LinkedHashMap;
import java.util.Random;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Sprite;
import nl.corebooster.setup.StarBackground;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Describes the end scene
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class EndScene {
	
	private LinkedHashMap<String, Object> sprites;
	
	private Sprite background;
	private StarBackground stars;
	
	private Music bgMusic;
			
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	/**
	 * Initializes the scene
	 * @throws SlickException Indicates a failure to initialize the display 
	 */
	public EndScene() throws SlickException
	{
		sprites = new LinkedHashMap<String, Object>();
		stars = new StarBackground(screenWidth, screenHeight);
		
		// Set background
		background = new Sprite("background", "img", "intro_background.png", false, 0, -2160);
		
		// Set title and 'press space to start'
		Sprite title = new Sprite("title", "img", "theend.png", false, 228, 150);
		title.getImage().setAlpha(0);
		sprites.put("title", title);
		
		// Make sprite objects
		Sprite spaceship = new Sprite("spaceship", "sprites", "spaceship.png", false, 416, 210);
		sprites.put("spaceship", spaceship);
		
		// Stars playing background music
		bgMusic = new Music("data/music/IntroSong.ogg");
		bgMusic.loop(1f, 0.1f);
		
	}
	
	/**
	 * Gets a sprite from the sprites
	 * @param key The key of the sprite to get
	 * @return The Sprite objects from the sprites LinkedHashMap
	 */
	private Sprite getSprite(String key)
	{
		return (Sprite) sprites.get(key);
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
		getSprite("spaceship").animateDown(540);
		getSprite("spaceship").animateLeftRight(randInt(50, 250));
		
		if(getSprite("spaceship").getY() >= screenHeight) {
			getSprite("title").fadeIn(100);
		}
	}

	/**
	 * Renders the scene
	 * @param g The graphics to draw the intro scene on
	 */
	public void render(Graphics g) 
	{
		background.drawSprite(g);
		stars.drawStars(g);
		
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
