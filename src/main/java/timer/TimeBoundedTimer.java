package timer;

/**
 * This class bounds a Timer object thanks to a start and a stop time.
 *
 */
public class TimeBoundedTimer implements Timer {
	
	private Timer timer2bound; // Timer to be bounded
	private Integer startTime; // Start time
	private Integer stopTime; // Stop time
	
	private Integer next=0; // Next value of the iterator initialized to 0
	private int time=0;
	private boolean hasNext; // To know if the iterator is empty or is bounded

	/**
	 * Constructor with a stop time specified
	 * @param timer2bound
	 * @param startTime
	 * @param stopTime
	 */
	public TimeBoundedTimer(Timer timer2bound, int startTime, int stopTime) {
		this.timer2bound = timer2bound;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.init();
	}

	/**
	 * Constructor without any stop time specified.
	 * Stop time then takes the max value by default
	 * @param timer2bound
	 * @param startTime
	 */
	public TimeBoundedTimer(Timer timer2bound, int startTime) {
		this.timer2bound = timer2bound;
		this.startTime = startTime;
		this.stopTime = Integer.MAX_VALUE;
		this.init();
	}
	
	/**
	 * Calculates the value of the attribute next.
	 * It will be equal to the sum of the first times of the Timer
	 * until a time is greater than the start time
	 */
	private void init() {
		this.next = this.timer2bound.next();
		while (this.next < this.startTime) {
			this.next += this.timer2bound.next();
		}
		if(this.next<this.stopTime) {
			this.hasNext = true;
		}else {
			this.hasNext = false;
		}
	}

	/**
	 * @return false if the Timer is empty or if it is the
	 * max time before exceeding the stop time
	 * @return true else
	 */
	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	/**
	 * @return the next time of the iterator only if this next value 
	 * doesn't exceed the stop time
	 * @return null if the next value exceeds the stop time
	 */
	@Override
	public Integer next() {
		Integer next = this.next;
		this.time+=this.next;
		if(this.time < this.stopTime) {
			this.next = this.timer2bound.next();
		}else {
			next = null;
			this.hasNext=false;
		}
		return next;
	}

}
