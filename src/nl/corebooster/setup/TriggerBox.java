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
		SCENESWITCH, BORDER_SCENESWITCH, LOCKEDSCENESWITCH, MESSAGE, LOCKEDMESSAGE, TRAPDOOR, ITEM
	}
	
	private String objectName;
	private TriggerType triggerType;
	private int triggerDirection, alternateX, alternateY;
	private Rectangle box;
	private int boxMargin = 1;
	private String value;
	private boolean isTriggered;
	
	/**
	 * Constructs a new trigger box with a specific type and value
	 * @param objectName Name of the corresponding object
	 * @param type The type of trigger, SCENESWITCH/LOCKEDSCENESWITCH/MESSAGE/ANIMATE/ITEM
	 * @param triggerDirection The direction the player has to approach from for the trigger to work
	 * @param alternateX The x position to use if the normal position is blocked
	 * @param alternateY The y position to use if the normal position is blocked
	 * @param value The value of the trigger
	 * @param x The initial x position of the trigger box
	 * @param y The initial y position of the trigger box
	 * @param width The width of the trigger box
	 * @param height The height of the trigger box
	 * @param boxMargin The margin of the trigger box
	 */
	public TriggerBox(String objectName, TriggerType type, int triggerDirection, int alternateX, int alternateY, String value, int x, int y, int width, int height, int boxMargin)
	{
		this.objectName = objectName;
		this.triggerType = type;
		this.boxMargin = boxMargin;
		this.box = new Rectangle((x - boxMargin), (y - boxMargin), (width + boxMargin * 2), (height + boxMargin * 2));
		this.value = value;
		this.isTriggered = false;
		this.triggerDirection = triggerDirection;
		this.alternateX = alternateX;
		this.alternateX = alternateY;
	}
	
	/**
	 * Returns the name of the parent object of the trigger box
	 * @return The name of the parent object
	 */
	public String getObjectName()
	{
		return objectName;
	}
	
	/**
	 * Returns the type of the trigger
	 * @return The type of trigger
	 */
	public TriggerType getTriggerType()
	{
		return triggerType;
	}
	
	/**
	 * Returns the trigger direction
	 * @return The direction the player has to approach from for the trigger to work
	 */
	public int getTriggerDirection()
	{
		return triggerDirection;
	}
	
	/**
	 * Returns the alternate x position
	 * @return The alternate x position
	 */
	public int getAlternateX() {
		return alternateX;
	}
	
	/**
	 * Returns the alternate y position
	 * @return The alternate y position
	 */
	public int getAlternateY() {
		return alternateY;
	}
	
	/**
	 * Returns the shape of the trigger
	 * @return The rectangle shape of the collision box
	 */
	public Rectangle getShape() {
		return box;
	}
	
	/**
	 * Returns the value of the trigger
	 * @return The value of the trigger
	 */
	public String getValue()
	{
		return value;
	}
	
	/**
	 * Returns true if the trigger box is already triggered
	 * @return Whether the trigger box is triggered or not, true/false
	 */
	public boolean isTriggered()
	{
		return isTriggered;
	}
	
	/**
	 * Sets a new parent object name for the trigger box
	 * @param objectName The new parent object name
	 */
	public void setObjectName(String objectName)
	{
		this.objectName = objectName;
	}
	
	/**
	 * Sets the trigger type
	 * @param type The type of trigger
	 */
	public void setTriggerType(TriggerType type)
	{
		triggerType = type;
	}
	
	/**
	 * Sets the value of the trigger
	 * @param value The value of the trigger
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
	 * @param g The graphics to draw the trigger box on
	 * @param x The new x-position of the trigger box
	 * @param y The new y-position of the trigger box
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
	 * @param boxToCheck The shape to check triggering with
	 * @return Whether or not the two shapes collide and therefore trigger, true/false
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
