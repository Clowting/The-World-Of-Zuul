package nl.corebooster.scenes;

import java.util.LinkedHashMap;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Player;
import nl.corebooster.setup.Sprite;
import nl.corebooster.setup.TriggerBox;
import nl.corebooster.setup.TriggerBox.TriggerType;

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
	
	private String sceneName;
	private String nextScene;
	private boolean isActive, isRendered;
	
	private Music bgMusic;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public GameScene(String sceneName) throws SlickException
	{
		// Initialize objects
		sprites = new LinkedHashMap<String, Object>();
		
		player = new Player(screenWidth / 2, screenHeight / 2);
		
		this.sceneName = sceneName;
		nextScene = null;
		
		isActive = false;
		isRendered = false;
		
		bgMusic = null;
		
		// A switch for making different scenes
		switch(sceneName) {
			// "Ice"-scene: first scene after the intro
			case "ice":
				
				background = new Sprite("img", "background1.png", false, 0, 0);
				sprites.put("ice_cliff", new Sprite("sprites", "ice_cliff.png", true, 0, 0));
				
				sprites.put("landingpad", new AnimatedSprite("sprites", "landingpad.png", true, TriggerType.SCENESWITCH, "ice2", 50, 110, 384, 384, 1000));
				sprites.put("spaceship", new Sprite("sprites", "spaceship_big.png", false, 104, 162));
				
			break;
			
			// Test Scene
			case "ice2":
				
				background = new Sprite("img", "background1.png", false, 0, 0);
				
			break;
		}
	}
	
	/**
	 * Gets a sprite from the sprites
	 */
	public Sprite getSprite(String key)
	{
		return (Sprite) sprites.get(key);
	}
	
	/**
	 * Returns the name of the scene
	 */
	public String getSceneName()
	{
		return sceneName;
	}
	
	/**
	 * Returns the name of the next scene
	 */
	public String getNextScene()
	{
		return nextScene;
	}
	
	/**
	 * Sets the current trigger
	 */
	
	/**
	 * Returns true if intro has ended
	 * @return
	 */
	public boolean isActive()
	{
		return isActive;
	}
	
	/**
	 * Sets the name of the scene
	 */
	public void setSceneName(String sceneName)
	{
		this.sceneName = sceneName;
	}
	
	/**
	 * Sets the name of the next scene
	 */
	public void setNextScene(String scene)
	{
		nextScene = scene;
	}
	
	/**
	 * Returns true if currentScene has been rendered
	 * @return
	 */
	public boolean isRendered()
	{
		return isRendered;
	}
	
	/**
	 * Makes a scene active
	 */
	public void setActive()
	{
		isActive = true;
	}
	
	/**
	 * Makes a scene inactive
	 */
	public void setInactive()
	{
		isActive = false;
	}
	
	/**
	 * Sets isRendered to true when a scene is rendered
	 */
	public void setRendered()
	{
		isRendered = true;
	}
	
	/**
	 * Sets isRendered to false when a scene is not rendered
	 */
	public void setUnrendered()
	{
		isRendered = false;
	}
	
	/**
	 * Resets the next scene
	 */
	public void resetNextScene()
	{
		nextScene = null;
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
				
				s.drawBoxes(g);
			}
			else if(o instanceof AnimatedSprite) {
				AnimatedSprite as = (AnimatedSprite) o;
				
				as.drawBoxes(g);
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
		// Checks if the player is colliding with a sprite
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
		} 
		else {
			if(player.getRotation() == 270) {
				player.setX(player.getX() + 1);
			} 
			else if(player.getRotation() == 90) {
				player.setX(player.getX() - 1);
			} 
			else if(player.getRotation() == 0) {
				player.setY(player.getY() + 1);
			} 
			else if(player.getRotation() == 180) {
				player.setY(player.getY() - 1);
			}
		}
		
		// Checks if the player is triggering an event
		TriggerBox currentTriggerBox = player.getCurrentTriggerBox(sprites);
		
		if(currentTriggerBox != null && currentTriggerBox.isTriggered() == false) {
			switch(currentTriggerBox.getTriggerType()) {
				case SCENESWITCH:
					nextScene = currentTriggerBox.getValue();
					currentTriggerBox.resetTrigger();
				break;
				
				case MESSAGE:
					// Display an in-game message
				break;
			}
		}
	}
}
