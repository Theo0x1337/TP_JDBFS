package timer;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * DateTimer class representing the time interval between several dates
 *
 */
public class DateTimer  implements Timer {
	
	Vector<Integer> lapsTimes;
	Iterator<Integer> it;
	
	
	/**
	 * Constructor of the DateTimer class taking a TreeSet as parameter
	 * @param date 
	 * set of dates (TreeSet) which we will calculate the interval between each date and add them to an iterator
	 */
	public DateTimer(TreeSet<Integer> dates) {
		this.lapsTimes = new Vector<Integer>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();

	}
	
	
	/**
	 * Constructor of the DateTimer class taking a Vector as parameter
	 * @param lapsTimes
	 * vector containing the time intervals between the different dates
	 */
	public DateTimer(Vector<Integer> lapsTimes) {
		this.lapsTimes = new Vector<Integer>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	/**
	 * Overloaded method to know if the iterator has a next element 
	 */
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	/**
	 * Overloaded method to get the next element of the iterator
	 */
	public Integer next() {
		return it.next();
	}

}
