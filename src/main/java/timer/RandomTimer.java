package timer;

import java.util.Random;
import java.util.Vector;

/**
 * @author Flavien Vernier
 *
 */



public class RandomTimer implements Timer {
	
	//enumeration contenant les loi de distribution possible
	public static enum randomDistribution {
		POISSON, EXP, POSIBILIST, GAUSSIAN;
	}
	
	//private static String randomDistributionString[] = {"POISSON", "EXP", "POSIBILIST", "GAUSSIAN"};
	
	//attribut contenant un nombre aleatoire
	private Random r = new Random();
	//attribut contenant le type de distribution choisie de maniere aleatoire
	private randomDistribution distribution;
	//attribut representant le rate pour la loi exp
	private double rate;
	//attribut representant la moyenne pour la loi de poisson
	private double mean;
	//attribut representant la limite basse pour la loi gausienne ou probabiliste
	private double lolim;
	//attribut representant la limite haute pour la loi gausienne ou probabiliste
	private double hilim; 
	//private int width; 
	
	
	/**
	 * Methode permettant de simuler de facon aleatoire un type de distribution parmis les lois de poisson, exp, gaussienne et posibiliste pour le declanchement d'un timer
	 * @param distributionName
	 * 			nom de la distribution qui va etre choisie de facon aleatoire
	 * @return un objet randomDistribution avec une loi de distribution choisie aleatoirement
	 */
	public static randomDistribution string2Distribution(String distributionName){
		return RandomTimer.randomDistribution.valueOf(RandomTimer.randomDistribution.class, distributionName.toUpperCase());
	}	
	
	/**
	 * Permet d'obtenir le nom de la loi de distribution stockee dans l'attribut distribution
	 * @param distribution
	 * @return une chaine de caractere correspondant au nom de la loi de distribution du timer
	 */
	public static String distribution2String(randomDistribution distribution){
		return distribution.name();
	}
	
	/**
	 * Cosntructeur de la classe pecifiant les calculs a realiser selon les lois de distribution (poisson et exp)
	 * @param distribution
	 * 				le type de distribution associee a l'instance RandomTimer
	 * @param param
	 * 				parametre de type double utilise pour les calculs des lois de poissons et exp
	 * @throws Exception 
	 */
	public RandomTimer(randomDistribution distribution, double param) throws Exception{
		if(distribution == randomDistribution.EXP ){
			this.distribution = distribution;
			this.rate = param;
			this.mean = 1/param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else if(distribution == randomDistribution.POISSON){
			this.distribution = distribution;
			this.rate = Double.NaN;
			this.mean = param;
			this.lolim = 0;
			this.hilim = Double.POSITIVE_INFINITY;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	/**
	 * Cosntructeur de la classe pecifiant les calculs a realiser selon la loi de distribution probabiliste ou gaussienne
	 * @param distribution
	 * 				le type de distribution
	 * @param lolim
	 * 				limite basse du model
	 * @param hilim
	 * 				limite haute du model
	 * @throws Exception
	 */
	public RandomTimer(randomDistribution distribution, int lolim, int hilim) throws Exception{
		if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			this.distribution = distribution;
			this.mean = lolim + (hilim - lolim)/2;
			this.rate = Double.NaN;
			this.lolim = lolim;
			this.hilim = hilim;
		}else{
			throw new Exception("Bad Timer constructor for selected distribution");
		}
	}
	
	
	/**
	 * Getter de l'attribut distribution
	 * @return le nom du type de distribution utilise par l'instance
	 */
	public String getDistribution(){
		return this.distribution.name();
	}
	
	
	/**
	 * Methode permettant d'obtenir les differents parametres selon les loi de distribution
	 * @return les parametres des loi de distribution ou null si aucun ne correspond
	 */
	public String getDistributionParam() {
		if(distribution == randomDistribution.EXP ){
			return "rate: " + this.rate;
		}else if(distribution == randomDistribution.POISSON){
			return "mean: " + this.mean;
		}else if(distribution == randomDistribution.POSIBILIST || distribution == randomDistribution.GAUSSIAN){
			return "lolim: " + this.lolim + " hilim: " + this.hilim;
		}
		
		return "null";
	}
	
	/*
	 * Getter permettant d'obtenir la moyenne de la distribution en renvoyant l'attribut mean
	 */
	public double getMean(){
		return this.mean;
	}
	
	/**
	 * Methode toString permettant un affichage des objets de type RandomTimer
	 * @return une chaine de caractere specifiant les informations sur l'objet d'instance RandomTimer
	 */
	public String toString(){
		String s = this.getDistribution();
		switch (this.distribution){
		case POSIBILIST :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		case POISSON :
			s += " mean:" + this.mean;
			break;
		case EXP :
			s += " rate:" + this.rate;
			break;
		case GAUSSIAN :
			s += " LoLim:" + this.lolim + " HiLim:" + this.hilim;
			break;
		}
		
		return s;
	}
	

	/* (non-Javadoc)
	 * @see methodInvocator.Timer#next()
	 */
	@Override
	public Integer next(){
		switch (this.distribution){
		case POSIBILIST :
			return this.nextTimePosibilist();
		case POISSON :
			return this.nextTimePoisson();
		case EXP :
			return this.nextTimeExp();
		case GAUSSIAN :
			return this.nextTimeGaussian();
		}
		return -1; // Theoretically impossible !!!
	}
	
	/*
	 * Equivalent to methodInvocator.RandomTimer#next()
	 * 
	 * @param since has no effect
	 * 
	 * @see methodInvocator.RandomTimer#next(int)
	 */
	/*@Override
	public Integer next(int since){
		return this.next();
	}*/
	
	/*
	 * Prochain temps selon la distribution de la loi probabiliste
	 */
	private int nextTimePosibilist(){
	    return (int)this.lolim + (int)(this.r.nextDouble() * (this.hilim - this.lolim));
	}
	
	/*
	 * Prochain temps selon la distribution de la loi exp
	 */
	private int nextTimeExp(){
	    return (int)(-Math.log(1.0 - this.r.nextDouble()) / this.rate);
	}
	
	
	/*
	 * Prochain temps selon la distribution de la loi de poisson
	 */
	private int nextTimePoisson() {
	    
	    double L = Math.exp(-this.mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * this.r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}   		
	    
	/*
	 * methode qui renvoi le prochain tick de timer selon la distribution gaussienne
	 */
	private int nextTimeGaussian(){
		return (int)this.lolim + (int)((this.r.nextGaussian() + 1.0)/2.0 * (this.hilim - this.lolim));
	}
	
	
	/**
	 * Surcharge de la methode hasNext
	 */
	@Override
	public boolean hasNext() {
		return true;
	}
}
