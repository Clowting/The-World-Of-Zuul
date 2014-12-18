package nl.corebooster.setup;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * SetupClass is the main class, it's responsible for setting everything up
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class SetupClass extends BasicGame {
	
	private Game game;
	
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
		game = new Game();
	}
	
	/**
	 * Updates the game
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		game.animate();
	}
	
	/**
	 * Renders the game
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		game.render(container.getGraphics());
	}

	/**
	 * The main class, responsible for the applications initialization
	 * @param args		The startup arguments
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SetupClass("The World Of Zuul"));
		int updateInterval = 50;
		
		app.setDisplayMode(960, 540, false);
		
		app.setMinimumLogicUpdateInterval(updateInterval);
		app.setMaximumLogicUpdateInterval(updateInterval);
		
		app.start();
	}

}
