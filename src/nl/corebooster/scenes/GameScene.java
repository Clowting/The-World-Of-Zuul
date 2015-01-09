package nl.corebooster.scenes;

import java.util.LinkedHashMap;

import nl.corebooster.setup.AnimatedSprite;
import nl.corebooster.setup.Inventory;
import nl.corebooster.setup.Item;
import nl.corebooster.setup.Item.ItemType;
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
	private LinkedHashMap<String, Item> items;
	
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	private Sprite background;
	private Player player;
	private Inventory inventory;
	private Sprite overlay;
	
	private String sceneName;
	private String nextScene;
	private boolean isActive, isRendered;
	
	private Music bgMusic;
	private String bgMusicName;
	private float bgMusicVolume;
	
	/**
	 * Initializes the scene
	 * @param sceneName The name of the scene
	 * @param playerX The players initial x-position
	 * @param playerY The players initial y-position
	 * @throws SlickException Indicates a failure to initialize the display Indicates a failure to initialize the display
	 */
	public GameScene(String sceneName, int playerX, int playerY) throws SlickException
	{
		// Initialize objects
		sprites = new LinkedHashMap<String, Object>();
		items = new LinkedHashMap<String, Item>();
		player = new Player(playerX, playerY);
		inventory = new Inventory();
		
		// Black overlay for fade in and fade out
		overlay = new Sprite("overlay", "img", "overlay.png", false, 0, 0);
		overlay.getImage().setAlpha(0);
		
		this.sceneName = sceneName;
		nextScene = null;
		
		isActive = false;
		isRendered = false;
		
		bgMusic = null;
		
		// Initialize scene
		initializeScene(sceneName);
	}
	
	/**
	 * Returns the player object of the game scene
	 * @return The scenes player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Returns the inventory from the game scene
	 * @return The inventory
	 */
	public Inventory getInventory()
	{
		return inventory;
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
	 * @param key The key of the sprite to get
	 * @return The Sprite objects from the sprites LinkedHashMap
	 */
	public Sprite getSprite(String key)
	{
		return (Sprite) sprites.get(key);
	}
	
	/**
	 * Returns the name of the scene
	 * @return The name of the scene
	 */
	public String getSceneName()
	{
		return sceneName;
	}
	
	/**
	 * Returns the name of the next scene
	 * @return The name of the next scene
	 */
	public String getNextScene()
	{
		return nextScene;
	}
	
	/**
	 * Returns true if intro has ended
	 * @return Whether or not the intro scene has ended, true/false
	 */
	public boolean isActive()
	{
		return isActive;
	}
	
	/**
	 * Replaces the inventory with the given inventory
	 * @param inventory The new inventory
	 */
	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	/**
	 * Sets the name of the scene
	 * @param sceneName The new scene name
	 */
	public void setSceneName(String sceneName)
	{
		this.sceneName = sceneName;
	}
	
	/**
	 * Sets the name of the next scene
	 * @param scene The name of the next scene
	 */
	public void setNextScene(String scene)
	{
		nextScene = scene;
	}
	
	/**
	 * Returns true if currentScene has been rendered
	 * @return Whether or not the current scene has been rendered, true/false
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
	 * Initialize a game scene based on the name, with a given music file and volume
	 * @throws SlickException 
	 */
	private void initializeScene(String sceneName) throws SlickException
	{
		// A switch for making different scenes
		switch(sceneName) {
			// "Ice"-scene: first scene after the intro
			case "ice":
	
				background = new Sprite("background", "img", "background1.png", false, 0, 0);
	
				sprites.put("ice_cliff", new Sprite("ice_cliff", "sprites", "ice_cliff.png", true, 0, 0));
				sprites.put("landingpad", new AnimatedSprite("landingpad", "sprites", "landingpad.png", true, TriggerType.MESSAGE, "This is a test message", 10, 50, 80, 384, 384, 1000));
				sprites.put("spaceship", new Sprite("spaceship", "sprites", "spaceship_big.png", false, 104, 132));
				sprites.put("trigger_right", new Sprite("trigger_right", "img", "vertical_line_transparent.png", true, TriggerType.SCENESWITCH, "outside_headquarters", 1, 960, 0));
				
				items.put("headquarters_entrance_key", new Item("headquarters_entrance_key", "Key to the HQ", ItemType.KEY, "key_hq_icon.png", "key_hq.png", 700, 80));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
	
			// "Outside headquarters"-scene
			case "outside_headquarters":
	
				background = new Sprite("background", "img", "background2.png", false, 0, 0);
	
				sprites.put("bush", new Sprite("bush", "sprites", "bush.png", false, 50, 75));
				sprites.put("headquarters", new Sprite("headquarters", "sprites", "headquarters.png", true, 600, 0));
				sprites.put("headquarters_entrance_light", new Sprite("headquarters_entrance_light", "sprites", "headquarters_entrance_light.png", false, 440, 160));
				sprites.put("headquarters_entrance", new Sprite("headquarters_entrance", "sprites", "headquarters_entrance.png", true, TriggerType.LOCKEDSCENESWITCH, "headquarters", 5, 500, 160));
				sprites.put("trigger_left", new Sprite("trigger_left", "img", "vertical_line_transparent.png", true, TriggerType.SCENESWITCH, "ice", 1, 0, 0));
				
				items.put("cake", new Item("cake", "Very Tasty Spacecake", ItemType.SUPPLY, "cake_icon.png", "cake.png", 200, 50));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
	
			// "Inside headquarters"-scene
			case "headquarters":
	
				background = new Sprite("background", "img", "headquarters_background.png", false, 0, 0);
	
				sprites.put("cpanel", new AnimatedSprite("cpanel", "sprites", "cpanel.png", true, 185, 0, 685, 90, 200));
				//sprites.put("trapdoor_scenetrigger", new Sprite("img", "trapdoor_transparent.png", true, TriggerType.SCENESWITCH, "outside_headquarters", 1, 10, 10));
				sprites.put("trapdoor", new AnimatedSprite("trapdoor", "sprites", "trapdoor.png", false, TriggerType.ANIMATE, "trapdoor", 6, 10, 10, 90, 90, 50));
				sprites.put("liquid_transporter", new AnimatedSprite("liquid_transporter", "sprites", "liquid_transporter.png", true, 860, 210, 70, 210, 100));
				sprites.put("radar", new AnimatedSprite("radar", "sprites", "radar.png", true, 425, 240, 95, 95, 150));
				sprites.put("npc_1", new Sprite("npc_1", "sprites", "npc_red_up.png", true, TriggerType.MESSAGE, "You touch my tralala!", 10, 438, 430));
	
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
		}
		
		bgMusic = new Music("data/music/" + bgMusicName);
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
	 * @throws SlickException Indicates a failure to initialize the display
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
	 * @param g The graphics to draw the scene on
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void render(Graphics g) throws SlickException
	{
		// Draws collision boxes
		for(Object object : sprites.values()) {
			if(object instanceof Sprite) {
				Sprite sprite = (Sprite) object;
				
				sprite.drawBoxes(g);
			}
			else if(object instanceof AnimatedSprite) {
				AnimatedSprite animatedSprite = (AnimatedSprite) object;
				
				animatedSprite.drawBoxes(g);
			}
		}
		
		// Draw item collision boxes
		for(Item item : items.values()) {
			Sprite sprite = item.getSprite();
			
			sprite.drawBoxes(g);
		}
		
		// Draws the player collision box and background
		player.drawCollisionBox(g);
		background.drawSprite(g);
		
		// Draws the sprites.
		for(Object object : sprites.values()) {
			if(object instanceof Sprite) {
				Sprite sprite = (Sprite) object;
				
				sprite.drawSprite(g);
			}
			else if(object instanceof AnimatedSprite) {
				AnimatedSprite animatedSprite = (AnimatedSprite) object;
				
				animatedSprite.drawSprite(g);
			}
		}
		
		// Draw items
		for(Item item : items.values()) {
			Sprite sprite = item.getSprite();
			
			sprite.drawSprite(g);
		}		
		
		// Draw the player
		player.drawSprite(g);
		
		// Draw the inventory
		inventory.render(g);
		
		// Draw the overlay
		overlay.drawSprite(g);
	}
	
	/**
	 * Handles user input
	 * @param input The input key
	 * @throws SlickException Indicates a failure to initialize the display 
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
		
		// Calls the inventory key handler
		inventoryKeyHandler(input);
	}
	
	/**
	 * Handles the inventory keys
	 * @param input The input key
	 */
	private void inventoryKeyHandler(Input input)
	{
		if(input.isKeyDown(Input.KEY_1)) {
			inventory.setSelectedSlot(0);
		} else if(input.isKeyDown(Input.KEY_2)) {
			inventory.setSelectedSlot(1);
		} else if(input.isKeyDown(Input.KEY_3)) {
			inventory.setSelectedSlot(2);
		} else if(input.isKeyDown(Input.KEY_4)) {
			inventory.setSelectedSlot(3);
		}
	}
	
	/**
	 * Drops an item with a specific index
	 * @param index The index of the item to be dropped
	 */
	private void dropItem(int index) {
		if(inventory.itemExists(index)) {
			Item currentItem = inventory.getItem(index);
			String currentItemName = currentItem.getKeyValue();
			inventory.deleteItem(index);
			
			int x = player.getX();
			int y = player.getY();
			int rotation = player.getRotation();
			int itemWidth = currentItem.getSprite().getImage().getWidth();
			int itemOffset = 75;
			
			if(rotation == 0) {
				y -= itemOffset;
			} else if(rotation == 90) {
				x += itemOffset;
			} else if(rotation == 180) {
				y += itemOffset;
			} else if(rotation == 270) {
				x -= (itemOffset - itemWidth);
			}
			
			currentItem.getSprite().setX(x);
			currentItem.getSprite().setY(y);
			items.put(currentItemName, currentItem);
		}
	}
	
	/**
	 * Checks if the player is triggering an event
	 */
	public void triggerHandler() 
	{
		TriggerBox currentTriggerBoxSprites = player.getCurrentTriggerBox(sprites);
		TriggerBox currentTriggerBoxItems = player.getCurrentItemTriggerBox(items);
		
		triggerBoxHandler(currentTriggerBoxSprites);
		triggerBoxHandler(currentTriggerBoxItems);
	}
	
	/**
	 * Handles all the types of trigger boxes
	 * @param currentTriggerBox The trigger box to check
	 */
	private void triggerBoxHandler(TriggerBox currentTriggerBox)
	{
		if(currentTriggerBox != null && currentTriggerBox.isTriggered() == false) {
			
			String itemName = currentTriggerBox.getObjectName();
			
			switch(currentTriggerBox.getTriggerType()) {
				case SCENESWITCH:
					
					nextScene = currentTriggerBox.getValue();
					currentTriggerBox.resetTrigger();
					
				break;
				
				case LOCKEDSCENESWITCH:
					 
					String keyName = itemName + "_key";
					 
					if(inventory.hasItemSelected(keyName) || currentTriggerBox.isTriggered()) {
						nextScene = currentTriggerBox.getValue();
						currentTriggerBox.setTriggered();
						 
						// Remove key from inventory
						inventory.removeItem(keyName);
					}
					else {
						Item selectedItem = inventory.getSelectedItem();
						
						if(selectedItem != null) {
							inventory.setCurrentMessage("You can't open this door with a " + selectedItem.getItemName() + "!");
						}
						else {
							inventory.setCurrentMessage("You don't have the required key for this door!");
						}
					}
					 
					 
					 break;
				
				case MESSAGE:
					
					inventory.setCurrentMessage(currentTriggerBox.getValue());
					
				break;
				
				case ANIMATE:
					
					AnimatedSprite animatedSprite = (AnimatedSprite) sprites.get(itemName);
					
					animatedSprite.playAnimationOnce();
					
					currentTriggerBox.resetTrigger();
					
				break;
				
				case ITEM:
					
					Item item = items.get(itemName);
					items.remove(itemName);
					
					inventory.addItem(item);
					inventory.setCurrentMessage(currentTriggerBox.getValue());
					
				break;
				
				default:
					// Do nothing
				break;
				
			}
		}
	}
	
}
