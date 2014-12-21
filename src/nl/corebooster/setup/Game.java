package nl.corebooster.setup;

import nl.corebooster.scenes.GameScene;
import nl.corebooster.scenes.IntroScene;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * SetupClass is the main class, it's responsible for setting everything up
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Game extends BasicGame {
	
	private IntroScene intro;
	private GameScene currentScene;
	//private HashSet<>;
	
	/**
	 * Constructs the SetupClass
	 * @param title		The title of the window
	 */
	public Game(String title) {
		super(title);
	}
	
	/**
	 * Initializes the game
	 */
	public void init(GameContainer container) throws SlickException {
		intro = new IntroScene();
		currentScene = new GameScene();
	}
	
	/**
	 * Updates the game
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(!intro.hasEnded())
		{
			intro.animate();
			intro.keyHandler(input);
		}
		else if(!currentScene.hasEnded())
		{
			if(!currentScene.hasRendered())
			{
				intro.getAnimatedSprite("start").stopAnimation();
				render(container, container.getGraphics());
				currentScene.setRendered();
			}
			currentScene.keyHandler(input);
		}
	}
	
	/**
	 * Renders the game
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(!intro.hasEnded())
		{
			intro.render(g);
		}
		else
		{
			currentScene.render(g);
		}
	}

	/**
	 * The main class, responsible for the applications initialization
	 * @param args		The startup arguments
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("The World Of Zuul"));
		int updateInterval = 50;
		
		app.setDisplayMode(960, 540, false);
		
		app.setMinimumLogicUpdateInterval(updateInterval);
		app.setMaximumLogicUpdateInterval(updateInterval);
		app.setTargetFrameRate(60);
		
		app.start();
	}

}
