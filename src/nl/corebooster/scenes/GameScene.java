package nl.corebooster.scenes;

import java.util.LinkedHashMap;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Player;
import nl.corebooster.setup.Sprite;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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
		
		background = new Sprite("img", "background1.png", 0, 0);
		
		sprites.put("landingpad", new AnimatedSprite("sprites", "landingpad.png", 50, 50, 384, 384, 1000));
		
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
	 * Returns true if intro has ended
	 * @return
	 */
	public boolean hasEnded()
	{
		return hasEnded;
	}
	
	/**
	 * Returns true if currentScene has been rendered
	 * @return
	 */
	public boolean hasRendered()
	{
		return hasRendered;
	}
	
	/**
	 * Set hasRendered to true
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
		// Call methods on objects to animate
	}

	/**
	 * Renders the scene
	 * @param graphics
	 */
	public void render(Graphics g) 
	{
		// Draws collision boxes
		for(Object o : sprites.values()) {
			if(o instanceof Sprite) {
				Sprite s = (Sprite) o;
				s.drawCollisionBox(g);
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				as.drawCollisionBox(g);
			}
		}
		
		// Draws the player + collision box and background
		player.drawCollisionBox(g);
		background.drawSprite(g);
		player.drawSprite(g);
		
		// Draws the sprites.
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
		Object objectBoxToCheck = sprites.get("landingpad");
		AnimatedSprite as = (AnimatedSprite) objectBoxToCheck;
		Rectangle boxToCheck = player.getCollisionBox().getShape();
		boolean isColliding = as.getCollisionBox().isColliding(boxToCheck);
		
		if(!isColliding) {
			if(input.isKeyDown(Input.KEY_LEFT)) {
				player.moveLeft();
			}
			
			else if(input.isKeyDown(Input.KEY_RIGHT)) {
				player.moveRight();
			}
			
			else if(input.isKeyDown(Input.KEY_UP)) {
				player.moveUp();
			}
			
			else if(input.isKeyDown(Input.KEY_DOWN)) {
				player.moveDown();
			}
			
			else {
				player.stopAnimation();
			}
		} else {
			if(player.getRotation() == 270) {
				player.setX(player.getX() + 1);
			} else if(player.getRotation() == 90) {
				player.setX(player.getX() - 1);
			} else if(player.getRotation() == 0) {
				player.setY(player.getY() + 1);
			} else if(player.getRotation() == 180) {
				player.setY(player.getY() - 1);
			}
		}
	}
}
