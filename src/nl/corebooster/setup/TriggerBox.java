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
	private int boxMargin = 1;
	private String value;
	private boolean isTriggered;
	
	/**
	 * Constructs a new trigger box with a specific type and value
	 * @param type
	 * @param value
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public TriggerBox(TriggerType type, String value, int x, int y, int width, int height, int boxMargin)
	{
		triggerType = type;
		this.boxMargin = boxMargin;
		box = new Rectangle((x - boxMargin), (y - boxMargin), (width + boxMargin * 2), (height + boxMargin * 2));
		this.value = value;
		isTriggered = false;
	}
	
	/**
	 * Returns the type of the trigger
	 * @return
	 */
	public TriggerType getTriggerType()
	{
		return triggerType;
	}
	
	/**
	 * Returns the shape of the trigger
	 * @return
	 */
	public Rectangle getShape() {
		return box;
	}
	
	/**
	 * Returns the value of the trigger
	 * @return
	 */
	public String getValue()
	{
		return value;
	}
	
	/**
	 * Returns true if the trigger box is already triggered
	 * @return
	 */
	public boolean isTriggered()
	{
		return isTriggered;
	}
	
	/**
	 * Sets the trigger type
	 * @param type
	 */
	public void setTriggerType(TriggerType type)
	{
		triggerType = type;
	}
	
	/**
	 * Sets the value of the trigger
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * Sets isTriggered to true
	 */
	public void setTriggered()
	{
		isTriggered = true;
	}
	
	/**
	 * Resets the trigger when it is already triggered
	 */
	public void resetTrigger()
	{
		isTriggered = false;
	}
	
	/**
	 * Draws the trigger box on the screen
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawBox(Graphics g, int x, int y) 
	{
		box.setX(x - boxMargin);
		box.setY(y - boxMargin);
		g.setColor(null);
		g.draw(box);
	}
	
	/**
	 * Returns true if the given shape is triggering this trigger box
	 * @param boxToCheck
	 * @return
	 */
	public boolean isTriggering(Rectangle boxToCheck) 
	{
		
		if(box.intersects(boxToCheck)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
