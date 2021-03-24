package timer;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Classe DateTimer representant l'interval de temps entre plusieurs dates
 *
 */
public class DateTimer  implements Timer {
	
	Vector<Integer> lapsTimes;
	Iterator<Integer> it;
	
	
	/**
	 * Constructeur de la classe DateTimer prenant un TreeSet en parametre
	 * @param date 
	 * 				ensemble de dates (TreeSet) dont on va calculer l'interval entre chaque dates et les ajouter a un iterateur
	 */
	public DateTimer(TreeSet<Integer> dates) {
		this.lapsTimes = new Vector<Integer>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();

	}
	
	
	/**
	 * Construsteur de la classe DateTimer prenant un Vecteur en parametre
	 * @param lapsTimes
	 * 					vecteur contenant les intervals de temps entre les différentes dates
	 */
	public DateTimer(Vector<Integer> lapsTimes) {
		this.lapsTimes = new Vector<Integer>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	/**
	 * Methode surchargée pour savoir si l'itérateur a un element suivant 
	 */
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	/**
	 * Methode surchargée pour recuperer le prochain element de l'iterateur
	 */
	public Integer next() {
		return it.next();
	}

}
