package discreteBehaviorSimulator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClockTest {
	
	@Test
	void testgetInstance() {
		Clock clock = null;
		assertNull(clock);
		assertTrue(Clock.getInstance() instanceof Clock);
		assertNotNull(Clock.getInstance());
	 }
	
	@Test
	public void testAddRemoveObserver() {
		// Add observer
		ClockObserver obs = new ClockObserverSubClass();
		assertFalse(Clock.getInstance().getObservers().contains(obs));
		Clock.getInstance().addObserver(obs);
		assertTrue(Clock.getInstance().getObservers().contains(obs));
		
		// Remove observer
		Clock.getInstance().removeObserver(obs);
		assertFalse(Clock.getInstance().getObservers().contains(obs));
	}
	
	@Test
	public void testVirtual() {
		// If virtual is true
		Clock.getInstance().setVirtual(true);
		assertTrue(Clock.getInstance().isVirtual());
		
		// If virtual is false
		Clock.getInstance().setVirtual(false);
		assertFalse(Clock.getInstance().isVirtual());
	}
	
	@Test
	public void testSetNextJump() {
		// Add three observers
		ClockObserver obs = new ClockObserverSubClass();
		ClockObserver obs2 = new ClockObserverSubClass();
		ClockObserver obs3 = new ClockObserverSubClass();
		Clock.getInstance().addObserver(obs);
		Clock.getInstance().addObserver(obs2);
		Clock.getInstance().addObserver(obs3);
		
		// Set next jump
		int nextJump = 4;
		Clock.getInstance().setNextJump(nextJump);
		
		// Check
		assertEquals(Clock.getInstance().getNextJump(),4);
		for (ClockObserver observer : Clock.getInstance().getObservers()) {
			assertTrue(observer.isNextClockChangeCalled());
		}
	}
	
	@Test
	public void testSetNextJumpNegatif(){
		// Add an observer
		ClockObserver obs = new ClockObserverSubClass();
		Clock.getInstance().addObserver(obs);
		
		// Set next jump
		int nextJump = -3;
		
		// We check that an exception is thrown if the next jump is negative
		assertThrows(Exception.class, () -> {
			Clock.getInstance().setNextJump(nextJump);
		});
	}
	
	@Test
	public void testIncreaseDifferent() {
		Clock.getInstance().setNextJump(3);
		
		// We check that an exception is thrown if time is different than nextJump
		assertThrows(Exception.class, () -> {
			Clock.getInstance().increase(4);
		});
	}
	
	@Test
	public void testIncreaseSame() throws Exception {
		// Add three observers
		ClockObserver obs = new ClockObserverSubClass();
		ClockObserver obs2 = new ClockObserverSubClass();
		ClockObserver obs3 = new ClockObserverSubClass();
		Clock.getInstance().addObserver(obs);
		Clock.getInstance().addObserver(obs2);
		Clock.getInstance().addObserver(obs3);
		
		// increase time
		Clock.getInstance().setTime(10);
		Clock.getInstance().setNextJump(3);
		Clock.getInstance().increase(3);
		
		// Check if the time was successfully incremented
		assertEquals(Clock.getInstance().getTime(),13);
		
		// Check the observers called the clockChange method
		for (ClockObserver observer : Clock.getInstance().getObservers()) {
			assertTrue(observer.isClockChangeCalled());
		}
		
	}
	
	@Test
	public void testGetTimeVirtual() {
		Clock.getInstance().setVirtual(true);
		Clock.getInstance().setTime(10);
		assertEquals(Clock.getInstance().getTime(),10);
	}
	
	@Test
	public void testGetTimeNotVirtual() {
		Clock.getInstance().setVirtual(false);
		assertEquals(Clock.getInstance().getTime(),new Date().getTime());
	}
	
	@Test
	public void testToString() {
		Clock.getInstance().setTime(10);
		assertEquals(Clock.getInstance().toString(),String.valueOf(Clock.getInstance().getTime()));
	}

}
