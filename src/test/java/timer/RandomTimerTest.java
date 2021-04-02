package timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timer.RandomTimer.randomDistribution;

class RandomTimerTest {
	
	
	private randomDistribution distribPoiss;
	private randomDistribution distribExp;
	private randomDistribution distribPosi;
	private randomDistribution distribGaus;
	

	@BeforeEach
	void setUp() throws Exception {
		
		this.distribPoiss = RandomTimer.string2Distribution("POISSON");
		this.distribExp = RandomTimer.string2Distribution("EXP");
		this.distribPosi = RandomTimer.string2Distribution("POSIBILIST");
		this.distribGaus = RandomTimer.string2Distribution("GAUSSIAN");
	}

	
	@Test
	void string2DistributionTest() {
		String rd = RandomTimer.distribution2String(this.distribPoiss);
		assertTrue(rd.equals("POISSON"));
	}
	
	@Test
	void distribution2StringTest() {
		randomDistribution rd = RandomTimer.string2Distribution("POISSON");
		assertEquals(0,rd.compareTo(this.distribPoiss));
	}
	
	@Test
	void testConstructeurExp() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribExp,2.5);
		assertTrue(rdt.getDistribution().equals("EXP"));
		assertTrue(rdt.getMean() == 0.4);
	}
	
	@Test
	void testConstructeurGaussian() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribGaus,1,5);
		assertTrue(rdt.getDistribution().equals("GAUSSIAN"));
		assertEquals(3,rdt.getMean());
	}
	
	
	@Test
	void testGetDistributionParamExp() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribExp,2.5);
		assertEquals("rate: 2.5",rdt.getDistributionParam());
	}

	
	@Test
	void testGetDistributionParamPoisson() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribPoiss,2.5);
		assertEquals("mean: 2.5",rdt.getDistributionParam());
	}
	
	@Test
	void testGetDistributionParamPosi() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribPosi,1,5);
		assertEquals("lolim: 1.0 hilim: 5.0",rdt.getDistributionParam());
	}
	
	@Test
	void testGetDistributionParamGaus() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribGaus,1,5);
		assertEquals("lolim: 1.0 hilim: 5.0",rdt.getDistributionParam());
	}
	
	@Test
	void testGetMean() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribExp,2.5);
		assertEquals(0.4,rdt.getMean());
	}
	
	
	@Test
	void testNext() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribExp,2.5);
		assertTrue(rdt.next() >= 0);
	}
	
	@Test
	void testToStringGaus() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribGaus,1,5);
		assertEquals("GAUSSIAN LoLim:1.0 HiLim:5.0",rdt.toString());
	}
	
	@Test
	void testToStringExp() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribExp,2.5);
		assertEquals("EXP rate:2.5",rdt.toString());
	}
	
	@Test
	void testToStringPosi() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribPosi,1,5);
		assertEquals("POSIBILIST LoLim:1.0 HiLim:5.0",rdt.toString());
	}
	
	@Test
	void testToStringPoiss() throws Exception {
		RandomTimer rdt = new RandomTimer(this.distribPoiss,2.5);
		assertEquals("POISSON mean:2.5",rdt.toString());
	}
}
