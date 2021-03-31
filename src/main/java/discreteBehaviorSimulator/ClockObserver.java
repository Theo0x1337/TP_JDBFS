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
	 * @param nextJump
	 */
	public void nextClockChange(int nextJump);
	
	/**
	 * To know if the method is successfully called
	 * @return true or false
	 */
	public boolean isNextClockChangeCalled();
	
	/**
	 * To know if the method is successfully called
	 * @return true or false
	 */
	public boolean isClockChangeCalled();
}
