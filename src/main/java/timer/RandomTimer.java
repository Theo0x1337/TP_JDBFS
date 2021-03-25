package timer;

import java.util.Random;
import java.util.Vector;

/**
 * @author Flavien Vernier
 *
 */



public class RandomTimer implements Timer {
	
	//enumeration of possible distribution laws
	public static enum randomDistribution {
		POISSON, EXP, POSIBILIST, GAUSSIAN;
	}
	
	//private static String randomDistributionString[] = {"POISSON", "EXP", "POSIBILIST", "GAUSSIAN"};
	
	//attribute containing a random number
	private Random r = new Random();
	//attribute containing the type of distribution chosen randomly
	private randomDistribution distribution;
	//attribute representing the rate for the exp law
	private double rate;
	//attribut representant la moyenne pour la loi de poisson
	private double mean;
	//attribute representing the mean for the fish law
	private double lolim;
	//attribute representing the upper limit for the Gaussian or probabilistic law
	private double hilim; 
	//private int width; 
	
	
	/**
	 * Method for randomly simulating a type of distribution among the fish, exp, gaussian and posibilistic distributions for the triggering of a timer
	 * @param distributionName
	 * name of the distribution that will be randomly chosen
	 * @return a randomDistribution object with a randomly chosen distribution law
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
	 * Get the name of the distribution law stored in the distribution attribute
	 * @param distribution
	 * @return a string corresponding to the name of the distribution law of the timer
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
	 * Cosntructor of the class specifying the calculations to be performed according to the probabilistic or Gaussian distribution law
	 * @param distribution
	 * the type of distribution
	 * @param lolim
	 * lower limit of the model
	 * @param hilim
	 * upper limit of the model
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
	 * Getter of the distribution attribute
	 * @return the name of the distribution type used by the instance
	 */
	public String getDistribution(){
		return this.distribution.name();
	}
	
	
	/**
	 * Method to obtain the different parameters according to the distribution law
	 * @return the parameters of the distribution law or null if none match
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
	
	/*
	 * Getter allowing to obtain the average of the distribution by returning the attribute mean
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
	 * Next time according to the distribution of the probabilistic law
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
	 * Next time according to the distribution of the law exp
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
	 * method that returns the next timer tick according to the gaussian distribution
	 */
	private int nextTimeGaussian(){
		return (int)this.lolim + (int)((this.r.nextGaussian() + 1.0)/2.0 * (this.hilim - this.lolim));
	}
	
	
	/**
	 * Overloading the hasNext method
	 */
	@Override
	public boolean hasNext() {
		return true;
	}
}
