package nl.corebooster.setup;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes the inventory system used in the game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Inventory {

	private Sprite background;
	private String currentMessage;
	private static final int maxItemCount = 4;
	private ArrayList<Item> items;
	
	/**
	 * Initializes a new inventory
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public Inventory() throws SlickException
	{
		background = new Sprite("inventory", "img", "inventory.png", false, 0, 480);
		currentMessage = "Thanks for playing our game!";
		items = new ArrayList<Item>();
	}
	
	/**
	 * Returns the current message displayed in the inventory bar
	 * @return The current message
	 */
	public String getCurrentMessage()
	{
		return currentMessage;
	}
	
	/**
	 * Returns the ArrayList with items in the inventory
	 * @return The ArrayList with items
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Change the current message displayed in the inventory bar
	 * @param message The new message
	 */
	public void setCurrentMessage(String message)
	{
		currentMessage = message;
	}
	
	/**
	 * Gets an item out of the items ArrayList on a specific index
	 * @param index The index of the item to return
	 * @return The sprite of the item
	 */
	public Item getItem(int index)
	{
		return items.get(index);
	}
	
	/**
	 * Checks if an item exists in the inventory
	 * @param index The index of the item to check for
	 * @return If the item exists or not, true/false
	 */
	public boolean itemExists(int index) {
		if(items.get(index) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Delete an item out of the items ArrayList on a specific index
	 * @param index The index of the item to be deleted
	 */
	public void deleteItem(int index)
	{
		if(getItem(index) != null) {
			items.remove(index);
		}
	}
	
	/**
	 * Returns true if the inventory contains a specific item
	 * @param keyName The name of the item
	 * @return True if the item is in the inventory, false if not
	 */
	 public boolean hasItem(String keyName)
	 {
	 for(Item item: items) {
		 if(keyName.equals(item.getKeyValue())) {
			 return true;
		 }
	 }
	 
	 return false;
	 }
	
	/**
	 * Adds an item to the items ArrayList
	 * @param item The item to be added
	 */
	public void addItem(Item item)
	{
		if(items.size() < maxItemCount) {
			items.add(item);
		}
	}
	
	/**
	 * Renders the inventory items
	 * @param g	The graphics to render the inventory items on
	 */
	public void render(Graphics g)
	{
		// Draw background
		background.drawSprite(g);
		
		// Draw message
		g.setColor(new Color(0, 0, 0));
		g.drawString(currentMessage, 10, 500);
		g.setColor(new Color(255, 255, 255));
		
		// Draw items
		int offsetLeft = 721;
		
		for(Item item: items) {
			Sprite sprite = item.getItemIcon();
			sprite.setX(offsetLeft);
			sprite.drawSprite(g);
			
			offsetLeft += 61;
		}
	}
	
}
