package eduneu.info6205.components;
import org.apache.commons.math3.distribution.PoissonDistribution;

public class Poisson {
	
	
	public static void demo() {
	PoissonDistribution distribution = new PoissonDistribution(0.824);
	System.out.println(distribution.probability(0));
	}
}
