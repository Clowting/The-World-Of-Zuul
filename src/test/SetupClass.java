package test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SetupClass extends BasicGame {

	public SetupClass(String title) {
		super(title);
	}
	
	public void init(GameContainer container) throws SlickException {
		
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		
	}
	
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SetupClass("The World Of Zuul"));
		
		app.setDisplayMode(1280, 720, false);
		
		app.start();
	}

}
