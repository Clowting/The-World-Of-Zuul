package nl.corebooster.setup;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Describes the World of Zuul game
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Game {
	
	private HashMap<String, Sprite> sprites;
	private static final int screenWidth = 960;
	private static final int screenHeight = 540;
	
	/**
	 * Initializes the game
	 * @throws SlickException 
	 */
	public Game() throws SlickException
	{
		sprites = new HashMap<String, Sprite>();
		displayIntro();
	}
	
	/**
	 * Starts the game with the start screen
	 * @throws SlickException 
	 */
	private void displayIntro() throws SlickException
	{
		// Make sprite objects
		Sprite introBackground = new Sprite("img", "intro_background.png", 0, 0);
		Sprite spaceship = new Sprite("sprites", "spaceship.png", 150, 100);
		
		sprites.put("introBackground", introBackground);
		sprites.put("spaceship", spaceship);
	}
	
	/**
	 * Moves a sprite slowly up and down
	 */
	private void animateVertical(Sprite s, int maxUp, int maxDown)
	{
		boolean goingUp = true;
		int y = s.getY();
		
		while(goingUp) {
			if(y == maxUp) {
				goingUp = false;
			}
			else {
				y =- 1;
			}
		}
		
		while(!goingUp) {
			
		}
	}
	
	/**
	 * Animates all elements in the game
	 */
	public void animate()
	{
		sprites.get("spaceship").setY(sprites.get("spaceship").getY() + 1);
	}
	
	/**
	 * Renders the game
	 * @param graphics
	 */
	public void render(Graphics g) {
		for(Sprite s : sprites.values()) {
			g.drawImage(s.getImage(), s.getX(), s.getY());
		}
	}
	
}
