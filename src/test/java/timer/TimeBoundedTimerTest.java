package timer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeBoundedTimerTest {
	
	private Timer tb;
	private TimeBoundedTimer tbt;
	private TimeBoundedTimer tbt2;
	@BeforeEach
	void setUp() throws Exception {
		
		tb = new PeriodicTimer(10);
		tbt = new TimeBoundedTimer(tb, 2 , 11);
		tbt2 = new TimeBoundedTimer(tb, 2 , 10);
		
	}

	@Test
	void testTBT_withEnd() {
		
		 
		
		
		assertTrue(tbt.getTimer2bound().equals(tb));
		assertTrue(tbt.getStartTime().equals(2));
		assertTrue(tbt.getStopTime().equals(11));
		
	}
	@Test
	void testTBT_withoutEnd() {
		
		tbt = new TimeBoundedTimer(tb, 2);
		
		assertTrue(tbt.getTimer2bound().equals(tb));
		assertTrue(tbt.getStartTime().equals(2));
		assertTrue(tbt.getStopTime().equals(Integer.MAX_VALUE));

	}
	
	@Test
	void testInit() throws Exception{
        
        
		
		assertTrue(tbt.hasNext());
		
		assertFalse(tbt2.hasNext());
		
		
	}
	
	@Test
	void testHasNext() {
		
		 
	
		assertTrue(tbt.hasNext());
		
		assertFalse(tbt2.hasNext());
	}
	
	
	@Test
	void testNext() {
		
		assertNotNull(tbt.next());
		
		assertNull(tbt2.next());
	}
	
	
}
