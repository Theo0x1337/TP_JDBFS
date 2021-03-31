package discreteBehaviorSimulator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import action.DiscreteActionDependent;
import action.DiscreteActionInterface;
import action.DiscreteActionOnOffDependent;
import timer.OneShotTimer;


class DiscreteActionSimulatorTest {
	
	@Test
	void testSetNbLoop() {
		DiscreteActionSimulator das = new DiscreteActionSimulator();
		das.setNbLoop(4);
		assertEquals(das.getNbLoop(), 4);
		assertEquals(das.getStep(), 1);
	}
	
	@Test
	void testSetNbLoopNegative() {
		DiscreteActionSimulator das = new DiscreteActionSimulator();
		das.setNbLoop(-3);
		assertEquals(das.getNbLoop(), 0);
		assertEquals(das.getStep(), -1);
	}
	
	@Test
	void testAddAction() {
		// Dependent
		DiscreteActionSimulator das = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteActionInterface dad = new DiscreteActionDependent(new Object(), null, oneShotTimer);
		
		// On Off 
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
	    OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
	    DiscreteActionInterface daood = new DiscreteActionOnOffDependent(new Object(),null,oneShotTimerON,null,oneShotTimerOFF);

		// Add actions
		das.addAction(dad);
		das.addAction(daood);
		
		// Check adds
		assertTrue(das.getActionsList().contains(dad));
		assertTrue(das.getActionsList().contains(daood));
		
		// Check sort
		Vector<DiscreteActionInterface> copy = das.getActionsList();
		Collections.sort(copy);
		assertEquals(das.getActionsList(),copy);
	}
	
	@Test
	void testStart() {
		// Dependent
		DiscreteActionSimulator das = new DiscreteActionSimulator();
		OneShotTimer oneShotTimer = new OneShotTimer(5);
		DiscreteActionInterface dad = new DiscreteActionDependent(new Object(), "methodTest", oneShotTimer);
		
		// On Off 
		OneShotTimer oneShotTimerON = new OneShotTimer(1);
	    OneShotTimer oneShotTimerOFF = new OneShotTimer(5);
	    DiscreteActionInterface daood = new DiscreteActionOnOffDependent(new Object(),"methodTest",oneShotTimerON,"methodTest",oneShotTimerOFF);

		// Add actions
		das.addAction(dad);
		das.addAction(daood);
		
		// Test the method
		das.setNbLoop(5);
		long savedTimeBeforeStart = Clock.getInstance().getTime();
		das.start();
		
		// Check running == true
		assertTrue(das.getRunning());
		
		// Check running == false after stop
		das.stop();
		assertFalse(das.getRunning());
		
		// Check toString
		assertNotNull(das.toString());
		
		// Check time changed
		assertNotEquals(Clock.getInstance().getTime(),savedTimeBeforeStart);
		
		// Check empty list after start method ended
		assertTrue(das.getActionsList().isEmpty());
		
		// Check
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println(Clock.getInstance().getTime());
	}
	
	@Test
	void testStartEmptyList() {
		DiscreteActionSimulator das = new DiscreteActionSimulator();
		
		// We check that an exception is thrown if the list is empty
		assertThrows(Exception.class, () -> {
			das.start();
		});
	}

}
