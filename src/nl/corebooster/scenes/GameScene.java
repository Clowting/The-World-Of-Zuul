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
	private AnimatedSprite overlay;
	
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
		
		// Rainbow overlay
		overlay = new AnimatedSprite("overlay", "sprites", "spacecake_effect.png", false, 0, 0, 960, 540, 50);
		overlay.setAlpha(0);
		overlay.stopAnimation();
		
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
	public AnimatedSprite getOverlay()
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
				
				sprites.put("switch_right", new Sprite("switch_right", "img", "vertical_line_transparent.png", false, TriggerType.SCENESWITCH, 90, "outside_headquarters", 0, 955, 0));
	
				sprites.put("ice_cliff", new Sprite("ice_cliff", "sprites", "ice_cliff.png", true, 0, 0));
				sprites.put("landingpad", new AnimatedSprite("landingpad", "sprites", "landingpad.png", true, TriggerType.MESSAGE, -1, "This is a test message", 10, 50, 80, 384, 384, 1000));
				sprites.put("spaceship", new Sprite("spaceship", "sprites", "spaceship_big.png", false, 104, 132));
				
				items.put("headquarters_entrance_key", new Item("headquarters_entrance_key", "Key to the HQ", ItemType.KEY, "key_hq_icon.png", "key_hq.png", 700, 80));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
	
			// "Outside headquarters"-scene
			case "outside_headquarters":
	
				background = new Sprite("background", "img", "background2.png", false, 0, 0);
	
				sprites.put("switch_left", new Sprite("switch_left", "img", "vertical_line_transparent.png", false, TriggerType.SCENESWITCH, 270, "ice", 0, 0, 0));
				sprites.put("switch_bottom", new Sprite("switch_bottom", "img", "horizontal_line_transparent.png", false, TriggerType.SCENESWITCH, 180, "drill", 0, 0, 535));
				
				sprites.put("bush", new Sprite("bush", "sprites", "bush.png", true, 50, 75));
				sprites.put("headquarters", new Sprite("headquarters", "sprites", "headquarters.png", true, 600, 0));
				sprites.put("headquarters_entrance_light", new Sprite("headquarters_entrance_light", "sprites", "headquarters_entrance_light.png", false, 440, 160));
				sprites.put("headquarters_entrance", new Sprite("headquarters_entrance", "sprites", "headquarters_entrance.png", true, TriggerType.LOCKEDSCENESWITCH, 90, "headquarters", 0, 500, 160));
				
				items.put("cake", new Item("cake", "Very Tasty Spacecake", ItemType.SPACECAKE, "cake_icon.png", "cake.png", 200, 50));
				
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
	
			// "Inside headquarters"-scene
			case "headquarters":
	
				background = new Sprite("background", "img", "headquarters_background.png", false, 0, 0);
	
				sprites.put("headquarters_exit", new Sprite("headquarters_exit", "sprites", "headquarters_exit.png", true, TriggerType.SCENESWITCH, 270, "outside_headquarters", 0, 0, 160));
				sprites.put("cpanel", new AnimatedSprite("cpanel", "sprites", "cpanel.png", true, 185, 0, 685, 90, 200));
				//sprites.put("trapdoor_scenetrigger", new Sprite("img", "trapdoor_transparent.png", true, TriggerType.SCENESWITCH, "outside_headquarters", 1, 10, 10));
				sprites.put("trapdoor", new AnimatedSprite("trapdoor", "sprites", "trapdoor.png", false, TriggerType.ANIMATE, -1, "trapdoor", 6, 10, 10, 90, 90, 50));
				sprites.put("liquid_transporter", new AnimatedSprite("liquid_transporter", "sprites", "liquid_transporter.png", true, 860, 210, 70, 210, 100));
				sprites.put("radar", new AnimatedSprite("radar", "sprites", "radar.png", true, 425, 240, 95, 95, 150));
				sprites.put("npc_1", new Sprite("npc_1", "sprites", "npc_red_up.png", true, TriggerType.MESSAGE, -1, "Welcome! I'm glad you're here.\nWe have a problem at the construction site. Do you mind taking a look?", 10, 438, 430));
	
				bgMusicName = "GameSong01.ogg";
				bgMusicVolume = 0.05f;
	
			break;
			
			// "Drill"-scene
			case "drill":
				
				background = new Sprite("background", "img", "background3.png", false, 0, 0);
				
				sprites.put("switch_top", new Sprite("switch_top", "img", "horizontal_line_transparent.png", false, TriggerType.SCENESWITCH, 0, "outside_headquarters", 0, 0, 0));
				
				sprites.put("middlecore", new AnimatedSprite("middlecore", "sprites", "middlecore.png", true, 352, 240, 64, 64, 500));
				sprites.put("conveyer_1", new AnimatedSprite("conveyer_1", "sprites", "conveyer.png", true, 416, 240, 64, 64, 250));
				sprites.put("conveyer_2", new AnimatedSprite("conveyer_2", "sprites", "conveyer.png", true, 480, 240, 64, 64, 250));
				sprites.put("conveyer_3", new AnimatedSprite("conveyer_3", "sprites", "conveyer.png", true, 544, 240, 64, 64, 250));
				sprites.put("rocks", new AnimatedSprite("rocks", "sprites", "rocks.png", false, 416, 240, 192, 64, 250));
				sprites.put("rocks_2", new AnimatedSprite("rocks", "sprites", "rocks_2.png", false, 416, 240, 192, 64, 250));
				sprites.put("burner", new AnimatedSprite("burner", "sprites", "burner.png", true, 608, 240, 64, 64, 100));
				
				sprites.put("npc_1", new Sprite("npc_1", "sprites", "npc_red_up.png", true, TriggerType.MESSAGE, -1, "Good to see you here. The drill has stopped working!\nCan you ask around to see what parts the workers need?", 10, 438, 430));
				sprites.put("npc_2", new Sprite("npc_2", "sprites", "npc_yellow_left.png", true, TriggerType.MESSAGE, -1, "Niks", 10, 900, 240));
				
				
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
	 * Handles user input
	 * @param input The input key
	 * @throws SlickException Indicates a failure to initialize the display 
	 */
	public void keyHandler(Input input) throws SlickException
	{
		// Checks if the player is colliding with a sprite
		boolean isColliding = player.isCollidingWith(sprites);
		
		if(!isColliding) {
			// Move player
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
			
			// Calls the inventory key handler
			// Only called when player is not colliding with a sprite
			inventoryKeyHandler(input);
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
	 * Handles the inventory keys
	 * @param input The input key
	 */
	private void inventoryKeyHandler(Input input)
	{
		if(input.isKeyDown(Input.KEY_1)) {
			inventory.setSelectedSlot(0);
		} 
		else if(input.isKeyDown(Input.KEY_2)) {
			inventory.setSelectedSlot(1);
		} 
		else if(input.isKeyDown(Input.KEY_3)) {
			inventory.setSelectedSlot(2);
		} 
		else if(input.isKeyDown(Input.KEY_4)) {
			inventory.setSelectedSlot(3);
		}
		
		if(input.isKeyPressed(Input.KEY_D)) {
			dropItem();
		} else if(input.isKeyPressed(Input.KEY_SPACE)) {
			useItem();
		}
	}
	
	/**
	 * Drops the selected item
	 */
	private void dropItem() {
		Item selectedItem = inventory.getSelectedItem();
		
		if(selectedItem != null) {
			// Player info and drop distance
			int x = player.getX();
			int y = player.getY();
			int playerSize = player.getPlayerSize();
			int rotation = player.getRotation();
			int dropDistance = 20;
			
			System.out.println("Player X: " + x);
			System.out.println("Player Y: " + y);
			
			// Item info
			String selectedItemName = selectedItem.getKeyValue();
			int itemWidth = selectedItem.getItemWidth();
			int itemHeight = selectedItem.getItemHeight();
			
			// Calculate the drop position
			switch(rotation) {
				case 0:
					y -= (itemHeight + dropDistance);
				break;
				
				case 90:
					x += (playerSize + dropDistance);
				break;
				
				case 180:
					y += (playerSize + dropDistance);
				break;
				
				case 270:
					x -= (dropDistance + itemWidth);
				break;
			}
			
			System.out.println("Item X: " + x);
			System.out.println("Item Y: " + y);
			
			// Update item position
			if(x > 0 && y > 0 && x < screenWidth && y < screenHeight) {
				inventory.deleteSelectedItem();
				selectedItem.moveItem(x, y);
				items.put(selectedItemName, selectedItem);
			}
			else {
				inventory.setCurrentMessage("Can't drop this item here!");
			}
		}
	}
	
	/**
	 * Allows you to use an item
	 */
	private void useItem()
	{
		Item selectedItem = inventory.getSelectedItem();
		
		if(selectedItem != null) {
		
			switch(selectedItem.getItemType()) {
			
				case SPACECAKE:
					
					if(overlay.isStopped()) {
						overlay.startAnimation();
						overlay.setAlpha(255);
					}
					else {
						overlay.setAlpha(0);
						overlay.stopAnimation();
					}
					
				break;
			
				default:
					
				break;
				
			}
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
		if(currentTriggerBox != null) {
			
			String itemName = currentTriggerBox.getObjectName();
			
			switch(currentTriggerBox.getTriggerType()) {
				case SCENESWITCH:
					
					if(player.getRotation() == currentTriggerBox.getTriggerDirection()) {
						nextScene = currentTriggerBox.getValue();
						currentTriggerBox.resetTrigger();
					}
					
					
				break;
				
				case LOCKEDSCENESWITCH:
					
					if(player.getRotation() == currentTriggerBox.getTriggerDirection()) {
						String keyName = itemName + "_key";
						 
						if(inventory.hasItemSelected(keyName) || currentTriggerBox.isTriggered()) {
							nextScene = currentTriggerBox.getValue();
							currentTriggerBox.setTriggered();
							 
							// Remove key from inventory
							inventory.deleteItem(keyName);
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
					
					currentTriggerBox.setTriggered();
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
		if(overlay != null) {
			overlay.drawSprite(g);
		}
	}
	
}
