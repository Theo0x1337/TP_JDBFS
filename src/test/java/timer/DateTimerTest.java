package timer;




import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimerTest {

	private Vector<Integer> vecteur;
	private DateTimer datetimer;
	private TreeSet<Integer> dates;

	@BeforeEach
	void setUp() throws Exception {
			vecteur = new Vector<Integer>(1);
			
			
	        
	        
	        
	        dates = new TreeSet<Integer>();
	        dates.add(10);
	        dates.add(20);
	        dates.add(30);
	        
	        
	        
	        
	}

	@Test
	void testDateTimerV() {
		datetimer = new DateTimer(vecteur);
		assertTrue(datetimer.lapsTimes.equals(vecteur));
		assertNotNull(datetimer.it);
		
	}
	@Test
	void testDateTimerT() {
		datetimer = new DateTimer(dates);
		assertTrue(datetimer.lapsTimes.firstElement().equals(10));
		assertNotNull(datetimer.it);

	}
	
	
	@Test
	void testHasNext() {
		datetimer = new DateTimer(vecteur);
		assertFalse(datetimer.it.hasNext());
		vecteur.add(1);
		datetimer = new DateTimer(vecteur);
		assertTrue(datetimer.it.hasNext());
	}
	
}