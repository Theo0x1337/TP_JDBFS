package timer;

/**
 * 
 *MergedTimer class allowing to merge two timers
 */

public class MergedTimer implements Timer{
	
	//attribute of type Timer representing the first timer to merge
	private Timer timer1;
	//Timer attribute representing the second timer to merge
	private Timer timer2;
	
	
	/**
	 * Constructeur de la classe MergedTimer prenant 2 timers en parametre
	 * @param timer1
	 * 					premier timer a fusionner
	 * @param timer2
	 * 					deuxieme timer a fusionner
	 */
	public MergedTimer(Timer timer1, Timer timer2) {
		this.timer1 = timer1;
		this.timer2 = timer2;
	}
	
	
	/**
	 * Overloaded method to know if the two timers have a next element
	 */
	@Override
	public boolean hasNext() {
		return (this.timer1.hasNext() && this.timer2.hasNext());
	}

	
	/**
	 * Overloaded method, if the instance of the MergedTimer class has a next element, then it returns the sum of the two timers to merge
	 */
	@Override
	public Integer next() {
		if(this.hasNext()) {
			return this.timer1.next() + this.timer2.next();
		}
		return null;
	}

}
