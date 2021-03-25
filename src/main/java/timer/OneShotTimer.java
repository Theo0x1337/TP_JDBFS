package timer;
/**
 * OneShotTimer class that resumes a timer that executes only once
 *
 */
public class OneShotTimer  implements Timer {
	

	//attribute of type integer representing the moment when the timer is executed
	private Integer at;
	//attribute of type integer representing the moment when the timer is executed
	private boolean hasNext;
	
	
	/**
	 * Constructor of the OneShotTimer class taking an integer in parameter which represents the instant t where the timer will be executed
	 * @param at
	 * type integer representing the moment when the timer is executed
	 */
	public OneShotTimer(int at) {
		this.at = at;
		this.hasNext = true;
	}

	/**
	 * Overload the hasNext() method to know if the current instance has a next element 
	 */
	@Override
	public boolean hasNext() {
		return this.hasNext;
	}
	
	
	
	/**
	 * Overload the next() method, return the moment when the oneShotTimer executes and specify that there are no more events after it
	 */
	@Override
	public Integer next() {
		Integer next=this.at;
		this.at=null;
		this.hasNext = false;
		return next;
	}

	public Integer getAt() {
		return at;
	}

}
