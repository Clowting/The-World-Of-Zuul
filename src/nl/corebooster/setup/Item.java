package nl.corebooster.setup;

import nl.corebooster.setup.TriggerBox.TriggerType;

import org.newdawn.slick.SlickException;

/**
 * The Item class describes an inventory item in the game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Item {
	
	public enum ItemType {
		KEY, SUPPLY
	}
	
	private String keyValue;
	private String itemName;
	private ItemType itemType;
	private Sprite itemIcon;
	private Sprite sprite;
	
	/**
	 * Constructs an item
	 * @param keyValue The key of the item
	 * @param itemName The name of the item
	 * @param itemType The type of the item
	 * @param itemIconName The name of the item it's icon
	 * @param spriteName The name of the item it's sprite
	 * @param x The initial x position of the sprite
	 * @param y The initial y position of the sprite
	 * @throws SlickException Indicates a failure to initialize the display Indicates a failure to initialize the display
	 */
	public Item(String keyValue, String itemName, ItemType itemType, String itemIconName, String spriteName, int x, int y) throws SlickException 
	{
		this.keyValue = keyValue;
		this.itemName = itemName;
		this.itemType = itemType;
		itemIcon = new Sprite(keyValue, "items", itemIconName, false, 0, 484);
		sprite = new Sprite(keyValue, "items", spriteName, false, TriggerType.ITEM, "You picked up a " + itemName, 5, x, y);
	}
	
	/**
	 * Returns the key of the item
	 * @return The key of the item
	 */
	public String getKeyValue() 
	{
		return keyValue;
	}
	
	/**
	 * Retuns the name of the item
	 * @return The name of the item
	 */
	public String getItemName()
	{
		return itemName;
	}
	
	/**
	 * Returns the type of the item
	 * @return The type of the item
	 */
	public ItemType getItemType() 
	{
		return itemType;
	}
	
	/**
	 * Returns the icon of the item
	 * @return The icon of the item
	 */
	public Sprite getItemIcon() 
	{
		return itemIcon;
	}
	
	/**
	 * Returns the sprite of the item
	 * @return The sprite of the item
	 */
	public Sprite getSprite() 
	{
		return sprite;
	}
	
	
}
