package nl.corebooster.setup;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Inventory {

	private Sprite background;
	private static final int maxItemCount = 4;
	private ArrayList<Sprite> items;
	
	public Inventory() throws SlickException
	{
		background = new Sprite("img", "hud.png", false, 0, 480);
		items = new ArrayList<Sprite>();
	}
	
	public ArrayList<Sprite> getItems()
	{
		return items;
	}
	
	public Sprite getItem(int index)
	{
		return items.get(index);
	}
	
	public void addItem(Sprite item)
	{
		if(items.size() < maxItemCount) {
			items.add(item);
		}
	}
	
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
