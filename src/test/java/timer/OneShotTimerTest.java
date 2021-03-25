package timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneShotTimerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testOneShotTimer() {
		int at = 5;
		OneShotTimer ost = new OneShotTimer(at);
		assertNotNull(ost);
		
	}

	@Test
	void testHasNext() {
		int at = 2;
		OneShotTimer ost = new OneShotTimer(at);
		assertTrue(ost.hasNext());
	}

	@Test
	void testNext() {
		int at = 4;
		OneShotTimer ost = new OneShotTimer(at);
		int expected = 4;
		assertEquals(expected, ost.next());
		assertFalse(ost.hasNext());
	}

}
