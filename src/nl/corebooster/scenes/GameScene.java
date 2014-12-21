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
	
	private LinkedHashMap<String, Object> sprites;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	private Sprite background;
	private Player player;
	
	private boolean hasEnded, hasRendered;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public GameScene() throws SlickException
	{
		sprites = new LinkedHashMap<String, Object>();
		
		background = new Sprite("img", "intro_background.png", 0, 0);
		player = new Player(screenWidth / 2, screenHeight / 2);
		
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
		background.drawSprite(g);
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
	 * Handles user input
	 * @param input
	 * @throws SlickException 
	 */
	public void keyHandler(Input input) throws SlickException
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
}
