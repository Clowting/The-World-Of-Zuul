package nl.corebooster.tests;

import static org.junit.Assert.*;
import nl.corebooster.setup.CollisionBox;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Rectangle;

public class CollisionBoxTest {
	
	private CollisionBox collisionBox;

	@Before
	public void setUp() throws Exception {
		collisionBox = new CollisionBox(50, 50, 100, 100);
	}

	@Test
	public void testIsColliding() {
		Rectangle box = new Rectangle(25, 25, 100, 100);
		
		assertEquals(true, collisionBox.isColliding(box));
	}
	
	@Test
	public void testIsNotColliding() {
		Rectangle box = new Rectangle(10, 10, 25, 25);
		
		assertEquals(false, collisionBox.isColliding(box));
	}

}
