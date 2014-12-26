package nl.corebooster.scenes;

import java.util.LinkedHashMap;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Player;
import nl.corebooster.setup.Sprite;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Describes a game scene with the standard features
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class GameScene {
	
	private LinkedHashMap<String, Object> sprites;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	private Sprite background;
	private Player player;
	
	private boolean hasEnded, hasRendered;
	
	private Music bgMusic;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public GameScene() throws SlickException
	{
		sprites = new LinkedHashMap<String, Object>();
		
		background = new Sprite("img", "background1.png", false, 0, 0);
		sprites.put("ice_cliff", new Sprite("sprites", "ice_cliff.png", true, 0, 0));
		
		sprites.put("landingpad", new AnimatedSprite("sprites", "landingpad.png", true, 50, 75, 384, 384, 1000));
		sprites.put("spaceship", new Sprite("sprites", "spaceship_big.png", false, 104, 137));
		
		player = new Player(screenWidth / 2, screenHeight / 2);
		
		hasEnded = false;
		hasRendered = false;
		
		bgMusic = null;
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
	
	public void playMusic(String filename, float volume) throws SlickException {
		bgMusic = new Music("data/music/" + filename);
		bgMusic.loop(1f, volume);
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
				
				if(s.getCollisionBox() != null) {
					s.drawCollisionBox(g);
				}
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				
				if(as.getCollisionBox() != null) {
					as.drawCollisionBox(g);
				}
			}
		}
		
		// Draws the player collision box and background
		player.drawCollisionBox(g);
		background.drawSprite(g);
		
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
		
		// Draw the player
		player.drawSprite(g);
	}
	
	/**
	 * Handles user input
	 * @param input
	 * @throws SlickException 
	 */
	public void keyHandler(Input input) throws SlickException
	{
		boolean isColliding = player.isCollidingWith(sprites);
		
		if(!isColliding) {
			if(input.isKeyDown(Input.KEY_LEFT)) {
				player.moveLeft();
				
				if(input.isKeyPressed(Input.KEY_LEFT)) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_RIGHT)) {
				player.moveRight();
				
				if(input.isKeyPressed(Input.KEY_RIGHT)) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_UP)) {
				player.moveUp();
				
				if(input.isKeyPressed(Input.KEY_UP)) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_DOWN)) {
				player.moveDown();
				
				if(input.isKeyPressed(Input.KEY_DOWN)) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else {
				player.stopAnimation();
				player.stopFootstepSound();
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
