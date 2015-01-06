package nl.corebooster.setup;

/**
 * The Item class describes an inventory item in the game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Item {
	
	public enum ItemType {
		KEY, SUPPLY
	}
	
	private String itemName;
	private ItemType itemType;
	private Sprite itemIcon;
	private Sprite sprite;
	
	/**
	 * Constructs an item
	 * @param itemName The name of the item
	 * @param itemType The type of the item
	 * @param itemIcon The icon of the item
	 * @param sprite The sprite of the item in-game
	 */
	public Item(String itemName, ItemType itemType, Sprite itemIcon, Sprite sprite) {
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemIcon = itemIcon;
		this.sprite = sprite;
	}
	
	/**
	 * Returns the name of the item
	 * @return The name of the item
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Returns the type of the item
	 * @return The type of the item
	 */
	public ItemType getItemType() {
		return itemType;
	}
	
	/**
	 * Returns the icon of the item
	 * @return The icon of the item
	 */
	public Sprite getItemIcon() {
		return itemIcon;
	}
	
	/**
	 * Returns the sprite of the item
	 * @return The sprite of the item
	 */
	public Sprite getSprite() {
		return sprite;
	}
	
	
}
