package nl.corebooster.tests;

import static org.junit.Assert.*;
import nl.corebooster.setup.TriggerBox;
import nl.corebooster.setup.TriggerBox.TriggerType;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TriggerBox class
 * @author Raymon de Looff, Thijs Clowting, Richard Weug
 * @version 1.0
 */
public class TriggerBoxTest {
	
	private TriggerBox triggerBox;

	@Before
	public void setUp() throws Exception {
		triggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
	}

	@Test
	public void testGetObjectName() {
		assertEquals("testObject", triggerBox.getObjectName());
	}

	@Test
	public void testGetTriggerType() {
		assertEquals(TriggerType.MESSAGE, triggerBox.getTriggerType());
	}

	@Test
	public void testGetValue() {
		assertEquals("This is a test message", triggerBox.getValue());
	}

	@Test
	public void testIsTriggered() {
		assertEquals(false, triggerBox.isTriggered());
	}
	
	@Test
	public void testSetObjectName() {
		// Generate a temporary trigger box
		TriggerBox temporaryTriggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
		
		// Sets new object name
		temporaryTriggerBox.setObjectName("newTestObject");
		
		assertEquals("newTestObject", temporaryTriggerBox.getObjectName());
	}

	@Test
	public void testSetTriggerType() {
		// Generate a temporary trigger box
		TriggerBox temporaryTriggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
		
		// Sets new trigger type
		temporaryTriggerBox.setTriggerType(TriggerType.SCENESWITCH);
		
		assertEquals(TriggerType.SCENESWITCH, temporaryTriggerBox.getTriggerType());
	}

	@Test
	public void testSetTriggered() {
		// Generate a temporary trigger box
		TriggerBox temporaryTriggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
		
		// Triggers the trigger box
		temporaryTriggerBox.setTriggered();
		
		assertEquals(true, temporaryTriggerBox.isTriggered());
	}
	
	@Test
	public void testSetValue() {
		// Generate a temporary trigger box
		TriggerBox temporaryTriggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
		
		// Triggers the trigger box
		temporaryTriggerBox.setValue("This is a new test value");
		
		assertEquals("This is a new test value", temporaryTriggerBox.getValue());
	}

	@Test
	public void testResetTrigger() {
		// Generate a temporary trigger box
		TriggerBox temporaryTriggerBox = new TriggerBox("testObject", TriggerType.MESSAGE, 90, 0, 0, "This is a test message", 50, 200, 20, 20, 5);
		
		// Triggers the trigger box
		temporaryTriggerBox.setTriggered();
		temporaryTriggerBox.resetTrigger();
		
		assertEquals(false, temporaryTriggerBox.isTriggered());
	}

}
