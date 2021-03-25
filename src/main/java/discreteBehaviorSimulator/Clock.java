package discreteBehaviorSimulator;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
 * The Clock class is a virtual clock which accumulates  
 * the laps time of all simulated actions.
 * Each time an action is run, the global time is incremented
 * @version 0.4
 */
public class Clock {
	private static Clock instance = null; // No active instance of Clock at start
	
	private int time; // Total time incremented each time an action is run
	private int nextJump; // The time until the next action is run 
	private ReentrantReadWriteLock lock; // able or not to read and write
	private boolean virtual;
	
	
	private Set<ClockObserver> observers; // list of the listeners
	
	/*
	 * Constructor
	 * No parameters
	 */
	private Clock() {
		this.time = 0;
		this.nextJump=0; 
		this.lock = new ReentrantReadWriteLock();
		this.virtual = true;
		this.observers = new HashSet<ClockObserver>();
	}
	
	/**
	 * Creates a new instance of the Clock class
	 * only if 
	 * @return the Clock instance
	 */
	public static Clock getInstance() {
		if (Clock.instance == null) {
			Clock.instance = new Clock();
		}
		return Clock.instance;
	}
	
	/**
	 * Adds a listener
	 * @param o listener = instance of ClockObserver
	 */
	public void addObserver(ClockObserver o) {
		this.observers.add(o);
	}
	
	/**
	 * Removes a listener
	 * @param o listener = instance of ClockObserver
	 */
	public void removeObserver(ClockObserver o) {
		this.observers.remove(o);
	}
	
	/**
	 * Sets the clock virtual or not
	 * @param virtual true or false
	 */
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}
	
	/**
	 * checks if clock is virtual
	 * @return true or false
	 */
	public boolean isVirtual() {
		return this.virtual;
	}
	
	/**
	 * Updates the next jump time.
	 * @param nextJump
	 */
	public void setNextJump(int nextJump) {
		this.nextJump = nextJump;
		for(ClockObserver o:this.observers) {
			o.nextClockChange(this.nextJump);
		}
	}
	/*public void setTime(int time) throws IllegalAccessException {
		this.lock.lock();
		if (this.time < time) {
			this.time = time;
			for(ClockObserver o:this.observers) {
				o.ClockChange();
			}
		}else{
			this.lock.unlock();
			throw new IllegalAccessException("Set time error, cannot go back to the past !!!");
		}
		this.lock.unlock();
	}*/
	
	/**
	 * Increases the global time.
	 * @param time
	 * @throws Exception if the new jump is different to 
	 * the new time to be increased
	 */
	public void increase(int time) throws Exception {

		this.lockWriteAccess();

		if(time != this.nextJump) {
			throw new Exception("Unexpected time change");
		}
		this.time += time;
		for(ClockObserver o:this.observers) {
			o.clockChange(this.time);
		}
		this.unlockWriteAccess();
	}
	
	/**
	 * 
	 * @return the global time
	 */
	public long getTime() {
		if(this.virtual) {
			return this.time;
		}else {
			return new Date().getTime();
		}
	}
	
	public void lockReadAccess() {
		this.lock.readLock().lock();
	}
	
	public void unlockReadAccess() {
		this.lock.readLock().unlock();
	}
	
	public void lockWriteAccess() {
		this.lock.writeLock().lock();
	}
	public void unlockWriteAccess() {
		this.lock.writeLock().unlock();		
	}
	/**
	 * @return the global time
	 */
	public String toString() {
		return ""+this.time;
	}
}
