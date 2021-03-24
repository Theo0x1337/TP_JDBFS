package timer;

/**
 * 
 *Classe MergedTimer permettant de fusionenr deux timer
 */

public class MergedTimer implements Timer{
	
	//attribut de type Timer representant le premier timer a fusionner
	private Timer timer1;
	//attribut de type Timer representant le deuxieme timer a fusionner
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
	 * Methode surchargee pour savoir si les deux timers on un element suivant
	 */
	@Override
	public boolean hasNext() {
		return (this.timer1.hasNext() && this.timer2.hasNext());
	}

	
	/**
	 * Methode surchargee, si l'instance de la classe MergedTimer a un element suivant, alors cela return la somme des deux timers a fusionner
	 */
	@Override
	public Integer next() {
		if(this.hasNext()) {
			return this.timer1.next() + this.timer2.next();
		}
		return null;
	}

}
