package nl.corebooster.setup;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes the inventory system used in the game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Inventory {

	private Sprite background;
	private static final int maxItemCount = 4;
	private ArrayList<Sprite> items;
	
	/**
	 * Initializes a new inventory
	 * @throws SlickException Indicates a failure to initialise the display
	 */
	public Inventory() throws SlickException
	{
		background = new Sprite("img", "hud.png", false, 0, 480);
		items = new ArrayList<Sprite>();
	}
	
	/**
	 * Returns the ArrayList with items in the inventory
	 * @return The ArrayList with items
	 */
	public ArrayList<Sprite> getItems()
	{
		return items;
	}
	
	/**
	 * Gets an item out of the items ArrayList on a specific index
	 * @param index The index of the item to return
	 * @return The sprite of the item
	 */
	public Sprite getItem(int index)
	{
		return items.get(index);
	}
	
	/**
	 * Adds an item to the items ArrayList
	 * @param item The Sprite of the item to be added
	 */
	public void addItem(Sprite item)
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
		// Draw background of HUD
		background.drawSprite(g);
		
		// Draw items
		int offsetLeft = 700;
		
		for(Sprite item: items) {
			item.setX(offsetLeft);
			item.drawSprite(g);
			
			offsetLeft += 40;
		}
	}
	
}
