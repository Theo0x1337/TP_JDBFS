package timer;

/**
 * Classe PeriodicTimer, qui represente un timer s'executant de maniere periodique
 *
 */
public class PeriodicTimer implements Timer {

	//attribut entier representant la periode entre deux tick de timer
	private int period;
	//Atribut entier representant le moment ou le prochain tick de timer va avoir lieux
	private int next;
	//Attribut de type RandomTimer qui va generer des tick de timer de facon aleatoire seloin une loi de distribution
	private RandomTimer moreOrLess = null;
	
	
	/**
	 * Constructeur de la classe PeriodicTimer prenant une periode de temps en parametre
	 * @param at
	 * 			periode de temps entre deux declenchement de l'objet de type PeriodicTimer
	 */
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	
	/**
	 * Constructeur de la classe PeriodicTimer prenant une periode et un objet RandomTimer en parametre
	 * @param at
	 * 			periode de temps entre deux declenchement de l'objet de type PeriodicTimer
	 * @param moreOrLess
	 * 			RandomTimer permettant de simuler le prochain declanchement du PeriodicTimer
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
	 * Constructeur de la classe PeriodicTimer prenant en parametre une periode et autre entier representant le prochain déclenchement du PeriodicTimer
	 * @param period
	 * 			la periode de temps entre deux declanchement du PeriodicTimer
	 * @param at
	 * 			le moment du prochain declanchement du PeriodicTimer
	 */
	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}
	
	/**
	 * Constructeur de la classe PeriodicTimer prenant une periode et un objet RandomTimer en parametre
	 * @param period
	 *   		la periode de temps entre deux declanchement du timer
	 * @param at
	 * 			le moment du prochain declanchement du PeriodicTimer
	 * @param moreOrLess
	 * 			RandomTimer permettant de simuler le prochain declanchement du PeriodicTimer
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
	 * Getter de l'attribut period
	 * @return Un entier qui correspond a la periode entre deux declanchement de l'instance de PeriodicTimer 
	 */
	public int getPeriod() {
		return this.period;
	}
	
	
	/**
	 * Surcharge de la methode next
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
	 * Surcharge de la methode hasNext
	 */
	@Override
	public boolean hasNext() {
		return true;
	}

}
