package action;

import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.Vector;

import timer.DateTimer;
import timer.Timer;

/**
 * @author flver
 *
 */
public class DiscreteActionOnOffDependent implements DiscreteActionInterface {
	
	protected DiscreteActionInterface onAction;
	protected DiscreteActionInterface offAction;
	protected DiscreteActionInterface currentAction;
	
	private Integer currentLapsTime;
	private Integer lastOffDelay=0;
	
	/**
	 * Construct an On Off dependence, first action (method) called is On, then method nextMethod() is called to select the next action.
	 * The default behavior of nextMethod() is to switch between On and Off actions.  It can be change by overloading. 
	 * 
	 * @param o
	 * @param on
	 * @param timerOn
	 * @param off
	 * @param timerOff
	 */
	/*public DiscreteActionOnOffDependent(Wo o, Method on, Timer timerOn, Method off, Timer timerOff){
		this.onAction = new DiscreteAction(o, on, timerOn);
		this.offAction = new DiscreteAction(o, off, timerOff);
		
		this.currentAction = this.onAction;
	}*/
	
	public DiscreteActionOnOffDependent(Object o, String on, Timer timerOn, String off, Timer timerOff){
		this.onAction = new DiscreteAction(o, on, timerOn);
		this.offAction = new DiscreteAction(o, off, timerOff);
		
		this.currentAction = this.offAction;
		this.currentLapsTime = 0;
	}
	/**
	 * @param datesOn
	 * @param datesOff
	 * @param timeLapseOn
	 * @param timeLapseOff
	 */
	private void dates2Timalapse(TreeSet<Integer> datesOn, TreeSet<Integer> datesOff, Vector<Integer> timeLapseOn, Vector<Integer> timeLapseOff) {
		Vector<Integer> currentTimeLapse;
		TreeSet<Integer> currentDates;
		Integer date=0;
		if(datesOn.first()<datesOff.first()) {
			currentTimeLapse = timeLapseOn;
			currentDates = datesOn;
		}else {
			currentTimeLapse = timeLapseOff;	
			currentDates = datesOff;		
		}
		Integer nextDate;
		
		while(datesOn.size()>0 || datesOff.size()>0) {
			nextDate = currentDates.first();
		
			currentTimeLapse.add(nextDate - date);
			currentDates.remove(nextDate);
		
			date = nextDate;
			
			if(currentDates == datesOn) {
				currentDates = datesOff;
				currentTimeLapse = timeLapseOff;
			}else {
				currentDates = datesOn;
				currentTimeLapse = timeLapseOn;			
			}
		}
		
	}
	/**
	 * Allows an action to be switched on and off
	 * @param o
	 * @param on
	 * @param datesOn
	 * @param off
	 * @param datesOff
	 */
	public DiscreteActionOnOffDependent(Object o, String on, TreeSet<Integer> datesOn, String off, TreeSet<Integer> datesOff){
		/*Vector<Integer> timeLapseOn = new Vector<Integer>();
		Vector<Integer> timeLapseOff = new Vector<Integer>();
		
		dates2Timalapse((TreeSet<Integer>)datesOn.clone(), (TreeSet<Integer>)datesOff.clone(), timeLapseOn, timeLapseOff);
		*/
		this.onAction = new DiscreteAction(o, on, new DateTimer(datesOn));
		this.offAction = new DiscreteAction(o, off, new DateTimer(datesOff));
		
		
		
		if(datesOn.first() < datesOff.first()){
			this.currentAction = this.onAction;
		}else{
			this.currentAction = this.offAction;
		}
	}
	/**
	 * Goes to the next action by retrieving the various timers
	 */
	public void nextAction(){
		if (this.currentAction == this.onAction){
			this.currentAction = this.offAction;
			this.currentAction = this.currentAction.next();
			this.lastOffDelay = this.currentAction.getCurrentLapsTime();
		}else{
			this.currentAction = this.onAction;
			this.currentAction = this.currentAction.next();
			this.currentAction.spendTime(this.lastOffDelay);
		}
	}
	/**
	 * decrease the time of discrete action 
	 * @param t
	 */
	public	void spendTime(int t) {
		this.currentAction.spendTime(t);
	}
	/*
	 * return the method to execute
	 */
	public Method getMethod() {
		return this.currentAction.getMethod();
	}
	/*
	 * return the last laps time without update
	 */
	public Integer getCurrentLapsTime() {
		return this.currentAction.getCurrentLapsTime();
	}
	/*
	 * get the object on which the method must be invoked
	 */
	public Object getObject() {
		return this.currentAction.getObject();
	}
	/**
	 * compare discrete action according to the time before execution
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param c
	 */
	public int compareTo(DiscreteActionInterface c) {
		return this.currentAction.compareTo(c);
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public DiscreteActionInterface next() {
		this.nextAction();
		return this;
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return this.onAction.hasNext() || this.offAction.hasNext();		
	}

	

}
