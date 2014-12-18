package nl.corebooster.setup;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * SetupClass is the main class, it's responsible for setting everything up
 * @author Raymon de Looff, Thijs Clowting
 * @version 1.0a
 */
public class SetupClass extends BasicGame {
	
	/**
	 * Constructs the SetupClass
	 * @param title		The title of the window
	 */
	public SetupClass(String title) {
		super(title);
	}
	
	/**
	 * Initializes the game
	 */
	public void init(GameContainer container) throws SlickException {
		
	}
	
	/**
	 * Updates the game
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		
	}
	
	/**
	 * Renders the game
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawString("Hello Word!", 50, 50);
	}

	/**
	 * The main class, responsible for the applications initialization
	 * @param args		The startup arguments
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SetupClass("The World Of Zuul"));
		
		app.setDisplayMode(1280, 720, false);
		app.setVSync(true);
		
		app.start();
	}

}
