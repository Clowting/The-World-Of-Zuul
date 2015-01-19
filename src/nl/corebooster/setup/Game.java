package nl.corebooster.setup;

import java.util.HashMap;

import nl.corebooster.scenes.EndScene;
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
	private EndScene end;
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
	 * Returns the game scene with the given key
	 * @return The game scene
	 */
	public GameScene getScene(String key)
	{
		if(scenes.containsKey(key)) {
			return scenes.get(key);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Initializes the game
	 * @param container The game container to initialize
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void init(GameContainer container) throws SlickException {
		// Container preferences
		container.setShowFPS(false);
		
		intro = new IntroScene();
		
		scenes.put("ice", new GameScene("ice", 480, 270));
		scenes.put("outside_headquarters", new GameScene("outside_headquarters", 15, 260));
		scenes.put("above_headquarters", new GameScene("above_headquarters", 488, 461));
		scenes.put("headquarters", new GameScene("headquarters", 75, 270));
		scenes.put("basement_1", new GameScene("basement_1", 864, 80));
		scenes.put("basement_2", new GameScene("basement_2", 15, 60));
		scenes.put("basement_3", new GameScene("basement_3", 228, 15));
		scenes.put("basement_4", new GameScene("basement_4", 192, 15));
		scenes.put("drill", new GameScene("drill", 448, 15));
		
		currentScene = scenes.get("ice");
		currentScene.setActive();
		
		end = new EndScene();
	}
	
	/**
	 * Updates the game
	 * @param container The game container to update
	 * @param delta The update frequency
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(!intro.hasEnded()) {
			intro.animate();
			intro.keyHandler(input);
		}
		else if(currentScene.getSceneName().equals("drill") && currentScene.checkTradeInSupplies()) {
			end.animate();
		}
		else if(currentScene.isActive()) {
			if(!currentScene.isRendered()) {
				currentScene.playMusic();
				currentScene.setRendered();
				
				// Checks if the next player is colliding	
				int[] alternateCoordinates = currentScene.getAlternateCoordinates();
				
				if(alternateCoordinates != null) {
					Player currentPlayer = currentScene.getPlayer();
					currentPlayer.setX(alternateCoordinates[0]);
					currentPlayer.setY(alternateCoordinates[1]);
				}
				
			}
			
			if(currentScene.getNextScene() != null) {
				GameScene nextScene = scenes.get(currentScene.getNextScene());
				
				// Disables the current scene and enables the next scene
				currentScene.setInactive();
				nextScene.setActive();
			}
			
			currentScene.update();
			currentScene.keyHandler(input);
			currentScene.triggerHandler();
		}
		else {
			for(GameScene scene: scenes.values()) {
				if(scene.isActive()) {
					// Change player position for next scene
					Player currentPlayer = currentScene.getPlayer();
					Player nextPlayer = scene.getPlayer();
					int playerSize = currentPlayer.getPlayerSize();
					
					int currentPlayerRotation = currentPlayer.getRotation();
					int currentPlayerX = currentPlayer.getX();
					int currentPlayerY = currentPlayer.getY();
					int nextPlayerX = nextPlayer.getX();
					int nextPlayerY = nextPlayer.getY();
					boolean preservePlayerCoordinates = currentScene.preservePlayerCoordinates();
					int offset = 10;
					int x = 0;
					int y = 0;
					
					switch(currentPlayerRotation) {
						case 0:
							x = currentPlayerX;
							
							if(preservePlayerCoordinates) {
								y = nextPlayerY - offset;
							}
							else {
								y = 540 - playerSize - offset;
							}
						break;
						
						case 90:
							if(preservePlayerCoordinates) {
								x = nextPlayerX + offset;
							}
							else {
								x = offset;
							}
							
							y = currentPlayerY;
						break;
						
						case 180:
							x = currentPlayerX;
							
							if(preservePlayerCoordinates) {
								y = nextPlayerY + offset;
							}
							else {
								y = offset;
							}
						break;
						
						case 270:
							if(preservePlayerCoordinates) {
								x = nextPlayerX - offset;
							}
							else {
								x = 960 - playerSize - offset;
							}
							
							y = currentPlayerY;
						break;
					}
					
					nextPlayer.rotatePlayer(currentPlayerRotation);
					nextPlayer.setX(x);
					nextPlayer.setY(y);
					
					// Disables current scene
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
		}
		else if(currentScene.getSceneName().equals("drill") && currentScene.checkTradeInSupplies()) {
			// Disables the Game Scene and makes everything ready for the end scene
			currentScene.setInactive();
			currentScene.stopAllSounds();
			currentScene.setUnrendered();
			currentScene.resetNextScene();
			
			// Renders end scene
			end.render(g);
		}
		else
		{
			currentScene.render(g);
			
		}
	}

	/**
	 * The main class, responsible for the applications initialization
	 * @param args The startup arguments
	 * @throws SlickException Indicates a failure to initialize the display
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("Blue Abyss"));
		int updateInterval = 50;
		
		app.setDisplayMode(960, 540, false);
		app.setIcon("data/img/icon.png");
		
		app.setMinimumLogicUpdateInterval(updateInterval);
		app.setMaximumLogicUpdateInterval(updateInterval);
		app.setTargetFrameRate(60);
		
		app.start();
	}

}
