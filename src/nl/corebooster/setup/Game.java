package nl.corebooster.setup;

import java.util.HashMap;

import nl.corebooster.scenes.GameScene;
import nl.corebooster.scenes.IntroScene;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Game is the main class, it's handles the initialization, game logic and rendering.
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class Game extends BasicGame {
	
	private IntroScene intro;
	private GameScene currentScene;
	private HashMap<String, GameScene> scenes;
	
	/**
	 * Constructs the SetupClass
	 * @param title The title of the window
	 */
	public Game(String title) {
		super(title);
		
		scenes = new HashMap<String, GameScene>();
	}
	
	/**
	 * Initializes the game
	 * @param container The game container to initialize
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void init(GameContainer container) throws SlickException {
		intro = new IntroScene();
		
		scenes.put("ice", new GameScene("ice", 480, 270));
		scenes.put("outside_headquarters", new GameScene("outside_headquarters", 15, 260));
		scenes.put("headquarters", new GameScene("headquarters", 75, 270));
		scenes.put("drill", new GameScene("drill", 448, 15));
		
		currentScene = scenes.get("ice");
		currentScene.setActive();
	}
	
	/**
	 * Updates the game
	 * @param container The game container to update
	 * @param delta The update frequency
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(!intro.hasEnded())
		{
			intro.animate();
			intro.keyHandler(input);
		}
		else if(currentScene.isActive())
		{
			if(!currentScene.isRendered())
			{
				currentScene.playMusic();
				currentScene.setRendered();
			}
			
			if(currentScene.getNextScene() != null) {
				GameScene nextScene = scenes.get(currentScene.getNextScene());
				
				currentScene.setInactive();
				nextScene.setActive();
			}
			
			currentScene.keyHandler(input);
			currentScene.triggerHandler();
		}
		else
		{
			for(GameScene scene: scenes.values()) {
				if(scene.isActive()) {
					// Sets the current scene unrendered and resets the 'nextScene' value
					int currentPlayerRotation = currentScene.getPlayer().getRotation();
					scene.getPlayer().rotatePlayer(currentPlayerRotation);
					
					// Give the same inventory to the new scene
					Inventory currentInventory = currentScene.getInventory();
					scene.setInventory(currentInventory);
					
					currentScene.stopAllSounds();
					currentScene.setUnrendered();
					currentScene.resetNextScene();
					
					// Set the new current scene to the new scene
					currentScene = scene;
				}
			}
		}
	}
	
	/**
	 * Renders the game
	 * @param container The game container to render to
	 * @param g	The graphics to render on
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(!intro.hasEnded())
		{
			intro.render(g);
			intro.getOverlay().fadeOut(150);
			//g.drawString("Current scene: intro", 10, 30);
		}
		else
		{
			currentScene.render(g);
			//g.drawString("Current scene: " + currentScene.getSceneName(), 10, 30);
			
		}
	}

	/**
	 * The main class, responsible for the applications initialization
	 * @param args The startup arguments
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("The World Of Zuul"));
		int updateInterval = 20;
		
		app.setDisplayMode(960, 540, false);
		//app.setIcon("data/img/icon.png");
		
		app.setMinimumLogicUpdateInterval(updateInterval);
		app.setMaximumLogicUpdateInterval(updateInterval);
		app.setTargetFrameRate(60);
		
		app.start();
	}

}
