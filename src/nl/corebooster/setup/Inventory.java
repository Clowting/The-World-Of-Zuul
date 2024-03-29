package nl.corebooster.setup;

import java.util.ArrayList;
import java.util.Iterator;

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
	private Sprite selectedSlotImage;
	private int selectedSlot;
	private ArrayList<Item> items;
	
	/**
	 * Initializes a new inventory
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public Inventory() throws SlickException
	{
		background = new Sprite("inventory", "img", "inventory.png", false, 0, 465);
		currentMessage = "Thanks for playing our game!";
		selectedSlotImage = new Sprite("selectedSlot", "img", "selected_slot.png", false, 718, 481);
		selectedSlot = 0;
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
	 * Returns the current selected slot
	 * @return The number of the selected slot
	 */
	public int getSelectedSlot()
	{
		return selectedSlot;
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
	 * Sets the selected slot in the inventory
	 * @param selectedSlot The number of the selected slot
	 */
	public void setSelectedSlot(int selectedSlot)
	{
		this.selectedSlot = selectedSlot;
	}
	
	/**
	 * Gets an item out of the items ArrayList on a specific index
	 * @param index The index of the item to return
	 * @return The item
	 */
	public Item getItem(int index)
	{
		return items.get(index);
	}
	
	/**
	 * Returns true if the inventory contains a specific item
	 * @param keyName The name of the item
	 * @return True if the item is in the inventory, false if not
	 */
	 public boolean hasItemSelected(String keyName)
	 {
		 if(itemExists(selectedSlot)) {
			 Item selectedItem = items.get(selectedSlot);
			 
			 if(keyName.equals(selectedItem.getKeyValue())) {
				 return true;
			 }
			 else {
				 return false;
			 }
		 }
		 else {
			 return false;
		 }
	 }
	
	/**
	 * Returns the current selected item in the inventory
	 * @return The item
	 */
	public Item getSelectedItem()
	{
		if(itemExists(selectedSlot)) {
			return items.get(selectedSlot);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Removes the selected item from the inventory
	 */
	public void deleteSelectedItem()
	{
		if(itemExists(selectedSlot)) {
			items.remove(selectedSlot);
		}
	}
	
	/**
	 * Checks if an item exists in the inventory
	 * @param index The index of the item to check for
	 * @return If the item exists or not, true/false
	 */
	public boolean itemExists(int index) {
		if(index < items.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true if you can add an item to the inventory, false when full
	 * @return True if you can add an item, false if not
	 */
	public boolean canAddItem()
	{
		if(items.size() < maxItemCount) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adds an item to the items ArrayList
	 * @param item The item to be added
	 */
	public void addItem(Item item)
	{
		if(canAddItem()) {
			items.add(item);
		}
		else {
			setCurrentMessage("You can't pick up more than " + maxItemCount + " items!");
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
	 * Removes the item from the inventory with the given key value
	 * @param keyValue The key value of the item
	 */
	public void deleteItem(String keyValue)
	{
		Iterator<Item> iterator = items.iterator();
				
		while(iterator.hasNext()) {
			Item item = iterator.next();
			
			if(keyValue.equals(item.getKeyValue())) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Renders the inventory items
	 * @param g	The graphics to render the inventory items on
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void render(Graphics g) throws SlickException
	{
		// Draw background
		background.drawSprite(g);
		
		// Draw message
		g.drawString(currentMessage, 20, 490);
		
		// Draw selected slot
		int slotOffsetLeft = 718 + (selectedSlot * 61);
		selectedSlotImage.setX(slotOffsetLeft);
		selectedSlotImage.drawSprite(g);
		
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
