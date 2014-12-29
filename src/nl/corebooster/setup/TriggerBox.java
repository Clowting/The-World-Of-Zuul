package nl.corebooster.setup;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Describes an trigger box. When the player collides with a trigger box, an event will start
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class TriggerBox {
	
	public enum TriggerType {
		SCENESWITCH, MESSAGE
	}
	
	private TriggerType triggerType;
	private Rectangle box;
	private static final int boxMargin = 10;
	private String value;
	private boolean isTriggered;
	
	public TriggerBox(TriggerType type, String value, int x, int y, int width, int height)
	{
		triggerType = type;
		box = new Rectangle((x - boxMargin), (y - boxMargin), (width + boxMargin * 2), (height + boxMargin * 2));
		this.value = value;
		isTriggered = false;
	}
	
	public TriggerType getTriggerType()
	{
		return triggerType;
	}
	
	public Rectangle getShape() {
		return box;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public boolean isTriggered()
	{
		return isTriggered;
	}
	
	public void setTriggerType(TriggerType type)
	{
		triggerType = type;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public void resetTrigger()
	{
		isTriggered = false;
	}
	
	public void drawBox(Graphics g, int x, int y) 
	{
		box.setX(x - boxMargin);
		box.setY(y - boxMargin);
		g.setColor(null);
		g.draw(box);
	}
	
	public boolean isTriggering(Rectangle boxToCheck) 
	{
		
		if(box.intersects(boxToCheck)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
