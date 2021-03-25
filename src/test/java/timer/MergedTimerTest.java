package timer;


/**
 * @author theobernardin
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MergedTimerTest {

	private Timer time1;
	private Timer time2;
	
	private Timer dateTimer1;
	private Timer dateTimer2;
	private Timer dateTimer3;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.time1 = new PeriodicTimer(10);
		this.time1 = new PeriodicTimer(12);
		TreeSet<Integer> dates = new TreeSet<Integer>();
		dates.add(50);
		dates.add(20);
		this.dateTimer1 = new DateTimer(dates) ;
		dates.add(30);
		this.dateTimer2 = new DateTimer(dates) ;
		dates.clear();
		this.dateTimer3 = new DateTimer(dates);
	}

	@Test
	void testMergedTimer() {
		MergedTimer timeMerge = new MergedTimer(this.time1,this.time2);
		assertEquals(this.time1,timeMerge.getTimer1());
		assertEquals(this.time2,timeMerge.getTimer2());
	}
	
	
	@Test
	void testHasNextTrue() {
		MergedTimer timeMerge = new MergedTimer(this.dateTimer1,this.dateTimer2);
		Boolean result = timeMerge.hasNext();
		assertEquals(true,result);
	}
	
	@Test
	void testHasNextFalse() {
		MergedTimer timeMerge = new MergedTimer(this.dateTimer1,this.dateTimer3);
		Boolean result = timeMerge.hasNext();
		assertEquals(false,result);
	}
	
	
	@Test
	void testNextTrue(){
		MergedTimer timeMerge = new MergedTimer(this.dateTimer1,this.dateTimer2);
		Integer result = timeMerge.next();
		assertEquals(40,result);
	}
	
	
	@Test
	void testNextFalse(){
		MergedTimer timeMerge = new MergedTimer(this.dateTimer1,this.dateTimer2);
		Integer result = timeMerge.next();
		assertEquals(10,result);
	}
	
	
	@Test
	void testNextNull(){
		MergedTimer timeMerge = new MergedTimer(this.dateTimer1,this.dateTimer3);
		Integer result = timeMerge.next();
		assertEquals(null,result);
	}
	
	
	
	
	

}
