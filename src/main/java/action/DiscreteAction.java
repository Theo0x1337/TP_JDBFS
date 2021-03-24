package action;

import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import discreteBehaviorSimulator.LogFormatter;
import timer.Timer;

/**
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 *
 */

// TODO must inherit from Action
public class DiscreteAction implements DiscreteActionInterface {
	private Object object;
	private Method method;
	
	
	private Timer timmer;				// timer provides new lapsTime
	//private TreeSet<Integer> dates;	// obsolete, managed in timer 
	//private Vector<Integer> lapsTimes;// obsolete, managed in timer
	private Integer lapsTime; 			// waiting time (null if never used)
	
	private Logger logger;

	// Constructor
	
	private DiscreteAction() {
		// Start logger
			this.logger = Logger.getLogger("DAS");
			//this.logger = Logger.getLogger("APP");
			this.logger.setLevel(Level.ALL);
			this.logger.setUseParentHandlers(true);
			
			/*FileHandler logFile; 
			ConsoleHandler logConsole; 
			try{
				this.logFile = new FileHandler(this.getClass().getName() + ".log");
				//this.logFile.setFormatter(new SimpleFormatter());
				this.logFile.setFormatter(new LogFormatter());
				this.logConsole = new ConsoleHandler();
			}catch(Exception e){
				e.printStackTrace();
			}
			this.logger.addHandler(logFile);
			this.logger.addHandler(logConsole);*/
	}
	/**
	 * allows access to the object on which the action will be performed, the method to be applied to the object and the waiting time before applying the method
	 * @param o
	 * @param m
	 * @param timer
	 * @exception e
	 */
	public DiscreteAction(Object o, String m, Timer timmer){
		this();
		this.object = o;
		try{	
			this.method = o.getClass().getDeclaredMethod(m, new Class<?>[0]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.timmer = timmer;
		//this.updateTimeLaps();
	}
	
	// ATTRIBUTION
	/**
	 * decrease the time of discrete action 
	 * @param t
	 */
	public void spendTime(int t) {
		Integer old = this.lapsTime;
		if(this.lapsTime != null) {
			this.lapsTime -= t;
		}
		this.logger.log(Level.FINE, "[DA] operate spendTime on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
		//System.out.println(         "[DA] operate spendTime on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime() + "\n");
	}

	// RECUPERATION
	/*
	 * return the method to execute
	 */
	public Method getMethod(){
		return method;
	}
	/*
	 * return the last laps time without update
	 */
	public Integer getCurrentLapsTime(){
		return lapsTime;
	}
	/*
	 * get the object on which the method must be invoked
	 */
	public Object getObject(){
		return object;
	}



	// COMPARAISON
	/**
	 * compare discrete action according to the time before execution
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param c
	 */
	public int compareTo(DiscreteActionInterface c) {
		if (this.lapsTime == null) { // no lapstime is equivalent to infinity 
			return 1;
		}
		if (c.getCurrentLapsTime() == null) {// no lapstime is equivalent to infinity 
			return -1;
		}
    	if(this.lapsTime > c.getCurrentLapsTime()){
    		return 1;
    	}
    	if(this.lapsTime < c.getCurrentLapsTime()){
    		return -1;
    	}
		if(this.lapsTime == c.getCurrentLapsTime()){
			return 0;
		}
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Object : " + this.object.getClass().getName() + "\n Method : " + this.method.getName()+"\n Stat. : "+ this.timmer + "\n delay: " + this.lapsTime;

	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public DiscreteActionInterface next() {
		Integer old = this.lapsTime;
		this.lapsTime = this.timmer.next();
		this.logger.log(Level.FINE, "[DA] operate next on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
		//System.out.println("[DA] operate 'next' on " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime() + "\n");
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		Boolean more=false;
		if (this.timmer != null && this.timmer.hasNext()) {
			more = true;
		}/*else if (this.dates != null) {
			more = !this.dates.isEmpty();
		}else if (this.lapsTimes != null) {
			more = !this.lapsTimes.isEmpty();
		}*/
		return more;		
	}
	

}
