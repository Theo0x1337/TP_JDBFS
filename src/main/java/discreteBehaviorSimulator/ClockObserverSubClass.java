package discreteBehaviorSimulator;

/**
 * A new class which was not in the original code created 
 * to instantiate and test the clockObserver.
 * @author simon
 *
 */
public class ClockObserverSubClass implements ClockObserver {
	
	public boolean clockChangeCalled = false; // to know if the method is called
	public boolean nextClockChangeCalled = false; // to know if the method is called

	/**
	 * to know if the method is called
	 */
	public boolean isClockChangeCalled() {
		return clockChangeCalled;
	}

	/**
	 * to know if the method is called
	 */
	public boolean isNextClockChangeCalled() {
		return nextClockChangeCalled;
	}

	/**
	 * setter 
	 * @param clockChangeCalled
	 */
	public void setClockChangeCalled(boolean clockChangeCalled) {
		this.clockChangeCalled = clockChangeCalled;
	}

	/**
	 * setter 
	 * @param clockChangeCalled
	 */
	public void setNextClockChangeCalled(boolean nextClockChangeCalled) {
		this.nextClockChangeCalled = nextClockChangeCalled;
	}

	@Override
	public void clockChange(int time) {
		this.setClockChangeCalled(true);
	}

	@Override
	public void nextClockChange(int nextJump) {
		this.setNextClockChangeCalled(true);
	}

}
