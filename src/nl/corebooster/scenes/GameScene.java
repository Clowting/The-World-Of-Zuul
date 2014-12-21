package nl.corebooster.scenes;

import java.util.LinkedHashMap;
import java.util.Random;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Player;
import nl.corebooster.setup.Sprite;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameScene {
	private Player player;
	
	private LinkedHashMap<String, Object> sprites;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	private boolean hasEnded, hasRendered;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public GameScene() throws SlickException
	{
		player = new Player(screenWidth/2, screenHeight/2);
		
		sprites = new LinkedHashMap<String, Object>();
		
		hasEnded = false;
		hasRendered = false;
	}
	
	/**
	 * Gets a sprite from the sprites
	 */
	public Sprite getSprite(String key)
	{
		return (Sprite) sprites.get(key);
	}
	
	/**
	 * Returns if intro has ended
	 * @return
	 */
	public boolean hasEnded()
	{
		return hasEnded;
	}
	
	/**
	 * Returns if currentScene has been rendered
	 * @return
	 */
	public boolean hasRendered()
	{
		return hasRendered;
	}
	
	/**
	 * set hasRendered to true
	 */
	public void setRendered()
	{
		hasRendered = true;
	}
		
	/**
	 * Animates all elements in the scene
	 */
	public void animate()
	{
	}

	/**
	 * Renders the scene
	 * @param graphics
	 */
	public void render(Graphics g) 
	{
		//Objects to be rendered go here
		player.drawSprite(g);
		
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
	
	/**
	 * Handles user input
	 * @param input
	 */
	public void keyHandler(Input input)
	{
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			player.moveLeft();
		}
		else if(input.isKeyDown(Input.KEY_RIGHT))
		{
			player.moveRight();
		}
		else if(input.isKeyDown(Input.KEY_UP))
		{
			player.moveUp();
		}
		else if(input.isKeyDown(Input.KEY_DOWN))
		{
			player.moveDown();
		}
		else
		{
			player.stopAnimation();
		}
	}
	
	public void kaas()
	{
		if(player.getX() > screenWidth)
		{
			player.setX(-64);
		}
		if(player.getX() < -64)
		{
			player.setX(screenWidth);
		}
		if(player.getY() < -64)
		{
			player.setY(screenHeight);
		}
		if(player.getY() > screenHeight)
		{
			player.setY(-64);
		}
	}
}
