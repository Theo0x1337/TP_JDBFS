package timer;

/**
 * Classe OneShotTimer reprensentant un timer ne s'executant qu'une fois
 *
 */
public class OneShotTimer  implements Timer {
	
	//attribut de type entier reprensentant le moment ou le timer s'execute
	private Integer at;
	//attribut boolean permettant de savoir si le timer aura un prochain evenement
	private boolean hasNext;
	
	
	/**
	 * Constructeur de la classe OneShotTimer prenant un entier en parametre qui represente l'instant t ou le timer vas s'executer
	 * @param at
	 */
	public OneShotTimer(int at) {
		this.at = at;
		this.hasNext = true;
	}

	/**
	 * Surcharge de la methode hasNext() pour savoir si l'instance actuelle à un prochain element 
	 */
	@Override
	public boolean hasNext() {
		return this.hasNext;
	}
	
	
	
	/**
	 * Surcharge de la methode next(), return le moment ou le oneShotTimer s'execute et specifie qu'il n'y a plus d'autres evenements apres lui
	 */
	@Override
	public Integer next() {
		Integer next=this.at;
		this.at=null;
		this.hasNext = false;
		return next;
	}

}
