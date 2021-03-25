package timer;

/**
 * Classe PeriodicTimer, qui represente un timer s'executant de maniere periodique
 *
 */
public class PeriodicTimer implements Timer {

	//Integer attribute representing the period between two timer ticks
	private int period;
	//Integer attribute representing the time when the next timer tick will occur
	private int next;
	//Attribute of type RandomTimer which will generate timer ticks randomly according to a distribution law
	private RandomTimer moreOrLess = null;
	
	
	/**
	 * Constructor of the PeriodicTimer class taking a time period as parameter
	 * @param at
	 * period of time between two triggers of the PeriodicTimer object
	 */
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	
	/**
	 * Constructor of the PeriodicTimer class taking a period and a RandomTimer object as parameters
	 * @param at
	 * period of time between two triggers of the PeriodicTimer object
	 * @param moreOrLess
	 * RandomTimer allowing to simulate the next triggering of the PeriodicTimer
	 * 
	 * use MergedTimer instead
	 */
	@Deprecated
	public PeriodicTimer(int at, RandomTimer moreOrLess) {
		this.period = at;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	
	/**
	 * Constructor of the PeriodicTimer class taking as parameters a period and another integer representing the next trigger of the PeriodicTimer
	 * @param period
	 * the period of time between two triggers of the PeriodicTimer
	 * @param at
	 * the time of the next triggering of the PeriodicTimer
	 */
	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}
	
	/**
	 * Constructor of the PeriodicTimer class taking a period and a RandomTimer object as parameters
	 * @param period
	 * the period of time between two timer triggers
	 * @param at
	 * the time of the next triggering of the PeriodicTimer
	 * @param moreOrLess
	 * RandomTimer to simulate the next PeriodicTimer triggering
	 * 
	 * use MergedTimer instead
	 */
	@Deprecated
	public PeriodicTimer(int period, int at, RandomTimer moreOrLess) {
		this.period = period;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	
	/**
	 * Getter of the period attribute
	 * @return An integer that corresponds to the period between two triggering of the PeriodicTimer instance 
	 */
	public int getPeriod() {
		return this.period;
	}
	
	
	/**
	 * Overloading the next method
	 */
	@Override
	public Integer next() {
		
		int next =  this.next;
		
		if(this.moreOrLess != null) {
			this.next = this.period + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
		}else {
			this.next = this.period;
		}
		
		return next;
	}
	
	/*@Override
	public Integer next(int since) {
		
		int next = (this.at - (since % this.period) + this.period) % this.period;
		
		if(this.moreOrLess != null) {
			next += this.moreOrLess.next() - this.moreOrLess.getMean();
			this.next = this.period * 2 - next;
		}else {
			this.next = this.period;
		}
		
		return next;
	}*/

	/**
	 * Overloading the hasNext method
	 */
	@Override
	public boolean hasNext() {
		return true;
	}

	public int getNext() {
		return next;
	}

	public RandomTimer getMoreOrLess() {
		return moreOrLess;
	}

}
