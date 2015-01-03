package nl.corebooster.scenes;

import java.util.LinkedHashMap;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Player;
import nl.corebooster.setup.Sprite;
import nl.corebooster.setup.TriggerBox;
import nl.corebooster.setup.TriggerBox.TriggerType;

import org.newdawn.slick.Color;
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
	private Sprite messageBox;
	private Player player;
	private Sprite overlay;
	
	private String sceneName;
	private String nextScene;
	private boolean isActive, isRendered;
	
	private Music bgMusic;
	private String bgMusicName;
	private float bgMusicVolume;
	
	/**
	 * Initializes the scene
	 * @throws SlickException 
	 */
	public GameScene(String sceneName, int playerX, int playerY) throws SlickException
	{
		// Initialize objects
		sprites = new LinkedHashMap<String, Object>();
		
		player = new Player(playerX, playerY);
		
		// Black overlay for fade in and fade out
		overlay = new Sprite("img", "overlay.png", false, 0, 0);
		overlay.getImage().setAlpha(0);
		
		this.sceneName = sceneName;
		nextScene = null;
		
		isActive = false;
		isRendered = false;
		
		bgMusic = null;
		messageBox = new Sprite("img", "message_box.png", false, 5, 484);
		
		// A switch for making different scenes
		switch(sceneName) {
			// "Ice"-scene: first scene after the intro
			case "ice":
				
				background = new Sprite("img", "background1.png", false, 0, 0);
				
				sprites.put("ice_cliff", new Sprite("sprites", "ice_cliff.png", true, 0, 0));
				sprites.put("landingpad", new AnimatedSprite("sprites", "landingpad.png", true, 50, 80, 384, 384, 1000));
				sprites.put("spaceship", new Sprite("sprites", "spaceship_big.png", false, 104, 132));
				sprites.put("trigger_right", new Sprite("img", "vertical_line_transparent.png", true, TriggerType.SCENESWITCH, "outside_headquarters", 1, 960, 0));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
				bgMusic = new Music("data/music/" + bgMusicName);
				
			break;
			
			// "Outside headquarters"-scene
			case "outside_headquarters":
				
				background = new Sprite("img", "background1.png", false, 0, 0);
				
				sprites.put("headquarters", new Sprite("sprites", "headquarters.png", true, 600, 0));
				sprites.put("headquarters_entrance_light", new Sprite("sprites", "headquarters_entrance_light.png", false, 440, 160));
				sprites.put("headquarters_entrance", new Sprite("sprites", "headquarters_entrance.png", false, TriggerType.SCENESWITCH, "headquarters", 1, 500, 160));
				sprites.put("trigger_left", new Sprite("img", "vertical_line_transparent.png", true, TriggerType.SCENESWITCH, "ice", 1, 0, 0));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
				bgMusic = new Music("data/music/" + bgMusicName);
				
			break;
			
			// "Inside headquarters"-scene
			case "headquarters":
				
				background = new Sprite("img", "headquarters_background.png", false, 0, 0);
				
				sprites.put("table", new Sprite("sprites", "table.png", true, 290, 170));
				sprites.put("trapdoor", new Sprite("sprites", "trapdoor.png", false, 0, 0));
				sprites.put("plant", new Sprite("sprites", "plant.png", true, 850, 10));
				sprites.put("npc1", new Sprite("sprites", "npc_left.png", true, TriggerType.MESSAGE, "Thank you for playing our game!", 40, 896, 206));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
				bgMusic = new Music("data/music/" + bgMusicName);
				
			break;
		}
	}
	
	/**
	 * Returns the player object of the game scene
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Returns the overlay
	 * @return The sprite object of the overlay
	 */
	public Sprite getOverlay()
	{
		return overlay;
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
	
	/**
	 * Plays the given music in the background
	 * @param filename
	 * @param volume
	 * @throws SlickException
	 */
	public void playMusic() throws SlickException {
		if(bgMusicName != null) {
			bgMusic.loop(1f, bgMusicVolume);
		}

	}
	
	/**
	 * Stops all the sounds playing
	 */
	public void stopAllSounds()
	{
		if(bgMusic != null) {
			bgMusic.stop();
		}
		
		player.stopFootstepSound();
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
		
		// Draw message
		messageBox.drawSprite(g);
		if(currentTriggerMessage() != null) {
			renderText(g, currentTriggerMessage(), 15, 500);
		}		
		
		// Draw the overlay
		overlay.drawSprite(g);
		
	}
	
	/**
	 * Renders text to the screen
	 * @param g The graphics to draw the text on
	 * @param text The text to draw on the screen
	 * @param x The x position of the text
	 * @param y The y position of the text
	 */
	private void renderText(Graphics g, String text, int x, int y) {
		g.setColor(new Color(Color.black));
		String[] characterArray = text.split("");
		
		for(String character : characterArray) {
			g.drawString(character, x, y);
			x = x + 9;
		}
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
				if(player.canMoveLeft(0)) {
					player.moveLeft();
				}
				
				if(!player.isFootstepSoundPlaying()) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_RIGHT)) {
				if(player.canMoveRight(screenWidth)) {
					player.moveRight();
				}
				
				if(!player.isFootstepSoundPlaying()) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_UP)) {
				if(player.canMoveUp(0)) {
					player.moveUp();
				}
				
				if(!player.isFootstepSoundPlaying()) {
					player.stopFootstepSound();
					player.playFootstepSound();
				}
			}
			
			else if(input.isKeyDown(Input.KEY_DOWN)) {
				if(player.canMoveDown(screenHeight)) {
					player.moveDown();
				}
				
				if(!player.isFootstepSoundPlaying()) {
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
	}
	
	/**
	 * Checks if the player is triggering an event
	 */
	public void triggerHandler() 
	{
		TriggerBox currentTriggerBox = player.getCurrentTriggerBox(sprites);
		
		if(currentTriggerBox != null && currentTriggerBox.isTriggered() == false) {
			switch(currentTriggerBox.getTriggerType()) {
				case SCENESWITCH:
					nextScene = currentTriggerBox.getValue();
					currentTriggerBox.resetTrigger();
				break;
				default:
					// Do nothing
				break;
			}
		}
	}
	
	/**
	 * Returns a trigger message if the player is triggering a sprite
	 * @return The message
	 */
	public String currentTriggerMessage() 
	{
		TriggerBox currentTriggerBox = player.getCurrentTriggerBox(sprites);
		
		if(currentTriggerBox != null && currentTriggerBox.isTriggered() == false) {
			if(currentTriggerBox.getTriggerType() == TriggerType.MESSAGE) {
				return currentTriggerBox.getValue();
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
}
