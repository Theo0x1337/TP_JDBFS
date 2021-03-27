package timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class PeriodicTimerTest {
	private int at;
	private int period;
	private RandomTimer moreOrLess;
	private randomDistribution rd;
	private double param;

	@BeforeEach
	void setUp() throws Exception {
		at = 4;
		period = 10;
		param = 2;
		
	}

	@Test
	void testPeriodicTimerInt() {
		PeriodicTimer pt = new PeriodicTimer(at);
		assertNotNull(pt);
		assertEquals(at, pt.getPeriod());
		assertEquals(at, pt.getNext());
	}

	@Test
	void testPeriodicTimerIntRandomTimer() throws Exception {
		String dist = "POISSON";
		rd = moreOrLess.string2Distribution(dist);
		RandomTimer rt = new RandomTimer(rd, param);
		PeriodicTimer pt = new PeriodicTimer(at, rt);
		assertNotNull(pt);
		assertEquals(at, pt.getPeriod());
		
	}

	@Test
	void testPeriodicTimerIntInt() {
		PeriodicTimer pt = new PeriodicTimer(period, at);
		assertNotNull(pt);
		assertEquals(period, pt.getPeriod());
		
	}

	@Test
	void testPeriodicTimerIntIntRandomTimer() throws Exception {
		String dist = "EXP";
		rd = moreOrLess.string2Distribution(dist);
		RandomTimer rt = new RandomTimer(rd, param);
		PeriodicTimer pt = new PeriodicTimer(period, at, rt);
		assertNotNull(pt);
		assertEquals(period, pt.getPeriod());
	}

	@Test
	void testGetPeriod() {
		PeriodicTimer pt = new PeriodicTimer(period, at);
		assertEquals(period, pt.getPeriod());
	}

	@Test
	void testNext() {
		PeriodicTimer pt = new PeriodicTimer(period, at);
		assertEquals(at, pt.next());
	}

	@Test
	void testHasNext() {
		PeriodicTimer pt = new PeriodicTimer(at);
		assertTrue(pt.hasNext());
	}

}
