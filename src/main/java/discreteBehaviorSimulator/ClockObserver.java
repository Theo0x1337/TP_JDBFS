package discreteBehaviorSimulator;

/**
 * This interface is a listener of the class Clock.
 *
 */
public interface ClockObserver {
	/**
	 * Each time the global time is updated, this method is called
	 * @param time
	 */
	public void clockChange(int time);
	
	/**
	 * Each time the jump time is updated, this method is called
	 * @param time
	 */
	public void nextClockChange(int nextJump);
}
